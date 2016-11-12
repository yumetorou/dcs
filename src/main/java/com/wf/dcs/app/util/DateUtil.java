package com.wf.dcs.app.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtil {

    public static final String DATE = "MMM-dd-yyyy";
    public static final String TIME = "HH:mm";
    public static final String DATETIME = DATE + " " + TIME;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern(TIME);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATETIME);
    public static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = ISODateTimeFormat.dateTime();

    public static String toDateTimeString(LocalDateTime dateTime) {
        return null != dateTime ? dateTime.toString(DATETIME) : "";
    }

    /**
     * Parse to standard date time format.
     *
     * @param dateTime date time
     * @return the {@link DateTime}
     */
    public static DateTime toDateTime(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }

        if (dateTime.length() == DATE.length()) {
            return DATE_FORMATTER.parseDateTime(dateTime);
        } else {
            return DATE_TIME_FORMATTER.parseDateTime(dateTime);
        }
    }

    /**
     * Parse to standard date format.
     *
     * @param date date
     * @return the {@link LocalDate}
     */
    public static LocalDate toLocalDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }

        return DATE_FORMATTER.parseLocalDate(date);
    }

    /**
     * Parse to standard time format.
     *
     * @param time time
     * @return the {@link LocalTime}
     */
    public static LocalTime toLocalTime(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }

        return TIME_FORMATTER.parseLocalTime(time);
    }

    /**
     * Parse to standard date time format.
     *
     * @param dateTime date time
     * @return the {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }

        if (dateTime.length() == DATE.length()) {
            return DATE_FORMATTER.parseLocalDateTime(dateTime);
        } else if (dateTime.length() == DATETIME.length()) {
            return DATE_TIME_FORMATTER.parseLocalDateTime(dateTime);
        } else {
            return ISO_DATE_TIME_FORMATTER.parseLocalDateTime(dateTime);
        }
    }

    public static String getTimeDifferenceInString(Integer minutes) {
        LocalDateTime startTime = new LocalDateTime();
        LocalDateTime endTime = new LocalDateTime();

        endTime = endTime.plusMinutes(minutes);

        Period period = new Interval(
                startTime.toDate().getTime(),
                endTime.toDate().getTime()).toPeriod();

        return period.getHours() + " Hour(s)" + period.getMinutes() + " Minute(s)";
    }

    public static Period getTimeDifference(Integer minutes) {
        LocalDateTime startTime = new LocalDateTime();
        LocalDateTime endTime = new LocalDateTime();

        endTime = endTime.plusMinutes(minutes);

        return new Interval(
                startTime.toDate().getTime(),
                endTime.toDate().getTime()).toPeriod();
    }
}