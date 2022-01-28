package web.pages;

import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    protected By firstname;
    protected By lastname;
    protected By zipcode;

    public BasePage(WebDriver driver ) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public abstract BasePage isPageLoaded();
    public abstract BasePage open();

 /*   public BasePage open() {
        driver.get(baseUrl);
        return this;
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(basePageElementId));
        } catch (TimeoutException timeoutException) {
            return false;
        }
        return true;
    }
*/

}
