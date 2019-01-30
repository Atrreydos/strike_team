package ru.vigovskiy.strike_team.dto.event;

import ru.vigovskiy.strike_team.model.Enums.EventStatus;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class EventDto implements Identifiable<Integer> {

    private Integer id;
    private String name;
    private String description;
    private String date;
    private EventStatus status;

    public EventDto() {
    }

    public EventDto(Integer id, String name, String description, EventStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public EventDto(Integer id, String name, String description, String date, EventStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
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
        if (date != null ? !date.equals(eventDto.date) : eventDto.date != null) return false;
        return status == eventDto.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
    }
}
