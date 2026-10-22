package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final Logger logger = LogManager.getLogger(getClass());
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        int timeoutSeconds = ConfigReader.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        WebElement element = waitForClickable(locator);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (Exception e) {
            logger.warn("Standard click failed for {}, falling back to JavaScript click: {}", locator, e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        logger.info("Typing '{}' into element: {}", text, locator);
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    protected void uploadFile(By locator, String filePath) {
        logger.info("Uploading file '{}' to element: {}", filePath, locator);
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File to upload not found: " + file.getAbsolutePath());
        }
        WebElement element = driver.findElement(locator);
        element.sendKeys(file.getAbsolutePath());
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitForUrlContains(String partialUrl) {
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (Exception e) {
            logger.warn("Timeout waiting for URL to contain '{}'. Current URL: {}", partialUrl, driver.getCurrentUrl());
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
