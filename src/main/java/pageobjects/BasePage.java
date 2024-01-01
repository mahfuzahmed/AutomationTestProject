package pageobjects;

import applicationsettings.ApplicationSettings;
import browserutility.Browser;
import browserutility.DriverCommand;
import com.aventstack.extentreports.MediaEntityBuilder;
import helper.ExtentReport;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import helper.Screenshots;

public class BasePage extends ExtentReport {

    private static final Logger LOGGER = LogManager.getLogger();

    DriverCommand driverCommand;

    //[Start]: Menu items
    public static final String homeTabLabel = "Home";
    public static final String reservationsTabLabel = "Reservations";
    public static final String adminTabLabel = "Admin";
    public static final String aboutTabLabel = "About";
    //[End]: Menu items

    /**
     * Constructor of Base class. initializing Driver Command class inside constructor.
     */
    public BasePage() {
        driverCommand = new DriverCommand();
    }

    public List<WebElement> elementsSearch(By elementLocator) {
        return driverCommand.elementsSearch(elementLocator);
    }

    /**
     * Implementing overload of Web driver wait functionality
     *
     * @param wait: Take the timeout value as integer
     * @return Web driver wait
     */
    public WebDriverWait explicitlyWait(int wait) {
        return driverCommand.explicitlyWait(wait);
    }

    /**
     * Implementing implicitWait functionality. This function has been implemented to avoid Thread.sleep()
     *
     * @param waitTimeOut
     */
    public void implicitlyWait(int waitTimeOut) {
        driverCommand.implicitWait(waitTimeOut);

    }

    /**
     * Implementing is expected element visible functionality
     *
     * @param locator
     * @param waitTimeOut
     * @return
     */
    public boolean isExpectedElementVisible(By locator, int waitTimeOut) {
        boolean status = false;
        try {
            explicitlyWait(waitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
            status = true;
            stepPassed("Expected element is visible.");
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("waiting for visibility of element located")) {
                stepFailed(getCurrentMethodName() + "Expected element is not visible. " + e.getMessage());
            }

        }
        return status;
    }

    /**
     * Implementing is expected element clickable functionality
     *
     * @param locator
     * @param waitTimeOut
     * @return
     */
    public boolean isExpectedElementClickable(By locator, int waitTimeOut) {
        boolean status = true;
        try {
            explicitlyWait(waitTimeOut).until(ExpectedConditions.elementToBeClickable(locator));
            stepPassed("Expected element is clickable.");
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("waiting for clickable element to be located")) {
                stepFailed(getCurrentMethodName() + "Expected element is not clickable. " + e.getMessage());
            }
            status = false;
        }
        return status;
    }

    /**
     * Implementing element search method
     *
     * @param elementLocator
     * @return web element
     */
    public WebElement elementSearch(By elementLocator) {
        WebElement element = null;
        try {
            element = driverCommand.elementSearch(elementLocator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    /**
     * Customized method for passed steps
     *
     * @param message message to be logged
     */
    public static void stepPassed(String message) {
        reportLogger.pass(message);
        LOGGER.info(message);
    }

    /**
     * Customized method for information
     */
    public static void informationLog(String message) {
        try {
            message = getCustomErrorMessage(message);
            InputStream inputStream = new FileInputStream(Screenshots.takeScreenshot("Screenshot", ApplicationSettings.getReportDirectory()));
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            reportLogger.info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            LOGGER.error(message);
        } catch (Exception e) {
            LOGGER.info("Could not execute stepFailed method." + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * customized method for failed steps
     *
     * @param message message to be logged
     */
    public static void stepFailed(String message) {
        try {
            message = getCustomErrorMessage(message);
            InputStream inputStream = new FileInputStream(Screenshots.takeScreenshot("Screenshot", ApplicationSettings.getReportDirectory()));
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            reportLogger.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * customized method for splitting error message
     *
     * @param message message to be logged
     * @return error message
     */
    public static String getCustomErrorMessage(String message) {
        try {
            int index = message.indexOf("Session");
            if (index > 0) {
                message = message.substring(0, index - 1);
            } else {
                return message;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * type on an input field
     *
     * @param element   web element of the field
     * @param inputText text to be typed in
     */
    public void typeOnInputField(WebElement element, String inputText) {
        try {
            element.clear();
            element.sendKeys(inputText);
            stepPassed(inputText + " is typed in the field.");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + inputText + " cannot be typed in. " + e.getMessage());
        }
    }

    /**
     * type on an input field
     *
     * @param element   web element of the field
     * @param inputText text to be typed in
     * @param fieldName field label
     */
    public void typeOnInputField(WebElement element, String inputText, String fieldName) {
        try {
            element.clear();
            element.sendKeys(inputText);
            stepPassed(inputText + " is typed in the field: " + fieldName);
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + inputText + " cannot be typed in the field : " + fieldName + e.getMessage());
        }
    }

    /**
     * click on a web element using element
     *
     * @param element web element to be clicked
     */
    public static void clickOnElement(WebElement element) {
        try {
            element.click();
            stepPassed("Element is clicked.");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Element cannot be clicked on. " + e.getMessage());
        }
    }

    /**
     * click on a web element using locator
     *
     * @param locator locator of the web element to be clicked
     */
    public void clickOnElement(By locator) {
        try {
            WebElement clickableElement = explicitlyWait(2).until(ExpectedConditions.elementToBeClickable(locator));
            clickableElement.click();
            stepPassed("Element is clicked.");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Element cannot be clicked on. " + e.getMessage());
        }
    }

    /**
     * click on a button
     *
     * @param element      web element to be clicked
     * @param elementLabel label of the element
     */
    public void clickOnElement(WebElement element, String elementLabel) {
        try {
            element.click();
            stepPassed("'" + elementLabel + "'Element is clicked.");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "'" + elementLabel + "'Element cannot be clicked on. " + e.getMessage());
        }
    }

    /**
     * wait for a time while the specific element is loading
     *
     * @param time waiting time
     */
    public void waitWhileLoading(int time) {
        implicitlyWait(time);
    }

    public static String getText(WebElement element) {

        String value = "";
        try {
            value = element.getText();
            stepPassed("Able to get text from element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to get text from element" + e.getMessage());
        }
        return value;
    }

    public String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return currentDate.format(formatter);
    }

    /**
     * get name of the currently executing method
     *
     * @return method name
     */
    public static String getCurrentMethodName() {
        String nameOfCurrMethod = new Throwable().getStackTrace()[1].getMethodName();
        nameOfCurrMethod = "Method Name : " + nameOfCurrMethod + ". ";
        return nameOfCurrMethod;
    }

    /**
     * assertEquals with string for pass fail
     *
     * @param actualValue   actualValue
     * @param expectedValue expectedValue
     */
    public void assertEquals(String actualValue, String expectedValue) {
        if (Objects.equals(actualValue, expectedValue)) {
            stepPassed("Actual value: '" + actualValue + "' is same as expected value: '" + expectedValue + "'");
        } else {
            stepFailed("Actual value: '" + actualValue + "' is not same as expected value: '" + expectedValue + "'");
        }
    }

    /**
     * assertEquals with boolean for pass fail
     *
     * @param actualValue   actualValue
     * @param expectedValue expectedValue
     */
    public void assertEquals(boolean actualValue, boolean expectedValue) {
        if (Objects.equals(actualValue, expectedValue)) {
            stepPassed("Actual value: '" + actualValue + "' is same as expected value: '" + expectedValue + "'");
        } else {
            stepFailed("Actual value: '" + actualValue + "' is not same as expected value: '" + expectedValue + "'");
        }
    }

    /**
     * Click on a button
     *
     * @param buttonName button label
     */
    public void clickOnButton(String buttonName) {
        try {
            By desiredButton = By.xpath("//button[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + buttonName.toLowerCase() + "']");
            WebElement clickableElement = explicitlyWait(2).until(ExpectedConditions.elementToBeClickable(desiredButton));
            clickOnElement(clickableElement);
            stepPassed(getCurrentMethodName() + "Successfully clicked on '" + buttonName + "' button");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on '" + buttonName + "' button" + e.getMessage());
        }
    }

    /**
     * Click on a tab
     *
     * @param tabName tab label
     */
    public void clickOnTab(String tabName) {
        try {
            WebElement desiredTab = Browser.getWebDriver().findElement(By.xpath("//button[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + tabName.toLowerCase() + "']"));
            clickOnElement(desiredTab);
            stepPassed(getCurrentMethodName() + "Successfully clicked on '" + tabName + "' tab");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on '" + tabName + "' tab" + e.getMessage());
        }
    }

    /**
     * Click on a specific tab menu item
     *
     * @param tabName button label
     * @param tabMenuName tab menu name label
     */
    public void clickOnSpecificTabMenu(String tabName, String tabMenuName) {
        try {
            WebElement desiredTab = Browser.getWebDriver().findElement(By.xpath("//button[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + tabName.toLowerCase() + "']"));
            clickOnElement(desiredTab);
            WebElement desiredTabMenu = Browser.getWebDriver().findElement(By.xpath("//ul[@role='menu']/div//li/p[text()='"+tabMenuName+"']"));
            clickOnElement(desiredTabMenu);
            stepPassed(getCurrentMethodName() + "Successfully clicked on '" + tabMenuName + "' tab");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on '" + tabMenuName + "' tab" + e.getMessage());
        }
    }

    /**
     * Set Rows Per Page
     *
     * @param rowsPerPage value
     */
    public void setRowsPerPage(String rowsPerPage) {
        try {
            WebElement rowsPerPageDropdownLocator = Browser.getWebDriver().findElement(By.xpath("//p[text()='Rows per page:']/following-sibling::div/div[@role='button']"));
            clickOnElement(rowsPerPageDropdownLocator);
            WebElement rowsValue = Browser.getWebDriver().findElement(By.xpath("//ul[@role='listbox']//li[text()='" + rowsPerPage + "']"));
            clickOnElement(rowsValue);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + rowsPerPage + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on" + rowsPerPage + "element" + e.getMessage());
        }
    }

    /**
     * Navigate Previous and Next Pages
     *
     * @param isPreviousPage value
     * @param pageCount      value
     */
    public void navigateToPreviousNextPage(boolean isPreviousPage, int pageCount) {
        String buttonTitle = "Go to next page";
        try {
            if (isPreviousPage) {
                buttonTitle = "Go to previous page";
            }
            WebElement previousNextPageButtonLocator = Browser.getWebDriver().findElement(By.xpath("//div[@class='MuiTablePagination-actions']/button[@title='" + buttonTitle + "']"));
            for (int i = 0; i < pageCount; i++) {
                clickOnElement(previousNextPageButtonLocator);
            }
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + buttonTitle + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on" + buttonTitle + "element" + e.getMessage());
        }
    }

    /**
     * Is expected button enabled
     *
     * @param buttonName button name
     * @param waitTimeOut
     * @return True/False
     */
    public boolean isExpectedButtonEnabled(String buttonName, int waitTimeOut) {
        boolean status = true;
        try {
            By buttonLocator = By.xpath("//ul[@role='menu']//p[text()='"+buttonName+"']/..");
            status = isExpectedElementClickable(buttonLocator, waitTimeOut);
            if(status){
                stepPassed("Expected button element is enabled.");
            }else {
                stepFailed("Expected button element is not enabled");
            }
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("waiting for enabled element to be located")) {
                stepFailed(getCurrentMethodName() + "Expected element is not enabled. " + e.getMessage());
            }
            status = false;
        }
        return status;
    }


}
