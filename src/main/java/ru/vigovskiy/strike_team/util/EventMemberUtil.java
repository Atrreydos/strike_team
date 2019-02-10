package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventMemberUtil {

    public static EventMember createEventMemberFromDto(EventMemberDto dto, User user, Event event) {
        return new EventMember(dto.getId(), dto.getDecision(), user, event);
    }

    public static EventMemberDto createDtoFromEventMember(EventMember eventMember) {
        return new EventMemberDto(eventMember.getId(), eventMember.getUser().getId(), eventMember.getEvent().getId(), eventMember.getDecision());
    }

    public static List<EventMemberDto> createDtosFromEventMembers(List<EventMember> eventMembers) {
        return Optional.ofNullable(eventMembers).orElse(Collections.emptyList()).stream()
                .map(EventMemberUtil::createDtoFromEventMember)
                .collect(Collectors.toList());
    }
}
