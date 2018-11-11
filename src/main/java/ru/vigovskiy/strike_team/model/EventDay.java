package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public EventDay() {
    }

    public EventDay(Integer id, LocalDate day) {
        this.id = id;
        this.day = day;
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

    @Override
    public String toString() {
        return "EventDay{" +
                "id=" + id +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDay)) return false;

        EventDay eventDay = (EventDay) o;

        if (!id.equals(eventDay.id)) return false;
        return day.equals(eventDay.day);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + day.hashCode();
        return result;
    }
}
