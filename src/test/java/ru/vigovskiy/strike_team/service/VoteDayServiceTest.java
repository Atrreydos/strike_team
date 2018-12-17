package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY1_ID;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;

class VoteDayServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteDayService service;

    @Test
    void get() {
        VoteDay voteDay = service.get(VOTE_DAY1_ID);
        assertThat(voteDay).isEqualToIgnoringGivenFields(VOTE_DAY_1, "votes");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }
}