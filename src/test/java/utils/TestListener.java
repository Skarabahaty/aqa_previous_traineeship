package utils;

import models.Test;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private Test test = new Test();


    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getName();
        String method = result.getMethod().getMethodName();
        long startMillis = result.getStartMillis();

        test.setName(name);
        test.setMethod_name(method);
        test.setStart_time(startMillis);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        test.setEnd_time(endMillis);
        test.setStatus_id(status);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        test.setEnd_time(endMillis);
        test.setStatus_id(status);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        long endMillis = result.getEndMillis();
        int status = result.getStatus();

        test.setEnd_time(endMillis);
        test.setStatus_id(status);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(test.toString());
    }
}
