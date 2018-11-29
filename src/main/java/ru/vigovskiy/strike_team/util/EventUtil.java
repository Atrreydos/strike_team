package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.EventDto;
import ru.vigovskiy.strike_team.model.Event;

public class EventUtil {

    public static Event createEventFromDto(EventDto dto) {
        return new Event(dto.getId(), dto.getName(), dto.getDescription());
    }
}
