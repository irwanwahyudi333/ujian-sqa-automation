package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanKoreksiPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanKoreksiSteps {
    private static final Logger logger = LogManager.getLogger(LaporanKoreksiSteps.class);
    private LaporanKoreksiPage koreksiPage;

    private LaporanKoreksiPage getKoreksiPage() {
        if (koreksiPage == null) {
            koreksiPage = new LaporanKoreksiPage(DriverManager.getDriver());
        }
        return koreksiPage;
    }

    @Dengan("admin membuka halaman laporan koreksi absensi")
    public void adminMembukaHalamanLaporanKoreksiAbsensi() {
        logger.info("Membuka halaman Laporan Koreksi Absensi");
        getKoreksiPage().open();
        String currentUrl = getKoreksiPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/koreksi"),
                "URL halaman laporan koreksi tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan koreksi terlihat pada halaman")
    public void tabelDataLaporanKoreksiTerlihatPadaHalaman() {
        Assert.assertTrue(getKoreksiPage().isLaporanTableDisplayed(), "Tabel laporan koreksi tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter koreksi terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterKoreksiTerlihat() {
        Assert.assertTrue(getKoreksiPage().isFilterBarDisplayed(), "Filter bar koreksi tidak lengkap!");
        Assert.assertTrue(getKoreksiPage().isExportBtnDisplayed(), "Tombol export koreksi tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai koreksi {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiKoreksiDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal koreksi: {} s/d {}", startDate, endDate);
        getKoreksiPage().enterStartDate(startDate);
        getKoreksiPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada koreksi")
    public void adminMenekanTombolSearchPadaKoreksi() {
        getKoreksiPage().clickSearch();
    }

    @Maka("tabel koreksi menampilkan rekaman untuk karyawan {string}")
    public void tabelKoreksiMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getKoreksiPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel koreksi!");
    }

    @Dan("tabel koreksi menampilkan {int} baris data")
    public void tabelKoreksiMenampilkanBarisData(int expectedRows) {
        int actualRows = getKoreksiPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan koreksi tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error koreksi {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorKoreksi(String expectedError) {
        Assert.assertTrue(getKoreksiPage().isSnackbarVisible(), "Snackbar error koreksi tidak tampil!");
        String actualMsg = getKoreksiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian koreksi {string}")
    public void adminMemasukkanKataKunciPencarianKoreksi(String keyword) {
        getKoreksiPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada koreksi")
    public void adminMenekanTombolResetPadaKoreksi() {
        getKoreksiPage().clickReset();
    }

    @Maka("seluruh data koreksi kembali ditampilkan pada tabel")
    public void seluruhDataKoreksiKembaliDitampilkanPadaTabel() {
        int rows = getKoreksiPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan koreksi belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter koreksi")
    public void adminMembukaModalFilterKoreksi() {
        getKoreksiPage().clickFilterBtn();
    }

    @Maka("modal dialog filter koreksi terbuka pada layar")
    public void modalDialogFilterKoreksiTerbukaPadaLayar() {
        Assert.assertTrue(getKoreksiPage().isFilterModalDisplayed(), "Modal filter koreksi tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen koreksi {string}")
    public void adminMemilihFilterDepartemenKoreksi(String dept) {
        getKoreksiPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe koreksi {string}")
    public void adminMemilihFilterTipeKoreksi(String tipe) {
        getKoreksiPage().selectTipeKoreksi(tipe);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter koreksi")
    public void adminMenekanTombolTerapkanPadaModalFilterKoreksi() {
        getKoreksiPage().clickApplyFilter();
    }

    @Ketika("admin menekan tombol Approve koreksi jam pada baris pertama")
    public void adminMenekanTombolApproveKoreksiJamPadaBarisPertama() {
        getKoreksiPage().clickApproveBtn(1);
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses koreksi {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesKoreksi(String expectedMsg) {
        Assert.assertTrue(getKoreksiPage().isSnackbarVisible(), "Snackbar sukses koreksi tidak tampil!");
        String actualMsg = getKoreksiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar koreksi tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengatur jumlah baris koreksi menjadi {string} per halaman")
    public void adminMengaturJumlahBarisKoreksiMenjadiPerHalaman(String rows) {
        getKoreksiPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export koreksi")
    public void adminMenekanTombolExportKoreksi() {
        getKoreksiPage().clickExport();
    }
}

