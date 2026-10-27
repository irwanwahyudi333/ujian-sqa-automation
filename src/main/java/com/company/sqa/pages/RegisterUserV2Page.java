package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Page Object Model for Hadir Management - Registrasi User v2
 * Route: /management/user-v2/register
 */
public class RegisterUserV2Page extends BasePage {

    // Header & Feedback Elements
    private final By pageTitle = By.cssSelector(".page-title");
    private final By backButton = By.id("btn-back");
    private final By snackbarAlert = By.id("snackbar-alert");
    private final By snackbarMessage = By.id("snackbar-message");
    private final By snackbarCloseBtn = By.id("snackbar-close");

    // Seksi 1: Account Information
    private final By photoInput = By.cssSelector("input[type='file'][name='logo']");
    private final By photoUploadBtn = By.id("btn-upload-photo");
    private final By photoPreview = By.id("preview-image");
    private final By previewContainer = By.id("preview-container");
    private final By photoRemoveBtn = By.id("remove-photo-btn");
    private final By photoHintText = By.cssSelector(".file-hint");
    private final By nikInput = By.id("nik");
    private final By fullnameInput = By.id("fullname");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By passwordToggleBtn = By.id("toggle-password");

    // Seksi 2: Work Information
    private final By divisiInput = By.id("divisi");
    private final By unitInput = By.id("unit");
    private final By posisiKerjaInput = By.id("posisi-kerja");
    private final By jabatanInput = By.id("jabatan");
    private final By atasanInput = By.id("atasan");
    private final By atasanV2Input = By.id("atasan-v2");
    private final By atasanV3Input = By.id("atasan-v3");
    private final By tipeKontrakInput = By.id("tipe-kontrak");

    // Seksi 3: Pengaturan Absensi
    private final By lokasiKerjaInput = By.id("lokasi-kerja");
    private final By shiftTypeSelect = By.id("shift_type");
    private final By jadwalKerjaInput = By.id("jadwal-kerja");
    private final By selfieSelect = By.id("required_selfie");
    private final By jumlahCutiInput = By.id("jumlah-cuti");

    // Aksi Form
    private final By submitButton = By.id("submit");

    public RegisterUserV2Page(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String registerUrl = baseUrl + "/management/user-v2/register";
        logger.info("Navigating to Register User v2 page: {}", registerUrl);
        driver.get(registerUrl);
        waitForVisibility(nikInput);
    }

    // --- Account Information Actions ---

    public void enterNik(String nik) {
        logger.info("Entering NIK: {}", nik);
        type(nikInput, nik);
    }

    public void enterFullName(String fullName) {
        logger.info("Entering Full Name: {}", fullName);
        type(fullnameInput, fullName);
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
        logger.info("Clicking toggle password visibility button");
        click(passwordToggleBtn);
    }

    public boolean isPasswordMasked() {
        WebElement pwdElement = waitForVisibility(passwordInput);
        String typeAttr = pwdElement.getAttribute("type");
        return "password".equalsIgnoreCase(typeAttr);
    }

    public void uploadPhoto(String relativeOrAbsolutePath) {
        logger.info("Uploading employee photo: {}", relativeOrAbsolutePath);
        Path path = Paths.get(relativeOrAbsolutePath);
        File file;
        if (path.isAbsolute()) {
            file = path.toFile();
        } else {
            file = new File(System.getProperty("user.dir"), relativeOrAbsolutePath);
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("File foto tidak ditemukan: " + file.getAbsolutePath());
        }

        // Selenium standard: send absolute path directly to hidden file input
        WebElement inputElem = driver.findElement(photoInput);
        inputElem.sendKeys(file.getAbsolutePath());
    }

    public void removePhoto() {
        logger.info("Clicking remove photo button");
        click(photoRemoveBtn);
    }

    public boolean isPhotoPreviewDisplayed() {
        try {
            WebElement preview = driver.findElement(previewContainer);
            String classAttr = preview.getAttribute("class");
            return classAttr != null && classAttr.contains("has-image");
        } catch (Exception e) {
            return false;
        }
    }

    // --- Work Information Actions ---

    public void selectAutocomplete(By inputLocator, String value) {
        logger.info("Selecting autocomplete {} with value: {}", inputLocator, value);
        WebElement inputElem = waitForClickable(inputLocator);
        click(inputLocator);
        
        // Find parent wrapper and its dropdown
        WebElement wrapper = inputElem.findElement(By.xpath("./ancestor::div[contains(@class, 'autocomplete-wrapper')]"));
        By dropdownItemLocator = By.xpath(".//div[contains(@class, 'autocomplete-item') and contains(., '" + value + "')]");
        
        try {
            WebElement item = wrapper.findElement(dropdownItemLocator);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true})); " +
                "arguments[0].click();", item);
        } catch (Exception e) {
            // Fallback: set value via JavaScript and dispatch input/change events
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles: true}));", inputElem, value);
        }
    }

    public void selectDivisi(String value) {
        selectAutocomplete(divisiInput, value);
    }

    public void selectUnit(String value) {
        selectAutocomplete(unitInput, value);
    }

    public void selectPosisi(String value) {
        selectAutocomplete(posisiKerjaInput, value);
    }

    public void selectJabatan(String value) {
        selectAutocomplete(jabatanInput, value);
    }

    public void selectAtasan(String value) {
        selectAutocomplete(atasanInput, value);
    }

    public boolean isAtasanDisabled() {
        WebElement atasan = driver.findElement(atasanInput);
        return !atasan.isEnabled() || atasan.getAttribute("disabled") != null;
    }

    public boolean waitForAtasanToBeEnabled(int timeoutSeconds) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait customWait = 
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(timeoutSeconds));
            return customWait.until(d -> {
                WebElement atasan = d.findElement(atasanInput);
                return atasan.isEnabled() && atasan.getAttribute("disabled") == null;
            });
        } catch (Exception e) {
            return false;
        }
    }

    public String getAtasanPlaceholder() {
        WebElement atasan = driver.findElement(atasanInput);
        return atasan.getAttribute("placeholder");
    }

    public void selectAtasanClientV2(String value) {
        selectAutocomplete(atasanV2Input, value);
    }

    public void selectAtasanClientV3(String value) {
        selectAutocomplete(atasanV3Input, value);
    }

    public void selectTipeKontrak(String value) {
        selectAutocomplete(tipeKontrakInput, value);
    }

    // --- Pengaturan Absensi Actions ---

    public void selectLokasiKerja(String value) {
        selectAutocomplete(lokasiKerjaInput, value);
    }

    public void selectShiftType(String value) {
        logger.info("Selecting Shift Type: {}", value);
        WebElement selectElem = waitForVisibility(shiftTypeSelect);
        Select select = new Select(selectElem);
        select.selectByValue(value);
    }

    public void selectJadwalKerja(String value) {
        selectAutocomplete(jadwalKerjaInput, value);
    }

    public void selectSelfie(String value) {
        logger.info("Selecting Required Selfie: {}", value);
        WebElement selectElem = waitForVisibility(selfieSelect);
        Select select = new Select(selectElem);
        select.selectByValue(value);
    }

    public void enterJumlahCuti(int cuti) {
        logger.info("Entering Jumlah Cuti: {}", cuti);
        type(jumlahCutiInput, String.valueOf(cuti));
    }

    // --- Submit & Feedback Actions ---

    public void clickSubmit() {
        logger.info("Clicking Submit button");
        click(submitButton);
    }

    public void clickBack() {
        logger.info("Clicking Kembali button");
        click(backButton);
    }

    public String getSnackbarText() {
        try {
            WebElement alert = waitForVisibility(snackbarAlert);
            WebElement msg = alert.findElement(By.id("snackbar-message"));
            return msg.getText();
        } catch (Exception e) {
            logger.warn("Snackbar alert not visible: {}", e.getMessage());
            return "";
        }
    }

    public boolean isSnackbarDisplayed() {
        try {
            WebElement alert = driver.findElement(snackbarAlert);
            String classAttr = alert.getAttribute("class");
            return classAttr != null && classAttr.contains("show");
        } catch (Exception e) {
            return false;
        }
    }

    public void closeSnackbar() {
        click(snackbarCloseBtn);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public String getShiftTypeValue() {
        WebElement elem = waitForVisibility(shiftTypeSelect);
        return new Select(elem).getFirstSelectedOption().getAttribute("value");
    }

    public String getJumlahCutiValue() {
        return driver.findElement(jumlahCutiInput).getAttribute("value");
    }

    public boolean isAllMainElementsVisible() {
        return isElementDisplayed(nikInput) &&
               isElementDisplayed(fullnameInput) &&
               isElementDisplayed(emailInput) &&
               isElementDisplayed(passwordInput) &&
               isElementDisplayed(photoUploadBtn) &&
               isElementDisplayed(divisiInput) &&
               isElementDisplayed(unitInput) &&
               isElementDisplayed(posisiKerjaInput) &&
               isElementDisplayed(jabatanInput) &&
               isElementDisplayed(atasanInput) &&
               isElementDisplayed(lokasiKerjaInput) &&
               isElementDisplayed(shiftTypeSelect) &&
               isElementDisplayed(selfieSelect) &&
               isElementDisplayed(jumlahCutiInput) &&
               isElementDisplayed(submitButton);
    }

    public boolean isBackButtonVisible() {
        return isElementDisplayed(backButton);
    }

    public String getBackButtonText() {
        return getText(backButton).trim();
    }

    public boolean isSection1Visible() {
        return isElementDisplayed(photoUploadBtn) && isElementDisplayed(nikInput) && isElementDisplayed(fullnameInput);
    }

    public boolean isSection2Visible() {
        return isElementDisplayed(divisiInput) && isElementDisplayed(unitInput) && isElementDisplayed(jabatanInput);
    }

    public boolean isSection3Visible() {
        return isElementDisplayed(lokasiKerjaInput) && isElementDisplayed(shiftTypeSelect) && isElementDisplayed(jumlahCutiInput);
    }

    public boolean isSubmitButtonEnabled() {
        WebElement btn = waitForVisibility(submitButton);
        return btn.isEnabled();
    }

    public boolean areAllPlaceholdersValid() {
        String nikPh = driver.findElement(nikInput).getAttribute("placeholder");
        String namePh = driver.findElement(fullnameInput).getAttribute("placeholder");
        String emailPh = driver.findElement(emailInput).getAttribute("placeholder");
        String pwdPh = driver.findElement(passwordInput).getAttribute("placeholder");
        return nikPh != null && !nikPh.isEmpty() &&
               namePh != null && !namePh.isEmpty() &&
               emailPh != null && !emailPh.isEmpty() &&
               pwdPh != null && !pwdPh.isEmpty();
    }

    public String getPhotoHintText() {
        return getText(photoHintText).trim();
    }

    public void reloadPage() {
        driver.navigate().refresh();
        waitForVisibility(nikInput);
    }

    public void setResponsiveViewport(int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    public void resetViewport() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
    }

    public boolean isFormVisible() {
        return isElementDisplayed(By.id("register-form"));
    }

    public boolean isPageOpened() {
        return isFormVisible();
    }

    public void clickBreadcrumb(String text) {
        By crumbLocator = By.xpath("//nav[contains(@class, 'breadcrumb')]//a[contains(text(), '" + text + "')]");
        click(crumbLocator);
    }

    public void simulateTabNavigation() {
        WebElement nik = waitForVisibility(nikInput);
        nik.sendKeys(org.openqa.selenium.Keys.TAB);
    }

    public void submitWithEnterKey() {
        WebElement cuti = waitForVisibility(jumlahCutiInput);
        cuti.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void clickSubmitRapidly(int times) {
        WebElement btn = waitForVisibility(submitButton);
        for (int i = 0; i < times; i++) {
            try {
                btn.click();
            } catch (Exception e) {
                logger.info("Rapid submit click {} was prevented by UI loading protection: {}", i + 1, e.getMessage());
            }
        }
    }

    public String getNikValue() {
        return driver.findElement(nikInput).getAttribute("value");
    }

    public String getFullnameValue() {
        return driver.findElement(fullnameInput).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }

    public String getPasswordValue() {
        return driver.findElement(passwordInput).getAttribute("value");
    }

    public void resetJabatan() {
        WebElement jab = waitForVisibility(jabatanInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = ''; arguments[0].dispatchEvent(new Event('input', {bubbles: true})); arguments[0].dispatchEvent(new Event('change', {bubbles: true}));", jab);
    }
}
