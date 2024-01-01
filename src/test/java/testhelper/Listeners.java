package testHelper;

import pageobjects.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    public static int passedTestCasesCount = 0;
    public static int failedTestCasesCount = 0;


    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        BasePage.stepFailed("Assertion Failed. Found result: " + iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {
        failedTestCasesCount = iTestContext.getFailedTests().size();
        passedTestCasesCount = iTestContext.getPassedTests().size();
    }
}
