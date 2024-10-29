package com.cercli.db;

import com.cercli.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDatabase {

    private Map<String, Employee> employees = new HashMap<>();

    public void saveOrUpdateEmployee(String employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public Employee getEmployee(String employeeId){
        return employees.get(employeeId);
    }
}
