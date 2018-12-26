package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY1_ID;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_3;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtoFromVoteDay;

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
}