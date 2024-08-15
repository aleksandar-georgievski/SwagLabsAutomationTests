package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class ProductsTest extends Base {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication("firefox");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        loginPage.completeLogin(prop.getProperty("validUsername"), (prop.getProperty("validPassword")));
        Assert.assertTrue(loginPage.getMainHeadingText().contains("Swag Labs"), "Expected Heading Text is not displayed");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void sortProductsByLowerPrice() {
        productsPage.getFirstLowPriceProduct();
        Assert.assertEquals(productsPage.getNameOfPickedProduct(), dataProp.getProperty("firstLowPriceProductName"));
        Assert.assertEquals(productsPage.getPriceOfPickedProduct(), dataProp.getProperty("firstLowPriceProductPrice"));
    }
    @Test(priority = 2)
    public void sortProductsByHigherPrice() {
        productsPage.getFirstHighPriceProduct();
        Assert.assertEquals(productsPage.getNameOfPickedProduct(), dataProp.getProperty("firstHighPriceProductName"));
        Assert.assertEquals(productsPage.getPriceOfPickedProduct(), dataProp.getProperty("firstHighPriceProductPrice"));
    }
}
