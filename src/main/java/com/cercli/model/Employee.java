package com.cercli.model;

import com.cercli.exception.EmployeeBuilderException;
import com.cercli.exception.InvalidEmployeeContactException;
import com.cercli.builder.*;
import com.cercli.util.CommonUtils;
import com.cercli.util.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author P.Gautham Guganesh
 * @Description The model class representing an employee
 */
public class Employee {

    private static final ZoneId serverTimeZoneId = CommonUtils.getServerTimeZoneId();
    private final String employeeId;
    private final String name;
    private final String position;
    private final String email;
    private final BigDecimal salary;
    private final Instant createdAt;
    private Instant modifiedAt;
    public void setModifiedAt(Instant modifiedAt) { this.modifiedAt = modifiedAt; }

    public String getEmployeeId() { return employeeId; }
    public String getName(){ return name; }
    public String getPosition() { return position; }
    public String getEmail() { return email; }
    public BigDecimal getSalary() { return salary; }
    public Instant getCreatedAt() { return createdAt; }
    public String getCreatedAtWithTimeZone() {
        return CommonUtils.getFormattedDateTimeWithTimeZone(createdAt, serverTimeZoneId);
    }
    public String getModifiedAtWithTimeZone() {
        if(modifiedAt != null) return CommonUtils.getFormattedDateTimeWithTimeZone(modifiedAt, serverTimeZoneId);
        return "";
    }

    /**
     *
     * @param employeeId - Unique employee identifier.
     * @param name - Name of the employee.
     * @param position - The position the employee holds in the organisation.
     * @param email - The email provided to contact the employee.
     * @param salary - The salary drawn by the employee
     *
     * <br>
     * <br>
     * Note that the Employee instance is created and persisted under UTC timezone for uniformity
     * across the application. Depending upon the request being served, the appropriate timezone-conversion
     * will be carried out.
     */
    private Employee(String employeeId, String name, String position, String email, BigDecimal salary, Instant createdOn) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.createdAt = (createdOn == null) ? Instant.now() : createdOn;
    }

    /**
     *
     * @return - A String representation of the Employee Object.
     */
    @Override
    public String toString(){
         String modifiedAtWithTimeZoneOffset = "";
        if(modifiedAt != null)  modifiedAtWithTimeZoneOffset = ZonedDateTime.ofInstant(modifiedAt, serverTimeZoneId)
                                                                              .format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT));

        StringBuilder builder = new StringBuilder();
        builder.append("Employee[employeeId = ").append(employeeId).append(", ");
        builder.append("name = ").append(name).append(", ");
        builder.append("position = ").append(position).append(", ");
        builder.append("email = ").append(email).append(", ");
        builder.append("salary = ").append(salary.toString()).append(", ");
        builder.append("createdAt = ").append(getCreatedAtWithTimeZone()).append(", ");
        builder.append("modifiedAt = ").append(getModifiedAtWithTimeZone()).append("]");
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
    private static Employee createEmployee(String employeeId, String name, String position, String email, BigDecimal salary, Instant createdOn) throws InvalidEmployeeContactException {
        return new Employee(employeeId, name, position, email, salary, createdOn);
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
        private Instant createdOn;
        public EmployeeBuilder() {

        }

        @Override
        public Employee build() throws InvalidEmployeeContactException {
            return Employee.createEmployee(employeeId, name, position, email, salary, createdOn);
        }

        @Override
        public EmployeeBuild withCreatedOn(Instant createdOn) throws EmployeeBuilderException {
            if(createdOn == null)
                throw new EmployeeBuilderException("CreatedOn cannot be null");

            this.createdOn = createdOn;
            return this;
        }

        @Override
        public EmployeeBuild havingEmailAs(String email) throws EmployeeBuilderException, InvalidEmployeeContactException {
            if(CommonUtils.isEmptyString(email))
                throw new EmployeeBuilderException("Email cannot be null or empty");

            CommonUtils.validateEmail(email);
            this.email = email;
            return this;
        }

        @Override
        public EmployeeName withEmployeeId(String employeeId) throws EmployeeBuilderException {
            if(CommonUtils.isEmptyString(employeeId))
                throw new EmployeeBuilderException("Employee id cannot be null or empty");
            this.employeeId = employeeId;
            return this;
        }

        @Override
        public EmployeePosition havingName(String name) throws EmployeeBuilderException {
            if(CommonUtils.isEmptyString(name))
                throw new EmployeeBuilderException("Employee name cannot be null or empty");
            this.name = name;
            return this;
        }

        @Override
        public EmployeeSalary workingAs(String position) throws EmployeeBuilderException {
            if(CommonUtils.isEmptyString(position))
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
