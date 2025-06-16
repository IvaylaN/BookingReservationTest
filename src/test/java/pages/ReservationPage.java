package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ReservationPage extends BasePage{

    @FindBy(name = "firstname")
    protected WebElement firstName;

    @FindBy(name = "lastname")
    protected WebElement lastName;

    @FindBy(name = "email")
    protected WebElement email;

    @FindBy(css = "button[type='submit']")
    protected WebElement finalDetailsButton;

    @FindBy(name = "phoneNumber")
    protected WebElement phone;

    @FindBy(name = "//div[@class =\"payment-method-button__checkmark desktop\"]")
    protected WebElement newCardBox;

    public ReservationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillGuestDetails() {
        smallWait.until(d -> firstName.isDisplayed());
        enterText(firstName,"Ivan");
        enterText(lastName,"Ivanov");
        enterText(email,"testUser@example.com");
        finalDetailsButton.click();
    }

    public void fillPhoneNumber() {
        phone.sendKeys("888000111");
        //continueButton.click();
    }

    public void verifyPaymentMethodWithCard() {
        smallWait.until(ExpectedConditions.elementToBeSelected(newCardBox));
        if (!newCardBox.isSelected()) {
            clickElement(newCardBox);
        }
    }
}
