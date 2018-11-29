package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventDayTestData.*;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_3;

class EventDayServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventDayService service;

    @Test
    void get() {
        EventDay eventDay = service.get(EVENT_DAY1_ID);
        assertThat(eventDay).isEqualToIgnoringGivenFields(EVENT_DAY_1, "votes");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getWithVotes() {
        EventDay eventDay = service.getWithVotes(EVENT_DAY1_ID);
        assertThat(eventDay).isEqualToIgnoringGivenFields(EVENT_DAY_1, "votes");
        List<Vote> votes = eventDay.getVotes();
        assertThat(votes).usingElementComparatorOnFields("id").isEqualTo(Arrays.asList(VOTE_1, VOTE_3));
    }

    @Test
    void getWithVotesNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithVotes(0));
    }

    @Test
    void getAll() {
        List<EventDay> eventDays = service.getAll();
        assertThat(eventDays).usingElementComparatorIgnoringFields("votes").isEqualTo(Arrays.asList(EVENT_DAY_1, EVENT_DAY_2));
    }

    @Test
    void create() {
        EventDay newEventDay = new EventDay(null, LocalDate.of(2018, 10, 28), EVENT_1);
        EventDay createdEventDate = service.create(newEventDay);
        newEventDay.setId(createdEventDate.getId());
        assertThat(newEventDay).isEqualToComparingFieldByField(createdEventDate);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Arrays.asList(EVENT_DAY_1, EVENT_DAY_2, newEventDay));
    }

    @Test
    void update() {
        EventDay updatedEventDate = service.get(EVENT_DAY1_ID);
        updatedEventDate.setDay(LocalDate.of(2018, 9, 27));
        service.update(updatedEventDate);
        assertThat(updatedEventDate).isEqualToIgnoringGivenFields(service.get(EVENT_DAY1_ID), "votes");
    }

    @Test
    void delete() {
        service.delete(EVENT_DAY1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("votes").isEqualTo(Collections.singletonList(EVENT_DAY_2));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }

}