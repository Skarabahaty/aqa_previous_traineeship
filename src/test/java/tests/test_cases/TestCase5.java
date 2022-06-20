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

public class TestCase5 extends BaseTest {

    @Test
    public void testGetUsers() throws UnirestException {

        String mainPage = configData.getString("main_page");
        String testCase = testData.getString("case_5");
        String testPage = mainPage.concat(testCase);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(testPage)
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("correct_get_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        LinkedList<User> usersFromResponse = UnirestObjectsUtil.getUsersFromResponse(jsonNodeHttpResponse);

        int neededId = testData.getInt("case_5_id");
        User actualUser = UnirestObjectsUtil.getUserFromListById(usersFromResponse, neededId);

        JSONObject expectedUserJSON = (JSONObject) testData.getObject("case_5_user");
        User expectedUser = new User(expectedUserJSON);

        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }
}
