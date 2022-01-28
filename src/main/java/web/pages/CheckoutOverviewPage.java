package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/checkout-step-two.html";
    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Overview']");
    private static final By BUTTON_FINISH = By.id("finish");
    private static final By PRODUCT_NAME_LOCATOR = By.className("inventory_item_name");
    private static final String PRODUCT_DESC_LOCATOR = "//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_desc']";
    private static final String PRODUCT_PRICE_LOCATOR = "//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";

    public CheckoutOverviewPage(WebDriver driver) {
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

    public String findProductByNameInDescription(String partialProductTitle) {
        return driver.findElement(PRODUCT_NAME_LOCATOR).getText();
    }

    public String findProductPrice(String partialProductTitle) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, partialProductTitle))).getText();
    }

    public String findProductDesc(String partialProductTitle) {

        return driver.findElement(By.xpath(String.format(PRODUCT_DESC_LOCATOR, partialProductTitle))).getText();
    }

    public CheckoutCompletePage clickFinish() {
        driver.findElement(BUTTON_FINISH).click();
        return new CheckoutCompletePage(driver);
    }
}
