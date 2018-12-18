package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.util.VoteDayUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventVotingTestData.*;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_3;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.*;

class EventVotingServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventVotingService service;

    @Test
    void get() {
        EventVotingDto dto = service.get(EVENT_VOTING_1_ID);
        assertThat(dto).isEqualToComparingFieldByField(createDtoFromEventVoting(EVENT_VOTING_1));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getWithVoteDays() {
        EventVotingDtoFull dto = service.getWithVoteDays(EVENT_VOTING_1_ID);
        assertThat(dto).isEqualToIgnoringGivenFields(createDtoFullFromEventVoting(EVENT_VOTING_1), "voteDays");
        assertThat(dto.getVoteDays()).usingElementComparatorIgnoringFields("votes").isEqualTo(VoteDayUtil.createDtosFromVoteDays(Arrays.asList(VOTE_DAY_1, VOTE_DAY_3)));
    }

    @Test
    void getWithVoteDaysNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithVoteDays(0));
    }

    @Test
    void getAll() {
        List<EventVotingDto> events = service.getAll();
        assertThat(events).isEqualTo(createDtosFromEventVotings(Arrays.asList(EVENT_VOTING_1, EVENT_VOTING_2)));
    }

    @Test
    void create() {
        EventVotingDto newDto = new EventVotingDto(null, "description", EVENT_VOTING_1_ID);
        EventVotingDto createdDto = service.createOrUpdate(newDto);
        newDto.setId(createdDto.getId());
        assertThat(newDto).isEqualToComparingFieldByField(createdDto);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(createDtoFromEventVoting(EVENT_VOTING_1), createDtoFromEventVoting(EVENT_VOTING_2), newDto));
    }

    @Test
    void update() {
        EventVotingDto updatedDto = service.get(EVENT_VOTING_1_ID);
        updatedDto.setDescription("updated description");
        service.createOrUpdate(updatedDto);
        assertThat(updatedDto).isEqualToComparingFieldByField(service.get(EVENT_VOTING_1_ID));
    }

    @Test
    void deleteTest() {
        service.delete(EVENT_VOTING_1_ID);
        assertThat(service.getAll()).isEqualTo(Collections.singletonList(createDtoFromEventVoting(EVENT_VOTING_2)));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }
}