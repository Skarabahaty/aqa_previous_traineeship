package tests;

import data.ConfigData;
import data.TestData;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonReader;
import utils.Session;

public abstract class BaseTest {

    protected TestData testData;
    protected ConfigData configData;
    protected StringBuilder urlBase;
    protected Session session;
    protected Logger logger;
    protected int counter;

    static {
        Unirest.config().setObjectMapper(new JsonObjectMapper());
    }

    public BaseTest() {
        testData = new TestData(JsonReader.getDataFromFile("test_data.json"));
        configData = new ConfigData(JsonReader.getDataFromFile("config_data.json"));
        urlBase = new StringBuilder(configData.getString("main_page"));
        session = new Session();
        logger = LoggerFactory.getLogger(this.getClass());
        counter = 0;
    }

    public String setUrl(String configData, String testData) {
        return urlBase.append(configData).append(testData).toString();
    }

    public String setUrl(String configData) {
        return setUrl(configData, "");
    }

    public void logStep(String description) {
        logger.info(String.format("Step %d, %s", ++counter, description));
    }


}
