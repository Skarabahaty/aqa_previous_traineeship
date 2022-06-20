package tests.test_cases;

import models.user.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase6 extends BaseTest {

    @Test
    public void testGetUser()  {
        logStep("set url");
        String url = setUrl(configData.getString("users"), testData.getString("case_6_needed_id"));

        logStep("get post");
        User actualUser = session.getAsObject(url, User.class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get user object from test data");
        Object expectedUserObject = testData.getObject("case_5_user");

        logStep("compare user from user object");
        User expectedUser = new User(expectedUserObject);

        logStep("compare actual and expected user");
        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }
}
