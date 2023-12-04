package com.mobileautomation.nativeapp.pages;

import com.mobileautomation.utils.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * VideoPage class represents the page displaying a specific video on YouTube.
 */
public class VideoPage extends PageObject {
    // Element locators
    private By subscribeButtonLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Subscribe to OpenAI.\"]");

    /**
     * Constructor for VideoPage.
     * @param driver The AndroidDriver object used for interacting with the browser or app.
     */
    public VideoPage(AndroidDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public WebElement waitForSubscribeButton() {
        return waitForElementVisibility(subscribeButtonLocator, 10);
    }

    public void verifySubscribeButtonContentDesc(String expectedText) {
        verifyElementContentDesc(subscribeButtonLocator, expectedText);
    }
}
