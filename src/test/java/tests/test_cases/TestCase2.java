package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

import java.util.LinkedList;

public class TestCase2 extends BaseTest {

    @Test
    public void testGetOnePost() throws UnirestException {

        String mainPage = configData.getString("main_page");
        String testCase = testData.getString("case_2");
        String testPage = mainPage.concat(testCase);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(testPage)
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        JsonNode body = jsonNodeHttpResponse.getBody();
        LinkedList<Post> posts =
                UnirestObjectsUtil.getResponseObjectsFromHTTPResponse(body);

        Post actualPost = posts.get(0);
        Post expectedPost =
                UnirestObjectsUtil.getResponseObjectFromTestData(testData, "case_2_post");
        Assert.assertEquals(actualPost, expectedPost, "response objects aren't equal");
    }
}
