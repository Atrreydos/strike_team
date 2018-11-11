package ru.vigovskiy.strike_team.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.EventDayTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class EventDayServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private EventDayService service;

    @Test
    public void get() {
        EventDay eventDay = service.get(EVENT_DAY1_ID);
        assertThat(eventDay).isEqualToComparingFieldByField(EVENT_DAY_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(0);
    }

    @Test
    public void getAll() {
        List<EventDay> eventDays = service.getAll();
        assertThat(eventDays).isEqualTo(Arrays.asList(EVENT_DAY_1, EVENT_DAY_2));
    }

    @Test
    public void create() {
        EventDay newEventDay = new EventDay(null, LocalDate.of(2018, 10, 28));
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
        assertThat(updatedEventDate).isEqualToComparingFieldByField(service.get(EVENT_DAY1_ID));
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