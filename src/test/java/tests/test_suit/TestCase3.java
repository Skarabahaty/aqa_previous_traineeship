package tests.test_suit;

import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase3 extends BaseTest {

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

        logger.info("click send to bottom button");
        loginWithPasswordCheckForm.clickSendToBottomButton();

        logger.info("wait for help form content to disappear");
        loginWithPasswordCheckForm.waitForHelpFormContentToDisappear();

        Assert.assertFalse(loginWithPasswordCheckForm.isHelpFormContentDisplayed(),
                "help form content is visible");
    }
}
