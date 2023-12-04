package com.mobileautomation.nativeapp.pages;

import com.mobileautomation.utils.PageObject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * HomePage class represents the functionalities and elements of the YouTube home page in the application.
 */
public class HomePage extends PageObject {
    // Element locators
    private By permissionDenyButtonLocator = By.id("com.android.permissioncontroller:id/permission_deny_button");
    private AppiumBy searchButtonLocator = new AppiumBy.ByAccessibilityId("Search");
    private By searchFieldLocator = By.id("com.google.android.youtube:id/search_edit_text");

    /**
     * Constructor for HomePage.
     * @param driver The AndroidDriver object used for interacting with the browser or app.
     */
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public WebElement waitForPermissionDenyButton() {
        return waitForElementVisibility(permissionDenyButtonLocator, 10);
    }

    public void clickPermissionDenyButton() {
        clickElement(permissionDenyButtonLocator);
    }

    public WebElement waitForSearchButton() {
        return waitForElementVisibility(searchButtonLocator, 10);
    }

    public void clickSearchButton() {
        clickElement(searchButtonLocator);
    }

    public WebElement waitForSearchField() {
        return waitForElementVisibility(searchFieldLocator, 10);
    }

    public void enterKeyword(String inputValue) {
        enterText(searchFieldLocator, inputValue);
    }

}
