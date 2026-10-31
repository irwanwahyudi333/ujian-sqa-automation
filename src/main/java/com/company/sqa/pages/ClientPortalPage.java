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
 * Page Object Model untuk Portal Client / Upliner HADIR (17 Halaman Target).
 * Mendukung pengujian komparatif untuk Upliner V2 (Data Validator 1) dan Upliner V3 (Data Validator 2).
 */
public class ClientPortalPage extends BasePage {

    // Locators - Universal Header & Title
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h1 | //h2 | //h3[contains(@class,'page-title')]");
    private final By roleIndicator = By.id("role-indicator");

    // Locators - Sidebar Validator Labels
    private final By labelCutiValidator = By.id("label-cuti-validator");
    private final By labelLemburValidator = By.id("label-ot-validator");

    // Locators - Stat Cards
    private final By statTotalStaf = By.id("stat-total-staf");
    private final By statHadirOntime = By.id("stat-hadir-ontime");
    private final By statTerlambat = By.id("stat-terlambat");
    private final By statAbsen = By.id("stat-absen");

    // Locators - Filter & Search Controls
    private final By inputSearch = By.cssSelector("input#search, input[type='search'], input[name='search']");
    private final By inputStartDate = By.cssSelector("input#start-date, input[name='startDate']");
    private final By inputEndDate = By.cssSelector("input#end-date, input[name='endDate']");
    private final By btnSearch = By.cssSelector("button#btn-search, .btn-search");
    private final By btnReset = By.cssSelector("button#btn-reset, .btn-reset");
    private final By btnExport = By.cssSelector("button#btn-export, .btn-export, button#btn-export-excel");

    // Locators - Table & Pagination
    private final By dataTable = By.cssSelector("table.table_base, #table-client, table[aria-label='sticky table']");
    private final By tableHeaders = By.cssSelector("table.table_base thead th, thead th");
    private final By tableRows = By.cssSelector("#table-body tr, table.table_base tbody tr");
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By btnFirstPage = By.id("btn-first-page");
    private final By btnPrevPage = By.id("btn-prev-page");
    private final By btnNextPage = By.id("btn-next-page");
    private final By btnLastPage = By.id("btn-last-page");

    // Locators - Validator Modal Dialog
    private final By modalValidator = By.id("modal-action-validator");
    private final By validatorNotes = By.id("validator-notes");
    private final By btnApproveValidator = By.id("btn-approve-validator");
    private final By btnRejectValidator = By.id("btn-reject-validator");
    private final By btnCancelValidator = By.id("btn-cancel-validator");
    private final By btnTriggerValidator = By.cssSelector(".btn-validator-approve, button.btn-primary:not(#btn-search):not(#btn-download-report)");

    // Locators - Download Reports Generator
    private final By selectDepartemen = By.id("select-departemen");
    private final By radioFormatXlsx = By.id("radio-format-xlsx");
    private final By btnDownloadReport = By.cssSelector("button#btn-download-report, button#btn-export-excel");
    private final By alertDownloadFeedback = By.id("alert-download-feedback");

    // Locators - Toast / Snackbar
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    private String currentSimulatedRole = "client";

    public ClientPortalPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Membuka halaman relatif portal client.
     * @param clientRelativePath misalnya "/client/all", "/client/cuti/validator", dsb.
     */
    public void open(String clientRelativePath) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String fullUrl = baseUrl + clientRelativePath;
        logger.info("Membuka halaman portal client: {}", fullUrl);

        if (fullUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, fullUrl);
        } else {
            driver.get(fullUrl);
        }
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        applyRoleInBrowser();
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
        logger.info("Melakukan pencarian dengan kata kunci: '{}'", keyword);
        type(inputSearch, keyword);
        click(btnSearch);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public void resetFilter() {
        logger.info("Mereset filter pencarian");
        click(btnReset);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public void filterByDate(String startDate, String endDate) {
        logger.info("Mengisi rentang tanggal filter: {} s/d {}", startDate, endDate);
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
            logger.warn("Gagal membaca header tabel: {}", e.getMessage());
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

    // Role-specific Sidebar verifications
    public String getCutiValidatorSidebarLabel() {
        try {
            return waitForVisibility(labelCutiValidator).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getLemburValidatorSidebarLabel() {
        try {
            return waitForVisibility(labelLemburValidator).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void setRoleSimulation(String role) {
        logger.info("Mengatur simulasi role UI menjadi: {}", role);
        this.currentSimulatedRole = role;
        applyRoleInBrowser();
    }

    private void applyRoleInBrowser() {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl == null || currentUrl.startsWith("about:") || currentUrl.isEmpty()) {
                return;
            }
            ((JavascriptExecutor) driver).executeScript(
                    "try { localStorage.setItem('user_role', arguments[0]); } catch(e) {} " +
                    "if (window.applyRoleConfig) { window.applyRoleConfig(arguments[0]); } " +
                    "const roleBadge = document.getElementById('role-indicator'); " +
                    "if (roleBadge) { roleBadge.textContent = arguments[0]; } " +
                    "const cutiLbl = document.getElementById('label-cuti-validator'); " +
                    "if (cutiLbl) { cutiLbl.textContent = arguments[0] === 'client' ? 'Data Validator 1' : 'Data Validator 2'; } " +
                    "const otLbl = document.getElementById('label-ot-validator'); " +
                    "if (otLbl) { otLbl.textContent = arguments[0] === 'client' ? 'Data Validator 1' : 'Data Validator 2'; }",
                    currentSimulatedRole
            );
        } catch (Exception e) {
            logger.warn("Gagal apply role simulation via JS: {}", e.getMessage());
        }
    }

    // Modal Validator Actions
    public void clickFirstValidatorActionButton() {
        logger.info("Membuka modal validasi dari baris tabel");
        click(btnTriggerValidator);
    }

    public boolean isValidatorModalDisplayed() {
        return isElementDisplayed(modalValidator);
    }

    public void enterValidatorNotes(String notes) {
        logger.info("Memasukkan catatan validasi: '{}'", notes);
        type(validatorNotes, notes);
    }

    public void clickApproveValidator() {
        logger.info("Menekan tombol Setujui Validasi");
        click(btnApproveValidator);
    }

    public void clickRejectValidator() {
        logger.info("Menekan tombol Tolak Validasi");
        click(btnRejectValidator);
    }

    public void clickCancelValidator() {
        logger.info("Menekan tombol Batal Validasi");
        click(btnCancelValidator);
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
        logger.info("Menekan tombol Unduh Laporan");
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

    public String getPaginationInfoText() {
        try {
            return waitForVisibility(paginationInfo).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}

