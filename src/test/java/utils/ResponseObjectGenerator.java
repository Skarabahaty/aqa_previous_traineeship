package utils;

import com.mashape.unirest.http.JsonNode;
import models.ResponseObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseObjectGenerator {

    public static boolean isResponseObjectAbleToGenerate(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        JSONObject jsonObject = (JSONObject) array.get(0);
        try {
            new ResponseObject(jsonObject);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}

