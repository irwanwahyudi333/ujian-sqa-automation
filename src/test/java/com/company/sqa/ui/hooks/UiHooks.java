package com.company.sqa.ui.hooks;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;
import java.util.List;

public class UiHooks {
    private static final Logger logger = LogManager.getLogger(UiHooks.class);

    @Before("@ui")
    public void beforeUiScenario(Scenario scenario) {
        logger.info("Starting UI Scenario: '{}'", scenario.getName());
        String uiUrl = ConfigReader.getUiBaseUrl();
        if (uiUrl != null && (uiUrl.contains("localhost") || uiUrl.contains("127.0.0.1"))) {
            if (!com.company.sqa.utils.LocalWebServer.isRunning()) {
                com.company.sqa.utils.LocalWebServer.start();
            }
        }
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();
        try {
            DriverManager.initDriver(browser, headless);
        } catch (Exception e) {
            logger.warn("Percobaan inisialisasi driver pertama gagal, mencoba ulang: {}", e.getMessage());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) {}
            DriverManager.initDriver(browser, headless);
        }
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
                }
                // Bersihkan dialog modal yang mungkin masih terbuka agar skenario berikutnya bersih
                try {
                    List<WebElement> cancelButtons = driver.findElements(By.xpath("//div[@role='dialog']//button[contains(text(), 'Batal') or contains(text(), 'Tutup') or contains(text(), 'Tidak')]"));
                    for (WebElement btn : cancelButtons) {
                        if (btn.isDisplayed()) {
                            btn.click();
                        }
                    }
                } catch (Exception ignored) {}
            } catch (Exception e) {
                logger.error("Failed during scenario teardown", e);
            }
        }
    }

    @io.cucumber.java.AfterAll
    public static void afterAllUiScenarios() {
        logger.info("All UI scenarios completed. Quitting WebDriver...");
        DriverManager.quitDriver();
    }
}
