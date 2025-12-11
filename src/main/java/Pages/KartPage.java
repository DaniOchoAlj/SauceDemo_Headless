package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class KartPage {
    private WebDriver driver;
    private By hamburgerMenu = By.id("react-burger-menu-btn");
    private By logoutOption = By.id("logout_sidebar_link");
    private By shoppingKartItems = By.cssSelector("div.inventory_item_name");
    private By removeItemsButtons = By.cssSelector("button[id*='remove']");
    private By KartBadge = By.cssSelector("span.shopping_cart_badge");

    public KartPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets all the items found in the shopping kart page
     * @return List of all elements added in the shopping kart
     */
    public List<String> getKartItems() {
        List<WebElement> kartAddedItems = driver.findElements(shoppingKartItems);
        List<String> kartItemsNames = new ArrayList<>();

        for (WebElement kartAddedItem : kartAddedItems) {
            kartItemsNames.add(kartAddedItem.getText());
        }
        return kartItemsNames;
    }

    /**
     * Removes all elements in the shopping kart
     */
    public void removeItemsFromKart () {
        List<WebElement> removeButtons = driver.findElements(removeItemsButtons);

        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    /**
     *
     * @return True or False if the shopping kart is empty
     */
    public boolean emptyKart () {
        List<WebElement> kartItemsQty = driver.findElements(KartBadge);
        return kartItemsQty.isEmpty();
    }

    /**
     * Logs user out
     * @return Homepage with no users logged in
     */
    public SauceDemoHomePage logUserOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(hamburgerMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption));
        driver.findElement(logoutOption).click();
        return new SauceDemoHomePage(driver);
    }
}
