package utils;

import models.TestEntry;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private final TestEntry testEntry = new TestEntry();

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
        JDBConnector.addEntity(testEntry);
    }
}
