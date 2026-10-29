package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanValidationOvertimePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanValidationOvertimeSteps {
    private static final Logger logger = LogManager.getLogger(LaporanValidationOvertimeSteps.class);
    private LaporanValidationOvertimePage validationOvertimePage;

    private LaporanValidationOvertimePage getValidationOvertimePage() {
        if (validationOvertimePage == null) {
            validationOvertimePage = new LaporanValidationOvertimePage(DriverManager.getDriver());
        }
        return validationOvertimePage;
    }

    @Dengan("admin membuka halaman laporan validation overtime")
    public void adminMembukaHalamanLaporanValidationOvertime() {
        logger.info("Membuka halaman Laporan Validation Overtime");
        getValidationOvertimePage().open();
        String currentUrl = getValidationOvertimePage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/validation-overtime"),
                "URL halaman laporan validation overtime tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan validation overtime terlihat pada halaman")
    public void tabelDataLaporanValidationOvertimeTerlihatPadaHalaman() {
        Assert.assertTrue(getValidationOvertimePage().isLaporanTableDisplayed(), "Tabel laporan validation overtime tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter validation overtime terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterValidationOvertimeTerlihat() {
        Assert.assertTrue(getValidationOvertimePage().isFilterBarDisplayed(), "Filter bar validation overtime tidak lengkap!");
        Assert.assertTrue(getValidationOvertimePage().isExportBtnDisplayed(), "Tombol export validation overtime tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai validation overtime {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiValidationOvertimeDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal validation overtime: {} s/d {}", startDate, endDate);
        getValidationOvertimePage().enterStartDate(startDate);
        getValidationOvertimePage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada validation overtime")
    public void adminMenekanTombolSearchPadaValidationOvertime() {
        getValidationOvertimePage().clickSearch();
    }

    @Maka("tabel validation overtime menampilkan rekaman untuk karyawan {string}")
    public void tabelValidationOvertimeMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getValidationOvertimePage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel validation overtime!");
    }

    @Dan("tabel validation overtime menampilkan {int} baris data")
    public void tabelValidationOvertimeMenampilkanBarisData(int expectedRows) {
        int actualRows = getValidationOvertimePage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan validation overtime tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error validation overtime {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorValidationOvertime(String expectedError) {
        Assert.assertTrue(getValidationOvertimePage().isSnackbarVisible(), "Snackbar error validation overtime tidak tampil!");
        String actualMsg = getValidationOvertimePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian validation overtime {string}")
    public void adminMemasukkanKataKunciPencarianValidationOvertime(String keyword) {
        getValidationOvertimePage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada validation overtime")
    public void adminMenekanTombolResetPadaValidationOvertime() {
        getValidationOvertimePage().clickReset();
    }

    @Maka("seluruh data validation overtime kembali ditampilkan pada tabel")
    public void seluruhDataValidationOvertimeKembaliDitampilkanPadaTabel() {
        int rows = getValidationOvertimePage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan validation overtime belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter validation overtime")
    public void adminMembukaModalFilterValidationOvertime() {
        getValidationOvertimePage().clickFilterBtn();
    }

    @Maka("modal dialog filter validation overtime terbuka pada layar")
    public void modalDialogFilterValidationOvertimeTerbukaPadaLayar() {
        Assert.assertTrue(getValidationOvertimePage().isFilterModalDisplayed(), "Modal filter validation overtime tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen validation overtime {string}")
    public void adminMemilihFilterDepartemenValidationOvertime(String dept) {
        getValidationOvertimePage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status validasi {string}")
    public void adminMemilihFilterStatusValidasi(String status) {
        getValidationOvertimePage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter validation overtime")
    public void adminMenekanTombolTerapkanPadaModalFilterValidationOvertime() {
        getValidationOvertimePage().clickApplyFilter();
    }

    @Ketika("admin menekan tombol Approve lembur pada baris pertama")
    public void adminMenekanTombolApproveLemburPadaBarisPertama() {
        getValidationOvertimePage().clickApproveRow1();
    }

    @Ketika("admin mengatur jumlah baris validation overtime menjadi {string} per halaman")
    public void adminMengaturJumlahBarisValidationOvertimeMenjadiPerHalaman(String rows) {
        getValidationOvertimePage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export validation overtime")
    public void adminMenekanTombolExportValidationOvertime() {
        getValidationOvertimePage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses validation overtime {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesValidationOvertime(String expectedMsg) {
        Assert.assertTrue(getValidationOvertimePage().isSnackbarVisible(), "Snackbar sukses validation overtime tidak tampil!");
        String actualMsg = getValidationOvertimePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }
}

