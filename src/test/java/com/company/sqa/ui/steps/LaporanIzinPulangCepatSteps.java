package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanIzinPulangCepatPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanIzinPulangCepatSteps {
    private static final Logger logger = LogManager.getLogger(LaporanIzinPulangCepatSteps.class);
    private LaporanIzinPulangCepatPage pulangCepatPage;

    private LaporanIzinPulangCepatPage getPulangCepatPage() {
        if (pulangCepatPage == null) {
            pulangCepatPage = new LaporanIzinPulangCepatPage(DriverManager.getDriver());
        }
        return pulangCepatPage;
    }

    @Dengan("admin membuka halaman laporan izin pulang cepat")
    public void adminMembukaHalamanLaporanIzinPulangCepat() {
        logger.info("Membuka halaman Laporan Izin Pulang Cepat");
        getPulangCepatPage().open();
        String currentUrl = getPulangCepatPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/izin-pulang-cepat"),
                "URL halaman laporan izin pulang cepat tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan izin pulang cepat terlihat pada halaman")
    public void tabelDataLaporanIzinPulangCepatTerlihatPadaHalaman() {
        Assert.assertTrue(getPulangCepatPage().isLaporanTableDisplayed(), "Tabel laporan izin pulang cepat tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter izin pulang cepat terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterIzinPulangCepatTerlihat() {
        Assert.assertTrue(getPulangCepatPage().isFilterBarDisplayed(), "Filter bar izin pulang cepat tidak lengkap!");
        Assert.assertTrue(getPulangCepatPage().isExportBtnDisplayed(), "Tombol export izin pulang cepat tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai izin pulang cepat {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiIzinPulangCepatDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal izin pulang cepat: {} s/d {}", startDate, endDate);
        getPulangCepatPage().enterStartDate(startDate);
        getPulangCepatPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada izin pulang cepat")
    public void adminMenekanTombolSearchPadaIzinPulangCepat() {
        getPulangCepatPage().clickSearch();
    }

    @Maka("tabel izin pulang cepat menampilkan rekaman untuk karyawan {string}")
    public void tabelIzinPulangCepatMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getPulangCepatPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel izin pulang cepat!");
    }

    @Dan("tabel izin pulang cepat menampilkan {int} baris data")
    public void tabelIzinPulangCepatMenampilkanBarisData(int expectedRows) {
        int actualRows = getPulangCepatPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan izin pulang cepat tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error izin pulang cepat {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorIzinPulangCepat(String expectedError) {
        Assert.assertTrue(getPulangCepatPage().isSnackbarVisible(), "Snackbar error izin pulang cepat tidak tampil!");
        String actualMsg = getPulangCepatPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian izin pulang cepat {string}")
    public void adminMemasukkanKataKunciPencarianIzinPulangCepat(String keyword) {
        getPulangCepatPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada izin pulang cepat")
    public void adminMenekanTombolResetPadaIzinPulangCepat() {
        getPulangCepatPage().clickReset();
    }

    @Maka("seluruh data izin pulang cepat kembali ditampilkan pada tabel")
    public void seluruhDataIzinPulangCepatKembaliDitampilkanPadaTabel() {
        int rows = getPulangCepatPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan izin pulang cepat belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter izin pulang cepat")
    public void adminMembukaModalFilterIzinPulangCepat() {
        getPulangCepatPage().clickFilterBtn();
    }

    @Maka("modal dialog filter izin pulang cepat terbuka pada layar")
    public void modalDialogFilterIzinPulangCepatTerbukaPadaLayar() {
        Assert.assertTrue(getPulangCepatPage().isFilterModalDisplayed(), "Modal filter izin pulang cepat tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen izin pulang cepat {string}")
    public void adminMemilihFilterDepartemenIzinPulangCepat(String dept) {
        getPulangCepatPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status izin pulang cepat {string}")
    public void adminMemilihFilterStatusIzinPulangCepat(String status) {
        getPulangCepatPage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter izin pulang cepat")
    public void adminMenekanTombolTerapkanPadaModalFilterIzinPulangCepat() {
        getPulangCepatPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail izin pulang cepat pada baris ke-{int}")
    public void adminMengklikTombolDetailIzinPulangCepatPadaBarisKe(int rowIndex) {
        getPulangCepatPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail izin pulang cepat terbuka pada layar")
    public void modalDialogDetailIzinPulangCepatTerbukaPadaLayar() {
        Assert.assertTrue(getPulangCepatPage().isDetailModalDisplayed(), "Modal detail izin pulang cepat tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail pulang cepat untuk {string}")
    public void modalMenampilkanJudulDetailPulangCepatUntuk(String expectedName) {
        String title = getPulangCepatPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail izin pulang cepat")
    public void adminMenutupModalDetailIzinPulangCepat() {
        getPulangCepatPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris izin pulang cepat menjadi {string} per halaman")
    public void adminMengaturJumlahBarisIzinPulangCepatMenjadiPerHalaman(String rows) {
        getPulangCepatPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export izin pulang cepat")
    public void adminMenekanTombolExportIzinPulangCepat() {
        getPulangCepatPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses izin pulang cepat {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesIzinPulangCepat(String expectedMsg) {
        Assert.assertTrue(getPulangCepatPage().isSnackbarVisible(), "Snackbar sukses export tidak tampil!");
        String actualMsg = getPulangCepatPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }
}
