package com.cercli;

import com.cercli.model.Employee;

import java.math.BigDecimal;
import java.util.UUID;

public class EmployeeManagementApplication {

    public static void main(String[] args) throws Exception {
        Employee newEmployee = new Employee.EmployeeBuilder()
                                        .withEmployeeId(UUID.randomUUID().toString())
                                        .havingName("Gautham")
                                        .workingAs("Software Engineer")
                                        .withSalary(new BigDecimal(10000.00))
                                        .havingEmailAs("gautham.guganesh@gmail.com").build();

        System.out.println(newEmployee);
    }
}
