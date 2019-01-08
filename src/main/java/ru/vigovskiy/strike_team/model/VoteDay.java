package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vote_days")
public class VoteDay extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "vote_day_seq", sequenceName = "vote_day_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_day_seq")
    private Integer id;

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "event_voting_id", nullable = false)
    @NotNull
    private EventVoting eventVoting;

    @OneToMany(mappedBy = "voteDay", fetch = FetchType.EAGER, cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    private List<Vote> votes;

    public VoteDay() {
    }

    public VoteDay(Integer id, LocalDate day, EventVoting eventVoting) {
        this.id = id;
        this.day = day;
        this.eventVoting = eventVoting;
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

    public EventVoting getEventVoting() {
        return eventVoting;
    }

    public void setEventVoting(EventVoting eventVoting) {
        this.eventVoting = eventVoting;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
