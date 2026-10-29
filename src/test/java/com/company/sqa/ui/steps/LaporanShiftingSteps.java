package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanShiftingPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanShiftingSteps {
    private static final Logger logger = LogManager.getLogger(LaporanShiftingSteps.class);
    private LaporanShiftingPage shiftingPage;

    private LaporanShiftingPage getShiftingPage() {
        if (shiftingPage == null) {
            shiftingPage = new LaporanShiftingPage(DriverManager.getDriver());
        }
        return shiftingPage;
    }

    @Dengan("admin membuka halaman laporan shifting")
    public void adminMembukaHalamanLaporanShifting() {
        logger.info("Membuka halaman Laporan Shifting");
        getShiftingPage().open();
        String currentUrl = getShiftingPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/shifting"),
                "URL halaman laporan shifting tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan shifting terlihat pada halaman")
    public void tabelDataLaporanShiftingTerlihatPadaHalaman() {
        Assert.assertTrue(getShiftingPage().isLaporanTableDisplayed(), "Tabel laporan shifting tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter shifting terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterShiftingTerlihat() {
        Assert.assertTrue(getShiftingPage().isFilterBarDisplayed(), "Filter bar shifting tidak lengkap!");
        Assert.assertTrue(getShiftingPage().isExportBtnDisplayed(), "Tombol export shifting tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai shifting {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiShiftingDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal shifting: {} s/d {}", startDate, endDate);
        getShiftingPage().enterStartDate(startDate);
        getShiftingPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada shifting")
    public void adminMenekanTombolSearchPadaShifting() {
        getShiftingPage().clickSearch();
    }

    @Maka("tabel shifting menampilkan rekaman untuk karyawan {string}")
    public void tabelShiftingMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getShiftingPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel shifting!");
    }

    @Dan("tabel shifting menampilkan {int} baris data")
    public void tabelShiftingMenampilkanBarisData(int expectedRows) {
        int actualRows = getShiftingPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan shifting tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error shifting {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorShifting(String expectedError) {
        Assert.assertTrue(getShiftingPage().isSnackbarVisible(), "Snackbar error shifting tidak tampil!");
        String actualMsg = getShiftingPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian shifting {string}")
    public void adminMemasukkanKataKunciPencarianShifting(String keyword) {
        getShiftingPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada shifting")
    public void adminMenekanTombolResetPadaShifting() {
        getShiftingPage().clickReset();
    }

    @Maka("seluruh data shifting kembali ditampilkan pada tabel")
    public void seluruhDataShiftingKembaliDitampilkanPadaTabel() {
        int rows = getShiftingPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan shifting belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter shifting")
    public void adminMembukaModalFilterShifting() {
        getShiftingPage().clickFilterBtn();
    }

    @Maka("modal dialog filter shifting terbuka pada layar")
    public void modalDialogFilterShiftingTerbukaPadaLayar() {
        Assert.assertTrue(getShiftingPage().isFilterModalDisplayed(), "Modal filter shifting tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen shifting {string}")
    public void adminMemilihFilterDepartemenShifting(String dept) {
        getShiftingPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter pola shift {string}")
    public void adminMemilihFilterPolaShift(String shift) {
        getShiftingPage().selectPolaShift(shift);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter shifting")
    public void adminMenekanTombolTerapkanPadaModalFilterShifting() {
        getShiftingPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail shifting pada baris ke-{int}")
    public void adminMengklikTombolDetailShiftingPadaBarisKe(int rowIndex) {
        getShiftingPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail shifting terbuka pada layar")
    public void modalDialogDetailShiftingTerbukaPadaLayar() {
        Assert.assertTrue(getShiftingPage().isDetailModalDisplayed(), "Modal detail shifting tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail shifting untuk {string}")
    public void modalMenampilkanJudulDetailShiftingUntuk(String expectedName) {
        String title = getShiftingPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail shifting tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail shifting")
    public void adminMenutupModalDetailShifting() {
        getShiftingPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris shifting menjadi {string} per halaman")
    public void adminMengaturJumlahBarisShiftingMenjadiPerHalaman(String rows) {
        getShiftingPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export shifting")
    public void adminMenekanTombolExportShifting() {
        getShiftingPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses shifting {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesShifting(String expectedMsg) {
        Assert.assertTrue(getShiftingPage().isSnackbarVisible(), "Snackbar sukses export shifting tidak tampil!");
        String actualMsg = getShiftingPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }
}

