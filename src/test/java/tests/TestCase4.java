package tests;

import entities.forms.LoginWithPasswordCheckForm;
import entities.forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase4 extends BaseTest {

    @Test
    public void test() {
        String mainPageUrl = testData.getValue("main_page").toString();
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        String timerState = loginWithPasswordCheckForm.getTimerState();
        String expectedTImerState = testData.getValue("expected_timer_state").toString();
        Assert.assertEquals(timerState, "00:00:00");
    }
}
