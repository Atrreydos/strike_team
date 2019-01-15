package ru.vigovskiy.strike_team.util;

public class StringUtil {

    public static boolean isNotBlank(String s) {
        return (s != null && !s.trim().isEmpty());
    }
}
