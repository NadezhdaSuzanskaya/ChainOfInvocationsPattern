package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElementId = LOGIN_BUTTON;
    }

    private static final By USERNAME_TEXT_FIELD = By.id("user-name");
    private static final By PASSWORD_TEXT_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.tagName("h3");

    public static final String USERNAME_TEXT_FIELD_PLACEHOLDER = "Username";
    public static final String PASSWORD_TEXT_FIELD_PLACEHOLDER = "Password";

    public CatalogPage login(String username, String password) {
        driver.findElement(USERNAME_TEXT_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new CatalogPage(driver);
    }

    public LoginPage invalidlLogin(String username, String password) {
        driver.findElement(USERNAME_TEXT_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new LoginPage(driver);
    }


    public String getUsernamePlaceholder() {
        return driver.findElement(USERNAME_TEXT_FIELD).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return driver.findElement(PASSWORD_TEXT_FIELD).getAttribute("placeholder");
    }

    public String getErrorText() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public boolean findLoginButton() {
        return driver.findElements(LOGIN_BUTTON).isEmpty();

    }

    public String returnLoginErrorMessage(String username, String password) {
        invalidlLogin(username, password);
        return getErrorText();
    }
}
