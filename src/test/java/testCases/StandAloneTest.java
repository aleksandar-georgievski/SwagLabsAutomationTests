package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        //Login Page
        String productName = "Sauce Labs Bike Light";
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String getLogoHeadingText = driver.findElement(By.className("app_logo")).getText();
        Assert.assertTrue(getLogoHeadingText.contains("Swag Labs"), "Expected Heading Text is not displayed");

        //Product Page
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inventory_list"))));
        List <WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
        WebElement prod = products.stream().filter(product ->
                product.findElement(By.cssSelector(".inventory_item_name")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        assert prod != null;
        prod.findElement(By.cssSelector(".btn_inventory")).click();

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        // Picked Product List & Checkout Page 1
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"))));
        List <WebElement> cartProductsList = driver.findElements(By.cssSelector(".inventory_item_name"));
        boolean cartList = cartProductsList.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(cartList);
        driver.findElement(By.id("checkout")).click();


        //Checkout Page 2
        driver.findElement(By.id("first-name")).sendKeys("Aleksandar");
        driver.findElement(By.id("last-name")).sendKeys("Georgievski");
        driver.findElement(By.id("postal-code")).sendKeys("1000");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.cssSelector("#finish")).click();

        // Success Page
        String orderCompletedMessage = driver.findElement(By.cssSelector(".complete-header")).getText();
        Assert.assertEquals(orderCompletedMessage, "Thank you for your order!");
        driver.findElement(By.id("back-to-products")).click();

        driver.quit();
    }
}
