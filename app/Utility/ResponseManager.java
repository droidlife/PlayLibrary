package Utility;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.Map;

public class ResponseManager extends Controller {

    private static Map<String, Object> response = new HashMap<>();
    private final ObjectMapper objMapper = new ObjectMapper();

    public static Map<String, Object> unauthorize() {
        response.put("result", false);
        response.put("message", "Provided Credentials Are Wrong");
        return response;
    }


    public static Map<String, Object> serverError() {
        response.put("result", false);
        response.put("message", "An exception occurred!");
        return response;
    }

    public final <T> String resultBuilder(boolean status, T result) throws JsonProcessingException {
        final Map<String, Object> object = new HashMap<>();
        object.put("status", status);
        object.put("result", result);
        return new ObjectMapper().writeValueAsString(object);
    }

}
