package pages;

import browserutility.Browser;
import pageobjects.*;
import org.openqa.selenium.support.PageFactory;

public final class PagesFactory {

    private static <T> T GetPage(Class<T> className) {
        return PageFactory.initElements(Browser.getWebDriver(), className);
    }

    public static LoginPage getLoginPage() {return GetPage(LoginPage.class);}

    public static HomePage getHomePage() {return GetPage(HomePage.class);
    }

    public static BasePage getBasePage() {
        return GetPage(BasePage.class);
    }

    public static ReservationPage getReservationPage() {
        return GetPage(ReservationPage.class);
    }
}
