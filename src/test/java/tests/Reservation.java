package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class Reservation extends BaseTestMethods {

    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"2025-12-22", "2025-12-30"}};
    }

    @Test(dataProvider = "getData")
    public void searchFoRHotelAndSetAdditionalFilters(String checkInDate, String checkOutDate) {
        SearchPage searchPage = new SearchPage(driver);
        FilterPage filterPage = new FilterPage(driver);
        BasePage basePage = new BasePage(driver);

        System.out.println("1. Load Booking website");
        searchPage.navigateTo();
        searchPage.acceptCookiesIfPresent();
        basePage.closeDialog();

        System.out.println("2. Search for a hotel, set dates and search");
        searchPage.searchDestination();
        searchPage.acceptCookiesIfPresent();
        searchPage.setDatesAndSearch(checkInDate, checkOutDate);
        searchPage.chooseNumberOfGuests();

        System.out.println("2. Set additional filter");
        filterPage.selectOrderByLowestPrice();
        filterPage.selectPropertyType();
        }

    @Test (dependsOnMethods = "searchFoRHotelAndSetAdditionalFilters")
    public void selectHotelAndVerifyDetails(){
        HotelPage hotelPage = new HotelPage(driver);
        FilterPage filterPage = new FilterPage(driver);
        BasePage basePage = new BasePage(driver);

        System.out.println("1. Select the third hotel and its cheapest room");
        filterPage.selectThirdHotel();
        basePage.moveToNewWindow();
        //filterPage.selectCheapestRoom();
        hotelPage.selectReserveBtn();
        hotelPage.selectApartment();
        hotelPage.selectIWillReserveBtn();
    }

    @Test (dependsOnMethods = "selectHotelAndVerifyDetails")
    public void fillGuestInformationAndVerifuPayment(){
        ReservationPage reservationPage = new ReservationPage(driver);
        BasePage basePage = new BasePage(driver);

        basePage.moveToNewWindow();
        reservationPage.fillGuestDetails();
        reservationPage.fillPhoneNumber();
        reservationPage.verifyPaymentMethodWithCard();
    }
}

