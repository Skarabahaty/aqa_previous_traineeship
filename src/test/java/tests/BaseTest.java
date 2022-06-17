package tests;

import data.ConfigData;
import data.TestData;
import org.testng.annotations.BeforeClass;
import utils.JsonReader;

public abstract class BaseTest {

    protected TestData testData;
    protected ConfigData configData;


    @BeforeClass
    public void beforeClass() {
        testData = new TestData(JsonReader.getDataFromFile("test_data.json"));
        configData = new ConfigData(JsonReader.getDataFromFile("config_data.json"));
    }
}
