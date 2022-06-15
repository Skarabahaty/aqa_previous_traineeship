package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.PathProvider;
import utils.Randomizer;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

public class InterestsForm extends Form {

    public InterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests-page']"), "interests form");
    }


    public void chooseThreeInterests(int unselectAllIndex, int selectAllIndex) {
        List<ICheckBox> checkBoxes = getElementFactory().findElements(By.className("checkbox__box"), ICheckBox.class);
        checkBoxes.get(unselectAllIndex).click();
        Set<Integer> threeRandomIndexes = Randomizer.getThreeRandomIndexes(selectAllIndex);
        for (int randomIndex : threeRandomIndexes) {
            checkBoxes.get(randomIndex).click();
        }
    }

    public void downloadAvatar() {

        try {
            Robot robot = new Robot();

            String avatarPath = PathProvider.getAvatarPath();

            StringSelection stringSelection = new StringSelection(avatarPath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            ILink link = getElementFactory().getLink(By.linkText("upload"), "download avatar link");
            link.click();

            robot.delay(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clickNextButton() {
        IButton nextButton = getElementFactory().getButton(By.xpath("//button[text()='Next']"), "next button");
        nextButton.getMouseActions().moveMouseToElement();
        nextButton.click();
    }
}
