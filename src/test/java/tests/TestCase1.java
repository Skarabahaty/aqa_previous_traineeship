package tests;

import entities.forms.InterestsForm;
import entities.forms.LoginWithPasswordCheckForm;
import entities.forms.MainPage;
import entities.forms.PersonalDetailsForm;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest {

    @Test
    public void testName() {
        browser.maximize();
        browser.goTo("https://userinyerface.com/");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        Assert.assertTrue(loginWithPasswordCheckForm.state().waitForDisplayed());

        scrollABit();

        loginWithPasswordCheckForm.fillForm();
        loginWithPasswordCheckForm.acceptTerms();
        loginWithPasswordCheckForm.clickNextButton();

        InterestsForm interestsForm = new InterestsForm();
        Assert.assertTrue(interestsForm.state().waitForDisplayed());

        scrollABit();

        interestsForm.chooseThreeInterests();
        interestsForm.downloadAvatar();
        interestsForm.clickNextButton();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed());
    }
}
