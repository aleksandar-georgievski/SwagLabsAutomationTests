package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutStepTwoPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='cart_list']//div[@class='cart_item']")
    List<WebElement> cartList;
    @FindBy(css = "#finish")
    WebElement finishButton;
    By cartItemsSelect = By.xpath("//div[@class='cart_list']//div[@class='cart_item']");
    By itemNameSelector = By.cssSelector(".inventory_item_name");

    public List<WebElement> getItemFromOverviewList() {
        waitForElementToAppear(cartItemsSelect);
        return cartList;
    }
    public boolean verifyProductDisplayFromOverviewList(String productName) {
        return existsInListInCartPage(getItemFromOverviewList(), itemNameSelector, productName);
    }
    public void redirectToSuccessPage() {
        finishButton.click();
    }
}
