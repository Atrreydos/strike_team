package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDtoFull;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.util.VoteDayUtil;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventTestData.EVENT1_ID;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.EventVotingTestData.*;
import static ru.vigovskiy.strike_team.VoteDayTestData.*;
import static ru.vigovskiy.strike_team.util.EventUtil.createDtoFromEvent;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.*;

class EventVotingServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventVotingService service;
    @Autowired(required = false)
    private EventService eventService;
    @Autowired(required = false)
    private VoteDayService voteDayService;

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
    void createWithExistingEvent() {
        assertThat(eventService.getAll().size()).isEqualTo(2);
        EventVotingDto newDto = new EventVotingDto(null, "description", createDtoFromEvent(EVENT_1));
        EventVotingDto createdDto = service.createOrUpdate(newDto);
        newDto.setId(createdDto.getId());
        assertThat(newDto).isEqualToComparingFieldByField(createdDto);
        assertThat(service.getAll()).usingFieldByFieldElementComparator()
                .isEqualTo(Arrays.asList(createDtoFromEventVoting(EVENT_VOTING_1), createDtoFromEventVoting(EVENT_VOTING_2), newDto));
        assertThat(eventService.getAll().size()).isEqualTo(2);
    }

    @Test
    void createWithNewEvent() {
        assertThat(eventService.getAll().size()).isEqualTo(2);
        EventVotingDto newDto = new EventVotingDto(null, "description", new EventDto(null, "new event name", "new event description"));
        EventVotingDto createdDto = service.createOrUpdate(newDto);
        newDto.setId(createdDto.getId());
        newDto.getEvent().setId(createdDto.getEvent().getId());
        assertThat(newDto).isEqualToComparingFieldByField(createdDto);
        assertThat(service.getAll()).usingFieldByFieldElementComparator()
                .isEqualTo(Arrays.asList(createDtoFromEventVoting(EVENT_VOTING_1), createDtoFromEventVoting(EVENT_VOTING_2), newDto));
        EventDto eventDto = eventService.get(createdDto.getEvent().getId());
        assertThat(createdDto.getEvent()).isEqualToComparingFieldByField(eventDto);
        assertThat(eventService.getAll().size()).isEqualTo(3);
    }

    @Test
    void update() {
        assertThat(eventService.getAll().size()).isEqualTo(2);
        EventVotingDto updatedDto = service.get(EVENT_VOTING_1_ID);
        updatedDto.setDescription("updated description");

        EventDto event = updatedDto.getEvent();
        event.setName("new event name");
        event.setDescription("new event description");

        EventVotingDto updated = service.createOrUpdate(updatedDto);
        assertThat(updated).isEqualToComparingFieldByField(service.get(EVENT_VOTING_1_ID));
        assertThat(eventService.getAll().size()).isEqualTo(2);
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

    @Test
    void getAcceptedDaysTest() {
        List<VoteDay> acceptedDays1 = service.getMaxAcceptedDays(EVENT_VOTING_1_ID);
        assertThat(acceptedDays1).usingElementComparatorIgnoringFields("votes", "eventVoting").isEqualTo(Collections.singletonList(VOTE_DAY_1));

        EventVotingDto newDto = new EventVotingDto(null, "description", createDtoFromEvent(EVENT_1));
        newDto = service.createOrUpdate(newDto);
        List<VoteDay> acceptedDays2 = service.getMaxAcceptedDays(newDto.getId());
        assertThat(acceptedDays2).isEqualTo(Collections.emptyList());
    }

    @Test
    void setupDayForEventTest() {
        Event event = eventService.find(EVENT1_ID);
        assertThat(event.getDate()).isNull();

        service.setupDayForEvent(EVENT_VOTING_1_ID, VOTE_DAY_1_ID);
        VoteDay voteDay = voteDayService.find(VOTE_DAY_1_ID);
        LocalDate day = voteDay.getDay();
        event = eventService.find(EVENT1_ID);
        assertThat(event.getDate()).isEqualTo(day);
    }
}