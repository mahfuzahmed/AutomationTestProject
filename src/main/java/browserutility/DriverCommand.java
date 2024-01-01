/**
 * Driver Command class implementation.
 * Author: Mahfuz, Date: October 17, 2023
 */

package browserutility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DriverCommand {

    final WebDriver webDriver;
    int waitTimeOut = 60;

    public DriverCommand() {
        this.webDriver = Browser.getWebDriver();
    }

    /***
     * Implementing overload of Webdriverwait functionality
     * @param wait: Take the timeout value as integer
     * @return
     */
    public WebDriverWait explicitlyWait(int wait) {
        try {
            return new WebDriverWait(webDriver, wait);
        } catch (Exception e) {
            return null;
        }
    }

    /***
     * Implementing implicitWait functionality. This function has been implemented to avoid Thread.sleep()
     * @param waitTimeOutInSeconds
     */
    public void implicitWait(int waitTimeOutInSeconds) {
        try {
            explicitlyWait(waitTimeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(By.id("dummy locator")));
        } catch (Exception exception) {
        }

    }

    /***
     * Implementing maximizeWindow functionality
     */
    public void maximizeWindow() {
        try {
            webDriver.manage().window().maximize();
        } catch (Exception e) {
        }
    }

    /***
     * Implementing minimizeWindow functionality
     */
    public void minimizeWindow() {
        try {
            webDriver.manage().window().maximize();
        } catch (Exception e) {
        }
    }

    /***
     * Implementing close focused screen functionality
     */
    public void closeFocusedScreen() {
        try {
            Browser.closeFocusedScreen();
        } catch (Exception e) {
        }
    }

    /***
     * Implementing get current page title functionality
     * @return current page title
     */
    public String getCurrentPageTitle() {
        try {
            String currentPageTitle = webDriver.getTitle();
            return currentPageTitle;
        } catch (Exception e) {
            return null;
        }
    }

    public void scriptExecutorClick(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
        }
    }

    public void reloadPage() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("location.reload()");
        } catch (Exception e) {
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
//            executor.executeScript("arguments[0].scrollIntoView();", element);
            executor.executeScript("window.scrollBy(0,200)", element);
        } catch (Exception e) {

        }
    }

    public void AcceptAlert() {
        try {
            explicitlyWait(30).until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
        } catch (Exception exception) {
        }
    }

    public String getAlertText() {
        try {
            explicitlyWait(30).until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            return alert.getText();
        } catch (Exception exception) {
        }
        return null;
    }

    public void DenyAlert() {
        try {
            explicitlyWait(30).until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            alert.dismiss();
        } catch (Exception exception) {
        }
    }

    public void switchTab(int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tabNumber));
        } catch (Exception exception) {
        }
    }

    public void navigateBack() {
        try {
            webDriver.navigate().back();
        } catch (Exception exception) {
        }
    }

    public WebElement elementSearch(By elementLocator) {
        try {
            return explicitlyWait(waitTimeOut).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement elementSearch(WebElement parentElement, By elementLocator) {
        try {
            return parentElement.findElement(elementLocator);
        } catch (Exception e) {
            return null;
        }
    }

    public List<WebElement> elementsSearch(By elementLocator) {
        List<WebElement> elements = new ArrayList<>();
        try {
            return explicitlyWait(waitTimeOut).until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
        } catch (Exception e) {
            return elements;
        }
    }

    public List<WebElement> multipleElementsSearch(WebElement parentElement, By elementLocator) {
        try {
            return parentElement.findElements(elementLocator);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAttributePresent(WebElement element, String attribute) {
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean isLocatorPresent(By locator) {
        try {
            return webDriver.findElements(locator).size() > 0;
        } catch (Exception e) {

        }
        return false;
    }

    public void moveToElementAction(WebElement element) {
        try {
            Actions action = new Actions(webDriver);
            action.moveToElement(element).perform();
        } catch (Exception e) {

        }
    }

    public void openPageInNewTab(WebElement element) {
        try {
            Actions newTab = new Actions(webDriver);
            newTab.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
        } catch (Exception e) {

        }
    }

    public void rightClick(WebElement element) {
        try {
            Actions actions = new Actions(webDriver);
            actions.contextClick(element).perform();
        } catch (Exception e) {
        }
    }
}
