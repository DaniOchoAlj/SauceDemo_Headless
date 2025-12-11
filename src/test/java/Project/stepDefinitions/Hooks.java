package Project.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {
    private final TestContext context;
    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUpBrowser() {
        this.context.driver.get("https://www.saucedemo.com/");
    }

    @AfterStep
    public void failedTestScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            Path screenshotDir = Paths.get("target/screenshots");
            Path DestinationPath = screenshotDir.resolve(scenario.getName()+"_" + ".png");

            try {
                    Files.createDirectories(screenshotDir);
                    File sourceFile = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.FILE);
                    Files.copy(sourceFile.toPath(),DestinationPath);
                }
                catch (IOException e)
                    {
                        System.out.println("Error while taking screenshot: "+e.getMessage());
                    }
                catch (Exception e)
                    {
                        System.out.println("Error while getting the driver: "+e.getMessage());
                    }

            final byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName() + " - failed");
        }
    }
    @After
    public void tearDown() {
        context.tearDown();
    }
}
