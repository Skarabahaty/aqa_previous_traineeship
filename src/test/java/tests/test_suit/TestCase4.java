package tests.test_suit;

import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase4 extends BaseTest {

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

        logStep("get timer state");
        String actualTimerState = loginWithPasswordCheckForm.getTimerState();

        String expectedTImerStartSequence = testData.getValue("/expected_timer_start").toString();
        Assert.assertTrue(actualTimerState.startsWith(expectedTImerStartSequence),
                "timer starts with wrong value");
    }
}
