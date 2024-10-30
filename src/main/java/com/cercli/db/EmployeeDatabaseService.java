package com.cercli.db;

import com.cercli.exception.EmployeeNotAvailableException;
import com.cercli.model.Employee;

import java.time.Instant;
import java.util.List;

/**
 * @author P.Gautham Guganesh
 * @Description Service class that provides a set of methods to interact with the database.
 */
public class EmployeeDatabaseService {

    public EmployeeDatabaseService() {

    }

    /**
     * Adds an object to the Employee Database and records the action.
     * @param employee
     */
    public void addEmployee(Employee employee) {
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance();
        employeeDatabase.saveOrUpdateEmployee(employee);
        employeeDatabase.addChangeLog(new ChangeLog(ChangeLogAction.ADD, employee.toString(), Instant.now()));
    }

    /**
     * Updates an existing employee in the database and records the action.
     * @param employee
     */
    public void updateEmployee(Employee employee) throws EmployeeNotAvailableException {
        employee.setModifiedAt(Instant.now());
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance();
        if(getEmployee(employee.getEmployeeId()) != null) {
            employeeDatabase.saveOrUpdateEmployee(employee);
            employeeDatabase.addChangeLog(new ChangeLog(ChangeLogAction.UPDATE, employee.toString(), Instant.now()));
        }
    }

    /**
     * Fetches an existing employee in the database and records the action.
     * @param employeeId
     * @return The fetched employee object
     * @throws EmployeeNotAvailableException - When the employee is not available in the database for an employeeId.
     */
    public Employee getEmployee(String employeeId) throws EmployeeNotAvailableException {
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance();
        employeeDatabase.addChangeLog(new ChangeLog(ChangeLogAction.GET_EMPLOYEE, employeeId, Instant.now()));
        return employeeDatabase.getEmployee(employeeId);
    }

    /**
     * Fetches all employees in the database.
     * @return List of all employees if available. Else an empty list.
     */
    public List<Employee> getAllEmployees(){
        EmployeeDatabase employeeDatabase = EmployeeDatabase.getInstance();
        employeeDatabase.addChangeLog(new ChangeLog(ChangeLogAction.GET_ALL_EMPLOYEES, "N/A", Instant.now()));
        return employeeDatabase.getAllEmployess();
    }

    /**
     * Fetches the dbChangeLog
     * @return The list of changeLog actions on the database.
     */
    public List<ChangeLog> getDBChangeLog(){
        return EmployeeDatabase.getInstance().retrieveDatabaseChangeLog();
    }
}
