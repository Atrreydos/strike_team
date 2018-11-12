package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "event_days")
public class EventDay extends AbstractBaseEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "event_day_seq", sequenceName = "event_day_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_day_seq")
    private Integer id;

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull
    private Event event;

    @OneToMany(mappedBy = "eventDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;

    public EventDay() {
    }

    public EventDay(Integer id, LocalDate day, Event event) {
        this.id = id;
        this.day = day;
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

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "EventDay{" +
                "id=" + id +
                ", day=" + day +
                ", event=" + event +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDay)) return false;

        EventDay eventDay = (EventDay) o;

        if (!id.equals(eventDay.id)) return false;
        if (!day.equals(eventDay.day)) return false;
        return event.equals(eventDay.event);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + day.hashCode();
        result = 31 * result + event.hashCode();
        return result;
    }
}
