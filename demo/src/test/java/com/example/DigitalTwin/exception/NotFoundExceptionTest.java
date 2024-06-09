package com.example.DigitalTwin.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundExceptionTest {

    @Test
    public void testNotFoundExceptionMessage() {
        String errorMessage = "Resource not found";
        NotFoundException exception = new NotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testNotFoundExceptionIsRuntimeException() {
        NotFoundException exception = new NotFoundException("Resource not found");

        assertTrue(exception instanceof RuntimeException);
    }
}
