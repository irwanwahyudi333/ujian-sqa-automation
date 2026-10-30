package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentHomePage extends BasePage {

    // Header & Greeting
    private final By userGreeting = By.xpath("//*[contains(text(), 'Hai,')]");
    private final By subtitleText = By.xpath("//*[contains(text(), 'Have a nice day')]");
    private final By userPhoto = By.xpath("//img[@alt='photo user']");

    // Kehadiranmu Section
    private final By kehadiranmuTitle = By.xpath("//*[contains(text(), 'Kehadiranmu')]");
    private final By metricWfo = By.xpath("//img[@alt='wfo']");
    private final By metricLeave = By.xpath("//img[@alt='leave']");
    private final By metricSick = By.xpath("//img[@alt='sick']");
    private final By metricWfh = By.xpath("//img[@alt='wfh']");
    private final By metricOvertime = By.xpath("//img[@alt='overtime']");
    private final By metricLate = By.xpath("//img[@alt='late']");
    private final By metricEarlyLeave = By.xpath("//img[@alt='early_leave']");

    // Menu Pintas Grid
    private final By menuAbsensi = By.xpath("//img[@alt='Absensi']");
    private final By menuKoreksi = By.xpath("//img[@alt='Koreksi Absen']");
    private final By menuIzin = By.xpath("//img[@alt='Izin']");
    private final By menuLembur = By.xpath("//img[@alt='Lembur']");
    private final By menuCuti = By.xpath("//img[@alt='Cuti']");
    private final By menuSakit = By.xpath("//img[@alt='Sakit']");

    // Riwayat Singkat & Tombol Utama
    private final By historyTitle = By.xpath("//*[contains(text(), 'History Absensi')]");
    private final By historyMoreLink = By.xpath("//*[contains(text(), 'Selengkapnya')]");
    private final By btnAbsenMasuk = By.xpath("//button[contains(., 'Absen Masuk')]");

    public AbsentHomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Home: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isUserGreetingDisplayed() {
        return isElementDisplayed(userGreeting);
    }

    public String getUserGreetingText() {
        return getText(userGreeting);
    }

    public boolean isSubtitleDisplayed() {
        return isElementDisplayed(subtitleText);
    }

    public boolean isKehadiranmuSectionDisplayed() {
        return isElementDisplayed(kehadiranmuTitle);
    }

    public boolean areAttendanceMetricsDisplayed() {
        return isElementDisplayed(metricWfo) &&
               isElementDisplayed(metricLeave) &&
               isElementDisplayed(metricSick) &&
               isElementDisplayed(metricWfh) &&
               isElementDisplayed(metricOvertime) &&
               isElementDisplayed(metricLate) &&
               isElementDisplayed(metricEarlyLeave);
    }

    public boolean areQuickMenusDisplayed() {
        return isElementDisplayed(menuAbsensi) &&
               isElementDisplayed(menuKoreksi) &&
               isElementDisplayed(menuIzin) &&
               isElementDisplayed(menuLembur) &&
               isElementDisplayed(menuCuti) &&
               isElementDisplayed(menuSakit);
    }

    public boolean isHistorySectionDisplayed() {
        return isElementDisplayed(historyTitle);
    }

    public boolean isBtnAbsenMasukDisplayed() {
        return isElementDisplayed(btnAbsenMasuk);
    }

    public void clickHistoryMore() {
        logger.info("Clicking on 'Selengkapnya' history link");
        click(historyMoreLink);
    }

    public void clickMenuKoreksi() {
        logger.info("Clicking on 'Koreksi Absen' quick menu");
        click(menuKoreksi);
    }

    public void clickMenuIzin() {
        logger.info("Clicking on 'Izin' quick menu");
        click(menuIzin);
    }

    public void clickMenuLembur() {
        logger.info("Clicking on 'Lembur' quick menu");
        click(menuLembur);
    }

    public void clickMenuCuti() {
        logger.info("Clicking on 'Cuti' quick menu");
        click(menuCuti);
    }

    public void clickMenuSakit() {
        logger.info("Clicking on 'Sakit' quick menu");
        click(menuSakit);
    }
}

