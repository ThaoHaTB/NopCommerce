package nopCommerceTestcases;

import com.BaseTest;
import nopCommercePageObjects.HomePageObject;
import nopCommercePageObjects.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        log.info("Precondition-Step 01: Open url");
        driver = getBrowser(browser, url);
        log.info("Precondition-Step 02: Verify Home page is displayed");
        homePage=new HomePageObject(driver);
        Assert.assertTrue(homePage.isSliderDisplay());
    }

    @Test
    public void TC_01_Search_with_empty_data(){
       log.info("Step 01: Click on Search button");
       homePage.clickOnDynamicButton(driver,"Search");
       verifyTrue(homePage.getAlertText(driver).equals("Please enter some search keyword"));
    }
}
