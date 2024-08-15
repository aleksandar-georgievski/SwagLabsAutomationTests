package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By cartItemsSelect = By.xpath("//div[@class='cart_list']//div[@class='cart_item']");
    By itemNameSelector = By.cssSelector(".inventory_item_name");
    @FindBy(xpath = "//div[@class='cart_list']//div[@class='cart_item']")
    List<WebElement> cartList;
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public List<WebElement> getCartProductsList() {
        waitForElementToAppear(cartItemsSelect);
        return cartList;
    }
    public boolean verifyProductDisplay(String productName) {
        return existsInListInCartPage(getCartProductsList(), itemNameSelector, productName);
    }
    public void redirectToCheckoutPage() {
        checkoutButton.click();
    }
















}


//    WebElement item = driver.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"));
//        Assert.assertTrue(item.isDisplayed());