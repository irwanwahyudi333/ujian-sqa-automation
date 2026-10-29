package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model untuk Laporan Izin Off
 * (https://magang.dikahadir.com/laporan/izin-off).
 */
public class LaporanIzinOffPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LaporanIzinOffPage.class);

    // Header & Export
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h6 | //h5");
    private final By exportBtn = By.xpath("//*[@id='btn-export'] | //button[contains(., 'Export')]");

    // Filter controls
    private final By startDateInput = By.xpath("//*[@id='start-date'] | //input[@placeholder='Start Date' or @type='date']");
    private final By endDateInput = By.xpath("//*[@id='end-date'] | //input[@placeholder='End Date' or @type='date']");
    private final By searchInput = By.xpath("//*[@id='search'] | //input[contains(@placeholder, 'Search') or contains(@placeholder, 'Cari')]");
    private final By searchBtn = By.xpath("//*[@id='btn-search'] | //button[contains(., 'Search') or @type='submit']");
    private final By resetBtn = By.xpath("//*[@id='btn-reset'] | //button[contains(., 'Reset')]");
    private final By filterBtn = By.xpath("//*[@id='btn-filter'] | //button[contains(., 'Filter')]");

    // Table elements
    private final By laporanTable = By.xpath("//*[@id='table-laporan'] | //table");
    private final By tableBody = By.xpath("//*[@id='table-body'] | //table//tbody");
    private final By tableRows = By.xpath("//*[@id='table-body']//tr[not(contains(@class, 'empty-state'))] | //table//tbody//tr");
    private final By firstRowActionBtn = By.xpath("(//table//tbody//tr)[1]//button[@aria-label='action' or contains(@class, 'MuiIconButton')]");
    private final By popoverMenuItems = By.xpath("//*[contains(@class, 'MuiPopover-root') or contains(@class, 'MuiMenu-paper')]//li");

    // Pagination
    private final By rowsPerPageSelect = By.xpath("//*[@id='rows-per-page'] | //div[contains(@class, 'MuiTablePagination-select')]");
    private final By paginationInfo = By.xpath("//*[@id='pagination-info'] | //*[contains(@class, 'MuiTablePagination-displayedRows')]");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    // Modal Filter
    private final By filterModal = By.xpath("//*[@id='filter-modal'] | //*[@role='dialog']");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterStatusOffSelect = By.id("filter-status-off");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By closeFilterModalBtn = By.id("btn-close-filter-modal");

    // Snackbar notification
    private final By snackbar = By.xpath("//*[@id='snackbar'] | //*[contains(@class, 'MuiAlert-root') or contains(@class, 'MuiSnackbar-root')]");
    private final By snackbarMessage = By.xpath("//*[@id='snackbar-message'] | //*[contains(@class, 'MuiAlert-message')]");

    public LaporanIzinOffPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/izin-off";
        logger.info("Membuka halaman Laporan Izin Off: {}", url);
        if (url.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, url);
        }
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
        logger.info("Mengisi kata kunci pencarian izin off: {}", keyword);
        type(searchInput, keyword);
    }

    public void clickSearch() {
        logger.info("Mengklik tombol Search Izin Off");
        click(searchBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset Izin Off");
        click(resetBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickFilterBtn() {
        logger.info("Membuka modal filter izin off");
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

    public void selectStatusOff(String status) {
        logger.info("Memilih status izin off: {}", status);
        WebElement el = driver.findElement(filterStatusOffSelect);
        Select select = new Select(el);
        select.selectByValue(status);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter izin off");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickCancelFilter() {
        logger.info("Membatalkan filter izin off");
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
        By rowApproveBtn = By.cssSelector("#table-body tr:nth-child(" + rowIndex + ") #btn-approve-off");
        click(rowApproveBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickRejectBtn(int rowIndex) {
        logger.info("Mengklik tombol Reject baris ke-{}", rowIndex);
        By rowRejectBtn = By.cssSelector("#table-body tr:nth-child(" + rowIndex + ") #btn-reject-off");
        click(rowRejectBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Izin Off");
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

    // ==========================================
    // Methods Khusus Verifikasi Bug Menu Aksi Izin Off (Live App)
    // ==========================================

    public void clickFirstRowActionBtn() {
        logger.info("Mengklik tombol aksi baris pertama tabel izin off");
        click(firstRowActionBtn);
        waitForAjaxOrTimeout(600);
    }

    public List<String> getActionMenuTexts() {
        List<WebElement> items = driver.findElements(popoverMenuItems);
        List<String> texts = new ArrayList<>();
        for (WebElement item : items) {
            String text = item.getText().trim();
            if (!text.isEmpty()) {
                texts.add(text);
            }
        }
        return texts;
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

