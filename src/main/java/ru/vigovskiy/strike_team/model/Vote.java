package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "vote_seq", sequenceName = "vote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    private Integer id;

    @Column(name = "decision")
    @NotNull
    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "vote_day_id", updatable = false)
    @NotNull
    private VoteDay voteDay;

    public Vote() {
    }

    public Vote(Integer id, DecisionType decisionType, User user, VoteDay voteDay) {
        this.id = id;
        this.decisionType = decisionType;
        this.user = user;
        this.voteDay = voteDay;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoteDay getVoteDay() {
        return voteDay;
    }

    public void setVoteDay(VoteDay voteDay) {
        this.voteDay = voteDay;
    }
}
