package com.cercli.builder;

import com.cercli.exception.EmployeeBuilderException;

public interface EmployeeId {

    public EmployeeName withEmployeeId(String employeeId) throws EmployeeBuilderException;
}
