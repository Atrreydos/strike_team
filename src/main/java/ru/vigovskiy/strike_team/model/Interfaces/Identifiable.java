package ru.vigovskiy.strike_team.model.Interfaces;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> extends Serializable {
    T getId();

    void setId(T id);

    default boolean isNew() {
        return getId() == null;
    }
}
