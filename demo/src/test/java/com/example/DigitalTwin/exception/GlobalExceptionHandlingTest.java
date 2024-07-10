package com.example.DigitalTwin.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlingTest {

    @InjectMocks
    private GlobalExceptionHandling globalExceptionHandling;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleAppException() {
        AppException ex = new AppException("Test AppException");
        WebRequest request = null; // You can mock this if needed

        ResponseEntity<ErrorResponse> response = globalExceptionHandling.userHandlerException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test AppException", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertTrue(response.getBody().getTimeStamp() > 0);
    }

    @Test
    public void testHandleNotFoundException() {
        NotFoundException ex = new NotFoundException("Test NotFoundException");
        WebRequest request = null; // You can mock this if needed

        ResponseEntity<ErrorResponse> response = globalExceptionHandling.userHandlerException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test NotFoundException", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getBody().getStatus());
        assertTrue(response.getBody().getTimeStamp() > 0);
    }

    @Test
    public void testHandleValidationErrors() throws Exception {
        BindingResult bindingResult = new BindException(this, "test");
        bindingResult.addError(new FieldError("test", "field", "defaultMessage"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Map<String, List<String>>> response = globalExceptionHandling.handleValidationErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().get("errors").size());
        assertEquals("defaultMessage", response.getBody().get("errors").get(0));
    }
}
