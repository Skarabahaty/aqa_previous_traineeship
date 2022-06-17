package tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.ResponseObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CollectionsUtils;

import java.util.LinkedList;

public class GetAllPostsTest extends BaseTest {

    @Test
    public void testGetAllPosts() throws UnirestException {

        String mainPage = configData.getString("main_page");
        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(mainPage)
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");

        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        JsonNode body = jsonNodeHttpResponse.getBody();
        JSONArray array = body.getArray();

        LinkedList<ResponseObject> responseObjects = new LinkedList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            ResponseObject responseObject = new ResponseObject(jsonObject);
            responseObjects.add(responseObject);
        }

        boolean isListSorted = CollectionsUtils.isListSorted(responseObjects);
        Assert.assertTrue(isListSorted, "responses not sorted by id");
    }
}
