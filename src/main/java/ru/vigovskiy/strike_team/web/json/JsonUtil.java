package ru.vigovskiy.strike_team.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class JsonUtil {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    private static ObjectMapper mapper;

    @Autowired(required = false)
    public JsonUtil(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    private void init() {
        List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
        messageConverters.forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                mapper = ((MappingJackson2HttpMessageConverter) httpMessageConverter).getObjectMapper();
            }
        });
    }

    private static ObjectMapper getMapper() {
        return mapper;
    }

    public static String convertToJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T convertToObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static <T> List<T> convertToObjects(String json, Class<T> clazz) throws IOException {
        ObjectReader reader = getMapper().readerFor(clazz);
        return reader.<T>readValues(json).readAll();
    }

}
