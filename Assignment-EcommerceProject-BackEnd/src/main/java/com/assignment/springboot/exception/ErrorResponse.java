package com.assignment.springboot.exception;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String errors;

    public ErrorResponse(Date timestamp, String message, String errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
