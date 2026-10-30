package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentPermitPage extends BasePage {

    private final By pageTitle = By.xpath("//*[contains(., 'Halaman Izin')]");
    private final By tabTerlambat = By.xpath("//button[@role='tab'][contains(., 'Terlambat')]");
    private final By tabPulangCepat = By.xpath("//button[@role='tab'][contains(., 'Pulang Cepat')]");
    private final By tabIzinOff = By.xpath("//button[@role='tab'][contains(., 'Izin Off')]");
    private final By btnAjukanIzin = By.xpath("//button[contains(., 'Ajukan Izin')]");
    private final By btnKembali = By.xpath("//button[contains(., 'Kembali')]");

    // Modal Drawer Form
    private final By modalDrawer = By.xpath("//div[contains(@class, 'MuiDrawer-root')]//*[contains(., 'Ajukan Izin')]");
    private final By btnReset = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Reset')]");
    private final By btnSubmitAjukan = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Ajukan')]");

    public AbsentPermitPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Permit: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(pageTitle);
            return isElementDisplayed(pageTitle);
        } catch (Exception e) {
            return isElementDisplayed(btnAjukanIzin);
        }
    }

    public boolean areTabsDisplayed() {
        return isElementDisplayed(tabTerlambat) &&
               isElementDisplayed(tabPulangCepat) &&
               isElementDisplayed(tabIzinOff);
    }

    public void clickTabPulangCepat() {
        logger.info("Switching to tab 'Pulang Cepat'");
        click(tabPulangCepat);
    }

    public void clickTabIzinOff() {
        logger.info("Switching to tab 'Izin Off'");
        click(tabIzinOff);
    }

    public void clickTabTerlambat() {
        logger.info("Switching to tab 'Terlambat'");
        click(tabTerlambat);
    }

    public boolean isBtnAjukanIzinDisplayed() {
        return isElementDisplayed(btnAjukanIzin);
    }

    public boolean isBtnKembaliDisplayed() {
        return isElementDisplayed(btnKembali);
    }

    public void openModalAjukanIzin() {
        logger.info("Opening modal drawer Ajukan Izin");
        click(btnAjukanIzin);
    }

    public boolean isModalDrawerDisplayed() {
        return isElementDisplayed(modalDrawer);
    }

    public boolean areModalFormFieldsDisplayed() {
        try {
            waitForVisibility(modalDrawer);
            boolean hasInputs = !driver.findElements(By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input")).isEmpty();
            boolean hasReset = isElementDisplayed(btnReset);
            boolean hasSubmit = isElementDisplayed(btnSubmitAjukan);
            return hasInputs && hasReset && hasSubmit;
        } catch (Exception e) {
            return false;
        }
    }
}
