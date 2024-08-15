package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(className = "app_logo")
    WebElement mainHeading;
    @FindBy(className = "error-message-container")
    WebElement usernameAndPasswordWarningMessage;

    public void enterUsername(String usernameText) {
        username.sendKeys(usernameText);
    }
    public void enterPassword(String passwordText) {
        password.sendKeys(passwordText);
    }
    public void clickOnLoginButton() {
        loginButton.click();
    }
    public String getMainHeadingText() {
        return mainHeading.getText();
    }
    public String getUsernameAndPasswordWarningText() {
        return usernameAndPasswordWarningMessage.getText();
    }

    //Complete Valid Login
    public void completeLogin(String usernameText, String passwordText) {
        username.sendKeys(usernameText);
        password.sendKeys(passwordText);
        loginButton.click();
    }
}
