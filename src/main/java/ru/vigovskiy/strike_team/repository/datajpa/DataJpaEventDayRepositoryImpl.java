package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.repository.EventDayRepository;

import java.util.List;

@Repository
public class DataJpaEventDayRepositoryImpl implements EventDayRepository {

    private final CrudEventDayRepository repository;

    @Autowired
    public DataJpaEventDayRepositoryImpl(CrudEventDayRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public EventDay save(EventDay event) {
        return repository.save(event);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public EventDay get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public EventDay getWithVotes(int id) {
        return repository.findByIdWithVotes(id);
    }

    @Override
    public List<EventDay> getAll() {
        return repository.findAll();
    }
}
