package ru.vigovskiy.strike_team.util;

import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
}
