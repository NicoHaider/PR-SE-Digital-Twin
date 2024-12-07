package com.example.DigitalTwin.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {

    @Test
    public void testGettersAndSetters() {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus("404");
        errorResponse.setMessage("Not Found");
        errorResponse.setTimeStamp(1627846569000L);

        assertEquals("404", errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getMessage());
        assertEquals(1627846569000L, errorResponse.getTimeStamp());
    }

    @Test
    public void testDefaultValues() {
        ErrorResponse errorResponse = new ErrorResponse();

        assertNull(errorResponse.getStatus());
        assertNull(errorResponse.getMessage());
        assertEquals(0L, errorResponse.getTimeStamp());
    }
}
