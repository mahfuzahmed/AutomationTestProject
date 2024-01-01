package pageobjects;

import browserutility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // [Start]: Buttons
    public static final String reserveADeskButton = "Reserve a Desk";
    public static final String yesContinueButton = "Yes, Continue";
    // [End]: Buttons

    /**
     * Get Username from Home Page
     *
     */
    public String getUserNameFromHomePage() {
        String
                userName = "";
        try {
            WebElement userNameText = Browser.getWebDriver().findElement(By.xpath("//div[@class='MuiBox-root css-q084sr']/p[contains(text(),'Welcome')]"));
            String userNameWholeText = getText(userNameText);
            String[] splitString = userNameWholeText.split(", ");
            String name = splitString[1];
            userName = name.replaceAll("!", "");
            stepPassed(getCurrentMethodName() + "Successfully retrieved username text element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to get text from username element" + e.getMessage());
        }
        return userName;
    }

    /**
     * Cancel specific reservation by date
     *
     * @param date date value of reservation
     */
    public void cancelSpecificReservation(String date){

        String[] parts = date.split(" ");
        String dateMonth = parts[0];
        String dateDate = parts[1];
        try {
            WebElement reservationCancelButton = Browser.getWebDriver().findElement(By.xpath("//p[text()='"+dateMonth+"']/following-sibling::p[text()='"+dateDate+"']/../../../..//button"));
            clickOnElement(reservationCancelButton);
            clickOnButton(yesContinueButton);
            stepPassed(getCurrentMethodName() + "Successfully clicked on cancel button for specific reservation on homepage");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Could not click on cancel button for specific reservation on homepage" + e.getMessage());
        }

    }

}