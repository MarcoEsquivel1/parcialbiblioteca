package com.example.parcialbiblioteca.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter ;
import lombok.Setter ;
@Getter
@Setter
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
    private Map<String, List<String>> errors;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description, Map<String, List<String>> errors) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.errors = errors;
    }

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
