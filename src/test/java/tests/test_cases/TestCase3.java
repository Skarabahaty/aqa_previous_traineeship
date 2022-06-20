package tests.test_cases;

import models.Post;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

public class TestCase3 extends BaseTest {

    @Test
    public void testGetWrongPost()  {
        logStep("set url");
        String url = setUrl(configData.getString("posts"),
                testData.getString("case_3_needed_id"));

        logStep("get post");
        Post post = session.getAsObject(url, Post.class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_NOT_FOUND;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("check if post empty");
        Assert.assertTrue(UnirestObjectsUtil.isPostEmpty(post), "post is empty");
    }
}
