package steps;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import web.pages.*;

public class PurchaseSteps extends BaseSteps{
    public static final String TEST_PRODUCT_TITL = "Test.allTheThings() T-Shirt (Red)";
    public PurchaseSteps(CartPage cartPage) {
        super(cartPage);
    }

    public PurchaseSteps checkProductDetailsInCart(String price, String desc) {
        System.out.println("price:"+price);
        softAssert.assertEquals(cartPage.findProductPrice(TEST_PRODUCT_TITL), price, "The price is not for selected product");
        softAssert.assertEquals(cartPage.findProductDesc(TEST_PRODUCT_TITL), desc, "The dscription is for not selected product");

        return this;
    }

    public PurchaseSteps checkNameOfProductInCart() {
        //the page cart
        String product = "";
        if (cartPage.ListOfProductsInCart() != null) {
            for (int i = 0; i < cartPage.ListOfProductsInCart().size(); i++) {
                if (cartPage.ListOfProductsInCart().get(i).equals(TEST_PRODUCT_TITL)) {
                    product = cartPage.ListOfProductsInCart().get(i);
                }
            }
        }
        Assert.assertEquals(product, TEST_PRODUCT_TITL, "Selected product is not in the cart");
        return this;
    }
}
