package ru.vigovskiy.strike_team.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.repository.VoteDayRepository;
import ru.vigovskiy.strike_team.service.EventVotingService;
import ru.vigovskiy.strike_team.service.VoteDayService;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtoFromVoteDay;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtosFromVoteDays;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createVoteDayFromDto;

@Service
public class VoteDayServiceImpl implements VoteDayService {
    private static final Logger log = LoggerFactory.getLogger(VoteDayServiceImpl.class);

    private VoteDayRepository repository;
    private EventVotingService eventVotingService;

    @Autowired
    public VoteDayServiceImpl(VoteDayRepository repository, EventVotingService eventVotingService) {
        this.repository = repository;
        this.eventVotingService = eventVotingService;
    }

    @Override
    public VoteDayDto get(int id) throws NotFoundException {
        VoteDay voteDay = find(id);
        return createDtoFromVoteDay(voteDay);
    }

    @Override
    public List<VoteDayDto> getForEventVoting(int eventVotingId) {
        List<VoteDay> voteDays = repository.getForEventVoting(eventVotingId);
        return createDtosFromVoteDays(voteDays);
    }

    @Override
    public VoteDayDto createOrUpdate(VoteDayDto dto) {
        EventVoting eventVoting = eventVotingService.find(dto.getEventVotingId());
        VoteDay voteDay = createVoteDayFromDto(dto, eventVoting);
        voteDay = repository.save(voteDay);
        return createDtoFromVoteDay(voteDay);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            log.info("VoteDay with id {} not found for deleting", id);
            throw new NotFoundException("Not found VoteDay for deleting with id = " + id);
        }
    }

    @Override
    public VoteDay find(Integer voteDayId) {
        VoteDay voteDay = repository.get(voteDayId);
        if (voteDay == null) {
            log.error("VoteDay with id {} not found", voteDayId);
            throw new NotFoundException("Not found voteDay with id = " + voteDayId);
        }
        return voteDay;
    }
}