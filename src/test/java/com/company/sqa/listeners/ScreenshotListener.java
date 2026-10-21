package com.company.sqa.listeners;

import com.company.sqa.utils.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(ScreenshotListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test '{}' failed. Checking for active WebDriver for Allure screenshot attachment...", result.getName());
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                captureScreenshotToAllure(driver, result.getName());
            } catch (Exception e) {
                logger.error("Failed to capture screenshot in ScreenshotListener", e);
            }
        }
    }

    @Attachment(value = "Failure Screenshot - {testName}", type = "image/png")
    public byte[] captureScreenshotToAllure(WebDriver driver, String testName) {
        logger.info("Attaching failure screenshot to Allure report for test: {}", testName);
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
