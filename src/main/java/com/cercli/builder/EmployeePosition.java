package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;

public interface EmployeePosition {

    public EmployeeSalary workingAs(String position) throws EmployeeBuilderException;
}
