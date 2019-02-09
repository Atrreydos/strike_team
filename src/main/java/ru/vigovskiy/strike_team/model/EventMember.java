package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Enums.MemberDecision;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event_members")
public class EventMember extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "event_member_seq", sequenceName = "event_member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_member_seq")
    private Integer id;

    @Column(name = "decision")
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberDecision decision;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", updatable = false)
    @NotNull
    private Event event;

    public EventMember() {
    }

    public EventMember(Integer id, MemberDecision decision, User user, Event event) {
        this.id = id;
        this.decision = decision;
        this.user = user;
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

    public MemberDecision getDecision() {
        return decision;
    }

    public void setDecision(MemberDecision decision) {
        this.decision = decision;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
