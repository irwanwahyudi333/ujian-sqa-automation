package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentCorrectionPage extends BasePage {

    private final By pageTitle = By.xpath("//*[contains(., 'Halaman Koreksi')]");
    private final By totalCounter = By.xpath("//*[contains(., 'List Koreksi')]");
    private final By btnAjukanKoreksi = By.xpath("//button[contains(., 'Ajukan Koreksi')]");
    private final By btnKembali = By.xpath("//button[contains(., 'Kembali')]");

    // Modal Drawer Form
    private final By modalDrawer = By.xpath("//*[contains(., 'Ajukan Koreksi Absen')]");
    private final By drawerContainer = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]");
    private final By btnReset = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Reset')]");
    private final By btnSubmitAjukan = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Ajukan')]");

    public AbsentCorrectionPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Correction: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(pageTitle);
            return isElementDisplayed(pageTitle);
        } catch (Exception e) {
            return isElementDisplayed(btnAjukanKoreksi);
        }
    }

    public boolean isBtnAjukanKoreksiDisplayed() {
        return isElementDisplayed(btnAjukanKoreksi);
    }

    public boolean isBtnKembaliDisplayed() {
        return isElementDisplayed(btnKembali);
    }

    public void openModalAjukanKoreksi() {
        logger.info("Opening modal drawer Ajukan Koreksi");
        click(btnAjukanKoreksi);
    }

    public boolean isModalDrawerDisplayed() {
        return isElementDisplayed(modalDrawer);
    }

    public boolean areModalFormFieldsDisplayed() {
        try {
            waitForVisibility(modalDrawer);
            boolean hasInputs = driver.findElements(By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input")).size() >= 2;
            boolean hasReset = isElementDisplayed(btnReset);
            boolean hasSubmit = isElementDisplayed(btnSubmitAjukan);
            return hasInputs && hasReset && hasSubmit;
        } catch (Exception e) {
            return false;
        }
    }
}
