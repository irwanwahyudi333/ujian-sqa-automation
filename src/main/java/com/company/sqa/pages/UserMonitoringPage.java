package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for HADIR Management User Monitoring page:
 * (https://magang.dikahadir.com/management/user-monitoring).
 *
 * Implements complete action methods with dynamic waits and non-blocking
 * element checking (driver.findElements().isEmpty()) for fast headless execution
 * and zero delay on negative scenario validations.
 */
public class UserMonitoringPage extends BasePage {

    // ==========================================
    // Locators - Header & Breadcrumbs
    // ==========================================
    private final By pageTitle = By.cssSelector("#page-title, .page-title, h1");
    private final By breadcrumbCurrent = By.cssSelector("#breadcrumb-current, .breadcrumb-current");
    private final By breadcrumbHome = By.cssSelector("#breadcrumb-home, .breadcrumb-home");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");

    // ==========================================
    // Locators - Search & Toolbar Card
    // ==========================================
    private final By formSearch = By.id("form-search");
    private final By searchInput = By.id("search");
    private final By btnFilter = By.id("btn-filter");
    private final By btnReset = By.id("btn-reset");
    private final By btnSearch = By.id("btn-search");
    private final By btnTambah = By.id("btn-tambah");

    // ==========================================
    // Locators - Table
    // ==========================================
    private final By table = By.id("user-monitoring-table");
    private final By tableBody = By.id("user-monitoring-table-body");
    private final By tableHeaders = By.cssSelector("#user-monitoring-table thead th");
    private final By tableRows = By.cssSelector("#user-monitoring-table-body tr");
    private final By emptyTableMessage = By.id("empty-table-message");

    // Headers
    private final By thPhoto = By.id("th-photo");
    private final By thFullname = By.id("th-fullname");
    private final By thEmail = By.id("th-email");
    private final By thUnit = By.id("th-unit");
    private final By thCreatedAt = By.id("th-created-at");
    private final By thAction = By.id("th-action");

    // Cells
    private final By cellPhotos = By.cssSelector(".cell-photo");
    private final By cellFullnames = By.cssSelector(".cell-fullname");
    private final By cellEmails = By.cssSelector(".cell-email");
    private final By cellUnits = By.cssSelector(".cell-unit");
    private final By cellCreatedAts = By.cssSelector(".cell-created-at");

    // Row Action Buttons
    private final By btnEditUsers = By.cssSelector(".btn-edit-user, .action-btn.edit");
    private final By btnDeleteUsers = By.cssSelector(".btn-delete-user, .action-btn.delete");

    // ==========================================
    // Locators - Modal Dialog: Filter User
    // ==========================================
    private final By modalFilter = By.id("modal-filter-user");
    private final By modalFilterTitle = By.id("alert-dialog-title-filter");
    private final By formFilter = By.id("form-filter-user");
    private final By unitSelectFilter = By.id("job_departement_id");
    private final By btnBatalFilter = By.id("btn-batal-filter");
    private final By btnTerapkanFilter = By.id("btn-terapkan-filter");
    private final By btnCloseFilter = By.cssSelector("#modal-filter-user .action-btn, #modal-filter-user .modal-close");

    // ==========================================
    // Locators - Modal Dialog: Tambah User
    // ==========================================
    private final By modalTambah = By.id("modal-tambah-user");
    private final By modalTambahTitle = By.id("alert-dialog-title-add");
    private final By formTambah = By.id("form-tambah-user");
    private final By addFullnameInput = By.id("add-fullname");
    private final By addEmailInput = By.id("add-email");
    private final By addPasswordInput = By.id("add-password");
    private final By addUnitSelect = By.id("add-unit");
    private final By btnBatalTambah = By.id("btn-batal-tambah");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnCloseTambah = By.cssSelector("#modal-tambah-user .action-btn, #modal-tambah-user .modal-close");

    // ==========================================
    // Locators - Modal Dialog: Edit User
    // ==========================================
    private final By modalEdit = By.id("modal-edit-user");
    private final By modalEditTitle = By.id("alert-dialog-title-edit");
    private final By formEdit = By.id("form-edit-user");
    private final By editIdInput = By.id("edit-id");
    private final By editFullnameInput = By.id("edit-fullname");
    private final By editEmailInput = By.id("edit-email");
    private final By editPasswordInput = By.id("edit-password");
    private final By editUnitSelect = By.id("edit-unit");
    private final By btnBatalEdit = By.id("btn-batal-edit");
    private final By btnSimpanEdit = By.id("btn-simpan-edit");
    private final By btnCloseEdit = By.cssSelector("#modal-edit-user .action-btn, #modal-edit-user .modal-close");

    // ==========================================
    // Locators - Modal Dialog: Hapus User
    // ==========================================
    private final By modalDelete = By.id("modal-delete-user");
    private final By modalDeleteTitle = By.id("alert-dialog-title-delete");
    private final By formDelete = By.id("form-delete-user");
    private final By deleteIdInput = By.id("delete-id");
    private final By deleteConfirmText = By.id("delete-confirm-text");
    private final By btnBatalDelete = By.id("btn-batal-delete");
    private final By btnConfirmDelete = By.id("btn-confirm-delete");
    private final By btnCloseDelete = By.cssSelector("#modal-delete-user .action-btn, #modal-delete-user .modal-close");

    // ==========================================
    // Locators - Pagination Bar
    // ==========================================
    private final By paginationContainer = By.id("pagination-container");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By displayedRowsText = By.id("displayed-rows");
    private final By btnFirstPage = By.id("btn-first-page");
    private final By btnPrevPage = By.id("btn-prev-page");
    private final By btnNextPage = By.id("btn-next-page");
    private final By btnLastPage = By.id("btn-last-page");
    private final By checkboxDense = By.id("checkbox-dense");

    // ==========================================
    // Locators - Toast / Snackbar
    // ==========================================
    private final By snackbar = By.id("alert-snackbar");
    private final By alertMessage = By.id("alert-message");
    private final By snackbarClose = By.cssSelector(".snackbar-close");

    public UserMonitoringPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // 1. Navigation & Page Load
    // ==========================================

    public void navigateToUserMonitoringPage() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/management/user-monitoring";
        logger.info("Navigating to User Monitoring page: {}", url);
        driver.get(url);
    }

    public boolean isPageLoaded() {
        return isPageTitleDisplayed() && isTableDisplayed();
    }

    public boolean isPageTitleDisplayed() {
        return isFastDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isBreadcrumbsDisplayed() {
        return isFastDisplayed(breadcrumbs) || isFastDisplayed(breadcrumbCurrent);
    }

    public String getBreadcrumbsText() {
        if (isFastDisplayed(breadcrumbCurrent)) {
            return getText(breadcrumbCurrent);
        }
        return isFastDisplayed(breadcrumbs) ? getText(breadcrumbs) : "";
    }

    public boolean isFormSearchDisplayed() {
        return isFastDisplayed(formSearch);
    }

    public boolean isTableDisplayed() {
        return isFastDisplayed(table);
    }

    public boolean isPaginationContainerDisplayed() {
        return isFastDisplayed(paginationContainer);
    }

    // ==========================================
    // 2. Search & Toolbar Actions
    // ==========================================

    public void enterSearchName(String name) {
        logger.info("Entering search name: '{}'", name);
        type(searchInput, name);
    }

    public void clearSearchInput() {
        logger.info("Clearing search input");
        WebElement input = waitForVisibility(searchInput);
        input.clear();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public String getSearchInputValue() {
        return driver.findElement(searchInput).getAttribute("value");
    }

    public void clickSearchButton() {
        logger.info("Clicking Search button");
        click(btnSearch);
    }

    public void pressEnterOnSearchInput() {
        logger.info("Submitting search via ENTER key");
        WebElement input = waitForVisibility(searchInput);
        input.sendKeys(Keys.ENTER);
    }

    public void clickResetButton() {
        logger.info("Clicking Reset button");
        click(btnReset);
    }

    public void clickFilterButton() {
        logger.info("Clicking Filter button to open filter modal");
        click(btnFilter);
    }

    public void clickTambahButton() {
        logger.info("Clicking Tambahkan button to open add modal");
        click(btnTambah);
    }

    // ==========================================
    // 3. Table Rows & Data Methods
    // ==========================================

    public int getTableRowsCount() {
        if (isEmptyTableMessageDisplayed()) {
            return 0;
        }
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public boolean isEmptyTableMessageDisplayed() {
        return isFastDisplayed(emptyTableMessage);
    }

    public String getEmptyTableMessageText() {
        return isEmptyTableMessageDisplayed() ? getText(emptyTableMessage) : "";
    }

    public List<String> getAllFullnames() {
        List<String> names = new ArrayList<>();
        List<WebElement> elements = driver.findElements(cellFullnames);
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();
        List<WebElement> elements = driver.findElements(cellEmails);
        for (WebElement el : elements) {
            emails.add(el.getText().trim());
        }
        return emails;
    }

    public List<String> getAllUnits() {
        List<String> units = new ArrayList<>();
        List<WebElement> elements = driver.findElements(cellUnits);
        for (WebElement el : elements) {
            units.add(el.getText().trim());
        }
        return units;
    }

    public List<String> getAllCreatedAts() {
        List<String> createdAts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(cellCreatedAts);
        for (WebElement el : elements) {
            createdAts.add(el.getText().trim());
        }
        return createdAts;
    }

    public boolean isRowWithFullnameDisplayed(String name) {
        List<String> names = getAllFullnames();
        for (String n : names) {
            if (n.equalsIgnoreCase(name) || n.toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean isRowWithIdDisplayed(String id) {
        By rowLocator = By.id("user-row-" + id);
        return isFastDisplayed(rowLocator);
    }

    public void clickEditUserById(String id) {
        logger.info("Clicking Edit button for user ID: {}", id);
        By locator = By.id("btn-edit-" + id);
        click(locator);
    }

    public void clickDeleteUserById(String id) {
        logger.info("Clicking Delete button for user ID: {}", id);
        By locator = By.id("btn-delete-" + id);
        click(locator);
    }

    public void clickFirstEditButton() {
        logger.info("Clicking first available Edit user button");
        List<WebElement> editButtons = driver.findElements(btnEditUsers);
        if (!editButtons.isEmpty()) {
            editButtons.get(0).click();
        } else {
            throw new RuntimeException("No edit user buttons found in table");
        }
    }

    public void clickFirstDeleteButton() {
        logger.info("Clicking first available Delete user button");
        List<WebElement> deleteButtons = driver.findElements(btnDeleteUsers);
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
        } else {
            throw new RuntimeException("No delete user buttons found in table");
        }
    }

    public boolean isPhotoOrAvatarDisplayedInRow(int rowIndex) {
        List<WebElement> photos = driver.findElements(cellPhotos);
        if (rowIndex >= 0 && rowIndex < photos.size()) {
            return photos.get(rowIndex).isDisplayed();
        }
        return false;
    }

    // ==========================================
    // 4. Modal Dialog: Filter User
    // ==========================================

    public boolean isModalFilterDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(modalFilter);
            if (elements.isEmpty()) return false;
            String classes = elements.get(0).getAttribute("class");
            return classes != null && classes.contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalFilterTitle() {
        return getText(modalFilterTitle);
    }

    public void selectUnitFilter(String unit) {
        logger.info("Selecting unit in Filter modal: '{}'", unit);
        Select select = new Select(waitForVisibility(unitSelectFilter));
        if (unit == null || unit.trim().isEmpty() || unit.equalsIgnoreCase("Semua Unit")) {
            select.selectByIndex(0);
            return;
        }
        for (WebElement opt : select.getOptions()) {
            String optText = opt.getText().trim();
            String optVal = opt.getAttribute("value") != null ? opt.getAttribute("value").trim() : "";
            if (optText.equalsIgnoreCase(unit) || optVal.equalsIgnoreCase(unit) ||
                optText.toLowerCase().contains(unit.toLowerCase())) {
                opt.click();
                return;
            }
        }
        select.selectByVisibleText(unit);
    }

    public String getSelectedUnitFilter() {
        Select select = new Select(driver.findElement(unitSelectFilter));
        return select.getFirstSelectedOption().getText().trim();
    }

    public List<String> getAvailableUnitFilterOptions() {
        Select select = new Select(driver.findElement(unitSelectFilter));
        List<String> options = new ArrayList<>();
        for (WebElement opt : select.getOptions()) {
            options.add(opt.getText().trim());
        }
        return options;
    }

    public void clickTerapkanFilter() {
        logger.info("Clicking Terapkan button in Filter modal");
        click(btnTerapkanFilter);
    }

    public void clickBatalFilter() {
        logger.info("Clicking Batal button in Filter modal");
        click(btnBatalFilter);
    }

    public void clickCloseModalFilter() {
        logger.info("Clicking close (X) in Filter modal");
        click(btnCloseFilter);
    }

    // ==========================================
    // 5. Modal Dialog: Tambah User
    // ==========================================

    public boolean isModalTambahDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(modalTambah);
            if (elements.isEmpty()) return false;
            String classes = elements.get(0).getAttribute("class");
            return classes != null && classes.contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTambahTitle() {
        return getText(modalTambahTitle);
    }

    public void enterAddFullname(String fullname) {
        logger.info("Entering Add Fullname: '{}'", fullname);
        type(addFullnameInput, fullname);
    }

    public void enterAddEmail(String email) {
        logger.info("Entering Add Email: '{}'", email);
        type(addEmailInput, email);
    }

    public void enterAddPassword(String password) {
        logger.info("Entering Add Password");
        type(addPasswordInput, password);
    }

    public void selectAddUnit(String unit) {
        logger.info("Selecting Add Unit: '{}'", unit);
        Select select = new Select(waitForVisibility(addUnitSelect));
        if (unit == null || unit.trim().isEmpty()) {
            select.selectByIndex(0);
            return;
        }
        for (WebElement opt : select.getOptions()) {
            String optText = opt.getText().trim();
            String optVal = opt.getAttribute("value") != null ? opt.getAttribute("value").trim() : "";
            if (optText.equalsIgnoreCase(unit) || optVal.equalsIgnoreCase(unit) ||
                optText.toLowerCase().contains(unit.toLowerCase())) {
                opt.click();
                return;
            }
        }
        select.selectByVisibleText(unit);
    }

    public void fillAddUserForm(String fullname, String email, String password, String unit) {
        if (fullname != null) enterAddFullname(fullname);
        if (email != null) enterAddEmail(email);
        if (password != null) enterAddPassword(password);
        if (unit != null) selectAddUnit(unit);
    }

    public String getAddFullnameValue() {
        return driver.findElement(addFullnameInput).getAttribute("value");
    }

    public String getAddEmailValue() {
        return driver.findElement(addEmailInput).getAttribute("value");
    }

    public String getAddPasswordValue() {
        return driver.findElement(addPasswordInput).getAttribute("value");
    }

    public String getAddUnitValue() {
        Select select = new Select(driver.findElement(addUnitSelect));
        return select.getFirstSelectedOption().getText().trim();
    }

    public void clickSimpanTambah() {
        logger.info("Clicking Tambah button in Add modal");
        click(btnSimpanTambah);
    }

    public void clickBatalTambah() {
        logger.info("Clicking Batal button in Add modal");
        click(btnBatalTambah);
    }

    public void clickCloseModalTambah() {
        logger.info("Clicking close (X) in Add modal");
        click(btnCloseTambah);
    }

    // ==========================================
    // 6. Modal Dialog: Edit User
    // ==========================================

    public boolean isModalEditDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(modalEdit);
            if (elements.isEmpty()) return false;
            String classes = elements.get(0).getAttribute("class");
            return classes != null && classes.contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalEditTitle() {
        return getText(modalEditTitle);
    }

    public String getEditIdValue() {
        return driver.findElement(editIdInput).getAttribute("value");
    }

    public void enterEditFullname(String fullname) {
        logger.info("Entering Edit Fullname: '{}'", fullname);
        type(editFullnameInput, fullname);
    }

    public String getEditFullnameValue() {
        return driver.findElement(editFullnameInput).getAttribute("value");
    }

    public void enterEditEmail(String email) {
        logger.info("Entering Edit Email: '{}'", email);
        type(editEmailInput, email);
    }

    public String getEditEmailValue() {
        return driver.findElement(editEmailInput).getAttribute("value");
    }

    public void enterEditPassword(String password) {
        logger.info("Entering Edit Password");
        type(editPasswordInput, password);
    }

    public String getEditPasswordValue() {
        return driver.findElement(editPasswordInput).getAttribute("value");
    }

    public void selectEditUnit(String unit) {
        logger.info("Selecting Edit Unit: '{}'", unit);
        Select select = new Select(waitForVisibility(editUnitSelect));
        if (unit == null || unit.trim().isEmpty()) {
            select.selectByIndex(0);
            return;
        }
        for (WebElement opt : select.getOptions()) {
            String optText = opt.getText().trim();
            String optVal = opt.getAttribute("value") != null ? opt.getAttribute("value").trim() : "";
            if (optText.equalsIgnoreCase(unit) || optVal.equalsIgnoreCase(unit) ||
                optText.toLowerCase().contains(unit.toLowerCase())) {
                opt.click();
                return;
            }
        }
        select.selectByVisibleText(unit);
    }

    public String getEditUnitValue() {
        Select select = new Select(driver.findElement(editUnitSelect));
        return select.getFirstSelectedOption().getText().trim();
    }

    public void fillEditUserForm(String fullname, String email, String password, String unit) {
        if (fullname != null) enterEditFullname(fullname);
        if (email != null) enterEditEmail(email);
        if (password != null) enterEditPassword(password);
        if (unit != null) selectEditUnit(unit);
    }

    public void clickSimpanEdit() {
        logger.info("Clicking Simpan button in Edit modal");
        click(btnSimpanEdit);
    }

    public void clickBatalEdit() {
        logger.info("Clicking Batal button in Edit modal");
        click(btnBatalEdit);
    }

    public void clickCloseModalEdit() {
        logger.info("Clicking close (X) in Edit modal");
        click(btnCloseEdit);
    }

    // ==========================================
    // 7. Modal Dialog: Hapus User
    // ==========================================

    public boolean isModalDeleteDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(modalDelete);
            if (elements.isEmpty()) return false;
            String classes = elements.get(0).getAttribute("class");
            return classes != null && classes.contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalDeleteTitle() {
        return getText(modalDeleteTitle);
    }

    public String getDeleteConfirmText() {
        return getText(deleteConfirmText);
    }

    public String getDeleteIdValue() {
        return driver.findElement(deleteIdInput).getAttribute("value");
    }

    public void clickConfirmDelete() {
        logger.info("Clicking Hapus confirm button in Delete modal");
        click(btnConfirmDelete);
    }

    public void clickBatalDelete() {
        logger.info("Clicking Tidak button in Delete modal");
        click(btnBatalDelete);
    }

    public void clickCloseModalDelete() {
        logger.info("Clicking close (X) in Delete modal");
        click(btnCloseDelete);
    }

    // ==========================================
    // 8. Pagination Bar & Display Controls
    // ==========================================

    public void selectRowsPerPage(String rows) {
        logger.info("Selecting rows per page: '{}'", rows);
        Select select = new Select(waitForVisibility(rowsPerPageSelect));
        select.selectByVisibleText(rows);
    }

    public String getSelectedRowsPerPage() {
        Select select = new Select(driver.findElement(rowsPerPageSelect));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getDisplayedRowsText() {
        return getText(displayedRowsText);
    }

    public void clickFirstPage() {
        logger.info("Clicking First page button");
        click(btnFirstPage);
    }

    public void clickPrevPage() {
        logger.info("Clicking Prev page button");
        click(btnPrevPage);
    }

    public void clickNextPage() {
        logger.info("Clicking Next page button");
        click(btnNextPage);
    }

    public void clickLastPage() {
        logger.info("Clicking Last page button");
        click(btnLastPage);
    }

    public boolean isFirstPageButtonEnabled() {
        return driver.findElement(btnFirstPage).isEnabled();
    }

    public boolean isPrevPageButtonEnabled() {
        return driver.findElement(btnPrevPage).isEnabled();
    }

    public boolean isNextPageButtonEnabled() {
        return driver.findElement(btnNextPage).isEnabled();
    }

    public boolean isLastPageButtonEnabled() {
        return driver.findElement(btnLastPage).isEnabled();
    }

    public void toggleDensePadding(boolean enable) {
        logger.info("Setting dense padding checkbox to: {}", enable);
        WebElement checkbox = driver.findElement(checkboxDense);
        if (checkbox.isSelected() != enable) {
            checkbox.click();
        }
    }

    public boolean isDensePaddingEnabled() {
        return driver.findElement(checkboxDense).isSelected();
    }

    // ==========================================
    // 9. Keyboard Actions & Escape Key
    // ==========================================

    public void pressEscapeKey() {
        logger.info("Simulating ESCAPE key to dismiss modals");
        try {
            new Actions(driver).sendKeys(Keys.ESCAPE).perform();
            ((JavascriptExecutor) driver).executeScript(
                    "document.dispatchEvent(new KeyboardEvent('keydown', {'key': 'Escape', 'keyCode': 27, 'bubbles': true}));"
            );
        } catch (Exception e) {
            logger.warn("Escape key trigger failed: {}", e.getMessage());
        }
    }

    // ==========================================
    // 10. Toast / Snackbar Notifications
    // ==========================================

    public boolean isSnackbarDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(snackbar);
            if (elements.isEmpty()) return false;
            String classes = elements.get(0).getAttribute("class");
            return classes != null && classes.contains("open");
        } catch (Exception e) {
            return false;
        }
    }

    public String getToastMessage() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
            return msg.getText().trim();
        } catch (Exception e) {
            logger.warn("Could not retrieve snackbar message text: {}", e.getMessage());
            return "";
        }
    }

    public void clickCloseSnackbar() {
        logger.info("Clicking close on snackbar");
        click(snackbarClose);
    }

    public void waitForSnackbarToDisappear(int timeoutSeconds) {
        try {
            long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
            while (System.currentTimeMillis() < endTime) {
                if (!isSnackbarDisplayed()) {
                    return;
                }
                Thread.sleep(200);
            }
        } catch (Exception ignored) {}
    }

    // ==========================================
    // Helper non-blocking fast display check
    // ==========================================

    private boolean isFastDisplayed(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getTableHeaders() {
        List<String> headers = new ArrayList<>();
        List<WebElement> elements = driver.findElements(tableHeaders);
        for (WebElement el : elements) {
            headers.add(el.getText().trim());
        }
        return headers;
    }

    public boolean isAddFormRequired(String fieldName) {
        By locator = null;
        if (fieldName.equalsIgnoreCase("nama") || fieldName.equalsIgnoreCase("fullname")) locator = addFullnameInput;
        else if (fieldName.equalsIgnoreCase("email")) locator = addEmailInput;
        else if (fieldName.equalsIgnoreCase("password")) locator = addPasswordInput;
        else if (fieldName.equalsIgnoreCase("unit")) locator = addUnitSelect;
        
        if (locator == null) return false;
        try {
            WebElement el = driver.findElement(locator);
            String req = el.getAttribute("required");
            return req != null && (req.equals("true") || req.equals("required") || req.isEmpty());
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailByFullname(String fullname) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".cell-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                List<WebElement> emailEls = row.findElements(By.cssSelector(".cell-email"));
                return emailEls.isEmpty() ? "" : emailEls.get(0).getText().trim();
            }
        }
        return "";
    }

    public String getUnitByFullname(String fullname) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".cell-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                List<WebElement> unitEls = row.findElements(By.cssSelector(".cell-unit"));
                return unitEls.isEmpty() ? "" : unitEls.get(0).getText().trim();
            }
        }
        return "";
    }

    public void clickEditUserByFullname(String fullname) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".cell-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-edit-user, .action-btn.edit"));
                btn.click();
                return;
            }
        }
        throw new RuntimeException("User with name '" + fullname + "' not found to edit!");
    }

    public void clickDeleteUserByFullname(String fullname) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".cell-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-delete-user, .action-btn.delete"));
                btn.click();
                return;
            }
        }
        throw new RuntimeException("User with name '" + fullname + "' not found to delete!");
    }

    public void clickEditButtonInRow(int index) {
        List<WebElement> buttons = driver.findElements(btnEditUsers);
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).click();
        } else {
            throw new RuntimeException("Edit button index " + index + " not found!");
        }
    }

    public void clickDeleteButtonInRow(int index) {
        List<WebElement> buttons = driver.findElements(btnDeleteUsers);
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).click();
        } else {
            throw new RuntimeException("Delete button index " + index + " not found!");
        }
    }

    public String getFirstRowFullname() {
        List<WebElement> names = driver.findElements(cellFullnames);
        return names.isEmpty() ? "" : names.get(0).getText().trim();
    }

    public boolean isSearchInputEmpty() {
        String val = getSearchInputValue();
        return val == null || val.trim().isEmpty();
    }

}
