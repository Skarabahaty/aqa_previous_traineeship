package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.ResponseObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.CollectionsUtils;
import utils.ResponseObjectProcessor;

import java.util.LinkedList;

public class TestCase1 extends BaseTest {

    @Test
    public void testGetAllPosts() throws UnirestException {

        String mainPage = configData.getString("main_page");
        String testCase = testData.getString("case_1");
        String testPage = mainPage.concat(testCase);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(testPage)
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        JsonNode body = jsonNodeHttpResponse.getBody();
        LinkedList<ResponseObject> responseObjects =
                ResponseObjectProcessor.getResponseObjectsFromHTTPResponse(body);

        boolean isListSorted = CollectionsUtils.isListSorted(responseObjects);
        Assert.assertTrue(isListSorted, "responses not sorted by id");
    }
}
