package com.parkinpro.ems.exception;

/**
 * Custom exception thrown when an employee is not found
 */
public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
