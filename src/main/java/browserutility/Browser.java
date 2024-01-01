/**
 * Browser class implementation.
 * Author: Mahfuz, Date: December 15, 2023
 */

package browserutility;

import applicationsettings.ApplicationSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Browser {

    private Browser() {
        //private constructor to prevent initializing the browser object.
    }

    private static WebDriver webDriver;

    /***
     * Implementing launch browser functionality
     */
    public static void launchBrowser() {
        String browser = ApplicationSettings.getBrowserName();
        try {
            if (browser.equalsIgnoreCase("Chrome")) {

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
            webDriver.manage().window().maximize();
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    /***
     * Implementing get webdriver functionality
     * @return
     */
    public static WebDriver getWebDriver() {
        return webDriver;
    }

    /***
     * Implementing goToUrl functionality
     * @param url
     */
    public static void goToUrl(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * Implementing closeFocusedScreen functionality
     */
    public static void closeFocusedScreen() {
        try {
            webDriver.close();
        } catch (Exception e) {
        }
    }

    /***
     * Implementing quitBrowser functionality
     */
    public static void quitBrowser() {
        try {
            webDriver.quit();
        } catch (Exception e) {

        }
    }

    /**
     * Implementing hover functionality
     *
     * @param webElement
     * @return
     */
    public static void moveToAnElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).perform();
        } catch (WebDriverException exception) {

        }
    }

    /**
     * Implementing is expected element clickable functionality
     *
     * @param webElement
     * @return
     */
    public static boolean isExpectedElementClickable(WebElement webElement, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 120);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (WebDriverException exception) {

        }
        return false;
    }

    /**
     * get the latest navigated url
     *
     * @return the url
     */
    public static String getCurrentURL() {
        String url = "";
        try {
            ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));
            url = webDriver.getCurrentUrl();
            closeFocusedScreen();
            webDriver.switchTo().window(tabs.get(0));
        } catch (WebDriverException exception) {

        }
        return url;
    }
}
