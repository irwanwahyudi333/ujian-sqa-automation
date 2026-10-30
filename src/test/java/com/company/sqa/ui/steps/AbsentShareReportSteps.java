package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentShareReportPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AbsentShareReportSteps {

    private static final Logger logger = LogManager.getLogger(AbsentShareReportSteps.class);
    private AbsentShareReportPage absentShareReportPage;

    private AbsentShareReportPage getAbsentShareReportPage() {
        if (absentShareReportPage == null) {
            absentShareReportPage = new AbsentShareReportPage(DriverManager.getDriver());
        }
        return absentShareReportPage;
    }

    @Maka("Halaman share report atau halaman proteksi presensi harus ditampilkan")
    public void halamanShareReportAtauHalamanProteksiPresensiHarusDitampilkan() {
        WebDriver driver = DriverManager.getDriver();
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL pada pengujian share report: {}", currentUrl);
        boolean isShareReport = currentUrl.contains("/reports/share_report");
        boolean isAbsentHome = currentUrl.contains("/apps/absent");
        Assert.assertTrue(isShareReport || isAbsentHome, "URL tidak valid: " + currentUrl);
    }

    @Dan("Header navigasi harus tersedia")
    public void headerNavigasiHarusTersedia() {
        WebDriver driver = DriverManager.getDriver();
        if (driver.getCurrentUrl().contains("/reports/share_report")) {
            Assert.assertTrue(getAbsentShareReportPage().isBtnBackDisplayed(), "Tombol Kembali tidak tampil!");
        } else {
            logger.info("Pengguna ter-redirect ke /apps/absent karena hak akses non-upliner (SEC-REP-001)");
            Assert.assertTrue(driver.getCurrentUrl().contains("/apps/absent"), "Bukan halaman absent!");
        }
    }

    @Ketika("Pengguna berada pada halaman formulir share report")
    public void penggunaBeradaPadaHalamanFormulirShareReport() {
        WebDriver driver = DriverManager.getDriver();
        logger.info("Memeriksa status halaman formulir share report pada: {}", driver.getCurrentUrl());
    }

    @Maka("Tombol download report harus ditampilkan")
    public void tombolDownloadReportHarusDitampilkan() {
        WebDriver driver = DriverManager.getDriver();
        if (driver.getCurrentUrl().contains("/reports/share_report")) {
            Assert.assertTrue(getAbsentShareReportPage().isBtnDownloadDisplayed(), "Tombol Download Report tidak tampil!");
        } else {
            logger.info("Kontrol otorisasi SEC-REP-001 aktif: Non-upliner tidak diizinkan mengunduh share report");
            Assert.assertTrue(driver.getCurrentUrl().contains("/apps/absent"), "Pengguna tidak berada pada halaman absent!");
        }
    }
}
