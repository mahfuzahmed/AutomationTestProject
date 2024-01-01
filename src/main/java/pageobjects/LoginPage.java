package pageobjects;

import browserutility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Start: Home page locators
    By titleLocator = By.xpath("//a[text()='Desk Reservation System']");
    // End: Home page locators

    // Start: Login page locators
    By signInWithGoogleButtonLocator = By.xpath("//span[@class='nsm7Bb-HzV7m-LgbsSe-BPrWId' and text()='Sign in with Google']/../..");
    // End: Login page locators

    /**
     * Check proper application title was displayed
     *
     * @return application title visibility status
     */
    public boolean isApplicationTitleVisible() {
        boolean status = false;
        try {
            status = isExpectedElementVisible(titleLocator, 3);
            if (status) {
                informationLog("Logo is visible.");
            } else {
                informationLog("Logo is not visible.");
            }
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Logo state could not be retrieved. " + e.getMessage());
        }
        return status;
    }

    /**
     * Check if sign in with Google button is displayed
     *
     * @return sign in with Google button visibility status
     */
    public boolean isSignInWithGoogleButtonVisible() {
        boolean status = false;
        try {
            status = isExpectedElementVisible(signInWithGoogleButtonLocator, 3);
            stepPassed(getCurrentMethodName() + "Sign in with Google button is found visible");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Sign in with Google button state could not be retrieved. " + e.getMessage());
        }
        return status;
    }

    /**
     * Logout from application
     */
    public void logOutFromApplication() {
        try {
            WebElement userProfileButton = Browser.getWebDriver().findElement(By.xpath("//img[@class='MuiAvatar-img css-1hy9t21']/../.."));
            clickOnElement(userProfileButton);

            WebElement signOutButton = Browser.getWebDriver().findElement(By.xpath("//ul[@role='menu']//p[text()='Sign out']/.."));
            clickOnElement(signOutButton);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + signOutButton + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Get Username from user icon
     *
     * @param isUserType boolean condition for the return value the User Type
     */
    public String getUserNameEmailUserTypeFromUserIcon(boolean isUserType, boolean isUserEmail) {
        String
                value = "",
                userEmailField = "1",
                classValue = "MuiBox-root css-19fzyh2";
        if(isUserType){
            classValue = "MuiBox-root css-17bl89u";
        }
        if(isUserEmail)
        {
            userEmailField = "2";
        }
        try {
            WebElement userProfileButton = Browser.getWebDriver().findElement(By.xpath("//img[@class='MuiAvatar-img css-1hy9t21']/../.."));
            clickOnElement(userProfileButton);
            WebElement textElement = Browser.getWebDriver().findElement(By.xpath("//ul[@role='menu']//div[@class='"+classValue+"']/p["+userEmailField+"]"));
            value = getText(textElement);
            stepPassed(getCurrentMethodName() + "Successfully retrieved text element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to get text from element" + e.getMessage());
        }
        return value;
    }

}