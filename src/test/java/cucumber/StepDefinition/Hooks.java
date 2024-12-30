package cucumber.StepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.util.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hooks {

    private static WebDriver driver;
    public Scenario scenario;
    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */

    public void openBrowser(Scenario scenario) throws Exception {

        System.out.println("openBrowser"+scenario.toString());

        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.geolocation", 2);
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.prompt_for_download", false);
        chromePreferences.put("download.directory_upgrade", true);
        chromePreferences.put("download.default_directory", System.getProperty("user.dir") + "/src/test/resources/downloads");
        chromePreferences.put("safebrowsing.enabled", false);
        chromePreferences.put("plugins.always_open_pdf_externally", true);
        chromePreferences.put("plugins.plugins_disabled", new ArrayList<String>(){{ add("Chrome PDF Viewer"); }});
        chromePreferences.put("credentials_enable_service", false);
        chromePreferences.put("password_manager_enabled", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setExperimentalOption("prefs", chromePreferences);

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withVerbose(true)
                .build();



        driver = new ChromeDriver(chromeOptions);


        //driver = new ChromeDriver();
    }


    public static WebDriver getDriver() {
        return driver;
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }

    @AfterStep
    public void screenshot(Scenario scenario){
        scenario.write("Current Page URL is " + driver.getCurrentUrl());
        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
