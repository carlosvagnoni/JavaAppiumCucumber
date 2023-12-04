package com.mobileautomation.nativeapp.pages;

import com.mobileautomation.utils.PageObject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

/**
 * SearchResultsPage class represents the page displaying search results on YouTube.
 */
public class SearchResultsPage extends PageObject {
    // Element locators
    private AppiumBy openAIVideoLocator = new AppiumBy.ByAccessibilityId("OpenAI DevDay, Opening Keynote - 45 minutes - Go to channel - OpenAI - 2.1M views - Streamed 4 weeks ago - play video");

    /**
     * Constructor for SearchResultsPage.
     * @param driver The AndroidDriver object used for interacting with the browser or app.
     */
    public SearchResultsPage(AndroidDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public WebElement waitForOpenAIVideo() {
        return waitForElementVisibility(openAIVideoLocator, 10);
    }

    public void clickOpenAIVideo() {
        clickElement(openAIVideoLocator);
    }
}
