package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class РurchaseTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";
    public static final String TEST_PRODUCT_TITLE1 = "Sauce Labs Backpack";

    @BeforeMethod
    private void login() {
        Assert.assertNotNull(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded");
        Assert.assertNotNull(
                loginPage
                        .login(USERNAME, PASSWORD)
                        .open()
                        .isPageLoaded()
                , "Catalog page is not loaded");
    }

    @Test
    public void makePurchaseTest() {
        String price = catalogPage.findProductPrice(TEST_PRODUCT_TITLE);
        String desc = catalogPage.findProductDesc(TEST_PRODUCT_TITLE);
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);

        //the page cart
        Assert.assertNotNull(
                cartPage
                        .open()
                        .isPageLoaded()
                , "Cart page is not loaded");
        //check that in the cart is the same product that was added from the list of products
        purchaseSteps.checkNameOfProductInCart();
        purchaseSteps.checkProductDetailsInCart(price, desc);
        Assert.assertNotNull(
                cartPage
                        .checkout()
                        .open()
                        .isPageLoaded()
                , "CheckoutInfo  page is not loaded");
        Assert.assertNotNull(
                checkoutInfoPage
                        .enterData(FIRST_NAME, LAST_NAME, ZIP_CODE)
                        .clickContinue()
                        .open()
                        .isPageLoaded()
                , "Checkout Overage  page is not loaded");

        checkProductInSecondConfirmationPage(price, desc);
        Assert.assertNotNull(
                checkoutOverviewPage
                        .clickFinish()
                        .open()
                        .isPageLoaded()
                , "Checkout Overview  page is not loaded");
    }

    private void checkProductInSecondConfirmationPage(String price, String desc) {
        Assert.assertEquals(checkoutOverviewPage.findProductByNameInDescription(TEST_PRODUCT_TITLE), TEST_PRODUCT_TITLE, "Selected product is not in the cart");
        Assert.assertEquals(checkoutOverviewPage.findProductPrice(TEST_PRODUCT_TITLE), price, "The price is not for selected product");
        Assert.assertEquals(checkoutOverviewPage.findProductDesc(TEST_PRODUCT_TITLE), desc, "The description is not for selected product");
    }

    @Test
    public void checkCounIconInCartTest() {
        String str1 = "";
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        Assert.assertNotNull(
                cartPage
                        .open()
                        .isPageLoaded()
                , "Cart page is not loaded");
        str1 = Integer.toString(cartPage.ListOfProductsInCart().size());
        Assert.assertEquals(str1, cartPage.CheckCountIcon(), "Value of added products is not right");
    }

    @Test
    public void checkEmptyIconInCartTest() {
        Assert.assertNotNull(
                cartPage
                        .open()
                        .isPageLoaded()
                , "Cart page is not loaded");
        Assert.assertTrue(cartPage.CheckCountIconIsNull(), "Some value is displayed on the icon 'Cart'");
    }

    @AfterMethod
    private void removeFromCart() {
        if ((cartPage.findContinueShoppingButton()) && (loginPage.findLoginButton())) {
            catalogPage.returnToOriginalState();
        } else if (loginPage.findLoginButton()) {
            cartPage.returnToProductsPage();
            catalogPage.returnToOriginalState();
        }

    }

}