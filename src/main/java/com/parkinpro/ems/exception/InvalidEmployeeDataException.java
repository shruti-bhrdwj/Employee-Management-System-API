package com.parkinpro.ems.exception;

/**
 * Custom exception thrown for invalid employee data
 */
public class InvalidEmployeeDataException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
