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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for HADIR Management Monthly Schedule page:
 * (https://magang.dikahadir.com/management/monthly-schedule).
 *
 * Implements complete action methods with dynamic waits and non-blocking
 * element checking for headless browsers (Firefox/Chrome).
 */
public class MonthlySchedulePage extends BasePage {

    // Locators - Header & Breadcrumbs
    private final By pageTitle = By.cssSelector("h1#page-title, .page-title");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");

    // Locators - Import Schedule Card
    private final By formImport = By.id("form-import-schedule");
    private final By fileInput = By.id("selfie");
    private final By btnImport = By.id("btn-import");
    private final By btnImportText = By.id("btn-import-text");
    private final By btnDownloadTemplate = By.id("btn-download-template");
    private final By fileErrorMsg = By.id("file-error-msg");

    // Locators - Search & Filter Card
    private final By formFilter = By.id("form-filter-schedule");
    private final By fullnameInput = By.id("fullname");
    private final By btnCari = By.id("btn-cari");
    private final By unitSelect = By.id("jobDepartement");
    private final By btnReset = By.id("btn-reset");

    // Locators - Table
    private final By table = By.id("table-monthly-schedule");
    private final By tableHeaders = By.cssSelector("#table-monthly-schedule thead th");
    private final By tableRows = By.cssSelector("#schedule-table-body tr");
    private final By emptyTableMessage = By.id("empty-table-message");
    private final By cellFullnames = By.cssSelector(".cell-fullname");
    private final By cellUnits = By.cssSelector(".cell-unit");
    private final By cellShifts = By.cssSelector(".cell-shift");
    private final By cellStartTimes = By.cssSelector(".cell-start-time");
    private final By cellEndTimes = By.cssSelector(".cell-end-time");
    private final By cellDates = By.cssSelector(".cell-date");

    // Locators - Action Buttons per Row
    private final By btnEditSchedule = By.cssSelector(".btn-edit-schedule, .action-btn.edit");
    private final By btnDeleteSchedule = By.cssSelector(".btn-delete-schedule, .action-btn.delete");

    // Locators - Pagination Bar
    private final By paginationBar = By.cssSelector(".pagination-bar");
    private final By densePaddingCheckbox = By.id("dense-padding");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By displayedRowsText = By.id("displayed-rows");
    private final By btnFirstPage = By.id("btn-first-page");
    private final By btnPrevPage = By.id("btn-prev-page");
    private final By btnNextPage = By.id("btn-next-page");
    private final By btnLastPage = By.id("btn-last-page");

    // Locators - Modal Dialog: Edit Schedule
    private final By modalEdit = By.id("modal-edit-schedule");
    private final By modalEditTitle = By.id("alert-dialog-title-edit");
    private final By editIdInput = By.id("edit-id");
    private final By editFullnameInput = By.id("edit-fullname");
    private final By editShiftingSelect = By.id("edit-shifting");
    private final By editScheduleDateInput = By.id("edit-schedule-date");
    private final By btnSimpanEdit = By.id("btn-simpan-edit");
    private final By btnBatalEdit = By.id("btn-batal-edit");
    private final By btnCloseEdit = By.cssSelector("#modal-edit-schedule .action-btn");

    // Locators - Modal Dialog: Delete Schedule
    private final By modalDelete = By.id("modal-delete-schedule");
    private final By modalDeleteTitle = By.id("alert-dialog-title-delete");
    private final By deleteIdInput = By.id("delete-id");
    private final By deleteConfirmText = By.id("delete-confirm-text");
    private final By btnConfirmDelete = By.id("btn-confirm-delete");
    private final By btnBatalDelete = By.id("btn-batal-delete");
    private final By btnCloseDelete = By.cssSelector("#modal-delete-schedule .action-btn");

    // Locators - Toast / Snackbar
    private final By snackbar = By.id("alert-snackbar");
    private final By alertMessage = By.id("alert-message");
    private final By snackbarClose = By.cssSelector(".snackbar-close");

    public MonthlySchedulePage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // 1. Navigation & Page Load
    // ==========================================

    public void navigateToMonthlySchedulePage() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/management/monthly-schedule";
        logger.info("Navigating to Monthly Schedule management page: {}", url);
        driver.get(url);
    }

    public boolean isPageTitleDisplayed() {
        return isFastDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isBreadcrumbsDisplayed() {
        return isFastDisplayed(breadcrumbs);
    }

    public String getBreadcrumbsText() {
        return getText(breadcrumbs);
    }

    public boolean isImportCardDisplayed() {
        return isFastDisplayed(formImport);
    }

    public boolean isTableDisplayed() {
        return isFastDisplayed(table);
    }

    public boolean isPaginationBarDisplayed() {
        return isFastDisplayed(paginationBar);
    }

    // ==========================================
    // 2. Import Excel Jadwal Bulanan
    // ==========================================

    public void uploadScheduleFile(String filePath) {
        logger.info("Selecting file for import: {}", filePath);
        File file = new File(filePath);
        WebElement input = driver.findElement(fileInput);
        input.sendKeys(file.getAbsolutePath());
    }

    public void clickImportButton() {
        logger.info("Clicking Import button");
        click(btnImport);
    }

    public String getImportButtonText() {
        return getText(btnImportText);
    }

    public boolean isImportButtonEnabled() {
        return driver.findElement(btnImport).isEnabled();
    }

    public void clickDownloadTemplateButton() {
        logger.info("Clicking Download Template button");
        click(btnDownloadTemplate);
    }

    public boolean isDownloadTemplateButtonDisplayed() {
        return isFastDisplayed(btnDownloadTemplate);
    }

    public boolean isFileErrorMessageDisplayed() {
        return isFastDisplayed(fileErrorMsg);
    }

    public String getFileErrorMessage() {
        return getText(fileErrorMsg);
    }

    // ==========================================
    // 3. Search & Filter
    // ==========================================

    public void enterFullnameSearch(String fullname) {
        logger.info("Entering search name: '{}'", fullname);
        type(fullnameInput, fullname);
    }

    public void clearFullnameSearch() {
        logger.info("Clearing search name input");
        WebElement input = waitForVisibility(fullnameInput);
        input.clear();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public String getFullnameInputValue() {
        return driver.findElement(fullnameInput).getAttribute("value");
    }

    public void pressEnterOnFullnameSearch() {
        logger.info("Pressing Enter on fullname search input");
        WebElement input = waitForVisibility(fullnameInput);
        input.sendKeys(Keys.ENTER);
    }

    public void clickCariButton() {
        logger.info("Clicking Cari button");
        click(btnCari);
    }

    public void selectUnitFilter(String unitName) {
        logger.info("Selecting Unit filter: '{}'", unitName);
        WebElement selectElement = waitForVisibility(unitSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(unitName);
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                    "document.getElementById('form-filter-schedule').dispatchEvent(new Event('submit', { cancelable: true, bubbles: true }));",
                    selectElement
            );
        } catch (Exception e) {
            logger.warn("Dispatch filter submit failed: {}", e.getMessage());
        }
    }

    public String getSelectedUnitFilter() {
        Select select = new Select(driver.findElement(unitSelect));
        return select.getFirstSelectedOption().getText().trim();
    }

    public List<String> getAvailableUnitOptions() {
        Select select = new Select(driver.findElement(unitSelect));
        List<String> options = new ArrayList<>();
        for (WebElement opt : select.getOptions()) {
            options.add(opt.getText().trim());
        }
        return options;
    }

    public void clickResetButton() {
        logger.info("Clicking Reset filter button");
        click(btnReset);
    }

    // ==========================================
    // 4. Table Data & Rows
    // ==========================================

    public List<String> getTableHeaders() {
        List<WebElement> elements = driver.findElements(tableHeaders);
        List<String> headers = new ArrayList<>();
        for (WebElement el : elements) {
            headers.add(el.getText().trim());
        }
        return headers;
    }

    public int getRowCount() {
        if (isEmptyTableMessageDisplayed()) {
            return 0;
        }
        return driver.findElements(tableRows).size();
    }

    public List<String> getFullnameList() {
        List<WebElement> elements = driver.findElements(cellFullnames);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public List<String> getUnitList() {
        List<WebElement> elements = driver.findElements(cellUnits);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public List<String> getShiftingList() {
        List<WebElement> elements = driver.findElements(cellShifts);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public List<String> getStartTimesList() {
        List<WebElement> elements = driver.findElements(cellStartTimes);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public List<String> getEndTimesList() {
        List<WebElement> elements = driver.findElements(cellEndTimes);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public List<String> getScheduleDateList() {
        List<WebElement> elements = driver.findElements(cellDates);
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText().trim());
        }
        return list;
    }

    public boolean isEmptyTableMessageDisplayed() {
        List<WebElement> elements = driver.findElements(emptyTableMessage);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public String getEmptyTableMessageText() {
        return getText(emptyTableMessage);
    }

    public void clickEditOnRow(int rowIndex) {
        logger.info("Clicking Edit button on row index: {}", rowIndex);
        List<WebElement> editBtns = driver.findElements(btnEditSchedule);
        if (rowIndex < editBtns.size()) {
            WebElement btn = editBtns.get(rowIndex);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
            btn.click();
        } else {
            throw new IndexOutOfBoundsException("Row index " + rowIndex + " out of bounds, total rows: " + editBtns.size());
        }
    }

    public void clickDeleteOnRow(int rowIndex) {
        logger.info("Clicking Delete button on row index: {}", rowIndex);
        List<WebElement> deleteBtns = driver.findElements(btnDeleteSchedule);
        if (rowIndex < deleteBtns.size()) {
            WebElement btn = deleteBtns.get(rowIndex);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
            btn.click();
        } else {
            throw new IndexOutOfBoundsException("Row index " + rowIndex + " out of bounds, total rows: " + deleteBtns.size());
        }
    }

    // ==========================================
    // 5. Pagination Bar
    // ==========================================

    public void selectRowsPerPage(String rows) {
        logger.info("Setting rows per page to: {}", rows);
        WebElement element = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(element);
        select.selectByVisibleText(rows);
    }

    public String getDisplayedRowsText() {
        return getText(displayedRowsText);
    }

    public void clickFirstPage() {
        logger.info("Clicking First page button");
        click(btnFirstPage);
    }

    public void clickPrevPage() {
        logger.info("Clicking Previous page button");
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
        logger.info("Setting dense padding to: {}", enable);
        WebElement checkbox = driver.findElement(densePaddingCheckbox);
        if (checkbox.isSelected() != enable) {
            checkbox.click();
        }
    }

    public boolean isDensePaddingEnabled() {
        return driver.findElement(densePaddingCheckbox).isSelected();
    }

    // ==========================================
    // 6. Modal Dialog: Edit Schedule
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

    public String getModalEditTitleText() {
        return getText(modalEditTitle);
    }

    public String getEditFullnameValue() {
        return driver.findElement(editFullnameInput).getAttribute("value");
    }

    public boolean isEditFullnameDisabled() {
        String disabled = driver.findElement(editFullnameInput).getAttribute("disabled");
        return disabled != null && (disabled.equals("true") || disabled.equals("disabled"));
    }

    public String getSelectedEditShifting() {
        Select select = new Select(driver.findElement(editShiftingSelect));
        return select.getFirstSelectedOption().getText().trim();
    }

    public List<String> getAvailableEditShiftingOptions() {
        Select select = new Select(driver.findElement(editShiftingSelect));
        List<String> options = new ArrayList<>();
        for (WebElement opt : select.getOptions()) {
            options.add(opt.getText().trim());
        }
        return options;
    }

    public void selectEditShifting(String shiftName) {
        logger.info("Selecting Shifting in Edit modal: '{}'", shiftName);
        Select select = new Select(waitForVisibility(editShiftingSelect));
        if (shiftName == null || shiftName.trim().isEmpty()) {
            select.selectByIndex(0);
            return;
        }
        for (WebElement opt : select.getOptions()) {
            String optText = opt.getText().trim();
            String optVal = opt.getAttribute("value") != null ? opt.getAttribute("value").trim() : "";
            if (optText.equalsIgnoreCase(shiftName) || optVal.equalsIgnoreCase(shiftName) ||
                optText.toLowerCase().contains(shiftName.toLowerCase()) || optVal.toLowerCase().contains(shiftName.toLowerCase())) {
                opt.click();
                return;
            }
        }
        select.selectByVisibleText(shiftName);
    }

    public String getEditScheduleDateValue() {
        return driver.findElement(editScheduleDateInput).getAttribute("value");
    }

    public void setEditScheduleDate(String dateYyyyMmDd) {
        logger.info("Setting Schedule Date in Edit modal: '{}'", dateYyyyMmDd);
        WebElement dateField = waitForVisibility(editScheduleDateInput);
        dateField.clear();
        dateField.sendKeys(dateYyyyMmDd);
    }

    public void clickSimpanEdit() {
        logger.info("Clicking Simpan button in Edit modal");
        click(btnSimpanEdit);
    }

    public void clickBatalEdit() {
        logger.info("Clicking Cancel button in Edit modal");
        click(btnBatalEdit);
    }

    public void clickCloseModalEdit() {
        logger.info("Clicking close (X) in Edit modal");
        click(btnCloseEdit);
    }

    // ==========================================
    // 7. Modal Dialog: Delete Schedule
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

    public String getModalDeleteTitleText() {
        return getText(modalDeleteTitle);
    }

    public String getDeleteConfirmText() {
        return getText(deleteConfirmText);
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
    // 8. Escape Key & Global Actions
    // ==========================================

    public void pressEscapeKey() {
        logger.info("Pressing ESCAPE key to dismiss modals");
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
    // 9. Toast / Snackbar Notifikasi
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

    // ==========================================
    // Helper non-blocking
    // ==========================================

    private boolean isFastDisplayed(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
