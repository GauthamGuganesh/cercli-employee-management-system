package com.cercli.builder;

import com.cercli.Exception.InvalidEmployeeContactException;
import com.cercli.model.Employee;

public interface EmployeeBuild {

    public Employee build() throws InvalidEmployeeContactException;
}
