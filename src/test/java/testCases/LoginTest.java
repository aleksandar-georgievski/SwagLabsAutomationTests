package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class LoginTest extends Base {
    WebDriver driver;
    public LoginPage loginPage;
    public LoginTest() {
        super();
    }
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
        loginPage = new LoginPage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyLoginWithValidCredentials() {
        loginPage.completeLogin(prop.getProperty("validUsername"), prop.getProperty("validPassword"));
        Assert.assertTrue(loginPage.getMainHeadingText().contains("Swag Labs"), "Expected Heading Text is not displayed");
    }
    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.completeLogin(dataProp.getProperty("invalidUsername"), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("usernameAndPasswordWarningText")));
    }
    @Test(priority = 3)
    public void verifyLoginWithoutProvidingAnyCredentials() {
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("usernameRequiredWarningText")));
    }
    @Test(priority = 4)
    public void verifyLoginProvidingOnlyUsername() {
        loginPage.enterUsername(prop.getProperty("validUsername"));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("passwordRequiredWarningText")));
    }
    @Test(priority = 5)
    public void verifyLoginProvidingOnlyPassword() {
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("usernameRequiredWarningText")));
    }
    @Test(priority = 6)
    public void verifyLoginWithValidUsernameAndInvalidPassword() {
        loginPage.completeLogin(prop.getProperty("validUsername"), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("usernameAndPasswordWarningText")));
    }
    @Test(priority = 7)
    public void verifyLoginWithInvalidUsernameAndValidPassword() {
        loginPage.completeLogin(dataProp.getProperty("invalidUsername"), prop.getProperty("validPassword"));
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(dataProp.getProperty("usernameAndPasswordWarningText")));
    }

}
