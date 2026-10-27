package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model untuk halaman edit pengguna dan approval registrasi mandiri
 * (https://magang.dikahadir.com/management/user-v2/edit/{id}).
 */
public class UserEditPage extends BasePage {

    private final By pageTitle = By.xpath("//h1[contains(text(), 'Edit User')] | //h4[contains(text(), 'Edit User')] | //h2[contains(text(), 'Edit User')]");
    private final By selfieContainer = By.id("selfie-container");
    private final By selfieImage = By.cssSelector("img[alt='company_logo'], #user-photo");
    private final By nikInput = By.id("nik");
    private final By fullnameInput = By.id("fullname");
    private final By emailInput = By.id("email");
    private final By submitBtn = By.cssSelector("button[type='submit'], #btn-submit");
    private final By cancelBtn = By.cssSelector("button#btn-cancel, button.btn-secondary");
    private final By alertSnackbar = By.id("alert-snackbar");
    private final By alertMessage = By.id("alert-message");

    public UserEditPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigasi langsung ke URL edit user berdasarkan ID.
     */
    public void open(String userId) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String url = baseUrl + "/management/user-v2/edit/" + userId;
        logger.info("Navigasi ke halaman edit user: {}", url);
        driver.get(url);
        waitForVisibility(pageTitle);
    }

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public boolean isSelfieContainerDisplayed() {
        return isElementDisplayed(selfieContainer) || isElementDisplayed(selfieImage);
    }

    public String getSelfieImageSrc() {
        try {
            WebElement img = waitForVisibility(selfieImage);
            return img.getAttribute("src");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isSelfieImageBroken() {
        try {
            WebElement img = driver.findElement(selfieImage);
            String brokenAttr = img.getAttribute("data-broken");
            if ("false".equalsIgnoreCase(brokenAttr)) {
                return false;
            }
            if ("true".equalsIgnoreCase(brokenAttr)) {
                return true;
            }
            Object naturalWidth = ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].naturalWidth;", img);
            return naturalWidth != null && ((Number) naturalWidth).intValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailInputEditable() {
        WebElement emailElem = waitForVisibility(emailInput);
        String readonly = emailElem.getAttribute("readonly");
        String disabled = emailElem.getAttribute("disabled");
        return (readonly == null || readonly.isEmpty()) && (disabled == null || disabled.isEmpty());
    }

    public void enterNik(String nik) {
        type(nikInput, nik);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void clickSubmit() {
        click(submitBtn);
    }

    public String getAlertMessage() {
        waitForVisibility(alertSnackbar);
        return getText(alertMessage);
    }

    public String getNikValue() {
        return driver.findElement(nikInput).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }
}
