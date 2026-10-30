package com.company.sqa.ui.steps;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.DriverManager;
import com.company.sqa.utils.SessionHelper;
import io.cucumber.java.id.Dengan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class CommonUiSteps {
    private static final Logger logger = LogManager.getLogger(CommonUiSteps.class);

    @Dengan("Pengguna membuka halaman {string}")
    public void penggunaMembukaHalaman(String pageName) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl;
        if (pageName.startsWith("http://") || pageName.startsWith("https://")) {
            targetUrl = pageName;
        } else if (pageName.startsWith("/")) {
            targetUrl = baseUrl + pageName;
        } else {
            targetUrl = baseUrl + "/management/" + pageName;
        }

        WebDriver driver = DriverManager.getDriver();
        if (targetUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, targetUrl);
        }

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl != null && currentUrl.equals(targetUrl)) {
            logger.info("Browser sudah berada di halaman target: {}", targetUrl);
            return;
        }

        logger.info("Pengguna membuka halaman target: {}", targetUrl);
        try {
            driver.get(targetUrl);
        } catch (TimeoutException te) {
            logger.warn("Page navigation timeout diabaikan demi kelancaran pengujian: {}", te.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("window.stop();");
            } catch (Exception ignored) {}
        }
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ignored) {}
    }
}

