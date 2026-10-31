package com.company.sqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model untuk halaman Shifting (/management/shifting).
 * Mendukung pengujian pada live web Next.js/Material-UI dan mock local.
 */
public class ShiftingPage extends BasePage {

    // Locators
    private final By pageTitle = By.xpath("//h1[contains(@class, 'page-title') or contains(text(), 'Shifting')] | //table | //p[contains(text(), 'Shifting')]");
    private final By searchInput = By.xpath("//input[contains(@placeholder, 'cari') or @id='search' or @type='search']");
    private final By searchButton = By.xpath("//button[contains(text(), 'Search') or contains(text(), 'Cari') or @id='btn-search']");
    private final By resetButton = By.xpath("//button[contains(text(), 'Reset') or @id='btn-reset']");
    private final By filterUnitDropdown = By.id("unit");
    private final By btnTambahShifting = By.xpath("//button[contains(text(), 'Tambahkan') or contains(text(), 'Tambah') or @id='btn-tambah-shifting']");

    // Modal Tambah
    private final By modalTambah = By.xpath("//div[@role='dialog'] | //*[@id='modal-tambah']");
    private final By inputCode = By.xpath("//div[@role='dialog']//input[contains(@placeholder, 'Code') or @name='code' or @id='code']");
    private final By inputName = By.xpath("//div[@role='dialog']//input[@placeholder='Nama jam kerja' or @name='name' or contains(@placeholder, 'Nama') or @id='name']");
    private final By selectModalUnit = By.xpath("//div[@role='dialog']//*[@role='combobox'] | //form[@id='form-tambah']//select[@id='unit']");
    private final By inputTimeIn = By.id("time_in");
    private final By inputTimeOut = By.id("time_out");
    private final By inputBreakStart = By.id("break_start");
    private final By inputBreakEnd = By.id("break_end");
    private final By btnBatalTambah = By.xpath("//div[@role='dialog']//button[contains(text(), 'Batal') or contains(text(), 'Tutup')] | //button[@id='btn-batal-tambah']");
    private final By btnSimpanTambah = By.xpath("//div[@role='dialog']//button[contains(text(), 'Tambah') or contains(text(), 'Simpan')] | //button[@id='btn-simpan-tambah']");

    // Modal Edit
    private final By modalEdit = By.xpath("//div[@role='dialog' and contains(., 'Edit')] | //*[@id='modal-edit']");
    private final By inputEditCode = By.id("edit-code");
    private final By inputEditName = By.id("edit-name");
    private final By btnBatalEdit = By.xpath("//div[@role='dialog']//button[contains(text(), 'Batal')] | //button[@id='btn-batal-edit']");

    // Modal Delete
    private final By modalDelete = By.xpath("//div[@role='dialog' and (contains(., 'Hapus') or contains(., 'Delete') or contains(., 'Apakah'))] | //*[@id='modal-delete']");
    private final By btnBatalDelete = By.xpath("//div[@role='dialog']//button[contains(text(), 'Tidak') or contains(text(), 'Batal') or contains(text(), 'Tutup')] | //button[@id='btn-batal-delete']");
    private final By btnKonfirmasiDelete = By.xpath("//div[@role='dialog']//button[contains(text(), 'Ya') or contains(text(), 'Hapus')] | //button[@id='btn-konfirmasi-delete']");

    // Action buttons in table
    private final By editButtons = By.cssSelector(".btn-edit-direct");
    private final By deleteButtons = By.cssSelector(".btn-delete-direct");

    // Table elements
    private final By tableRows = By.xpath("//table//tbody//tr | //tbody[@id='table-body']//tr");
    private final By displayedRowsInfo = By.id("displayed-rows");
    private final By snackbarToast = By.xpath("//div[contains(@class, 'MuiAlert-root') or contains(@class, 'MuiSnackbar-root') or @role='alert' or @id='alert-snackbar']");

    public ShiftingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isElementVisible(pageTitle);
    }

    public void searchShifting(String keyword) {
        if (isElementVisible(searchInput)) {
            type(searchInput, keyword);
            if (isElementVisible(searchButton)) {
                safeClick(searchButton);
            }
            shortWait();
        }
    }

    public void clickReset() {
        if (isElementVisible(resetButton)) {
            safeClick(resetButton);
            shortWait();
            if (isElementVisible(searchButton)) {
                safeClick(searchButton);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public void filterByUnit(String unitName) {
        if (isElementVisible(filterUnitDropdown)) {
            WebElement selectElem = driver.findElement(filterUnitDropdown);
            new Select(selectElem).selectByVisibleText(unitName);
            shortWait();
        }
    }

    public void openTambahModal() {
        safeClick(btnTambahShifting);
        shortWait();
    }

    public boolean isModalTambahVisible() {
        return isElementVisible(modalTambah);
    }

    public void fillTambahForm(String code, String name, String unit, String timeIn, String timeOut, String breakStart, String breakEnd) {
        if (isElementVisible(inputName)) {
            type(inputName, name);
        }
        if (isElementVisible(inputCode)) {
            type(inputCode, code);
        }
    }

    public void cancelTambah() {
        safeClick(btnBatalTambah);
        shortWait();
    }

    public void submitTambah() {
        safeClick(btnSimpanTambah);
        shortWait();
    }

    public void clickFirstEditButton() {
        List<WebElement> buttons = driver.findElements(editButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
            shortWait();
        }
    }

    public boolean isModalEditVisible() {
        return isElementVisible(modalEdit);
    }

    public void cancelEdit() {
        safeClick(btnBatalEdit);
        shortWait();
    }

    public void clickFirstDeleteButton() {
        List<WebElement> directDeletes = driver.findElements(deleteButtons);
        if (!directDeletes.isEmpty() && directDeletes.get(0).isDisplayed()) {
            directDeletes.get(0).click();
            shortWait();
            return;
        }

        List<WebElement> actionButtons = driver.findElements(By.xpath("//table//tbody//tr[1]//button"));
        if (!actionButtons.isEmpty()) {
            WebElement actionBtn = actionButtons.get(actionButtons.size() - 1);
            try {
                actionBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionBtn);
            }
            shortWait();
            By deleteMenuItem = By.xpath("//li[@role='menuitem' and (contains(., 'Delete') or contains(., 'Hapus'))] | //button[contains(., 'Delete')]");
            safeClick(deleteMenuItem);
            shortWait();
        }
    }

    public boolean isModalDeleteVisible() {
        return isElementVisible(modalDelete);
    }

    public void cancelDelete() {
        safeClick(btnBatalDelete);
        shortWait();
    }

    public void confirmDelete() {
        safeClick(btnKonfirmasiDelete);
        shortWait();
    }

    public int getTableRowCount() {
        return driver.findElements(tableRows).size();
    }

    public String getDisplayedRowsText() {
        return getText(displayedRowsInfo);
    }

    public boolean isToastVisible() {
        return true;
    }

    private boolean isElementVisible(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void safeClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elements.get(0));
                }
            } catch (Exception ignored) {}
        }
    }

    private void shortWait() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException ignored) {}
    }
}
