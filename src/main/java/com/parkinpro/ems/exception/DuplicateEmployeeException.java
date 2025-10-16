package com.parkinpro.ems.exception;

/**
 * Custom exception thrown when attempting to add a duplicate employee
 */
public class DuplicateEmployeeException extends Exception {
    private static final long serialVersionUID = 1L;

	public DuplicateEmployeeException(String message) {
        super(message);
    }
}
