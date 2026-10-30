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
 * Page Object Model for HADIR Management User Monitoring page:
 * (https://magang.dikahadir.com/management/user-monitoring).
 *
 * Implements complete action methods with dynamic waits and safe JavaScript
 * scrolling/clicking for headless browsers (Firefox/Chrome).
 */
public class UserMonitoringPage extends BasePage {

    // Locators - Header & Breadcrumbs
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //*[contains(@class, 'page-title')] | //h1[contains(., 'User Monitoring')]");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");

    // Locators - Search & Action Bar
    private final By formSearch = By.cssSelector("form#form-search, .form-search");
    private final By searchInput = By.cssSelector("input#search, input[name='search'], .input-search");
    private final By searchBtn = By.cssSelector("button#btn-search, .btn-search");
    private final By resetBtn = By.cssSelector("button#btn-reset, .btn-reset");
    private final By tambahMonitoringBtn = By.cssSelector("button#btn-tambah-monitoring, .button-add");

    // Locators - Table
    private final By table = By.cssSelector("table.table_base, table[aria-label='sticky table']");
    private final By tableHeaders = By.cssSelector("table.table_base thead th");
    private final By tableRows = By.cssSelector("#table-body tr, table.table_base tbody tr");
    private final By userNameCells = By.cssSelector("#table-body tr td:nth-child(3)");
    private final By userNikCells = By.cssSelector("#table-body tr td:nth-child(4)");
    private final By emptyTableMessage = By.id("empty-table-message");

    // Locators - Modal Tambah
    private final By modalTambah = By.id("modal-tambah");
    private final By modalTambahTitle = By.id("alert-dialog-title");
    private final By inputFullname = By.id("fullname");
    private final By inputNik = By.id("nik");
    private final By selectLocation = By.id("location");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnBatalTambah = By.id("btn-batal-tambah");
    private final By btnCloseTambah = By.cssSelector("#modal-tambah .modal-close-btn");

    // Locators - Modal Edit
    private final By modalEdit = By.id("modal-edit");
    private final By modalEditTitle = By.id("alert-dialog-title-edit");
    private final By inputEditId = By.id("edit-id");
    private final By inputEditFullname = By.id("edit-fullname");
    private final By inputEditNik = By.id("edit-nik");
    private final By selectEditLocation = By.id("edit-location");
    private final By btnSimpanEdit = By.id("btn-simpan-edit");
    private final By btnBatalEdit = By.id("btn-batal-edit");
    private final By btnCloseEdit = By.cssSelector("#modal-edit .modal-close-btn");

    // Locators - Modal Delete
    private final By modalDelete = By.id("modal-delete");
    private final By modalDeleteTitle = By.id("alert-dialog-title-delete");
    private final By deletePromptText = By.id("delete-prompt-text");
    private final By btnKonfirmasiDelete = By.id("btn-konfirmasi-delete");
    private final By btnBatalDelete = By.id("btn-batal-delete");
    private final By btnCloseDelete = By.cssSelector("#modal-delete .modal-close-btn");

    // Locators - Action Menus / Buttons
    private final By editButtons = By.cssSelector(".btn-edit-direct, button[title='Edit']");
    private final By deleteButtons = By.cssSelector(".btn-delete-direct, button[title='Hapus']");

    // Locators - Pagination
    private final By paginationBar = By.cssSelector(".pagination-bar, .MuiTablePagination-root");
    private final By rowsPerPageSelect = By.cssSelector("#rows-per-page, .MuiTablePagination-select");
    private final By displayedRowsText = By.cssSelector("#displayed-rows, .MuiTablePagination-displayedRows");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    // Locators - Alert / Snackbar
    private final By alertSnackbar = By.id("alert-snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    public UserMonitoringPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the User Monitoring page.
     */
    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String userMonitoringUrl = baseUrl + "/management/user-monitoring";
        logger.info("Navigating to User Monitoring page: {}", userMonitoringUrl);
        if (userMonitoringUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, userMonitoringUrl);
        } else {
            driver.get(userMonitoringUrl);
        }
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(formSearch));
        logger.info("User Monitoring page loaded successfully");
    }

    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(formSearch)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitleText() {
        try {
            WebElement titleElem = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return titleElem.getText().trim();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }

    public String getBreadcrumbText() {
        try {
            return driver.findElement(breadcrumbs).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isSearchInputVisible() {
        return isElementVisible(searchInput);
    }

    public String getSearchPlaceholder() {
        try {
            return driver.findElement(searchInput).getAttribute("placeholder");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isSearchButtonVisible() {
        return isElementVisible(searchBtn);
    }

    public boolean isResetButtonVisible() {
        return isElementVisible(resetBtn);
    }

    public boolean isTambahMonitoringButtonVisible() {
        return isElementVisible(tambahMonitoringBtn);
    }

    public void search(String keyword) {
        logger.info("Searching for: {}", keyword);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(keyword);
        safeClick(searchBtn);
        shortWait();
    }

    public void resetSearch() {
        logger.info("Clicking reset search button");
        safeClick(resetBtn);
        shortWait();
    }

    public List<String> getTableHeaders() {
        List<WebElement> thElements = driver.findElements(tableHeaders);
        List<String> headers = new ArrayList<>();
        for (WebElement th : thElements) {
            headers.add(th.getText().trim());
        }
        return headers;
    }

    public int getTableRowCount() {
        try {
            return driver.findElements(tableRows).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getUserNames() {
        List<WebElement> cells = driver.findElements(userNameCells);
        List<String> names = new ArrayList<>();
        for (WebElement c : cells) {
            names.add(c.getText().trim());
        }
        return names;
    }

    public List<String> getUserNiks() {
        List<WebElement> cells = driver.findElements(userNikCells);
        List<String> niks = new ArrayList<>();
        for (WebElement c : cells) {
            niks.add(c.getText().trim());
        }
        return niks;
    }

    public void openTambahModal() {
        logger.info("Opening Tambah User Monitoring modal");
        safeClick(tambahMonitoringBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalTambah));
    }

    public boolean isTambahModalVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTambah)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTambahModalTitle() {
        try {
            return driver.findElement(modalTambahTitle).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void fillTambahForm(String fullname, String nik, String location) {
        logger.info("Filling Tambah form: {}, {}, {}", fullname, nik, location);
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputFullname));
        nameInput.clear();
        nameInput.sendKeys(fullname);

        WebElement nikInput = driver.findElement(inputNik);
        nikInput.clear();
        nikInput.sendKeys(nik);

        if (location != null && !location.isEmpty()) {
            WebElement locSelect = driver.findElement(selectLocation);
            new Select(locSelect).selectByVisibleText(location);
        }
    }

    public void submitTambahModal() {
        safeClick(btnSimpanTambah);
        shortWait();
    }

    public void batalTambahModal() {
        safeClick(btnBatalTambah);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalTambah));
    }

    public void closeTambahModalWithX() {
        safeClick(btnCloseTambah);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalTambah));
    }

    public void clickDeleteButtonOnFirstRow() {
        logger.info("Clicking delete button on first row");
        List<WebElement> buttons = driver.findElements(deleteButtons);
        if (!buttons.isEmpty()) {
            safeClickElement(buttons.get(0));
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalDelete));
        }
    }

    public boolean isDeleteModalVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalDelete)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDeletePromptText() {
        try {
            return driver.findElement(deletePromptText).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void batalDeleteModal() {
        safeClick(btnBatalDelete);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalDelete));
    }

    public void confirmDeleteModal() {
        safeClick(btnKonfirmasiDelete);
        shortWait();
    }

    public boolean isSnackbarVisible() {
        try {
            return driver.findElement(alertSnackbar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSnackbarText() {
        try {
            return driver.findElement(snackbarMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void setRowsPerPage(int count) {
        logger.info("Changing rows per page to: {}", count);
        WebElement selectElem = wait.until(ExpectedConditions.visibilityOfElementLocated(rowsPerPageSelect));
        new Select(selectElem).selectByValue(String.valueOf(count));
        shortWait();
    }

    public String getDisplayedRowsText() {
        try {
            return driver.findElement(displayedRowsText).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Safe helper methods
    private boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void safeClickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void shortWait() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }
}

