package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.HomePage;
import pageobjects.ReservationPage;
import pages.PagesFactory;

public class ReservationPageTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(ReservationPageTest.class);

    BasePage basePage;
    HomePage homePage;

    ReservationPage reservationPage;

    String startDatePickerLabel = "Start date Picker";
    String startDatePickerValue;
    String levelPickerLabel = "Level Picker";
    String levelPickerValue = "Level-03";
    String repeatPickerLabel = "Repeat Picker";
    String repeatPickerValue = "Does not repeat";
    String roomNumberValue = "305";
    String seatNumberValue = "01";

    @Test
    public void verifySeatCountInListView() throws Exception {
        LOGGER.info("Login");
        initializeTest("Validate Reserve a Desk", "This test will reserve a desk", "Sanity");
        homePage = PagesFactory.getHomePage();
        basePage = PagesFactory.getBasePage();
        reservationPage = PagesFactory.getReservationPage();
        LOGGER.info("Click on Reserve a desk");
        basePage.clickOnButton(HomePage.reserveADeskButton);
        homePage.implicitlyWait(5);

        startDatePickerValue = reservationPage.getCurrentDate();

        reservationPage.selectItemFromDropdownList(startDatePickerLabel, startDatePickerValue);
        reservationPage.selectItemFromDropdownList(levelPickerLabel, levelPickerValue);
        reservationPage.selectItemFromDropdownList(repeatPickerLabel, repeatPickerValue);

        reservationPage.clickOnButton("Search");
        reservationPage.waitWhileLoading(2);

        reservationPage.selectFloorViewListView(true);
        String seatCount = reservationPage.getAvailableSeatCount();
        reservationPage.assertEquals(seatCount, "14");
    }


    @Test
    public void verifyMakeSingleReservation() throws Exception {
        LOGGER.info("Started Validate Make A Single Reservation Test");
        initializeTest("Validate Make Single Reservation", "This test will verify making a reservation from the Reservation Page", "Sanity");
        reservationPage = PagesFactory.getReservationPage();
        reservationPage.waitWhileLoading(2);

        startDatePickerValue = reservationPage.getCurrentDate();

        reservationPage.selectItemFromDropdownList(startDatePickerLabel, startDatePickerValue);
        reservationPage.selectItemFromDropdownList(levelPickerLabel, levelPickerValue);
        reservationPage.selectItemFromDropdownList(repeatPickerLabel, repeatPickerValue);

        reservationPage.clickOnButton("Search");
        reservationPage.waitWhileLoading(2);
        reservationPage.selectRoomAndSeatNumber(roomNumberValue, seatNumberValue);
        reservationPage.waitWhileLoading(2);
        reservationPage.clickOnButton("Reserve");
        reservationPage.waitWhileLoading(2);
        reservationPage.clickOnButton("Ok");
        reservationPage.waitWhileLoading(2);
        LOGGER.info("Ended Validate Make A Single Reservation Test.");

    }

    @Test
    public void verifyCancelSingleReservation() throws Exception {
        LOGGER.info("Started Validate Cancel A Single Reservation Test");
        initializeTest("Validate Cancel Single Reservation", "This test will verify cancelling a reservation from the Reservation Page", "Sanity");
        reservationPage = PagesFactory.getReservationPage();
        reservationPage.waitWhileLoading(2);

        startDatePickerValue = reservationPage.getCurrentDate();
        reservationPage.selectItemFromDropdownList(startDatePickerLabel, startDatePickerValue);
        reservationPage.selectItemFromDropdownList(levelPickerLabel, levelPickerValue);
        reservationPage.selectItemFromDropdownList(repeatPickerLabel, repeatPickerValue);

        reservationPage.clickOnButton("Search");
        reservationPage.waitWhileLoading(2);
        reservationPage.selectRoomAndSeatNumber(roomNumberValue, seatNumberValue);
        reservationPage.waitWhileLoading(2);
        reservationPage.clickOnButton(HomePage.yesContinueButton);
        reservationPage.waitWhileLoading(2);
        LOGGER.info("Ended Validate Cancel A Single Reservation Test");
    }


}
