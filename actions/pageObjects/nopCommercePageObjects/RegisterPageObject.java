package nopCommercePageObjects;

import com.AbstractPage;
import nopCommercePageUI.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends AbstractPage {
    public WebDriver driver;
    public RegisterPageObject(WebDriver driver){
        this.driver=driver;
    }
    public void clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver,RegisterPageUI.REGISTER_BUTTON);
    }
    public boolean isSuccessMessageDisplay() {
        waitForElementVisible(driver,RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return isElementDisplayed(driver,RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }
}
