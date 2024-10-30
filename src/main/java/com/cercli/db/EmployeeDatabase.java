package com.cercli.db;

import com.cercli.Exception.EmployeeNotAvailableException;
import com.cercli.model.Employee;

import java.util.*;

/**
 * @author - P.Gautham Guganesh
 * @Description The employee database holding all employee objects.
 */
public class EmployeeDatabase {

    private static EmployeeDatabase employeeDatabase;
    private final Map<String, Employee> employees = new HashMap<>();
    private final List<ChangeLog> dbChangeLog = new ArrayList<>();
    private EmployeeDatabase() {

    }

    /**
     * Creates a singleton EmployeeDatabase object used throughout the application.
     * @return The employeeDatabase instance.
     */
    protected static synchronized EmployeeDatabase getInstance(){
        if(employeeDatabase == null) employeeDatabase = new EmployeeDatabase();
        return employeeDatabase;
    }

    /**
     * Saves or updates the employee object against the employeeId in the database.
     * @param employeeId
     * @param employee
     * @throws IllegalArgumentException when the Employee object or employeeId is null.
     */
    public synchronized void saveOrUpdateEmployee(String employeeId, Employee employee) throws IllegalArgumentException {
        if(employee == null) throw new IllegalArgumentException("Employee cannot be null for database save/update ");
        if(employeeId == null) throw new IllegalArgumentException(("EmployeeId cannot be null for database save/update"));
        employees.put(employeeId, employee);
    }

    /**
     * Retrieves an employee object for the given employeeId.
     * @param employeeId
     * @return The retrieved Employee object.
     * @throws EmployeeNotAvailableException - If no employee is mapped against the input employeeId
     */
    public Employee getEmployee(String employeeId) throws EmployeeNotAvailableException {
        Employee employee = employees.get(employeeId);
        if(employee == null) throw new EmployeeNotAvailableException("Employee not present in database for employeeId - " + employeeId);

        return employee;
    }

    /**
     * Retrieves all employee objects in a database.
     * @return The list of all employees available in the database
     */
    public List<Employee> getAllEmployess() {
        if(employees.isEmpty()) return Collections.EMPTY_LIST;
        return new ArrayList<>(employees.values());
    }

    /**
     * Retrieves a string representation of all employees in a database.
     * @return The string representation of all employees in database
     */
    @Override
    public String toString(){
        if(employees.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        employees.values().forEach(employee-> stringBuilder.append(employee.toString()).append("\n"));

        return stringBuilder.toString();
    }

    /**
     * Adds a changeLog event to the dbChangeLog.
     * @param changeLog
     */
    public synchronized void addChangeLog(ChangeLog changeLog) {
        dbChangeLog.add(changeLog);
    }

    /**
     * Retrieves all changeLoge events from the database.
     * @return - The List of changeLog events for the database.
     */
    public List<ChangeLog> retrieveDatabaseChangeLog(){
        return Collections.unmodifiableList(dbChangeLog);
    }
}
