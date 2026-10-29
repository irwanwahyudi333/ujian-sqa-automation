package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanIzinOffPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanIzinOffSteps {
    private static final Logger logger = LogManager.getLogger(LaporanIzinOffSteps.class);
    private LaporanIzinOffPage izinOffPage;

    private LaporanIzinOffPage getIzinOffPage() {
        if (izinOffPage == null) {
            izinOffPage = new LaporanIzinOffPage(DriverManager.getDriver());
        }
        return izinOffPage;
    }

    @Dengan("admin membuka halaman laporan izin off")
    public void adminMembukaHalamanLaporanIzinOff() {
        logger.info("Membuka halaman Laporan Izin Off");
        getIzinOffPage().open();
        String currentUrl = getIzinOffPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/izin-off"),
                "URL halaman laporan izin off tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan izin off terlihat pada halaman")
    public void tabelDataLaporanIzinOffTerlihatPadaHalaman() {
        Assert.assertTrue(getIzinOffPage().isLaporanTableDisplayed(), "Tabel laporan izin off tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter izin off terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterIzinOffTerlihat() {
        Assert.assertTrue(getIzinOffPage().isFilterBarDisplayed(), "Filter bar izin off tidak lengkap!");
        Assert.assertTrue(getIzinOffPage().isExportBtnDisplayed(), "Tombol export izin off tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai izin off {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiIzinOffDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal izin off: {} s/d {}", startDate, endDate);
        getIzinOffPage().enterStartDate(startDate);
        getIzinOffPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada izin off")
    public void adminMenekanTombolSearchPadaIzinOff() {
        getIzinOffPage().clickSearch();
    }

    @Maka("tabel izin off menampilkan rekaman untuk karyawan {string}")
    public void tabelIzinOffMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getIzinOffPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel izin off!");
    }

    @Dan("tabel izin off menampilkan {int} baris data")
    public void tabelIzinOffMenampilkanBarisData(int expectedRows) {
        int actualRows = getIzinOffPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan izin off tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error izin off {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorIzinOff(String expectedError) {
        Assert.assertTrue(getIzinOffPage().isSnackbarVisible(), "Snackbar error izin off tidak tampil!");
        String actualMsg = getIzinOffPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian izin off {string}")
    public void adminMemasukkanKataKunciPencarianIzinOff(String keyword) {
        getIzinOffPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada izin off")
    public void adminMenekanTombolResetPadaIzinOff() {
        getIzinOffPage().clickReset();
    }

    @Maka("seluruh data izin off kembali ditampilkan pada tabel")
    public void seluruhDataIzinOffKembaliDitampilkanPadaTabel() {
        int rows = getIzinOffPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan izin off belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter izin off")
    public void adminMembukaModalFilterIzinOff() {
        getIzinOffPage().clickFilterBtn();
    }

    @Maka("modal dialog filter izin off terbuka pada layar")
    public void modalDialogFilterIzinOffTerbukaPadaLayar() {
        Assert.assertTrue(getIzinOffPage().isFilterModalDisplayed(), "Modal filter izin off tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen izin off {string}")
    public void adminMemilihFilterDepartemenIzinOff(String dept) {
        getIzinOffPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status persetujuan izin off {string}")
    public void adminMemilihFilterStatusPersetujuanIzinOff(String status) {
        getIzinOffPage().selectStatusOff(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter izin off")
    public void adminMenekanTombolTerapkanPadaModalFilterIzinOff() {
        getIzinOffPage().clickApplyFilter();
    }

    @Ketika("admin menekan tombol Approve izin off pada baris pertama")
    public void adminMenekanTombolApproveIzinOffPadaBarisPertama() {
        getIzinOffPage().clickApproveBtn(1);
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses izin off {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesIzinOff(String expectedMsg) {
        Assert.assertTrue(getIzinOffPage().isSnackbarVisible(), "Snackbar sukses izin off tidak tampil!");
        String actualMsg = getIzinOffPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar izin off tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengatur jumlah baris izin off menjadi {string} per halaman")
    public void adminMengaturJumlahBarisIzinOffMenjadiPerHalaman(String rows) {
        getIzinOffPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export izin off")
    public void adminMenekanTombolExportIzinOff() {
        getIzinOffPage().clickExport();
    }

    // ==========================================
    // Step Definitions Khusus Verifikasi Bug Menu Aksi (Live App)
    // ==========================================

    @Ketika("admin mengklik tombol aksi pada baris pertama tabel izin off")
    public void adminMengklikTombolAksiPadaBarisPertamaTabelIzinOff() {
        getIzinOffPage().clickFirstRowActionBtn();
    }

    @Maka("menu popover aksi tidak memuat label copy-paste {string} atau {string}")
    public void menuPopoverAksiTidakMemuatLabelCopyPaste(String forbidden1, String forbidden2) {
        java.util.List<String> menuItems = getIzinOffPage().getActionMenuTexts();
        boolean hasForbiddenLabel = false;
        for (String item : menuItems) {
            if (item.contains(forbidden1) || item.contains(forbidden2)) {
                hasForbiddenLabel = true;
                break;
            }
        }
        Assert.assertFalse(hasForbiddenLabel,
                "[DEFECT-OFF-001] Menu aksi pada Laporan Izin Off salah label (copy-paste dari modul Koreksi)! Ditemukan menu: " + menuItems);
    }
}

