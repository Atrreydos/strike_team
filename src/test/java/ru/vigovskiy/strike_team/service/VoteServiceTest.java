package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2;
import static ru.vigovskiy.strike_team.VoteTestData.*;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtoFromVote;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteService service;

    @Test
    void get() {
        Vote vote = service.get(VOTE1_ID);
        assertThat(vote).isEqualToIgnoringGivenFields(VOTE_1, "voteDay");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getAll() {
        List<Vote> votes = service.getAll();
        assertThat(votes).usingElementComparatorIgnoringFields("voteDay").isEqualTo(Arrays.asList(VOTE_1, VOTE_2, VOTE_3));
    }

    @Test
    void create() {
        Vote newVote = new Vote(null, DecisionType.ACCEPT, USER_1, VOTE_DAY_2);
        VoteDto dto = createDtoFromVote(newVote);
        VoteDto createdDto = service.create(dto);
        dto.setId(createdDto.getId());
        assertThat(dto).isEqualToComparingFieldByField(createdDto);
    }

    @Test
    void createDuplicate() {
        Vote newVote = new Vote(null, DecisionType.REJECT, USER_1, VOTE_DAY_1);
        VoteDto newDto = createDtoFromVote(newVote);
        assertThrows(DataIntegrityViolationException.class, () -> service.create(newDto));
    }

    @Test
    void update() {
        Vote updatedVote = service.get(VOTE1_ID);
        updatedVote.setDecisionType(DecisionType.REJECT);
        service.update(updatedVote);
        assertThat(updatedVote).isEqualToIgnoringGivenFields(service.get(VOTE1_ID), "voteDay");
    }

    @Test
    void delete() {
        service.delete(VOTE1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("voteDay").isEqualTo(Arrays.asList(VOTE_2, VOTE_3));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }
}