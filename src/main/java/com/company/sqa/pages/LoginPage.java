package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final By loginTitle = By.xpath("//h1[contains(text(), 'Login')]");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By passwordToggleBtn = By.id("toggle-password");
    private final By submitButton = By.id("submit");
    private final By alertBanner = By.xpath("//div[@role='alert'] | //div[contains(@class, 'MuiAlert-message')] | //div[contains(@class, 'alert-banner')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String loginUrl = baseUrl + "/authentication/login";
        logger.info("Navigating to login page: {}", loginUrl);
        driver.get(loginUrl);
        waitForVisibility(emailInput);
    }

    public void enterEmail(String email) {
        logger.info("Entering Email in Login page: {}", email);
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        logger.info("Entering Password in Login page: [PROTECTED]");
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

    public void clickMasuk() {
        logger.info("Clicking Masuk button");
        click(submitButton);
    }

    public void doubleClickMasuk() {
        logger.info("Double-clicking Masuk button quickly");
        WebElement btn = waitForClickable(submitButton);
        Actions actions = new Actions(driver);
        actions.doubleClick(btn).perform();
    }

    public void pressEnterOnPassword() {
        logger.info("Pressing Enter key on password input");
        WebElement pwdElement = waitForVisibility(passwordInput);
        pwdElement.sendKeys(Keys.ENTER);
    }

    public void pastePassword(String password) {
        logger.info("Pasting password into input: [PROTECTED]");
        WebElement pwdElement = waitForVisibility(passwordInput);
        pwdElement.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", pwdElement, password);
    }

    public void clearPassword() {
        logger.info("Clearing password field");
        WebElement pwdElement = waitForVisibility(passwordInput);
        pwdElement.clear();
    }

    public String getEmailValue() {
        WebElement element = waitForVisibility(emailInput);
        return element.getAttribute("value");
    }

    public String getPasswordValue() {
        WebElement element = waitForVisibility(passwordInput);
        return element.getAttribute("value");
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

    public boolean isLoginTitleDisplayed() {
        return isElementDisplayed(loginTitle);
    }

    public boolean isEmailInputDisplayed() {
        return isElementDisplayed(emailInput);
    }

    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isSubmitButtonDisplayed() {
        return isElementDisplayed(submitButton);
    }

    public void setWindowResolution(int width, int height) {
        logger.info("Setting browser window resolution to: {}x{}", width, height);
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void refreshPage() {
        logger.info("Refreshing login page");
        driver.navigate().refresh();
        waitForVisibility(emailInput);
    }

    public void navigateBack() {
        logger.info("Navigating back in browser history");
        driver.navigate().back();
    }

    public void navigateForward() {
        logger.info("Navigating forward in browser history");
        driver.navigate().forward();
    }

    public void setZoomLevel(int percentage) {
        logger.info("Setting browser zoom level to {}%", percentage);
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '" + percentage + "%';");
    }

    public boolean isPasswordToggleDisplayed() {
        return isElementDisplayed(passwordToggleBtn);
    }

    public void togglePasswordMultipleTimes(int times) {
        logger.info("Toggling password visibility {} times", times);
        for (int i = 0; i < times; i++) {
            togglePasswordVisibility();
        }
    }

    public void pressTab() {
        logger.info("Pressing TAB key");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }

    public boolean isFormElementsPresent() {
        return isEmailInputDisplayed() && isPasswordInputDisplayed() && isSubmitButtonDisplayed();
    }

    public void navigateTo(String relativeUrl) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String fullUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        if (!relativeUrl.startsWith("/")) {
            fullUrl += "/";
        }
        fullUrl += relativeUrl;
        logger.info("Navigating directly to: {}", fullUrl);
        driver.get(fullUrl);
    }

    public String getSessionToken() {
        try {
            return (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.getItem('token');");
        } catch (Exception e) {
            return null;
        }
    }

    public void clickLogoutIfPresent() {
        By logoutLocator = By.id("logout-btn");
        if (isElementDisplayed(logoutLocator)) {
            logger.info("Clicking logout button");
            click(logoutLocator);
        } else {
            // fallback navigasi ke login
            open();
        }
    }

    public boolean isElementFocused(String elementId) {
        try {
            WebElement active = driver.switchTo().activeElement();
            return elementId.equalsIgnoreCase(active.getAttribute("id"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmitButtonDisabled() {
        try {
            WebElement btn = driver.findElement(submitButton);
            return !btn.isEnabled() || Boolean.parseBoolean(btn.getAttribute("disabled"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasRoleAlert() {
        try {
            WebElement alert = driver.findElement(alertBanner);
            return "alert".equalsIgnoreCase(alert.getAttribute("role"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasAccessibleLabelFor(String elementId) {
        try {
            WebElement label = driver.findElement(By.cssSelector("label[for='" + elementId + "']"));
            return label.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordAriaLabelPresent() {
        try {
            WebElement toggle = driver.findElement(passwordToggleBtn);
            String aria = toggle.getAttribute("aria-label");
            return aria != null && !aria.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void focusElement(String elementId) {
        logger.info("Focusing on element with id: {}", elementId);
        WebElement element = driver.findElement(By.id(elementId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
    }
}

