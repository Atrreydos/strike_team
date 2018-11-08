package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }
}
