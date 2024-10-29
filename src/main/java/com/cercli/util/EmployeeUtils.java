package com.cercli.util;

import com.cercli.Exception.InvalidEmployeeContactException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author P.GauthamGuganesh
 * @Description Contains utility methods to carry out common functionalities re-used across the application.
 */
public class EmployeeUtils {

    /**
     *
     * @param email
     * @throws InvalidEmployeeContactException
     * <br>
     * <br>
     * Throws an exception if the provided email is invalid. Does not return any value otherwise.
     */
    public static void validateEmail(String email) throws InvalidEmployeeContactException {
        if(email == null || email.isEmpty())
            throw new InvalidEmployeeContactException("Employee email value is null or empty.");

        Pattern emailValidationPattern = Pattern.compile(Constants.EMAIL_VALIDATION_REGEX);
        Matcher emailMatcher = emailValidationPattern.matcher(email);
        if(!emailMatcher.matches())
            throw new InvalidEmployeeContactException("Invalid employee email.");
    }

    public static boolean isEmptyString(String value){
        if(value == null || value.isEmpty()) return true;

        return false;
    }
}
