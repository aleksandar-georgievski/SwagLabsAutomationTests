package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BasePage {
    WebDriver driver;
    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item")
    List <WebElement> productList;
    @FindBy(css = ".shopping_cart_link")
    WebElement clickOnShoppingCartIcon;
    @FindBy(css = ".inventory_container .inventory_list .inventory_item:first-child .inventory_item_img")
    WebElement selectFirstProductInProductsList;
    @FindBy(css = ".select_container")
    WebElement clickOnSelectContainer;
    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value='lohi']")
    WebElement selectLowToHigh;
    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value='hilo']")
    WebElement selectHighToLow;
    @FindBy(css = ".inventory_details_name")
    WebElement productName;
    @FindBy(css = ".inventory_details_price")
    WebElement productPrice;
    By byProductsListSelector = By.cssSelector(".inventory_list");
    By productItemNameSelector = By.cssSelector(".inventory_item_name");
    By addProductToCart = By.cssSelector(".btn_inventory");

    private List<WebElement> getProductList() {
        waitForElementToAppear(byProductsListSelector);
        return productList;
    }
    public WebElement getProductByName(String productName) {
        WebElement product = findInList(getProductList(), productItemNameSelector, productName);
        assert product != null;
        return product;
    }
    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(addProductToCart).click();
    }
    public void redirectToCartPage() {
        clickOnShoppingCartIcon.click();
    }

    public void getFirstLowPriceProduct() {
        clickOnSelectContainer.click();
        selectLowToHigh.click();
        selectFirstProductInProductsList.click();
    }
    public String getNameOfPickedProduct() {
         return productName.getText();
    }
    public String getPriceOfPickedProduct() {
        return productPrice.getText();
    }
    public void getFirstHighPriceProduct() {
        clickOnSelectContainer.click();
        selectHighToLow.click();
        selectFirstProductInProductsList.click();
    }
}
