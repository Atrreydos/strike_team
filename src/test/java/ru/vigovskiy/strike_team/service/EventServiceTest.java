package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.EventDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventDayTestData.EVENT_DAY_1;
import static ru.vigovskiy.strike_team.EventTestData.*;

class EventServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventService eventService;

    @Test
    void get() {
        EventDto eventDto = eventService.get(EVENT1_ID);
        assertThat(eventDto).isEqualToComparingFieldByField(new EventDto(EVENT_1));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> eventService.get(0));
    }

    @Test
    void getWithEventDays() {
        Event event = eventService.getWithEventDays(EVENT1_ID);
        assertThat(event).isEqualToIgnoringGivenFields(EVENT_1, "eventDays");
        assertThat(event.getEventDays()).usingElementComparatorIgnoringFields("votes").isEqualTo(Collections.singletonList(EVENT_DAY_1));
    }

    @Test
    void getWithEventDaysNotFound() {
        assertThrows(NotFoundException.class, () -> eventService.getWithEventDays(0));
    }

    @Test
    void getAll() {
        List<EventDto> events = eventService.getAll();
        assertThat(events).isEqualTo(Arrays.asList(new EventDto(EVENT_1), new EventDto(EVENT_2)));
    }

    @Test
    void create() {
        EventDto newEventDto = new EventDto(null, "new name", "new description");
        EventDto createdEventDto = eventService.create(newEventDto);
        newEventDto.setId(createdEventDto.getId());
        assertThat(newEventDto).isEqualToComparingFieldByField(createdEventDto);
        assertThat(eventService.getAll()).isEqualTo(Arrays.asList(new EventDto(EVENT_1), new EventDto(EVENT_2), newEventDto));
    }

    @Test
    void update() {
        EventDto updatedEvent = eventService.get(EVENT1_ID);
        updatedEvent.setName("updated name");
        updatedEvent.setDescription("updated description");
        eventService.update(updatedEvent);
        assertThat(updatedEvent).isEqualToIgnoringGivenFields(eventService.get(EVENT1_ID), "eventDays");
    }

    @Test
    void delete() {
        eventService.delete(EVENT1_ID);
        assertThat(eventService.getAll()).isEqualTo(Collections.singletonList(new EventDto(EVENT_2)));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> eventService.delete(0));
    }

}