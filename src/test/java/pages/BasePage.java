package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
        smallWait = new WebDriverWait(driver, 3);
        mediumWait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@aria-label=\\\"Window offering discounts of 10% or more when you sign in to Booking.com\\\"]")
    WebElement confirmDialog;

    @FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
    WebElement dismissButton;

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

    public void waitForVisibility(WebElement element){
        smallWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void closeDialog(){
        try {
            smallWait.until(ExpectedConditions.elementToBeClickable(dismissButton));
            clickElement(dismissButton);
            mediumWait.until(ExpectedConditions.invisibilityOf(confirmDialog));
        } catch (Exception e) {

        }
        }
    }



