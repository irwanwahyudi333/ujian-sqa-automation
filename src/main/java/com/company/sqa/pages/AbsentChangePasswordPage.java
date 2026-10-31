package com.company.sqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AbsentChangePasswordPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(AbsentChangePasswordPage.class);

    private final By titleHeader = By.xpath("//p[contains(@class, 'MuiTypography-root') and contains(text(), 'Change Password')]");
    private final By btnBack = By.xpath("//button[contains(text(), 'Kembali') or .//*[@data-testid='ArrowBackIcon']]");
    private final By inputPassword = By.xpath("//input[@id='password' or @name='password']");
    private final By inputPasswordConfirmation = By.xpath("//input[@id='passwordConfirmation' or @name='passwordConfirmation']");
    private final By toggleButtons = By.xpath("//button[@aria-label='toggle password visibility']");
    private final By btnSubmit = By.xpath("//button[@type='submit' and contains(., 'Confirm New Password')]");
    private final By helperTexts = By.xpath("//p[contains(@class, 'MuiFormHelperText-root') and contains(@class, 'Mui-error')]");

    public AbsentChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(titleHeader);
            return isDisplayed(titleHeader) && isDisplayed(inputPassword);
        } catch (Exception e) {
            log.warn("Halaman Absent Change Password tidak termuat: {}", e.getMessage());
            return isDisplayed(inputPassword);
        }
    }

    public boolean isBtnBackDisplayed() {
        return isDisplayed(btnBack);
    }

    public boolean areFormFieldsDisplayed() {
        return isDisplayed(inputPassword) && isDisplayed(inputPasswordConfirmation) && isDisplayed(btnSubmit);
    }

    public void enterNewPassword(String password) {
        type(inputPassword, password);
        log.info("Memasukkan password baru");
    }

    public void enterConfirmPassword(String confirmation) {
        type(inputPasswordConfirmation, confirmation);
        log.info("Memasukkan konfirmasi password");
    }

    public void clickToggleNewPassword() {
        List<WebElement> toggles = driver.findElements(toggleButtons);
        if (!toggles.isEmpty()) {
            toggles.get(0).click();
            log.info("Mengklik toggle visibilitas password baru");
        }
    }

    public void clickToggleConfirmPassword() {
        List<WebElement> toggles = driver.findElements(toggleButtons);
        if (toggles.size() > 1) {
            toggles.get(1).click();
            log.info("Mengklik toggle visibilitas konfirmasi password");
        }
    }

    public String getNewPasswordInputType() {
        return getAttribute(inputPassword, "type");
    }

    public String getConfirmPasswordInputType() {
        return getAttribute(inputPasswordConfirmation, "type");
    }

    public void clickConfirmNewPasswordButton() {
        click(btnSubmit);
        log.info("Menekan tombol Confirm New Password");
    }

    public boolean isErrorTextDisplayed() {
        try {
            List<WebElement> errors = driver.findElements(helperTexts);
            for (WebElement error : errors) {
                if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

