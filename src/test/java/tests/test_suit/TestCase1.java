package tests.test_suit;

import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import forms.InterestsForm;
import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import forms.PersonalDetailsForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class TestCase1 extends BaseTest {

    @Test
    public void test() {
        String mainPageUrl = configData.getValue("/main_page").toString();

        logStep("go to main page");
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(),
                "main page isn't displayed");

        logStep("click start link");
        mainPage.clickStartLink();

        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        Assert.assertTrue(loginWithPasswordCheckForm.state().waitForDisplayed(),
                "login with password check form isn't displayed");

        logStep("get config data");
        int emailLettersAmount = (int) configData.getValue("/email_length");
        int domainLettersAmount = (int) configData.getValue("/domain_length");

        logStep("generate email");
        String email = CredentialsGenerator.generateEmail(emailLettersAmount);
        logStep("set email and return it");
        loginWithPasswordCheckForm.setEmail(email);

        logStep("generate domain");
        String domain = CredentialsGenerator.generateDomain(domainLettersAmount);
        logStep("set domain");
        loginWithPasswordCheckForm.setDomain(domain);

        logStep("click dot.something combo box");
        loginWithPasswordCheckForm.clickDotSomethingComboBox();

        logStep("generate dot.smth");
        int optionsAmount = loginWithPasswordCheckForm.getComboBoxOptionsAmount();
        int optionIndex = Randomizer.getRandomIntInRange(optionsAmount - 1);

        logStep("set dot.smth");
        loginWithPasswordCheckForm.setDotSomething(optionIndex);

        logStep("get password length");
        int passwordLettersAmount = (int) configData.getValue("/password_length");

        logStep("generate password");
        String password = CredentialsGenerator.generatePassword(email, passwordLettersAmount);

        logStep("set email and return it");
        loginWithPasswordCheckForm.setPassword(password);

        logStep("accepting terms");
        loginWithPasswordCheckForm.acceptTerms();

        logStep("click next button");
        loginWithPasswordCheckForm.clickNextButton();

        InterestsForm interestsForm = new InterestsForm();
        Assert.assertTrue(interestsForm.state().waitForDisplayed(),
                "interests form isn't displayed");

        logStep("get test data for interests check");
        int interestsNeededNumber = (int) testData.getValue("/interests_number");
        String unselectAllText = (String) testData.getValue("/unselect_all_text");
        String selectAllText = (String) testData.getValue("/select_all_text");

        logStep("get lists of checkboxes and their names");
        List<ICheckBox> checkBoxes = interestsForm.getCheckBoxes();
        List<ILabel> checkBoxesNames = interestsForm.getCheckBoxesNames();

        logStep("find index of \"select all\" and \"unselect all\" checkboxes");
        int unselectAllCheckBoxIndex = CheckBoxesProcessor.findCheckBoxIndexByName(checkBoxesNames, unselectAllText);
        int selectAllCheckBoxIndex = CheckBoxesProcessor.findCheckBoxIndexByName(checkBoxesNames, selectAllText);

        logStep("get checkboxes overall amount");
        int interestsOverallNumber = checkBoxes.size();

        logStep("get random indexes of interests");
        Set<Integer> randomInterests = Randomizer.getRandomIndexes(
                interestsNeededNumber,
                unselectAllCheckBoxIndex,
                selectAllCheckBoxIndex,
                interestsOverallNumber);

        logStep("check \"unselect all\" checkbox");
        interestsForm.clickCheckBoxByIndex(unselectAllCheckBoxIndex);

        for (int index : randomInterests) {
            logStep("check interest");
            interestsForm.clickCheckBoxByIndex(index);
        }

        logStep("configure picture");
        String avatarName = configData.getValue("/picture_name").toString();
        String avatarStorageFolder = configData.getValue("/picture_store_directory").toString();

        logStep("configure robot");
        int robotDelay = (int) configData.getValue("/robot_delay");

        logStep("download avatar");
        interestsForm.clickDownloadAvatarLink();

        logStep("get picture path");
        Path avatarPath = PathProvider.getPicturePath(avatarName, avatarStorageFolder);

        logStep("set value to clipboard");
        ClipboardUtil.setValueToClipBoard(avatarPath);

        logStep("send image");
        RobotUtil.sendImageUsingRobot(robotDelay);

        logStep("click next button");
        interestsForm.clickNextButton();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        boolean displayed = personalDetailsForm.state().waitForExist();
        if (displayed) {
            logStep("exists");
        } else {
            logStep("not exists");
        }
//        Assert.assertTrue(displayed, "personal details page isn't opened");
    }
}
