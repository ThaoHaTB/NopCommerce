package nopCommercePageObjects;

import com.AbstractPage;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends AbstractPage {
    WebDriver driver;
    public MyAccountPage(WebDriver driver) {
        this.driver=driver;
    }
}
