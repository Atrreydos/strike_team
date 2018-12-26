package ru.vigovskiy.strike_team.dto.vote;

import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class VoteDto implements Identifiable<Integer> {

    private Integer id;
    private Integer userId;
    private Integer voteDayId;
    private DecisionType decisionType;

    public VoteDto() {
    }

    public VoteDto(Integer id, Integer userId, Integer voteDayId, DecisionType decisionType) {
        this.id = id;
        this.userId = userId;
        this.voteDayId = voteDayId;
        this.decisionType = decisionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVoteDayId() {
        return voteDayId;
    }

    public void setVoteDayId(Integer voteDayId) {
        this.voteDayId = voteDayId;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteDto)) return false;

        VoteDto voteDto = (VoteDto) o;

        if (id != null ? !id.equals(voteDto.id) : voteDto.id != null) return false;
        if (userId != null ? !userId.equals(voteDto.userId) : voteDto.userId != null) return false;
        if (voteDayId != null ? !voteDayId.equals(voteDto.voteDayId) : voteDto.voteDayId != null) return false;
        return decisionType == voteDto.decisionType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (voteDayId != null ? voteDayId.hashCode() : 0);
        result = 31 * result + (decisionType != null ? decisionType.hashCode() : 0);
        return result;
    }
}
