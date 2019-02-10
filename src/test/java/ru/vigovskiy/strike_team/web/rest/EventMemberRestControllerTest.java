package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.eventMember.EventMemberDto;
import ru.vigovskiy.strike_team.service.EventMemberService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vigovskiy.strike_team.EventMemberTestData.EVENT_MEMBER_1;
import static ru.vigovskiy.strike_team.EventMemberTestData.EVENT_MEMBER_3;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1_ID;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.util.EventMemberUtil.createDtosFromEventMembers;


class EventMemberRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected EventMemberService service;

    private static final String REST_URL = EventMemberRestController.REST_URL + '/';

//    @Test
//    void testGetUnAuth() throws Exception {
//        mockMvc.perform(delete(REST_URL + "1"))
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("http://localhost/login"));
//    }
//
//
//    @Test
//    void create() throws Exception {
//        VoteDay expected = new VoteDay(null, LocalDate.now(), EVENT_VOTING_1);
//        VoteDayDto expectedDto = createDtoFromVoteDay(expected);
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .with(userAuth(ADMIN_1))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        VoteDayDto returned = TestUtil.readFromJson(action, VoteDayDto.class);
//        expectedDto.setId(returned.getId());
//
//        assertThat(returned).isEqualToComparingFieldByField(expectedDto);
//    }

//    @Test
//    void testDelete() throws Exception {
//        mockMvc.perform(delete(REST_URL + EVENT_VOTING_1_ID)
//                .with(userAuth(ADMIN_1)))
//                .andExpect(status().isNoContent());
//
////        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Collections.singletonList(createDtoFromEventVoting(EVENT_VOTING_2)));
//    }

//    @Test
//    void testDeleteNotFound() throws Exception {
//        mockMvc.perform(delete(REST_URL + 0)
//                .with(userAuth(ADMIN_1)))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//    }

    @Test
    void getForEvent() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "event/" + EVENT_1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<EventMemberDto> returned = TestUtil.readListFromJson(action, EventMemberDto.class);
        assertThat(returned).isEqualTo(createDtosFromEventMembers(Arrays.asList(EVENT_MEMBER_1, EVENT_MEMBER_3)));
    }

//    @Test
//    void testCreateInvalid() throws Exception {
//        User expected = new User(null, null, "", "newPass", false, Role.ROLE_USER, Role.ROLE_ADMIN);
//        ResultActions action = mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userAuth(ADMIN_1))
//                .content(JsonUtil.convertToJson(expected)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
//
//    @Test
//    void testUpdateInvalid() throws Exception {
//        User updated = service.get(USER1_ID);
//        updated.setName("");
//        mockMvc.perform(put(REST_URL + USER1_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userAuth(ADMIN_1))
//                .content(JsonUtil.convertToJson(updated)))
//                .andExpect(status().isUnprocessableEntity())
//                .andDo(print())
//                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
//                .andDo(print());
//    }
}