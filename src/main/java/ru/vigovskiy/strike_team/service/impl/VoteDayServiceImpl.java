package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.repository.VoteDayRepository;
import ru.vigovskiy.strike_team.service.VoteDayService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

@Service
public class VoteDayServiceImpl implements VoteDayService {
    private static final Logger log = LoggerFactory.getLogger(VoteDayServiceImpl.class);

    private VoteDayRepository repository;

    @Autowired
    public VoteDayServiceImpl(VoteDayRepository repository) {
        this.repository = repository;
    }

    @Override
    public VoteDay get(int id) throws NotFoundException {
        VoteDay voteDay = repository.get(id);
        if (voteDay == null) {
            log.error("VoteDay with id {} not found", id);
            throw new NotFoundException("Not found voteDay with id = " + id);
        }
        return voteDay;
    }
}