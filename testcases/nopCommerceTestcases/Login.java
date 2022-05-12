package nopCommerceTestcases;

import com.BaseTest;
import com.GlobalConstants;
import nopCommercePageObjects.HomePageObject;
import nopCommercePageObjects.LoginPageObject;
import nopCommercePageObjects.PageGeneratorManager;
import nopCommercePageObjects.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login extends BaseTest {
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

        log.info("Precondition- Step 03: Go to Login Page");
        homePage.clickOnDymanicMenuLink(driver,"Log in");
        loginPage=new LoginPageObject(driver);
    }
    @Test
    public void TC_01_Login_With_Empty_Data(){
        log.info("Step 01: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 02: Verify error mesage is displayed");
        loginPage.isErrorMessageDisplay(driver,"Email-error","Please enter your email");
    }
    @Test
    public void TC_02_Login_With_Invalid_Email(){
        log.info("Step 01: Input invalid email to Email text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Email","aaaa");

        log.info("Step 02: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 03: Verify error message is displayed");
        verifyTrue(loginPage.isErrorMessageDisplay(driver,"Email-error","Wrong email"));
    }

    @Test
    public void TC_03_Login_With_Not_Register_Email(){
        log.info("Step 01: Input email to Email text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Email","123cntt@gmail.com");

        log.info("Step 02: Input password to Password text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Password","2334245");

        log.info("Step 03: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 04: Verify error message display");
        verifyTrue(loginPage.isValidEmailErrorMessageDisplay("Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found"));
    }

    @Test
    public  void TC_04_Login_With_Correct_Email_And_Empty_Pass(){
        log.info("Step 01: Input email to Email text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);

        log.info("Step 02: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 03: Verify Error message is displayed");
        verifyTrue(loginPage.isValidEmailErrorMessageDisplay("Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect"));
    }

    @Test
    public void TC_05_Login_With_Correct_Email_And_Incorrect_Email(){
        log.info("Step 01: Input email to Email text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);

        log.info("Step 02: Input password to Password text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Password","2334245");

        log.info("Step 03: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 04: Verify Error message is displayed");
        verifyTrue(loginPage.isValidEmailErrorMessageDisplay("Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect"));
    }
    @Test
    public void TC_06_Login_Successfully(){
        log.info("Step 01: Input email to Email text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);

        log.info("Step 02: Input password to Password text box");
        loginPage.sendKeyToDynamicTextbox(driver,"Password",GlobalConstants.password);

        log.info("Step 03: Click on Login button");
        loginPage.clickOnDynamicButton(driver,"Log in");

        log.info("Step 04: Verify Home Page is display");
        homePage= PageGeneratorManager.getHomePage(driver);
        verifyTrue(homePage.isSliderDisplay());
    }
}
