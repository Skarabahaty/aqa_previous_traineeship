package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected Browser browser;
    protected final org.slf4j.Logger logger;
    protected final JsonSettingsFile testData;
    protected final JsonSettingsFile configData;
    protected int stepCounter;

    protected BaseTest() {
        testData = new JsonSettingsFile("test_data.json");
        configData = new JsonSettingsFile("test_config.json");
        logger = LoggerFactory.getLogger(BaseTest.class);
        stepCounter = 0;
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

    protected void logStep(String description) {
        String message = String.format("Step %d: %s", ++stepCounter, description);
        logger.info(message);
    }
}
