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
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model untuk Halaman Leader Izin Off
 * Target URL: http://localhost:8085/leader/izin-off atau https://magang.dikahadir.com/leader/izin-off
 */
public class LeaderIzinOffPage extends BasePage {

    // Header & Navigation
    private final By pageTitle = By.id("page-title");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");
    private final By btnTambah = By.id("btn-tambah");

    // Filter & Search Toolbar
    private final By searchInput = By.id("search-input");
    private final By filterStatus = By.id("filter-status");
    private final By startDate = By.id("start-date");
    private final By endDate = By.id("end-date");
    private final By btnSearch = By.id("btn-search");
    private final By btnReset = By.id("btn-reset");

    // Table Elements
    private final By tableIzinOff = By.id("table-izin-off");
    private final By tableBody = By.id("table-body-izin-off");
    private final By tableRows = By.cssSelector("#table-body-izin-off tr");
    private final By emptyTableMessage = By.id("empty-table-message");
    private final By cellFullnames = By.cssSelector(".user-fullname");
    private final By cellStatuses = By.cssSelector(".cell-status .badge");
    private final By btnApproveList = By.cssSelector(".btn-approve");
    private final By btnRejectList = By.cssSelector(".btn-reject");
    private final By btnDetailList = By.cssSelector(".btn-detail");

    // Approval Modal ("Approval Modal Izin Off")
    private final By modalApproval = By.id("modal-approval-izin-off");
    private final By modalApprovalTitle = By.id("modal-approval-title");
    private final By approvalPromptText = By.id("approval-prompt-text");
    private final By rejectReasonContainer = By.id("reject-reason-container");
    private final By rejectReasonInput = By.id("reject_reason");
    private final By btnSubmitApproval = By.id("sumbit-approval-request");
    private final By btnBatalApproval = By.id("btn-batal-approval");
    private final By btnCloseApproval = By.id("btn-close-approval");
    private final By approvalId = By.id("approval-id");
    private final By approvalType = By.id("approval-type");

    // Detail Modal
    private final By modalDetail = By.id("modal-detail-izin-off");
    private final By modalDetailTitle = By.id("modal-detail-title");
    private final By detailFullname = By.id("detail-fullname");
    private final By detailUpliner = By.id("detail-upliner");
    private final By detailDateOff = By.id("detail-date-off");
    private final By detailStatus = By.id("detail-status");
    private final By detailReason = By.id("detail-reason");
    private final By detailRejectReasonBox = By.id("detail-reject-reason-box");
    private final By detailRejectReason = By.id("detail-reject-reason");
    private final By btnTutupDetail = By.id("btn-tutup-detail");

    // Tambah / Pengajuan Modal
    private final By modalTambah = By.id("modal-tambah-izin-off");
    private final By modalTambahTitle = By.id("modal-tambah-title");
    private final By formTambah = By.id("form-tambah");
    private final By addFullname = By.id("add-fullname");
    private final By addUpliner = By.id("add-upliner");
    private final By addDateOff = By.id("add-date-off");
    private final By addReason = By.id("add-reason");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnBatalTambah = By.id("btn-batal-tambah");

    // Pagination & View Mode
    private final By paginationInfo = By.id("pagination-info");
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By btnPrevPage = By.id("btn-prev-page");
    private final By btnNextPage = By.id("btn-next-page");
    private final By densePaddingSwitch = By.id("dense-padding-switch");

    // Toast Notification / Snackbar
    private final By snackbar = By.id("snackbar");
    private final By snackbarMessage = By.id("snackbar-message");
    private final By snackbarClose = By.id("snackbar-close");

    
    protected void clearInput(By locator) {
        WebElement element = waitForVisibility(locator);
        element.clear();
    }

    public LeaderIzinOffPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // Fast & Safe Non-blocking Helper Methods
    // ==========================================
    public boolean isFastDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            return false;
        }
        try {
            return elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==========================================
    // Navigation
    // ==========================================
    public void navigateToLeaderIzinOffPage() {
        String baseUrl = ConfigReader.getProperty("ui.url", "http://localhost:8085");
        String targetUrl = baseUrl + "/leader/izin-off";
        logger.info("Navigating to Leader Izin Off page: {}", targetUrl);
        driver.get(targetUrl);
        waitForVisibility(pageTitle);
    }

    // ==========================================
    // Header & Breadcrumbs
    // ==========================================
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
        return getText(breadcrumbs).replaceAll("\\s+", " ").trim();
    }

    // ==========================================
    // Search & Filter Toolbar
    // ==========================================
    public boolean isSearchInputDisplayed() {
        return isFastDisplayed(searchInput);
    }

    public String getSearchInputPlaceholder() {
        return getAttribute(searchInput, "placeholder");
    }

    public String getSearchInputValue() {
        return getAttribute(searchInput, "value");
    }

    public boolean isSearchInputEmpty() {
        String val = getSearchInputValue();
        return val == null || val.trim().isEmpty();
    }

    public void enterSearchName(String keyword) {
        logger.info("Entering search keyword: '{}'", keyword);
        clearInput(searchInput);
        type(searchInput, keyword);
    }

    public void clearSearchInput() {
        clearInput(searchInput);
    }

    public void pressEnterInSearchInput() {
        logger.info("Pressing Enter in search input");
        WebElement input = waitForVisibility(searchInput);
        input.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton() {
        logger.info("Clicking Search button");
        click(btnSearch);
    }

    public void clickResetButton() {
        logger.info("Clicking Reset button");
        click(btnReset);
    }

    public void selectStatusFilter(String status) {
        logger.info("Selecting status filter: '{}'", status);
        WebElement selectElement = waitForVisibility(filterStatus);
        Select select = new Select(selectElement);
        select.selectByValue(status);
    }

    public String getSelectedStatusFilter() {
        WebElement selectElement = waitForVisibility(filterStatus);
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    public void setDateRange(String start, String end) {
        logger.info("Setting date range: {} to {}", start, end);
        if (start != null && !start.isEmpty()) {
            WebElement startEl = waitForVisibility(startDate);
            startEl.clear();
            startEl.sendKeys(start);
        }
        if (end != null && !end.isEmpty()) {
            WebElement endEl = waitForVisibility(endDate);
            endEl.clear();
            endEl.sendKeys(end);
        }
    }

    // ==========================================
    // Table Rows & Data Verification
    // ==========================================
    public boolean isTableDisplayed() {
        return isFastDisplayed(tableIzinOff);
    }

    public int getTableRowsCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.size() == 1 && isFastDisplayed(emptyTableMessage)) {
            return 0;
        }
        return rows.size();
    }

    public boolean isEmptyTableMessageDisplayed() {
        return isFastDisplayed(emptyTableMessage);
    }

    public String getEmptyTableMessageText() {
        if (isFastDisplayed(emptyTableMessage)) {
            return getText(emptyTableMessage);
        }
        return "";
    }

    public List<String> getAllFullnames() {
        List<WebElement> elements = driver.findElements(cellFullnames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public List<String> getAllStatuses() {
        List<WebElement> elements = driver.findElements(cellStatuses);
        List<String> statuses = new ArrayList<>();
        for (WebElement el : elements) {
            statuses.add(el.getText().trim());
        }
        return statuses;
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

    public String getStatusByFullname(String fullname) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                List<WebElement> statusEls = row.findElements(By.cssSelector(".cell-status .badge"));
                return statusEls.isEmpty() ? "" : statusEls.get(0).getText().trim();
            }
        }
        return "";
    }

    public void clickApproveByFullname(String fullname) {
        logger.info("Clicking Approve button for user: '{}'", fullname);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-approve"));
                btn.click();
                return;
            }
        }
        if (isFastDisplayed(btnNextPage) && isNextButtonEnabled()) {
            clickNextPage();
            rows = driver.findElements(tableRows);
            for (WebElement row : rows) {
                List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
                if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                    WebElement btn = row.findElement(By.cssSelector(".btn-approve"));
                    btn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("User with name '" + fullname + "' not found to approve!");
    }

    public void clickRejectByFullname(String fullname) {
        logger.info("Clicking Reject button for user: '{}'", fullname);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-reject"));
                btn.click();
                return;
            }
        }
        if (isFastDisplayed(btnNextPage) && isNextButtonEnabled()) {
            clickNextPage();
            rows = driver.findElements(tableRows);
            for (WebElement row : rows) {
                List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
                if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                    WebElement btn = row.findElement(By.cssSelector(".btn-reject"));
                    btn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("User with name '" + fullname + "' not found to reject!");
    }

    public void clickDetailByFullname(String fullname) {
        logger.info("Clicking Detail button for user: '{}'", fullname);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
            if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-detail"));
                btn.click();
                return;
            }
        }
        if (isFastDisplayed(btnNextPage) && isNextButtonEnabled()) {
            clickNextPage();
            rows = driver.findElements(tableRows);
            for (WebElement row : rows) {
                List<WebElement> nameEls = row.findElements(By.cssSelector(".user-fullname"));
                if (!nameEls.isEmpty() && nameEls.get(0).getText().trim().equalsIgnoreCase(fullname)) {
                    WebElement btn = row.findElement(By.cssSelector(".btn-detail"));
                    btn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("User with name '" + fullname + "' not found for detail!");
    }

    public void clickFirstApproveButton() {
        logger.info("Clicking first available Approve button");
        List<WebElement> buttons = driver.findElements(btnApproveList);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        } else {
            throw new RuntimeException("No approve button found in table!");
        }
    }

    public void clickFirstRejectButton() {
        logger.info("Clicking first available Reject button");
        List<WebElement> buttons = driver.findElements(btnRejectList);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        } else {
            throw new RuntimeException("No reject button found in table!");
        }
    }

    public void clickFirstDetailButton() {
        logger.info("Clicking first available Detail button");
        List<WebElement> buttons = driver.findElements(btnDetailList);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        } else {
            throw new RuntimeException("No detail button found in table!");
        }
    }

    public String getFirstRowFullname() {
        List<WebElement> names = driver.findElements(cellFullnames);
        return names.isEmpty() ? "" : names.get(0).getText().trim();
    }

    // ==========================================
    // Approval Modal ("Approval Modal Izin Off")
    // ==========================================
    public boolean isModalApprovalDisplayed() {
        return isFastDisplayed(modalApproval);
    }

    public String getModalApprovalTitle() {
        return getText(modalApprovalTitle);
    }

    public String getApprovalPromptText() {
        return getText(approvalPromptText);
    }

    public boolean isRejectReasonContainerDisplayed() {
        return isFastDisplayed(rejectReasonContainer);
    }

    public void enterRejectReason(String reason) {
        logger.info("Entering reject reason: '{}'", reason);
        clearInput(rejectReasonInput);
        type(rejectReasonInput, reason);
    }

    public String getRejectReasonValue() {
        return getAttribute(rejectReasonInput, "value");
    }

    public boolean isRejectReasonRequired() {
        WebElement input = driver.findElement(rejectReasonInput);
        String req = input.getAttribute("required");
        return "true".equals(req) || req != null;
    }

    public String getApprovalSubmitButtonText() {
        return getText(btnSubmitApproval);
    }

    public void clickSubmitApproval() {
        logger.info("Clicking Submit Approval button: {}", btnSubmitApproval);
        click(btnSubmitApproval);
    }

    public void clickBatalApproval() {
        logger.info("Clicking Batal Approval button");
        click(btnBatalApproval);
    }

    public void clickCloseApprovalWithX() {
        logger.info("Clicking Close (X) button on Approval modal");
        click(btnCloseApproval);
    }

    public String getApprovalIdValue() {
        return getAttribute(approvalId, "value");
    }

    public String getApprovalTypeValue() {
        return getAttribute(approvalType, "value");
    }

    // ==========================================
    // Detail Modal
    // ==========================================
    public boolean isModalDetailDisplayed() {
        return isFastDisplayed(modalDetail);
    }

    public String getModalDetailTitle() {
        return getText(modalDetailTitle);
    }

    public String getDetailFullname() {
        return getText(detailFullname);
    }

    public String getDetailStatus() {
        return getText(detailStatus);
    }

    public String getDetailReason() {
        return getText(detailReason);
    }

    public String getDetailRejectReason() {
        return getText(detailRejectReason);
    }

    public boolean isDetailRejectReasonBoxDisplayed() {
        return isFastDisplayed(detailRejectReasonBox);
    }

    public void clickTutupDetail() {
        logger.info("Clicking Tutup button on Detail modal");
        click(btnTutupDetail);
    }

    // ==========================================
    // Tambah / Pengajuan Modal
    // ==========================================
    public boolean isModalTambahDisplayed() {
        return isFastDisplayed(modalTambah);
    }

    public void clickTambahButton() {
        logger.info("Clicking Tambah button: {}", btnTambah);
        click(btnTambah);
    }

    public void enterAddFullname(String name) {
        clearInput(addFullname);
        type(addFullname, name);
    }

    public void enterAddUpliner(String upliner) {
        clearInput(addUpliner);
        type(addUpliner, upliner);
    }

    public void enterAddDateOff(String date) {
        WebElement dateEl = waitForVisibility(addDateOff);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", dateEl, date);
        } catch (Exception e) {
            dateEl.clear();
            dateEl.sendKeys(date);
        }
    }

    public void enterAddReason(String reason) {
        clearInput(addReason);
        type(addReason, reason);
    }

    public void fillTambahForm(String name, String upliner, String date, String reason) {
        logger.info("Filling Tambah Izin Off form: {}, {}, {}, {}", name, upliner, date, reason);
        enterAddFullname(name);
        if (upliner != null && !upliner.isEmpty()) {
            enterAddUpliner(upliner);
        }
        enterAddDateOff(date);
        enterAddReason(reason);
    }

    public void clickSimpanTambah() {
        logger.info("Clicking Simpan button on Tambah modal");
        click(btnSimpanTambah);
    }

    public void clickBatalTambah() {
        logger.info("Clicking Batal button on Tambah modal");
        click(btnBatalTambah);
    }

    public boolean isAddFullnameEmpty() {
        String val = getAttribute(addFullname, "value");
        return val == null || val.trim().isEmpty();
    }

    // ==========================================
    // Pagination & View Mode
    // ==========================================
    public String getPaginationInfoText() {
        return getText(paginationInfo);
    }

    public void selectRowsPerPage(String limit) {
        logger.info("Selecting rows per page: '{}'", limit);
        WebElement selectElement = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(selectElement);
        select.selectByValue(limit);
    }

    public void clickNextPage() {
        logger.info("Clicking Next page button");
        String currentInfo = isFastDisplayed(paginationInfo) ? getText(paginationInfo) : "";
        click(btnNextPage);
        try {
            wait.until(d -> !currentInfo.equals(d.findElement(paginationInfo).getText()));
        } catch (Exception ignored) {
            try { Thread.sleep(300); } catch (InterruptedException ignored2) {}
        }
    }

    public void clickPrevPage() {
        logger.info("Clicking Prev page button");
        click(btnPrevPage);
    }

    public boolean isPrevButtonEnabled() {
        WebElement btn = driver.findElement(btnPrevPage);
        return btn.isEnabled();
    }

    public boolean isNextButtonEnabled() {
        WebElement btn = driver.findElement(btnNextPage);
        return btn.isEnabled();
    }

    public void setDensePadding(boolean enable) {
        logger.info("Setting dense padding: {}", enable);
        WebElement checkbox = waitForVisibility(densePaddingSwitch);
        if (checkbox.isSelected() != enable) {
            checkbox.click();
        }
    }

    public boolean isDensePaddingChecked() {
        WebElement checkbox = waitForVisibility(densePaddingSwitch);
        return checkbox.isSelected();
    }

    public boolean isTableDense() {
        WebElement table = driver.findElement(tableIzinOff);
        String classes = table.getAttribute("class");
        return classes != null && classes.contains("dense");
    }

    // ==========================================
    // Toast Notification / Snackbar
    // ==========================================
    public boolean isSnackbarDisplayed() {
        try {
            return wait.until(d -> {
                WebElement el = d.findElement(snackbar);
                String cls = el.getAttribute("class");
                return cls != null && cls.contains("show");
            });
        } catch (Exception e) {
            return isFastDisplayed(snackbar);
        }
    }

    public String getSnackbarText() {
        try {
            waitForVisibility(snackbarMessage);
            return getText(snackbarMessage);
        } catch (Exception e) {
            return isFastDisplayed(snackbarMessage) ? getText(snackbarMessage) : "";
        }
    }

    public void clickCloseSnackbar() {
        logger.info("Clicking close on snackbar");
        click(snackbarClose);
    }

    // ==========================================
    // Keyboard Actions
    // ==========================================
    public void pressEscapeKey() {
        logger.info("Simulating ESCAPE key to dismiss modals");
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
    }
}
