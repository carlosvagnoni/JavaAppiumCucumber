package com.mobileautomation.hooks;

import com.mobileautomation.nativeapp.steps.PlayVideoSteps;
import com.mobileautomation.utils.DriverManager;
import com.mobileautomation.webapp.steps.LoginSteps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * This class contains setup and teardown methods (hooks) for scenarios in a test suite.
 * It initializes the AndroidDriver and handles actions before and after scenario execution.
 */
public class Hooks {
    public static AndroidDriver driver;
    private static final Logger logging = Logger.getLogger(Hooks.class);
    private URL appiumServerUrl;

    @Before("@nativeapp")
    public void beforeScenarioNativeApp(Scenario scenario) {
        logging.info("Running SCENARIO: " + scenario.getName());
        PlayVideoSteps.currentScenario = scenario;

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("R52R30KTCVH");
        options.setAppPackage("com.google.android.youtube");
        options.setAppActivity("com.google.android.youtube.HomeActivity");

        try {
            appiumServerUrl = new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            logging.error("The URL structure is not correct.");
        }

        AndroidDriver driver = new AndroidDriver(appiumServerUrl, options);

        DriverManager.setDriver(driver);
    }

    @Before("@webapp")
    public void beforeScenarioWebApp(Scenario scenario) {
        logging.info("Running SCENARIO: " + scenario.getName());
        LoginSteps.currentScenario = scenario;

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("R52R30KTCVH");
        options.setOrientation(ScreenOrientation.LANDSCAPE);
        options.withBrowserName("Chrome");
        options.setChromedriverArgs(Arrays.asList("--incognito"));

        try {
            appiumServerUrl = new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            logging.error("The URL structure is not correct.");
        }

        AndroidDriver driver = new AndroidDriver(appiumServerUrl, options);

        driver.get("https://www.demoblaze.com");

        DriverManager.setDriver(driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        logging.info("Finished SCENARIO: " + scenario.getName());
        driver = DriverManager.getDriver();

        switch (scenario.getStatus()) {
            case SKIPPED -> logging.info("One or more steps of this scenario was passed over during testing.");
            case PASSED -> logging.info("The scenario was tested successfully.");
            case FAILED -> {
                logging.error("One or more steps of this scenario failed.");
                byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotAs, "image/png", scenario.getName());
                driver.quit();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
