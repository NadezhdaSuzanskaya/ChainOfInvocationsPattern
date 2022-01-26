package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.*;

public class ProductTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Sauce Labs Backpack";
    private String count = "1";
    private String price = "$29.99";
    private String button_name = "ADD TO CART";
    private String button_name_after_adding = "REMOVE";
    private String desc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";

    @BeforeMethod
    private void login() {
        Assert.assertTrue(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded");
        Assert.assertTrue(
                loginPage
                        .login(USERNAME, PASSWORD)
                        .open()
                        .isPageLoaded()
                , "Catalog page is not loaded");
    }

    @Test
    public void addProductToCartTest() {
        //add test product to cart

        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        Assert.assertTrue(
                cartPage
                        .open()
                        .isPageLoaded()
                , "Cart page is not loaded");
        Assert.assertEquals(
                cartPage
                        .findProductByNameInCard(TEST_PRODUCT_TITLE)
                , TEST_PRODUCT_TITLE
                , "Selected product is not in the cart");
        Assert.assertEquals(
                catalogPage
                        .getCountOfProductInCart()
                , count
                , "Count of product on the icon is not 1");
    }

    @Test
    public void logoutTest() {
        Assert.assertTrue(
                catalogPage
                        .logout()
                        .open()
                        .isPageLoaded(), "Login page page is not loaded");
    }

    @Test
    public void checkButtonName() {
        Assert.assertEquals(
                catalogPage
                        .findProductAddButton(TEST_PRODUCT_TITLE)
                , button_name
                , "Name of the button on the product isn't correct");
        Assert.assertEquals(
                catalogPage
                        .addProductToCart(TEST_PRODUCT_TITLE)
                        .findProductAddButton(TEST_PRODUCT_TITLE)
                , button_name_after_adding
                , "Name of the button on the product isn't correct");

    }

    @Test
    public void checkProductElementsTest() {
        Assert.assertEquals(
                catalogPage
                        .findProductPrice(TEST_PRODUCT_TITLE)
                , price
                , "Price of the product isn't correct");

        Assert.assertEquals(
                catalogPage
                        .findProductAddButton(TEST_PRODUCT_TITLE)
                , button_name
                , "Name of the button on the product isn't correct");

        Assert.assertEquals(
                catalogPage
                        .findProductDesc(TEST_PRODUCT_TITLE)
                , desc
                , "Description of the product isn't correct");

        Assert.assertTrue(
                catalogPage
                        .findProductImg(TEST_PRODUCT_TITLE)
                , "IMG of the product isn't displayed");

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