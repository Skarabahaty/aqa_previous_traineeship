package tests.test_cases;

import com.google.gson.JsonElement;
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

        logStep("get user from user object");
        JsonElement case5User = testData.getJsonObject("case_5_user");
        User expectedUser = gson.fromJson(case5User, User.class);

        logStep("compare actual and expected user");
        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }
}
