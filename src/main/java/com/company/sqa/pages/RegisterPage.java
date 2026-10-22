package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    // Locators
    private final By nikInput = By.id("nik");
    private final By fullNameInput = By.id("fullname");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By passwordToggleBtn = By.cssSelector("div.MuiInputAdornment-root button");
    private final By selfieFileInput = By.id("selfie");
    private final By submitButton = By.id("submit");
    private final By loginLinkBtn = By.xpath("//button[contains(text(), 'disini')]");
    private final By alertBanner = By.xpath("//div[@role='alert'] | //div[contains(@class, 'MuiAlert-message')]");
    private final By fileErrorText = By.xpath("//*[contains(text(), 'File harus berupa gambar')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String registerUrl = baseUrl + "/absen/register";
        logger.info("Navigating to register page: {}", registerUrl);
        driver.get(registerUrl);
        waitForVisibility(nikInput);
    }

    public void enterNik(String nik) {
        logger.info("Entering NIK: {}", nik);
        type(nikInput, nik);
    }

    public void enterFullName(String fullName) {
        logger.info("Entering Full Name: {}", fullName);
        type(fullNameInput, fullName);
    }

    public void enterEmail(String email) {
        logger.info("Entering Email: {}", email);
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        logger.info("Entering Password: [PROTECTED]");
        type(passwordInput, password);
    }

    public void togglePasswordVisibility() {
        logger.info("Clicking password visibility toggle button");
        click(passwordToggleBtn);
    }

    public boolean isPasswordMasked() {
        WebElement pwdElement = waitForVisibility(passwordInput);
        String typeAttr = pwdElement.getAttribute("type");
        return "password".equalsIgnoreCase(typeAttr);
    }

    public void uploadSelfieImage(String relativeOrAbsolutePath) {
        logger.info("Uploading selfie file: {}", relativeOrAbsolutePath);
        uploadFile(selfieFileInput, relativeOrAbsolutePath);
    }

    public void clickDaftar() {
        logger.info("Clicking Daftar button");
        click(submitButton);
    }

    public void clickMasukDisini() {
        logger.info("Clicking 'disini' link to go to login page");
        click(loginLinkBtn);
    }

    public String getAlertText() {
        try {
            return getText(alertBanner);
        } catch (Exception e) {
            logger.warn("Alert banner not visible within timeout: {}", e.getMessage());
            return "";
        }
    }

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertBanner);
    }

    public String getFileErrorMessage() {
        try {
            return getText(fileErrorText);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isFileErrorDisplayed() {
        return isElementDisplayed(fileErrorText);
    }

    public String getNikHtml5ValidationMessage() {
        WebElement element = driver.findElement(nikInput);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public String getPasswordValue() {
        WebElement pwdElement = waitForVisibility(passwordInput);
        return pwdElement.getAttribute("value");
    }
}
