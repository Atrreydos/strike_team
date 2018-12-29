package ru.vigovskiy.strike_team.service;

import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    VoteDto get(Integer id) throws NotFoundException;

    List<VoteDto> getAll();

    VoteDto create(VoteDto dto);

    void delete(Integer id) throws NotFoundException;

    VoteDto createOrUpdate(VoteDto dto);
}