package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.LocalWebServer;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model untuk Portal Team Leader HADIR (11 Halaman Target).
 * Mendukung pengujian UI seluruh klaster (Presensi, Cuti, Lembur, Shifting, dan Generate Report).
 */
public class LeaderPortalPage extends BasePage {

    // Locators - Header & Title
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h1[contains(@class,'page-title')] | //h1");
    private final By roleIndicator = By.id("role-indicator");

    // Locators - Stat Cards
    private final By statTotalStaf = By.id("stat-total-staf");
    private final By statHadirOntime = By.id("stat-hadir-ontime");
    private final By statTerlambat = By.id("stat-terlambat");
    private final By statAbsen = By.id("stat-absen");

    // Locators - Filter & Search Controls
    private final By inputSearch = By.cssSelector("input#search, input#search-input, input[type='search']");
    private final By filterStatus = By.id("filter-status");
    private final By filterCutiType = By.id("filter-cuti-type");
    private final By filterShiftPola = By.id("filter-shift-pola");
    private final By inputStartDate = By.id("start-date");
    private final By inputEndDate = By.id("end-date");
    private final By btnSearch = By.cssSelector("button#btn-search, .btn-primary");
    private final By btnReset = By.cssSelector("button#btn-reset, .btn-secondary");

    // Locators - Table & Pagination
    private final By dataTable = By.cssSelector("table#table-leader, table.data-table, table.table_base");
    private final By tableRows = By.cssSelector("#table-body tr, table tbody tr");
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");

    // Locators - Action Buttons Baris
    private final By btnFirstApprove = By.cssSelector(".btn-approve-koreksi, .btn-approve-cuti, .btn-approve-lembur, .btn-approve-shift, .action-btn.approve");
    private final By btnFirstReject = By.cssSelector(".btn-reject-koreksi, .btn-reject-cuti, .btn-reject-lembur, .btn-reject-shift, .action-btn.reject");

    // Locators - Approval Modal Dialog
    private final By modalApproval = By.cssSelector(".modal-backdrop, #modal-approval-koreksi, #modal-approval-cuti, #modal-approval-lembur, #modal-approval-shifting");
    private final By rejectReasonInput = By.id("reject_reason");
    private final By btnSubmitApproval = By.id("sumbit-approval-request");

    // Locators - Generate Report Form
    private final By formGenerate = By.id("form-generate-leader");
    private final By selectReportType = By.id("select-report-type");
    private final By btnDownloadReport = By.id("btn-download-report");
    private final By alertDownloadFeedback = By.id("alert-download-feedback");

    public LeaderPortalPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Membuka halaman relatif portal Leader.
     * @param leaderRelativePath misalnya "/leader/all", "/leader/koreksi", dsb.
     */
    public void open(String leaderRelativePath) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        if (baseUrl.contains("localhost") && !LocalWebServer.isRunning()) {
            LocalWebServer.start(8085);
            baseUrl = LocalWebServer.getBaseUrl();
        }

        String fullUrl = baseUrl + leaderRelativePath;
        logger.info("Membuka halaman portal leader: {}", fullUrl);

        if (fullUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, fullUrl);
            driver.get(fullUrl);
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

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(dataTable),
                    ExpectedConditions.presenceOfElementLocated(formGenerate),
                    ExpectedConditions.presenceOfElementLocated(inputSearch),
                    ExpectedConditions.presenceOfElementLocated(pageTitle)
            ));
        } catch (Exception e) {
            logger.warn("Menunggu elemen halaman leader timeout: {}", e.getMessage());
        }
    }

    public boolean isPageLoaded() {
        try {
            String currentUrl = driver.getCurrentUrl();
            return currentUrl.contains("/leader/") || driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDataTableDisplayed() {
        try {
            return waitForVisibility(dataTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFilterBarDisplayed() {
        try {
            return driver.findElement(inputSearch).isDisplayed() || 
                   driver.findElement(By.cssSelector(".filter-bar, .filter-card")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areStatCardsDisplayed() {
        try {
            return driver.findElement(statTotalStaf).isDisplayed() &&
                   driver.findElement(statHadirOntime).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void search(String keyword) {
        logger.info("Mencari data dengan kata kunci: {}", keyword);
        type(inputSearch, keyword);
        try {
            driver.findElement(btnSearch).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(btnSearch));
        }
    }

    public void filterByStatus(String status) {
        logger.info("Menyaring berdasarkan status: {}", status);
        WebElement element = waitForVisibility(filterStatus);
        new Select(element).selectByValue(status.toUpperCase());
    }

    public void filterByCutiType(String type) {
        logger.info("Menyaring berdasarkan tipe cuti: {}", type);
        WebElement element = waitForVisibility(filterCutiType);
        new Select(element).selectByVisibleText(type);
    }

    public void filterByShiftPola(String pola) {
        logger.info("Menyaring berdasarkan pola shift: {}", pola);
        WebElement element = waitForVisibility(filterShiftPola);
        new Select(element).selectByValue(pola);
    }

    public void resetFilter() {
        logger.info("Menekan tombol reset filter");
        click(btnReset);
    }

    public boolean isTextPresentInTable(String text) {
        try {
            WebElement tableElement = waitForVisibility(dataTable);
            return tableElement.getText().toLowerCase().contains(text.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public int getTableRowCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            int visibleCount = 0;
            for (WebElement row : rows) {
                if (row.isDisplayed()) visibleCount++;
            }
            return visibleCount;
        } catch (Exception e) {
            return 0;
        }
    }

    public void openFirstApprovalModal(String action) {
        logger.info("Membuka modal approval baris pertama dengan aksi: {}", action);
        if ("approve".equalsIgnoreCase(action) || "setujui".equalsIgnoreCase(action)) {
            click(btnFirstApprove);
        } else {
            click(btnFirstReject);
        }
    }

    public boolean isApprovalModalDisplayed() {
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalApproval));
            return modal.isDisplayed() || modal.getAttribute("class").contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public void fillRejectReason(String reason) {
        logger.info("Mengisi alasan penolakan: {}", reason);
        type(rejectReasonInput, reason);
    }

    public void submitApproval() {
        logger.info("Menekan tombol konfirmasi modal approval");
        click(btnSubmitApproval);
        try {
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (org.openqa.selenium.NoAlertPresentException ignored) {
        } catch (Exception ignored) {
        }
    }

    public boolean isGenerateFormDisplayed() {
        try {
            return waitForVisibility(formGenerate).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void submitGenerateReport() {
        logger.info("Mengirim permintaan generate report");
        click(btnDownloadReport);
    }

    public boolean isGenerateSuccessAlertDisplayed() {
        try {
            return waitForVisibility(alertDownloadFeedback).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
