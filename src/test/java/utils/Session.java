package utils;

import com.google.gson.JsonObject;
import exceptions.ResponseException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;

import java.util.HashMap;
import java.util.Set;

public class Session {

    private int status;

    public <T> T getAsObject(String route, Class<T> clazz) throws ResponseException {
        try {
            HttpResponse<T> httpResponse = Unirest.get(route)
                    .asObject(clazz);

            setStatus(httpResponse.getStatus());

            return httpResponse.getBody();

        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResponseException("Problem with response");
        }
    }

    public <T, M extends JsonObject> T post(String route, M body, Class<T> clazz) throws ResponseException {
        try {
            HashMap<String, Object> map = new HashMap<>();
            Set<String> strings = body.keySet();
            for (String string : strings) {
                map.put(string, body.get(string));
            }

            HttpResponse<T> httpResponse = Unirest.post(route)
                    .fields(map)
                    .asObject(clazz);

            setStatus(httpResponse.getStatus());

            return httpResponse.getBody();
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResponseException("Problem with response");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
