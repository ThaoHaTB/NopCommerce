package nopCommercePageObjects;

import com.AbstractPage;
import nopCommercePageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends AbstractPage {
    public WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver=driver;
    }
    public boolean isSliderDisplay() {
        waitForElementVisible(driver, HomePageUI.HOMEPAGE_SLIDE);
        return isElementDisplayed(driver,HomePageUI.HOMEPAGE_SLIDE);
    }
    public void clickToRegisterLink() {
        waitForElementClickable(driver,HomePageUI.REGISTER_LINK);
        clickToElement(driver,HomePageUI.REGISTER_LINK);
    }

}
