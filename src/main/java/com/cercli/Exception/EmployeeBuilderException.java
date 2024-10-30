package com.cercli.Exception;

/**
 * This exception is thrown when invalid input is provided while building the Employee object
 */
public class EmployeeBuilderException extends Exception {

    public EmployeeBuilderException(String message){
        super(message);
    }
}
