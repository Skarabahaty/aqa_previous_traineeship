package forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import models.Comment;
import org.openqa.selenium.By;

public class UserForm extends Form {

    private final String likeButtonByPostIdDraft = "//div[contains(@id, \"%d\")]//div[contains(@class, \"PostBottomActionContainer \")]";

    public UserForm() {
        super(By.id("wide_column"), "user page");
    }

    public ILabel getWallPostByTextAndPostId(String text, int postId) {
        String wallPostLocatorDraft = "//div[contains(@class, \"wall_post_text\") and contains(text(), \"%s\")]//ancestor::div[contains(@data-post-id, \"%d\")]";
        String wallPostXpathExpression = String.format(wallPostLocatorDraft, text, postId);
        return getElementFactory().getLabel(By.xpath(wallPostXpathExpression), "wall post", ElementState.DISPLAYED);
    }

    public void clickShowNextCommentLink(int recordId) {
        String showNextCommentLinkXpathDraft = "//a[contains(@onclick, \"showNextReplies\") and contains(@onclick, \"%d\")]//span[text()]";
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
        ILabel likeCounter = likeButton.findChildElement(By.xpath("//div[contains(@class, \"counter\") and contains(text(), \"\")]"), ILabel.class);
        likeCounter.state().waitForDisplayed();
    }

    public Comment findCommentByIdAndReturnIt(int commentId) {
        String commentExpression = String.format("//div[contains(@id, \"%d\") and contains(@id, \"post\")]", commentId);
        ILabel comment = getElementFactory().getLabel(By.xpath(commentExpression), "comment");

        String commentContentExpression = String.format("//div[contains(@id, \"%d\")]//div[@class=\"wall_reply_text\"]", commentId);
        ILabel commentContent = comment.findChildElement(By.xpath(commentContentExpression), ILabel.class);
        String commentText = commentContent.getText();

        String commentAuthorExpression = "//a[@class=\"author\"]";
        ILabel commentAuthor = comment.findChildElement(By.xpath(commentAuthorExpression), ILabel.class);
        String dataFromId = commentAuthor.getAttribute("data-from-id");
        int commentAuthorId = Integer.parseInt(dataFromId);

        return new Comment(commentAuthorId, commentText);
    }
}
