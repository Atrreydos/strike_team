package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.EventDay;

import java.time.LocalDate;

import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_2;

public class EventDayTestData {
    public static final int EVENT_DAY1_ID = 1;
    public static final int EVENT_DAY2_ID = 2;

    public static final EventDay EVENT_DAY_1 = new EventDay(EVENT_DAY1_ID, LocalDate.of(2018, 10, 30), EVENT_1);
    public static final EventDay EVENT_DAY_2 = new EventDay(EVENT_DAY2_ID, LocalDate.of(2018, 10, 31), EVENT_2);

}
