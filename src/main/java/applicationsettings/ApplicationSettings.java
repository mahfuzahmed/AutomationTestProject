package applicationsettings;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationSettings {

    private static String fileExt;
    private static String browserName;
    private static String url;
    public static String reportDirectory;

    // Environment Details for Production
    private static final String productionUrl = "https://desk.enosis.com.bd/signin";
    private static final String devUrl = "http://localhost:3000/signin?credential=test:mahfuz.abir@enosisbd.com";
    private static final String stagingURL = "https://d3mc0swzhjhc7f.cloudfront.net/signin";
    private static final String productionTestDataFilePath = "src//test//java//testdata//test_Data.xls";

    private ApplicationSettings() { // private constructor
    }

    public static void setUp(String os, String browser, String environment) {
        if (!(System.getProperty("os.name").contains(os))) {
            String invalidOSstring = "!!!!! PROJECT IS TRYING TO BE RUN ON '" + os + "' OPERATING SYSTEM. !!!!!\n";
            System.err.println(invalidOSstring);
        }
        if (os.equalsIgnoreCase("Win"))
            fileExt = ".exe";
        else
            fileExt = "";

        if (browser == null)
            browserName = "Chrome";
        else
            browserName = browser;

        if (environment.equalsIgnoreCase("Production")) {
            url = productionUrl;
        } else if (environment.equalsIgnoreCase("Dev")) {
            url = devUrl;
        } else if (environment.equalsIgnoreCase("Staging")) {
            url = stagingURL;
        }
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static String getUrl() {
        return url;
    }

    public static String getProductionTestDataFilePath() {
        return productionTestDataFilePath;
    }

    public static String getLoginCredentialsSheetName() {
        return "login";
    }

    public static String getEnterpriseAdminLoginCredentialsTableName() {
        return "Credentials";
    }

    public static String getProductTableName() {
        return "ProductList";
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyyMMdd_HHmmssMS").format(new Date());
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static void setReportDirectory(String path) {
        reportDirectory = path;
    }

    public static String getReportDirectory() {
        return reportDirectory;
    }

}
