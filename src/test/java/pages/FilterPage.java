package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class FilterPage extends BasePage{

    @FindBy(css = "//div[@data-testid =\"property-card\"]")
    private List<WebElement> hotelList;

    @FindBy(xpath = "//div[@data-filters-item=\"ht_id:ht_id=203\"]//*[@for=\":r4o:\"]//span[@class=\"c850687b9b\"]")
    private WebElement hotelCheckBox;

    @FindBy(xpath = "//div[@data-testid =\"property-card\"][3]//a[@data-testid=\"availability-cta-btn\"]")
    private WebElement seeAvalabilityBtnOfThirdHotel;

    public FilterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectOrderByLowestPrice(){
        WebElement  lowestPrice = (driver.findElement(By.xpath("//span[contains(text(), \"Price (highest first)\")]")));
        mediumWait.until(ExpectedConditions.visibilityOf(lowestPrice));
        clickElement(lowestPrice);
    }

    public void selectPropertyType() {
        smallWait.until(ExpectedConditions.elementToBeSelected(hotelCheckBox));
        if (!hotelCheckBox.isSelected()) {
            clickElement(hotelCheckBox);
        }
    }

    public void selectThirdHotel() {
        mediumWait.until(d -> hotelList.size() >= 3);
        WebElement thirdHotel = hotelList.get(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thirdHotel);
        thirdHotel.click();
        seeAvalabilityBtnOfThirdHotel.click();
    }

   /* public void selectCheapestRoom() {
        double minPrice = Double.MAX_VALUE;
        WebElement selected = null;

        for (WebElement price : priceElements) {
            try {
                String text = price.getText().replaceAll("[^\\d]", "");
                double value = Double.parseDouble(text);
                if (value <= minPrice) {
                    minPrice = value;
                    selected = price;
                }
            } catch (Exception ignored) {}
        }

        if (selected != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selected);
            WebElement reserveBtn = selected.findElement(By.xpath(""));
            reserveBtn.click();
        }
    }*/




    }




