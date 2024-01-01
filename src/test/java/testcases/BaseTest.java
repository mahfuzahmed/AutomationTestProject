/**
 * Base test class implementation.
 * Author: Mahfuz, Date: October 17, 2023
 */

package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

import browserutility.Browser;
import applicationsettings.ApplicationSettings;
import helper.ExtentReport;

import java.io.IOException;


public class BaseTest {

    public static String suiteFileName;
    private static String browser;

    public static Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void getTestSuiteName(ITestContext context) {
        suiteFileName = context.getCurrentXmlTest().getName();
        LOGGER.info("Set Test Suite Name");
    }


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        LOGGER.info("Setup Started");
        ExtentReport.createExtentReport(suiteFileName);

        String os = "Win";
        browser = "Chrome";
        String environment = "Dev";

        // The below statement will work when test suite will be executed with mvn build command
        ApplicationSettings.setUp(os, browser, environment);

        Browser.launchBrowser();
        Browser.goToUrl(ApplicationSettings.getUrl());
        LOGGER.info("Setup Completed");
    }

    /***
     * Implementing before test method functionality
     */
    @BeforeMethod
    public void startTest() {
        // TO Do: this method is not tested. It has to be tested thoroughly before final integration to the report.
        ExtentReport.setTestStartTime();
        LOGGER.info("Test Started");
    }


    /***
     * Implementing initialize test functionality
     *
     * @param testName Test Case Name
     * @param testDescription Test Case Description
     * @param testCategory Test Case CategoryL
     */
    public void initializeTest(String testName, String testDescription, String testCategory) throws IOException {
        ExtentReport.createTest(testName, testDescription, testCategory);
    }

    /***
     * Implementing suite teardown functionality
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Browser.quitBrowser();
        ExtentReport.generateReport();
    }
}
