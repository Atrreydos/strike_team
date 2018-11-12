package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class EventServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private EventService eventService;

    @Test
    public void get() {
        Event event = eventService.get(EVENT1_ID);
        assertThat(event).isEqualTo(EVENT_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        eventService.get(0);
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