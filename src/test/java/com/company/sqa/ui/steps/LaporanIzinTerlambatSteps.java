package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanIzinTerlambatPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanIzinTerlambatSteps {
    private static final Logger logger = LogManager.getLogger(LaporanIzinTerlambatSteps.class);
    private LaporanIzinTerlambatPage terlambatPage;

    private LaporanIzinTerlambatPage getTerlambatPage() {
        if (terlambatPage == null) {
            terlambatPage = new LaporanIzinTerlambatPage(DriverManager.getDriver());
        }
        return terlambatPage;
    }

    @Dengan("admin membuka halaman laporan izin terlambat")
    public void adminMembukaHalamanLaporanIzinTerlambat() {
        logger.info("Membuka halaman laporan izin terlambat");
        getTerlambatPage().open();
        String currentUrl = getTerlambatPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/izin-terlambat"),
                "URL halaman laporan izin terlambat tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan izin terlambat terlihat pada halaman")
    public void tabelDataLaporanIzinTerlambatTerlihatPadaHalaman() {
        Assert.assertTrue(getTerlambatPage().isLaporanTableDisplayed(), "Tabel laporan izin terlambat tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter izin terlambat terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterIzinTerlambatTerlihat() {
        Assert.assertTrue(getTerlambatPage().isFilterBarDisplayed(), "Filter bar laporan izin terlambat tidak lengkap!");
        Assert.assertTrue(getTerlambatPage().isExportBtnDisplayed(), "Tombol export izin terlambat tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai izin terlambat {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiIzinTerlambatDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal izin terlambat: {} s/d {}", startDate, endDate);
        getTerlambatPage().enterStartDate(startDate);
        getTerlambatPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada izin terlambat")
    public void adminMenekanTombolSearchPadaIzinTerlambat() {
        getTerlambatPage().clickSearch();
    }

    @Maka("tabel izin terlambat menampilkan rekaman untuk karyawan {string}")
    public void tabelIzinTerlambatMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getTerlambatPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel izin terlambat!");
    }

    @Dan("tabel izin terlambat menampilkan {int} baris data")
    public void tabelIzinTerlambatMenampilkanBarisData(int expectedRows) {
        int actualRows = getTerlambatPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan izin terlambat tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error izin terlambat {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorIzinTerlambat(String expectedMsg) {
        Assert.assertTrue(getTerlambatPage().isSnackbarVisible(), "Snackbar error izin terlambat tidak muncul!");
        String actualMsg = getTerlambatPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian izin terlambat {string}")
    public void adminMemasukkanKataKunciPencarianIzinTerlambat(String keyword) {
        getTerlambatPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada izin terlambat")
    public void adminMenekanTombolResetPadaIzinTerlambat() {
        getTerlambatPage().clickReset();
    }

    @Maka("seluruh data izin terlambat kembali ditampilkan pada tabel")
    public void seluruhDataIzinTerlambatKembaliDitampilkanPadaTabel() {
        int rows = getTerlambatPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan izin terlambat belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter izin terlambat")
    public void adminMembukaModalFilterIzinTerlambat() {
        getTerlambatPage().clickFilterBtn();
    }

    @Maka("modal dialog filter izin terlambat terbuka pada layar")
    public void modalDialogFilterIzinTerlambatTerbukaPadaLayar() {
        Assert.assertTrue(getTerlambatPage().isFilterModalDisplayed(), "Modal filter izin terlambat tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen izin terlambat {string}")
    public void adminMemilihFilterDepartemenIzinTerlambat(String dept) {
        getTerlambatPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status izin terlambat {string}")
    public void adminMemilihFilterStatusIzinTerlambat(String status) {
        getTerlambatPage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter izin terlambat")
    public void adminMenekanTombolTerapkanPadaModalFilterIzinTerlambat() {
        getTerlambatPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail izin terlambat pada baris ke-{int}")
    public void adminMengklikTombolDetailIzinTerlambatPadaBarisKe(int rowIndex) {
        getTerlambatPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail izin terlambat terbuka pada layar")
    public void modalDialogDetailIzinTerlambatTerbukaPadaLayar() {
        Assert.assertTrue(getTerlambatPage().isDetailModalDisplayed(), "Modal detail izin terlambat tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail keterlambatan untuk {string}")
    public void modalMenampilkanJudulDetailKeterlambatanUntuk(String expectedName) {
        String title = getTerlambatPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail tidak cocok! Expected contains: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail izin terlambat")
    public void adminMenutupModalDetailIzinTerlambat() {
        getTerlambatPage().clickCloseDetail();
    }

    @Ketika("admin menekan tombol Export izin terlambat")
    public void adminMenekanTombolExportIzinTerlambat() {
        getTerlambatPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses izin terlambat {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesIzinTerlambat(String expectedMsg) {
        Assert.assertTrue(getTerlambatPage().isSnackbarVisible(), "Snackbar sukses tidak muncul!");
        String actualMsg = getTerlambatPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak cocok! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengubah jumlah baris per halaman izin terlambat menjadi {string}")
    public void adminMengubahJumlahBarisPerHalamanIzinTerlambatMenjadi(String rows) {
        getTerlambatPage().selectRowsPerPage(rows);
    }

    @Dan("admin menekan tombol halaman berikutnya pada izin terlambat")
    public void adminMenekanTombolHalamanBerikutnyaPadaIzinTerlambat() {
        getTerlambatPage().clickNextPage();
    }

    @Maka("informasi paginasi izin terlambat menampilkan {string}")
    public void informasiPaginasiIzinTerlambatMenampilkan(String expectedInfo) {
        String info = getTerlambatPage().getPaginationInfo();
        Assert.assertTrue(info.contains(expectedInfo),
                "Informasi paginasi tidak sesuai! Expected contains: '" + expectedInfo + "', Actual: '" + info + "'");
    }
}

