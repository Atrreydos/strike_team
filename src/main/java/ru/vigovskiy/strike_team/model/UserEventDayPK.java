package ru.vigovskiy.strike_team.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserEventDayPK implements Serializable {

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "event_day_id", insertable = false, updatable = false)
    private int eventDayId;

    public UserEventDayPK() {
    }

    public UserEventDayPK(Integer userId, int eventDayId) {
        this.userId = userId;
        this.eventDayId = eventDayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getEventDayId() {
        return eventDayId;
    }

    public void setEventDayId(int eventDayId) {
        this.eventDayId = eventDayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEventDayPK)) return false;

        UserEventDayPK that = (UserEventDayPK) o;

        if (eventDayId != that.eventDayId) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + eventDayId;
        return result;
    }
}
