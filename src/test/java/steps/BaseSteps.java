package steps;

import org.testng.asserts.SoftAssert;
import web.pages.*;

public class BaseSteps {
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutInfoPage checkoutInfoPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;
    protected SoftAssert softAssert = new SoftAssert();

    public BaseSteps(LoginPage loginPage, CatalogPage catalogPage, CartPage cartPage, CheckoutInfoPage checkoutInfoPage, CheckoutOverviewPage checkoutOverviewPage, CheckoutCompletePage checkoutCompletePage, SoftAssert softAssert) {
        this.loginPage = loginPage;
        this.catalogPage = catalogPage;
        this.cartPage = cartPage;
        this.checkoutInfoPage = checkoutInfoPage;
        this.checkoutOverviewPage = checkoutOverviewPage;
        this.checkoutCompletePage = checkoutCompletePage;
        this.softAssert = softAssert;
    }

    public BaseSteps(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }
}
