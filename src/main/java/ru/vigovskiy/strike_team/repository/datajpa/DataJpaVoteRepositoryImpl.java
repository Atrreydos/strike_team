package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.UserVoteDayPK;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    private final CrudVoteRepository repository;

    @Autowired
    public DataJpaVoteRepositoryImpl(CrudVoteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    @Transactional
    public boolean delete(UserVoteDayPK id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Vote get(UserVoteDayPK id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }
}
