package com.example.weather.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the error info which is used in the controller for error handling.
 */
@Data
@AllArgsConstructor
public class ErrorDto {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}
