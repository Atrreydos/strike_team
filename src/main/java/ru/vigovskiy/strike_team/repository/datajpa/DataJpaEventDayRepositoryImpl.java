package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.EventDay;
import ru.vigovskiy.strike_team.repository.EventDayRepository;

import java.util.List;

@Repository
public class DataJpaEventDayRepositoryImpl implements EventDayRepository {

    @Autowired
    private CrudEventDayRepository repository;

    @Override
    public EventDay save(EventDay event) {
        return repository.save(event);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public EventDay get(int id) {
        return repository.findByIdWithVotes(id);
    }

    @Override
    public List<EventDay> getAll() {
        return repository.findAll();
    }
}
