package ru.vigovskiy.strike_team.model;

import ru.vigovskiy.strike_team.model.Interfaces.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event extends AbstractNamedEntity implements Identifiable<Integer> {

    @Id
    @SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Integer id;

    @Column(name = "description")
    private String description;

    public Event() {
    }

    public Event(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (!super.equals(o)) return false;

        Event event = (Event) o;

        if (!id.equals(event.id)) return false;
        return description != null ? description.equals(event.description) : event.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
