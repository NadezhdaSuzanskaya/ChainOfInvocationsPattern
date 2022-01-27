package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage  extends BasePage{
    public static final String BASE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Your Information']");
    private static final By BUTTON_CONTINUE=By.id("continue");
    private static final By FIRST_NAME_FIELD=By.name("firstName");
    private static final By LAST_NAME_FIELD=By.name("lastName");
    private static final By ZIP_CODE_FIELD=By.name("postalCode");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        this.baseUrl=BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
        this.firstname=FIRST_NAME_FIELD;
        this.lastname=LAST_NAME_FIELD;
        this.zipcode=ZIP_CODE_FIELD;
    }
    public CheckoutInfoPage enterData(String firstname, String lastname, String zipcode)
    {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstname);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIP_CODE_FIELD).sendKeys(zipcode);
        return new CheckoutInfoPage(driver);
    }
    public CheckoutOverviewPage clickContinue()
    {
        driver.findElement(BUTTON_CONTINUE).click();
        return new CheckoutOverviewPage(driver);
    }
}
