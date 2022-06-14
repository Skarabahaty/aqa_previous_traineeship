package tests;

import entities.forms.LoginWithPasswordCheckForm;
import entities.forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest {

    @Test
    public void testName() {
        browser.goTo("https://userinyerface.com/");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        Assert.assertTrue(loginWithPasswordCheckForm.state().waitForDisplayed());

        loginWithPasswordCheckForm.fillForm();
    }
}
