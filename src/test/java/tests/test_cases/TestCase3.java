package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.ResponseObjectGenerator;

public class TestCase3 extends BaseTest {

    @Test
    public void testGetWrongPost() throws UnirestException {

        String mainPage = configData.getString("main_page");
        String testCase = testData.getString("case_3");
        String testPage = mainPage.concat(testCase);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(testPage)
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("not_found_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        JsonNode body = jsonNodeHttpResponse.getBody();
        boolean responseObjectAbleToGenerate = ResponseObjectGenerator.isResponseObjectAbleToGenerate(body);
        Assert.assertFalse(responseObjectAbleToGenerate, "response object is able to generate");
    }
}
