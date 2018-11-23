package ru.vigovskiy.strike_team;

import org.springframework.test.web.servlet.ResultActions;
import ru.vigovskiy.strike_team.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;

public class TestUtil {

    private static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.convertToObject(getContent(action), clazz);
    }
}
