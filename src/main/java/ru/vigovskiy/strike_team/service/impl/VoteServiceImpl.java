package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.repository.VoteRepository;
import ru.vigovskiy.strike_team.service.UserService;
import ru.vigovskiy.strike_team.service.VoteDayService;
import ru.vigovskiy.strike_team.service.VoteService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.VoteUtil.createDtoFromVote;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtosFromVotes;
import static ru.vigovskiy.strike_team.util.VoteUtil.createVoteFromDto;

@Service
public class VoteServiceImpl implements VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);

    private VoteRepository repository;
    private UserService userService;
    private VoteDayService voteDayService;

    @Autowired
    public VoteServiceImpl(VoteRepository repository, UserService userService, VoteDayService voteDayService) {
        this.repository = repository;
        this.userService = userService;
        this.voteDayService = voteDayService;
    }

    @Override
    public VoteDto get(Integer id) throws NotFoundException {
        Vote vote = repository.get(id);
        if (vote == null) {
            log.error("User with id {} not found", id);
            throw new NotFoundException("Not found vote with id = " + id);
        }
        return createDtoFromVote(vote);
    }

    @Override
    public List<VoteDto> getAll() {
        return createDtosFromVotes(repository.getAll());
    }

    @Override
    public VoteDto create(VoteDto dto) {
        User user = userService.get(dto.getUserId());
        VoteDay voteDay = voteDayService.get(dto.getVoteDayId());
        Vote vote = createVoteFromDto(dto, user, voteDay);
        return createDtoFromVote(repository.save(vote));
    }

    @Override
    public void update(VoteDto dto) {
        User user = userService.get(dto.getUserId());
        VoteDay voteDay = voteDayService.get(dto.getVoteDayId());
        repository.save(createVoteFromDto(dto, user, voteDay));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("Vote with id {} not found for deleting", id);
            throw new NotFoundException("Not found vote for deleting with id = " + id);
        }
    }
}