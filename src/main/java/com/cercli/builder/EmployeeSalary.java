package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;

import java.math.BigDecimal;

public interface EmployeeSalary {

    public EmployeeEmail withSalary(BigDecimal salary) throws EmployeeBuilderException;
}
