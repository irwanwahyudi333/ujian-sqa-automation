package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanDailyReportPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanDailyReportSteps {
    private static final Logger logger = LogManager.getLogger(LaporanDailyReportSteps.class);
    private LaporanDailyReportPage dailyReportPage;

    private LaporanDailyReportPage getDailyReportPage() {
        if (dailyReportPage == null) {
            dailyReportPage = new LaporanDailyReportPage(DriverManager.getDriver());
        }
        return dailyReportPage;
    }

    @Dengan("admin membuka halaman laporan daily report")
    public void adminMembukaHalamanLaporanDailyReport() {
        logger.info("Membuka halaman Laporan Daily Report");
        getDailyReportPage().open();
        String currentUrl = getDailyReportPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/daily-report"),
                "URL halaman laporan daily report tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan daily report terlihat pada halaman")
    public void tabelDataLaporanDailyReportTerlihatPadaHalaman() {
        Assert.assertTrue(getDailyReportPage().isLaporanTableDisplayed(), "Tabel laporan daily report tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter daily report terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterDailyReportTerlihat() {
        Assert.assertTrue(getDailyReportPage().isFilterBarDisplayed(), "Filter bar daily report tidak lengkap!");
        Assert.assertTrue(getDailyReportPage().isExportBtnDisplayed(), "Tombol export daily report tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai daily report {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiDailyReportDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal daily report: {} s/d {}", startDate, endDate);
        getDailyReportPage().enterStartDate(startDate);
        getDailyReportPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada daily report")
    public void adminMenekanTombolSearchPadaDailyReport() {
        getDailyReportPage().clickSearch();
    }

    @Maka("tabel daily report menampilkan rekaman untuk karyawan {string}")
    public void tabelDailyReportMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getDailyReportPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel daily report!");
    }

    @Dan("tabel daily report menampilkan {int} baris data")
    public void tabelDailyReportMenampilkanBarisData(int expectedRows) {
        int actualRows = getDailyReportPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan daily report tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error daily report {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorDailyReport(String expectedError) {
        Assert.assertTrue(getDailyReportPage().isSnackbarVisible(), "Snackbar error daily report tidak tampil!");
        String actualMsg = getDailyReportPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian daily report {string}")
    public void adminMemasukkanKataKunciPencarianDailyReport(String keyword) {
        getDailyReportPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada daily report")
    public void adminMenekanTombolResetPadaDailyReport() {
        getDailyReportPage().clickReset();
    }

    @Maka("seluruh data daily report kembali ditampilkan pada tabel")
    public void seluruhDataDailyReportKembaliDitampilkanPadaTabel() {
        int rows = getDailyReportPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan daily report belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter daily report")
    public void adminMembukaModalFilterDailyReport() {
        getDailyReportPage().clickFilterBtn();
    }

    @Maka("modal dialog filter daily report terbuka pada layar")
    public void modalDialogFilterDailyReportTerbukaPadaLayar() {
        Assert.assertTrue(getDailyReportPage().isFilterModalDisplayed(), "Modal filter daily report tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen daily report {string}")
    public void adminMemilihFilterDepartemenDailyReport(String dept) {
        getDailyReportPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status kehadiran daily report {string}")
    public void adminMemilihFilterStatusKehadiranDailyReport(String status) {
        getDailyReportPage().selectStatusDaily(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter daily report")
    public void adminMenekanTombolTerapkanPadaModalFilterDailyReport() {
        getDailyReportPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail daily report pada baris ke-{int}")
    public void adminMengklikTombolDetailDailyReportPadaBarisKe(int rowIndex) {
        getDailyReportPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail daily report terbuka pada layar")
    public void modalDialogDetailDailyReportTerbukaPadaLayar() {
        Assert.assertTrue(getDailyReportPage().isDetailModalDisplayed(), "Modal detail daily report tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail daily report untuk {string}")
    public void modalMenampilkanJudulDetailDailyReportUntuk(String expectedName) {
        String title = getDailyReportPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail daily report tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail daily report")
    public void adminMenutupModalDetailDailyReport() {
        getDailyReportPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris daily report menjadi {string} per halaman")
    public void adminMengaturJumlahBarisDailyReportMenjadiPerHalaman(String rows) {
        getDailyReportPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export daily report")
    public void adminMenekanTombolExportDailyReport() {
        getDailyReportPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses daily report {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesDailyReport(String expectedMsg) {
        Assert.assertTrue(getDailyReportPage().isSnackbarVisible(), "Snackbar sukses export daily report tidak tampil!");
        String actualMsg = getDailyReportPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }
}

