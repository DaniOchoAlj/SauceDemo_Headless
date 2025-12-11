package Project.stepDefinitions;

import Pages.SauceDemoHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Login")
@Feature("Attempt to login with locked out user")
public class LoginLockedUser {
    private final WebDriver driver;
    private final SauceDemoHomePage sauceDemoHomePage;
    protected String alertResult;
    public LoginLockedUser(TestContext context) {
        this.driver = context.driver;
        this.sauceDemoHomePage = new SauceDemoHomePage(driver);
    }

    @Given("User is on the home page")
    @Story("Fail login when user is locked out")
    public void user_is_on_the_home_page() {
        //Before opens the browser and goes to the page;
    }
    @When("Attempts to login with blocked credentials {string} and {string}")
    @Step("Type username and password, and click on login button")
    public void Attempts_to_login_with_blocked_credentials (String usrlc,String pwdlc) {
        alertResult = sauceDemoHomePage.lockedUserLogin (usrlc,pwdlc);
    }
    @Then("Homepage shows the alert and login fails")
    @Step("Read alert message")
    public void homepage_shows_the_alert_and_login_fails() {
        assertEquals(alertResult,"Epic sadface: Sorry, this user has been locked out");
    }
}
