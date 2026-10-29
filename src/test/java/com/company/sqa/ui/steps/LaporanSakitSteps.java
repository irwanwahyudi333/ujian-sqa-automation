package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanSakitPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanSakitSteps {
    private static final Logger logger = LogManager.getLogger(LaporanSakitSteps.class);
    private LaporanSakitPage sakitPage;

    private LaporanSakitPage getSakitPage() {
        if (sakitPage == null) {
            sakitPage = new LaporanSakitPage(DriverManager.getDriver());
        }
        return sakitPage;
    }

    @Dengan("admin membuka halaman laporan sakit")
    public void adminMembukaHalamanLaporanSakit() {
        logger.info("Membuka halaman Laporan Sakit");
        getSakitPage().open();
        String currentUrl = getSakitPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/sakit"),
                "URL halaman laporan sakit tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan sakit terlihat pada halaman")
    public void tabelDataLaporanSakitTerlihatPadaHalaman() {
        Assert.assertTrue(getSakitPage().isLaporanTableDisplayed(), "Tabel laporan sakit tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter sakit terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterSakitTerlihat() {
        Assert.assertTrue(getSakitPage().isFilterBarDisplayed(), "Filter bar sakit tidak lengkap!");
        Assert.assertTrue(getSakitPage().isExportBtnDisplayed(), "Tombol export sakit tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai sakit {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiSakitDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal sakit: {} s/d {}", startDate, endDate);
        getSakitPage().enterStartDate(startDate);
        getSakitPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada sakit")
    public void adminMenekanTombolSearchPadaSakit() {
        getSakitPage().clickSearch();
    }

    @Maka("tabel sakit menampilkan rekaman untuk karyawan {string}")
    public void tabelSakitMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getSakitPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel sakit!");
    }

    @Dan("tabel sakit menampilkan {int} baris data")
    public void tabelSakitMenampilkanBarisData(int expectedRows) {
        int actualRows = getSakitPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan sakit tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error sakit {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorSakit(String expectedError) {
        Assert.assertTrue(getSakitPage().isSnackbarVisible(), "Snackbar error sakit tidak tampil!");
        String actualMsg = getSakitPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian sakit {string}")
    public void adminMemasukkanKataKunciPencarianSakit(String keyword) {
        getSakitPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada sakit")
    public void adminMenekanTombolResetPadaSakit() {
        getSakitPage().clickReset();
    }

    @Maka("seluruh data sakit kembali ditampilkan pada tabel")
    public void seluruhDataSakitKembaliDitampilkanPadaTabel() {
        int rows = getSakitPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan sakit belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter sakit")
    public void adminMembukaModalFilterSakit() {
        getSakitPage().clickFilterBtn();
    }

    @Maka("modal dialog filter sakit terbuka pada layar")
    public void modalDialogFilterSakitTerbukaPadaLayar() {
        Assert.assertTrue(getSakitPage().isFilterModalDisplayed(), "Modal filter sakit tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen sakit {string}")
    public void adminMemilihFilterDepartemenSakit(String dept) {
        getSakitPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status verifikasi sakit {string}")
    public void adminMemilihFilterStatusVerifikasiSakit(String status) {
        getSakitPage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter sakit")
    public void adminMenekanTombolTerapkanPadaModalFilterSakit() {
        getSakitPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail sakit pada baris ke-{int}")
    public void adminMengklikTombolDetailSakitPadaBarisKe(int rowIndex) {
        getSakitPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail sakit terbuka pada layar")
    public void modalDialogDetailSakitTerbukaPadaLayar() {
        Assert.assertTrue(getSakitPage().isDetailModalDisplayed(), "Modal detail sakit tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail sakit untuk {string}")
    public void modalMenampilkanJudulDetailSakitUntuk(String expectedName) {
        String title = getSakitPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail sakit tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail sakit")
    public void adminMenutupModalDetailSakit() {
        getSakitPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris sakit menjadi {string} per halaman")
    public void adminMengaturJumlahBarisSakitMenjadiPerHalaman(String rows) {
        getSakitPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export sakit")
    public void adminMenekanTombolExportSakit() {
        getSakitPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses sakit {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesSakit(String expectedMsg) {
        Assert.assertTrue(getSakitPage().isSnackbarVisible(), "Snackbar sukses export sakit tidak tampil!");
        String actualMsg = getSakitPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    // ==========================================
    // Step Definitions Khusus Verifikasi Bug Gambar & Link Unduh (Live App)
    // ==========================================

    @Dan("seluruh gambar dan thumbnail bukti sakit berhasil dimuat dengan benar tanpa broken image")
    public void seluruhGambarDanThumbnailBuktiSakitBerhasilDimuatDenganBenarTanpaBrokenImage() {
        int brokenCount = getSakitPage().countBrokenImages();
        Assert.assertEquals(brokenCount, 0,
                "[DEFECT-SKT-001] Ditemukan " + brokenCount + " gambar/thumbnail bukti sakit atau avatar yang rusak (broken image / HTTP 404 / URL mengandung 'null')!");
    }

    @Dan("seluruh tautan unduh berkas bukti sakit mengarah ke URL berkas dokumen yang valid")
    public void seluruhTautanUnduhBerkasBuktiSakitMengarahKeURLBerkasDokumenYangValid() {
        int invalidCount = getSakitPage().countInvalidDownloadLinks();
        Assert.assertEquals(invalidCount, 0,
                "[DEFECT-SKT-002] Ditemukan " + invalidCount + " tombol Download berkas bukti sakit yang mengarah ke URL invalid '.../uploads/null' (HTTP 404)!");
    }
}

