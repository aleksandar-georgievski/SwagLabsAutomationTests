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
        loginPage.login(testUserData.getValidUser().username(), testUserData.getValidUser().password());
        Assert.assertTrue(loginPage.getMainHeadingText().contains("Swag Labs"), "Expected Heading Text is not displayed");
    }
    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.login(testUserData.getInvalidUser().username(), testUserData.getInvalidUser().password());
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("usernameAndPasswordWarningText")));
    }
    @Test(priority = 3)
    public void verifyLoginWithoutProvidingAnyCredentials() {
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("usernameRequiredWarningText")));
    }
    @Test(priority = 4)
    public void verifyLoginProvidingOnlyUsername() {
        loginPage.enterUsername(testUserData.getValidUser().username());
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("passwordRequiredWarningText")));
    }
    @Test(priority = 5)
    public void verifyLoginProvidingOnlyPassword() {
        loginPage.enterPassword(testUserData.getValidUser().password());
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("usernameRequiredWarningText")));
    }
    @Test(priority = 6)
    public void verifyLoginWithValidUsernameAndInvalidPassword() {
        loginPage.login(testUserData.getValidUser().username(), testUserData.getInvalidUser().password());
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("usernameAndPasswordWarningText")));
    }
    @Test(priority = 7)
    public void verifyLoginWithInvalidUsernameAndValidPassword() {
        loginPage.login(testUserData.getInvalidUser().username(), testUserData.getValidUser().password());
        Assert.assertTrue(loginPage.getUsernameAndPasswordWarningText().contains(prop.getProperty("usernameAndPasswordWarningText")));
    }

}
