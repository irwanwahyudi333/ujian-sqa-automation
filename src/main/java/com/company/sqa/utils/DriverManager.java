package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static synchronized void initDriver() {
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();
        initDriver(browser, headless);
    }

    public static synchronized void initDriver(String browser, boolean headless) {
        if (driverThreadLocal.get() != null) {
            logger.info("Driver already initialized for current thread");
            return;
        }

        WebDriver driver;
        String selectedBrowser = (browser == null) ? "firefox" : browser.toLowerCase().trim();
        logger.info("Initializing WebDriver for browser: {} (headless: {})", selectedBrowser, headless);

        switch (selectedBrowser) {
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }

        int implicitWait = ConfigReader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
        logger.info("WebDriver initialized successfully for thread: {}", Thread.currentThread().getId());
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static synchronized void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                logger.info("Closing and quitting WebDriver for thread: {}", Thread.currentThread().getId());
                driver.quit();
            } catch (Exception e) {
                logger.warn("Exception during driver quit: {}", e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
