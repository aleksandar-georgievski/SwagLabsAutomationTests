package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
//    protected void waitForWebElementToAppear(WebElement findBy) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(findBy));
//    }
    public boolean matchListItem(WebElement item, By selector, String search) {
        return item.findElement(selector).getText().equalsIgnoreCase(search);
    }
    public WebElement findInList(List<WebElement> list, By selector, String search) {
        return list.stream()
                .filter(item -> matchListItem(item, selector, search))
                .findFirst()
                .orElse(null);
    }
    public boolean existsInListInCartPage(List<WebElement> list, By selector, String search) {
        return list.stream()
                .anyMatch(item -> matchListItem(item, selector, search));
    }
}
