package com.cercli.builder;

import com.cercli.exception.EmployeeBuilderException;
import com.cercli.exception.InvalidEmployeeContactException;

public interface EmployeeEmail {

    public EmployeeBuild havingEmailAs(String email) throws EmployeeBuilderException, InvalidEmployeeContactException;
}
