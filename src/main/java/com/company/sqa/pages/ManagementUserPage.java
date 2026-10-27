package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model for Hadir Management User page:
 * (https://magang.dikahadir.com/management/user).
 */
public class ManagementUserPage extends BasePage {

    // Locators - Search & Filter Bar
    private final By searchTypeSelect = By.id("search-type");
    private final By searchInput = By.id("search");
    private final By searchBtn = By.id("btn-search");
    private final By resetBtn = By.id("btn-reset");
    private final By filterBtn = By.id("btn-filter");

    // Locators - Action Bar
    private final By exportBtn = By.id("btn-export-users");

    // Locators - Table
    private final By userTable = By.id("user-table");
    private final By tableBody = By.id("user-table-body");
    private final By tableRows = By.cssSelector("#user-table-body tr:not(.alphabet-divider)");
    private final By alphabetDividers = By.cssSelector(".alphabet-divider");

    // Locators - Filter Modal
    private final By filterModal = By.id("filter-modal");
    private final By filterDepartmentSelect = By.id("filter-departement");
    private final By filterJobLevelSelect = By.id("filter-job-level");
    private final By filterEmployeeTypeSelect = By.id("filter-employee-type");
    private final By filterPositionSelect = By.id("filter-job-position");
    private final By filterLocationSelect = By.id("filter-location");
    private final By filterStatusSelect = By.id("filter-status-active");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");

    // Locators - Sisa Cuti Modal
    private final By leaveModal = By.id("leave-modal");
    private final By leaveModalTitle = By.id("leave-modal-title");
    private final By leaveTotalText = By.id("leave-total");
    private final By leaveAvailableText = By.id("leave-available");
    private final By leaveMassText = By.id("leave-mass");
    private final By leaveApprovedText = By.id("leave-approved");
    private final By leaveRemainingText = By.id("leave-remaining");
    private final By closeLeaveModalBtn = By.id("btn-close-leave-modal");

    // Locators - Delete Modal
    private final By deleteModal = By.id("delete-modal");
    private final By deleteModalTitle = By.id("delete-modal-title");
    private final By deleteModalMessage = By.id("delete-modal-message");
    private final By confirmDeleteBtn = By.id("btn-confirm-delete");
    private final By cancelDeleteBtn = By.id("btn-cancel-delete");

    // Locators - Snackbar
    private final By snackbar = By.id("snackbar");
    private final By snackbarText = By.id("snackbar-text");

    // Locators - Pagination
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By firstPageBtn = By.id("btn-page-first");
    private final By prevPageBtn = By.id("btn-page-prev");
    private final By nextPageBtn = By.id("btn-page-next");
    private final By lastPageBtn = By.id("btn-page-last");

    public ManagementUserPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the management user page.
     */
    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String userUrl = baseUrl + "/management/user";
        logger.info("Navigating to Management User page: {}", userUrl);
        driver.get(userUrl);
        waitForVisibility(userTable);
    }

    public boolean isTableDisplayed() {
        return isElementDisplayed(userTable);
    }

    public boolean isSearchTypeSelectDisplayed() {
        return isElementDisplayed(searchTypeSelect);
    }

    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(searchInput);
    }

    public boolean isSearchBtnDisplayed() {
        return isElementDisplayed(searchBtn);
    }

    public boolean isResetBtnDisplayed() {
        return isElementDisplayed(resetBtn);
    }

    public boolean isFilterBtnDisplayed() {
        return isElementDisplayed(filterBtn);
    }

    public boolean isExportBtnDisplayed() {
        return isElementDisplayed(exportBtn);
    }

    public void selectSearchType(String typeValue) {
        logger.info("Selecting search type: {}", typeValue);
        WebElement element = waitForVisibility(searchTypeSelect);
        Select select = new Select(element);
        select.selectByValue(typeValue);
    }

    public void enterSearchQuery(String query) {
        type(searchInput, query);
    }

    public void clickSearch() {
        click(searchBtn);
    }

    public void clickReset() {
        click(resetBtn);
    }

    public void clickFilterButton() {
        click(filterBtn);
    }

    public boolean isFilterModalDisplayed() {
        return isElementDisplayed(filterModal);
    }

    public void selectFilterDepartment(String depId) {
        Select select = new Select(waitForVisibility(filterDepartmentSelect));
        select.selectByValue(depId);
    }

    public void selectFilterJobLevel(String lvlId) {
        Select select = new Select(waitForVisibility(filterJobLevelSelect));
        select.selectByValue(lvlId);
    }

    public void selectFilterStatus(String statusVal) {
        WebElement element = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(filterStatusSelect));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Select select = new Select(element);
        select.selectByValue(statusVal);
    }

    public void clickApplyFilter() {
        click(applyFilterBtn);
    }

    public void clickCancelFilter() {
        click(cancelFilterBtn);
    }

    public int getVisibleUserRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public List<String> getAllVisibleUserNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> nameLinks = driver.findElements(By.cssSelector(".user-name-link"));
        for (WebElement link : nameLinks) {
            names.add(link.getText().trim());
        }
        return names;
    }

    public void toggleActiveSwitch(String userId) {
        By switchLocator = By.id("switch-active-" + userId);
        logger.info("Toggling active switch for user ID: {}", userId);
        WebElement switchElement = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(switchLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", switchElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", switchElement);
    }

    public boolean isActiveSwitchChecked(String userId) {
        By switchLocator = By.id("switch-active-" + userId);
        WebElement element = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(switchLocator));
        return element.isSelected();
    }

    public void toggleProjectSwitch(String userId) {
        By switchLocator = By.id("switch-project-" + userId);
        logger.info("Toggling user project switch for user ID: {}", userId);
        WebElement switchElement = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(switchLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", switchElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", switchElement);
    }

    public boolean isProjectSwitchChecked(String userId) {
        By switchLocator = By.id("switch-project-" + userId);
        WebElement element = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(switchLocator));
        return element.isSelected();
    }

    public void clickSyncApproval(String userId) {
        By syncBtn = By.id("btn-sync-" + userId);
        click(syncBtn);
    }

    public void clickLeaveInfo(String userId) {
        By leaveBtn = By.id("btn-leave-" + userId);
        click(leaveBtn);
    }

    public boolean isLeaveModalDisplayed() {
        return isElementDisplayed(leaveModal);
    }

    public String getLeaveModalTitleText() {
        return getText(leaveModalTitle);
    }

    public void closeLeaveModal() {
        click(closeLeaveModalBtn);
    }

    public void clickDeleteUser(String userId) {
        By deleteBtn = By.id("btn-delete-" + userId);
        click(deleteBtn);
    }

    public boolean isDeleteModalDisplayed() {
        return isElementDisplayed(deleteModal);
    }

    public String getDeleteModalMessageText() {
        return getText(deleteModalMessage);
    }

    public void confirmDelete() {
        click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        click(cancelDeleteBtn);
    }

    public void clickExportUsers() {
        click(exportBtn);
    }

    public boolean isSnackbarDisplayed() {
        return isElementDisplayed(snackbar);
    }

    public String getSnackbarText() {
        return getText(snackbarText);
    }

    public String getPaginationInfoText() {
        return getText(paginationInfo);
    }
}
