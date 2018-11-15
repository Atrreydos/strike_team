package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_1;
import static ru.vigovskiy.strike_team.EventTestData.*;

public class EventServiceTest extends AbstractServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void get() {
        Event event = eventService.get(EVENT1_ID);
        assertThat(event).isEqualToIgnoringGivenFields(EVENT_1, "eventDays");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        eventService.get(0);
    }

    @Test
    public void getWithEventDays() {
        Event event = eventService.getWithEventDays(EVENT1_ID);
        assertThat(event).isEqualToIgnoringGivenFields(EVENT_1, "eventDays");
        assertThat(event.getEventDays()).usingElementComparatorIgnoringFields("votes").isEqualTo(Collections.singletonList(EVENT_DAY_1));
    }

    @Test(expected = NotFoundException.class)
    public void getWithEventDaysNotFound() {
        eventService.getWithEventDays(0);
    }

    @Test
    public void getAll() {
        List<Event> events = eventService.getAll();
        assertThat(events).isEqualTo(Arrays.asList(EVENT_1, EVENT_2));
    }

    @Test
    public void create() {
        Event newEvent = new Event(null, "new name", "new description");
        Event createdEvent = eventService.create(newEvent);
        newEvent.setId(createdEvent.getId());
        assertThat(newEvent).isEqualToComparingFieldByField(createdEvent);
        assertThat(eventService.getAll()).isEqualTo(Arrays.asList(EVENT_1, EVENT_2, newEvent));
    }

    @Test
    public void update() {
        Event updatedEvent = eventService.get(EVENT1_ID);
        updatedEvent.setName("updated name");
        updatedEvent.setDescription("updated description");
        eventService.update(updatedEvent);
        assertThat(updatedEvent).isEqualToIgnoringGivenFields(eventService.get(EVENT1_ID), "eventDays");
    }

    @Test
    public void delete() {
        eventService.delete(EVENT1_ID);
        assertThat(eventService.getAll()).isEqualTo(Collections.singletonList(EVENT_2));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        eventService.delete(0);
    }

}