package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;
import models.Comment;
import models.Post;
import models.comments_response.CommentsInitialResponse;
import models.delete_post.DeletePostInitialResponse;
import models.forms.UserForm;
import models.likes.LikedUser;
import models.likes.LikesInitialResponse;
import models.photo_upload.PhotoUploadServerResponseAfterUpload;
import models.photo_upload.save_photo.SavedPhoto;
import models.photo_upload.save_photo.SavedPhotoInitialResponse;
import models.photo_upload.upload_url.PhotoUploadLinkInitialResponse;
import models.wall_post.WallPostInitialResponse;
import org.openqa.selenium.By;

import java.nio.file.Path;
import java.util.Arrays;

public class VkUtil {

    static {
        Unirest.config().setObjectMapper(new JsonObjectMapper());
    }

    private static final JsonSettingsFile API_SETTINGS = new JsonSettingsFile("api_settings.json");
    private static final String API_QUERY_DRAFT = "https://api.vk.com/method/%s";
    private static final String ACCESS_TOKEN = (String) API_SETTINGS.getValue("/access_token");
    private static final String API_VERSION = (String) API_SETTINGS.getValue("/api_version");

    public static int postNewRecordOnWall(String recordContent, int actualUserId) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.WALL_POST.getMethod());
        WallPostInitialResponse wallPostInitialResponse = Unirest.post(urlBase)
                .field("owner_id", actualUserId)
                .field("message", recordContent)
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .asObject(WallPostInitialResponse.class)
                .getBody();
        return wallPostInitialResponse.getWallPostInfo().getPostId();
    }

    public static Post checkIfPostExistsOnPageAndReturnIt(String postText, int postId, UserForm userForm) {
        ILabel wallPostWithText = userForm.getWallPostByTextAndPostId(postText, postId);
        String rawId = wallPostWithText.getAttribute("id");
        int posterId = extractPosterIdFromRawId(postId, rawId);
        return new Post(posterId, postText);
    }


    private static int extractPosterIdFromRawId(int postId, String rawId) {
        String toReplace = String.format("_%d", postId);
        String post = rawId
                .replace(toReplace, "")
                .replace("post", "");
        return Integer.parseInt(post);
    }

    public static String downloadPictureOnServerAndReturnLinkOnIt(String pictureName, String pictureStorageFolder, int actualUserId) {
        String uploadLink = getUploadLink();
        PhotoUploadServerResponseAfterUpload photoUploadServerResponseAfterUpload =
                uploadPictureAndReturnServerResponse(pictureName, uploadLink, pictureStorageFolder);
        SavedPhoto savedPhoto =
                savePictureOnServerAndReturnOriginalOne(photoUploadServerResponseAfterUpload, actualUserId);
        return String.format("photo%d_%d", savedPhoto.getOwner_id(), savedPhoto.getId());
    }

    public static void editPostOnWallAndPinPictureToIt(int recordId, String newContent, int actualUserId, String pictureLink) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.WALL_EDIT.getMethod());
        Unirest.post(urlBase)
                .field("post_id", recordId)
                .field("owner_id", String.valueOf(actualUserId))
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .field("message", newContent)
                .field("attachments", pictureLink)
                .asEmpty();
    }

    private static SavedPhoto savePictureOnServerAndReturnOriginalOne(PhotoUploadServerResponseAfterUpload photoUploadServerResponseAfterUpload, int actualUserId) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.PHOTOS_SAVE_WALL_PHOTO.getMethod());
        SavedPhotoInitialResponse response = Unirest.post(urlBase)
                .field("owner_id", actualUserId)
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .field("hash", photoUploadServerResponseAfterUpload.getHash())
                .field("server", String.valueOf(photoUploadServerResponseAfterUpload.getServer()))
                .field("photo", photoUploadServerResponseAfterUpload.getPhoto())
                .asObject(SavedPhotoInitialResponse.class)
                .getBody();
        int firstIndex = 0;
        return response.getUploadedAndSavedPhotos()[firstIndex];
    }

    private static PhotoUploadServerResponseAfterUpload uploadPictureAndReturnServerResponse(String pictureName, String uploadLink, String pictureStorageFolder) {
        Path picturePath = PathProvider.getPicturePath(pictureName, pictureStorageFolder);
        return Unirest.post(uploadLink)
                .multiPartContent()
                .field("photo", picturePath.toFile())
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .asObject(PhotoUploadServerResponseAfterUpload.class)
                .getBody();
    }

    private static String getUploadLink() {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.GET_WALL_UPLOAD_SERVER.getMethod());
        PhotoUploadLinkInitialResponse response = Unirest.post(urlBase)
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .asObject(PhotoUploadLinkInitialResponse.class)
                .getBody();
        return response.getUploadLinkServerInfo().getUploadUrl();
    }

    public static String findPictureOnPostAndReturnPictureLink(Post post, UserForm userForm) {
        ILabel wallPostLabel = userForm.getWallPostByTextAndPostId(post.getPostText(), post.getAuthorId());
        ILink childElement = wallPostLabel.findChildElement(By.xpath("//div[@class=\"post_content\"]//a"), ILink.class);
        String href = childElement.getAttribute("href");
        return href.replace("https://vk.com/", "");
    }

    public static int addCommentToPostAndReturnItId(int recordId, String comment, int actualUserId) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.WALL_CREATE_COMMENT.getMethod());

        CommentsInitialResponse response = Unirest.post(urlBase)
                .field("post_id", recordId)
                .field("owner_id", String.valueOf(actualUserId))
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .field("message", comment)
                .asObject(CommentsInitialResponse.class)
                .getBody();
        return response.getCommentInfo().getCommentId();
    }

    public static Comment findCommentByIdAndReturnIt(int commentId, UserForm userForm) {
        String commentExpression = String.format("//div[contains(@id, \"%d\") and contains(@id, \"post\")]", commentId);
        ILabel comment = userForm.returnElementFactory().getLabel(By.xpath(commentExpression), "comment");

        String commentContentExpression = String.format("//div[contains(@id, \"%d\")]//div[@class=\"wall_reply_text\"]", commentId);
        ILabel commentContent = comment.findChildElement(By.xpath(commentContentExpression), ILabel.class);
        String commentText = commentContent.getText();

        String commentAuthorExpression = "//a[@class=\"author\"]";
        ILabel commentAuthor = comment.findChildElement(By.xpath(commentAuthorExpression), ILabel.class);
        String dataFromId = commentAuthor.getAttribute("data-from-id");
        int commentAuthorId = Integer.parseInt(dataFromId);

        return new Comment(commentAuthorId, commentText);
    }

    public static boolean checkIfLikeFromUser(int actualId, int postId) {
        LikedUser[] users = getLikedUsers(postId);
        return Arrays.stream(users).anyMatch(user -> user.getUid() == actualId);
    }

    private static LikedUser[] getLikedUsers(int postId) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.WALL_GET_LIKES.getMethod());
        LikesInitialResponse response = Unirest.post(urlBase)
                .field("post_id", postId)
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .asObject(LikesInitialResponse.class)
                .getBody();
        return response.getLikesContainer().getUsers();
    }

    public static boolean deletePost(int postId, int actualUserId) {
        String urlBase = String.format(API_QUERY_DRAFT, ApiMethods.WALL_DELETE.getMethod());
        DeletePostInitialResponse response = Unirest.post(urlBase)
                .field("owner_id", actualUserId)
                .field("post_id", String.valueOf(postId))
                .field("access_token", ACCESS_TOKEN)
                .field("v", API_VERSION)
                .asObject(DeletePostInitialResponse.class)
                .getBody();
        return response.ifDeletionSuccessful();
    }

    public static boolean checkIfPostDeleted(String postText, int postId, UserForm userForm) {
        ILabel wallPostWithText = userForm.getWallPostByTextAndPostId(postText, postId);
        return wallPostWithText.state().waitForNotDisplayed();
    }
}
