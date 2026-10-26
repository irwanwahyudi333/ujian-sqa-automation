package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model for Hadir Laporan Semua (Attendance Activity) page:
 * (https://magang.dikahadir.com/laporan/all).
 */
public class LaporanAllPage extends BasePage {

    // Locators - Header & Action
    private final By pageTitle = By.id("page-title");
    private final By exportBtn = By.id("btn-export");

    // Locators - Filter & Search Bar
    private final By startDateInput = By.id("start-date");
    private final By endDateInput = By.id("end-date");
    private final By searchInput = By.id("search");
    private final By searchBtn = By.id("btn-search");
    private final By resetBtn = By.id("btn-reset");
    private final By filterBtn = By.id("btn-filter");

    // Locators - Table
    private final By laporanTable = By.id("table-laporan");
    private final By tableBody = By.id("table-body");
    private final By tableRows = By.cssSelector("#table-body tr");
    private final By emptyState = By.cssSelector(".empty-state");

    // Locators - Filter Modal
    private final By filterModal = By.id("filter-modal");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterStatusSelect = By.id("filter-status");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");

    // Locators - Photo Modal
    private final By photoModal = By.id("photo-modal");
    private final By photoModalTitle = By.id("photo-modal-title");
    private final By closePhotoModalBtn = By.id("btn-close-photo");

    // Locators - Map Modal
    private final By mapModal = By.id("map-modal");
    private final By mapAddressText = By.id("map-address-text");
    private final By mapCoordsText = By.id("map-coords-text");
    private final By closeMapModalBtn = By.id("btn-close-map");

    // Locators - Snackbar / Toast
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    // Locators - Pagination
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    public LaporanAllPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/all";
        logger.info("Membuka halaman Laporan Semua: {}", url);
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

    public void enterStartDate(String dateStr) {
        type(startDateInput, dateStr);
    }

    public void enterEndDate(String dateStr) {
        type(endDateInput, dateStr);
    }

    public void enterSearch(String keyword) {
        type(searchInput, keyword);
    }

    public void clickSearch() {
        click(searchBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickReset() {
        click(resetBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickFilterBtn() {
        click(filterBtn);
        waitForVisibility(filterModal);
    }

    public boolean isFilterModalDisplayed() {
        return isElementDisplayed(filterModal);
    }

    public void selectFilterDepartemen(String departemen) {
        WebElement selectElement = waitForVisibility(filterDepartemenSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(departemen);
    }

    public void selectFilterStatus(String status) {
        WebElement selectElement = waitForVisibility(filterStatusSelect);
        Select select = new Select(selectElement);
        select.selectByValue(status);
    }

    public void applyFilter() {
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
    }

    public void cancelFilter() {
        click(cancelFilterBtn);
    }

    public void clickExport() {
        click(exportBtn);
        waitForAjaxOrTimeout(300);
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.size() == 1 && isElementDisplayed(emptyState)) {
            return 0;
        }
        return rows.size();
    }

    public boolean isEmployeeNamePresent(String expectedName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.getText().contains(expectedName)) {
                return true;
            }
        }
        return false;
    }

    public void clickPhotoOfRow(int rowIndex) {
        List<WebElement> avatars = driver.findElements(By.cssSelector(".avatar-wrapper"));
        if (rowIndex < avatars.size()) {
            avatars.get(rowIndex).click();
            waitForVisibility(photoModal);
        }
    }

    public boolean isPhotoModalDisplayed() {
        return isElementDisplayed(photoModal);
    }

    public void closePhotoModal() {
        click(closePhotoModalBtn);
    }

    public void clickLocationLinkOfRow(int rowIndex) {
        List<WebElement> links = driver.findElements(By.cssSelector(".location-link"));
        if (rowIndex < links.size()) {
            links.get(rowIndex).click();
            waitForVisibility(mapModal);
        }
    }

    public boolean isMapModalDisplayed() {
        return isElementDisplayed(mapModal);
    }

    public void closeMapModal() {
        click(closeMapModalBtn);
    }

    public String getSnackbarMessage() {
        waitForVisibility(snackbar);
        return getText(snackbarMessage);
    }

    public boolean isSnackbarVisible() {
        return isElementDisplayed(snackbar);
    }

    public String getPaginationInfoText() {
        return getText(paginationInfo);
    }

    public void selectRowsPerPage(String value) {
        WebElement selectElem = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(selectElem);
        select.selectByValue(value);
        waitForAjaxOrTimeout(300);
    }

    public void clickNextPage() {
        click(nextPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickPrevPage() {
        click(prevPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public boolean isNextPageDisabled() {
        WebElement btn = driver.findElement(nextPageBtn);
        return btn.getAttribute("disabled") != null;
    }

    public boolean isPrevPageDisabled() {
        WebElement btn = driver.findElement(prevPageBtn);
        return btn.getAttribute("disabled") != null;
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
