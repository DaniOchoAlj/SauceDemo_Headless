package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SauceDemoHomePage {
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginAlertResult = By.className("error-message-container");

    public SauceDemoHomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method receives 2 params to log in successfully
     * @param username
     * @param password
     * @return Homepage with user logged in
     */
    public SwagLabsPage userLogin(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(usernameField)));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new SwagLabsPage(driver);
    }

    /**
     * Method receives 2 params (locked out user) and attempting to log in but fails
     * @param lockedUser
     * @param lockedPassword
     * @return Alert message indicating the user is locked out
     */
    public String lockedUserLogin(String lockedUser, String lockedPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(usernameField)));
        driver.findElement(usernameField).sendKeys(lockedUser);
        driver.findElement(passwordField).sendKeys(lockedPassword);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginAlertResult)));
        return driver.findElement(loginAlertResult).getText();
    }
}
