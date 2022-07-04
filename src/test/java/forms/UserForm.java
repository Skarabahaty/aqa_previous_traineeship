package forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import models.Comment;
import org.openqa.selenium.By;

public class UserForm extends Form {

    private final String likeButtonByPostIdDraft = "//div[contains(@id, '%d')]//div[contains(@class, 'PostBottomActionContainer ')]";
    private final String wallPostLocatorDraft = "//div[contains(@class, 'wall_post_text') and contains(text(), '%s')]//ancestor::div[contains(@data-post-id, '%d')]";
    private final String showNextCommentLinkXpathDraft = "//a[contains(@onclick, 'showNextReplies') and contains(@onclick, '%d')]//span[text()]";
    private final By likeCounterLocator = By.xpath("//div[contains(@class, 'counter') and contains(text(), '')]");
    private final String commentXpathExpressionDraft = "//div[contains(@id, '%d') and contains(@id, 'post')]";
    private final String commentContentXpathExpressionDraft = "//div[contains(@id, '%d')]//div[@class='wall_reply_text']";
    private final String commentAuthorXpathExpression = "//a[@class='author']";

    public UserForm() {
        super(By.id("wide_column"), "user page");
    }

    public ILabel getWallPostByTextAndPostId(String text, int postId) {
        String wallPostXpathExpression = String.format(wallPostLocatorDraft, text, postId);
        return getElementFactory().getLabel(By.xpath(wallPostXpathExpression), "wall post", ElementState.DISPLAYED);
    }

    public void clickShowNextCommentLink(int recordId) {
        String xpathExpression = String.format(showNextCommentLinkXpathDraft, recordId);
        ILabel showNextCommentLink = getElementFactory().getLabel(By.xpath(xpathExpression), "show next comment link", ElementState.DISPLAYED);
        showNextCommentLink.click();
    }

    public void likePost(int postId) {
        String xpathExpression = String.format(likeButtonByPostIdDraft, postId);
        IButton likeButton = getElementFactory().getButton(By.xpath(xpathExpression), "like button", ElementState.DISPLAYED);
        likeButton.click();
    }

    public void waitForLikeToAppear(int postId) {
        String xpathExpression = String.format(likeButtonByPostIdDraft, postId);
        IButton likeButton = getElementFactory().getButton(By.xpath(xpathExpression), "like button", ElementState.DISPLAYED);
        ILabel likeCounter = likeButton.findChildElement(likeCounterLocator, ILabel.class);
        likeCounter.state().waitForDisplayed();
    }

    public Comment findCommentByIdAndReturnIt(int commentId) {
        String commentExpression = String.format(commentXpathExpressionDraft, commentId);
        ILabel comment = getElementFactory().getLabel(By.xpath(commentExpression), "comment");

        String commentContentExpression = String.format(commentContentXpathExpressionDraft, commentId);
        ILabel commentContent = comment.findChildElement(By.xpath(commentContentExpression), ILabel.class);
        String commentText = commentContent.getText();

        ILabel commentAuthor = comment.findChildElement(By.xpath(commentAuthorXpathExpression), ILabel.class);
        String dataFromId = commentAuthor.getAttribute("data-from-id");
        int commentAuthorId = Integer.parseInt(dataFromId);

        return new Comment(commentAuthorId, commentText);
    }
}
