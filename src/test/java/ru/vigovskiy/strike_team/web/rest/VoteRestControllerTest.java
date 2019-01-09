package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.vote.VoteDto;
import ru.vigovskiy.strike_team.model.Enums.DecisionType;
import ru.vigovskiy.strike_team.model.Vote;
import ru.vigovskiy.strike_team.service.VoteService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_2_ID;
import static ru.vigovskiy.strike_team.VoteTestData.*;
import static ru.vigovskiy.strike_team.util.VoteUtil.createDtoFromVote;


class VoteRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected VoteService service;

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(delete(REST_URL + "1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    void create() throws Exception {
        Vote expected = new Vote(null, DecisionType.REJECT, ADMIN_1, VOTE_DAY_2);
        VoteDto expectedDto = createDtoFromVote(expected);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andDo(print())
                .andExpect(status().isOk());

        VoteDto returned = TestUtil.readFromJson(action, VoteDto.class);
        expectedDto.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void update() throws Exception {
        Vote expected = VOTE_1;
        expected.setDecisionType(DecisionType.REJECT);
        VoteDto expectedDto = createDtoFromVote(expected);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(USER_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andDo(print())
                .andExpect(status().isOk());

        VoteDto returned = TestUtil.readFromJson(action, VoteDto.class);
        expectedDto.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expectedDto);
        assertThat(service.find(VOTE_1_ID)).isEqualToIgnoringGivenFields(VOTE_1, "voteDay");
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE_DAY_2_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteDayWithoutVotes() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE_DAY_2_ID)
                .with(userAuth(USER_1)))
                .andExpect(status().isNoContent())
                .andDo(print());
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