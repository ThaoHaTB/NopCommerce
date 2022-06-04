package nopCommercePageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static HomePageObject homePage;
    public static RegisterPageObject registerPage;
    public static MyAccountPage myAccountPage;
    public static LoginPageObject loginPage;
    public static SearchPageObject searchPage;
    private PageGeneratorManager(){
    }
    public static HomePageObject getHomePage(WebDriver driver){
        return (homePage == null) ? homePage = new HomePageObject(driver) : homePage;
    }
    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return (registerPage == null) ? registerPage = new RegisterPageObject(driver) : registerPage;
    }
    public static LoginPageObject getLoginPage(WebDriver driver){
        return (loginPage == null) ? loginPage = new LoginPageObject(driver) : loginPage;
    }
    public static MyAccountPage getMyAccountPage(WebDriver driver){
        return (myAccountPage == null) ? myAccountPage = new MyAccountPage(driver) : myAccountPage;
    }

    public static SearchPageObject getSearchPage(WebDriver driver){
        return (searchPage == null) ? searchPage = new SearchPageObject(driver) : searchPage;
    }

}
