/***
 * Extent report class to initialize, update and generate test report
 */

package helper;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import applicationsettings.ApplicationSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReport {

    public static com.aventstack.extentreports.ExtentReports extentReport;
    public static String extentConfigPath = System.getProperty("user.dir") + "\\extent-config.xml";
    public static com.aventstack.extentreports.ExtentTest reportLogger;
    private static long startTime;

    // private constructor to restrict the initialization of this class
    public ExtentReport() {

    }

    /***
     * Implementing set test start time functionality
     */
    public static void setTestStartTime() {
        // TO Do: this method is not tested
        startTime = System.currentTimeMillis();
    }


    /***
     * Implementing extent report functionality to generate custom report
     */
    public static void generateReport() {
        extentReport.flush();

    }

    /***
     * Implementing create extent report method to generate custom report
     */
    public static void createExtentReport(String suiteName) {

        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy_MM_dd_hh_mm_ss_a";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDate = now.format(formatter);

        String reportDirectory = System.getProperty("user.dir") + "/test-output/test-reports/" + formattedDate + "/";
        extentReport = new com.aventstack.extentreports.ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportDirectory + "/" + suiteName + "-" + formattedDate + ".html");
        htmlReporter.loadXMLConfig(extentConfigPath);
        extentReport.attachReporter(htmlReporter);
        ApplicationSettings.setReportDirectory(formattedDate);
    }

    /***
     * Implementing create test method
     */
    public static void createTest(String testCaseName, String description, String category) throws IOException {
        reportLogger = extentReport.createTest(testCaseName, description);
        reportLogger.assignCategory(category);
    }

    public static void writeTimeStampIntoFile(String dateTime) {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                String text = "Timestamp " + dateTime + "\r\n";
                Files.write(Paths.get("filename.txt"), text.getBytes(), StandardOpenOption.APPEND);
            } else {
                String text = "Timestamp " + dateTime + "\r\n";
                Files.write(Paths.get("filename.txt"), text.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
