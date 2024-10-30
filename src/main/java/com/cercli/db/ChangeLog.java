package com.cercli.db;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author P.GauthamGuganesh
 * @Description Class defining the events that take place on the database.
 */
public class ChangeLog {
    private final ChangeLogAction action;
    private final String value;
    private final Instant timeStamp;
    public ChangeLog(ChangeLogAction action, String value, Instant timeStamp){
        this.action = action;
        this.value = value;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[action = ").append(action.toString()).append(", ");
        builder.append("value = ").append(value).append(", ");
        builder.append("timestamp = ").append(timeStamp).append("]");

        return builder.toString();
    }
}
