package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventTestData.*;
import static ru.vigovskiy.strike_team.util.EventUtil.createDtoFromEvent;

class EventServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventService service;

    @Test
    void get() {
        EventDto eventDto = service.get(EVENT1_ID);
        assertThat(eventDto).isEqualToComparingFieldByField(createDtoFromEvent(EVENT_1));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0));
    }

    @Test
    void getAll() {
        List<EventDto> events = service.getAll();
        assertThat(events).isEqualTo(Arrays.asList(createDtoFromEvent(EVENT_1), createDtoFromEvent(EVENT_2)));
    }

    @Test
    void create() {
        EventDto newEventDto = new EventDto(null, "new name", "new description");
        EventDto createdEventDto = service.create(newEventDto);
        newEventDto.setId(createdEventDto.getId());
        assertThat(newEventDto).isEqualToComparingFieldByField(createdEventDto);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(createDtoFromEvent(EVENT_1), createDtoFromEvent(EVENT_2), newEventDto));
    }

    @Test
    void update() {
        EventDto updatedEvent = service.get(EVENT1_ID);
        updatedEvent.setName("updated name");
        updatedEvent.setDescription("updated description");
        service.update(updatedEvent);
        assertThat(updatedEvent).isEqualToComparingFieldByField(service.get(EVENT1_ID));
    }

    @Test
    void delete() {
        service.delete(EVENT1_ID);
        assertThat(service.getAll()).isEqualTo(Collections.singletonList(createDtoFromEvent(EVENT_2)));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0));
    }

}