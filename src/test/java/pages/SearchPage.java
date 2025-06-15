package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;

public class SearchPage extends BasePage {

    private final String URL = "https://www.booking.com";

    @FindBy(xpath = "//input[@id=\":rh:\" or @placeholder=\"Where are you going?\"]")
    WebElement destinationSearchField;

    @FindBy(xpath = "//div[@data-testid=\"autocomplete-results-options\"]")
    private WebElement optionsTable;

    @FindBy(xpath = "//div[@data-testid=\"occupancy-popup\"]")
    private WebElement occupancyPopUp;

    @FindBy(xpath = "//h3[@aria-live='polite'][1]")
    private List<WebElement> calendarMonthTitles;

    @FindBy(xpath = "//span[@class=\"e32aa465fd\"][1]")
    private List<WebElement> occupancyData;

    @FindBy(xpath = "//button[@type='submit']//span[text()='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//button[@data-testid=\"occupancy-config\"]")
    private WebElement occupancyField;

    @FindBy(xpath = "//button[@aria-label=\"Next month\"]")
    WebElement calendarNextBtn;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCalendarMonthTitleTxt() {
        return calendarMonthTitles.get(0).getText();
    }

    public WebElement getCheckInDate(String date) {
        return driver.findElement(By.xpath("//span[@data-date='" + date + "']"));
    }

    public WebElement getCheckOutDate(String date) {
        return driver.findElement(By.xpath("//span[@data-date='" + date + "']"));
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public void setDatesAndSearch( String checkInDate, String checkOutDate){
        //dateField.click();
        while (!getCalendarMonthTitleTxt().contains("December 2025")) {
            calendarNextBtn.click();
        }
        // Select check-in and check-out dates
        WebElement checkIn = getCheckInDate(checkInDate);
        smallWait.until(ExpectedConditions.elementToBeClickable(checkIn));
        Actions actions = new Actions(driver);
        actions.doubleClick(checkIn).perform();

        WebElement checkOut = getCheckOutDate(checkOutDate);
        smallWait.until(ExpectedConditions.elementToBeClickable(checkOut)).click();

        // Click the Search button
        WebElement searchButton = getSearchBtn();
        smallWait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public WebElement getAdultCount(){
        return occupancyData.get(0);
    }

    public void chooseNumberOfGuests() {
        occupancyField.click();
        mediumWait.until(ExpectedConditions.visibilityOf(occupancyPopUp));
        int adultCount = Integer.parseInt(String.valueOf(getAdultCount()));
        if(adultCount == 2){
            clickSearch();
        }
    }

    public void clickSearch() {
        searchBtn.click();
    }

    public void searchDestination() {
        destinationSearchField.clear();
        Actions actions = new Actions(driver);
        actions.doubleClick(destinationSearchField).perform();
        if (!optionsTable.isDisplayed()){
            destinationSearchField.click();
        }
        mediumWait.until(ExpectedConditions.visibilityOf(optionsTable));

        selectCityByValue();
        //getCurrentCity("Sofia, Bulgaria");
    }

    public void selectCityByValue(){
        WebElement  citySofia = (driver.findElement(By.xpath("//div[@data-testid = \"autocomplete-result\"]//div[contains(text(), \"Sofia\")]")));
        mediumWait.until(ExpectedConditions.visibilityOf(citySofia));
        clickElement(citySofia);
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptBtn = mediumWait.until(ExpectedConditions.elementToBeClickable(
                    By.id("onetrust-accept-btn-handler")));
            acceptBtn.click();
            mediumWait.until(ExpectedConditions.invisibilityOf(acceptBtn));
        } catch (TimeoutException e) {
        }
    }

    public String getSearchFieldCity(){
        waitForVisibility(destinationSearchField);
        return destinationSearchField.getText();
    }
    public void getCurrentCity(String text) {
        Assert.assertTrue(getSearchFieldCity().contains("Sofia, Bulgaria"));
    }
    public void navigateTo(){
        driver.get(URL);
    }
    public void closePopUp() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

    }
}

