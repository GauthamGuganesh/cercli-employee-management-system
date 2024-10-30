package com.cercli.builder;

import com.cercli.exception.EmployeeBuilderException;

public interface EmployeeName {

    public EmployeePosition havingName(String name) throws EmployeeBuilderException;

}
