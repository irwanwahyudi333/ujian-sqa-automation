package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for Hadir Management Self Registration page:
 * (https://magang.dikahadir.com/management/self-registration).
 */
public class SelfRegistrationPage extends BasePage {

    // Locators - Page Header
    private final By pageTitle = By.xpath("//h2[contains(text(), 'Data User Registrasi Mobile')] | //h4[contains(text(), 'Data User Registrasi Mobile')]");

    // Locators - Search & Filter Bar
    private final By searchTypeSelect = By.id("search-type");
    private final By searchInput = By.id("search");
    private final By searchBtn = By.id("btn-search");
    private final By filterBtn = By.id("btn-filter");
    private final By resetBtn = By.id("btn-reset");

    // Locators - Bulk Action Toolbar
    private final By bulkToolbar = By.id("bulk-action-toolbar");
    private final By bulkUpdateBtn = By.id("btn-bulk-update");
    private final By selectedCountText = By.id("selected-count");

    // Locators - Table
    private final By table = By.cssSelector("table.table_base, table[aria-label='simple table']");
    private final By selectAllCheckbox = By.id("select-all-checkbox");
    private final By tableRows = By.cssSelector("#user-table-body tr");
    private final By rowCheckboxes = By.cssSelector(".row-checkbox");
    private final By viewButtons = By.cssSelector(".btn-view, [data-testid='view-data-btn']");
    private final By deleteButtons = By.cssSelector(".btn-delete, [data-testid='delete-data-btn']");

    // Locators - Filter User Modal
    private final By filterModal = By.id("filter-modal");
    private final By filterUnitHrisInput = By.id("filter-unit-hris");
    private final By filterDepartmentInput = By.id("filter-departement");
    private final By filterJobLevelInput = By.id("filter-job-level");
    private final By filterEmployeeTypeInput = By.id("filter-employee-type");
    private final By filterPositionInput = By.id("filter-position");
    private final By filterLocationInput = By.id("filter-location");
    private final By filterStatusSelect = By.id("filter-status");
    private final By filterSubmitBtn = By.id("btn-filter-submit");
    private final By filterCancelBtn = By.id("btn-filter-cancel");
    private final By filterCloseBtn = By.id("btn-filter-close");

    // Locators - Delete Modal
    private final By deleteModal = By.id("delete-modal");
    private final By deleteModalTitle = By.id("alert-dialog-slide-title");
    private final By deleteModalDescription = By.id("alert-dialog-slide-description");
    private final By deleteConfirmBtn = By.id("btn-confirm-delete");
    private final By deleteCancelBtn = By.id("btn-cancel-delete");
    private final By deleteCloseBtn = By.id("btn-close-delete-modal");

    // Locators - Batch Edit Modal
    private final By batchModal = By.id("batch-edit-modal");
    private final By batchDivisionInput = By.id("batch-division");
    private final By batchPositionInput = By.id("batch-position");
    private final By batchJobInput = By.id("batch-job");
    private final By batchSupervisorInput = By.id("batch-supervisor");
    private final By batchContractTypeInput = By.id("batch-contract-type");
    private final By batchLocationInput = By.id("batch-location");
    private final By batchShiftTypeSelect = By.id("batch-shift-type");
    private final By batchScheduleInput = By.id("batch-work-schedule");
    private final By batchSelfieSelect = By.id("batch-selfie");
    private final By batchLeaveAmountInput = By.id("batch-leave-amount");
    private final By batchSubmitBtn = By.id("btn-batch-submit");
    private final By batchCancelBtn = By.id("btn-batch-cancel");
    private final By batchCloseBtn = By.id("btn-batch-close");

    // Locators - Pagination
    private final By rowsPerPageSelect = By.id("pagination-rows-per-page");
    private final By displayedRowsText = By.id("displayed-rows");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    // Locators - Alert / Snackbar
    private final By alertSnackbar = By.id("alert-snackbar");
    private final By alertMessage = By.id("alert-message");
    private final By alertCloseBtn = By.id("btn-alert-close");

    public SelfRegistrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the self-registration page.
     */
    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String selfRegUrl = baseUrl + "/management/self-registration";
        logger.info("Navigating to self-registration page: {}", selfRegUrl);
        driver.get(selfRegUrl);
        waitForVisibility(pageTitle);
    }

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(searchInput);
    }

    public boolean isSearchTypeSelectDisplayed() {
        return isElementDisplayed(searchTypeSelect);
    }

    public boolean isSearchBtnDisplayed() {
        return isElementDisplayed(searchBtn);
    }

    public boolean isFilterBtnDisplayed() {
        return isElementDisplayed(filterBtn);
    }

    public boolean isResetBtnDisplayed() {
        return isElementDisplayed(resetBtn);
    }

    public boolean isTableDisplayed() {
        return isElementDisplayed(table);
    }

    public boolean isPaginationDisplayed() {
        return isElementDisplayed(rowsPerPageSelect);
    }

    public String getSearchInputValue() {
        return driver.findElement(searchInput).getAttribute("value");
    }

    public String getSearchInputPlaceholder() {
        return driver.findElement(searchInput).getAttribute("placeholder");
    }

    public void pressEnterOnSearch() {
        driver.findElement(searchInput).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public boolean isRowPresentWithText(String text) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.isDisplayed() && row.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    // --- Search & Filter Methods ---

    public void selectSearchType(String typeValueOrText) {
        logger.info("Selecting search type: {}", typeValueOrText);
        WebElement element = waitForVisibility(searchTypeSelect);
        Select select = new Select(element);
        try {
            select.selectByValue(typeValueOrText);
        } catch (Exception e) {
            select.selectByVisibleText(typeValueOrText);
        }
    }

    public void enterSearchQuery(String query) {
        logger.info("Entering search query: {}", query);
        type(searchInput, query);
    }

    public void clickSearch() {
        logger.info("Clicking Search button");
        click(searchBtn);
    }

    public void clickReset() {
        logger.info("Clicking Reset button");
        click(resetBtn);
    }

    public void searchUser(String query, String type) {
        selectSearchType(type);
        enterSearchQuery(query);
        clickSearch();
    }

    public void openFilterModal() {
        logger.info("Opening filter modal");
        click(filterBtn);
        waitForVisibility(filterModal);
    }

    public void closeFilterModal() {
        logger.info("Closing filter modal");
        click(filterCloseBtn);
    }

    public void cancelFilter() {
        logger.info("Canceling filter modal");
        click(filterCancelBtn);
    }

    public void applyUnitHrisFilter(String unitName) {
        openFilterModal();
        type(filterUnitHrisInput, unitName);
        click(filterSubmitBtn);
    }

    // --- Table & Row Interaction Methods ---

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

    public void toggleSelectAll() {
        logger.info("Toggling Select All checkbox");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectAllCheckbox));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click(); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element
        );
    }

    public boolean isSelectAllChecked() {
        return driver.findElement(selectAllCheckbox).isSelected();
    }

    public void selectRow(int index) {
        List<WebElement> checkboxes = driver.findElements(rowCheckboxes);
        if (index >= 0 && index < checkboxes.size()) {
            logger.info("Selecting table row index {}", index);
            WebElement cb = checkboxes.get(index);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click(); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                cb
            );
        }
    }

    public boolean isBulkToolbarVisible() {
        return isElementDisplayed(bulkToolbar);
    }

    public int getSelectedCount() {
        try {
            String text = getText(selectedCountText).trim();
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getUserFullName(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-fullname")).getText().trim();
        }
        return "";
    }

    public String getUserNik(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-nik")).getText().trim();
        }
        return "";
    }

    public String getUserEmail(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-email")).getText().trim();
        }
        return "";
    }

    public String getUserPosition(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-position")).getText().trim();
        }
        return "";
    }

    public String getUserDivision(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-division")).getText().trim();
        }
        return "";
    }

    public String getUserUnit(int index) {
        List<WebElement> rows = driver.findElements(tableRows);
        if (index >= 0 && index < rows.size()) {
            return rows.get(index).findElement(By.className("cell-unit")).getText().trim();
        }
        return "";
    }

    public void clickViewOnRow(int index) {
        List<WebElement> buttons = driver.findElements(viewButtons);
        if (index >= 0 && index < buttons.size()) {
            logger.info("Clicking View on row index {}", index);
            buttons.get(index).click();
        }
    }

    public void clickDeleteOnRow(int index) {
        List<WebElement> buttons = driver.findElements(deleteButtons);
        if (index >= 0 && index < buttons.size()) {
            logger.info("Clicking Delete on row index {}", index);
            buttons.get(index).click();
            waitForVisibility(deleteModal);
        }
    }

    // --- Delete Modal Methods ---

    public boolean isDeleteModalDisplayed() {
        return isElementDisplayed(deleteModal);
    }

    public String getDeleteModalDescription() {
        return getText(deleteModalDescription);
    }

    public void confirmDelete() {
        logger.info("Confirming deletion in delete modal");
        click(deleteConfirmBtn);
    }

    public void cancelDelete() {
        logger.info("Canceling deletion in delete modal");
        click(deleteCancelBtn);
    }

    // --- Batch Edit Modal Methods ---

    public void openBatchEditModal() {
        logger.info("Opening batch edit modal");
        click(bulkUpdateBtn);
        waitForVisibility(batchModal);
    }

    public boolean isBatchModalDisplayed() {
        return isElementDisplayed(batchModal);
    }

    public void submitBatchEdit() {
        logger.info("Submitting batch edit modal");
        click(batchSubmitBtn);
    }

    public void closeBatchModal() {
        logger.info("Closing batch edit modal");
        click(batchCloseBtn);
    }

    public void fillBatchEditForm(String division, String position, String job, String shiftType, String selfie, String leaveAmount) {
        openBatchEditModal();
        if (division != null) type(batchDivisionInput, division);
        if (position != null) type(batchPositionInput, position);
        if (job != null) type(batchJobInput, job);
        if (shiftType != null) {
            Select select = new Select(driver.findElement(batchShiftTypeSelect));
            select.selectByValue(shiftType);
        }
        if (selfie != null) {
            Select select = new Select(driver.findElement(batchSelfieSelect));
            select.selectByValue(selfie);
        }
        if (leaveAmount != null) {
            type(batchLeaveAmountInput, leaveAmount);
        }
        submitBatchEdit();
    }

    // --- Pagination Methods ---

    public String getDisplayedRowsText() {
        return getText(displayedRowsText);
    }

    public void selectRowsPerPage(String count) {
        logger.info("Selecting rows per page: {}", count);
        Select select = new Select(waitForVisibility(rowsPerPageSelect));
        select.selectByValue(count);
    }

    public boolean isNextPageEnabled() {
        return driver.findElement(nextPageBtn).isEnabled();
    }

    public boolean isPrevPageEnabled() {
        return driver.findElement(prevPageBtn).isEnabled();
    }

    // --- Alerts / Toast Notification Methods ---

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertSnackbar);
    }

    public String getAlertText() {
        try {
            return getText(alertMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public void closeAlert() {
        click(alertCloseBtn);
    }
}
