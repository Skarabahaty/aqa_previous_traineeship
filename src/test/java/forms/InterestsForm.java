package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class InterestsForm extends Form {

    public InterestsForm() {
        super(By.className("avatar-and-interests-page"), "interests form");
    }

    private final By checkBoxNameLocator = By.xpath("//span[@class='checkbox small']//following-sibling::span[text()]");
    private final List<ILabel> checkBoxesNames = getElementFactory().findElements(checkBoxNameLocator, ILabel.class);
    private final By checkBoxLocator = By.className("checkbox__box");
    private final List<ICheckBox> checkBoxes = getElementFactory().findElements(checkBoxLocator, ICheckBox.class);
    private final By downloadAvatarLinkLocator = By.linkText("upload");
    private final By nextButtonLocator = By.xpath("//button[contains(text(), 'Next')]");
    private final ILink downloadAvatarLink = getElementFactory().getLink(downloadAvatarLinkLocator, "download avatar link");
    private final IButton nextButton = getElementFactory().getButton(nextButtonLocator, "next button");

    public void clickCheckBoxByIndex(int index) {
        checkBoxes.get(index).click();
    }

    public List<ILabel> getCheckBoxesNames() {
        return checkBoxesNames;
    }

    public List<ICheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public void clickDownloadAvatarLink() {
        downloadAvatarLink.click();
    }

    public void clickNextButton() {
        nextButton.getJsActions().scrollBy(0, 500);
        nextButton.click();
    }
}
