package tests.test_case;

import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase2 extends BaseTest {

    @Test
    public void test() {
        String mainPageUrl = testData.getValue("/main_page").toString();
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        loginWithPasswordCheckForm.acceptCookies();
        Assert.assertFalse(loginWithPasswordCheckForm.ifCookiesFormPresent());
    }
}
