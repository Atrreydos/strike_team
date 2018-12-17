package ru.vigovskiy.strike_team.service.impl;

import org.springframework.stereotype.Service;
import ru.vigovskiy.strike_team.service.EventVotingService;

@Service
public class EventVotingServiceImpl implements EventVotingService {
//    private static final Logger log = LoggerFactory.getLogger(EventVotingServiceImpl.class);
//
//    private EventRepository repository;
//
//    @Autowired
//    public EventVotingServiceImpl(EventRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public EventDto get(int id) throws NotFoundException {
//        Event event = repository.get(id);
//        if (event == null) {
//            log.error("Event with id {} not found", id);
//            throw new NotFoundException("Not found event with id = " + id);
//        }
//        return createDtoFromEvent(event);
//    }
//
//    @Override
//    public Event getWithEventDays(int id) throws NotFoundException {
//        Event event = repository.getWithEventDays(id);
//        if (event == null) {
//            log.error("Event with id {} not found", id);
//            throw new NotFoundException("Not found event with id = " + id);
//        }
//        return event;
//    }
//
//    @Override
//    public List<EventDto> getAll() {
//        return repository.getAll().stream()
//                .map(EventUtil::createDtoFromEvent)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EventDto create(EventDto dto) {
//        if (dto.isNew()) {
//            Event event = createEventFromDto(dto);
//            event = repository.save(event);
//            return createDtoFromEvent(event);
//        }
//        else {
//            return update(dto);
//        }
//    }
//
//    @Override
//    public EventDto update(EventDto dto) {
//        if (!dto.isNew()) {
//            Event event = repository.get(dto.getId());
//            event = repository.save(EventUtil.updateEventFromDto(event, dto));
//            return createDtoFromEvent(event);
//        }
//        else {
//            return create(dto);
//        }
//    }
//
//    @Override
//    public void delete(int id) throws NotFoundException {
//        boolean deleted = repository.delete(id);
//        if (!deleted) {
//            log.info("Vote with id {} not found for deleting", id);
//            throw new NotFoundException("Not found event for deleting with id = " + id);
//        }
//    }
}