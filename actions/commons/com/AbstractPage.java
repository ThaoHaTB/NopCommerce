package com;

import java.util.List;
import java.util.Set;

import nopCommercePageUI.AbstractPageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    private Alert alert;
    private WebDriverWait explicitWait;
    private long timeout=30;
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;

    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public Alert waitForAlertPresence(WebDriver driver){
        explicitWait=new WebDriverWait(driver,timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver){
        alert =waitForAlertPresence(driver);
        alert.accept();
    }

    public void cancelAlert(WebDriver driver){
        alert=waitForAlertPresence(driver);
        alert.dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String value){
        alert=waitForAlertPresence(driver);
        alert.sendKeys(value);
    }

    public String getAlertText(WebDriver driver){
        alert=waitForAlertPresence(driver);
        return alert.getText();
    }

    public void swichToWindowByID(WebDriver driver, String parentID){
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(parentID)) {
                driver.switchTo().window(winHandle);
                driver.close();
                sleepInSecond(1);
            }
            if (driver.getWindowHandles().size() == 1) {
                driver.switchTo().window(parentID);
                break;
            }
        }
    }

    public void swichToWindowByTitle(WebDriver driver, String expectedWindowTitle){
        Set<String> allWindowID=driver.getWindowHandles();
        for(String id:allWindowID){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(expectedWindowTitle))
                break;
        }
    }

    public void closeAllWindowExceptParent(WebDriver driver,String parentID){
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(parentID)) {
                driver.switchTo().window(winHandle);
                driver.close();
                sleepInSecond(1);
            }
            if (driver.getWindowHandles().size() == 1) {
                driver.switchTo().window(parentID);
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }
    public WebElement getElement(WebDriver driver, String locator, String... params){
        return driver.findElement(getByXpath(getDynamicLocator(locator,params)));
    }

    public List<WebElement> getElements(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getElement(driver,locator).click();
    }
    public void clickToElement(WebDriver driver, WebElement element){
        element.click();
    }

    public String getDynamicLocator(String locator, String... params){
        return String.format(locator,(Object[]) params);
    }
    public void clickToElement(WebDriver driver, String locator,String... params){

        getElement(driver,getDynamicLocator(locator,params)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value){
        getElement(driver,locator).clear();
        getElement(driver,locator).sendKeys(value);
    }
    public void sendKeyToElement(WebDriver driver, String locator, String value, String... params){
        locator=getDynamicLocator(locator, params);
        getElement(driver,locator).clear();
        getElement(driver,locator).sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    public int getElementSize(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator)).size();
    }
    public int getElementSize(WebDriver driver, String locator, String... params){
        locator=getDynamicLocator(locator,params);
        return driver.findElements(getByXpath(locator)).size();
    }
    public void selectDropdownByText(WebDriver driver,String locator, String itemText){
        select=new Select(getElement(driver,locator));
        select.selectByVisibleText(itemText);
    }
    public void selectDropdownByText(WebDriver driver,String locator, String itemText, String...params){
        select =new Select(driver.findElement(getByXpath(getDynamicLocator(locator,params))));
        select.selectByVisibleText(itemText);
    }
    public WebElement getSelectedItemDropdown(WebDriver driver,String locator, String...params){
        select =new Select(driver.findElement(getByXpath(getDynamicLocator(locator,params))));
        return select.getFirstSelectedOption();
    }


    public boolean isDropdownMultiple(WebDriver driver,String locator){
        select = new Select(getElement(driver,locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);
        explicitWait = new WebDriverWait(driver, timeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator,String attributeName){
        return getElement(driver,locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator,String attributeName, String... params){
        locator=getDynamicLocator(locator,params);
        return getElement(driver,locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver,String locator){
        return getElement(driver,locator).getText();
    }

    public String getElementText(WebDriver driver,String locator, String... params){
        return getElement(driver,getDynamicLocator(locator,params)).getText();
    }

    public void checkTocheckboxOrRadio(WebDriver driver,String locator){
        if(!getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator){
        if(getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver,locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... params){
        return getElement(driver,getDynamicLocator(locator,params)).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }
    public void waitForElementVisible(WebDriver driver,String locator){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver,String locator, String... params){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator,params))));
    }

    public void waitForElementVisible(WebDriver driver,WebElement ele){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForAllElementVisible(WebDriver driver,String locator){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver,String locator){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }
    public void waitForElementClickable(WebDriver driver,String locator,String... params){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator,params))));
    }

    public void waitForElementClickable(WebDriver driver,WebElement element){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator,String... params){
        explicitWait=new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator,params))));
    }

    public WebDriver switchToIframeByElement(WebDriver driver, String locator){
        return driver.switchTo().frame(getElement(driver,locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator){
        action=new Actions(driver);
        action.moveToElement(getElement(driver,locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        action=new Actions(driver);
        action.doubleClick(getElement(driver,locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        action= new Actions(driver);
        action.contextClick(getElement(driver,locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator,String targetLocator){
        action=new Actions(driver);
        action.dragAndDrop(getElement(driver,sourceLocator),getElement(driver,targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key){
        action=new Actions(driver);
        action.sendKeys(getElement(driver,locator),key).perform();
    }
    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params){
        action=new Actions(driver);
        locator=getDynamicLocator(locator,params);
        action.sendKeys(getElement(driver,locator),key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    //nopcommerce
    public void sendKeyToDynamicTextbox(WebDriver driver, String textbox_id, String inputValue) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_TEXT_BOX, textbox_id);
        getElement(driver,nopCommercePageUI.AbstractPageUI.DYNAMIC_TEXT_BOX, textbox_id).clear();
        sendKeyToElement(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_TEXT_BOX, inputValue, textbox_id);
    }

    public void clickOnDynamicButton(WebDriver driver, String buuttonText) {
        waitForElementClickable(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_BUTTON, buuttonText);
        clickToElement(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_BUTTON, buuttonText);
    }

    public void clickOnDynamicRadioButton(WebDriver driver, String label) {
        waitForElementClickable(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_RADIO_BUTTON, label);
        clickToElement(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_RADIO_BUTTON, label);
    }

    public void selectDynamicDropdowlist(WebDriver driver, String name, String valueSlect) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_SELECT, name);
        selectDropdownByText(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_SELECT, valueSlect, name);
    }

    public boolean isRadioButtonSelectCorrectly(WebDriver driver, String label) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_RADIO_BUTTON, label);
        return isElementDisplayed(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_RADIO_BUTTON, label);
    }

    public boolean isTextboxloadCorrectly(WebDriver driver, String textbox_id, String valueInput) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_TEXT_BOX, textbox_id);

        return getElement(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_TEXT_BOX, textbox_id).getAttribute("value").equals(valueInput);
    }

    public boolean isSelectLoadCorrectly(WebDriver driver, String name, String valueSelect) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_SELECT, name);
        return getSelectedItemDropdown(driver, nopCommercePageUI.AbstractPageUI.DYNAMIC_SELECT, name).getText().equals(valueSelect);
    }

    public void clickOnDymanicMenuLink(WebDriver driver, String pageName) {
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.HEADER_LINK, pageName);
        clickToElement(driver, nopCommercePageUI.AbstractPageUI.HEADER_LINK, pageName);
    }

    public void clickOnCategoryMenuLink(WebDriver driver, String categoryName) {
        waitForElementVisible(driver, AbstractPageUI.CATEGORY_MENU, categoryName);
        clickToElement(driver, nopCommercePageUI.AbstractPageUI.CATEGORY_MENU, categoryName);
    }

    public boolean isErrorMessageDisplay(WebDriver driver, String errorID, String message){
        waitForElementVisible(driver, nopCommercePageUI.AbstractPageUI.ERROR_MESSAGE,errorID);
        return getElement(driver, nopCommercePageUI.AbstractPageUI.ERROR_MESSAGE,errorID).getText().equals(message);
    }
}
