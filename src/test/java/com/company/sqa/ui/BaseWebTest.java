package com.company.sqa.ui;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseWebTest {
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUp(@Optional String browser, @Optional String headless) {
        String targetBrowser = (browser != null && !browser.isEmpty()) ? browser : ConfigReader.getBrowser();
        boolean isHeadless = (headless != null && !headless.isEmpty()) ? Boolean.parseBoolean(headless) : ConfigReader.isHeadless();

        logger.info("Setting up WebDriver in BaseWebTest: browser={}, headless={}", targetBrowser, isHeadless);
        DriverManager.initDriver(targetBrowser, isHeadless);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Tearing down WebDriver in BaseWebTest");
        DriverManager.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
