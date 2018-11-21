package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventDayTestData.*;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_1;
import static ru.vigovskiy.strike_team.VoteTestData.VOTE_3;

public class EventDayServiceTest extends AbstractServiceTest{

    @Autowired(required = false)
    private EventDayService service;

    @Test
    public void get() {
        EventDay eventDay = service.get(EVENT_DAY1_ID);
        assertThat(eventDay).isEqualToIgnoringGivenFields(EVENT_DAY_1, "votes");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(0);
    }

    @Test
    public void getWithVotes() {
        EventDay eventDay = service.getWithVotes(EVENT_DAY1_ID);
        assertThat(eventDay).isEqualToIgnoringGivenFields(EVENT_DAY_1, "votes");
        assertThat(eventDay.getVotes()).usingElementComparatorIgnoringFields().isEqualTo(Arrays.asList(VOTE_1, VOTE_3));
    }

    @Test(expected = NotFoundException.class)
    public void getWithVotesNotFound() {
        service.getWithVotes(0);
    }

    @Test
    public void getAll() {
        List<EventDay> eventDays = service.getAll();
        assertThat(eventDays).isEqualTo(Arrays.asList(EVENT_DAY_1, EVENT_DAY_2));
    }

    @Test
    public void create() {
        EventDay newEventDay = new EventDay(null, LocalDate.of(2018, 10, 28), EVENT_1);
        EventDay createdEventDate = service.create(newEventDay);
        newEventDay.setId(createdEventDate.getId());
        assertThat(newEventDay).isEqualToComparingFieldByField(createdEventDate);
        assertThat(service.getAll()).isEqualTo(Arrays.asList(EVENT_DAY_1, EVENT_DAY_2, newEventDay));
    }

    @Test
    public void update() {
        EventDay updatedEventDate = service.get(EVENT_DAY1_ID);
        updatedEventDate.setDay(LocalDate.of(2018, 9, 27));
        service.update(updatedEventDate);
        assertThat(updatedEventDate).isEqualTo(service.get(EVENT_DAY1_ID));
    }

    @Test
    public void delete() {
        service.delete(EVENT_DAY1_ID);
        assertThat(service.getAll()).isEqualTo(Collections.singletonList(EVENT_DAY_2));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(0);
    }

}