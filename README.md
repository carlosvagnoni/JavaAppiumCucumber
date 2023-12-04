# Automated Mobile Testing with Java, Appium, and Cucumber 🤖☕

This project provides a structure and tools for automated mobile testing using Java, Appium, and Cucumber, following Behavior-Driven Development (BDD) best practices and employing the Page Object Model design pattern.

## Testing YouTube Mobile App and Demoblaze WebApp 🧪

This suite of tests is aimed at validating scenarios for both a native mobile application, specifically YouTube, and a web application, focusing on [demoblaze.com](https://www.demoblaze.com).

Refer to `nativeapp/features` and `webapp/features` directories to explore and manage the scenarios for both.

## Table of Contents 📑
- [Requirements](#requirements)
- [Folder Structure](#folder-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Test Execution](#test-execution)
- [Contact](#contact)

## <a id="requirements">Requirements 📋</a>

- JDK 21
- Appium 2.2.2
- Appium UiAutomator2 Driver 2.34.2
- Java-Client 9.0.0
- Selenium 4.15.0
- Cucumber 7.14.1

## <a id="folder-structure">Folder Structure 📂</a>

- **pom.xml:** Maven configuration file specifying project dependencies.
- **run.bat:** Batch script for Windows environment execution.

### Directory "src/test/java/com/mobileautomation"

- **TestRunner.java:** Cucumber test runner class.

- **hooks/**
  - **Hooks.java:** Cucumber hooks for setup and teardown.

- **nativeapp/**
  - **features/**
    - **PlayVideo.feature:** Feature file for playing videos on YouTube.
  - **pages/**
    - Directory containing Page Object Model classes for YouTube app.
  - **steps/**
    - **PlayVideoSteps.java:** Step definitions for playing videos on YouTube.

- **utils/**
  - **DriverManager.java:** Utility class for managing drivers.
  - **PageObject.java:** Base class for Page Object Model.

- **webapp/**
  - **features/**
    - **Login.feature:** Feature file for demoblaze login.
  - **pages/**
    - **BasePage.java:** Page class for demoblaze header and footer.
  - **steps/**
    - **LoginSteps.java:** Step definitions for demoblaze login.

### Directory "src/test/resources"

- **log4j.properties:** Configuration file for logging.

## <a id="installation">Installation 🛠️</a>

1. Clone this repository:

    ```bash
    git clone https://github.com/carlosvagnoni/JavaAppiumCucumber.git
    cd JavaAppiumCucumber
    ```

2. Compile the project:

    ```bash
    mvn clean compile
    ```

## <a id="configuration">Configuration ⚙️</a>

- Ensure that the Appium server is running.
- Ensure the device is connected (for real devices) or the emulator is running (for emulated devices) before starting the tests.

## <a id="test-execution">Test Execution ▶️</a>

Run all the tests:

```bash
mvn test
```

Open report:

```bash
start "" "target\reports\mobileautomation.html"
```

**NOTE:**

- Set up the respective environment variables beforehand.
- On Windows environments, you can directly execute the `run.bat` file.

## <a id="contact">Contact 📧</a> 

If you have any questions or suggestions, feel free to contact me through my social media accounts.

Thank you for your interest in this project!