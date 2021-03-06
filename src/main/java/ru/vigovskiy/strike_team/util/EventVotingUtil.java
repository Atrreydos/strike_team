package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.event.EventDto;
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

import static ru.vigovskiy.strike_team.util.EventUtil.createDtoFromEvent;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtosFromVoteDays;
import static ru.vigovskiy.strike_team.web.SecurityUtil.getAuthUserIdOrNull;

public class EventVotingUtil {

    public static EventVoting createEventVotingFromDto(EventVotingDto dto, Event event) {
        return new EventVoting(dto.getId(), dto.getDescription(), dto.getStatus(), event);
    }

    public static EventVotingDto createDtoFromEventVoting(EventVoting eventVoting) {
        boolean votedByAuthUser = false;
        Integer authUserId = getAuthUserIdOrNull();
        if (authUserId != null) {
            votedByAuthUser = eventVoting.getVoteDays().stream().anyMatch(voteDay -> voteDay.getVotes().stream().anyMatch(vote -> vote.getUser().getId().equals(authUserId)));
        }

        Event event = eventVoting.getEvent();
        EventDto eventDto = createDtoFromEvent(event);

        return new EventVotingDto(eventVoting.getId(), eventVoting.getDescription(), eventVoting.getStatus(), eventDto, votedByAuthUser);
    }

    public static List<EventVotingDto> createDtosFromEventVotings(List<EventVoting> eventVotings) {
        return Optional.ofNullable(eventVotings).orElse(Collections.emptyList()).stream()
                .map(EventVotingUtil::createDtoFromEventVoting).collect(Collectors.toList());
    }

    public static EventVotingDtoFull createDtoFullFromEventVoting(EventVoting eventVoting) {
        Event event = eventVoting.getEvent();
        EventDto eventDto = createDtoFromEvent(event);
        List<VoteDay> voteDays = Optional.ofNullable(eventVoting.getVoteDays()).orElse(Collections.emptyList());
        List<VoteDayDto> dtosFromVoteDays = createDtosFromVoteDays(voteDays);
        return new EventVotingDtoFull(eventVoting.getId(), eventVoting.getDescription(), eventVoting.getStatus(), eventDto, dtosFromVoteDays);
    }
}
