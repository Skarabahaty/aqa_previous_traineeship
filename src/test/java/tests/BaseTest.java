package tests;

import data.ConfigData;
import data.TestData;
import org.testng.annotations.BeforeTest;
import utils.JsonReader;

public abstract class BaseTest {

    protected TestData testData;
    protected ConfigData configData;
    protected StringBuilder testPageURL;

    @BeforeTest
    public void beforeClass() {
        testData = new TestData(JsonReader.getDataFromFile("test_data.json"));
        configData = new ConfigData(JsonReader.getDataFromFile("config_data.json"));
        testPageURL.append(configData.getString("main_page"));
    }

}
