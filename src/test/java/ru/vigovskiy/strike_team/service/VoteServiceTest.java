package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.UserVoteDayPK;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.UserTestData.USER1_ID;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY2_ID;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2;
import static ru.vigovskiy.strike_team.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteService voteService;

    @Test
    void get() {
        Vote vote = voteService.get(VOTE1_ID);
        assertThat(vote).isEqualToIgnoringGivenFields(VOTE_1, "voteDay");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.get(new UserVoteDayPK(0, 0)));
    }

    @Test
    void getAll() {
        List<Vote> votes = voteService.getAll();
        assertThat(votes).usingElementComparatorIgnoringFields("voteDay").isEqualTo(Arrays.asList(VOTE_1, VOTE_2, VOTE_3));
    }

    @Test
    void create() {
        Vote newVote = new Vote(new UserVoteDayPK(USER1_ID, VOTE_DAY2_ID), DecisionType.ACCEPT, USER_1, VOTE_DAY_2);
        Vote createdVote = voteService.create(newVote);
        newVote.setId(createdVote.getId());
        assertThat(newVote).isEqualToIgnoringGivenFields(createdVote, "voteDay");
    }

    @Test
    void update() {
        Vote updatedVote = voteService.get(VOTE1_ID);
        updatedVote.setDecisionType(DecisionType.REJECT);
        voteService.update(updatedVote);
        assertThat(updatedVote).isEqualToIgnoringGivenFields(voteService.get(VOTE1_ID), "voteDay");
    }

    @Test
    void delete() {
        voteService.delete(VOTE1_ID);
        assertThat(voteService.getAll()).usingElementComparatorIgnoringFields("voteDay").isEqualTo(Arrays.asList(VOTE_2, VOTE_3));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.delete(new UserVoteDayPK(0, 0)));
    }
}