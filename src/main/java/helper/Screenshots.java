/***
 * Screenshot is a helper class which helps to capture custom screenshot.
 * <p>
 * @use {@link #takeScreenshot(String)} method to capture screenshot.
 *  </p>
 */
package helper;

import browserutility.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Screenshots {

    public static String getDate(){
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy_MM_dd_hh_mm_ss_a";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }

    public static String takeScreenshot(String fileName, String reportDirectory) throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) Browser.getWebDriver();
        File screenshotSource = screenshot.getScreenshotAs(OutputType.FILE);

        String screenshotFilePath = System.getProperty("user.dir") + "/test-output/test-reports/" + reportDirectory + "/Screenshots/" + fileName + "_" + getDate() + ".png";

        File screenshotDestination = new File(screenshotFilePath);
        FileUtils.copyFile(screenshotSource, screenshotDestination);
        return screenshotFilePath;
    }

}
