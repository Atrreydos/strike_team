package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vigovskiy.strike_team.model.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_2;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTE1_ID);
        assertThat(vote).isEqualToIgnoringGivenFields(VOTE_1, "user");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        voteService.get(0);
    }

    @Test
    public void getAll() {
        List<Vote> votes = voteService.getAll();
        assertThat(votes).isEqualTo(Arrays.asList(VOTE_1, VOTE_2, VOTE_3));
    }

    @Test
    public void create() {
        Vote newVote = new Vote(null, DecisionType.ACCEPT, USER_1, EVENT_DAY_2);
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
        assertThat(updatedVote).isEqualTo(voteService.get(VOTE1_ID));
    }

    @Test
    public void delete() {
        voteService.delete(VOTE1_ID);
        assertThat(voteService.getAll()).isEqualTo(Arrays.asList(VOTE_2, VOTE_3));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        voteService.delete(0);
    }
}