package Pages;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;

public class SwagLabsPage {
    private WebDriver driver;
    private By appLogoTittle = By.cssSelector("div.app_logo");
    private By tShirts = By.xpath("//div[contains(text(),'T-Shirt')]");
    private By itemsAddButtons = By.cssSelector("button[id*='t-shirt']");
    private By goToKart = By.id("shopping_cart_container");

    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method scans homepage's user logged in
     * @return Page's title text
     */
    public String getTitle() {
        return driver.findElement(appLogoTittle).getText();
    }

    /**
     * Searches elements according to the hardcoded 'T-Shirt' criteria
     * @return list of found elements according to the search criteria
     */
    public List<String> findDesiredItems() {
        List<WebElement> tShirtsList = driver.findElements(tShirts);
        List<String> foundItemsList = new ArrayList<>();

        for (WebElement tShirt : tShirtsList) {
            foundItemsList.add(tShirt.getText());
        }
        return foundItemsList;
    }

    /**
     * Adds to the shopping kart, all the found items according to the search criteria
     */
    public void addItemsToKart() {
        List<WebElement> addButtons = driver.findElements(itemsAddButtons);
        for (WebElement addButton : addButtons) {
            addButton.click();
        }
    }

    /**
     * Clicks on the shopping kart icon
     * @return shopping kart page
     */
    public KartPage clickShoppingKart() {
        driver.findElement(goToKart).click();
        return new KartPage(driver);
    }
}
