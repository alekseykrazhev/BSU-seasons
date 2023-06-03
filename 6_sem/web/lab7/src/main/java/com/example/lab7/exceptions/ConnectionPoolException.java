package com.example.lab7.exceptions;

/**
 * Connection Pool Exception class
 */
public class ConnectionPoolException extends Exception {
    /**
     * Connection pool exception object constructor
     * @param message error message
     * @param throwable throwable
     */
    public ConnectionPoolException(String message, Throwable throwable) {
        super(message, throwable);
    }
}