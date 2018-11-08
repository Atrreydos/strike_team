package ru.vigovskiy.strike_team.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.repository.VoteRepository;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);

    private VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        Vote vote = repository.get(id);
        if (vote == null) {
            log.error("User with id {} not found", id);
            throw new NotFoundException("Not found vote with id = " + id);
        }
        return vote;
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public Vote create(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public void update(Vote vote) {
        repository.save(vote);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("Vote with id {} not found for deleting", id);
            throw new NotFoundException("Not found vote for deleting with id = " + id);
        }
    }
}