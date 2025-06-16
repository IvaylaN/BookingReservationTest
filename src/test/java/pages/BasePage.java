package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;


public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
        smallWait = new WebDriverWait(driver, 3);
        mediumWait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@aria-label=\\\"Window offering discounts of 10% or more when you sign in to Booking.com\\\"]")
    WebElement confirmDialog;

    protected static WebDriver driver;
    protected WebDriverWait smallWait;
    protected WebDriverWait mediumWait;

    protected void clickElement(WebElement element){
        smallWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        smallWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void checkURL(String url) {
        mediumWait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitForVisibility(WebElement element){
        smallWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void moveToNewWindow(){
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondwindow = windows.get(1);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(secondwindow, currentURL, "New window URL is not correct.");
    }

    public void closeDialog(){
        try {
            smallWait.until(ExpectedConditions.visibilityOf(confirmDialog));
            WebElement dismissButton = driver.findElement(By.xpath("//button[@aria-label='Dismiss sign-in info.']"));
            smallWait.until(ExpectedConditions.elementToBeClickable(dismissButton));
            dismissButton.click();
        } catch (Exception e) {
        }
    }


}
