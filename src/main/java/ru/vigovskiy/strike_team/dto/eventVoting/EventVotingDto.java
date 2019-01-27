package ru.vigovskiy.strike_team.dto.eventVoting;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.model.Enums.EventVotingStatus;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class EventVotingDto implements Identifiable<Integer> {

    private Integer id;
    private String description;
    /*TODO @NotNull*/
    private EventDto event;
    private EventVotingStatus status;

    public EventVotingDto() {
    }

    public EventVotingDto(Integer id, String description, EventVotingStatus status, EventDto event) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.event = event;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventVotingDto)) return false;

        EventVotingDto that = (EventVotingDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public EventVotingStatus getStatus() {
        return status;
    }

    public void setStatus(EventVotingStatus status) {
        this.status = status;
    }

}
