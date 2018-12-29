package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.repository.VoteRepository;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.UserTestData.USER1_ID;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY1_ID;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2;
import static ru.vigovskiy.strike_team.VoteTestData.*;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtoFromVote;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtosFromVotes;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private VoteService service;
    @Autowired(required = false)
    private VoteRepository repository;

    @Test
    void get() {
        VoteDto voteDto = service.get(VOTE1_ID);
        assertThat(voteDto).isEqualToComparingFieldByField(createDtoFromVote(VOTE_1));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getAll() {
        List<VoteDto> voteDtos = service.getAll();
        assertThat(voteDtos).usingFieldByFieldElementComparator().isEqualTo(createDtosFromVotes(Arrays.asList(VOTE_1, VOTE_2, VOTE_3)));
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
    void delete() {
        service.delete(VOTE1_ID);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(createDtosFromVotes(Arrays.asList(VOTE_2, VOTE_3)));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }

    @Test
    void repositoryGetForUserByVoteDay() {
        Vote vote = repository.getForUserByVoteDay(USER1_ID, VOTE_DAY1_ID);
        assertThat(vote).isEqualToIgnoringGivenFields(VOTE_1, "user", "voteDay");
    }
}