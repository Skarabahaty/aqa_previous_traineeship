package tests;

import models.Comment;
import models.Post;
import models.forms.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.SignInPageSteps;
import utils.Randomizer;
import utils.VkUtil;

import java.util.Objects;

public class VkApiTest extends BaseTest {

    @Test
    public void testVkApi() {
        logStep("read main page url from json");
        String mainPageURL = (String) configData.getValue("/main_page");

        logStep("go to main page");
        browser.goTo(mainPageURL);

        MainPage mainPage = new MainPage();
        mainPage.state().waitForDisplayed();

        logStep("click sign in button");
        mainPage.clickSignInButton();
        SignInPageSteps signInPageSteps = new SignInPageSteps();
        signInPageSteps.waitForDisplayed();

        logStep("read login from json");
        String login = (String) testData.getValue("/login_phone");
        logStep("read password from json");
        String password = (String) testData.getValue("/password");

        logStep("sign in");
        signInPageSteps.signIn(login, password);

        NavigationForm navigationForm = new NavigationForm();
        navigationForm.state().waitForDisplayed();

        NewsForm newsForm = new NewsForm();
        newsForm.state().waitForDisplayed();

        logStep("click my page button");
        navigationForm.clickMyPageButton();

        UserForm userForm = new UserForm();
        userForm.state().waitForDisplayed();

        logStep("get string length for randomizer from json");
        int stringLength = (int) configData.getValue("/random_string_length");

        logStep("get initial post content");
        String initialPostContent = Randomizer.getRandomString(stringLength);

        logStep("get actual user ID");
        int actualUserId = (int) testData.getValue("/owner_id");
        logStep("get expected wall post");
        Post expectedPost = new Post(actualUserId, initialPostContent);

        logStep("post new record on wall");
        int postId = VkUtil.postNewRecordOnWall(initialPostContent, actualUserId);

        logStep("check if post exist");
        Post actualPost = VkUtil.checkIfPostExistsOnPageAndReturnIt(initialPostContent, postId, userForm);

        logStep("check if actual and expected posts are equal");
        Assert.assertEquals(actualPost, expectedPost, "posts aren't equal");

        logStep("get string for editing post");
        String newPostContent = Randomizer.getRandomString(stringLength);
        logStep("get picture name from json");
        String pictureName = (String) testData.getValue("/picture_name");
        logStep("get picture storage folder from json");
        String pictureStorageFolder = (String) configData.getValue("/picture_storage_folder");

        logStep("download picture on VK server");
        String expectedLink = VkUtil.downloadPictureOnServerAndReturnLinkOnIt(pictureName, pictureStorageFolder, actualUserId);

        logStep("edit record on wall");
        VkUtil.editPostOnWallAndPinPictureToIt(postId, newPostContent, actualUserId, expectedLink);

        logStep("check if post exist after editing");
        Post actualPostAfterEdit = VkUtil.checkIfPostExistsOnPageAndReturnIt(newPostContent, postId, userForm);

        logStep("check if post after editing and original post aren't equal");
        Assert.assertNotEquals(actualPostAfterEdit, actualPost, "posts after editing are equal");

        logStep("find picture on post and return it's link");
        String actualLink = VkUtil.findPictureOnPostAndReturnPictureLink(Objects.requireNonNull(actualPostAfterEdit), userForm);

        logStep("check if picture's link are equal");
        Assert.assertEquals(actualLink, expectedLink, "images aren't equal");

        logStep("get random string for comment");
        String commentString = Randomizer.getRandomString(stringLength);

        logStep("add a comment to post");
        int commentId = VkUtil.addCommentToPostAndReturnItId(postId, commentString, actualUserId);

        logStep("click show next comment link");
        userForm.clickShowNextCommentLink(postId);

        logStep("find needed comment");
        Comment actualComment = VkUtil.findCommentByIdAndReturnIt(commentId, userForm);

        logStep("compare actual comment with expected comment");
        Comment expectedComment = new Comment(actualUserId, commentString);
        Assert.assertEquals(actualComment, expectedComment, "actual comment doesn't match expected comment");

        logStep("like post");
        userForm.likePost(postId);
        userForm.waitForLikeToAppear(postId);

        logStep("check if liked user present");
        boolean ifLikedUserPresent = VkUtil.checkIfLikeFromUser(actualUserId, postId);
        Assert.assertTrue(ifLikedUserPresent, "there is no like from needed user");

        logStep("delete post");
        boolean ifPostDeleted = VkUtil.deletePost(postId, actualUserId);
        logStep("check if post deleted based on API data");
        Assert.assertTrue(ifPostDeleted, "post still exist");

        logStep("check if post deleted based on UI data");
        boolean ifPostDeletedUI = VkUtil.checkIfPostDeleted(newPostContent, postId, userForm);
        Assert.assertTrue(ifPostDeletedUI, "post still exist");
    }
}
