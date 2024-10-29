package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;
import com.cercli.Exception.InvalidEmployeeContactException;

public interface EmployeeEmail {

    public EmployeeBuild havingEmailAs(String email) throws EmployeeBuilderException, InvalidEmployeeContactException;
}
