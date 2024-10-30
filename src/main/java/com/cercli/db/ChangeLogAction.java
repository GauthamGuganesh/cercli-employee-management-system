package com.cercli.db;

/**
 * @Author P.Gautham Guganesh
 * @Description  An enum describing the actions available to be done on the employeeDatabase.
 */
public enum ChangeLogAction {
    ADD("ADD"),
    UPDATE("UPDATE"),
    GET_EMPLOYEE("GET_EMPLOYEE"),
    GET_ALL_EMPLOYEES("GET_ALL_EMPLOYEES");

    private final String value;
    ChangeLogAction(String value) {
        this.value = value;
    }
}
