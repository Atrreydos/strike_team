package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.EventMember;

public interface EventMemberRepository {

    EventMember save(EventMember eventMember);

    boolean delete(Integer id);
}
