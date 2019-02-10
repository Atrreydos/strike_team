package ru.vigovskiy.strike_team.repository;

import ru.vigovskiy.strike_team.model.EventMember;

import java.util.List;

public interface EventMemberRepository {

    EventMember get(Integer id);

    List<EventMember> getForEvent(int eventId);

    EventMember getForUserByEvent(int userId, int eventId);

    EventMember save(EventMember eventMember);

    boolean delete(Integer id);
}
