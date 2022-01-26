package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.LoginPage;

public class LoginTest extends BaseTest {

    public static final String invalidCredential = "Epic sadface: Username and password do not match any user in this service";
    public static final String requiredUserName = "Epic sadface: Username is required";
    public static final String requiredUserPassword = "Epic sadface: Password is required";

    @BeforeMethod
    public void openLoginPage() {
        Assert.assertTrue(
                loginPage
                        .open()
                        .isPageLoaded()
                , "Login page is not loaded");
    }

    @Test
    public void validCredentialsLoginTest() {
        Assert.assertTrue(
                loginPage
                        .login(USERNAME, PASSWORD)
                        .open()
                        .isPageLoaded()
                , "Catalog page is not loaded");
    }

    @Test
    public void invalidCredentialsLoginTest() {

        Assert.assertEquals(
                loginPage
                        .returnLoginErrorMessage(WRONG_USERNAME, WRONG_PASSWORD)
                , invalidCredential
                , "The text message when credentials are wrong is not correct");
    }

    @Test
    public void requiredUserNameTest() {
        Assert.assertEquals(
                loginPage
                        .returnLoginErrorMessage(EMPTY_NAME, PASSWORD)
                , requiredUserName
                , "The text message when username is absent  is not correct");
    }

    @Test
    public void requiredPasswordTest() {
        Assert.assertEquals(
                loginPage
                        .returnLoginErrorMessage(USERNAME, EMPTY_PASSWORD)
                , requiredUserPassword
                , "The text message when password is absent  is not correct");
    }

    @Test
    public void USERNAME_PLACEHOLDER_TEST() {
        Assert.assertEquals(
                loginPage.
                        getUsernamePlaceholder()
                , LoginPage.USERNAME_TEXT_FIELD_PLACEHOLDER
                , "Username placeholder is not valid"
        );
    }

    @Test
    public void PASSWORD_PLACEHOLDER_TEST() {
        Assert.assertEquals(
                loginPage.
                        getPasswordPlaceholder()
                , LoginPage.PASSWORD_TEXT_FIELD_PLACEHOLDER
                , "Password placeholder is not valid"
        );
    }

}
