package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class InterestsForm extends Form {

    public InterestsForm() {
        super(By.className("avatar-and-interests-page"), "interests form");
    }

    private final By checkBoxNameLocator = By.xpath("//span[@class='checkbox small']//following-sibling::span[text()]");
    private final By checkBoxLocator = By.className("checkbox__box");
    private final By downloadAvatarLinkLocator = By.linkText("upload");
    private final By nextButtonLocator = By.xpath("//button[contains(text(), 'Next')]");
    private final ILink downloadAvatarLink = getElementFactory().getLink(downloadAvatarLinkLocator, "download avatar link");
    private final IButton nextButton = getElementFactory().getButton(nextButtonLocator, "next button");

    public void chooseInterests(int interestsNumber) {

        List<ILabel> checkBoxesNames = getElementFactory().findElements(checkBoxNameLocator, ILabel.class);
        List<ICheckBox> checkBoxes = getElementFactory().findElements(checkBoxLocator, ICheckBox.class);

        int unselectAllCheckBoxIndex = CheckBoxesProcessor.findUnselectAllCheckBoxIndex(checkBoxesNames);
        checkBoxes.get(unselectAllCheckBoxIndex).click();

        int selectAllCheckBoxIndex = CheckBoxesProcessor.findSelectAllCheckBoxIndex(checkBoxesNames);

        int interestsOverallNumber = checkBoxes.size();
        Set<Integer> randomInterests = Randomizer.getRandomIndexes(
                interestsNumber,
                unselectAllCheckBoxIndex,
                selectAllCheckBoxIndex,
                interestsOverallNumber);

        for (int index : randomInterests) {
            checkBoxes.get(index).click();
        }
    }

    public void downloadAvatar(String avatarName, String avatarStorageFolder, int robotDelay) {

        Path avatarPath = PathProvider.getPicturePath(avatarName, avatarStorageFolder);
        downloadAvatarLink.click();
        ClipboardUtil.setValueToClipBoard(avatarPath);
        RobotUtil.sendImageUsingRobot(robotDelay);
    }

    public void clickNextButton() {
        nextButton.click();
    }
}
