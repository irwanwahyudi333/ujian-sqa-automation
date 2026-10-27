package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanAllPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanAllSteps {
    private static final Logger logger = LogManager.getLogger(LaporanAllSteps.class);
    private LaporanAllPage laporanPage;

    private LaporanAllPage getLaporanPage() {
        if (laporanPage == null) {
            laporanPage = new LaporanAllPage(DriverManager.getDriver());
        }
        return laporanPage;
    }

    @Dengan("admin membuka halaman laporan semua")
    public void adminMembukaHalamanLaporanSemua() {
        logger.info("Membuka halaman laporan semua");
        getLaporanPage().open();
        String currentUrl = getLaporanPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/all"),
                "URL halaman laporan semua tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan kehadiran terlihat pada halaman")
    public void tabelDataLaporanKehadiranTerlihatPadaHalaman() {
        Assert.assertTrue(getLaporanPage().isLaporanTableDisplayed(), "Tabel laporan kehadiran tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter laporan terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterLaporanTerlihat() {
        Assert.assertTrue(getLaporanPage().isFilterBarDisplayed(), "Filter bar laporan tidak tampil lengkap!");
        Assert.assertTrue(getLaporanPage().isExportBtnDisplayed(), "Tombol export tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai laporan {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiLaporanDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal: {} s/d {}", startDate, endDate);
        getLaporanPage().enterStartDate(startDate);
        getLaporanPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada laporan")
    public void adminMenekanTombolSearchPadaLaporan() {
        getLaporanPage().clickSearch();
    }

    @Maka("tabel menampilkan aktivitas kehadiran untuk karyawan {string}")
    public void tabelMenampilkanAktivitasKehadiranUntukKaryawan(String expectedName) {
        Assert.assertTrue(getLaporanPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel laporan!");
    }

    @Dan("tabel laporan menampilkan {int} baris data")
    public void tabelLaporanMenampilkanBarisData(int expectedRows) {
        int actualRows = getLaporanPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error laporan {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorLaporan(String expectedMsg) {
        Assert.assertTrue(getLaporanPage().isSnackbarVisible(), "Snackbar tidak muncul!");
        String actualMsg = getLaporanPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian laporan {string}")
    public void adminMemasukkanKataKunciPencarianLaporan(String keyword) {
        getLaporanPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada laporan")
    public void adminMenekanTombolResetPadaLaporan() {
        getLaporanPage().clickReset();
    }

    @Maka("seluruh data aktivitas kehadiran kembali ditampilkan pada tabel")
    public void seluruhDataAktivitasKehadiranKembaliDitampilkanPadaTabel() {
        int rows = getLaporanPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter laporan")
    public void adminMembukaModalFilterLaporan() {
        getLaporanPage().clickFilterBtn();
    }

    @Maka("modal dialog filter laporan terbuka pada layar")
    public void modalDialogFilterLaporanTerbukaPadaLayar() {
        Assert.assertTrue(getLaporanPage().isFilterModalDisplayed(), "Modal filter laporan tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen laporan {string}")
    public void adminMemilihFilterDepartemenLaporan(String dept) {
        getLaporanPage().selectFilterDepartemen(dept);
    }

    @Dan("admin memilih filter status laporan {string}")
    public void adminMemilihFilterStatusLaporan(String status) {
        getLaporanPage().selectFilterStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter laporan")
    public void adminMenekanTombolTerapkanPadaModalFilterLaporan() {
        getLaporanPage().applyFilter();
    }

    @Ketika("admin mengklik thumbnail foto selfie pada baris ke-{int}")
    public void adminMengklikThumbnailFotoSelfiePadaBarisKe(int rowIdx) {
        getLaporanPage().clickPhotoOfRow(rowIdx - 1);
    }

    @Maka("modal dialog preview foto selfie terbuka")
    public void modalDialogPreviewFotoSelfieTerbuka() {
        Assert.assertTrue(getLaporanPage().isPhotoModalDisplayed(), "Modal foto selfie tidak terbuka!");
    }

    @Ketika("admin menutup modal preview foto selfie")
    public void adminMenutupModalPreviewFotoSelfie() {
        getLaporanPage().closePhotoModal();
    }

    @Ketika("admin mengklik tautan lokasi masuk pada baris ke-{int}")
    public void adminMengklikTautanLokasiMasukPadaBarisKe(int rowIdx) {
        getLaporanPage().clickLocationLinkOfRow(rowIdx - 1);
    }

    @Maka("modal dialog peta koordinat presisi terbuka")
    public void modalDialogPetaKoordinatPresisiTerbuka() {
        Assert.assertTrue(getLaporanPage().isMapModalDisplayed(), "Modal peta koordinat tidak terbuka!");
    }

    @Ketika("admin menutup modal peta koordinat")
    public void adminMenutupModalPetaKoordinat() {
        getLaporanPage().closeMapModal();
    }

    @Ketika("admin menekan tombol Export laporan")
    public void adminMenekanTombolExportLaporan() {
        getLaporanPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses laporan {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesLaporan(String expectedMsg) {
        Assert.assertTrue(getLaporanPage().isSnackbarVisible(), "Snackbar tidak muncul!");
        String actualMsg = getLaporanPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengubah jumlah baris per halaman laporan menjadi {string}")
    public void adminMengubahJumlahBarisPerHalamanLaporanMenjadi(String rowsValue) {
        getLaporanPage().selectRowsPerPage(rowsValue);
    }

    @Ketika("admin menekan tombol halaman berikutnya pada laporan")
    public void adminMenekanTombolHalamanBerikutnyaPadaLaporan() {
        getLaporanPage().clickNextPage();
    }

    @Maka("informasi paginasi laporan menampilkan {string}")
    public void informasiPaginasiLaporanMenampilkan(String expectedInfo) {
        String actualInfo = getLaporanPage().getPaginationInfoText();
        Assert.assertTrue(actualInfo.contains(expectedInfo),
                "Informasi paginasi tidak sesuai! Expected contains: '" + expectedInfo + "', Actual: '" + actualInfo + "'");
    }
}
