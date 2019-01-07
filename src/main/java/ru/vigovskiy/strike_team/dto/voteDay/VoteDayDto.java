package ru.vigovskiy.strike_team.dto.voteDay;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import java.util.List;

public class VoteDayDto implements Identifiable<Integer> {

    private Integer id;
    private String day;
    private Integer eventVotingId;
    private DecisionType myVote;
    private List<VoteDto> votes;

    public VoteDayDto() {
    }

    public VoteDayDto(Integer id, String day, Integer eventVotingId, DecisionType myVote, List<VoteDto> votes) {
        this.id = id;
        this.day = day;
        this.eventVotingId = eventVotingId;
        this.myVote = myVote;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getEventVotingId() {
        return eventVotingId;
    }

    public void setEventVotingId(Integer eventVotingId) {
        this.eventVotingId = eventVotingId;
    }

    public DecisionType getMyVote() {
        return myVote;
    }

    public void setMyVote(DecisionType myVote) {
        this.myVote = myVote;
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
        if (myVote != that.myVote) return false;
        return votes != null ? votes.equals(that.votes) : that.votes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (eventVotingId != null ? eventVotingId.hashCode() : 0);
        result = 31 * result + (myVote != null ? myVote.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        return result;
    }
}
