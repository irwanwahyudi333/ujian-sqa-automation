package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model untuk Modul Monitoring HADIR (10 Halaman Target).
 * Didesain khusus untuk peran Auditor Monitoring / User Monitoring (role_id: monitoring).
 */
public class MonitoringPage extends BasePage {

    // Locators - Header & Title
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h1 | //h2 | //h3[contains(@class,'page-title')]");
    private final By roleIndicator = By.id("role-indicator");

    // Locators - Stat Cards (pada /monitoring/semua)
    private final By statTotalStaf = By.id("stat-total-staf");
    private final By statHadirOntime = By.id("stat-hadir-ontime");
    private final By statTerlambat = By.id("stat-terlambat");
    private final By statAbsen = By.id("stat-absen");

    // Locators - Filter & Search Bar
    private final By inputSearch = By.cssSelector("input#search, input[type='search'], input[name='search']");
    private final By inputStartDate = By.cssSelector("input#start-date, input[name='startDate']");
    private final By inputEndDate = By.cssSelector("input#end-date, input[name='endDate']");
    private final By btnSearch = By.cssSelector("button#btn-search, .btn-search");
    private final By btnReset = By.cssSelector("button#btn-reset, .btn-reset");
    private final By btnExport = By.cssSelector("button#btn-export, .btn-export");

    // Locators - Tabel & Paginasi
    private final By dataTable = By.cssSelector("table.table_base, #table-monitoring, table[aria-label='sticky table']");
    private final By tableHeaders = By.cssSelector("table.table_base thead th, thead th");
    private final By tableRows = By.cssSelector("#table-body tr, table.table_base tbody tr");
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");

    // Locators - Download Reports Generator
    private final By cardDownloadReport = By.id("download-report-card");
    private final By selectDepartemen = By.id("select-departemen");
    private final By btnDownloadReport = By.cssSelector("button#btn-download-report, button#btn-export-excel");
    private final By alertDownloadFeedback = By.id("alert-download-feedback");

    // Locators - Toast / Snackbar
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    // Locators - Potensi tombol aksi yang DILARANG pada akun read-only monitoring
    private final By forbiddenActionButtons = By.cssSelector(
            "#btn-simpan-tambah, #btn-konfirmasi-delete, .btn-validator-approve, " +
            "#btn-approve-validator, #btn-reject-validator, button[title='Hapus'], button[title='Edit']"
    );

    public MonitoringPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Membuka halaman relatif modul monitoring.
     * @param relativePath misal "/monitoring/semua", "/monitoring/cuti", "/monitoring/download-reports/kehadiran"
     */
    public void open(String relativePath) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String fullUrl = baseUrl + relativePath;
        logger.info("Membuka halaman monitoring: {}", fullUrl);

        if (fullUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, fullUrl);
        } else {
            try {
                driver.get(fullUrl);
            } catch (org.openqa.selenium.TimeoutException te) {
                logger.warn("Page navigation timeout diabaikan demi kelancaran pengujian: {}", te.getMessage());
                try {
                    ((JavascriptExecutor) driver).executeScript("window.stop();");
                } catch (Exception ignored) {}
            }
        }
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(dataTable),
                    ExpectedConditions.presenceOfElementLocated(btnDownloadReport),
                    ExpectedConditions.presenceOfElementLocated(inputSearch)
            ));
        } catch (Exception e) {
            logger.warn("Pemberitahuan: Elemen utama belum langsung terlihat: {}", e.getMessage());
        }
    }

    public boolean isPageLoaded() {
        return isElementDisplayed(dataTable) || isElementDisplayed(btnDownloadReport) || isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        try {
            return waitForVisibility(pageTitle).getText().trim();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }

    public boolean isDataTableDisplayed() {
        return isElementDisplayed(dataTable);
    }

    public boolean isFilterBarDisplayed() {
        return isElementDisplayed(inputSearch) || isElementDisplayed(btnSearch);
    }

    public void search(String keyword) {
        logger.info("Melakukan pencarian monitoring dengan kata kunci: '{}'", keyword);
        type(inputSearch, keyword);
        click(btnSearch);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public void resetFilter() {
        logger.info("Mereset filter pencarian monitoring");
        click(btnReset);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public void filterByDate(String startDate, String endDate) {
        logger.info("Mengisi rentang tanggal filter monitoring: {} s/d {}", startDate, endDate);
        if (startDate != null && !startDate.isEmpty()) {
            type(inputStartDate, startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            type(inputEndDate, endDate);
        }
        click(btnSearch);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public int getTableRowCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getTableHeaders() {
        List<String> headers = new ArrayList<>();
        try {
            List<WebElement> headerElements = driver.findElements(tableHeaders);
            for (WebElement el : headerElements) {
                headers.add(el.getText().trim());
            }
        } catch (Exception e) {
            logger.warn("Gagal membaca header tabel monitoring: {}", e.getMessage());
        }
        return headers;
    }

    public boolean isTextPresentInTable(String text) {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            for (WebElement row : rows) {
                if (row.getText().toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.warn("Pencarian teks '{}' dalam tabel gagal: {}", text, e.getMessage());
        }
        return false;
    }

    public boolean areStatCardsDisplayed() {
        return isElementDisplayed(statTotalStaf) && isElementDisplayed(statHadirOntime);
    }

    /**
     * Memverifikasi asas Least Privilege (ketiadaan tombol approval atau manipulasi data).
     */
    public boolean hasForbiddenActionButtons() {
        try {
            List<WebElement> forbidden = driver.findElements(forbiddenActionButtons);
            for (WebElement btn : forbidden) {
                if (btn.isDisplayed()) {
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    // Download Reports Actions
    public boolean isDownloadFormDisplayed() {
        return isElementDisplayed(btnDownloadReport);
    }

    public void selectDepartemen(String deptName) {
        try {
            WebElement selectEl = waitForVisibility(selectDepartemen);
            Select select = new Select(selectEl);
            select.selectByVisibleText(deptName);
        } catch (Exception e) {
            logger.warn("Gagal memilih departemen {}: {}", deptName, e.getMessage());
        }
    }

    public void clickDownloadReport() {
        logger.info("Menekan tombol Unduh Laporan Monitoring");
        click(btnDownloadReport);
    }

    public String getSnackbarText() {
        try {
            return waitForVisibility(snackbarMessage).getText().trim();
        } catch (Exception e) {
            try {
                return waitForVisibility(alertDownloadFeedback).getText().trim();
            } catch (Exception ex) {
                return "";
            }
        }
    }
}

