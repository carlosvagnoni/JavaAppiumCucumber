package com.mobileautomation.utils;

import io.appium.java_client.android.AndroidDriver;

/**
 * DriverManager class manages the AndroidDriver instance used across the application.
 */
public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void setDriver(AndroidDriver androidDriver) {
        driver = androidDriver;
    }
}
