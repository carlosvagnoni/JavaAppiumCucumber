package com.mobileautomation.nativeapp.steps;

import com.mobileautomation.nativeapp.pages.HomePage;
import com.mobileautomation.nativeapp.pages.SearchResultsPage;
import com.mobileautomation.nativeapp.pages.VideoPage;
import com.mobileautomation.hooks.Hooks;
import com.mobileautomation.utils.DriverManager;
import com.mobileautomation.webapp.steps.LoginSteps;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * This class contains Cucumber steps for user play video functionality on YouTube.
 */
public class PlayVideoSteps {
        private static final Logger logging = Logger.getLogger(LoginSteps.class);
        private static AndroidDriver driver;
        private String keyword;
        private HomePage homePage;
        private SearchResultsPage searchResultsPage;
        private VideoPage videoPage;
        public static Scenario currentScenario;

        public PlayVideoSteps() {
                this.driver = DriverManager.getDriver();
                this.homePage = new HomePage(driver);
                this.searchResultsPage = new SearchResultsPage(driver);
                this.videoPage = new VideoPage(driver);
        }

        @Given("the user is using the YouTube application")
        public void theUserIsUsingTheYoutubeApplication() {
                homePage.waitForPermissionDenyButton();
                homePage.clickPermissionDenyButton();
                byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                currentScenario.attach(screenshotAs, "image/png", "the user is using the YouTube application.");
        }

        @When("the user performs a search with the keyword {string}")
        public void theUserPerformsASearchWithTheKeyword(String keyword) {
                this.keyword = keyword;
                homePage.waitForSearchButton();
                homePage.clickSearchButton();
                homePage.waitForSearchField();
                homePage.enterKeyword(keyword);
                homePage.simulateEnter();
        }

        @And("a video is selected from the search results")
        public void aVideoIsSelectedFromTheSearchResults() {
                searchResultsPage.waitForOpenAIVideo();
                byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                currentScenario.attach(screenshotAs, "image/png", "a video is selected from the search results.");
                searchResultsPage.clickOpenAIVideo();
        }

        @Then("the selected video is played")
        public void theSelectedVideoIsPlayed() {
                videoPage.waitForSubscribeButton();
                videoPage.verifySubscribeButtonContentDesc(keyword);
                byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                currentScenario.attach(screenshotAs, "image/png", "the selected video is played.");
        }
}
