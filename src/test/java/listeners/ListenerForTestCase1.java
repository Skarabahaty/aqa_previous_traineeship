package listeners;

import models.Author;
import models.Project;
import models.Session;
import models.TestEntry;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.tables_utils.AuthorTableUtil;
import utils.tables_utils.ProjectTableUtil;
import utils.tables_utils.SessionTableUtil;
import utils.tables_utils.TestTableUtil;

public class ListenerForTestCase1 implements ITestListener {

    private TestEntry testEntry;

    @Override
    public void onStart(ITestContext context) {

        Session session = SessionTableUtil.getSessionAndAddItInDB();

        Author author = AuthorTableUtil.getAuthorAndAddItInDB();

        Project project = ProjectTableUtil.getProjectAndAddTiInDB();

        testEntry = TestTableUtil.getTestEntry(session, author, project);
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
