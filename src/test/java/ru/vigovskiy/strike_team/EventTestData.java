package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Enums.EventStatus;
import ru.vigovskiy.strike_team.model.Event;

import java.util.Arrays;
import java.util.List;

public class EventTestData {
    public static final int EVENT1_ID = 1;
    public static final int EVENT2_ID = 2;

    public static final Event EVENT_1 = new Event(EVENT1_ID, "событие 1", "описание события 1", EventStatus.IN_VOTING);
    public static final Event EVENT_2 = new Event(EVENT2_ID, "событие 2", "описание события 2", EventStatus.IN_VOTING);

    public static final List<Event> EVENTS = Arrays.asList(EVENT_1, EVENT_2);
}
