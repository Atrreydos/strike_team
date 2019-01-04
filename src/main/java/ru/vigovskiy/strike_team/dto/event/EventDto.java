package ru.vigovskiy.strike_team.dto.event;

import org.springframework.format.annotation.DateTimeFormat;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import java.time.LocalDate;

import static ru.vigovskiy.strike_team.util.DateUtil.DATE_PATTERN;

public class EventDto implements Identifiable<Integer> {

    private Integer id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate date;

    public EventDto() {
    }

    public EventDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public EventDto(Integer id, String name, String description, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;

        EventDto eventDto = (EventDto) o;

        if (id != null ? !id.equals(eventDto.id) : eventDto.id != null) return false;
        if (name != null ? !name.equals(eventDto.name) : eventDto.name != null) return false;
        if (description != null ? !description.equals(eventDto.description) : eventDto.description != null)
            return false;
        return date != null ? date.equals(eventDto.date) : eventDto.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
