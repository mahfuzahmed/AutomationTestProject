package pageobjects;

import browserutility.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReservationPage extends BasePage {

    @FindBy(xpath = "(//*[local-name()='svg' and @focusable='false' and @data-testid='CalendarIcon'])[1]/..")
    WebElement startDatePicker;

    @FindBy(xpath = "//*[local-name()='svg' and @focusable='false' and @data-testid='ArrowDropDownIcon']/../..")
    WebElement levelPicker;

    @FindBy(xpath = "(//*[local-name()='svg' and @focusable='false' and @data-testid='ArrowDropDownIcon'])[2]/../div")
    WebElement repeatPicker;

    @FindBy(xpath = "(//*[local-name()='svg' and @focusable='false' and @data-testid='CalendarIcon'])[2]")
    WebElement repeatEndDatePicker;

    @FindBy(xpath = "(//*[local-name()='svg' and @focusable='false' and @data-testid='CalendarIcon'])[3]/..")
    WebElement repeatEndsOnDatePicker;


    /**
     * Select item from drop down list
     *
     * @param dropDownName dropdown name value
     * @param dropDownItem item to be selected from the dropdown
     */
    public void selectItemFromDropdownList(String dropDownName, String dropDownItem) {

        if (dropDownName.equalsIgnoreCase("Start Date Picker")) {
            clickOnElement(startDatePicker);
            datePicker(dropDownItem);
        } else if (dropDownName.equalsIgnoreCase("Level Picker")) {
            clickOnElement(levelPicker);
            implicitlyWait(2);
            WebElement levelItem = Browser.getWebDriver().findElement(By.xpath("//ul[@role='listbox']/li[text()='" + dropDownItem + "']"));
            clickOnElement(levelItem);
        } else if (dropDownName.equalsIgnoreCase("Repeat Picker")) {
            clickOnElement(repeatPicker);
            implicitlyWait(2);
            WebElement repeatItem = Browser.getWebDriver().findElement(By.xpath("//ul[@role='listbox']/li[text()='" + dropDownItem + "']"));
            clickOnElement(repeatItem);
        } else if (dropDownName.equalsIgnoreCase("Repeat End Date Picker")) {
            clickOnElement(repeatEndDatePicker);
            datePicker(dropDownItem);
        }else if(dropDownName.equalsIgnoreCase("Custom Recurrence Ends On")){
            clickOnElement(repeatEndsOnDatePicker);
            datePicker(dropDownItem);
        }

    }

    /**
     * Pick given dte item
     *
     * @param date drop down attribute value
     */
    public void datePicker(String date) {
        String[] parts = date.split(" ");
        String dateDate = parts[0];
        String dateMonth = parts[1];
        String dateYear = parts[2];

        WebElement yearDropdownLocatorBrowser = Browser.getWebDriver().findElement(By.xpath("(//*[local-name()='svg' and @focusable='false' and @data-testid='ArrowDropDownIcon'])[3]/.."));
        clickOnElement(yearDropdownLocatorBrowser);

        WebElement yearLocator = Browser.getWebDriver().findElement(By.xpath("//button[text()='" + dateYear + "']"));
        clickOnElement(yearLocator);

        WebElement nextMonthArrowLocator = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @focusable='false' and @data-testid='ArrowRightIcon']/.."));
        for (int i = 0; ; i++) {

            WebElement calendarMonthLocator = Browser.getWebDriver().findElement(By.xpath("//div[contains(@id, ':-grid-label')]"));
            String calendarMonth = getText(calendarMonthLocator);

            if (!calendarMonth.equalsIgnoreCase(dateMonth + " " + dateYear)) {
                clickOnElement(nextMonthArrowLocator);
            } else {
                break;
            }
        }

        WebElement dateLocator = Browser.getWebDriver().findElement(By.xpath("//button[@role='gridcell' and text()='" + dateDate + "']"));
        clickOnElement(dateLocator);
    }

    /**
     * Click on desired seat number using room and seat number
     *
     * @param roomNumber room number
     * @param seatNumber seat number
     */
    public void selectRoomAndSeatNumber(String roomNumber, String seatNumber) {
        try {
            WebElement roomSeatNumber = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='g' and @data-desk-number='" + roomNumber + seatNumber + "']"));
            clickOnElement(roomSeatNumber);
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on" + roomNumber + seatNumber + "element" + e.getMessage());
        }

    }

    /**
     * Click on Floor View or List View
     *
     * @param isListView True/False
     */
    public void selectFloorViewListView(boolean isListView) {
        try {
            WebElement buttonLocator;
            if (!isListView) {
                buttonLocator = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @focusable='false' and @data-testid='MapOutlinedIcon']/.."));
            } else {
                buttonLocator = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @focusable='false' and @data-testid='FormatListBulletedIcon']/.."));
            }
            clickOnElement(buttonLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + buttonLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Click on Filter By Room Dropdown
     */
    public void clickOnFilterByRoom() {
        try {
            WebElement filterLocator = Browser.getWebDriver().findElement(By.xpath("//label[@id='filter-label']/following-sibling::div"));
            clickOnElement(filterLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + filterLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Select Room Number in Filter By Room Dropdown
     *
     * @param roomNumber room number
     */
    public void selectRoomInFilterByRoom(String roomNumber) {
        try {
            WebElement roomNumberCheckboxLocator = Browser.getWebDriver().findElement(By.xpath("//ul[@role='listbox']//span[text()='" + roomNumber + "']/../preceding-sibling::span/input"));
            clickOnElement(roomNumberCheckboxLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + roomNumberCheckboxLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Get Available Seat Count Text
     */
    public String getAvailableSeatCount() {
        String seatCountText = "";
        String numberString = "";
        try {
            WebElement availableSeatCount = Browser.getWebDriver().findElement(By.xpath("//div[@class='MuiBox-root css-pori7h']//p"));
            seatCountText = getText(availableSeatCount);
            String[] parts = seatCountText.split(":");
            numberString = parts[1].trim();
            stepPassed(getCurrentMethodName() + "Successfully fetched text from" + availableSeatCount + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to get text from element" + e.getMessage());
        }
        return numberString;
    }

    /**
     * Click on Reserve Button by room seat number
     *
     * @param roomNumber room number
     * @param seatNumber seat number
     */
    public void clickOnReserveButtonByRoomSeatNumber(String roomNumber, String seatNumber) {
        try {
            WebElement roomSeatNumberReserveButtonLocator = Browser.getWebDriver().findElement(By.xpath("//p[text()='" + roomNumber + "-" + seatNumber + "']/../../..//button[text()='Reserve']"));
            clickOnElement(roomSeatNumberReserveButtonLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + roomSeatNumberReserveButtonLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Get custom recurrence modal title
     */
    public void getCustomRecurrenceModalTitle() {
        try {
            WebElement customRecurrenceModalTitle = Browser.getWebDriver().findElement(By.xpath("//h2//p"));
            String customRecurrenceModalTitleText = getText(customRecurrenceModalTitle);
            stepPassed(getCurrentMethodName() + "Successfully fetched text from" + customRecurrenceModalTitleText + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to get text from element" + e.getMessage());
        }
    }

    /**
     * Click on Repeat Week Count Increase/Decrease button
     *
     * @param numberOfWeeks number of weeks to repeat
     * @param isDecrease    True/False
     */
    public void repeatWeekCountDecreaseIncrease(int numberOfWeeks, boolean isDecrease) {
        try {
            WebElement increaseDecreaseButton;
            if (isDecrease) {
                increaseDecreaseButton = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @data-testid='RemoveIcon']/.."));
            } else {
                increaseDecreaseButton = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @data-testid='AddIcon']/.."));
            }
            int intWeekCountValue = 0;
            while (numberOfWeeks != intWeekCountValue) {
                WebElement weekCount = Browser.getWebDriver().findElement(By.xpath("//div[@class='MuiBox-root css-1vc8sxq']/p"));
                String weekCountValueText = getText(weekCount);
                intWeekCountValue = Integer.parseInt(weekCountValueText);
                clickOnElement(increaseDecreaseButton);
            }
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + increaseDecreaseButton + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }

    }

    /**
     * Select weekday to repeat reservation
     *
     * @param weekday day of week to select
     */
    public void repeatOnWeekdaySelector(String weekday, boolean unselect) {
        try {
            String
                    className = "MuiBox-root css-pc0xb1",
                    day = "M",
                    i = "1";

            if (unselect){className = "MuiBox-root css-1fqsgwn";}

            if (weekday.equalsIgnoreCase("Tuesday")) {
                day = "T";
            } else if (weekday.equalsIgnoreCase("Wednesday")) {
                day = "W";
            } else if (weekday.equalsIgnoreCase("Thursday")) {
                day = "T";
                i="2";
            } else if (weekday.equalsIgnoreCase("Friday")) {
                day = "F";
            } else if (weekday.equalsIgnoreCase("Saturday")) {
                day = "S";
            } else if (weekday.equalsIgnoreCase("Sunday")) {
                day = "S";
                i="2";
            }
            WebElement weekdayLocator = Browser.getWebDriver().findElement(By.xpath("(//div[@class='" + className + "']/p[text()='"+day+"'])["+i+"]"));
            clickOnElement(weekdayLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + weekdayLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Close custom recurrence modal
     */
    public void closeCustomRecurrenceModal() {
        try {
            WebElement closeCustomRecurrenceModuleButton = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @data-testid='ClearIcon']/.."));
            clickOnElement(closeCustomRecurrenceModuleButton);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + closeCustomRecurrenceModuleButton + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Click on Zoom Buttons by Value
     */
    public void clickOnZoomButtons(String buttonValue) {
        try {
            WebElement zoomButtonLocators = Browser.getWebDriver().findElement(By.xpath("//button[@value='"+buttonValue+"']"));
            clickOnElement(zoomButtonLocators);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + zoomButtonLocators + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }

    /**
     * Set Zoom to specific room
     * @param roomValue day of week to select
     */
    public void setZoomToSpecificRoom(String roomValue) {
        try {
            WebElement zoomSearchButtonLocator = Browser.getWebDriver().findElement(By.xpath("//*[local-name()='svg' and @data-testid='SearchIcon']/../.."));
            clickOnElement(zoomSearchButtonLocator);
            WebElement roomLocator = Browser.getWebDriver().findElement(By.xpath("//ul[@role='menu']//p[text()='"+roomValue+"']/.."));
            clickOnElement(roomLocator);
            stepPassed(getCurrentMethodName() + "Successfully clicked on" + roomLocator + "element");
        } catch (Exception e) {
            stepFailed(getCurrentMethodName() + "Unable to click on element" + e.getMessage());
        }
    }
}
