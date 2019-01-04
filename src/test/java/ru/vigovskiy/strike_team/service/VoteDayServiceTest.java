package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventVotingTestData.EVENT_VOTING_1_ID;
import static ru.vigovskiy.strike_team.VoteDayTestData.*;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_3;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtoFromVoteDay;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtosFromVoteDays;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.getAcceptVotesCount;

class VoteDayServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteDayService service;

    @Test
    void get() {
        VoteDayDto voteDayDto = service.get(VOTE_DAY1_ID);
        VOTE_DAY_1.setVotes(Arrays.asList(VOTE_1, VOTE_3));
        assertThat(voteDayDto).isEqualToComparingFieldByField(createDtoFromVoteDay(VOTE_DAY_1));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getForEventVoting() {
        List<VoteDayDto> voteDayDtoList = service.getForEventVoting(EVENT_VOTING_1_ID);
        assertThat(voteDayDtoList).usingElementComparatorIgnoringFields("votes").isEqualTo(createDtosFromVoteDays(Arrays.asList(VOTE_DAY_1, VOTE_DAY_3)));
    }

    @Test
    void createOrUpdate() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAcceptVotesCountTest() {
        VoteDay voteDay1 = service.find(VOTE_DAY1_ID);
        Long day_1_acceptVotes = getAcceptVotesCount(voteDay1);
        assertThat(day_1_acceptVotes).isEqualTo(1);
        VoteDay voteDay3 = service.find(VOTE_DAY3_ID);
        Long day_3_acceptVotes = getAcceptVotesCount(voteDay3);
        assertThat(day_3_acceptVotes).isEqualTo(0);
    }
}