package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.model.VoteDay;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VoteUtil {

    public static Vote createVoteFromDto(VoteDto dto, User user, VoteDay voteDay) {
        return new Vote(dto.getId(), dto.getDecisionType(), user, voteDay);
    }

    public static VoteDto createDtoFromVote(Vote vote) {
        return new VoteDto(vote.getId(), vote.getUser().getId(), vote.getVoteDay().getId(), vote.getDecisionType());
    }

    public static List<VoteDto> createDtosFromVotes(List<Vote> votes) {
        return Optional.ofNullable(votes).orElse(Collections.emptyList()).stream()
                .map(VoteUtil::createDtoFromVote)
                .collect(Collectors.toList());
    }
}
