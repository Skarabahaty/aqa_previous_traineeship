package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected final IElementFactory elementFactory;
    protected final Browser browser;

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
        browser = AqualityServices.getBrowser();
    }

    @BeforeMethod
    protected void beforeMethod() {
        browser.maximize();
    }

    @AfterMethod
    protected void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    public void scrollDownALittleBit() {
        AqualityServices.getBrowser().scrollWindowBy(0, 200);
    }

}
