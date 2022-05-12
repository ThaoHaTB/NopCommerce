package nopCommerceTestcases;

import com.BaseTest;
import com.GlobalConstants;
import nopCommercePageObjects.HomePageObject;
import nopCommercePageObjects.LoginPageObject;
import nopCommercePageObjects.MyAccountPage;
import nopCommercePageObjects.PageGeneratorManager;
import nopCommercePageUI.AbstractPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccount extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    MyAccountPage myAccountPage;
    LoginPageObject loginPage;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        log.info("Precondition-Step 01: Open url");
        driver = getBrowser(browser, url);
        log.info("Precondition-Step 02: Verify Home page is displayed");
        homePage=new HomePageObject(driver);

        log.info("Precondition- Step 03: Go to Login Page");
        homePage.clickOnDymanicMenuLink(driver,"Log in");

        log.info("Precondition- Step 04: Login");
        loginPage=new LoginPageObject(driver);
        Login login=new Login();
        login.TC_06_Login_Successfully();

        log.info("Precondition- Step 05: Go to My Account Page");
        homePage.clickOnDymanicMenuLink(driver,"My account");
        myAccountPage=new MyAccountPage(driver);
    }

    @Test
    public void TC01_Update_Customer_Info(){
        log.info("Step 01: Update Gender");
        myAccountPage.clickOnDynamicRadioButton(driver,"Male");

        log.info("Step 02: Input to First name textbox");
        myAccountPage.sendKeyToDynamicTextbox(driver,"FirstName","Mai");

        log.info("Step 03: Input to Last name textbox");
        myAccountPage.sendKeyToDynamicTextbox(driver, "LastName","Le");

        log.info("Step 04: Input to Email textbox");
        myAccountPage.sendKeyToDynamicTextbox(driver,"Email","tester@gmail.com");

        log.info("Step 05: Verify First name is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"FirstName").equals("Mai"));

        log.info("Step 06: Verify Last name is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"LastName").equals("Le"));

        log.info("Step 07: Verify Email  is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"Email").equals("tester@gmail.com"));
    }
}
