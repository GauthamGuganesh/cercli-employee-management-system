package com.cercli.builder;

import com.cercli.Exception.EmployeeBuilderException;
import com.cercli.Exception.InvalidEmployeeContactException;
import com.cercli.model.Employee;

import java.time.Instant;

public interface EmployeeBuild {

    public Employee build() throws InvalidEmployeeContactException;

    public EmployeeBuild withCreatedOn(Instant createdOn) throws EmployeeBuilderException;
}
