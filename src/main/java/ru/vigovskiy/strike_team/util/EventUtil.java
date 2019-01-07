package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Event;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.DateUtil.DATE_FORMATTER;

public class EventUtil {

    public static Event createEventFromDto(EventDto dto) {
        return new Event(dto.getId(), dto.getName(), dto.getDescription(), LocalDate.parse(dto.getDate()));
    }

    public static Event updateEventFromDto(Event event, EventDto dto) {
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setDate(LocalDate.parse(dto.getDate()));
        return event;
    }

    public static EventDto createDtoFromEvent(Event event) {
        LocalDate date = event.getDate();
        String dateString = date == null ? null : date.format(DATE_FORMATTER);
        return new EventDto(event.getId(), event.getName(), event.getDescription(), dateString);
    }

    public static List<EventDto> createDtosFromEvents(List<Event> events) {
        return Optional.ofNullable(events).orElse(Collections.emptyList()).stream()
                .map(EventUtil::createDtoFromEvent).collect(Collectors.toList());
    }
}
