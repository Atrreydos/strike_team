package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_votings")
public class EventVoting extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "event_voting_seq", sequenceName = "event_voting_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_voting_seq")
    private Integer id;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false, updatable = false)
    @NotNull
    private Event event;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventVoting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteDay> voteDays = new ArrayList<>();

    public EventVoting() {
    }

    public EventVoting(Integer id, String description, Event event) {
        this.id = id;
        this.description = description;
        this.event = event;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<VoteDay> getVoteDays() {
        return voteDays;
    }

    public void setVoteDays(List<VoteDay> voteDays) {
        this.voteDays = voteDays;
    }
}
