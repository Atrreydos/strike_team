package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.DecisionType;
import ru.vigovskiy.strike_team.model.UserEventDayPK;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY2_ID;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_2;
import static ru.vigovskiy.strike_team.UserTestData.USER1_ID;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteService voteService;

    @Test
    void get() {
        Vote vote = voteService.get(VOTE1_ID);
        assertThat(vote).isEqualToComparingFieldByField(VOTE_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.get(new UserEventDayPK(0, 0)));
    }

    @Test
    void getAll() {
        List<Vote> votes = voteService.getAll();
        assertThat(votes).isEqualTo(Arrays.asList(VOTE_1, VOTE_2, VOTE_3));
    }

    @Test
    void create() {
        Vote newVote = new Vote(new UserEventDayPK(USER1_ID, EVENT_DAY2_ID), DecisionType.ACCEPT, USER_1, EVENT_DAY_2);
        Vote createdVote = voteService.create(newVote);
        newVote.setId(createdVote.getId());
        assertThat(newVote).isEqualToComparingFieldByField(createdVote);
        assertThat(newVote).isEqualToComparingFieldByField(voteService.get(createdVote.getId()));
    }

    @Test
    void update() {
        Vote updatedVote = voteService.get(VOTE1_ID);
        updatedVote.setDecisionType(DecisionType.REJECT);
        voteService.update(updatedVote);
        assertThat(updatedVote).isEqualToComparingFieldByField(voteService.get(VOTE1_ID));
    }

    @Test
    void delete() {
        voteService.delete(VOTE1_ID);
        assertThat(voteService.getAll()).isEqualTo(Arrays.asList(VOTE_2, VOTE_3));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.delete(new UserEventDayPK(0, 0)));
    }
}