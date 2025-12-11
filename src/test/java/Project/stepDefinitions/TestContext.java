package Project.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    public WebDriver driver;

    public TestContext() {
        String browser = System.getProperty("browser", "Chrome");
        String headlessProp = System.getProperty("headless", "true"); // Lee el valor como String
        boolean isHeadless = headlessProp.equalsIgnoreCase("true");

        switch (browser.toLowerCase()) {
                case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");

                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080"); // Recomendado para Headless
                    }

                    Map<String, Object> prefs = new HashMap<>();
                        prefs.put("profile.password_manager_leak_detection", false);
                        options.setExperimentalOption("prefs", prefs);

                    this.driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();


                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080"); // Recomendado para Headless
                }

                this.driver = new EdgeDriver(edgeOptions);

                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                this.driver = new OperaDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                this.driver = new InternetExplorerDriver();
                break;
            case "safari":
                System.out.println("ADVERTENCIA: Safari en Windows no es compatible. Usando Chrome por defecto.");
                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        this.driver.manage().window().maximize();
    }
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
