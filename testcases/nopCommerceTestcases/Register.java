package nopCommerceTestcases;

import com.BaseTest;
import com.GlobalConstants;
import nopCommercePageObjects.HomePageObject;
import nopCommercePageObjects.LoginPageObject;
import nopCommercePageObjects.RegisterPageObject;
import nopCommercePageUI.RegisterPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class Register extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    //LoginPageObject loginPage;
    //String email, password;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        log.info("Precondition-Step 01: Open url");
        driver = getBrowser(browser, url);
        GlobalConstants.email=getRandomemail();
        GlobalConstants.password="Abcd@12345";
        log.info("Precondition-Step 02: Verify Home page is displayed");
        homePage=new HomePageObject(driver);
        Assert.assertTrue(homePage.isSliderDisplay());

        log.info("Precondition- Step 03: Go to Register Page");
        homePage.clickOnDymanicMenuLink(driver,"Register");
        registerPage=new RegisterPageObject(driver);
    }
    @Test
    public void TC_01_Register_With_Empty_Data(){
        log.info("Step 01: Click on Register button");
        registerPage.clickOnDynamicButton(driver,"Register");

        log.info("Step 2: Verify required First Name message is displayed");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"FirstName-error","First name is required."));

        log.info("Step 3: Verify required Last Name message is displayed");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"LastName-error","Last name is required."));

        log.info("Step 4: Verify required Password message is displayed");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"Password-error","Password is required."));

        log.info("Step 5: Verify required Confirm Password message is displayed");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"ConfirmPassword-error","Password is required."));
    }
    @Test
    public void TC_02_Register_With_Invalid_Email(){

        log.info("Step 01: Input invalid email");
        registerPage.sendKeyToDynamicTextbox(driver,"Email","aaa");

        log.info("Step 02: Verify error message display");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"Email-error","Wrong email"));

    }
    @Test
    public void TC_03_Register_With_Valid_Data(){
        log.info("Step 01: Choose Gender");
        registerPage.clickOnDynamicRadioButton(driver,"Female");

        log.info("Step 02: Input to First name textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"FirstName","Thao");

        log.info("Step 03: Input to Last name textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "LastName","Ha");

        log.info("Step 04: Input to Email textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);

        log.info("Step 05: Input to Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "Password",GlobalConstants.password);

        log.info("Step 06: Input to Confirm Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "ConfirmPassword",GlobalConstants.password);;

        log.info("Step 07: Click to Register button");
        registerPage.clickOnDynamicButton(driver,"Register");

        log.info("Step 08: Verify successful message is displayed");
        verifyTrue(registerPage.isSuccessMessageDisplay());

        log.info("Step 09: Logout");
        registerPage.clickOnDymanicMenuLink(driver,"Log out");
    }
    @Test
    public void TC_04_Register_With_Existed_Email(){
        log.info("Step 01: Go to Register Page");
        registerPage.clickOnDymanicMenuLink(driver,"Register");
        registerPage=new RegisterPageObject(driver);

        log.info("Step 02: Input to First name textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"FirstName","Thao");

        log.info("Step 03: Input to Last name textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "LastName","Ha");

        log.info("Step 04: Input to Email textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);

        log.info("Step 05: Input to Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "Password",GlobalConstants.password);

        log.info("Step 06: Input to Confirm Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "ConfirmPassword",GlobalConstants.password);

        log.info("Step 07: Click to Register button");
        registerPage.clickOnDynamicButton(driver,"Register");

        log.info("Step 8: Verify invalid email error message is displayed");
        verifyTrue(registerPage.isElementDisplayed(driver, RegisterPageUI.INVALID_EMAIL_ERROR_MESSAGE));

    }

    @Test
    public void TC_05_Register_With_Invalid_Password_Length(){
        log.info("Step 01: Go to Register Page");
        registerPage.clickOnDymanicMenuLink(driver,"Register");
        registerPage=new RegisterPageObject(driver);

        log.info("Step 02: Input password has 3 characters");
        registerPage.sendKeyToDynamicTextbox(driver,"Password","1aB");

        log.info("Step 03: Click on Register button");
        registerPage.clickOnDynamicButton(driver,"Register");

        log.info("Step 04: Verify error message display");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"Password-error","Password must meet the following rules:"));
    }

    @Test
    public void TC_06_Register_With_Password_Not_Match(){
        log.info("Step 01: Input data to Password field");
        registerPage.sendKeyToDynamicTextbox(driver, "Password",GlobalConstants.password);

        log.info("Step 02: Input to Confirm Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "ConfirmPassword","abcd111");

        log.info("Step 03: Click on Register button");
        registerPage.clickOnDynamicButton(driver,"Register");

        log.info("Step 04: Verify error message display");
        verifyTrue(registerPage.isErrorMessageDisplay(driver,"ConfirmPassword-error","The password and confirmation password do not match."));
    }

    public String getRandomemail(){
        Random generator = new Random();
        return "hathaok37cntt+"+generator.nextInt()+"@gmail.com";
    }
}
