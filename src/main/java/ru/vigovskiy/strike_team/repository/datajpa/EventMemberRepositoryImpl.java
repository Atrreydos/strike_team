package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.repository.EventMemberRepository;

import java.util.List;

@Repository
public class EventMemberRepositoryImpl implements EventMemberRepository {

    private final CrudEventMemberRepository repository;

    @Autowired
    public EventMemberRepositoryImpl(CrudEventMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventMember get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EventMember> getForEvent(int eventId) {
        return repository.findAllByEventId(eventId);
    }

    @Override
    public EventMember getForUserByEvent(int userId, int eventId) {
        return repository.findByUserIdAndEventId(userId, eventId);
    }

    @Override
    public EventMember save(EventMember eventMember) {
        return repository.save(eventMember);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id) != 0;
    }
}
