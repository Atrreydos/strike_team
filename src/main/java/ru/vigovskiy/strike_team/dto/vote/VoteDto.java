package ru.vigovskiy.strike_team.dto.vote;

import ru.vigovskiy.strike_team.model.Enums.DecisionType;

import java.io.Serializable;

public class VoteDto implements Serializable {

    private Integer userId;

    private Integer voteDayId;

    private DecisionType decisionType;

    public VoteDto(Integer userId, Integer voteDayId, DecisionType decisionType) {
        this.userId = userId;
        this.voteDayId = voteDayId;
        this.decisionType = decisionType;
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
}
