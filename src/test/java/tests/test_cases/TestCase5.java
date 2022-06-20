package tests.test_cases;

import models.user.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

public class TestCase5 extends BaseTest {

    @Test
    public void testGetUsers() {
        logStep("set url");
        String url = setUrl(configData.getString("users"));

        logStep("get users");
        User[] users = session.getAsObject(url, User[].class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get needed id from test data");
        int neededId = testData.getInt("case_5_id");

        logStep("get user by id");
        User actualUser = UnirestObjectsUtil.getUserById(users, neededId);

        logStep("get user object from test data");
        Object userObject = testData.getObject("case_5_user");

        logStep("get user from user object");
        User expectedUser = new User(userObject);

        logStep("compare actual and expected user");
        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }
}
