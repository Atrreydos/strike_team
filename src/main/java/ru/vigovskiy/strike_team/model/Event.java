package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Enums.EventStatus;
import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event extends AbstractNamedEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventStatus status;

    public Event() {
    }

    public Event(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Event(Integer id, String name, String description, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
