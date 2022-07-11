package listeners;

import com.google.gson.JsonObject;
import models.Author;
import models.Project;
import models.Session;
import models.TestEntry;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.JsonReader;
import utils.tables_utils.AuthorTableUtil;
import utils.tables_utils.ProjectTableUtil;
import utils.tables_utils.SessionTableUtil;
import utils.tables_utils.TestTableUtil;

public class ListenerForTestCase1 implements ITestListener {


    private TestEntry testEntry;

    @Override
    public void onStart(ITestContext context) {
        JsonObject dataFromFile = JsonReader.getDataFromFile("test_configs.json");

        int randomBuildNumber = dataFromFile.get("session_random_number_border").getAsInt();
        Session session = new Session(randomBuildNumber);
        SessionTableUtil.add(session);
        SessionTableUtil.checkForPresenceAndSetID(session);

        JsonObject authorData = dataFromFile.get("author").getAsJsonObject();
        Author author = new Author(authorData);
        if (! AuthorTableUtil.isAuthorPresentInDatabase(author)) {
            AuthorTableUtil.add(author);
        }
        AuthorTableUtil.checkForPresenceAndSetID(author);

        String projectName = dataFromFile.get("project").getAsString();
        Project project = new Project(projectName);
        if (!ProjectTableUtil.isProjectPresentInDatabase(project)) {
            ProjectTableUtil.add(project);
        }
        ProjectTableUtil.checkForPresenceAndSetID(project);

        String browser = dataFromFile.get("browser").getAsString();
        String env = dataFromFile.get("env").getAsString();
        String testName = dataFromFile.get("test_name").getAsString();

        testEntry = new TestEntry(testName, session.getId(), project.getId(), author.getId(), browser, env);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String method = result.getMethod().getMethodName();
        long startMillis = result.getStartMillis();

        testEntry.setMethodName(method);
        testEntry.setStartTime(startMillis);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        testEntry.setEndTime(endMillis);
        testEntry.setStatusId(status);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        testEntry.setEndTime(endMillis);
        testEntry.setStatusId(status);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        testEntry.setEndTime(endMillis);
        testEntry.setStatusId(status);
    }

    @Override
    public void onFinish(ITestContext context) {
        TestTableUtil.add(testEntry);
        TestEntry entryFromDatabase = TestTableUtil.checkForPresenceAndReturnEntry(testEntry);
        Assert.assertEquals(entryFromDatabase, testEntry, "test entry isn't present in table");
    }
}
