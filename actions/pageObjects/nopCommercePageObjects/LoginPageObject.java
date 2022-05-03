package nopCommercePageObjects;

import com.AbstractPage;
import nopCommercePageUI.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    public WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver=driver;
    }
    public boolean isValidEmailErrorMessageDisplay(String errorMessage){
        waitForElementVisible(driver, LoginPageUI.VALIDATE_ERROR);
        return getElementText(driver,LoginPageUI.VALIDATE_ERROR).trim().equals(errorMessage);
    }
}
