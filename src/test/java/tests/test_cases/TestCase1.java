package tests.test_cases;

import models.Post;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.CollectionsUtils;

public class TestCase1 extends BaseTest {

    @Test
    public void testGetAllPosts()  {

        logStep("set url");
        String url = setUrl(configData.getString("posts"));

        logStep("get posts");
        Post[] posts = session.getAsObject(url, Post[].class);

        logStep("get session status");
        int actualStatus = session.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("check if posts sorted by id");
        boolean isListSorted = CollectionsUtils.isArraySortedAscending(posts);
        Assert.assertTrue(isListSorted, "responses not sorted by id");
    }
}
