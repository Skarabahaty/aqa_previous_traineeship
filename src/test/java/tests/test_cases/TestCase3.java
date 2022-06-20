package tests.test_cases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.UnirestObjectsUtil;

public class TestCase3 extends BaseTest {

    @Test
    public void testGetWrongPost() throws UnirestException {

        String testCaseData = testData.getString("case_3");
        testPageURL.append(testCaseData);

        HttpResponse<JsonNode> jsonNodeHttpResponse =
                Unirest.get(String.valueOf(testPageURL))
                        .asJson();

        int status = jsonNodeHttpResponse.getStatus();
        int expectedStatus = testData.getInt("not_found_code");
        Assert.assertEquals(status, expectedStatus, "status isn't correct");

        Assert.assertTrue(UnirestObjectsUtil.isPostEmpty(jsonNodeHttpResponse));
    }
}
