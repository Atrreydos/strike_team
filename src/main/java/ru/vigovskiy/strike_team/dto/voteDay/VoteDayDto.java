package ru.vigovskiy.strike_team.dto.voteDay;

import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class VoteDayDto implements Identifiable<Integer> {

    private Integer id;
    private Integer userId;
    private Integer voteDayId;
    private DecisionType decisionType;

    public VoteDayDto(Integer id, Integer userId, Integer voteDayId, DecisionType decisionType) {
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
}
