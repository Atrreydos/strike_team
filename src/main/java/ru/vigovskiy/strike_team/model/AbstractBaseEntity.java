package ru.vigovskiy.strike_team.model;

import javax.persistence.*;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {

    public static final int START_SEQ = 1;

    protected AbstractBaseEntity() {
    }
}