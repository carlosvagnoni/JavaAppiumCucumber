package com.mobileautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This class serves as the Test Runner for Mobile Automation Demo.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/java/com/mobileautomation/nativeapp/features",        // Location of feature files
                "src/test/java/com/mobileautomation/webapp/features"
        },
        glue = {                                                                // Glue between feature files and step definition files
                "com/mobileautomation/nativeapp/steps",
                "com/mobileautomation/webapp/steps",
                "com/mobileautomation/hooks"
        },
        plugin = {                                                              // Format and location of reports
                "pretty", "html:target/reports/mobileautomation.html",
                "json:target/reports/cucumber.json"
        }
)
public class TestRunner {
}
