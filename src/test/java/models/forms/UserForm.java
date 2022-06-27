package models.forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
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

    public IElementFactory returnElementFactory() {
        return getElementFactory();
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
}
