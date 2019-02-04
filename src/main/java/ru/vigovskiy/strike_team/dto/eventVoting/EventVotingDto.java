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
    private boolean votedByAuthUser;

    public EventVotingDto() {
    }

    public EventVotingDto(Integer id, String description, EventVotingStatus status, EventDto event) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.event = event;
    }

    public EventVotingDto(Integer id, String description, EventVotingStatus status, EventDto event, boolean votedByAuthUser) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.event = event;
        this.votedByAuthUser = votedByAuthUser;
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

    public EventVotingStatus getStatus() {
        return status;
    }

    public void setStatus(EventVotingStatus status) {
        this.status = status;
    }

    public boolean isVotedByAuthUser() {
        return votedByAuthUser;
    }

    public void setVotedByAuthUser(boolean votedByAuthUser) {
        this.votedByAuthUser = votedByAuthUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventVotingDto)) return false;

        EventVotingDto that = (EventVotingDto) o;

        if (votedByAuthUser != that.votedByAuthUser) return false;
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
        result = 31 * result + (votedByAuthUser ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventVotingDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", event=" + event +
                ", status=" + status +
                ", votedByAuthUser=" + votedByAuthUser +
                '}';
    }
}
