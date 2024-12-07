package com.example.DigitalTwin.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppExceptionTest {

    @Test
    public void testAppExceptionMessage() {
        String errorMessage = "An error occurred";
        AppException exception = new AppException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testAppExceptionIsRuntimeException() {
        AppException exception = new AppException("An error occurred");

        assertTrue(exception instanceof RuntimeException);
    }
}
