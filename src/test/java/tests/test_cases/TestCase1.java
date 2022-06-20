package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.CollectionsUtils;
import utils.UnirestObjectsUtil;

import java.util.LinkedList;

public class TestCase1 extends BaseTest {

    @Test
    public void testGetAllPosts() throws UnirestException {

        String testCaseData = testData.getString("case_1");
        testPageURL.append(testCaseData);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(String.valueOf(testPageURL))
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        LinkedList<Post> posts = UnirestObjectsUtil.getPostsFromHTTPResponse(jsonNodeHttpResponse);

        boolean isListSorted = CollectionsUtils.isListSortedAscending(posts);
        Assert.assertTrue(isListSorted, "responses not sorted by id");
    }
}
