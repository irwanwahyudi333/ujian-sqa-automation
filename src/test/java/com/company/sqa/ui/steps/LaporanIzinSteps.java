package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanIzinPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanIzinSteps {
    private static final Logger logger = LogManager.getLogger(LaporanIzinSteps.class);
    private LaporanIzinPage izinPage;

    private LaporanIzinPage getIzinPage() {
        if (izinPage == null) {
            izinPage = new LaporanIzinPage(DriverManager.getDriver());
        }
        return izinPage;
    }

    @Dengan("admin membuka halaman laporan izin")
    public void adminMembukaHalamanLaporanIzin() {
        logger.info("Membuka halaman laporan izin");
        getIzinPage().open();
        String currentUrl = getIzinPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/izin"),
                "URL halaman laporan izin tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan izin terlihat pada halaman")
    public void tabelDataLaporanIzinTerlihatPadaHalaman() {
        Assert.assertTrue(getIzinPage().isLaporanTableDisplayed(), "Tabel laporan izin tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter izin terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterIzinTerlihat() {
        Assert.assertTrue(getIzinPage().isFilterBarDisplayed(), "Filter bar laporan izin tidak lengkap!");
        Assert.assertTrue(getIzinPage().isExportBtnDisplayed(), "Tombol export izin tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai izin {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiIzinDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal izin: {} s/d {}", startDate, endDate);
        getIzinPage().enterStartDate(startDate);
        getIzinPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada izin")
    public void adminMenekanTombolSearchPadaIzin() {
        getIzinPage().clickSearch();
    }

    @Maka("tabel izin menampilkan rekaman untuk karyawan {string}")
    public void tabelIzinMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getIzinPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel izin!");
    }

    @Dan("tabel izin menampilkan {int} baris data")
    public void tabelIzinMenampilkanBarisData(int expectedRows) {
        int actualRows = getIzinPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan izin tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error izin {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorIzin(String expectedMsg) {
        Assert.assertTrue(getIzinPage().isSnackbarVisible(), "Snackbar error izin tidak muncul!");
        String actualMsg = getIzinPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian izin {string}")
    public void adminMemasukkanKataKunciPencarianIzin(String keyword) {
        getIzinPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada izin")
    public void adminMenekanTombolResetPadaIzin() {
        getIzinPage().clickReset();
    }

    @Maka("seluruh data izin kembali ditampilkan pada tabel")
    public void seluruhDataIzinKembaliDitampilkanPadaTabel() {
        int rows = getIzinPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan izin belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter izin")
    public void adminMembukaModalFilterIzin() {
        getIzinPage().clickFilterBtn();
    }

    @Maka("modal dialog filter izin terbuka pada layar")
    public void modalDialogFilterIzinTerbukaPadaLayar() {
        Assert.assertTrue(getIzinPage().isFilterModalDisplayed(), "Modal filter izin tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen izin {string}")
    public void adminMemilihFilterDepartemenIzin(String dept) {
        getIzinPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe izin {string}")
    public void adminMemilihFilterTipeIzin(String type) {
        getIzinPage().selectTipeIzin(type);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter izin")
    public void adminMenekanTombolTerapkanPadaModalFilterIzin() {
        getIzinPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail izin pada baris ke-{int}")
    public void adminMengklikTombolDetailIzinPadaBarisKe(int rowIndex) {
        getIzinPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail izin terbuka pada layar")
    public void modalDialogDetailIzinTerbukaPadaLayar() {
        Assert.assertTrue(getIzinPage().isDetailModalDisplayed(), "Modal detail izin tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail izin untuk {string}")
    public void modalMenampilkanJudulDetailIzinUntuk(String expectedName) {
        String title = getIzinPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail tidak cocok! Expected contains: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail izin")
    public void adminMenutupModalDetailIzin() {
        getIzinPage().clickCloseDetail();
    }

    @Ketika("admin menekan tombol Export izin")
    public void adminMenekanTombolExportIzin() {
        getIzinPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses izin {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesIzin(String expectedMsg) {
        Assert.assertTrue(getIzinPage().isSnackbarVisible(), "Snackbar sukses tidak muncul!");
        String actualMsg = getIzinPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak cocok! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengubah jumlah baris per halaman izin menjadi {string}")
    public void adminMengubahJumlahBarisPerHalamanIzinMenjadi(String rows) {
        getIzinPage().selectRowsPerPage(rows);
    }

    @Dan("admin menekan tombol halaman berikutnya pada izin")
    public void adminMenekanTombolHalamanBerikutnyaPadaIzin() {
        getIzinPage().clickNextPage();
    }

    @Maka("informasi paginasi izin menampilkan {string}")
    public void informasiPaginasiIzinMenampilkan(String expectedInfo) {
        String info = getIzinPage().getPaginationInfo();
        Assert.assertTrue(info.contains(expectedInfo),
                "Informasi paginasi tidak sesuai! Expected contains: '" + expectedInfo + "', Actual: '" + info + "'");
    }
}

