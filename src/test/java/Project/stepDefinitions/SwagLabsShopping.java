package Project.stepDefinitions;

import Pages.KartPage;
import Pages.SauceDemoHomePage;
import Pages.SwagLabsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwagLabsShopping {
    private final WebDriver driver;
    private final SauceDemoHomePage sauceDemoHomePage;
    private final SwagLabsPage swagLabsPage;
    private final KartPage kartPage;

    public SwagLabsShopping(TestContext context) {
        this.driver = context.driver;
        this.sauceDemoHomePage = new SauceDemoHomePage(driver);
        this.swagLabsPage = new SwagLabsPage(driver);
        this.kartPage = new KartPage(driver);
    }

    private List<String> desiredItems;

    @Given("User is on the home page and goes to log in {string} and {string}")
    public void user_is_on_the_home_page_and_goes_to_log_in (String usr, String pwd) {
        sauceDemoHomePage.userLogin(usr, pwd);
        assertEquals(swagLabsPage.getTitle(), "Swag Labs", "Different titles");
    }

    @When("Search for products and adds them to the shopping kart")
    public void search_for_products_and_adds_them_to_the_shopping_kart() {
        desiredItems = swagLabsPage.findDesiredItems();
        swagLabsPage.addItemsToKart();
        swagLabsPage.clickShoppingKart();
    }
    @Then("They can be reviewed in the kart and  logout")
    public void they_can_be_reviewed_in_the_kart_and_logout() {
        List<String> addedItems = kartPage.getKartItems();
        assertEquals(addedItems, desiredItems,"ERROR: Listas diferentes");
        kartPage.removeItemsFromKart();
        assertTrue(kartPage.emptyKart(),"ERROR: Carrito no se vació");
        kartPage.logUserOut();
    }
}
