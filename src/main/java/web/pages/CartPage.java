package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Your Cart']");
    private static final By PRODUCRT_NAME_LOCATOR = By.className("inventory_item_name");
    private static final By BUTTON_CHECKOUT = By.id("checkout");
    private static final By BUTTON_CONTINUE_SHOPPING = By.id("continue-shopping");
    private static final String PRODUCT_DESC_LOCATOR = "//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_desc']";
    private static final String PRODUCT_PRICE_LOCATOR = "//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";
    //  private static final String LIST_OF_PRODUCT_IN_CART = "//div[@class='cart_item']";
    private static final String LIST_OF_PRODUCT_IN_CART = "//div[@class='cart_item']//div[@class='inventory_item_name']";
    private static final String COUNT_ICON = "[class='shopping_cart_badge']";


    public CartPage(WebDriver driver) {
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

    public String findProductByNameInCard(String partialProductTitle) {
        return driver.findElement(PRODUCRT_NAME_LOCATOR).getText();
    }

    public String findProductPrice(String partialProductTitle) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, partialProductTitle))).getText();
    }

    public String findProductDesc(String partialProductTitle) {

        return driver.findElement(By.xpath(String.format(PRODUCT_DESC_LOCATOR, partialProductTitle))).getText();
    }

    public CheckoutInfoPage checkout() {

        driver.findElement(BUTTON_CHECKOUT).click();
        return new CheckoutInfoPage(driver);
    }

    public void returnToProductsPage() {
        driver.findElement(BUTTON_CONTINUE_SHOPPING).click();
    }

    public boolean findContinueShoppingButton() {
        return driver.findElements(BUTTON_CONTINUE_SHOPPING).isEmpty();

    }

    public boolean CheckListOfProducts() {
        return driver.findElements(By.xpath(LIST_OF_PRODUCT_IN_CART)).isEmpty();
    }

    public boolean CheckCountIconIsNull() {
        return driver.findElements(By.cssSelector(COUNT_ICON)).isEmpty();
    }

    public String CheckCountIcon() {
        return driver.findElement(By.cssSelector(COUNT_ICON)).getText();
    }

    public List<String> ListOfProductsInCart() {
        List<WebElement> elems;
        List<String> expectedProductList = new ArrayList<>();
        if (!CheckListOfProducts()) {
            elems = driver.findElements(By.cssSelector("[class='cart_item']"));

            for (int i = 0; i < elems.size(); i++) {
                //  expectedProductList.add("[class='cart_item']");
                expectedProductList.add(driver.findElements(By.xpath(LIST_OF_PRODUCT_IN_CART)).get(i).getText());
            }
            return expectedProductList;
        } else {
            return null;
        }
    }
}
