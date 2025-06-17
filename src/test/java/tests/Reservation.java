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

        System.out.println("Load Booking website");
        searchPage.navigateTo();
        searchPage.acceptCookiesIfPresent();
        basePage.closeDialog();

        System.out.println("Search for destination, set dates and search");
        searchPage.searchDestination();

        System.out.println("Set check-in and check-out dates");
        searchPage.setDatesAndSearch(checkInDate, checkOutDate);

        System.out.println("Make sure that number of guest are set to 2");
        searchPage.verifyNumberOfGuests();

        System.out.println("Select filters for the search results - lowest price");
        filterPage.selectOrderByLowestPrice();

        System.out.println("Select filters for the search results - property type");
        filterPage.selectPropertyType();
        }

    @Test (dependsOnMethods = "searchFoRHotelAndSetAdditionalFilters")
    public void selectHotelAndVerifyDetails(){
        HotelPage hotelPage = new HotelPage(driver);
        FilterPage filterPage = new FilterPage(driver);
        BasePage basePage = new BasePage(driver);

        System.out.println("Select the third hotel and its cheapest room");
        //filterPage.selectThirdHotel();
        //filterPage.selectCheapestRoom();

        System.out.println("Reserve the room and verify details");
        hotelPage.selectReserveBtn();
        hotelPage.selectApartment();
        hotelPage.selectIWillReserveBtn();
    }

    @Test (dependsOnMethods = "selectHotelAndVerifyDetails")
    public void fillGuestInformationAndVerifuPayment(){
        ReservationPage reservationPage = new ReservationPage(driver);
        BasePage basePage = new BasePage(driver);

        System.out.println("Fill guest details");

        reservationPage.fillGuestDetails();
        reservationPage.fillPhoneNumber();

        System.out.println("Verify payment method with card");
        reservationPage.verifyPaymentMethodWithCard();
    }
}

