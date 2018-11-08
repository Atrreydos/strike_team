package ru.vigovskiy.strike_team;


import ru.vigovskiy.strike_team.model.Event;

public class EventTestData {
    public static final int EVENT1_ID = 1;
    public static final int EVENT2_ID = 2;

    public static final Event EVENT_1 = new Event(EVENT1_ID, "событие 1", "описание события 1");
    public static final Event EVENT_2 = new Event(EVENT2_ID, "событие 2", "описание события 2");
}
