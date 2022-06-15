package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import entities.forms.LoginWithPasswordCheckForm;
import entities.forms.MainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {

    @Test
    public void test() {
        browser.goTo("https://userinyerface.com/");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed());

        mainPage.clickStartLink();
        LoginWithPasswordCheckForm loginWithPasswordCheckForm = new LoginWithPasswordCheckForm();
        loginWithPasswordCheckForm.acceptCookies();
        Assert.assertFalse(loginWithPasswordCheckForm.ifCookiesFormPresent());
    }
}
