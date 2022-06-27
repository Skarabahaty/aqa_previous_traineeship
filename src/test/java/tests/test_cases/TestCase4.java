package tests.test_cases;

import com.google.gson.JsonObject;
import models.Post;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

public class TestCase4 extends BaseTest {

    @Test
    public void testPostPost()  {

        logStep("set url");
        String url = setUrl(configData.getString("posts"));

        logStep("get object for post from test data");
        JsonObject objectForPost = UnirestObjectsUtil.getObjectForPost(configData, testData);

        logStep("post acquired post");
        Post actualPost = session.post(url, objectForPost, Post.class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_CREATED;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get post from test data");
        Post expectedPost = gson.fromJson(objectForPost, Post.class);

        logStep("compare actual and expected post");
        Assert.assertEquals(expectedPost, actualPost, "posts aren't equal");
    }
}
