package com.company.sqa.ui.hooks;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class UiHooks {
    private static final Logger logger = LogManager.getLogger(UiHooks.class);

    @Before("@ui")
    public void beforeUiScenario(Scenario scenario) {
        logger.info("Starting UI Scenario: '{}'", scenario.getName());
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();
        DriverManager.initDriver(browser, headless);
    }

    @After("@ui")
    public void afterUiScenario(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    logger.warn("Scenario '{}' FAILED. Capturing failure screenshot...", scenario.getName());
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "failure-screenshot");
                    Allure.addAttachment("Scenario Failure Screenshot", new ByteArrayInputStream(screenshot));
                    DriverManager.quitDriver();
                }
            } catch (Exception e) {
                logger.error("Failed to capture screenshot during scenario teardown", e);
                DriverManager.quitDriver();
            }
        }
    }
}
