package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.VoteDay;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtosFromVoteDays;

public class EventVotingUtil {

    public static EventVoting createEventVotingFromDto(EventVotingDto dto, Event event) {
        return new EventVoting(dto.getId(), dto.getDescription(), event);
    }

    public static EventVotingDto createDtoFromEventVoting(EventVoting eventVoting) {
        Event event = eventVoting.getEvent();
        EventVotingDto dto = new EventVotingDto(eventVoting.getId(), eventVoting.getDescription(), event.getId());
        dto.setEventName(event.getName());
        dto.setEventDescription(event.getDescription());
        return dto;
    }

    public static List<EventVotingDto> createDtosFromEventVotings(List<EventVoting> eventVotings) {
        return Optional.ofNullable(eventVotings).orElse(Collections.emptyList()).stream()
                .map(EventVotingUtil::createDtoFromEventVoting).collect(Collectors.toList());
    }

    public static EventVotingDtoFull createDtoFullFromEventVoting(EventVoting eventVoting) {
        Event event = eventVoting.getEvent();
        List<VoteDay> voteDays = Optional.ofNullable(eventVoting.getVoteDays()).orElse(Collections.emptyList());
        List<VoteDayDto> dtosFromVoteDays = createDtosFromVoteDays(voteDays);
        EventVotingDtoFull dto = new EventVotingDtoFull(eventVoting.getId(), eventVoting.getDescription(), event.getId(), dtosFromVoteDays);
        dto.setEventName(event.getName());
        dto.setEventDescription(event.getDescription());
        return dto;
    }
}
