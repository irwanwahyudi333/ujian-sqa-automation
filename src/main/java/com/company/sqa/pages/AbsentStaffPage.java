package com.company.sqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbsentStaffPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(AbsentStaffPage.class);

    private final By titleHeader = By.xpath("//p[contains(@class, 'MuiTypography-root') and contains(text(), 'View Staff')]");
    private final By btnBack = By.xpath("//button[contains(@class, 'MuiIconButton-root')][.//*[name()='polyline']]");
    private final By staffContainer = By.xpath("//div[contains(@class, 'MuiGrid-container')] | //div[contains(@class, 'css-1hpdiro')]");

    public AbsentStaffPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            waitForVisibility(titleHeader);
            return isDisplayed(titleHeader);
        } catch (Exception e) {
            log.warn("Halaman Absent Staff tidak termuat sepenuhnya: {}", e.getMessage());
            return isDisplayed(titleHeader);
        }
    }

    public boolean isBtnBackDisplayed() {
        return isDisplayed(btnBack);
    }

    public boolean isStaffContainerDisplayed() {
        return isDisplayed(staffContainer);
    }
}

