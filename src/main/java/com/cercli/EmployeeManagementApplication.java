package com.cercli;

import com.cercli.db.ChangeLog;
import com.cercli.db.EmployeeDatabaseService;
import com.cercli.model.Employee;
import java.math.BigDecimal;
import java.util.UUID;

public class EmployeeManagementApplication {

    public static void main(String[] args) throws Exception {

        //Generating employee identifier
        String employeeId = UUID.randomUUID().toString();

        //Building an employee object.
        Employee newEmployee = new Employee.EmployeeBuilder()
                                        .withEmployeeId(employeeId)
                                        .havingName("Gautham")
                                        .workingAs("Software Engineer")
                                        .withSalary(new BigDecimal("10000.00"))
                                        .havingEmailAs("gautham.guganesh@gmail.com").build();


        //Instantiating databaseService to perform add, retrieve operations on the database.
        EmployeeDatabaseService databaseService = new EmployeeDatabaseService();

        //Adding an employee.
        System.out.println("Employee Added\n");
        databaseService.addEmployee(newEmployee);

        //Fetching an existing employee.
        Employee existingEmployee = databaseService.getEmployee(employeeId);
        System.out.println("Retrieving an existing employee\n");
        System.out.println(existingEmployee);
        System.out.println("\n\n");

        //Employee object is immutable. Hence, update operation creates a new object against the existing employeeId.
        Employee updateEmployee = new Employee.EmployeeBuilder()
                                        .withEmployeeId(existingEmployee.getEmployeeId())
                                        .havingName("Guganesh")
                                        .workingAs("Lead Software Engineer")
                                        .withSalary(new BigDecimal("20000.00"))
                                        .havingEmailAs(existingEmployee.getEmail())
                                        .withCreatedOn(existingEmployee.getCreatedAt()).build();

        System.out.println("Updating an existing employee\n");
        databaseService.updateEmployee(updateEmployee);
        System.out.println(databaseService.getEmployee(employeeId));
        System.out.println("\n\n");

        //Retrieving all employees
        System.out.println("Retrieving all employees\n");
        System.out.println(databaseService.getAllEmployees());
        System.out.println("\n\n");

        //A changeLog recording all actions on the database.
        System.out.println("Retrieving transaction history");
        for(ChangeLog log : databaseService.getDBChangeLog())
            System.out.println(log);
    }
}
