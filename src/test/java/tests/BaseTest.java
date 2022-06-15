package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected Browser browser;
    protected final JsonSettingsFile testData;
    protected final JsonSettingsFile configData;

    protected BaseTest() {
        testData = new JsonSettingsFile("test_data.json");
        configData = new JsonSettingsFile("test_config.json");
    }

    @BeforeMethod
    protected void beforeMethod() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
    }

    @AfterMethod
    protected void afterTest() {
        AqualityServices.getBrowser().quit();
    }

    protected void scrollDownALittleBit() {
        int scrollPixelsAmount = (int) configData.getValue("/scroll_pixels_amount");
        AqualityServices.getBrowser().scrollWindowBy(0, scrollPixelsAmount);
    }

}
