package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.repository.EventVotingRepository;

import java.util.List;

@Repository
public class EventVotingRepositoryImpl implements EventVotingRepository {

    private final CrudEventVotingRepository repository;

    @Autowired
    public EventVotingRepositoryImpl(CrudEventVotingRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public EventVoting save(EventVoting eventVoting) {
        return repository.save(eventVoting);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public EventVoting get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public EventVoting getWithVoteDays(int id) {
        return repository.findByIdWithEventDays(id);
    }

    @Override
    public List<EventVoting> getAll() {
        return repository.findAll();
    }
}
