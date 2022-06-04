package nopCommerceTestcases;

import com.BaseTest;
import nopCommercePageObjects.HomePageObject;
import nopCommercePageObjects.LoginPageObject;
import nopCommercePageObjects.PageGeneratorManager;
import nopCommercePageObjects.SearchPageObject;
import nopCommercePageUI.HomePageUI;
import nopCommercePageUI.SearchUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    SearchPageObject searchPage;
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
        searchPage=new SearchPageObject(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 02: Verify Error message display");
        verifyTrue(searchPage.getAlertText(driver).equals("Please enter some search keyword"));
        searchPage.acceptAlert(driver);
    }

    @Test
    public void TC_02_Search_No_Result(){
        searchPage=PageGeneratorManager.getSearchPage(driver);
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Macbook Pro 2050");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Verify error message display");
        verifyTrue(searchPage.getElement(driver, SearchUI.MESSAGE_NO_RESULT).getText().equals("No products were found that matched your criteria."));
    }

    @Test
    public void TC_03_Search_With_Relative_Product_Name(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Lenovo");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Verify result");
        verifyTrue(searchPage.getElementSize(driver,SearchUI.LIST_ITEM)==2);
    }
    @Test
    public void TC_04_Search_With_Absolute_Product_Name(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Lenovo Thinkpad X1 Carbon Laptop");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Verify result");
        verifyTrue(searchPage.getElementSize(driver,SearchUI.LIST_ITEM)==1);
    }

    @Test
    public void TC_05_Advance_Search_With_Parent_Category(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Apple Macbook Pro");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Check on Advanced search");
        searchPage.clickOnDynamicRadioButton(driver,"Advanced search");

        log.info("Step 04: Select Category dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"cid","Computers");

        log.info("Step 05: Click on Search button");
        searchPage.clickToElement(driver,SearchUI.SEARCH_BUTTON);

        log.info("Step 06: Verify error message display");
        verifyTrue(searchPage.getElement(driver, SearchUI.MESSAGE_NO_RESULT).getText().equals("No products were found that matched your criteria."));

    }
    @Test
    public void TC_06_Advance_Search_With_Sub_Category(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Apple Macbook Pro");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Check on Advanced search");
        searchPage.clickOnDynamicRadioButton(driver,"Advanced search");

        log.info("Step 04: Select Category dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"cid","Computers");

        log.info("Step 05: Check on Automatically search sub categories");
        searchPage.clickOnDynamicRadioButton(driver,"Automatically search sub categories");

        log.info("Step 06: Click on Search button");
        searchPage.clickToElement(driver,SearchUI.SEARCH_BUTTON);

        log.info("Step 06: Verify result");
        verifyTrue(searchPage.getElementSize(driver,SearchUI.LIST_ITEM)==1);

    }
    @Test
    public void TC_07_Advance_Search_Incorrect_Manufacturer(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Apple Macbook Pro");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Check on Advanced search");
        searchPage.clickOnDynamicRadioButton(driver,"Advanced search");

        log.info("Step 04: Select Category dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"cid","Computers");

        log.info("Step 035: Check on Automatically search sub categories");
        searchPage.clickOnDynamicRadioButton(driver,"Automatically search sub categories");

        log.info("Step 04: Select Manufacturer dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"mid","HP");

        log.info("Step 05: Click on Search button");
        searchPage.clickToElement(driver,SearchUI.SEARCH_BUTTON);

        log.info("Step 06: Verify error message display");
        verifyTrue(searchPage.getElement(driver, SearchUI.MESSAGE_NO_RESULT).getText().equals("No products were found that matched your criteria."));
    }
    @Test
    public void TC_08_Advance_Search_Correct_Manufacturer(){
        log.info("Step 01: Input keyword to Search textbox");
        searchPage.sendKeyToDynamicTextbox(driver,"small-searchterms","Apple Macbook Pro");

        log.info("Step 02: Click on Search button");
        searchPage=PageGeneratorManager.getSearchPage(driver);
        searchPage.clickOnDynamicButton(driver,"Search");

        log.info("Step 03: Check on Advanced search");
        searchPage.clickOnDynamicRadioButton(driver,"Advanced search");

        log.info("Step 04: Select Category dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"cid","Computers");

        log.info("Step 035: Check on Automatically search sub categories");
        searchPage.clickOnDynamicRadioButton(driver,"Automatically search sub categories");

        log.info("Step 04: Select Manufacturer dropdown list");
        searchPage.selectDynamicDropdowlist(driver,"mid","Apple");

        log.info("Step 05: Click on Search button");
        searchPage.clickToElement(driver,SearchUI.SEARCH_BUTTON);

        log.info("Step 06: Verify result");
        verifyTrue(searchPage.getElementSize(driver,SearchUI.LIST_ITEM)==1);
    }
}
