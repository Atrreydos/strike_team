package ru.vigovskiy.strike_team.dto.eventVoting;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class EventVotingDto implements Identifiable<Integer> {

    private Integer id;
    private Integer eventId;
    private String name;
    private String description;

    public EventVotingDto() {
    }

    public EventVotingDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventVotingDto)) return false;

        EventVotingDto eventDto = (EventVotingDto) o;

        if (id != null ? !id.equals(eventDto.id) : eventDto.id != null) return false;
        if (name != null ? !name.equals(eventDto.name) : eventDto.name != null) return false;
        return description != null ? description.equals(eventDto.description) : eventDto.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
