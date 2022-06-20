package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Post;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

import java.util.HashMap;

public class TestCase4 extends BaseTest {

    @Test
    public void testPost() throws UnirestException {

        String testCaseData = testData.getString("case_4");
        testPageURL.append(testCaseData);

        HashMap<String, Object> objectForPost = UnirestObjectsUtil.getObjectForPost(configData, testData);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.post(String.valueOf(testPageURL))
                        .fields(objectForPost)
                        .asJson();

        JsonNode responseBody = jsonNodeHttpResponse.getBody();
        JSONObject responseBodyObject = responseBody.getObject();

        Post actualPost = new Post(responseBodyObject);

        Post expectedPost = UnirestObjectsUtil.getPostFromObjectForPost(objectForPost, testData);

        Assert.assertEquals(expectedPost, actualPost);
    }
}
