package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Model untuk Halaman Leader Staff Lists
 * Target URL: http://localhost:8085/leader/staff/lists atau https://magang.dikahadir.com/leader/staff/lists
 */
public class LeaderStaffPage extends BasePage {

    // ==========================================
    // 1. By Locators
    // ==========================================

    // Header & Breadcrumbs
    private final By pageTitle = By.id("page-title");
    private final By breadcrumbs = By.cssSelector(".breadcrumbs");
    private final By breadcrumbHome = By.id("breadcrumb-home");
    private final By breadcrumbParent = By.id("breadcrumb-parent");
    private final By breadcrumbCurrent = By.id("breadcrumb-current");

    // Toolbar Pencarian & Filter
    private final By formSearch = By.id("form-search");
    private final By searchInput = By.id("search");
    private final By btnFilter = By.id("btn-filter");
    private final By btnReset = By.id("btn-reset");
    private final By btnSearch = By.id("btn-search");
    private final By btnTambah = By.id("btn-tambah");

    // Tabel Staff & Baris
    private final By staffTable = By.id("staff-table");
    private final By staffTableBody = By.id("staff-table-body");
    private final By tableRows = By.cssSelector("#staff-table-body tr");
    private final By emptyTableMessage = By.id("empty-table-message");

    // Header Kolom Tabel (9 Kolom)
    private final By thPhoto = By.id("th-photo");
    private final By thNik = By.id("th-nik");
    private final By thFullname = By.id("th-fullname");
    private final By thEmail = By.id("th-email");
    private final By thUnit = By.id("th-unit");
    private final By thPosition = By.id("th-position");
    private final By thPhone = By.id("th-phone");
    private final By thStatus = By.id("th-status");
    private final By thAction = By.id("th-action");

    // Modal Filter Staff
    private final By modalFilter = By.id("modal-filter-staff");
    private final By filterTitle = By.id("alert-dialog-title-filter");
    private final By formFilter = By.id("form-filter-staff");
    private final By filterModalUnit = By.id("filter-modal-unit");
    private final By filterModalStatus = By.id("filter-modal-status");
    private final By btnBatalFilter = By.id("btn-batal-filter");
    private final By btnTerapkanFilter = By.id("btn-terapkan-filter");
    private final By btnCloseFilter = By.cssSelector("#modal-filter-staff .action-btn");

    // Modal Detail Staff
    private final By modalDetail = By.id("modal-detail-staff");
    private final By detailTitle = By.id("alert-dialog-title-detail");
    private final By detailNik = By.id("detail-nik");
    private final By detailFullname = By.id("detail-fullname");
    private final By detailEmail = By.id("detail-email");
    private final By detailUnit = By.id("detail-unit");
    private final By detailPosition = By.id("detail-position");
    private final By detailPhone = By.id("detail-phone");
    private final By detailStatus = By.id("detail-status");
    private final By detailJoinedDate = By.id("detail-joined-date");
    private final By btnCloseDetail = By.id("btn-close-detail");
    private final By btnCloseDetailX = By.cssSelector("#modal-detail-staff .action-btn");

    // Modal Tambah Staff
    private final By modalTambah = By.id("modal-tambah-staff");
    private final By tambahTitle = By.id("alert-dialog-title-add");
    private final By formTambah = By.id("form-tambah-staff");
    private final By addNik = By.id("add-nik");
    private final By addFullname = By.id("add-fullname");
    private final By addEmail = By.id("add-email");
    private final By addUnit = By.id("add-unit");
    private final By addPosition = By.id("add-position");
    private final By addPhone = By.id("add-phone");
    private final By addStatus = By.id("add-status");
    private final By btnBatalTambah = By.id("btn-batal-tambah");
    private final By btnSimpanTambah = By.id("btn-simpan-tambah");
    private final By btnCloseTambah = By.cssSelector("#modal-tambah-staff .action-btn");

    // Modal Edit Staff
    private final By modalEdit = By.id("modal-edit-staff");
    private final By editTitle = By.id("alert-dialog-title-edit");
    private final By formEdit = By.id("form-edit-staff");
    private final By editId = By.id("edit-id");
    private final By editNik = By.id("edit-nik");
    private final By editFullname = By.id("edit-fullname");
    private final By editEmail = By.id("edit-email");
    private final By editUnit = By.id("edit-unit");
    private final By editPosition = By.id("edit-position");
    private final By editPhone = By.id("edit-phone");
    private final By editStatus = By.id("edit-status");
    private final By btnBatalEdit = By.id("btn-batal-edit");
    private final By btnSimpanEdit = By.id("btn-simpan-edit");
    private final By btnCloseEdit = By.cssSelector("#modal-edit-staff .action-btn");

    // Modal Hapus Staff
    private final By modalDelete = By.id("modal-delete-staff");
    private final By deleteTitle = By.id("alert-dialog-title-delete");
    private final By formDelete = By.id("form-delete-staff");
    private final By deleteId = By.id("delete-id");
    private final By deleteConfirmText = By.id("delete-confirm-text");
    private final By btnBatalDelete = By.id("btn-batal-delete");
    private final By btnConfirmDelete = By.id("btn-confirm-delete");
    private final By btnCloseDelete = By.cssSelector("#modal-delete-staff .action-btn");

    // Paginasi & Pengaturan Tampilan
    private final By rowsPerPageSelect = By.id("rows-per-page");
    private final By displayedRows = By.id("displayed-rows");
    private final By btnFirstPage = By.id("btn-first-page");
    private final By btnPrevPage = By.id("btn-prev-page");
    private final By btnNextPage = By.id("btn-next-page");
    private final By btnLastPage = By.id("btn-last-page");
    private final By checkboxDense = By.id("checkbox-dense");

    // Toast Snackbar
    private final By snackbar = By.id("alert-snackbar");
    private final By snackbarMessage = By.id("alert-message");
    private final By snackbarClose = By.cssSelector(".snackbar-close");

    public LeaderStaffPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // Fast & Safe Non-blocking Helper Methods (OPTIMASI DELAY)
    // ==========================================
    public boolean isFastDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            return false;
        }
        try {
            WebElement el = elements.get(0);
            String cls = el.getAttribute("class");
            if (cls != null) {
                if (cls.contains("modal-backdrop")) {
                    return cls.contains("open");
                }
                if (cls.contains("snackbar")) {
                    return cls.contains("open");
                }
            }
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clearInput(By locator) {
        WebElement element = waitForVisibility(locator);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        } catch (Exception ignored) {}
        element.clear();
    }

    private void selectDropdownOption(By locator, String visibleText) {
        WebElement selectEl = waitForVisibility(locator);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", selectEl);
        } catch (Exception ignored) {}
        try {
            Select select = new Select(selectEl);
            select.selectByVisibleText(visibleText);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                "for (var i = 0; i < arguments[0].options.length; i++) {" +
                "  if (arguments[0].options[i].text === arguments[1]) {" +
                "    arguments[0].selectedIndex = i;" +
                "    arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "    break;" +
                "  }" +
                "}", selectEl, visibleText);
        }
    }

    // ==========================================
    // 2. Navigation
    // ==========================================
    public void navigateToLeaderStaffPage() {
        String baseUrl = ConfigReader.getProperty("ui.url", "http://localhost:8085");
        String targetUrl = baseUrl + "/leader/staff/lists";
        logger.info("Navigating to Leader Staff Lists page: {}", targetUrl);
        driver.get(targetUrl);
        waitForVisibility(pageTitle);
    }

    // ==========================================
    // 3. Header & Breadcrumbs
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
    // 4. Search & Filter Toolbar
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

    public void enterSearchKeyword(String keyword) {
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

    public boolean isFilterButtonDisplayed() {
        return isFastDisplayed(btnFilter);
    }

    public void clickFilterButton() {
        logger.info("Clicking Filter button");
        click(btnFilter);
    }

    public boolean isTambahButtonDisplayed() {
        return isFastDisplayed(btnTambah);
    }

    public void clickTambahButton() {
        logger.info("Clicking Tambah Staff button");
        click(btnTambah);
    }

    // ==========================================
    // 5. Table & Column Verification
    // ==========================================
    public boolean isTableDisplayed() {
        return isFastDisplayed(staffTable);
    }

    public boolean isHeaderColumnDisplayed(String columnName) {
        switch (columnName.toUpperCase()) {
            case "FOTO": return isFastDisplayed(thPhoto);
            case "NIK": return isFastDisplayed(thNik);
            case "NAMA LENGKAP": return isFastDisplayed(thFullname);
            case "EMAIL": return isFastDisplayed(thEmail);
            case "UNIT / DEPARTEMEN":
            case "UNIT": return isFastDisplayed(thUnit);
            case "JABATAN": return isFastDisplayed(thPosition);
            case "NO. HP":
            case "PHONE": return isFastDisplayed(thPhone);
            case "STATUS": return isFastDisplayed(thStatus);
            case "AKSI": return isFastDisplayed(thAction);
            default: return false;
        }
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
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> names = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> nameEls = row.findElements(By.cssSelector(".cell-fullname"));
            if (!nameEls.isEmpty()) {
                names.add(nameEls.get(0).getText().trim());
            }
        }
        return names;
    }

    public List<String> getAllNiks() {
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> niks = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty()) {
                niks.add(nikEls.get(0).getText().trim());
            }
        }
        return niks;
    }

    public List<String> getAllUnits() {
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> units = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> unitEls = row.findElements(By.cssSelector(".cell-unit .badge-unit"));
            if (!unitEls.isEmpty()) {
                units.add(unitEls.get(0).getText().trim());
            }
        }
        return units;
    }

    public List<String> getAllStatuses() {
        List<WebElement> rows = driver.findElements(tableRows);
        List<String> statuses = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> statusEls = row.findElements(By.cssSelector(".cell-status .badge-status"));
            if (!statusEls.isEmpty()) {
                statuses.add(statusEls.get(0).getText().trim());
            }
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

    public boolean isRowWithNikDisplayed(String nik) {
        List<String> niks = getAllNiks();
        for (String item : niks) {
            if (item.equalsIgnoreCase(nik) || item.toLowerCase().contains(nik.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String getStatusByNik(String nik) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty() && nikEls.get(0).getText().trim().equalsIgnoreCase(nik)) {
                List<WebElement> statusEls = row.findElements(By.cssSelector(".cell-status .badge-status"));
                return statusEls.isEmpty() ? "" : statusEls.get(0).getText().trim();
            }
        }
        return "";
    }

    public String getUnitByNik(String nik) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty() && nikEls.get(0).getText().trim().equalsIgnoreCase(nik)) {
                List<WebElement> unitEls = row.findElements(By.cssSelector(".cell-unit .badge-unit"));
                return unitEls.isEmpty() ? "" : unitEls.get(0).getText().trim();
            }
        }
        return "";
    }

    // ==========================================
    // 6. Action Buttons per Row (Detail, Edit, Delete)
    // ==========================================
    public void clickDetailByNik(String nik) {
        logger.info("Clicking Detail button for staff with NIK: '{}'", nik);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty() && nikEls.get(0).getText().trim().equalsIgnoreCase(nik)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-detail-staff"));
                btn.click();
                return;
            }
        }
        throw new RuntimeException("Staff with NIK '" + nik + "' not found to click detail!");
    }

    public void clickDetailFirstRow() {
        logger.info("Clicking Detail button on first row");
        WebElement btn = waitForVisibility(By.cssSelector("#staff-table-body tr .btn-detail-staff"));
        btn.click();
    }

    public void clickEditByNik(String nik) {
        logger.info("Clicking Edit button for staff with NIK: '{}'", nik);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty() && nikEls.get(0).getText().trim().equalsIgnoreCase(nik)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-edit-staff"));
                btn.click();
                return;
            }
        }
        throw new RuntimeException("Staff with NIK '" + nik + "' not found to edit!");
    }

    public void clickEditFirstRow() {
        logger.info("Clicking Edit button on first row");
        WebElement btn = waitForVisibility(By.cssSelector("#staff-table-body tr .btn-edit-staff"));
        btn.click();
    }

    public void clickDeleteByNik(String nik) {
        logger.info("Clicking Delete button for staff with NIK: '{}'", nik);
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> nikEls = row.findElements(By.cssSelector(".cell-nik"));
            if (!nikEls.isEmpty() && nikEls.get(0).getText().trim().equalsIgnoreCase(nik)) {
                WebElement btn = row.findElement(By.cssSelector(".btn-delete-staff"));
                btn.click();
                return;
            }
        }
        throw new RuntimeException("Staff with NIK '" + nik + "' not found to delete!");
    }

    public void clickDeleteFirstRow() {
        logger.info("Clicking Delete button on first row");
        WebElement btn = waitForVisibility(By.cssSelector("#staff-table-body tr .btn-delete-staff"));
        btn.click();
    }

    // ==========================================
    // 7. Modal Filter Staff
    // ==========================================
    public boolean isModalFilterDisplayed() {
        return isFastDisplayed(modalFilter);
    }

    public void selectFilterUnit(String unit) {
        logger.info("Selecting filter unit: '{}'", unit);
        selectDropdownOption(filterModalUnit, unit);
    }

    public void selectFilterStatus(String status) {
        logger.info("Selecting filter status: '{}'", status);
        selectDropdownOption(filterModalStatus, status);
    }

    public void clickTerapkanFilter() {
        logger.info("Clicking Terapkan Filter button");
        click(btnTerapkanFilter);
    }

    public void clickBatalFilter() {
        logger.info("Clicking Batal Filter button");
        click(btnBatalFilter);
    }

    public void clickCloseFilterX() {
        logger.info("Clicking Close (X) on Filter modal");
        click(btnCloseFilter);
    }

    // ==========================================
    // 8. Modal Detail Staff
    // ==========================================
    public boolean isModalDetailDisplayed() {
        return isFastDisplayed(modalDetail);
    }

    public String getDetailNik() {
        return getText(detailNik);
    }

    public String getDetailFullname() {
        return getText(detailFullname);
    }

    public String getDetailEmail() {
        return getText(detailEmail);
    }

    public String getDetailUnit() {
        return getText(detailUnit);
    }

    public String getDetailPosition() {
        return getText(detailPosition);
    }

    public String getDetailPhone() {
        return getText(detailPhone);
    }

    public String getDetailStatus() {
        return getText(detailStatus);
    }

    public String getDetailJoinedDate() {
        return getText(detailJoinedDate);
    }

    public void clickCloseDetail() {
        logger.info("Clicking Tutup button on Detail modal");
        click(btnCloseDetail);
    }

    public void clickCloseDetailX() {
        logger.info("Clicking Close (X) on Detail modal");
        click(btnCloseDetailX);
    }

    // ==========================================
    // 9. Modal Tambah Staff
    // ==========================================
    public boolean isModalTambahDisplayed() {
        return isFastDisplayed(modalTambah);
    }

    public void enterAddNik(String nik) {
        clearInput(addNik);
        type(addNik, nik);
    }

    public void enterAddFullname(String fullname) {
        clearInput(addFullname);
        type(addFullname, fullname);
    }

    public void enterAddEmail(String email) {
        clearInput(addEmail);
        type(addEmail, email);
    }

    public void selectAddUnit(String unit) {
        logger.info("Selecting add unit: '{}'", unit);
        selectDropdownOption(addUnit, unit);
    }

    public void enterAddPosition(String position) {
        clearInput(addPosition);
        type(addPosition, position);
    }

    public void enterAddPhone(String phone) {
        clearInput(addPhone);
        type(addPhone, phone);
    }

    public void selectAddStatus(String status) {
        logger.info("Selecting add status: '{}'", status);
        selectDropdownOption(addStatus, status);
    }

    public void fillTambahStaffForm(String nik, String fullname, String email, String unit, String position, String phone, String status) {
        logger.info("Filling Tambah Staff form: NIK={}, Name={}, Email={}, Unit={}, Pos={}, Phone={}, Status={}",
                nik, fullname, email, unit, position, phone, status);
        if (nik != null) enterAddNik(nik);
        if (fullname != null) enterAddFullname(fullname);
        if (email != null) enterAddEmail(email);
        if (unit != null && !unit.isEmpty()) selectAddUnit(unit);
        if (position != null) enterAddPosition(position);
        if (phone != null) enterAddPhone(phone);
        if (status != null && !status.isEmpty()) selectAddStatus(status);
    }

    public void clickSimpanTambah() {
        logger.info("Clicking Simpan button on Tambah modal");
        click(btnSimpanTambah);
    }

    public void clickBatalTambah() {
        logger.info("Clicking Batal button on Tambah modal");
        click(btnBatalTambah);
    }

    public void clickCloseTambahX() {
        logger.info("Clicking Close (X) button on Tambah modal");
        click(btnCloseTambah);
    }

    // ==========================================
    // 10. Modal Edit Staff
    // ==========================================
    public boolean isModalEditDisplayed() {
        return isFastDisplayed(modalEdit);
    }

    public String getEditNikValue() {
        return getAttribute(editNik, "value");
    }

    public String getEditFullnameValue() {
        return getAttribute(editFullname, "value");
    }

    public String getEditEmailValue() {
        return getAttribute(editEmail, "value");
    }

    public String getEditPositionValue() {
        return getAttribute(editPosition, "value");
    }

    public String getEditPhoneValue() {
        return getAttribute(editPhone, "value");
    }

    public String getEditUnitValue() {
        WebElement selectEl = waitForVisibility(editUnit);
        Select select = new Select(selectEl);
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getEditStatusValue() {
        WebElement selectEl = waitForVisibility(editStatus);
        Select select = new Select(selectEl);
        return select.getFirstSelectedOption().getText().trim();
    }

    public void setEditFullname(String fullname) {
        clearInput(editFullname);
        type(editFullname, fullname);
    }

    public void setEditPosition(String position) {
        clearInput(editPosition);
        type(editPosition, position);
    }

    public void setEditPhone(String phone) {
        clearInput(editPhone);
        type(editPhone, phone);
    }

    public void selectEditUnit(String unit) {
        selectDropdownOption(editUnit, unit);
    }

    public void selectEditStatus(String status) {
        selectDropdownOption(editStatus, status);
    }

    public void clickSimpanEdit() {
        logger.info("Clicking Simpan Perubahan button on Edit modal");
        click(btnSimpanEdit);
    }

    public void clickBatalEdit() {
        logger.info("Clicking Batal button on Edit modal");
        click(btnBatalEdit);
    }

    public void clickCloseEditX() {
        logger.info("Clicking Close (X) button on Edit modal");
        click(btnCloseEdit);
    }

    // ==========================================
    // 11. Modal Hapus Staff
    // ==========================================
    public boolean isModalDeleteDisplayed() {
        return isFastDisplayed(modalDelete);
    }

    public String getDeleteConfirmText() {
        return getText(deleteConfirmText);
    }

    public void clickConfirmDelete() {
        logger.info("Clicking Confirm Delete button on Hapus modal");
        click(btnConfirmDelete);
    }

    public void clickBatalDelete() {
        logger.info("Clicking Batal button on Hapus modal");
        click(btnBatalDelete);
    }

    public void clickCloseDeleteX() {
        logger.info("Clicking Close (X) button on Hapus modal");
        click(btnCloseDelete);
    }

    // ==========================================
    // 12. Paginasi & Dense Padding
    // ==========================================
    public String getPaginationInfoText() {
        return getText(displayedRows);
    }

    public void selectRowsPerPage(String limit) {
        logger.info("Selecting rows per page: '{}'", limit);
        WebElement selectEl = waitForVisibility(rowsPerPageSelect);
        try {
            Select select = new Select(selectEl);
            select.selectByValue(limit);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; " +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                selectEl, limit);
        }
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

    public boolean isPrevButtonEnabled() {
        WebElement btn = driver.findElement(btnPrevPage);
        return btn.isEnabled();
    }

    public boolean isNextButtonEnabled() {
        WebElement btn = driver.findElement(btnNextPage);
        return btn.isEnabled();
    }

    public boolean isFirstButtonEnabled() {
        WebElement btn = driver.findElement(btnFirstPage);
        return btn.isEnabled();
    }

    public boolean isLastButtonEnabled() {
        WebElement btn = driver.findElement(btnLastPage);
        return btn.isEnabled();
    }

    public void setDensePadding(boolean enable) {
        logger.info("Setting dense padding: {}", enable);
        WebElement checkbox = waitForVisibility(checkboxDense);
        if (checkbox.isSelected() != enable) {
            checkbox.click();
        }
    }

    public boolean isDensePaddingChecked() {
        WebElement checkbox = waitForVisibility(checkboxDense);
        return checkbox.isSelected();
    }

    public boolean isTableDense() {
        WebElement table = driver.findElement(staffTable);
        String classes = table.getAttribute("class");
        return classes != null && classes.contains("dense");
    }

    // ==========================================
    // 13. Toast Notification / Snackbar
    // ==========================================
    public boolean isSnackbarDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return shortWait.until(d -> {
                List<WebElement> els = d.findElements(snackbar);
                if (els.isEmpty()) return false;
                String cls = els.get(0).getAttribute("class");
                return cls != null && cls.contains("open");
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
    // 14. Keyboard Actions
    // ==========================================
    public void pressEscapeKey() {
        logger.info("Simulating ESCAPE key to dismiss modals");
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
    }
}
