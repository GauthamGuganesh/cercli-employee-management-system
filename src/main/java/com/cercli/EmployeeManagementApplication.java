package com.cercli;

import com.cercli.db.ChangeLog;
import com.cercli.db.EmployeeDatabaseService;
import com.cercli.model.Employee;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EmployeeManagementApplication {

    public static void main(String[] args) throws Exception {
        String employeeId = UUID.randomUUID().toString();
        Employee newEmployee = new Employee.EmployeeBuilder()
                                        .withEmployeeId(employeeId)
                                        .havingName("Gautham")
                                        .workingAs("Software Engineer")
                                        .withSalary(new BigDecimal(10000.00))
                                        .havingEmailAs("gautham.guganesh@gmail.com").build();


        EmployeeDatabaseService databaseService = new EmployeeDatabaseService();
        databaseService.addEmployee(newEmployee, employeeId);
        Employee existingEmployee = databaseService.getEmployee(employeeId);
        System.out.println(existingEmployee);

        Thread.sleep(2000);
        Employee updateEmployee = new Employee.EmployeeBuilder()
                                        .withEmployeeId(existingEmployee.getEmployeeId())
                                        .havingName("Guganesh")
                                        .workingAs("Lead Software Engineer")
                                        .withSalary(new BigDecimal(20000.00))
                                        .havingEmailAs(existingEmployee.getEmail())
                                        .withCreatedOn(existingEmployee.getCreatedAt()).build();


        databaseService.updateEmployee(updateEmployee, employeeId);
        System.out.println(databaseService.getEmployee(employeeId));

        databaseService.getAllEmployees();

        for(ChangeLog log : databaseService.getDBChangeLog())
            System.out.println(log);
    }
}
