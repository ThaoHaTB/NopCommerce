package nopCommercePageUI;

public class AbstractPageUI {
    //Header link
    public static final String HEADER_LINK="//div[@class='header-links']//a[text()='%s']";
    //Text box
    public static final String DYNAMIC_TEXT_BOX="//input[@id='%s']";
    //button
    public static final String DYNAMIC_BUTTON="//button[text()='%s']";
    //radio button
    public static final String DYNAMIC_RADIO_BUTTON="//label[text()='%s']/../input";
    // Dropdown list
    public static final String DYNAMIC_SELECT="//select[@name='%s']";
    // Error message
    public static final String ERROR_MESSAGE="//span[@id='%s']";
    // Category menu
    public  static final String CATEGORY_MENU="//div[@class='listbox']//li/a[text()='%s']";
    // Info
    public  static final String INFO="//ul[@class='info']/li[@class='%s']";

}
