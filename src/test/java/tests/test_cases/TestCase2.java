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

        String testCaseData = testData.getString("case_2");
        testPageURL.append(testCaseData);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(String.valueOf(testPageURL))
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        LinkedList<Post> posts = UnirestObjectsUtil.getPostsFromHTTPResponse(jsonNodeHttpResponse);
        int neededId = testData.getInt("case_2_id");

        Post actualPost = UnirestObjectsUtil.getPostById(posts, neededId);
        Post expectedPost =
                UnirestObjectsUtil.getPostFromTestData(testData, "case_2_post");
        Assert.assertEquals(actualPost, expectedPost, "response objects aren't equal");
    }
}
