package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model for Hadir Laporan Validation Overtime page:
 * (https://magang.dikahadir.com/laporan/validation-overtime).
 */
public class LaporanValidationOvertimePage extends BasePage {

    // Locators - Header & Actions
    private final By pageTitle = By.id("page-title");
    private final By exportBtn = By.id("btn-export");

    // Locators - Filter & Search Controls
    private final By startDateInput = By.id("start-date");
    private final By endDateInput = By.id("end-date");
    private final By searchInput = By.id("search");
    private final By searchBtn = By.id("btn-search");
    private final By resetBtn = By.id("btn-reset");
    private final By filterBtn = By.id("btn-filter");

    // Locators - Table & Rows
    private final By laporanTable = By.id("table-laporan");
    private final By tableBody = By.id("table-body");
    private final By tableRows = By.cssSelector("#table-body tr");
    private final By emptyState = By.cssSelector(".empty-state");

    // Locators - Modal Filter Lanjutan
    private final By filterModal = By.id("filter-modal");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterStatusSelect = By.id("filter-status-validation");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By closeFilterModalBtn = By.id("btn-close-filter-modal");

    // Locators - Row Actions
    private final By approveBtnRow1 = By.cssSelector("#table-body tr:nth-child(1) #btn-approve-overtime");
    private final By rejectBtnRow1 = By.cssSelector("#table-body tr:nth-child(1) #btn-reject-overtime");
    private final By returnBtnRow1 = By.cssSelector("#table-body tr:nth-child(1) #btn-return-overtime");

    // Locators - Modal Detail
    private final By detailModal = By.id("detail-modal");
    private final By detailModalTitle = By.id("detail-modal-title");
    private final By closeDetailModalXBtn = By.id("btn-close-detail-modal");

    // Locators - Snackbar Toast
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    // Locators - Pagination Controls
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    public LaporanValidationOvertimePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/validation-overtime";
        logger.info("Membuka halaman Laporan Validation Overtime: {}", url);
        driver.get(url);
        waitForVisibility(laporanTable);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isLaporanTableDisplayed() {
        return isElementDisplayed(laporanTable);
    }

    public boolean isFilterBarDisplayed() {
        return isElementDisplayed(startDateInput) && isElementDisplayed(endDateInput)
                && isElementDisplayed(searchInput) && isElementDisplayed(searchBtn)
                && isElementDisplayed(resetBtn) && isElementDisplayed(filterBtn);
    }

    public boolean isExportBtnDisplayed() {
        return isElementDisplayed(exportBtn);
    }

    public void enterStartDate(String date) {
        logger.info("Mengisi Start Date: {}", date);
        type(startDateInput, date);
    }

    public void enterEndDate(String date) {
        logger.info("Mengisi End Date: {}", date);
        type(endDateInput, date);
    }

    public void enterSearch(String keyword) {
        logger.info("Mengisi kata kunci pencarian: {}", keyword);
        type(searchInput, keyword);
    }

    public void clickSearch() {
        logger.info("Mengklik tombol Search");
        click(searchBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset");
        click(resetBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickFilterBtn() {
        logger.info("Membuka dialog modal filter validation overtime");
        click(filterBtn);
        waitForVisibility(filterModal);
    }

    public boolean isFilterModalDisplayed() {
        return isElementDisplayed(filterModal);
    }

    public void selectDepartemen(String departemen) {
        logger.info("Memilih filter departemen: {}", departemen);
        WebElement selectElement = waitForVisibility(filterDepartemenSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(departemen);
    }

    public void selectStatus(String status) {
        logger.info("Memilih filter status validation: {}", status);
        WebElement selectElement = waitForVisibility(filterStatusSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(status);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter modal");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
    }

    public void clickCancelFilter() {
        logger.info("Membatalkan filter modal");
        click(cancelFilterBtn);
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.size() == 1 && isElementDisplayed(emptyState)) {
            return 0;
        }
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

    public void clickApproveRow1() {
        logger.info("Mengklik tombol Approve pada baris 1");
        click(approveBtnRow1);
        waitForAjaxOrTimeout(300);
    }

    public void clickRejectRow1() {
        logger.info("Mengklik tombol Reject pada baris 1");
        click(rejectBtnRow1);
        waitForAjaxOrTimeout(300);
    }

    public void clickReturnRow1() {
        logger.info("Mengklik tombol Return pada baris 1");
        click(returnBtnRow1);
        waitForAjaxOrTimeout(300);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Validation Overtime");
        click(exportBtn);
        waitForAjaxOrTimeout(300);
    }

    public boolean isSnackbarVisible() {
        return isElementDisplayed(snackbar);
    }

    public String getSnackbarMessage() {
        waitForVisibility(snackbar);
        return getText(snackbarMessage);
    }

    public void selectRowsPerPage(String rows) {
        logger.info("Mengatur rows per page: {}", rows);
        WebElement selectElement = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(selectElement);
        select.selectByValue(rows);
        waitForAjaxOrTimeout(300);
    }

    public String getPaginationInfo() {
        return getText(paginationInfo);
    }

    public void clickNextPage() {
        logger.info("Navigasi ke halaman berikutnya");
        click(nextPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickPrevPage() {
        logger.info("Navigasi ke halaman sebelumnya");
        click(prevPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickFirstPage() {
        logger.info("Navigasi ke halaman pertama");
        click(firstPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickLastPage() {
        logger.info("Navigasi ke halaman terakhir");
        click(lastPageBtn);
        waitForAjaxOrTimeout(300);
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}

