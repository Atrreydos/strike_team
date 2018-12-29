package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.repository.VoteRepository;

import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    private final CrudVoteRepository repository;

    @Autowired
    public VoteRepositoryImpl(CrudVoteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Vote get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Vote getForUserByVoteDay(int userId, int voteDayId) {
        return repository.findByUserIdAndVoteDayId(userId, voteDayId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }
}
