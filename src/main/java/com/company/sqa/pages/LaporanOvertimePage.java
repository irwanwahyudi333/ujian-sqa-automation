package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model for Hadir Laporan Overtime page:
 * Supports both Local Mock (http://localhost:8085/laporan/overtime)
 * and Live Production/Staging (https://magang.dikahadir.com/laporan/overtime).
 */
public class LaporanOvertimePage extends BasePage {

    // Locators - Header & Actions
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h1 | //h2 | //h3[contains(., 'Overtime') or contains(., 'Lembur')] | //p[contains(text(), 'Laporan')]");
    private final By exportBtn = By.xpath("//button[@id='btn-export'] | //button[contains(., 'Export')]");

    // Locators - Filter & Search Controls
    private final By startDateInput = By.xpath("//input[@id='start-date'] | //input[contains(@placeholder, 'Start Date')]");
    private final By endDateInput = By.xpath("//input[@id='end-date'] | //input[contains(@placeholder, 'End Date')]");
    private final By searchInput = By.xpath("//input[@id='search'] | //input[contains(@placeholder, 'Cari') or contains(@placeholder, 'Search')]");
    private final By searchBtn = By.xpath("//button[@id='btn-search'] | //button[contains(., 'Search')]");
    private final By resetBtn = By.xpath("//button[@id='btn-reset'] | //button[contains(., 'Reset')]");
    private final By filterBtn = By.xpath("//button[@id='btn-filter'] | //button[contains(., 'Filter')]");

    // Locators - Table & Rows
    private final By laporanTable = By.xpath("//table[@id='table-laporan'] | //table");
    private final By tableBody = By.xpath("//tbody[@id='table-body'] | //table/tbody");
    private final By tableRows = By.cssSelector("#table-body tr, table tbody tr");
    private final By emptyState = By.cssSelector(".empty-state");

    // Locators - Modal Filter Lanjutan (Mock & Live)
    private final By filterModal = By.xpath("//*[@id='filter-modal'] | //div[@role='dialog']");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterStatusSelect = By.id("filter-status-overtime");
    private final By cancelFilterBtn = By.xpath("//*[@id='btn-cancel-filter'] | //button[contains(., 'Batal')]");
    private final By applyFilterBtn = By.xpath("//*[@id='btn-apply-filter'] | //button[contains(., 'Terapkan')]");
    private final By closeFilterModalBtn = By.xpath("//*[@id='btn-close-filter-modal'] | //button[@aria-label='close']");

    // Locators - Modal Detail / Modal Ubah Atasan (Live & Mock)
    private final By detailModal = By.xpath("//*[@id='detail-modal'] | //div[@role='dialog']");
    private final By detailModalTitle = By.xpath("//*[@id='detail-modal-title'] | //div[@role='dialog']//h2");
    private final By closeDetailBtn = By.xpath("//*[@id='btn-close-detail'] | //div[@role='dialog']//button[contains(., 'Cancel') or contains(., 'Tutup')]");
    private final By closeDetailModalXBtn = By.xpath("//*[@id='btn-close-detail-modal'] | //div[@role='dialog']//button[@aria-label='close']");

    // Locators - Dialog Ubah Atasan & Tombol Ubah Data (Live)
    private final By ubahAtasanDialog = By.xpath("//div[@role='dialog']");
    private final By ubahAtasanDialogTitle = By.xpath("//div[@role='dialog']//h2");
    private final By atasanSelectElements = By.cssSelector("div[role='dialog'] select, div[role='dialog'] [role='combobox'], div[role='dialog'] input[type='text']:not([readonly])");
    private final By ubahDataBtn = By.xpath("//div[@role='dialog']//button[contains(., 'Ubah Data')]");

    // Locators - Snackbar & Toast Alert
    private final By snackbar = By.xpath("//*[@id='snackbar'] | //div[@role='alert'] | //div[contains(@class, 'MuiAlert-message')]");
    private final By snackbarMessage = By.xpath("//*[@id='snackbar-message'] | //div[@role='alert'] | //div[contains(@class, 'MuiAlert-message')]");

    // Locators - Pagination Controls
    private final By paginationInfo = By.xpath("//*[@id='pagination-info'] | //*[contains(text(), 'of ')] | //*[contains(@class, 'MuiTablePagination-displayedRows')]");
    private final By rowsPerPageSelect = By.xpath("//*[@id='rows-per-page'] | //div[contains(@class, 'MuiTablePagination-select')]");
    private final By firstPageBtn = By.xpath("//*[@id='btn-first-page'] | //button[@aria-label='Go to first page']");
    private final By prevPageBtn = By.xpath("//*[@id='btn-prev-page'] | //button[@aria-label='Go to previous page']");
    private final By nextPageBtn = By.xpath("//*[@id='btn-next-page'] | //button[@aria-label='Go to next page']");
    private final By lastPageBtn = By.xpath("//*[@id='btn-last-page'] | //button[@aria-label='Go to last page']");

    public LaporanOvertimePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/overtime";
        logger.info("Membuka halaman Laporan Overtime: {}", url);
        if (url.contains("magang.dikahadir.com")) {
            SessionHelper.ensureAuthenticated(driver, url);
        }
        driver.get(url);
        waitForVisibility(laporanTable);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public boolean isLaporanTableDisplayed() {
        return isElementDisplayed(laporanTable);
    }

    public boolean isFilterBarDisplayed() {
        return isElementDisplayed(startDateInput) && isElementDisplayed(endDateInput)
                && isElementDisplayed(searchInput) && (isElementDisplayed(searchBtn) || isElementDisplayed(resetBtn));
    }

    public boolean isExportBtnDisplayed() {
        return isElementDisplayed(exportBtn);
    }

    public void enterStartDate(String date) {
        logger.info("Mengisi Start Date: {}", date);
        type(startDateInput, date);
    }

    public void enterEndDate(String date) {
        logger.info("Mengisi End Date: {}", date);
        type(endDateInput, date);
    }

    public void enterSearch(String keyword) {
        logger.info("Mengisi kata kunci pencarian: {}", keyword);
        type(searchInput, keyword);
    }

    public void clickSearch() {
        logger.info("Mengklik tombol Search");
        click(searchBtn);
        waitForAjaxOrTimeout(600);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset");
        click(resetBtn);
        waitForAjaxOrTimeout(600);
    }

    public void clickFilterBtn() {
        logger.info("Membuka dialog modal filter overtime");
        click(filterBtn);
        waitForVisibility(filterModal);
    }

    public boolean isFilterModalDisplayed() {
        return isElementDisplayed(filterModal);
    }

    public void selectDepartemen(String departemen) {
        logger.info("Memilih filter departemen: {}", departemen);
        WebElement selectElement = waitForVisibility(filterDepartemenSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(departemen);
    }

    public void selectStatus(String status) {
        logger.info("Memilih filter status overtime: {}", status);
        WebElement selectElement = waitForVisibility(filterStatusSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(status);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter modal");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(500);
    }

    public void clickCancelFilter() {
        logger.info("Membatalkan filter modal");
        click(cancelFilterBtn);
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.size() == 1 && isElementDisplayed(emptyState)) {
            return 0;
        }
        return rows.size();
    }

    public boolean isEmployeeNamePresent(String employeeName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(employeeName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void clickDetailBtn(int rowIndex) {
        logger.info("Mengklik tombol aksi/detail baris ke-{}", rowIndex);
        By rowActionBtn = By.xpath("(//table//tbody//tr)[" + rowIndex + "]//td[last()]//button");
        click(rowActionBtn);
        waitForVisibility(detailModal);
    }

    public boolean isDetailModalDisplayed() {
        return isElementDisplayed(detailModal);
    }

    public String getDetailModalTitle() {
        return getText(detailModalTitle);
    }

    public void clickCloseDetail() {
        logger.info("Menutup modal detail overtime");
        click(closeDetailBtn);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Overtime");
        click(exportBtn);
        waitForAjaxOrTimeout(500);
    }

    public boolean isSnackbarVisible() {
        return isElementDisplayed(snackbar);
    }

    public String getSnackbarMessage() {
        waitForVisibility(snackbar);
        return getText(snackbarMessage);
    }

    // ==========================================
    // Methods Khusus Verifikasi Bug Modal "Ubah Data" (Live App)
    // ==========================================

    public void clickRowActionButton(int rowIndex) {
        logger.info("Mengklik tombol aksi pada baris ke-{} tabel overtime", rowIndex);
        By actionBtn = By.xpath("(//table//tbody//tr)[" + rowIndex + "]//td[last()]//button");
        click(actionBtn);
        waitForVisibility(ubahAtasanDialog);
    }

    public boolean isUbahAtasanDialogDisplayed() {
        return isElementDisplayed(ubahAtasanDialog);
    }

    public String getUbahAtasanDialogTitle() {
        return getText(ubahAtasanDialogTitle);
    }

    public int getAtasanDropdownCount() {
        List<WebElement> dropdowns = driver.findElements(atasanSelectElements);
        return dropdowns.size();
    }

    public boolean isUbahDataButtonDisplayed() {
        return isElementDisplayed(ubahDataBtn);
    }

    public boolean isUbahDataButtonEnabled() {
        try {
            WebElement btn = driver.findElement(ubahDataBtn);
            return btn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickUbahDataButton() {
        logger.info("Mengklik tombol Ubah Data pada modal ubah atasan");
        click(ubahDataBtn);
        waitForAjaxOrTimeout(1000);
    }

    public void selectRowsPerPage(String rows) {
        logger.info("Mengatur rows per page: {}", rows);
        try {
            WebElement selectElement = waitForVisibility(rowsPerPageSelect);
            Select select = new Select(selectElement);
            select.selectByValue(rows);
        } catch (Exception e) {
            logger.info("Dropdown rows-per-page standar tidak ditemukan, menggunakan klik langsung");
            click(rowsPerPageSelect);
        }
        waitForAjaxOrTimeout(500);
    }

    public String getPaginationInfo() {
        return getText(paginationInfo);
    }

    public void clickNextPage() {
        logger.info("Navigasi ke halaman berikutnya");
        click(nextPageBtn);
        waitForAjaxOrTimeout(500);
    }

    public void clickPrevPage() {
        logger.info("Navigasi ke halaman sebelumnya");
        click(prevPageBtn);
        waitForAjaxOrTimeout(500);
    }

    public void clickFirstPage() {
        logger.info("Navigasi ke halaman pertama");
        click(firstPageBtn);
        waitForAjaxOrTimeout(500);
    }

    public void clickLastPage() {
        logger.info("Navigasi ke halaman terakhir");
        click(lastPageBtn);
        waitForAjaxOrTimeout(500);
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
