package ru.vigovskiy.strike_team;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.vigovskiy.strike_team.model.User;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestUtil {

    private static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.convertToObject(getContent(action), clazz);
    }

    public static <T> List<T> readListFromJson(ResultActions action, Class<T> clazz) throws IOException {
        return JsonUtil.convertToObjects(getContent(action), clazz);
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getLogin(), user.getPassword());
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    }
}
