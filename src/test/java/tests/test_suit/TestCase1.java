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
        String mainPageUrl = testData.getValue("/main_page").toString();
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        Assert.assertTrue(loginWithPasswordCheckForm.state().waitForDisplayed());

        scrollDownALittleBit();

        int emailLetters = (int) configData.getValue("/email_letters");
        int domainLetters = (int) configData.getValue("/domain_letters");

        loginWithPasswordCheckForm.fillForm(emailLetters, domainLetters);
        loginWithPasswordCheckForm.acceptTerms();
        loginWithPasswordCheckForm.clickNextButton();

        InterestsForm interestsForm = new InterestsForm();
        Assert.assertTrue(interestsForm.state().waitForDisplayed());

        scrollDownALittleBit();

        int unselectAllIndex = (int) testData.getValue("/unselect_all_index");
        int selectAllIndex = (int) testData.getValue("/select_all_index");

        interestsForm.chooseThreeInterests(unselectAllIndex, selectAllIndex);
        interestsForm.downloadAvatar();

        scrollDownALittleBit();

        interestsForm.clickNextButton();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed());
    }
}
