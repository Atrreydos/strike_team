package ru.vigovskiy.strike_team.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.repository.VoteDayRepository;

import java.util.List;

@Repository
public class VoteDayRepositoryImpl implements VoteDayRepository {

    private final CrudVoteDayRepository repository;

    @Autowired
    public VoteDayRepositoryImpl(CrudVoteDayRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public VoteDay save(VoteDay voteDay) {
        return repository.save(voteDay);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return repository.delete(id) != 0;
    }

    @Override
    public VoteDay get(Integer id) {
        return repository.getWithVotes(id);
    }

    @Override
    public List<VoteDay> getAll() {
        return repository.findAll();
    }
}
