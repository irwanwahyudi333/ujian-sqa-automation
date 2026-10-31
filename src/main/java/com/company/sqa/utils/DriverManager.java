package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
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

        WebDriver driver = null;
        String selectedBrowser = (browser == null) ? "firefox" : browser.toLowerCase().trim();
        logger.info("Initializing WebDriver for browser: {} (headless: {})", selectedBrowser, headless);

        switch (selectedBrowser) {
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }

                File cachedGecko = new File("/home/irwan/.cache/selenium/geckodriver/linux64/0.37.1/geckodriver");
                if (cachedGecko.exists()) {
                    System.setProperty("webdriver.gecko.driver", cachedGecko.getAbsolutePath());
                }

                // Inisialisasi dengan retry dan custom port bila PortProber gagal
                Exception lastException = null;
                for (int attempt = 1; attempt <= 8; attempt++) {
                    try {
                        int freePort = findAvailablePort();
                        GeckoDriverService.Builder serviceBuilder = new GeckoDriverService.Builder();
                        if (cachedGecko.exists()) {
                            serviceBuilder.usingDriverExecutable(cachedGecko);
                        }
                        if (freePort > 0) {
                            serviceBuilder.usingPort(freePort);
                        }
                        GeckoDriverService service = serviceBuilder.build();
                        driver = new FirefoxDriver(service, firefoxOptions);
                        break;
                    } catch (Exception e) {
                        lastException = e;
                        logger.warn("Percobaan inisialisasi FirefoxDriver ke-{} gagal: {}", attempt, e.getMessage());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ignored) {}
                    }
                }
                if (driver == null) {
                    throw new RuntimeException("Gagal menginisialisasi FirefoxDriver setelah 8 percobaan", lastException);
                }
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.warn("Could not maximize window in headless mode: {}", e.getMessage());
        }

        driverThreadLocal.set(driver);
        logger.info("WebDriver initialized successfully for thread: {}", Thread.currentThread().getId());
    }

    private static int findAvailablePort() {
        int min = 12000;
        int max = 19000;
        for (int i = 0; i < 5; i++) {
            int candidate = min + (int) (Math.random() * (max - min));
            try (ServerSocket socket = new ServerSocket(candidate)) {
                socket.setReuseAddress(true);
                return candidate;
            } catch (IOException ignored) {}
        }
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        } catch (IOException e) {
            return 14500 + (int) (Math.random() * 2000);
        }
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
