package com.mobileautomation.webapp.pages;

import com.mobileautomation.utils.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * BasePage class represents the header and footer functionalities and elements for demoblaze web page.
 */
public class BasePage extends PageObject {
    // Element locators
    private By loginButtonLocator = By.xpath("//*[@id=\"login2\"]");
    private By loginTitleLocator = By.xpath("//*[@id=\"logInModalLabel\"]");
    private By loginUsernameInputLocator = By.xpath("//*[@id=\"loginusername\"]");
    private By loginPasswordInputLocator = By.xpath("//*[@id=\"loginpassword\"]");
    private By submitLoginButtonLocator = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    private By loggedInUsernameLocator = By.xpath("//*[@id=\"nameofuser\"]");

    /**
     * Constructor for BasePage.
     * @param driver The AndroidDriver object used for interacting with the browser or app.
     */
    public BasePage(AndroidDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public void clickLoginButton() {
        clickElement(loginButtonLocator);
    }

    public WebElement waitForLoginTitle() {
        return waitForElementVisibility(loginTitleLocator, 10);
    }

    public void verifyLoginTitle() {
        verifyElementText(loginTitleLocator, "Log in");
    }

    public void enterLoginUsername(String inputValue) {
        enterText(loginUsernameInputLocator, inputValue);
    }

    public void enterLoginPassword(String inputValue) {
        enterText(loginPasswordInputLocator, inputValue);
    }

    public void submitLogin() {
        clickElement(submitLoginButtonLocator);
    }

    public boolean waitForLoggedInUsername(String text) {
        waitForElementToDisappear(loginTitleLocator, 10);
        return waitForElementInnerTextToBe(loggedInUsernameLocator, text, 10);
    }

    public void verifyLoggedInUsername(String expectText) {
        verifyElementText(loggedInUsernameLocator, expectText);
    }
}
