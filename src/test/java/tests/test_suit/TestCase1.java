package tests.test_suit;

import forms.InterestsForm;
import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import forms.PersonalDetailsForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase1 extends BaseTest {

    @Test
    public void test() {
        String mainPageUrl = configData.getValue("/main_page").toString();
        logger.info("go to main page");
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(),
                "main page isn't displayed");

        logger.info("click start link");
        mainPage.clickStartLink();

        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        Assert.assertTrue(loginWithPasswordCheckForm.state().waitForDisplayed(),
                "login with password check form isn't displayed");

        int emailLettersAmount = (int) configData.getValue("/email_letters");
        int domainLettersAmount = (int) configData.getValue("/domain_letters");

        logger.info("set email and return it");
        String email = loginWithPasswordCheckForm.setEmailAndReturnIt(emailLettersAmount);

        logger.info("set domain");
        loginWithPasswordCheckForm.setDomain(domainLettersAmount);

        logger.info("set dot smth");
        loginWithPasswordCheckForm.setDotSomething();

        logger.info("set email and return it");
        loginWithPasswordCheckForm.setPassword(email);

        logger.info("accepting terms");
        loginWithPasswordCheckForm.acceptTerms();

        logger.info("click next button");
        loginWithPasswordCheckForm.clickNextButton();

        InterestsForm interestsForm = new InterestsForm();
        Assert.assertTrue(interestsForm.state().waitForDisplayed(),
                "interests form isn't displayed");

        int interestsNumber = (int) testData.getValue("/interests_number");
        logger.info("choose interests");
        interestsForm.chooseInterests(interestsNumber);

        String avatarName = configData.getValue("/picture_name").toString();
        String avatarStorageFolder = configData.getValue("/picture_store_directory").toString();
        int robotDelay = (int) configData.getValue("/robot_delay");

        logger.info("download avatar");
        interestsForm.downloadAvatar(avatarName, avatarStorageFolder, robotDelay);

        logger.info("click next button");
        interestsForm.clickNextButton();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed(),
                "personal details page isn't opened");
    }
}
