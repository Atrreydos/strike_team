package ru.vigovskiy.strike_team.dto.voteDay;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import java.time.LocalDate;
import java.util.List;

public class VoteDayDto implements Identifiable<Integer> {

    private Integer id;
    private LocalDate day;
    private Integer eventVotingId;
    private List<VoteDto> votes;

    public VoteDayDto(Integer id, LocalDate day, Integer eventVotingId, List<VoteDto> votes) {
        this.id = id;
        this.day = day;
        this.eventVotingId = eventVotingId;
        this.votes = votes;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Integer getEventVotingId() {
        return eventVotingId;
    }

    public void setEventVotingId(Integer eventVotingId) {
        this.eventVotingId = eventVotingId;
    }

    public List<VoteDto> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDto> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteDayDto)) return false;

        VoteDayDto that = (VoteDayDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (eventVotingId != null ? !eventVotingId.equals(that.eventVotingId) : that.eventVotingId != null)
            return false;
        return votes != null ? votes.equals(that.votes) : that.votes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (eventVotingId != null ? eventVotingId.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        return result;
    }
}
