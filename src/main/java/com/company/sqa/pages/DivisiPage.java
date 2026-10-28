package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for HADIR Management Divisi page:
 * (https://magang.dikahadir.com/management/divisi).
 *
 * Implements complete action methods with dynamic waits and safe JavaScript
 * scrolling/clicking for headless browsers (Firefox/Chrome).
 */
public class DivisiPage extends BasePage {

    // Locators - Header & Breadcrumbs
    private final By pageTitle = By.cssSelector("h1.page-title, .page-title");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");

    // Locators - Search & Action Bar
    private final By formSearch = By.cssSelector("form.form-search, #form-search");
    private final By searchInput = By.cssSelector("input#search, input[name='search'], .input-search");
    private final By searchBtn = By.cssSelector("button#btn-search, .btn-search");
    private final By resetBtn = By.cssSelector("button#btn-reset, .btn-reset");
    private final By tambahDivisiBtn = By.cssSelector("button#btn-tambah-divisi, .button-add");

    // Locators - Table
    private final By table = By.cssSelector("table.table_base, table[aria-label='sticky table']");
    private final By tableHeaders = By.cssSelector("table.table_base thead th");
    private final By tableRows = By.cssSelector("#divisi-table-body tr");
    private final By divisionNameCells = By.cssSelector(".cell-title");
    private final By emptyTableMessage = By.id("empty-table-message");

    // Locators - Modal Tambah Divisi
    private final By modalTambah = By.id("modal-tambah-divisi");
    private final By modalTambahTitle = By.id("alert-dialog-title");
    private final By inputNamaDivisi = By.cssSelector("#name, #input-nama-divisi");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnBatalTambah = By.id("btn-batal-tambah");
    private final By btnCloseTambah = By.cssSelector("#modal-tambah-divisi .modal-close-btn");

    // Locators - Modal Edit Divisi
    private final By modalEdit = By.id("modal-edit-divisi");
    private final By modalEditTitle = By.id("alert-dialog-title-edit");
    private final By inputEditId = By.id("edit-id");
    private final By inputEditNamaDivisi = By.cssSelector("#edit-name, #input-edit-nama-divisi");
    private final By btnSimpanEdit = By.id("btn-simpan-edit");
    private final By btnBatalEdit = By.id("btn-batal-edit");
    private final By btnCloseEdit = By.cssSelector("#modal-edit-divisi .modal-close-btn");

    // Locators - Modal Hapus Divisi
    private final By modalDelete = By.id("modal-delete-divisi");
    private final By modalDeleteTitle = By.id("alert-dialog-slide-title");
    private final By inputDeleteId = By.id("delete-id");
    private final By deleteDivisionNameText = By.id("delete-division-name");
    private final By deleteConfirmText = By.id("delete-confirm-text");
    private final By btnConfirmDelete = By.id("btn-confirm-delete");
    private final By btnBatalDelete = By.id("btn-batal-delete");
    private final By btnCloseDelete = By.cssSelector("#modal-delete-divisi .modal-close-btn");

    // Locators - Action Menus
    private final By actionMenuButtons = By.cssSelector("button[aria-label='action']");
    private final By actionMenus = By.cssSelector(".action-menu");

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
    private final By alertMessage = By.id("alert-message");
    private final By alertCloseBtn = By.cssSelector(".snackbar-close");

    public DivisiPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the Divisi management page.
     */
    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String divisiUrl = baseUrl + "/management/divisi";
        logger.info("Navigating to Divisi management page: {}", divisiUrl);
        driver.get(divisiUrl);
        waitForVisibility(pageTitle);
    }

    // --- Header & Breadcrumbs ---

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isBreadcrumbsDisplayed() {
        return isElementDisplayed(breadcrumbs);
    }

    public String getBreadcrumbsText() {
        return getText(breadcrumbs);
    }

    // --- Search & Filter Bar ---

    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(searchInput);
    }

    public String getSearchInputValue() {
        return driver.findElement(searchInput).getAttribute("value");
    }

    public String getSearchInputPlaceholder() {
        return driver.findElement(searchInput).getAttribute("placeholder");
    }

    public void enterSearchQuery(String query) {
        logger.info("Entering search query: '{}'", query);
        type(searchInput, query);
    }

    public void clickSearch() {
        logger.info("Clicking Search button");
        click(searchBtn);
    }

    public void pressEnterOnSearch() {
        logger.info("Pressing ENTER on search input");
        WebElement input = waitForVisibility(searchInput);
        input.sendKeys(Keys.ENTER);
    }

    public void clickReset() {
        logger.info("Clicking Reset button");
        click(resetBtn);
    }

    public void searchDivision(String keyword) {
        enterSearchQuery(keyword);
        clickSearch();
    }

    public boolean isResetBtnDisplayed() {
        return isElementDisplayed(resetBtn);
    }

    public boolean isSearchBtnDisplayed() {
        return isElementDisplayed(searchBtn);
    }

    // --- Tambahkan Divisi (Add Modal) ---

    public boolean isTambahButtonDisplayed() {
        return isElementDisplayed(tambahDivisiBtn);
    }

    public void clickTambahButton() {
        logger.info("Clicking Tambahkan button to open Tambah Divisi modal");
        click(tambahDivisiBtn);
        waitForVisibility(modalTambah);
    }

    public boolean isModalTambahDisplayed() {
        try {
            List<WebElement> elems = driver.findElements(modalTambah);
            return !elems.isEmpty() && elems.get(0).isDisplayed() && elems.get(0).getAttribute("class").contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTambahTitle() {
        return getText(modalTambahTitle);
    }

    public void enterNamaDivisi(String name) {
        logger.info("Entering Nama Divisi: '{}'", name);
        type(inputNamaDivisi, name);
    }

    public String getNamaDivisiInputValue() {
        return driver.findElement(inputNamaDivisi).getAttribute("value");
    }

    public void clickSimpanTambah() {
        logger.info("Clicking Simpan / Tambah button in modal Tambah");
        click(btnSimpanTambah);
    }

    public void clickBatalTambah() {
        logger.info("Clicking Batal button in modal Tambah");
        click(btnBatalTambah);
    }

    public void clickCloseTambah() {
        logger.info("Clicking Close (X) button in modal Tambah");
        click(btnCloseTambah);
    }

    public void addDivision(String name) {
        clickTambahButton();
        enterNamaDivisi(name);
        clickSimpanTambah();
    }

    // --- Edit Divisi (Edit Modal) ---

    public void clickEditDirect(String divisionName) {
        logger.info("Clicking direct Edit button for division: '{}'", divisionName);
        By editLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//button[contains(@class, 'btn-edit')]");
        safeClick(editLocator);
        waitForVisibility(modalEdit);
    }

    public void clickEditDirectByIndex(int rowIndex) {
        logger.info("Clicking direct Edit button on row index: {}", rowIndex);
        List<WebElement> rows = driver.findElements(tableRows);
        if (rowIndex < rows.size()) {
            WebElement editBtn = rows.get(rowIndex).findElement(By.cssSelector(".btn-edit"));
            safeClickElement(editBtn);
            waitForVisibility(modalEdit);
        } else {
            throw new IndexOutOfBoundsException("Row index " + rowIndex + " out of bounds, total rows: " + rows.size());
        }
    }

    public void clickEditViaDropdown(String divisionName) {
        logger.info("Clicking Edit via 3-dots dropdown menu for division: '{}'", divisionName);
        openActionMenuForDivision(divisionName);
        By menuItemEditLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//button[contains(@class, 'menu-item') and not(contains(@class, 'delete'))]");
        safeClick(menuItemEditLocator);
        waitForVisibility(modalEdit);
    }

    public boolean isModalEditDisplayed() {
        try {
            List<WebElement> elems = driver.findElements(modalEdit);
            return !elems.isEmpty() && elems.get(0).isDisplayed() && elems.get(0).getAttribute("class").contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalEditTitle() {
        return getText(modalEditTitle);
    }

    public String getEditNamaDivisiInputValue() {
        return driver.findElement(inputEditNamaDivisi).getAttribute("value");
    }

    public void enterEditNamaDivisi(String newName) {
        logger.info("Entering updated Nama Divisi: '{}'", newName);
        WebElement input = waitForVisibility(inputEditNamaDivisi);
        input.clear();
        input.sendKeys(newName);
    }

    public void clearEditNamaDivisi() {
        logger.info("Clearing Nama Divisi input in edit modal");
        WebElement input = waitForVisibility(inputEditNamaDivisi);
        input.clear();
        // Also ensure field is cleared via Keys in case of React/MUI controlled input
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public void clickSimpanEdit() {
        logger.info("Clicking Simpan button in modal Edit");
        click(btnSimpanEdit);
    }

    public void clickBatalEdit() {
        logger.info("Clicking Batal button in modal Edit");
        click(btnBatalEdit);
    }

    public void clickCloseEdit() {
        logger.info("Clicking Close (X) button in modal Edit");
        click(btnCloseEdit);
    }

    public void editDivision(String oldName, String newName) {
        clickEditDirect(oldName);
        enterEditNamaDivisi(newName);
        clickSimpanEdit();
    }

    // --- Hapus Divisi (Delete Modal) ---

    public void clickDeleteDirect(String divisionName) {
        logger.info("Clicking direct Delete button for division: '{}'", divisionName);
        By deleteLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//button[contains(@class, 'btn-delete')]");
        safeClick(deleteLocator);
        waitForVisibility(modalDelete);
    }

    public void clickDeleteDirectByIndex(int rowIndex) {
        logger.info("Clicking direct Delete button on row index: {}", rowIndex);
        List<WebElement> rows = driver.findElements(tableRows);
        if (rowIndex < rows.size()) {
            WebElement delBtn = rows.get(rowIndex).findElement(By.cssSelector(".btn-delete"));
            safeClickElement(delBtn);
            waitForVisibility(modalDelete);
        } else {
            throw new IndexOutOfBoundsException("Row index " + rowIndex + " out of bounds, total rows: " + rows.size());
        }
    }

    public void clickDeleteViaDropdown(String divisionName) {
        logger.info("Clicking Delete via 3-dots dropdown menu for division: '{}'", divisionName);
        openActionMenuForDivision(divisionName);
        By menuItemDeleteLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//button[contains(@class, 'menu-item') and contains(@class, 'delete')]");
        safeClick(menuItemDeleteLocator);
        waitForVisibility(modalDelete);
    }

    public boolean isModalDeleteDisplayed() {
        try {
            List<WebElement> elems = driver.findElements(modalDelete);
            return !elems.isEmpty() && elems.get(0).isDisplayed() && elems.get(0).getAttribute("class").contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalDeleteTitle() {
        return getText(modalDeleteTitle);
    }

    public String getDeleteConfirmationText() {
        return getText(deleteConfirmText);
    }

    public String getDeleteDivisionNameText() {
        return getText(deleteDivisionNameText);
    }

    public void clickConfirmDelete() {
        logger.info("Clicking Confirm Delete button in modal Hapus");
        click(btnConfirmDelete);
    }

    public void clickBatalDelete() {
        logger.info("Clicking Batal button in modal Hapus");
        click(btnBatalDelete);
    }

    public void clickCloseDelete() {
        logger.info("Clicking Close (X) button in modal Hapus");
        click(btnCloseDelete);
    }

    public void deleteDivision(String divisionName) {
        clickDeleteDirect(divisionName);
        clickConfirmDelete();
    }

    // --- Action Menu 3-Dots Dropdown ---

    public void openActionMenuForDivision(String divisionName) {
        logger.info("Opening 3-dots action menu for division: '{}'", divisionName);
        By actionBtnLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//button[@aria-label='action']");
        safeClick(actionBtnLocator);
    }

    public boolean isActionMenuDisplayedForDivision(String divisionName) {
        By menuLocator = By.xpath("//tr[contains(., '" + divisionName + "')]//div[contains(@class, 'action-menu')]");
        try {
            WebElement menu = driver.findElement(menuLocator);
            return menu.isDisplayed() && menu.getAttribute("class").contains("show");
        } catch (Exception e) {
            return false;
        }
    }

    // --- Table & Rows ---

    public boolean isTableDisplayed() {
        return isElementDisplayed(table);
    }

    public List<String> getTableHeaders() {
        List<WebElement> headerElems = driver.findElements(tableHeaders);
        List<String> headers = new ArrayList<>();
        for (WebElement elem : headerElems) {
            headers.add(elem.getText().trim());
        }
        return headers;
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        int visibleCount = 0;
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                visibleCount++;
            }
        }
        return visibleCount;
    }

    public List<String> getAllDivisionNames() {
        List<WebElement> cells = driver.findElements(divisionNameCells);
        List<String> names = new ArrayList<>();
        for (WebElement cell : cells) {
            if (cell.isDisplayed()) {
                names.add(cell.getText().trim());
            }
        }
        return names;
    }

    public boolean isDivisionPresent(String divisionName) {
        List<String> allNames = getAllDivisionNames();
        for (String name : allNames) {
            if (name.equalsIgnoreCase(divisionName.trim())) {
                return true;
            }
        }
        return false;
    }

    public String getCreatedDateForDivision(String divisionName) {
        By locator = By.xpath("//tr[contains(., '" + divisionName + "')]/td[2]");
        return getText(locator);
    }

    public String getUpdatedDateForDivision(String divisionName) {
        By locator = By.xpath("//tr[contains(., '" + divisionName + "')]/td[3]");
        return getText(locator);
    }

    public boolean isNoDataMessageDisplayed() {
        try {
            List<WebElement> elems = driver.findElements(emptyTableMessage);
            if (!elems.isEmpty()) {
                return elems.get(0).isDisplayed();
            }
            return !driver.findElements(By.xpath("//td[contains(., 'Tidak ada data divisi ditemukan')]")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoDataMessageText() {
        return getText(emptyTableMessage);
    }

    // --- Pagination ---

    public boolean isPaginationDisplayed() {
        return isElementDisplayed(paginationBar);
    }

    public void selectRowsPerPage(String rowsOption) {
        logger.info("Selecting rows per page: {}", rowsOption);
        WebElement element = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(element);
        select.selectByValue(rowsOption);
    }

    public String getSelectedRowsPerPage() {
        WebElement element = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getDisplayedRowsText() {
        return getText(displayedRowsText);
    }

    public void clickFirstPage() {
        logger.info("Clicking First Page button");
        safeClick(firstPageBtn);
    }

    public void clickPrevPage() {
        logger.info("Clicking Previous Page button");
        safeClick(prevPageBtn);
    }

    public void clickNextPage() {
        logger.info("Clicking Next Page button");
        safeClick(nextPageBtn);
    }

    public void clickLastPage() {
        logger.info("Clicking Last Page button");
        safeClick(lastPageBtn);
    }

    public boolean isFirstPageDisabled() {
        return isButtonDisabled(firstPageBtn);
    }

    public boolean isPrevPageDisabled() {
        return isButtonDisabled(prevPageBtn);
    }

    public boolean isNextPageDisabled() {
        return isButtonDisabled(nextPageBtn);
    }

    public boolean isLastPageDisabled() {
        return isButtonDisabled(lastPageBtn);
    }

    private boolean isButtonDisabled(By locator) {
        try {
            WebElement btn = driver.findElement(locator);
            return !btn.isEnabled() || btn.getAttribute("disabled") != null;
        } catch (Exception e) {
            return false;
        }
    }

    // --- Alert / Snackbar ---

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertSnackbar);
    }

    public String getAlertMessage() {
        waitForVisibility(alertSnackbar);
        return getText(alertMessage);
    }

    public void clickCloseAlert() {
        logger.info("Clicking Close button on alert snackbar");
        click(alertCloseBtn);
    }

    public boolean waitForAlertToDisappear(int timeoutSeconds) {
        try {
            return new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(alertSnackbar));
        } catch (Exception e) {
            return false;
        }
    }

    // --- Safe JavaScript Scrolling & Interaction Helpers ---

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        safeClickElement(element);
    }

    private void safeClickElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            element.click();
        } catch (Exception e) {
            logger.debug("Native click failed, using JavaScript click: {}", e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void pressEscapeKey() {
        logger.info("Sending ESCAPE key to active element and window");
        try {
            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        } catch (Exception e) {
            // ignore
        }
        try {
            new org.openqa.selenium.interactions.Actions(driver).sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {
            // ignore
        }
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.dispatchEvent(new KeyboardEvent('keydown', {'key': 'Escape', 'code': 'Escape', 'keyCode': 27, 'which': 27, bubbles: true}));"
            );
        } catch (Exception e) {
            // ignore
        }
    }
}
