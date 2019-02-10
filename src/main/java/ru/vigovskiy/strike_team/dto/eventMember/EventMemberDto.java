package ru.vigovskiy.strike_team.dto.eventMember;

import ru.vigovskiy.strike_team.model.Enums.MemberDecision;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

public class EventMemberDto implements Identifiable<Integer> {

    private Integer id;
    private Integer userId;
    private Integer eventId;
    private MemberDecision decision;

    public EventMemberDto() {
    }

    public EventMemberDto(Integer id, Integer userId, Integer eventId, MemberDecision decision) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.decision = decision;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public MemberDecision getDecision() {
        return decision;
    }

    public void setDecision(MemberDecision decision) {
        this.decision = decision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventMemberDto)) return false;

        EventMemberDto that = (EventMemberDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        return decision == that.decision;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventMemberDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", decision=" + decision +
                '}';
    }

}
