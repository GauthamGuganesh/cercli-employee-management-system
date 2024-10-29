package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;

public interface EmployeeId {

    public EmployeeName withEmployeeId(String employeeId) throws EmployeeBuilderException;
}
