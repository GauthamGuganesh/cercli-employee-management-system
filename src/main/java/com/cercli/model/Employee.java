package com.cercli.model;

import com.cercli.Exception.EmployeeBuilderException;
import com.cercli.Exception.InvalidEmployeeContactException;
import com.cercli.builder.*;
import com.cercli.util.EmployeeUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author P.Gautham Guganesh
 * @Description The model class representing an employee
 */
public class Employee {

    private final String employeeId;
    private final String name;
    private final String position;
    private final String email;
    private final BigDecimal salary;
    private final Instant createdAt;
    /**
     *
     * @param employeeId - Unique employee identifier.
     * @param name - Name of the employee.
     * @param position - The position the employee holds in the organisation.
     * @param email - The email provided to contact the employee.
     * @param salary - The salary drawn by the employee
     * @Return - A new instance of the employee object created with the provided parameters.
     *
     * <br>
     * <br>
     * Note that the Employee instance is created and persisted under UTC timezone for uniformity
     * across the application. Depending upon the request being served, the appropriate timezone-conversion
     * will be carried out.
     */
    private Employee(String employeeId, String name, String position, String email, BigDecimal salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.createdAt = Instant.now();
    }

    /**
     *
     * @return - A String representation of the Employee Object.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Employee[employeeId = ").append(employeeId).append(", ");
        builder.append("name = ").append(name).append(", ");
        builder.append("position = ").append(position).append(", ");
        builder.append("email = ").append(email).append(", ");
        builder.append("salary = ").append(salary.toString()).append(", ");
        builder.append("createdAt = ").append(createdAt).append("]");
        return builder.toString();
    }

    /**
     *
     * @param employeeId - Unique employee identifier.
     * @param name - Name of the employee.
     * @param position - The position the employee holds in the organisation.
     * @param email - The email provided to contact the employee.
     * @param salary - The salary drawn by the employee
     * @return - A new instance of the employee object created with the provided parameters.
     * @throws InvalidEmployeeContactException - Thrown when contact details like email, mobile ,address are invalid.
     */
    private static Employee createEmployee(String employeeId, String name, String position, String email, BigDecimal salary) throws InvalidEmployeeContactException {
        return new Employee(employeeId, name, position, email, salary);
    }

    /**
     *
     * @param employee - An employee instance to be copied.
     * @return - A new immutable instance of the input employee object.
     * @throws InvalidEmployeeContactException - Thrown when employee contact details like email are invalid.
     */
    public static Employee copyOf(Employee employee) throws InvalidEmployeeContactException {
        if(employee == null)
            throw new NullPointerException("Employee object is null. A new copy cannot be created.");

        EmployeeUtils.validateEmail(employee.email);
        return new Employee(employee.employeeId, employee.name, employee.position, employee.email, employee.salary);
    }

    /**
     * <p>
     * A static fluent-builder class that helps construct the Employee object.
     * All userInput parameters are assumed to be mandatory without which
     * building an Employee object will not be possible.
     * </p>
     * Implements <code> EmployeeId, EmployeeName, EmployeePosition, EmployeeSalary, EmployeeEmail, EmployeeBuild </code> interfaces.
     * Each interface returns the type of the next type down the chain.
     *
     * <p>
     * The builder methods throw <code>EmployeeBuilderException</code> - if the input values or null or empty.
     * <code>InvalidEmployeeContactException</code> - if the employee email is invalid.
     * </p>
     */
    public static class EmployeeBuilder implements EmployeeId, EmployeeName, EmployeePosition,
            EmployeeSalary, EmployeeEmail, EmployeeBuild {

        private String employeeId;
        private String name;
        private String position;
        private String email;
        private BigDecimal salary;

        public EmployeeBuilder() {

        }

        @Override
        public Employee build() throws InvalidEmployeeContactException {
            return Employee.createEmployee(employeeId, name, position, email, salary);
        }

        @Override
        public EmployeeBuild havingEmailAs(String email) throws EmployeeBuilderException, InvalidEmployeeContactException {
            if(EmployeeUtils.isEmptyString(email))
                throw new EmployeeBuilderException("Email cannot be null or empty");

            EmployeeUtils.validateEmail(email);
            this.email = email;
            return this;
        }

        @Override
        public EmployeeName withEmployeeId(String employeeId) throws EmployeeBuilderException {
            if(EmployeeUtils.isEmptyString(employeeId))
                throw new EmployeeBuilderException("Employee id cannot be null or empty");
            this.employeeId = employeeId;
            return this;
        }

        @Override
        public EmployeePosition havingName(String name) throws EmployeeBuilderException {
            if(EmployeeUtils.isEmptyString(name))
                throw new EmployeeBuilderException("Employee name cannot be null or empty");
            this.name = name;
            return this;
        }

        @Override
        public EmployeeSalary workingAs(String position) throws EmployeeBuilderException {
            if(EmployeeUtils.isEmptyString(position))
                throw new EmployeeBuilderException("Employee position cannot be null or empty");
            this.position = position;
            return this;
        }

        @Override
        public EmployeeEmail withSalary(BigDecimal salary) throws EmployeeBuilderException {
            if(salary == null)
                throw new EmployeeBuilderException("Salary cannot be null");
            this.salary = salary;
            return this;
        }
    }

}