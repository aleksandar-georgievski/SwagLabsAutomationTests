package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class CheckoutTest extends Base {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CheckoutFirstPage checkoutStepOnePage;
    CheckoutSecondPage checkoutStepTwoPage;
    CartPage cartPage;
    SuccessPage successPage;
    public CheckoutTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutFirstPage(driver);
        checkoutStepTwoPage = new CheckoutSecondPage(driver);
        successPage = new SuccessPage(driver);

        loginPage.login(prop.getProperty("validUsername"), (prop.getProperty("validPassword")));

        Assert.assertTrue(loginPage.getMainHeadingText().contains("Swag Labs"), "Expected Heading Text is not displayed");


    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySelectingASingleProductsFromProductsPageAndAddToCart() {
        productsPage.addProductToCart(prop.getProperty("validProductName"));
    }
    @Test(priority = 2)
    public void verifyClickingOnCartIconWithoutSelectingAProduct() {
        productsPage.redirectToCartPage();
        Assert.assertEquals(checkoutStepOnePage.getCartPageTitle(), "Your Cart");
    }
    @Test(priority = 3)
    public void verifyIfTextFieldsInBillingSectionHavePlaceholders() {
        productsPage.redirectToCartPage();
        Assert.assertEquals(checkoutStepOnePage.getCartPageTitle(), "Your Cart");
        cartPage.redirectToCheckoutPage();
        Assert.assertTrue(checkoutStepOnePage.checkForFirstNamePlaceholderText().contains("First Name"));
        Assert.assertEquals(checkoutStepOnePage.checkForLastNamePlaceholderText(), "Last Name");
        Assert.assertEquals(checkoutStepOnePage.checkForPostalCodePlaceholderText(), "Zip/Postal Code");
    }
    @Test(priority = 4)
    public void verifyBillingSectionWithValidCredentials() {
        productsPage.redirectToCartPage();
        Assert.assertEquals(checkoutStepOnePage.getCartPageTitle(), "Your Cart");
        cartPage.redirectToCheckoutPage();
        checkoutStepOnePage.completeBillingPlaceholders("Aleksandar", "Georgievski", "1000");
    }
    @Test(priority = 5)
    public void verifyBillingSectionWithoutProvidingAnyCredentials() {
        productsPage.addProductToCart(prop.getProperty("validProductName"));
        productsPage.redirectToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(prop.getProperty("validProductName")));
        cartPage.redirectToCheckoutPage();
        checkoutStepOnePage.redirectOnCheckoutStepTwoPage();
        Assert.assertTrue(checkoutStepOnePage.getCheckoutStepOneErrorMessage().contains(prop.getProperty("checkoutStepOneErrorMessage")));
    }
    @Test(priority = 6)
    public void verifyAddingASingleProductToCartAndCheckVisibilityOfTheProductInOverviewSection() {
        productsPage.addProductToCart(prop.getProperty("validProductName"));
        productsPage.redirectToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(prop.getProperty("validProductName")));
        cartPage.redirectToCheckoutPage();
        checkoutStepOnePage.completeBillingPlaceholders("Aleksandar", "Georgievski", "1000");
        Assert.assertTrue(checkoutStepTwoPage.verifyProductDisplayFromOverviewList(prop.getProperty("validProductName")));
    }
    @Test(priority = 7)
    public void verifyAddingASingleProductToCartAndCompleteTheOrder() {
        productsPage.addProductToCart(prop.getProperty("validProductName"));
        productsPage.redirectToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay(prop.getProperty("validProductName")));
        cartPage.redirectToCheckoutPage();
        checkoutStepOnePage.completeBillingPlaceholders("Aleksandar", "Georgievski", "1000");
        Assert.assertTrue(checkoutStepTwoPage.verifyProductDisplayFromOverviewList(prop.getProperty("validProductName")));
        checkoutStepTwoPage.redirectToSuccessPage();

        Assert.assertEquals(successPage.verifySuccessHeaderMessage(), prop.getProperty("successHeaderMessage"));
        successPage.redirectToProductsPage();
    }
    @Test(priority = 8)
    public void verifySuccessHeadingText() {
        productsPage.redirectToCartPage();
        cartPage.redirectToCheckoutPage();
        checkoutStepOnePage.completeBillingPlaceholders("Aleksandar", "Georgievski", "1000");
        checkoutStepTwoPage.redirectToSuccessPage();
        Assert.assertEquals(successPage.verifySuccessHeaderMessage(), prop.getProperty("successHeaderMessage"));
    }
}














