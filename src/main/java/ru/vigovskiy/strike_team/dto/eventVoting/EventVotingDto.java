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
}
