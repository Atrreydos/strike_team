package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.DecisionType;
import ru.vigovskiy.strike_team.model.UserEventDayPK;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY2_ID;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_2;
import static ru.vigovskiy.strike_team.UserTestData.USER1_ID;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTE1_ID);
        assertThat(vote).isEqualToComparingFieldByField(VOTE_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        voteService.get(new UserEventDayPK(0,0));
    }

    @Test
    public void getAll() {
        List<Vote> votes = voteService.getAll();
        assertThat(votes).isEqualTo(Arrays.asList(VOTE_1, VOTE_2, VOTE_3));
    }

    @Test
    public void create() {
        Vote newVote = new Vote(new UserEventDayPK(USER1_ID, EVENT_DAY2_ID), DecisionType.ACCEPT, USER_1, EVENT_DAY_2);
        Vote createdVote = voteService.create(newVote);
        newVote.setId(createdVote.getId());
        assertThat(newVote).isEqualToComparingFieldByField(createdVote);
        assertThat(newVote).isEqualToComparingFieldByField(voteService.get(createdVote.getId()));
    }

    @Test
    public void update() {
        Vote updatedVote = voteService.get(VOTE1_ID);
        updatedVote.setDecisionType(DecisionType.REJECT);
        voteService.update(updatedVote);
        assertThat(updatedVote).isEqualToComparingFieldByField(voteService.get(VOTE1_ID));
    }

    @Test
    public void delete() {
        voteService.delete(VOTE1_ID);
        assertThat(voteService.getAll()).isEqualTo(Arrays.asList(VOTE_2, VOTE_3));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        voteService.delete(new UserEventDayPK(0,0));
    }
}