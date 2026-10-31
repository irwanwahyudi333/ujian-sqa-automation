package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private static final Logger logger = LogManager.getLogger(SessionHelper.class);

    public static final String LIVE_SESSION_COOKIE_NAME = "hadir_web_magang";
    public static final String LIVE_SESSION_COOKIE_VALUE = "Fe26.2*1*26621929fd940687410714784c440df1a34559df670533e98bbaa48af2adfe77*RlyS-KRCb8gHTRCCEg2PTQ*Gapw-YvqRZS1Tc5CPmO5MwSiGJB2gyCHqVt2C9rv9WU2I3BYjgbHeghNIZUkcN4PuyRm4kIxwBMJku1OZFbI8g7_hUFHqKPC4NSQUYKrBQvsRS6-Yk-MZPbG0guZ03kmtRTHpZDQnfOysDE3-pBTPtsYzaFspHsmzfQahJPdv4KZppufgo5GGq5kG1jZxW2ZZbanDs8My-4gHXApOyXnREpT111PJJm3e2lGclcKOHHg84BbdVhquZDwieYJORYf12-qOyqQ2nUBHxNhOyuSg1VULe2McDrfsuV-4DGbfaoTmzsxLJeaa4NBDGpMNASxsmH7ewyq18U9NQU0ZYJLo2cQ8_vK9a-Jk6pnXsU69_M-bh0Vt-ZtKlmq5SsFYjEi5fbI-rsNScE2G-CQDHSk0Fm__jovnt2DOLjpxlLpQWRyG-XER01T3a7RvnfV-8vjUOT_IubDGo2lr3lEpnbem38QHn01YCJOTgM1z53NNB82Ibz9xa_wf5wcwHMK8WY2-Yw_oTQmo2_exhQomG1zAIMU1iy8ZNifdOSrrikhOLQrj_g1yWe-KMXMm9tOavUM7lCxM-7PUnb0OthnpAXkzY6xchjOvXkecMx1UuriIElKcaJyv6Lmgf2taW_BvSGx2m54MTsjvJAWjKEC4TQNQbyIgiyKWMhrjH8gxuZLUJI*1793921990183*a39ecf4cec162cc738092bf4e8d90b407c815251161ec048879f99dbf2507256*pZ-QbDmaq1yhHFRmDwIoPlePnJDOFhCbxgelcSkBO8w~2";

    public static void ensureAuthenticated(WebDriver driver, String targetUrl) {
        if (driver == null || targetUrl == null) return;

        if (targetUrl.contains("magang.dikahadir.com")) {
            try {
                // If current domain is not yet on magang.dikahadir.com, navigate to login page first to set domain context
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl == null || !currentUrl.contains("magang.dikahadir.com")) {
                    driver.get("https://magang.dikahadir.com/robots.txt");
                }
                
                // Check if cookie already exists
                Cookie existingCookie = driver.manage().getCookieNamed(LIVE_SESSION_COOKIE_NAME);
                if (existingCookie == null) {
                    Cookie authCookie = new Cookie.Builder(LIVE_SESSION_COOKIE_NAME, LIVE_SESSION_COOKIE_VALUE)
                            .domain("magang.dikahadir.com")
                            .path("/")
                            .isSecure(true)
                            .isHttpOnly(true)
                            .build();
                    driver.manage().addCookie(authCookie);
                    logger.info("Live session cookie '{}' berhasil diinjeksi ke browser WebDriver", LIVE_SESSION_COOKIE_NAME);
                }
            } catch (Exception e) {
                logger.warn("Peringatan: Gagal menginjeksi cookie autentikasi live: {}", e.getMessage());
            }
        }
    }
}

