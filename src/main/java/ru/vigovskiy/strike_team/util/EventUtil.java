package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventUtil {

    public static Event createEventFromDto(EventDto dto) {
        return new Event(dto.getId(), dto.getName(), dto.getDescription(), dto.getDate());
    }

    public static Event updateEventFromDto(Event event, EventDto dto) {
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        return event;
    }

    public static EventDto createDtoFromEvent(Event event) {
        return new EventDto(event.getId(), event.getName(), event.getDescription(), event.getDate());
    }

    public static List<EventDto> createDtosFromEvents(List<Event> events) {
        return Optional.ofNullable(events).orElse(Collections.emptyList()).stream()
                .map(EventUtil::createDtoFromEvent).collect(Collectors.toList());
    }
}
