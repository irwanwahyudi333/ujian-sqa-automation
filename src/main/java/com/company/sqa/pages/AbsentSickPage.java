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
}
