package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for HADIR Management Client Upliner page:
 * (https://magang.dikahadir.com/management/client-upliner).
 */
public class ClientUplinerPage extends BasePage {

    // Locators - Header & Breadcrumbs
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //*[contains(@class, 'page-title')] | //h1[contains(., 'Client Upliner')]");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");

    // Locators - Search & Action Bar
    private final By formSearch = By.cssSelector("form#form-search, .form-search");
    private final By searchInput = By.cssSelector("input#search, input[name='search'], .input-search");
    private final By searchBtn = By.cssSelector("button#btn-search, .btn-search");
    private final By resetBtn = By.cssSelector("button#btn-reset, .btn-reset");
    private final By tambahUplinerBtn = By.cssSelector("button#btn-tambah-upliner, .button-add");

    // Locators - Table
    private final By table = By.cssSelector("table.table_base, table[aria-label='sticky table']");
    private final By tableHeaders = By.cssSelector("table.table_base thead th");
    private final By tableRows = By.cssSelector("#table-body tr, table.table_base tbody tr");
    private final By uplinerNameCells = By.cssSelector("#table-body tr td:nth-child(3)");
    private final By uplinerNikCells = By.cssSelector("#table-body tr td:nth-child(4)");

    // Locators - Modal Tambah
    private final By modalTambah = By.id("modal-tambah");
    private final By modalTambahTitle = By.cssSelector("#modal-tambah .modal-title");
    private final By inputFullname = By.id("fullname");
    private final By inputNik = By.id("nik");
    private final By selectType = By.id("type");
    private final By selectRole = By.id("role");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnBatalTambah = By.id("btn-batal-tambah");

    // Locators - Modal Delete
    private final By modalDelete = By.cssSelector("#modal-delete, #modal-delete-upliner");
    private final By deletePromptText = By.id("delete-prompt-text");
    private final By btnKonfirmasiDelete = By.id("btn-konfirmasi-delete");
    private final By btnBatalDelete = By.id("btn-batal-delete");

    // Locators - Action Buttons
    private final By deleteButtons = By.cssSelector(".btn-delete-direct, button[title='Hapus']");

    // Locators - Snackbar
    private final By alertSnackbar = By.id("alert-snackbar");
    private final By snackbarMessage = By.id("snackbar-message");

    public ClientUplinerPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String cuUrl = baseUrl + "/management/client-upliner";
        logger.info("Navigating to Client Upliner page: {}", cuUrl);
        if (cuUrl.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, cuUrl);
        } else {
            driver.get(cuUrl);
        }
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(formSearch));
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
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText().trim();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }

    public boolean isSearchInputVisible() {
        return isElementVisible(searchInput);
    }

    public boolean isTambahUplinerButtonVisible() {
        return isElementVisible(tambahUplinerBtn);
    }

    public void search(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(keyword);
        safeClick(searchBtn);
        shortWait();
    }

    public void resetSearch() {
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

    public List<String> getUplinerNames() {
        List<WebElement> cells = driver.findElements(uplinerNameCells);
        List<String> names = new ArrayList<>();
        for (WebElement c : cells) {
            names.add(c.getText().trim());
        }
        return names;
    }

    public void openTambahModal() {
        safeClick(tambahUplinerBtn);
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

    public void batalTambahModal() {
        safeClick(btnBatalTambah);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalTambah));
    }

    public void clickDeleteButtonOnFirstRow() {
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

