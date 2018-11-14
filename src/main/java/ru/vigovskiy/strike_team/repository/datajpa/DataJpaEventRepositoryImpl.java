package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.repository.EventRepository;

import java.util.List;

@Repository
public class DataJpaEventRepositoryImpl implements EventRepository {

    private final CrudEventRepository repository;

    @Autowired
    public DataJpaEventRepositoryImpl(CrudEventRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Event get(int id) {
        return repository.findByIdWithEventDays(id);
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }
}
