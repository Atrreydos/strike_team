package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.repository.EventMemberRepository;

@Repository
public class EventMemberRepositoryImpl implements EventMemberRepository {

    private final CrudEventMemberRepository repository;

    @Autowired
    public EventMemberRepositoryImpl(CrudEventMemberRepository repository) {
        this.repository = repository;
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
