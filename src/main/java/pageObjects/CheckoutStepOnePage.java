package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutStepOnePage extends AbstractComponent {
    WebDriver driver;
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstName;
    @FindBy(id = "last-name")
    private WebElement lastName;
    @FindBy(id = "postal-code")
    private WebElement postalCode;
    @FindBy(id = "continue")
    WebElement continueButton;
    @FindBy(css = ".title")
    WebElement cartPageTitle;
    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement checkoutStepOneErrorMessage;

    public String getCartPageTitle() {
        return cartPageTitle.getText();
    }
    public void redirectOnCheckoutStepTwoPage() {
        continueButton.click();
    }
    public String checkForFirstNamePlaceholderText() {
        return firstName.getAttribute("placeholder");
    }
    public String checkForLastNamePlaceholderText() {
        return lastName.getAttribute("placeholder");
    }
    public String checkForPostalCodePlaceholderText() {
        return postalCode.getAttribute("placeholder");
    }
    public String getCheckoutStepOneErrorMessage(){
        return checkoutStepOneErrorMessage.getText();
    }
    public void completeBillingPlaceholders(String firstNameText, String lastNameText, String postalCodeText) {
        firstName.sendKeys(firstNameText);
        lastName.sendKeys(lastNameText);
        postalCode.sendKeys(postalCodeText);
        continueButton.click();
    }
}
