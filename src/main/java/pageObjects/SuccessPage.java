package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage extends AbstractComponent {
    WebDriver driver;
    public SuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".complete-header")
    WebElement successHeader;
    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public String verifySuccessHeaderMessage() {
        return successHeader.getText();
    }
    public void redirectToProductsPage() {
        backHomeButton.click();
    }
}
