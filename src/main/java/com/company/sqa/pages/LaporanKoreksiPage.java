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
 * Page Object Model untuk Laporan Koreksi Absensi
 * (https://magang.dikahadir.com/laporan/koreksi).
 */
public class LaporanKoreksiPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LaporanKoreksiPage.class);

    // Header & Export
    private final By pageTitle = By.id("page-title");
    private final By exportBtn = By.id("btn-export");

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
    private final By filterTipeKoreksiSelect = By.id("filter-tipe-koreksi");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By closeFilterModalBtn = By.id("btn-close-filter-modal");

    // Snackbar notification
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    public LaporanKoreksiPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/koreksi";
        logger.info("Membuka halaman Laporan Koreksi: {}", url);
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
        logger.info("Mengisi kata kunci pencarian koreksi: {}", keyword);
        type(searchInput, keyword);
    }

    public void clickSearch() {
        logger.info("Mengklik tombol Search Koreksi");
        click(searchBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset Koreksi");
        click(resetBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickFilterBtn() {
        logger.info("Membuka modal filter koreksi");
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

    public void selectTipeKoreksi(String tipe) {
        logger.info("Memilih tipe koreksi: {}", tipe);
        WebElement el = driver.findElement(filterTipeKoreksiSelect);
        Select select = new Select(el);
        select.selectByValue(tipe);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter koreksi");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickCancelFilter() {
        logger.info("Membatalkan filter koreksi");
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

    public void clickApproveBtn(int rowIndex) {
        logger.info("Mengklik tombol Approve baris ke-{}", rowIndex);
        By rowApproveBtn = By.cssSelector("#table-body tr:nth-child(" + rowIndex + ") #btn-approve-koreksi");
        click(rowApproveBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickRejectBtn(int rowIndex) {
        logger.info("Mengklik tombol Reject baris ke-{}", rowIndex);
        By rowRejectBtn = By.cssSelector("#table-body tr:nth-child(" + rowIndex + ") #btn-reject-koreksi");
        click(rowRejectBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Koreksi");
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
