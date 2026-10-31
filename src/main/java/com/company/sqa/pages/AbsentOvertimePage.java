package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentOvertimePage extends BasePage {

    private final By pageTitle = By.xpath("//*[contains(., 'Halaman Lembur')]");
    private final By totalCounter = By.xpath("//*[contains(., 'List Overtime')]");
    private final By btnAjukanLembur = By.xpath("//button[contains(., 'Ajukan Lembur')]");
    private final By btnKembali = By.xpath("//button[contains(., 'Kembali')]");

    // Modal Drawer Form
    private final By modalDrawer = By.xpath("//div[contains(@class, 'MuiDrawer-root')]//*[contains(., 'Ajukan Lembur')]");
    private final By btnReset = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Reset')]");
    private final By btnSubmitAjukan = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Ajukan')]");

    public AbsentOvertimePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Overtime: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(pageTitle);
            return isElementDisplayed(pageTitle);
        } catch (Exception e) {
            return isElementDisplayed(btnAjukanLembur);
        }
    }

    public boolean isBtnAjukanLemburDisplayed() {
        return isElementDisplayed(btnAjukanLembur);
    }

    public boolean isBtnKembaliDisplayed() {
        return isElementDisplayed(btnKembali);
    }

    public void openModalAjukanLembur() {
        logger.info("Opening modal drawer Ajukan Lembur");
        click(btnAjukanLembur);
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
