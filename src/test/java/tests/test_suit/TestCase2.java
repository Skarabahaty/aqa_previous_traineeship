package tests.test_suit;

import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase2 extends BaseTest {

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

        logStep("accept cookies");
        loginWithPasswordCheckForm.acceptCookies();
        Assert.assertTrue(loginWithPasswordCheckForm.isCookiesFormDisappeared(),
                "cookies form is on page");
    }
}
