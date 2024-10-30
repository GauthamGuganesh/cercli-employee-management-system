package com.cercli.builder;

import com.cercli.exception.EmployeeBuilderException;

public interface EmployeePosition {

    public EmployeeSalary workingAs(String position) throws EmployeeBuilderException;
}
