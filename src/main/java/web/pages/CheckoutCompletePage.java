package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/checkout-complete.html";
    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Complete!']");


    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TITLE_LOCATOR));
        } catch (TimeoutException timeoutException) {
            return null;
        }
        return this;
    }

    @Override
    public BasePage open() {
        driver.get(BASE_URL);
        return this;
    }

}

