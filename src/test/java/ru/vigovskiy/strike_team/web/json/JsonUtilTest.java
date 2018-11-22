package ru.vigovskiy.strike_team.web.json;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import ru.vigovskiy.strike_team.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vigovskiy.strike_team.UserTestData.USERS;
import static ru.vigovskiy.strike_team.UserTestData.USER_1;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
class JsonUtilTest {


    @Test
    void testReadWriteValue() throws Exception {
        String json = JsonUtil.convertToJson(USER_1);
        User user = JsonUtil.convertToObject(json, User.class);
        assertThat(user).isEqualToComparingFieldByField(USER_1);
    }

    @Test
    void testReadWriteValues() throws Exception {
        String json = JsonUtil.convertToJson(USERS);
        List<User> meals = JsonUtil.convertToObjects(json, User.class);
        assertThat(meals).isEqualTo(USERS);
    }

}