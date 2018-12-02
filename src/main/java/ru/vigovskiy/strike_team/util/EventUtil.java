package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;

public class EventUtil {

    public static Event createEventFromDto(EventDto dto) {
        return new Event(dto.getId(), dto.getName(), dto.getDescription());
    }

    public static Event updateEventFromDto(Event event, EventDto dto) {
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        return event;
    }

    public static EventDto createDtoFromEvent(Event event) {
        return new EventDto(event.getId(), event.getName(), event.getDescription());
    }
}
