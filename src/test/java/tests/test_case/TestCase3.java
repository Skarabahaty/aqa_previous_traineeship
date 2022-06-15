package tests.test_case;

import forms.LoginWithPasswordCheckForm;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestCase3 extends BaseTest {

    @Test
    public void test() {
        String mainPageUrl = testData.getValue("/main_page").toString();
        browser.goTo(mainPageUrl);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        loginWithPasswordCheckForm.clickSendToBottomButton();

        int waitTime = (int) configData.getValue("/wait_for_form_disappear");
        loginWithPasswordCheckForm.waitForHelpFormContentToDisappear(waitTime);

        Assert.assertTrue(loginWithPasswordCheckForm.isHelpFormContentDisappear());
    }
}
