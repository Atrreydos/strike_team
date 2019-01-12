package ru.vigovskiy.strike_team.util;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDtoFull;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.model.VoteDay;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vigovskiy.strike_team.util.DateUtil.DATE_FORMATTER;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtosFromVotes;
import static ru.vigovskiy.strike_team.web.SecurityUtil.getAuthUserIdOrNull;

public class VoteDayUtil {

    public static VoteDay createVoteDayFromDto(VoteDayDto dto, EventVoting eventVoting) {
        return new VoteDay(dto.getId(), LocalDate.parse(dto.getDay(), DATE_FORMATTER), eventVoting);
    }

    public static VoteDayDto createDtoFromVoteDay(VoteDay voteDay) {
        List<Vote> votes = Optional.ofNullable(voteDay.getVotes()).orElse(Collections.emptyList());
        List<VoteDto> voteDtos = createDtosFromVotes(votes);

        DecisionType myDecision = votes.stream()
                .filter(vote -> vote.getUser().getId() != null)
                .filter(vote -> Objects.equals(vote.getUser().getId(), getAuthUserIdOrNull()))
                .map(Vote::getDecisionType)
                .findFirst().orElse(null);

        return new VoteDayDto(voteDay.getId(), voteDay.getDay().format(DATE_FORMATTER), voteDay.getEventVoting().getId(), myDecision, voteDtos);
    }

    public static List<VoteDayDto> createDtosFromVoteDays(List<VoteDay> voteDays) {
        return Optional.ofNullable(voteDays).orElse(Collections.emptyList()).stream()
                .map(VoteDayUtil::createDtoFromVoteDay)
                .collect(Collectors.toList());
    }

    public static VoteDayDtoFull createDtoFullFromVoteDay(VoteDay voteDay) {
        VoteDayDto dto = createDtoFromVoteDay(voteDay);
        int acceptCount = getAcceptVotesCount(voteDay);
        int rejectCount = getRejectVotesCount(voteDay);

        return new VoteDayDtoFull(dto, acceptCount, rejectCount);
    }

    public static List<VoteDayDtoFull> createDtosFullFromVoteDays(List<VoteDay> voteDays) {
        return Optional.ofNullable(voteDays).orElse(Collections.emptyList()).stream()
                .map(VoteDayUtil::createDtoFullFromVoteDay)
                .collect(Collectors.toList());
    }

    public static int getAcceptVotesCount(VoteDay voteDay) {
        return getVotesCountByType(voteDay, DecisionType.ACCEPT);
    }

    public static int getRejectVotesCount(VoteDay voteDay) {
        return getVotesCountByType(voteDay, DecisionType.REJECT);
    }

    private static int getVotesCountByType(VoteDay voteDay, DecisionType type) {
        if (voteDay == null) {
            return 0;
        }

        return (int) Optional.ofNullable(voteDay.getVotes())
                .orElse(Collections.emptyList())
                .stream()
                .filter(vote -> vote.getDecisionType().equals(type))
                .count();
    }
}
