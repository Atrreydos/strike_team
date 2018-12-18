package ru.vigovskiy.strike_team.dto.eventVoting;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class EventVotingDto implements Identifiable<Integer> {

    private Integer id;
    private String description;
    private Integer eventId;


    public EventVotingDto() {
    }

    public EventVotingDto(Integer id, String description, Integer eventId) {
        this.id = id;
        this.description = description;
        this.eventId = eventId;
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventVotingDto)) return false;

        EventVotingDto that = (EventVotingDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return eventId != null ? eventId.equals(that.eventId) : that.eventId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
