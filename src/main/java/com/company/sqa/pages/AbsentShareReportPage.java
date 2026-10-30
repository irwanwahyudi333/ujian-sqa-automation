package com.company.sqa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AbsentShareReportPage extends BasePage {

    private static final Logger log = LogManager.getLogger(AbsentShareReportPage.class);

    private final By titleHeader = By.xpath("//*[contains(@class, 'MuiTypography') and contains(text(), 'Download Report')]");
    private final By btnBack = By.xpath("//button[contains(@class, 'css-back-btn') or .//*[name()='polyline'] or .//*[name()='line']]");
    private final By selectReport = By.xpath("//select[@name='share_report' or @id='share_report_select']");
    private final By btnDownload = By.xpath("//button[@id='btn-download-report' or (@type='submit' and contains(., 'Download Report'))]");
    private final By errorMessage = By.xpath("//p[@id='error-message' or (contains(@class, 'MuiFormHelperText-root') and contains(@class, 'Mui-error'))]");

    public AbsentShareReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(titleHeader);
            return isDisplayed(titleHeader) && isDisplayed(selectReport);
        } catch (Exception e) {
            log.warn("Halaman Absent Share Report tidak termuat sepenuhnya: {}", e.getMessage());
            return isDisplayed(selectReport);
        }
    }

    public boolean isBtnBackDisplayed() {
        return isDisplayed(btnBack);
    }

    public boolean isSelectReportDisplayed() {
        return isDisplayed(selectReport);
    }

    public boolean isBtnDownloadDisplayed() {
        return isDisplayed(btnDownload);
    }

    public void selectReportType(String typeOrText) {
        try {
            WebElement selectElement = waitForVisibility(selectReport);
            Select select = new Select(selectElement);
            if (typeOrText.equalsIgnoreCase("day") || typeOrText.equalsIgnoreCase("Harian")) {
                select.selectByValue("day");
            } else if (typeOrText.equalsIgnoreCase("week") || typeOrText.equalsIgnoreCase("Mingguan")) {
                select.selectByValue("week");
            } else if (typeOrText.equalsIgnoreCase("month") || typeOrText.equalsIgnoreCase("Bulanan")) {
                select.selectByValue("month");
            } else {
                select.selectByVisibleText(typeOrText);
            }
            log.info("Memilih tipe share report: {}", typeOrText);
        } catch (Exception e) {
            log.error("Gagal memilih tipe share report {}: {}", typeOrText, e.getMessage());
        }
    }

    public void clickDownloadReport() {
        click(btnDownload);
        log.info("Menekan tombol Download Report");
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement error = waitForVisibility(errorMessage);
            return error != null && error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }
}
