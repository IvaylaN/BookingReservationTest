package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class FilterPage extends BasePage {

    @FindBy(xpath = "//a[@data-testid=\"availability-cta-btn\"]")
    private List<WebElement> seeAvailabilityBtns;

    @FindBy(xpath = "//label[@for=\":rv:\"]//span[@class=\"c850687b9b\"]")
    private WebElement hotelCheckBox;

    @FindBy(xpath = "//span[contains(text(), \"Property Type\")]")
    private WebElement propertyType;

    public FilterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectOrderByLowestPrice() {
        WebElement expandOptions = driver.findElement(By.xpath("//button[@data-testid=\"sorters-dropdown-trigger\"]"));
        smallWait.until(ExpectedConditions.elementToBeClickable(expandOptions));
        clickElement(expandOptions);
        WebElement lowestPrice = (driver.findElement(By.xpath("//span[contains(text(), \"Price (lowest first)\")]")));
        mediumWait.until(ExpectedConditions.visibilityOf(lowestPrice));
        clickElement(lowestPrice);
    }

    public void selectPropertyType() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", propertyType);
            smallWait.until(ExpectedConditions.elementToBeClickable(hotelCheckBox));
            if (!hotelCheckBox.isSelected()) {
                hotelCheckBox.click();
                smallWait.until(ExpectedConditions.elementToBeSelected(hotelCheckBox));
            }
        } catch (Exception e) {

        }
    }

    public void newWindow() {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        if (windows.size() > 1) {
            driver.switchTo().window(windows.get(1));
        }
    }

    public void selectThirdHotel() {
        mediumWait.until(d -> seeAvailabilityBtns.size() >= 3);
        WebElement thirdHotelBtn = seeAvailabilityBtns.get(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thirdHotelBtn);
        smallWait.until(ExpectedConditions.elementToBeClickable(thirdHotelBtn));
        thirdHotelBtn.click();
        newWindow();
    }
}






