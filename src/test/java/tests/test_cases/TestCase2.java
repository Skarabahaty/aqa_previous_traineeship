package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.ResponseObject;
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
        LinkedList<ResponseObject> responseObjects =
                UnirestObjectsUtil.getResponseObjectsFromHTTPResponse(body);

        ResponseObject actualResponseObject = responseObjects.get(0);
        ResponseObject expectedResponseObject =
                UnirestObjectsUtil.getResponseObjectFromTestData(testData, "case_2_post");
        Assert.assertEquals(actualResponseObject, expectedResponseObject, "response objects aren't equal");
    }
}
