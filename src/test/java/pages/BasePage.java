package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        mediumWait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    protected WebDriver driver;
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
}
