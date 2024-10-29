package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;

public interface EmployeeName {

    public EmployeePosition havingName(String name) throws EmployeeBuilderException;
}
