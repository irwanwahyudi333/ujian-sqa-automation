package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object Model for Hadir Laporan Sakit page:
 * (https://magang.dikahadir.com/laporan/sakit).
 */
public class LaporanSakitPage extends BasePage {

    // Locators - Header & Actions
    private final By pageTitle = By.xpath("//*[@id='page-title'] | //h6 | //h5");
    private final By exportBtn = By.xpath("//*[@id='btn-export'] | //button[contains(., 'Export')]");

    // Locators - Filter & Search Controls
    private final By startDateInput = By.xpath("//*[@id='start-date'] | //input[@placeholder='Start Date' or @type='date']");
    private final By endDateInput = By.xpath("//*[@id='end-date'] | //input[@placeholder='End Date' or @type='date']");
    private final By searchInput = By.xpath("//*[@id='search'] | //input[contains(@placeholder, 'Search') or contains(@placeholder, 'Cari')]");
    private final By searchBtn = By.xpath("//*[@id='btn-search'] | //button[contains(., 'Search') or @type='submit']");
    private final By resetBtn = By.xpath("//*[@id='btn-reset'] | //button[contains(., 'Reset')]");
    private final By filterBtn = By.xpath("//*[@id='btn-filter'] | //button[contains(., 'Filter')]");

    // Locators - Table & Rows
    private final By laporanTable = By.xpath("//*[@id='table-laporan'] | //table");
    private final By tableBody = By.xpath("//*[@id='table-body'] | //table//tbody");
    private final By tableRows = By.xpath("//*[@id='table-body']//tr | //table//tbody//tr");
    private final By emptyState = By.xpath("//*[contains(@class, 'empty-state')] | //td[contains(., 'Tidak ada data')]");

    // Locators - Sakit Images & Links
    private final By sakitImages = By.xpath("//table//tbody//tr//img");
    private final By sakitDownloadLinks = By.xpath("//table//tbody//tr//a[contains(., 'Download')]");
    private final By sakitViewButtons = By.xpath("//table//tbody//tr//button[contains(., 'View')] | //table//tbody//tr//a[contains(., 'View')]");
    private final By modalDialog = By.xpath("//*[@role='dialog']");
    private final By modalDialogImage = By.xpath("//*[@role='dialog']//img");

    // Locators - Modal Filter Lanjutan
    private final By filterModal = By.xpath("//*[@id='filter-modal'] | //*[@role='dialog']");
    private final By filterDepartemenSelect = By.id("filter-departemen");
    private final By filterStatusSelect = By.id("filter-status-sakit");
    private final By cancelFilterBtn = By.id("btn-cancel-filter");
    private final By applyFilterBtn = By.id("btn-apply-filter");
    private final By closeFilterModalBtn = By.id("btn-close-filter-modal");

    // Locators - Modal Detail
    private final By detailModal = By.id("detail-modal");
    private final By detailModalTitle = By.id("detail-modal-title");
    private final By closeDetailBtn = By.id("btn-close-detail");
    private final By closeDetailModalXBtn = By.id("btn-close-detail-modal");

    // Locators - Snackbar Toast
    private final By snackbar = By.xpath("//*[@id='snackbar'] | //*[contains(@class, 'MuiAlert-root') or contains(@class, 'MuiSnackbar-root')]");
    private final By snackbarMessage = By.xpath("//*[@id='snackbar-message'] | //*[contains(@class, 'MuiAlert-message')]");

    // Locators - Pagination Controls
    private final By paginationInfo = By.xpath("//*[@id='pagination-info'] | //*[contains(@class, 'MuiTablePagination-displayedRows')]");
    private final By rowsPerPageSelect = By.xpath("//*[@id='rows-per-page'] | //div[contains(@class, 'MuiTablePagination-select')]");
    private final By firstPageBtn = By.id("btn-first-page");
    private final By prevPageBtn = By.id("btn-prev-page");
    private final By nextPageBtn = By.id("btn-next-page");
    private final By lastPageBtn = By.id("btn-last-page");

    public LaporanSakitPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/laporan/sakit";
        logger.info("Membuka halaman Laporan Sakit: {}", url);
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
                && isElementDisplayed(searchInput) && isElementDisplayed(searchBtn)
                && isElementDisplayed(resetBtn) && isElementDisplayed(filterBtn);
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
        waitForAjaxOrTimeout(300);
    }

    public void clickReset() {
        logger.info("Mengklik tombol Reset");
        click(resetBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickFilterBtn() {
        logger.info("Membuka dialog modal filter sakit");
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
        logger.info("Memilih filter status verifikasi: {}", status);
        WebElement selectElement = waitForVisibility(filterStatusSelect);
        Select select = new Select(selectElement);
        select.selectByVisibleText(status);
    }

    public void clickApplyFilter() {
        logger.info("Menerapkan filter modal");
        click(applyFilterBtn);
        waitForAjaxOrTimeout(400);
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
        logger.info("Mengklik tombol detail baris ke-{}", rowIndex);
        By rowDetailBtn = By.cssSelector("#table-body tr:nth-child(" + rowIndex + ") td:nth-child(11) button");
        click(rowDetailBtn);
        waitForVisibility(detailModal);
    }

    public boolean isDetailModalDisplayed() {
        return isElementDisplayed(detailModal);
    }

    public String getDetailModalTitle() {
        return getText(detailModalTitle);
    }

    public void clickCloseDetail() {
        logger.info("Menutup modal detail sakit");
        click(closeDetailBtn);
    }

    public void clickExport() {
        logger.info("Mengklik tombol Export Sakit");
        click(exportBtn);
        waitForAjaxOrTimeout(300);
    }

    public boolean isSnackbarVisible() {
        return isElementDisplayed(snackbar);
    }

    public String getSnackbarMessage() {
        waitForVisibility(snackbar);
        return getText(snackbarMessage);
    }

    public void selectRowsPerPage(String rows) {
        logger.info("Mengatur rows per page: {}", rows);
        WebElement selectElement = waitForVisibility(rowsPerPageSelect);
        Select select = new Select(selectElement);
        select.selectByValue(rows);
        waitForAjaxOrTimeout(300);
    }

    public String getPaginationInfo() {
        return getText(paginationInfo);
    }

    public void clickNextPage() {
        logger.info("Navigasi ke halaman berikutnya");
        click(nextPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickPrevPage() {
        logger.info("Navigasi ke halaman sebelumnya");
        click(prevPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickFirstPage() {
        logger.info("Navigasi ke halaman pertama");
        click(firstPageBtn);
        waitForAjaxOrTimeout(300);
    }

    public void clickLastPage() {
        logger.info("Navigasi ke halaman terakhir");
        click(lastPageBtn);
        waitForAjaxOrTimeout(300);
    }

    // ==========================================
    // Methods Khusus Verifikasi Bug Foto & Tautan Unduh Sakit (Live App)
    // ==========================================

    public int countBrokenImages() {
        List<WebElement> imgs = driver.findElements(sakitImages);
        int brokenCount = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement img : imgs) {
            try {
                String src = img.getAttribute("src");
                Long naturalWidth = (Long) js.executeScript("return arguments[0].naturalWidth;", img);
                if (src == null || src.contains("null") || naturalWidth == null || naturalWidth == 0) {
                    brokenCount++;
                }
            } catch (Exception e) {
                brokenCount++;
            }
        }
        return brokenCount;
    }

    public int countInvalidDownloadLinks() {
        List<WebElement> links = driver.findElements(sakitDownloadLinks);
        int invalidCount = 0;
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href == null || href.contains("/null") || href.endsWith("null")) {
                invalidCount++;
            }
        }
        return invalidCount;
    }

    public void clickFirstViewButton() {
        logger.info("Mengklik tombol View bukti sakit pada baris pertama");
        List<WebElement> viewBtns = driver.findElements(sakitViewButtons);
        if (!viewBtns.isEmpty()) {
            viewBtns.get(0).click();
            waitForAjaxOrTimeout(1000);
        }
    }

    public boolean isModalDialogDisplayed() {
        return isElementDisplayed(modalDialog);
    }

    public boolean isModalImageBroken() {
        try {
            WebElement img = driver.findElement(modalDialogImage);
            String src = img.getAttribute("src");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long naturalWidth = (Long) js.executeScript("return arguments[0].naturalWidth;", img);
            return src == null || src.contains("null") || naturalWidth == null || naturalWidth == 0;
        } catch (Exception e) {
            return true;
        }
    }

    private void waitForAjaxOrTimeout(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}

