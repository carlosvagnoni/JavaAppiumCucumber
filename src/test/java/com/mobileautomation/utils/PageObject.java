package com.mobileautomation.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * PageObject class encapsulates common methods and functionalities used across multiple pages.
 */
public class PageObject {
    private static final Logger logging = Logger.getLogger(PageObject.class);
    private final AndroidDriver driver;

    /**
     * Constructor for PageObject.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public PageObject(AndroidDriver driver) {
        this.driver = driver;
    }

    // Selenium methods for common interactions with web elements
    public void clickElement(By elementLocator) {
        handleException(() -> {
            driver.findElement(elementLocator).click();
            return null;
        });
    }

    public WebElement waitForElementVisibility(By elementLocator, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        });

    }

    public Boolean waitForElementToDisappear(By elementLocator, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        });
    }

    public Boolean waitForElementInnerTextToBe(By elementLocator, String expectedText, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElementLocated(elementLocator, expectedText));
        });
    }

    public void verifyElementText(By elementLocator, String expectedText) {
        handleException(() -> {
            String actualText = driver.findElement(elementLocator).getText();
            try {
                assert actualText.equals(expectedText);
            } catch (AssertionError e) {
                String errorMessage = "Expected text to be '" + expectedText + "', but was '" + actualText + "'";
                logging.error(new AssertionError(errorMessage, e));
                throw new AssertionError(errorMessage, e);
            }
            return null;
        });
    }

    public void verifyElementContentDesc(By elementLocator, String expectedText) {
        handleException(() -> {
            String contentDesc = driver.findElement(elementLocator).getAttribute("content-desc");
            try {
                assert contentDesc.contains(expectedText);
            } catch (AssertionError e) {
                String errorMessage = "Expected '" + expectedText + "' to be contained within '" + contentDesc + "', but it was not found.";
                logging.error(new AssertionError(errorMessage, e));
                throw new AssertionError(errorMessage, e);
            }
            return null;
        });
    }

    public void enterText(By elementLocator, String text) {
        handleException(() -> {
            driver.findElement(elementLocator).sendKeys(text);
            return null;
        });
    }

    public void simulateEnter() {
        handleException(() -> {
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));
            return null;
        });
    }

    public void waitForSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles exceptions and logs relevant information for various WebDriver interactions.
     *
     * @param action The action to be performed.
     * @param <T>    Type of the return value.
     * @return Returns the result of the action if successful.
     */
    public <T> T handleException(Supplier<T> action) {
        try {
            return action.get();
        } catch (NoSuchElementException e) {
            logging.debug("Element not found.", e);
            throw new NoSuchElementException("Element not found.", e);
        } catch (StaleElementReferenceException e) {
            logging.debug("Element not visible.", e);
            throw new StaleElementReferenceException("Element not visible.", e);
        } catch (ElementClickInterceptedException e) {
            logging.debug("Element click intercepted.", e);
            throw new ElementClickInterceptedException("Element click intercepted.", e);
        } catch (ElementNotInteractableException e) {
            logging.debug("Element not interactable.", e);
            throw new ElementNotInteractableException("Element not interactable.", e);
        } catch (TimeoutException e) {
            logging.debug("Timeout expired.", e);
            throw new TimeoutException("Timeout expired.", e);
        } catch (NoAlertPresentException e) {
            logging.debug("Alert not found.", e);
            throw new NoAlertPresentException("Alert not found.", e);
        } catch (WebDriverException e) {
            logging.debug("WebDriver exception occurred.", e);
            throw new WebDriverException("WebDriver exception occurred.", e);
        }
    }
}