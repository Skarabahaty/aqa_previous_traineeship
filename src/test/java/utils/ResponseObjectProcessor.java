package utils;

import com.mashape.unirest.http.JsonNode;
import data.TestData;
import models.ResponseObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class ResponseObjectProcessor {

    public static LinkedList<ResponseObject> getResponseObjectsFromHTTPResponse(JsonNode responseBody) {
        JSONArray array = responseBody.getArray();
        LinkedList<ResponseObject> responseObjects = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            ResponseObject responseObject = new ResponseObject(jsonObject);
            responseObjects.add(responseObject);
        }
        return responseObjects;
    }

    public static ResponseObject getResponseObjectFromTestData(TestData testData, String key) {

        JSONObject testObject = (JSONObject) testData.getTestObject(key);
        return new ResponseObject(testObject);
    }

}
