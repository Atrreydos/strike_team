package ru.vigovskiy.strike_team.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.TestUtil;
import ru.vigovskiy.strike_team.dto.voteDay.VoteDayDto;
import ru.vigovskiy.strike_team.model.VoteDay;
import ru.vigovskiy.strike_team.service.VoteDayService;
import ru.vigovskiy.strike_team.web.AbstractControllerTest;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vigovskiy.strike_team.EventVotingTestData.EVENT_VOTING_1;
import static ru.vigovskiy.strike_team.EventVotingTestData.EVENT_VOTING_1_ID;
import static ru.vigovskiy.strike_team.TestUtil.userAuth;
import static ru.vigovskiy.strike_team.UserTestData.ADMIN_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_1;
import static ru.vigovskiy.strike_team.VoteDayTestData.VOTE_DAY_3;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtoFromVoteDay;
import static ru.vigovskiy.strike_team.util.VoteDayUtil.createDtosFromVoteDays;


class VoteDayRestControllerTest extends AbstractControllerTest {

    @Autowired(required = false)
    protected VoteDayService service;

    private static final String REST_URL = VoteDayRestController.REST_URL + '/';

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(delete(REST_URL + "1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    void create() throws Exception {
        VoteDay expected = new VoteDay(null, LocalDate.now(), EVENT_VOTING_1);
        VoteDayDto expectedDto = createDtoFromVoteDay(expected);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtil.convertToJson(expectedDto))))
                .andDo(print())
                .andExpect(status().isOk());

        VoteDayDto returned = TestUtil.readFromJson(action, VoteDayDto.class);
        expectedDto.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + EVENT_VOTING_1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNoContent());

//        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Collections.singletonList(createDtoFromEventVoting(EVENT_VOTING_2)));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 0)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getForEventVoting() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + EVENT_VOTING_1_ID)
                .with(userAuth(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<VoteDayDto> returned = TestUtil.readListFromJson(action, VoteDayDto.class);
        assertThat(returned).usingElementComparatorIgnoringFields("votes", "myVote").isEqualTo(createDtosFromVoteDays(Arrays.asList(VOTE_DAY_1, VOTE_DAY_3)));
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