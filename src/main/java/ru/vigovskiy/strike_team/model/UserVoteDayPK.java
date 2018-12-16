package ru.vigovskiy.strike_team.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserVoteDayPK implements Serializable {

    private Integer userId;

    private int voteDayId;

    public UserVoteDayPK() {
    }

    public UserVoteDayPK(Integer userId, int voteDayId) {
        this.userId = userId;
        this.voteDayId = voteDayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getVoteDayId() {
        return voteDayId;
    }

    public void setVoteDayId(int voteDayId) {
        this.voteDayId = voteDayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVoteDayPK)) return false;

        UserVoteDayPK that = (UserVoteDayPK) o;

        if (voteDayId != that.voteDayId) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + voteDayId;
        return result;
    }
}
