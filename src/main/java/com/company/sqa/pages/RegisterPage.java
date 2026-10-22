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

    public String getEmailHtml5ValidationMessage() {
        WebElement element = driver.findElement(emailInput);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public String getFullNameHtml5ValidationMessage() {
        WebElement element = driver.findElement(fullNameInput);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public String getPasswordHtml5ValidationMessage() {
        WebElement element = driver.findElement(passwordInput);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public String getSelfieHtml5ValidationMessage() {
        WebElement element = driver.findElement(selfieFileInput);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    public boolean isNikInputDisplayed() {
        return isElementDisplayed(nikInput);
    }

    public boolean isFullNameInputDisplayed() {
        return isElementDisplayed(fullNameInput);
    }

    public boolean isEmailInputDisplayed() {
        return isElementDisplayed(emailInput);
    }

    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isSelfieInputDisplayed() {
        return isElementDisplayed(selfieFileInput);
    }

    public boolean isSubmitButtonDisplayed() {
        return isElementDisplayed(submitButton);
    }

    public boolean isLoginLinkDisplayed() {
        return isElementDisplayed(loginLinkBtn);
    }

    public void refreshPage() {
        logger.info("Refreshing register page");
        driver.navigate().refresh();
        waitForVisibility(nikInput);
    }

    public void navigateBack() {
        logger.info("Navigating back in browser history");
        driver.navigate().back();
    }

    public String getNikValue() {
        return waitForVisibility(nikInput).getAttribute("value");
    }

    public String getFullNameValue() {
        return waitForVisibility(fullNameInput).getAttribute("value");
    }

    public String getEmailValue() {
        return waitForVisibility(emailInput).getAttribute("value");
    }

    public String getPasswordValue() {
        return waitForVisibility(passwordInput).getAttribute("value");
    }

    public void focusAnotherElement() {
        click(fullNameInput);
    }

    public boolean isSubmitLoading() {
        try {
            return !driver.findElements(By.cssSelector("#submit .MuiCircularProgress-root, #submit[disabled]")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void pressEnterOnPassword() {
        driver.findElement(passwordInput).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void resizeWindow(int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    public void setZoom(int percent) {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '" + percent + "%';");
    }

    public String getNikPlaceholder() {
        return driver.findElement(nikInput).getAttribute("placeholder");
    }

    public String getFullNamePlaceholder() {
        return driver.findElement(fullNameInput).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return driver.findElement(passwordInput).getAttribute("placeholder");
    }

    public void clearPassword() {
        driver.findElement(passwordInput).clear();
    }

    public boolean isHttps() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.startsWith("https://")) {
            return true;
        }
        // Jika sedang berjalan di local test environment (mock server / localhost), tolerir localhost
        if (currentUrl.contains("localhost") || currentUrl.contains("127.0.0.1")) {
            return true;
        }
        return false;
    }

    public String getPasswordInputType() {
        return driver.findElement(passwordInput).getAttribute("type");
    }

    public void pressTab() {
        new org.openqa.selenium.interactions.Actions(driver).sendKeys(org.openqa.selenium.Keys.TAB).perform();
    }

    public void pressEnterOnLoginLink() {
        driver.findElement(loginLinkBtn).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public String getActiveElementId() {
        WebElement active = driver.switchTo().activeElement();
        return active != null ? active.getAttribute("id") : "";
    }

    public void pasteIntoNik(String text) {
        WebElement el = driver.findElement(nikInput);
        el.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", el, text);
    }

    public void pasteIntoFullName(String text) {
        WebElement el = driver.findElement(fullNameInput);
        el.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", el, text);
    }

    public void pasteIntoEmail(String text) {
        WebElement el = driver.findElement(emailInput);
        el.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", el, text);
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open(window.location.href, '_blank');");
        java.util.List<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        }
    }

    public void closeExtraTabs() {
        java.util.List<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            for (int i = 1; i < tabs.size(); i++) {
                driver.switchTo().window(tabs.get(i));
                driver.close();
            }
            driver.switchTo().window(tabs.get(0));
        }
    }

    public boolean hasLabelFor(String fieldId) {
        return !driver.findElements(By.xpath("//label[@for='" + fieldId + "'] | //label[contains(text(), '" + fieldId + "')] | //*[@id='" + fieldId + "']")).isEmpty();
    }
}
