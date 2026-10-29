package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanOvertimePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanOvertimeSteps {
    private static final Logger logger = LogManager.getLogger(LaporanOvertimeSteps.class);
    private LaporanOvertimePage overtimePage;

    private LaporanOvertimePage getOvertimePage() {
        if (overtimePage == null) {
            overtimePage = new LaporanOvertimePage(DriverManager.getDriver());
        }
        return overtimePage;
    }

    @Dengan("admin membuka halaman laporan overtime")
    public void adminMembukaHalamanLaporanOvertime() {
        logger.info("Membuka halaman Laporan Overtime");
        getOvertimePage().open();
        String currentUrl = getOvertimePage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/overtime"),
                "URL halaman laporan overtime tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan overtime terlihat pada halaman")
    public void tabelDataLaporanOvertimeTerlihatPadaHalaman() {
        Assert.assertTrue(getOvertimePage().isLaporanTableDisplayed(), "Tabel laporan overtime tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter overtime terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterOvertimeTerlihat() {
        Assert.assertTrue(getOvertimePage().isFilterBarDisplayed(), "Filter bar overtime tidak lengkap!");
        Assert.assertTrue(getOvertimePage().isExportBtnDisplayed(), "Tombol export overtime tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai overtime {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiOvertimeDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal overtime: {} s/d {}", startDate, endDate);
        getOvertimePage().enterStartDate(startDate);
        getOvertimePage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada overtime")
    public void adminMenekanTombolSearchPadaOvertime() {
        getOvertimePage().clickSearch();
    }

    @Maka("tabel overtime menampilkan rekaman untuk karyawan {string}")
    public void tabelOvertimeMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getOvertimePage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel overtime!");
    }

    @Dan("tabel overtime menampilkan {int} baris data")
    public void tabelOvertimeMenampilkanBarisData(int expectedRows) {
        int actualRows = getOvertimePage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan overtime tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error overtime {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorOvertime(String expectedError) {
        Assert.assertTrue(getOvertimePage().isSnackbarVisible(), "Snackbar error overtime tidak tampil!");
        String actualMsg = getOvertimePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian overtime {string}")
    public void adminMemasukkanKataKunciPencarianOvertime(String keyword) {
        getOvertimePage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada overtime")
    public void adminMenekanTombolResetPadaOvertime() {
        getOvertimePage().clickReset();
    }

    @Maka("seluruh data overtime kembali ditampilkan pada tabel")
    public void seluruhDataOvertimeKembaliDitampilkanPadaTabel() {
        int rows = getOvertimePage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan overtime belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter overtime")
    public void adminMembukaModalFilterOvertime() {
        getOvertimePage().clickFilterBtn();
    }

    @Maka("modal dialog filter overtime terbuka pada layar")
    public void modalDialogFilterOvertimeTerbukaPadaLayar() {
        Assert.assertTrue(getOvertimePage().isFilterModalDisplayed(), "Modal filter overtime tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen overtime {string}")
    public void adminMemilihFilterDepartemenOvertime(String dept) {
        getOvertimePage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status lembur {string}")
    public void adminMemilihFilterStatusLembur(String status) {
        getOvertimePage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter overtime")
    public void adminMenekanTombolTerapkanPadaModalFilterOvertime() {
        getOvertimePage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail overtime pada baris ke-{int}")
    public void adminMengklikTombolDetailOvertimePadaBarisKe(int rowIndex) {
        getOvertimePage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail overtime terbuka pada layar")
    public void modalDialogDetailOvertimeTerbukaPadaLayar() {
        Assert.assertTrue(getOvertimePage().isDetailModalDisplayed(), "Modal detail overtime tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail lembur untuk {string}")
    public void modalMenampilkanJudulDetailLemburUntuk(String expectedName) {
        String title = getOvertimePage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail lembur tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail overtime")
    public void adminMenutupModalDetailOvertime() {
        getOvertimePage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris overtime menjadi {string} per halaman")
    public void adminMengaturJumlahBarisOvertimeMenjadiPerHalaman(String rows) {
        getOvertimePage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export overtime")
    public void adminMenekanTombolExportOvertime() {
        getOvertimePage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses overtime {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesOvertime(String expectedMsg) {
        Assert.assertTrue(getOvertimePage().isSnackbarVisible(), "Snackbar sukses export overtime tidak tampil!");
        String actualMsg = getOvertimePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    // ==========================================
    // Skenario Verifikasi Bug: Tombol Ubah Data & Modal Ubah Atasan (Live)
    // ==========================================

    @Ketika("admin mengklik tombol aksi ubah data pada baris pertama tabel overtime")
    public void adminMengklikTombolAksiUbahDataPadaBarisPertamaTabelOvertime() {
        logger.info("Mengklik tombol aksi baris pertama untuk membuka dialog ubah atasan");
        getOvertimePage().clickRowActionButton(1);
    }

    @Maka("sistem menampilkan modal dialog ubah atasan {string}")
    public void sistemMenampilkanModalDialogUbahAtasan(String expectedTitle) {
        Assert.assertTrue(getOvertimePage().isUbahAtasanDialogDisplayed(), "Modal dialog ubah atasan tidak tampil!");
        String actualTitle = getOvertimePage().getUbahAtasanDialogTitle();
        Assert.assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
                "Judul modal dialog tidak sesuai! Expected: '" + expectedTitle + "', Actual: '" + actualTitle + "'");
    }

    @Dan("modal memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin")
    public void modalMemuatKontrolDropdownPilihanAtasanYangDapatDiubahOlehAdmin() {
        int dropdownCount = getOvertimePage().getAtasanDropdownCount();
        logger.info("Jumlah kontrol input/dropdown atasan pada modal: {}", dropdownCount);
        // DEFECT ASSERTION:
        // Pada aplikasi live saat ini, modal hanya menampilkan teks '-' tanpa select/dropdown kontrol!
        // Kegagalan ini menangkap cacat formulir dan ketidakfungsian tombol Ubah Data secara nyata.
        Assert.assertTrue(dropdownCount > 0,
                "[DEFECT UI LIVE] Modal 'Ubah atasan user yang request' TIDAK menyediakan kontrol dropdown/select untuk memilih Atasan 2 atau Atasan 3! Nilai hanya teks statis '-' sehingga tombol 'Ubah Data' tidak dapat digunakan untuk memilih atau mengubah data atasan baru.");
    }

    @Ketika("admin menekan tombol Ubah Data pada modal ubah atasan")
    public void adminMenekanTombolUbahDataPadaModalUbahAtasan() {
        Assert.assertTrue(getOvertimePage().isUbahDataButtonDisplayed(), "Tombol 'Ubah Data' tidak ditemukan pada modal!");
        getOvertimePage().clickUbahDataButton();
    }
}


