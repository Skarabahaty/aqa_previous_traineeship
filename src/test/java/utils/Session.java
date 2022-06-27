package utils;

import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONException;
import models.Response;

import java.util.HashMap;
import java.util.Set;

public class Session {

    public <T> Response getAsObject(String route, Class<T> clazz) throws UnirestException {
        try {
            HttpResponse<T> httpResponse = Unirest.get(route)
                    .asObject(clazz);
            return new Response(httpResponse);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new UnirestException("Problem with response");
        }
    }

    public <T, M extends JsonObject> Response post(String route, M body, Class<T> clazz) throws UnirestException {
        try {
            HashMap<String, Object> map = new HashMap<>();
            Set<String> strings = body.keySet();
            for (String string : strings) {
                map.put(string, body.get(string));
            }

            HttpResponse<T> httpResponse = Unirest.post(route)
                    .fields(map)
                    .asObject(clazz);
            return new Response(httpResponse);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new UnirestException("Problem with response");
        }
    }
}
