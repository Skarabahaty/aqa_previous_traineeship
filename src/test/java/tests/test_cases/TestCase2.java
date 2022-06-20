package tests.test_cases;

import models.Post;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

public class TestCase2 extends BaseTest {

    @Test
    public void testGetOnePost()  {
        String neededId = testData.getString("case_2_needed_id");

        logStep("set url");
        String url = setUrl(configData.getString("posts"), neededId);

        logStep("get post");
        Post actualPost = session.getAsObject(url, Post.class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get post from test data");
        Post expectedPost = UnirestObjectsUtil.getPostFromTestData(testData, "case_2_post");

        logStep("compare actual and expected post");
        Assert.assertEquals(actualPost, expectedPost, "response objects aren't equal");
    }
}
