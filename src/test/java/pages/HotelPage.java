package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HotelPage extends BasePage {

    @FindBy(xpath = "//button[@class=\"txp-sidebar-cta bui-button bui-button--primary  call_to_action--wide px--fw-cta js-ph__cta\" or @aria-description=\"Reserve this apartment now\"]")
    private static WebElement buttonReserve;

    @FindBy(xpath = "//button[@class=\"txp-bui-main-pp bui-button bui-button--primary  hp_rt_input px--fw-cta js-reservation-button\" and @data-popover-content-id=\"submit_cta_holder_button_tooltip\"]")
    private static WebElement buttonIWillReserve;


    public HotelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectReserveBtn() {
        buttonReserve.click();
    }

    public void selectApartment() {
        Select dropDown = new Select(driver.findElement(By.cssSelector(".hprt-nos-select js-hprt-nos-select")));
        dropDown.selectByValue("1");
    }

    public void selectIWillReserveBtn() {
        buttonIWillReserve.click();
    }

}
