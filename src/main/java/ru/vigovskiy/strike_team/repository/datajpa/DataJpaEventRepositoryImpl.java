package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.repository.EventRepository;

import java.util.List;

@Repository
public class DataJpaEventRepositoryImpl implements EventRepository {

    @Autowired
    private CrudEventRepository repository;

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Event get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }
}
