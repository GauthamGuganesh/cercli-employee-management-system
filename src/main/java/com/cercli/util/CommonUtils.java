package com.cercli.util;

import com.cercli.exception.InvalidEmployeeContactException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author P.GauthamGuganesh
 * @Description Contains utility methods to carry out common functionalities re-used across the application.
 */
public class CommonUtils {

    /**
     * Validates the input email using a regularExpression.
     *
     * @param email - the email to be validated.
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

    /**
     * @param value - The string value to be checked.
     * @return A <code>boolean</code> signifying null or emptiness of a string.
     */
    public static boolean isEmptyString(String value){
        return value == null || value.isEmpty();
    }

    /**
     * Simulates time-zone information. The offsetId can be mocked to simulate
     * any timezone that is required for the application to operate on.
     * @return The mocked server time-zoneId
     */
    public static ZoneId getServerTimeZoneId() {
        String offsetId = "+04:00";
        return ZoneId.ofOffset(Constants.TIMEZONE_UTC, ZoneOffset.of(offsetId));
    }

    /**
     * Converts the given UTC dateTime to a formatted string-representation in the target time-zone.
     * @param dateTime - The time object to be converted.
     * @param timeZoneId - The time-zone to convert to.
     * @return The formatted dateTime string in the server time-zone.
     */
    public static String getFormattedDateTimeWithTimeZone(Instant dateTime, ZoneId timeZoneId) {
        return ZonedDateTime.ofInstant(dateTime, timeZoneId)
                                .format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT));
    }
}
