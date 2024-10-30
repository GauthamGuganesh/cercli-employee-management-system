package com.cercli.builder;

import com.cercli.exception.EmployeeBuilderException;
import com.cercli.exception.InvalidEmployeeContactException;
import com.cercli.model.Employee;

import java.time.Instant;

public interface EmployeeBuild {

    public Employee build() throws InvalidEmployeeContactException;

    public EmployeeBuild withCreatedOn(Instant createdOn) throws EmployeeBuilderException;
}
