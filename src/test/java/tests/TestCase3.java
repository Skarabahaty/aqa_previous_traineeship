package tests;

import entities.forms.LoginWithPasswordCheckForm;
import entities.forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest {

    @Test
    public void test() {
        browser.goTo("https://userinyerface.com/");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();

        loginWithPasswordCheckForm.clickSendToBottomButton();
        loginWithPasswordCheckForm.waitForHelpFormContentToDisappear();
        Assert.assertTrue(loginWithPasswordCheckForm.isHelpFormContentDisappear());
    }
}
