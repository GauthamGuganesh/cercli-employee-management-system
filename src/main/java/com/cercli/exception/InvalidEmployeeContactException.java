package com.cercli.exception;

/**
 * This exception is thrown when an employee email has an invalid format.
 */
public class InvalidEmployeeContactException extends Exception {

    public InvalidEmployeeContactException(String message){
        super(message);
    }
}
