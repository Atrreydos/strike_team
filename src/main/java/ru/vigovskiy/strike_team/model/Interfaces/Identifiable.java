package ru.vigovskiy.strike_team.model.Interfaces;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Access;
import javax.persistence.AccessType;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public interface Identifiable<T extends Serializable> extends Serializable {
    T getId();

    void setId(T id);

    default boolean isNew() {
        return getId() == null;
    }
}
