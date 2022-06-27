package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import data.ConfigData;
import data.TestData;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;
import models.Post;
import models.Response;
import models.user.User;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CollectionsUtils;
import utils.JsonReader;
import utils.UnirestObjectsUtil;

public class UnirestTest {

    protected TestData testData;
    protected ConfigData configData;
    protected StringBuilder urlBase;
    protected Logger logger;
    protected int counter;
    protected Gson gson;

    static {
        Unirest.config().setObjectMapper(new JsonObjectMapper());
    }

    public UnirestTest() {
        testData = new TestData(JsonReader.getDataFromFile("test_data.json"));
        configData = new ConfigData(JsonReader.getDataFromFile("config_data.json"));
        urlBase = new StringBuilder(configData.getString("main_page"));
        logger = LoggerFactory.getLogger(this.getClass());
        counter = 0;
        gson = new Gson();
    }


    @Test
    public void test() {
        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
    }

    private void step1() {

        logStep("set url");
        String url = setUrl(configData.getString("posts"));

        logStep("get posts");
        Response response = UnirestObjectsUtil.getAsObject(url, Post[].class);
        Post[] posts = (Post[]) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("check if response is json");
        String actualContentType = response.getContentType();
        String neededContentType = testData.getString("needed_content_type");
        Assert.assertTrue(actualContentType.contains(neededContentType));

        logStep("check if posts sorted by id");
        boolean isListSorted = CollectionsUtils.isArraySortedAscending(posts);
        Assert.assertTrue(isListSorted, "responses not sorted by id");
    }

    private void step2() {
        String neededId = testData.getString("case_2_needed_id");

        logStep("set url");
        String url = setUrl(configData.getString("posts"), neededId);

        logStep("get post");
        Response response = UnirestObjectsUtil.getAsObject(url, Post.class);
        Post actualPost = (Post) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get post from test data");
        Post expectedPost = UnirestObjectsUtil.getObjectFromTestData(testData, "case_2_post", Post.class);

        logStep("compare actual and expected post");
        Assert.assertEquals(actualPost, expectedPost, "response objects aren't equal");
    }

    private void step3() {
        logStep("set url");
        String url = setUrl(configData.getString("posts"),
                testData.getString("case_3_needed_id"));

        logStep("get post");
        Response response = UnirestObjectsUtil.getAsObject(url, Post.class);
        Post post = (Post) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_NOT_FOUND;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("check if post empty");
        Assert.assertTrue(post.isPostEmpty(), "post is empty");
    }

    private void step4() {
        logStep("set url");
        String url = setUrl(configData.getString("posts"));

        logStep("get object for post from test data");
        JsonObject objectForPost = UnirestObjectsUtil.getObjectForPost(configData, testData);

        logStep("post acquired post");
        Response response = UnirestObjectsUtil.post(url, objectForPost, Post.class);
        Post actualPost = (Post) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_CREATED;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get post from test data");
        Post expectedPost = gson.fromJson(objectForPost, Post.class);

        logStep("compare actual and expected post");
        Assert.assertEquals(expectedPost, actualPost, "posts aren't equal");    }

    private void step5() {
        logStep("set url");
        String url = setUrl(configData.getString("users"));

        logStep("get users");
        Response response = UnirestObjectsUtil.getAsObject(url, User[].class);
        User[] users = (User[]) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("check if response is json");
        String actualContentType = response.getContentType();
        String neededContentType = testData.getString("needed_content_type");
        Assert.assertTrue(actualContentType.contains(neededContentType));

        logStep("get needed id from test data");
        int neededId = testData.getInt("case_5_id");

        logStep("get user by id");
        User actualUser = UnirestObjectsUtil.getUserById(users, neededId);

        logStep("get user from user object");
        String userString = testData.getJsonObject("case_5_user").toString();
        User expectedUser = gson.fromJson(userString, User.class);

        logStep("compare actual and expected user");
        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }

    private void step6() {
        logStep("set url");
        String url = setUrl(configData.getString("users"), testData.getString("case_6_needed_id"));

        logStep("get post");
        Response response = UnirestObjectsUtil.getAsObject(url, User.class);
        User actualUser = (User) response.getBody();

        logStep("get session status");
        int actualStatus = response.getStatus();
        int expectedStatus = HttpStatus.SC_OK;

        logStep("compare actual and expected session status");
        Assert.assertEquals(actualStatus, expectedStatus, "status isn't correct");

        logStep("get user from user object");
        JsonElement case5User = testData.getJsonObject("case_5_user");
        User expectedUser = gson.fromJson(case5User, User.class);

        logStep("compare actual and expected user");
        Assert.assertEquals(actualUser, expectedUser, "users aren't equal");
    }

    public String setUrl(String configData) {
        return setUrl(configData, "");
    }

    public String setUrl(String configData, String testData) {
        urlBase = new StringBuilder(this.configData.getString("main_page"));
        return urlBase.append(configData).append(testData).toString();
    }

    public void logStep(String description) {
        logger.info(String.format("Step %d, %s", ++counter, description));
    }
}
