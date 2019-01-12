package ru.vigovskiy.strike_team.dto.voteDay;

public class VoteDayDtoFull extends VoteDayDto {

    private int acceptCount;
    private int rejectCount;

    public VoteDayDtoFull() {
    }

    public VoteDayDtoFull(VoteDayDto dto, Integer acceptCount, Integer rejectCount) {
        super(dto.getId(), dto.getDay(), dto.getEventVotingId(), dto.getMyVote(), dto.getVotes());
        this.acceptCount = acceptCount;
        this.rejectCount = rejectCount;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public int getRejectCount() {
        return rejectCount;
    }

    public void setRejectCount(int rejectCount) {
        this.rejectCount = rejectCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteDayDtoFull)) return false;
        if (!super.equals(o)) return false;

        VoteDayDtoFull dtoFull = (VoteDayDtoFull) o;

        if (acceptCount != dtoFull.acceptCount) return false;
        return rejectCount == dtoFull.rejectCount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + acceptCount;
        result = 31 * result + rejectCount;
        return result;
    }

    @Override
    public String toString() {
        return "VoteDayDtoFull{" +
                "acceptCount=" + acceptCount +
                ", rejectCount='" + rejectCount + '\'' +
                '}';
    }
}
