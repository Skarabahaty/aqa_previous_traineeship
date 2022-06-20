package tests.test_cases;

import models.Post;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

import java.util.HashMap;

public class TestCase4 extends BaseTest {

    @Test
    public void testPostPost()  {

        logStep("set url");
        String url = setUrl(configData.getString("posts"));

        logStep("get object for post from test data");
        HashMap<String, Object> objectForPost = UnirestObjectsUtil.getObjectForPost(configData, testData);

        logStep("post acquired post");
        Post actualPost = session.post(url, objectForPost, Post.class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_CREATED;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get post from test data");
        Post expectedPost = new Post(objectForPost);

        logStep("compare actual and expected post");
        Assert.assertEquals(expectedPost, actualPost, "posts aren't equal");
    }
}
