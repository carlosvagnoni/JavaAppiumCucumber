package com.mobileautomation.webapp.steps;

import com.mobileautomation.hooks.Hooks;
import com.mobileautomation.nativeapp.pages.HomePage;
import com.mobileautomation.nativeapp.pages.SearchResultsPage;
import com.mobileautomation.nativeapp.pages.VideoPage;
import com.mobileautomation.utils.DriverManager;
import com.mobileautomation.webapp.pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

/**
 * This class contains Cucumber steps for user login functionality on demoblaze.com.
 */
public class LoginSteps {

    private static final Logger logging = Logger.getLogger(LoginSteps.class);
    private AndroidDriver driver;
    private String username;
    private String password;
    private BasePage basePage;

    // Represents the current scenario being executed
    public static Scenario currentScenario;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
        this.basePage = new BasePage(driver);
    }

    @Given("the user has signed up with credentials: {string}, {string}.")
    public void theUserHasSignedUpWithCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Given("the user is on the Login Page.")
    public void theUserIsOnLoginPage() {
        basePage.clickLoginButton();
        basePage.waitForLoginTitle();
        basePage.verifyLoginTitle();
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user is on the Login Page.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }

    @When("the user inputs their username and password into the form.")
    public void theUserInputsUsernameAndPassword() {
        basePage.enterLoginUsername(username);
        basePage.enterLoginPassword(password);
    }

    @When("the user clicks on the Submit button.")
    public void theUserClicksSubmitButton() {
        basePage.submitLogin();
    }

    @Then("the user should be logged in.")
    public void theUserShouldBeLoggedIn() {
        String expectedText = String.format("Welcome %s", username);
        basePage.waitForLoggedInUsername(expectedText);
        basePage.verifyLoggedInUsername(expectedText);
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user should be logged in.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }
}
