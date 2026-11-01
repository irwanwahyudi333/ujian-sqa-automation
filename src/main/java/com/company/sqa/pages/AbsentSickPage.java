package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentSickPage extends BasePage {

    private final By pageTitle = By.xpath("//*[contains(., 'Halaman Sakit')]");
    private final By totalCounter = By.xpath("//*[contains(., 'List Request Sakit')]");
    private final By btnAjukanSakit = By.xpath("//button[contains(., 'Ajukan Sakit')]");
    private final By btnKembali = By.xpath("//button[contains(., 'Kembali')]");
    private final By thumbnailSuratSakit = By.xpath("//img[contains(@alt, 'Bukti surat sakit')]");

    // Modal Drawer Form
    private final By modalDrawer = By.xpath("//div[contains(@class, 'MuiDrawer-root')]//*[contains(., 'Ajukan Request Sakit') or contains(., 'Ajukan Sakit')]");
    private final By inputTanggal = By.xpath("//*[contains(text(), '/') or contains(@class, 'css-6rfaag')]");
    private final By btnUpload = By.xpath("//button[contains(., 'Upload')]");
    private final By inputFileUpload = By.xpath("//input[@type='file']");
    private final By btnReset = By.xpath("//button[contains(., 'Reset')]");
    private final By btnSubmitAjukan = By.xpath("//button[contains(., 'Ajukan')]");

    // Defect & Binding Inspection Locators
    private final By dateFromEntries = By.xpath("//p[contains(text(), 'Dari tanggal')]");
    private final By dateToEntries = By.xpath("//p[contains(text(), 'Sampai Tanggal')]");
    private final By validationErrorAttachment = By.xpath("//*[contains(text(), 'surat sakit wajib') or contains(text(), 'Lampiran wajib') or contains(@class, 'Mui-error')]");
    private final By backdateWarningModal = By.xpath("//*[contains(text(), 'Backdate') or contains(text(), 'tanggal lampau') or contains(text(), 'Peringatan') or contains(., 'Konfirmasi Pengajuan')]");

    public AbsentSickPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Sick: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(pageTitle);
            return isElementDisplayed(pageTitle);
        } catch (Exception e) {
            return isElementDisplayed(btnAjukanSakit);
        }
    }

    public boolean isBtnAjukanSakitDisplayed() {
        return isElementDisplayed(btnAjukanSakit);
    }

    public boolean areThumbnailsDisplayed() {
        return isElementDisplayed(thumbnailSuratSakit);
    }

    public void openModalAjukanSakit() {
        logger.info("Opening modal drawer Ajukan Sakit");
        click(btnAjukanSakit);
    }

    public boolean isModalDrawerDisplayed() {
        return isElementDisplayed(modalDrawer);
    }

    public boolean areModalFormFieldsDisplayed() {
        try {
            waitForVisibility(modalDrawer);
            return isElementDisplayed(btnUpload) &&
                   isElementDisplayed(btnReset) &&
                   isElementDisplayed(btnSubmitAjukan);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSubmitAjukan() {
        logger.info("Clicking on 'Ajukan' button in Sick Modal");
        click(btnSubmitAjukan);
    }

    public boolean areSickDatesRenderingCurrentTimestampInsteadOfSickDates() {
        java.util.List<org.openqa.selenium.WebElement> list = driver.findElements(dateFromEntries);
        for (org.openqa.selenium.WebElement el : list) {
            String text = el.getText();
            if (text != null && text.matches(".*\\d{1,2}:\\d{2}.*")) {
                return true;
            }
        }
        return false;
    }

    public boolean isSuratSakitAttachmentRequired() {
        try {
            org.openqa.selenium.WebElement input = driver.findElement(inputFileUpload);
            String required = input.getAttribute("required");
            if (required != null && (required.equals("true") || required.equals("required"))) {
                return true;
            }
        } catch (Exception ignored) {}
        return isElementDisplayed(validationErrorAttachment);
    }

    public boolean isBackdateWarningDisplayed() {
        return isElementDisplayed(backdateWarningModal);
    }
}
