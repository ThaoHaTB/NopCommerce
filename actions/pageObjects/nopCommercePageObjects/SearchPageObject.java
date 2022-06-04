package nopCommercePageObjects;

import com.AbstractPage;
import org.openqa.selenium.WebDriver;

public class SearchPageObject extends AbstractPage {
    WebDriver driver;
    public SearchPageObject(WebDriver driver){
        this.driver=driver;
    }
}
