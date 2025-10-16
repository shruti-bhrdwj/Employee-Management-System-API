package com.parkinpro.ems.dto;

import java.time.LocalDateTime;

/**
 * Custom error response structure for API errors
 */
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    } 

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    } 

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    } 

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
