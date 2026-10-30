package com.company.sqa.pages;

import com.company.sqa.utils.ConfigReader;
import com.company.sqa.utils.SessionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AbsentActivityPage extends BasePage {

    private final By pageHeaderTitle = By.xpath("//*[contains(text(), 'History Absensi')]");
    private final By btnBack = By.xpath("//*[contains(text(), 'History Absensi')]/preceding-sibling::button | //*[contains(text(), 'History Absensi')]/parent::*//button");
    private final By checkInPhotos = By.xpath("//img[@alt='Photo Check In']");
    private final By activityCards = By.xpath("//div[contains(@class, 'MuiBox-root')][.//img[@alt='Photo Check In']]");

    public AbsentActivityPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigReader.getUiBaseUrl();
        String targetUrl = baseUrl + path;
        logger.info("Navigating to Absent Activity: {}", targetUrl);
        SessionHelper.ensureAuthenticated(driver, targetUrl);
        driver.get(targetUrl);
    }

    public boolean isPageHeaderTitleDisplayed() {
        return isElementDisplayed(pageHeaderTitle);
    }

    public String getPageHeaderTitleText() {
        return getText(pageHeaderTitle);
    }

    public boolean isBtnBackDisplayed() {
        return isElementDisplayed(btnBack);
    }

    public void clickBack() {
        logger.info("Clicking back button on Absent Activity page");
        click(btnBack);
    }

    public boolean areActivityCardsDisplayed() {
        try {
            waitForVisibility(checkInPhotos);
            List<WebElement> list = driver.findElements(checkInPhotos);
            return list != null && !list.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getActivityCardsCount() {
        try {
            return driver.findElements(checkInPhotos).size();
        } catch (Exception e) {
            return 0;
        }
    }
}

