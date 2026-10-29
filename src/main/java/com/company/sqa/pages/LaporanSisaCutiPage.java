package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model untuk Laporan Sisa Cuti
 * (https://magang.dikahadir.com/laporan/sisa-cuti).
 */
public class LaporanSisaCutiPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LaporanSisaCutiPage.class);

    // Header & Export
    private final By pageTitle = By.id("page-title");
    private final By exportBtn = By.id("btn-export");
    private final By syncCutiBtn = By.id("btn-sync-cuti");

    // Filter controls
    private final By startDateInput = By.id("start-date");
    private final By endDateInput = By.id("end-date");
    private final By searchInput = By.id("search");
    private final By searchBtn = By.id("btn-search");
    private final By resetBtn = By.id("btn-reset");
    private final By filterBtn = By.id("btn-filter");

    // Table elements
    private final By laporanTable = By.id("table-laporan");
    private final By tableBody = By.id("table-body");
    private final By tableRows = By.cssSelector("#table-body tr:not(.empty-state)");

    // Pagination
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By paginationInfo = By.id("pagination-info");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    // Modal Filter
    private final By filterModal = By.id("filter-modal");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterTahunCutiSelect = By.id("filter-tahun-cuti");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By closeFilterModalBtn = By.id("btn-close-filter-modal");

    // Snackbar notification
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    public LaporanSisaCutiPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/sisa-cuti";
        logger.info("Membuka halaman Laporan Sisa Cuti: {}", url);
        driver.get(url);
        waitForVisibility(laporanTable);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isLaporanTableDisplayed() {
        return isElementDisplayed(laporanTable);
    }

    public boolean isFilterBarDisplayed() {
        return isElementDisplayed(startDateInput)
                && isElementDisplayed(endDateInput)
                && isElementDisplayed(searchInput)
                && isElementDisplayed(searchBtn)
                && isElementDisplayed(resetBtn)
                && isElementDisplayed(filterBtn);
    }

    public boolean isExportBtnDisplayed() {
        return isElementDisplayed(exportBtn);
    }

    public boolean isSyncCutiBtnDisplayed() {
        return isElementDisplayed(syncCutiBtn);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public void enterStartDate(String date) {
        logger.info("Mengisi start date: {}", date);
        type(startDateInput, date);
    }

    public void enterEndDate(String date) {
        logger.info("Mengisi end date: {}", date);
        type(endDateInput, date);
    }

    public void enterSearch(String keyword) {
        logger.info("Mengisi kata kunci pencarian sisa cuti: {}", keyword);
        type(searchInput, keyword);
    }

    public void clickSearch() {
        logger.info("Mengklik tombol Search Sisa Cuti");
        click(searchBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset Sisa Cuti");
        click(resetBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickFilterBtn() {
        logger.info("Membuka modal filter sisa cuti");
        click(filterBtn);
        waitForVisibility(filterModal);
    }

    public boolean isFilterModalDisplayed() {
        return isElementDisplayed(filterModal);
    }

    public void selectDepartemen(String deptName) {
        logger.info("Memilih departemen: {}", deptName);
        WebElement el = driver.findElement(filterDepartemenSelect);
        Select select = new Select(el);
        select.selectByVisibleText(deptName);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter sisa cuti");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickCancelFilter() {
        logger.info("Membatalkan filter sisa cuti");
        click(cancelFilterBtn);
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public boolean isEmployeeNamePresent(String employeeName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(employeeName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void clickSyncCuti() {
        logger.info("Mengklik tombol Sync Kuota Cuti");
        click(syncCutiBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Sisa Cuti");
        click(exportBtn);
        waitForAjaxOrTimeout(300);
    }

    public boolean isSnackbarVisible() {
        try {
            waitForVisibility(snackbar);
            return isElementDisplayed(snackbar);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSnackbarMessage() {
        return getText(snackbarMessage);
    }

    public void selectRowsPerPage(String rows) {
        logger.info("Mengatur rows per page: {}", rows);
        WebElement el = driver.findElement(rowsPerPageSelect);
        Select select = new Select(el);
        select.selectByValue(rows);
        waitForAjaxOrTimeout(400);
    }

    public String getPaginationInfo() {
        return getText(paginationInfo);
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

