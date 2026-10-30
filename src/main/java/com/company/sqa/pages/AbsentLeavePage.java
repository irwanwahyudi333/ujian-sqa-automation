package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbsentLeavePage extends BasePage {

    private final By pageTitle = By.xpath("//*[contains(., 'Halaman Cuti')]");
    private final By totalCounter = By.xpath("//*[contains(., 'List Request Cuti')]");
    private final By btnAjukanCuti = By.xpath("//button[contains(., 'Ajukan Cuti')]");
    private final By btnKembali = By.xpath("//button[contains(., 'Kembali')]");

    // Modal Drawer Form
    private final By modalDrawer = By.xpath("//div[contains(@class, 'MuiDrawer-root')]//*[contains(., 'Ajukan Cuti')]");
    private final By selectTipeCuti = By.xpath("//*[@id='leave_type_id'] | //*[contains(., 'Tipe Cuti')]");
    private final By inputRentangTanggal = By.xpath("//*[contains(text(), '-') or contains(@class, 'css-6rfaag')]");
    private final By inputCatatan = By.xpath("//textarea[@id='notes' or @name='notes'] | //input[@id='notes']");
    private final By btnReset = By.xpath("//button[contains(., 'Reset')]");
    private final By btnSubmitAjukan = By.xpath("//button[contains(., 'Ajukan')]");

    public AbsentLeavePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Leave: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(pageTitle);
            return isElementDisplayed(pageTitle);
        } catch (Exception e) {
            return isElementDisplayed(btnAjukanCuti);
        }
    }

    public boolean isBtnAjukanCutiDisplayed() {
        return isElementDisplayed(btnAjukanCuti);
    }

    public boolean isBtnKembaliDisplayed() {
        return isElementDisplayed(btnKembali);
    }

    public void openModalAjukanCuti() {
        logger.info("Opening modal drawer Ajukan Cuti");
        click(btnAjukanCuti);
    }

    public boolean isModalDrawerDisplayed() {
        return isElementDisplayed(modalDrawer);
    }

    public boolean areModalFormFieldsDisplayed() {
        try {
            waitForVisibility(modalDrawer);
            return isElementDisplayed(selectTipeCuti) &&
                   isElementDisplayed(inputCatatan) &&
                   isElementDisplayed(btnReset) &&
                   isElementDisplayed(btnSubmitAjukan);
        } catch (Exception e) {
            return false;
        }
    }
}
