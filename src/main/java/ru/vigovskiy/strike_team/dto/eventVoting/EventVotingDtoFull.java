package ru.vigovskiy.strike_team.dto.eventVoting;

import ru.vigovskiy.strike_team.dto.event.EventDto;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.Enums.EventVotingStatus;

import java.util.ArrayList;
import java.util.List;

public class EventVotingDtoFull extends EventVotingDto {

    private List<VoteDayDto> voteDays = new ArrayList<>();

    public EventVotingDtoFull() {
    }

    public EventVotingDtoFull(Integer id, String description, EventVotingStatus status, EventDto event, List<VoteDayDto> voteDays) {
        super(id, description, status, event);
        this.voteDays = voteDays;
    }

    public List<VoteDayDto> getVoteDays() {
        return voteDays;
    }

    public void setVoteDays(List<VoteDayDto> voteDays) {
        this.voteDays = voteDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventVotingDtoFull)) return false;
        if (!super.equals(o)) return false;

        EventVotingDtoFull that = (EventVotingDtoFull) o;

        return voteDays != null ? voteDays.equals(that.voteDays) : that.voteDays == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (voteDays != null ? voteDays.hashCode() : 0);
        return result;
    }
}
