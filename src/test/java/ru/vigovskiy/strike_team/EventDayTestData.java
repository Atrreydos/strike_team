package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.EventDay;

import java.time.LocalDate;

public class EventDayTestData {
    public static final int EVENT_DAY1_ID = 1;
    public static final int EVENT_DAY2_ID = 2;

    public static final EventDay EVENT_DAY_1 = new EventDay(EVENT_DAY1_ID, LocalDate.of(2018, 10, 30));
    public static final EventDay EVENT_DAY_2 = new EventDay(EVENT_DAY2_ID, LocalDate.of(2018, 10, 31));

}
