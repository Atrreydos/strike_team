package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.model.Enums.MemberDecision;
import ru.vigovskiy.strike_team.model.EventMember;
import ru.vigovskiy.strike_team.repository.EventMemberRepository;
import ru.vigovskiy.strike_team.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vigovskiy.strike_team.EventMemberTestData.*;
import static ru.vigovskiy.strike_team.EventTestData.*;
import static ru.vigovskiy.strike_team.UserTestData.*;
import static ru.vigovskiy.strike_team.util.EventMemberUtil.createDtoFromEventMember;
import static ru.vigovskiy.strike_team.util.EventMemberUtil.createDtosFromEventMembers;

class EventMemberServiceTest extends AbstractServiceTest {

    @Autowired(required = false)
    private EventMemberService service;
    @Autowired(required = false)
    private EventMemberRepository repository;

    @Test
    void getForEvent() {
        List<EventMemberDto> memberDtos = service.getForEvent(EVENT_1_ID);
        assertThat(memberDtos).isEqualTo(createDtosFromEventMembers(Arrays.asList(EVENT_MEMBER_1, EVENT_MEMBER_3)));
    }

    @Test
    void getForEventWithoutMembers() {
        service.delete(EVENT_MEMBER_2_ID);
        List<EventMemberDto> memberDtos = service.getForEvent(EVENT_2_ID);
        assertThat(memberDtos).isEmpty();
    }

    @Test
    void find() {
        EventMember eventMember = service.find(EVENT_MEMBER_1_ID);
        assertThat(eventMember).isEqualToComparingFieldByField(EVENT_MEMBER_1);
    }

    @Test
    void notFind() {
        assertThrows(NotFoundException.class, () -> service.find(0));
    }

    @Test
    void repositoryGetForUserByEvent() {
        EventMember eventMember = repository.getForUserByEvent(USER1_ID, EVENT_1_ID);
        assertThat(eventMember).isEqualToComparingFieldByField(EVENT_MEMBER_1);
    }

    @Test
    void create() {
        EventMember newEventMember = new EventMember(null, MemberDecision.ACCEPT, ADMIN_1, EVENT_2);
        EventMemberDto dto = createDtoFromEventMember(newEventMember);
        EventMemberDto createdDto = service.create(dto);
        dto.setId(createdDto.getId());
        assertThat(dto).isEqualToComparingFieldByField(createdDto);
    }

    @Test
    void createDuplicate() {
        EventMember eventMember = new EventMember(null, MemberDecision.ACCEPT, USER_1, EVENT_1);
        EventMemberDto newDto = createDtoFromEventMember(eventMember);
        assertThrows(DataIntegrityViolationException.class, () -> service.create(newDto));
    }

}