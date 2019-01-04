package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.model.VoteDay;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.VoteUtil.createDtosFromVotes;
import static ru.vigovskiy.strike_team.web.SecurityUtil.getAuthUserIdOrNull;

public class VoteDayUtil {

    public static VoteDay createVoteDayFromDto(VoteDayDto dto, EventVoting eventVoting) {
        return new VoteDay(dto.getId(), dto.getDay(), eventVoting);
    }

    public static VoteDayDto createDtoFromVoteDay(VoteDay voteDay) {
        List<Vote> votes = Optional.ofNullable(voteDay.getVotes()).orElse(Collections.emptyList());
        List<VoteDto> voteDtos = createDtosFromVotes(votes);

        DecisionType myDecision = votes.stream()
                .filter(vote -> vote.getUser().getId() != null)
                .filter(vote -> Objects.equals(vote.getUser().getId(), getAuthUserIdOrNull()))
                .map(Vote::getDecisionType)
                .findFirst().orElse(null);

        return new VoteDayDto(voteDay.getId(), voteDay.getDay(), voteDay.getEventVoting().getId(), myDecision, voteDtos);
    }

    public static List<VoteDayDto> createDtosFromVoteDays(List<VoteDay> voteDays) {
        return Optional.ofNullable(voteDays).orElse(Collections.emptyList()).stream()
                .map(VoteDayUtil::createDtoFromVoteDay)
                .collect(Collectors.toList());
    }

    public static Long getAcceptVotesCount(VoteDay voteDay) {
        if (voteDay == null) {
            return 0l;
        }

        return Optional.ofNullable(voteDay.getVotes())
                .orElse(Collections.emptyList())
                .stream()
                .filter(vote -> vote.getDecisionType().equals(DecisionType.ACCEPT))
                .count();
    }
}
