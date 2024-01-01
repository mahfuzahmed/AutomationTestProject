package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pages.PagesFactory;

import java.util.Arrays;
import java.util.List;

public class HomePageTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(HomePageTest.class);
    HomePage homePage;
    BasePage basePage;
    LoginPage loginPage;

    /**
     * Verify proper username was displayed in the home page
     */
    @Test
    public void verifyUsernameDisplayedInHomePage() throws Exception {
        LOGGER.info("[Start]: verifyUsernameDisplayedInHomePage");
        initializeTest("Verify Username Is Displayed in Home Page", "Verify Username Is Displayed in Home Page", "Sanity");

        basePage = PagesFactory.getBasePage();
        loginPage = PagesFactory.getLoginPage();
        homePage = PagesFactory.getHomePage();
        homePage.implicitlyWait(5);
        String userNameInHomePage = homePage.getUserNameFromHomePage();
        String userNameInUserIcon = loginPage.getUserNameEmailUserTypeFromUserIcon(false, false);
        homePage.assertEquals(userNameInHomePage, userNameInUserIcon);

        LOGGER.info("[End]: verifyUsernameDisplayedInHomePage");
    }

    /**
     * Verify if user can go to create reservation page by clicking 'Reserve A Desk' Button
     */
    @Test
    public void verifyCreateReservationDisplayedByClickingReserveADeskButton() throws Exception {
        LOGGER.info("");
        initializeTest("", "", "Sanity");

        homePage = PagesFactory.getHomePage();
        basePage = PagesFactory.getBasePage();
        basePage.clickOnButton(HomePage.reserveADeskButton);
        homePage.implicitlyWait(5);

        LOGGER.info("");
    }
}
