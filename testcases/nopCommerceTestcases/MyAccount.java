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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import  customerInfo.*;

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
        loginPage=PageGeneratorManager.getLoginPage(driver);
        loginPage.sendKeyToDynamicTextbox(driver,"Email",GlobalConstants.email);
        loginPage.sendKeyToDynamicTextbox(driver,"Password",GlobalConstants.password);
        loginPage.clickOnDynamicButton(driver,"Log in");

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

        log.info("Step 05: Verify Gender is update successfully");
        verifyTrue(myAccountPage.isRadioButtonSelectCorrectly(driver,"Male"));

        log.info("Step 06: Verify First name is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"FirstName").equals("Mai"));

        log.info("Step 07: Verify Last name is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"LastName").equals("Le"));

        log.info("Step 08: Verify Email  is updated successfully");
        verifyTrue(myAccountPage.getElementText(driver, AbstractPageUI.DYNAMIC_TEXT_BOX,"Email").equals("tester@gmail.com"));
    }

    @Test
    public void TC_02_Add_A_Address(){
        log.info("Step 01: Go to Address category");
        myAccountPage.clickOnCategoryMenuLink(driver,"Addresses");

        log.info("Step 02: Click on new Address button");
        myAccountPage.clickOnDynamicButton(driver,"Add new");

        log.info("Step 03: Input info");
        CustomerAddress address=new CustomerAddress();
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_FirstName",address.getFirstName());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_LastName",address.getLastName());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_Email",address.getEmail());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_Company",address.getCompany());
        myAccountPage.selectDynamicDropdowlist(driver,"Address.CountryId",address.getCountry());
        //myAccountPage.selectDynamicDropdowlist(driver,"Address.StateProvinceId","Viet Nam");
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_City",address.getCity());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_Address1",address.getAddress1());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_Address2",address.getAddress2());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_ZipPostalCode",address.getZipcode());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_PhoneNumber",address.getPhoneNumber());
        myAccountPage.sendKeyToDynamicTextbox(driver,"Address_FaxNumber",address.getFaxNumber());
        myAccountPage.clickOnDynamicButton(driver,"Save");

        log.info("Step 03: Verify information is Save successfully");
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"name").equals(address.getFirstName()+" "+address.getLastName()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"email").split(":")[1].trim().equals(address.getEmail()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"phone").split(":")[1].trim().equals(address.getPhoneNumber()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"fax").split(":")[1].trim().equals(address.getFaxNumber()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"company").trim().equals(address.getCompany()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"address1").equals(address.getAddress1()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"address2").equals(address.getAddress2()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"city-state-zip").equals(address.getCity()+", "+address.getZipcode()));
        verifyTrue(myAccountPage.getElementText(driver,AbstractPageUI.INFO,"country").equals("Viet Nam"));
    }
    @Test
    public void TC_03_Change_Password(){
        log.info("Step 01: Go to Change Password category");
        myAccountPage.clickOnCategoryMenuLink(driver,"Change password");

        log.info("Step 02: Input old Password");
        myAccountPage.sendKeyToDynamicTextbox(driver,"OldPassword",GlobalConstants.password);

        log.info("Input new Password");
        myAccountPage.sendKeyToDynamicTextbox(driver,"NewPassword",GlobalConstants.newPassword);

        log.info("Input Confirm Password");
        myAccountPage.sendKeyToDynamicTextbox(driver,"ConfirmNewPassword",GlobalConstants.newPassword);
    }

    @Test
    public void TC_04_Add_A_Review_For_Product(){
        log.info("Step 01: Search a product");
        myAccountPage.sendKeyToDynamicTextbox(driver,"small-searchterms","computer");
        myAccountPage.clickOnDynamicButton(driver,"Search");
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
