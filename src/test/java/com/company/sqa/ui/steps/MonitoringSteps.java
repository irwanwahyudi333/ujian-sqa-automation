package com.company.sqa.ui.steps;

import com.company.sqa.pages.MonitoringPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class MonitoringSteps {
    private static final Logger logger = LogManager.getLogger(MonitoringSteps.class);
    private MonitoringPage monitoringPage;

    private MonitoringPage getMonitoringPage() {
        if (monitoringPage == null) {
            monitoringPage = new MonitoringPage(DriverManager.getDriver());
        }
        return monitoringPage;
    }

    @Dengan("auditor membuka halaman monitoring {string}")
    public void auditorMembukaHalamanMonitoring(String relativePath) {
        logger.info("Auditor membuka halaman: {}", relativePath);
        getMonitoringPage().open(relativePath);
        Assert.assertTrue(getMonitoringPage().isPageLoaded(),
                "Halaman monitoring '" + relativePath + "' gagal dimuat!");
    }

    @Maka("tabel data pemantauan terlihat pada halaman")
    public void tabelDataPemantauanTerlihatPadaHalaman() {
        Assert.assertTrue(getMonitoringPage().isDataTableDisplayed(),
                "Tabel data monitoring tidak tampil pada halaman!");
    }

    @Dan("kontrol pencarian dan filter tanggal monitoring terlihat")
    public void kontrolPencarianDanFilterTanggalMonitoringTerlihat() {
        Assert.assertTrue(getMonitoringPage().isFilterBarDisplayed(),
                "Kontrol pencarian atau filter tanggal monitoring tidak tampil!");
    }

    @Dan("kartu statistik metrik kehadiran terlihat pada dashboard monitoring")
    public void kartuStatistikMetrikKehadiranTerlihatPadaDashboardMonitoring() {
        Assert.assertTrue(getMonitoringPage().areStatCardsDisplayed(),
                "Kartu statistik metrik kehadiran tidak tampil pada dashboard monitoring!");
    }

    @Ketika("auditor mencari data dengan kata kunci {string} pada monitoring")
    public void auditorMencariDataDenganKataKunciPadaMonitoring(String keyword) {
        getMonitoringPage().search(keyword);
    }

    @Maka("tabel monitoring menampilkan baris dengan teks {string}")
    public void tabelMonitoringMenampilkanBarisDenganTeks(String expectedText) {
        Assert.assertTrue(getMonitoringPage().isTextPresentInTable(expectedText),
                "Teks atau data '" + expectedText + "' tidak ditemukan dalam tabel monitoring!");
    }

    @Ketika("auditor menekan tombol reset pada monitoring")
    public void auditorMenekanTombolResetPadaMonitoring() {
        getMonitoringPage().resetFilter();
    }

    @Maka("jumlah baris data tabel monitoring lebih besar dari nol")
    public void jumlahBarisDataTabelMonitoringLebihBesarDariNol() {
        Assert.assertTrue(getMonitoringPage().getTableRowCount() > 0,
                "Tabel monitoring tidak memiliki baris data!");
    }

    @Maka("halaman tidak menampilkan tombol aksi manipulasi atau approval data")
    public void halamanTidakMenampilkanTombolAksiManipulasiAtauApprovalData() {
        logger.info("Memverifikasi batasan hak akses (Least Privilege) akun Auditor Monitoring");
        Assert.assertFalse(getMonitoringPage().hasForbiddenActionButtons(),
                "Pelanggaran Otorisasi! Ditemukan tombol aksi modifikasi/approval pada akun auditor monitoring!");
    }

    @Maka("formulir generator laporan monitoring terlihat pada halaman")
    public void formulirGeneratorLaporanMonitoringTerlihatPadaHalaman() {
        Assert.assertTrue(getMonitoringPage().isDownloadFormDisplayed(),
                "Formulir generator download report monitoring tidak tampil!");
    }

    @Ketika("auditor memilih departemen monitoring {string}")
    public void auditorMemilihDepartemenMonitoring(String dept) {
        getMonitoringPage().selectDepartemen(dept);
    }

    @Ketika("auditor menekan tombol unduh laporan monitoring")
    public void auditorMenekanTombolUnduhLaporanMonitoring() {
        getMonitoringPage().clickDownloadReport();
    }

    @Maka("notifikasi proses unduhan laporan audit berhasil ditampilkan")
    public void notifikasiProsesUnduhanLaporanAuditBerhasilDitampilkan() {
        String msg = getMonitoringPage().getSnackbarText();
        logger.info("Notifikasi unduhan laporan audit: '{}'", msg);
        Assert.assertNotNull(msg, "Pesan notifikasi feedback unduhan tidak boleh null!");
    }
}

