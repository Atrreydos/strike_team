package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.model.VoteDay;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.VoteUtil.createDtosFromVotes;

public class VoteDayUtil {

    public static VoteDay createVoteDayFromDto(VoteDayDto dto, EventVoting eventVoting) {
        return new VoteDay(dto.getId(), dto.getDay(), eventVoting);
    }

    public static VoteDayDto createDtoFromVoteDay(VoteDay voteDay) {
        List<Vote> votes = Optional.ofNullable(voteDay.getVotes()).orElse(Collections.emptyList());
        List<VoteDto> voteDtos = createDtosFromVotes(votes);
        return new VoteDayDto(voteDay.getId(), voteDay.getDay(), voteDay.getEventVoting().getId(), voteDtos);
    }

    public static List<VoteDayDto> createDtosFromVoteDays(List<VoteDay> voteDays) {
        return Optional.ofNullable(voteDays).orElse(Collections.emptyList()).stream()
                .map(VoteDayUtil::createDtoFromVoteDay)
                .collect(Collectors.toList());
    }
}
