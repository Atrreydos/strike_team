package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.eventVoting.EventVotingDto;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.model.EventVoting;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.service.EventService;
import ru.vigovskiy.strike_team.service.EventVotingService;
import ru.vigovskiy.strike_team.service.VoteDayService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vigovskiy.strike_team.EventTestData.EVENT1_ID;
import static ru.vigovskiy.strike_team.EventTestData.EVENT_1;
import static ru.vigovskiy.strike_team.EventVotingTestData.*;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1_ID;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.createDtoFromEventVoting;
import static ru.vigovskiy.strike_team.util.EventVotingUtil.createDtosFromEventVotings;
import static ru.vigovskiy.strike_team.web.json.JsonUtil.convertToJson;


class EventVotingRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected EventVotingService service;
    @Autowired(required = false)
    protected EventService eventService;
    @Autowired(required = false)
    protected VoteDayService voteDayService;

    private static final String REST_URL = EventVotingRestController.REST_URL + '/';

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + EVENT_VOTING_1_ID)
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtoFromEventVoting(EVENT_VOTING_1))));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL )
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(createDtosFromEventVotings(Arrays.asList(EVENT_VOTING_1, EVENT_VOTING_2)))));
    }

    @Test
    void create() throws Exception {
        EventVoting expected = new EventVoting(null, "new description", EVENT_1);
        EventVotingDto expectedDto = createDtoFromEventVoting(expected);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andDo(print())
                .andExpect(status().isOk());

        EventVotingDto returned = TestUtil.readFromJson(action, EventVotingDto.class);
        expected.setId(returned.getId());
        expectedDto.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expectedDto);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(createDtosFromEventVotings(Arrays.asList(EVENT_VOTING_1, EVENT_VOTING_2, expected)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + EVENT_VOTING_1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());

        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Collections.singletonList(createDtoFromEventVoting(EVENT_VOTING_2)));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testSetupVoteDay() throws Exception {
        Event event = eventService.find(EVENT1_ID);
        assertThat(event.getDate()).isNull();

        mockMvc.perform(put(REST_URL + EVENT_VOTING_1_ID + "/vote-day/" + VOTE_DAY_1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());

        VoteDay voteDay = voteDayService.find(VOTE_DAY_1_ID);
        LocalDate day = voteDay.getDay();
        event = eventService.find(EVENT1_ID);
        assertThat(event.getDate()).isEqualTo(day);
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