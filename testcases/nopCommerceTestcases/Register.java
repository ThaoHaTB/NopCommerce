package nopCommerceTestcases;

import com.BaseTest;
import nopCommerceUI.HomePageObject;
import nopCommerceUI.LoginPageObject;
import nopCommerceUI.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class Register extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    String email, password;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        log.info("Precondition-Step 01: Open url");
        driver = getBrowser(browser, url);
        email=getRandomemail();
        password="Abcd@12345";
    }
    @Test
    public void TC_01_Register(){
        log.info("Step 02: Verify Home page is displayed");
        homePage=new HomePageObject(driver);
        Assert.assertTrue(homePage.isSliderDisplay());

        log.info("Step 03: Go to Register Page");
        homePage.clickOnDymanicMenuLink(driver,"Register");
        registerPage=new RegisterPageObject(driver);

        log.info("Step 04: Choose Gender");
        registerPage.clickToDynamicRadioButton(driver,"Female");

        log.info("Step 05: Input to First name textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"FirstName","Thao");

        log.info("Step 06: Input to Last name textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "LastName","Ha");

        log.info("Step 07: Input to Email textbox");
        registerPage.sendKeyToDynamicTextbox(driver,"Email",email);

        log.info("Step 08: Input to Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "Password",password);

        log.info("Step 09: Input to Confirm Password textbox");
        registerPage.sendKeyToDynamicTextbox(driver, "ConfirmPassword",password);;

        log.info("Step 10: Click to Register button");
        registerPage.clickRegisterButton();

        log.info("Step 11: Verify successfull message is displayed");
        Assert.assertTrue(registerPage.isSuccessMessageDisplay());
    }

    public String getRandomemail(){
        Random generator = new Random();
        return "hathaok37cntt+"+generator.nextInt()+"@gmail.com";
    }
}
