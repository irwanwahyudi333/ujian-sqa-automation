package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Unable to find config.properties in classpath");
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException ex) {
            logger.error("Failed to load config.properties", ex);
            throw new RuntimeException("Failed to load config.properties", ex);
        }
    }

    public static String getProperty(String key) {
        String systemVal = System.getProperty(key);
        if (systemVal != null && !systemVal.trim().isEmpty()) {
            return systemVal.trim();
        }
        String propVal = properties.getProperty(key);
        return propVal != null ? propVal.trim() : null;
    }

    public static String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return val != null ? val : defaultValue;
    }

    public static String getActiveEnvironment() {
        return getProperty("env", "qa").toLowerCase();
    }

    public static String getUiBaseUrl() {
        String env = getActiveEnvironment();
        String url = getProperty(env + ".ui.url");
        if (url != null && !url.isEmpty()) {
            return url;
        }
        return getProperty("ui.url", "https://magang.dikahadir.com");
    }

    public static String getApiBaseUrl() {
        String env = getActiveEnvironment();
        String url = getProperty(env + ".api.base.url");
        if (url != null && !url.isEmpty()) {
            return url;
        }
        return getProperty("api.base.url", "https://magang.dikahadir.com");
    }

    public static String getRegisterEmail() {
        return getProperty("register.email", "raxaf58877@backyr.com");
    }

    public static String getBrowser() {
        return getProperty("browser", "firefox");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "true"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("timeout.implicit", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("timeout.explicit", "15"));
    }
}
