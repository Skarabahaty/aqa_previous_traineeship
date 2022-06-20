package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.user.User;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

import java.util.LinkedList;

public class TestCase6 extends BaseTest {

    @Test
    public void testGetUsers() throws UnirestException {

        String testCaseData = testData.getString("case_6");
        testPageURL.append(testCaseData);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(String.valueOf(testPageURL))
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        LinkedList<User> usersFromResponse = UnirestObjectsUtil.getUsersFromResponse(jsonNodeHttpResponse);

        int neededId = testData.getInt("case_6_id");
        User actualUser = UnirestObjectsUtil.getUserById(usersFromResponse, neededId);

        JSONObject expectedUserJSON = (JSONObject) testData.getObject("case_5_user");
        User expectedUser = new User(expectedUserJSON);

        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }
}
