package com.example.lab2_part2.Exception;

/**
 * DAO Exception implementation
 */
public class DAOException extends Throwable {
    /**
     * DAO Exception main constructor
     * @param message message
     * @param throwable throwable
     */
    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
