package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.ReservationPage;
import pages.SearchPage;

public class Reservation extends BaseTestMethods {

    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{{"2025-12-22", "2025-12-30"}};
    }

    @Test(dataProvider = "getData")
    public void searchFoRHotelAndSetAdditionalFilters(String checkInDate, String checkOutDate) {
        SearchPage searchPage = new SearchPage(driver);
        FilterPage filterPage = new FilterPage(driver);

        System.out.println("1. Load Booking website");
        searchPage.navigateTo();
        searchPage.acceptCookiesIfPresent();

        System.out.println("2. Search for a hotel, set dates and search");
        searchPage.searchDestination();
        searchPage.acceptCookiesIfPresent();
        searchPage.setDatesAndSearch(checkInDate, checkOutDate);
        searchPage.chooseNumberOfGuests();

        System.out.println("2. Set additional filter");
        searchPage.closePopUp();
      /*  filterPage.applyFilters();
        filterPage.selectThirdHotel();
        filterPage.selectCheapestRoom();*/











        }

    @Test (dependsOnMethods = "searchFoRHotelAndSetAdditionalFilters")
    public void selectHotelAndVerifyDetails(){




    }


    @Test (dependsOnMethods = "selectHotelAndVerifyDetails")
    public void fillGuestInformationAndVerifuPayment(){
        ReservationPage reservationPage = new ReservationPage(driver);

        reservationPage.fillGuestDetails();
        reservationPage.fillPhoneNumber();
        reservationPage.verifyPaymentMethodWithCard();
    }


}

