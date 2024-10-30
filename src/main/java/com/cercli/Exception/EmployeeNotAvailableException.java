package com.cercli.Exception;

/**
 * This exception is thrown when the database is unable to fetch an employee object
 * for an employeeId.
 */
public class EmployeeNotAvailableException extends Exception{

    public EmployeeNotAvailableException(String message){
        super(message);
    }
}
