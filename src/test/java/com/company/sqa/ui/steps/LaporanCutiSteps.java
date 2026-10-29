package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanCutiPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanCutiSteps {
    private static final Logger logger = LogManager.getLogger(LaporanCutiSteps.class);
    private LaporanCutiPage cutiPage;

    private LaporanCutiPage getCutiPage() {
        if (cutiPage == null) {
            cutiPage = new LaporanCutiPage(DriverManager.getDriver());
        }
        return cutiPage;
    }

    @Dengan("admin membuka halaman laporan cuti")
    public void adminMembukaHalamanLaporanCuti() {
        logger.info("Membuka halaman Laporan Cuti");
        getCutiPage().open();
        String currentUrl = getCutiPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/cuti"),
                "URL halaman laporan cuti tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan cuti terlihat pada halaman")
    public void tabelDataLaporanCutiTerlihatPadaHalaman() {
        Assert.assertTrue(getCutiPage().isLaporanTableDisplayed(), "Tabel laporan cuti tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter cuti terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterCutiTerlihat() {
        Assert.assertTrue(getCutiPage().isFilterBarDisplayed(), "Filter bar cuti tidak lengkap!");
        Assert.assertTrue(getCutiPage().isExportBtnDisplayed(), "Tombol export cuti tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai cuti {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiCutiDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal cuti: {} s/d {}", startDate, endDate);
        getCutiPage().enterStartDate(startDate);
        getCutiPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada cuti")
    public void adminMenekanTombolSearchPadaCuti() {
        getCutiPage().clickSearch();
    }

    @Maka("tabel cuti menampilkan rekaman untuk karyawan {string}")
    public void tabelCutiMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getCutiPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel cuti!");
    }

    @Dan("tabel cuti menampilkan {int} baris data")
    public void tabelCutiMenampilkanBarisData(int expectedRows) {
        int actualRows = getCutiPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan cuti tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error cuti {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorCuti(String expectedError) {
        Assert.assertTrue(getCutiPage().isSnackbarVisible(), "Snackbar error cuti tidak tampil!");
        String actualMsg = getCutiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian cuti {string}")
    public void adminMemasukkanKataKunciPencarianCuti(String keyword) {
        getCutiPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada cuti")
    public void adminMenekanTombolResetPadaCuti() {
        getCutiPage().clickReset();
    }

    @Maka("seluruh data cuti kembali ditampilkan pada tabel")
    public void seluruhDataCutiKembaliDitampilkanPadaTabel() {
        int rows = getCutiPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan cuti belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter cuti")
    public void adminMembukaModalFilterCuti() {
        getCutiPage().clickFilterBtn();
    }

    @Maka("modal dialog filter cuti terbuka pada layar")
    public void modalDialogFilterCutiTerbukaPadaLayar() {
        Assert.assertTrue(getCutiPage().isFilterModalDisplayed(), "Modal filter cuti tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen cuti {string}")
    public void adminMemilihFilterDepartemenCuti(String dept) {
        getCutiPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe cuti {string}")
    public void adminMemilihFilterTipeCuti(String tipeCuti) {
        getCutiPage().selectTipeCuti(tipeCuti);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter cuti")
    public void adminMenekanTombolTerapkanPadaModalFilterCuti() {
        getCutiPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail cuti pada baris ke-{int}")
    public void adminMengklikTombolDetailCutiPadaBarisKe(int rowIndex) {
        getCutiPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail cuti terbuka pada layar")
    public void modalDialogDetailCutiTerbukaPadaLayar() {
        Assert.assertTrue(getCutiPage().isDetailModalDisplayed(), "Modal detail cuti tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail cuti untuk {string}")
    public void modalMenampilkanJudulDetailCutiUntuk(String expectedName) {
        String title = getCutiPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail cuti tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail cuti")
    public void adminMenutupModalDetailCuti() {
        getCutiPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris cuti menjadi {string} per halaman")
    public void adminMengaturJumlahBarisCutiMenjadiPerHalaman(String rows) {
        getCutiPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export cuti")
    public void adminMenekanTombolExportCuti() {
        getCutiPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses cuti {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesCuti(String expectedMsg) {
        Assert.assertTrue(getCutiPage().isSnackbarVisible(), "Snackbar sukses export cuti tidak tampil!");
        String actualMsg = getCutiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    // ==========================================
    // Skenario Verifikasi Bug: Tombol Ubah Data & Modal Ubah Atasan (Live Cuti)
    // ==========================================

    @Ketika("admin membuka menu aksi baris pertama dan memilih Edit pada cuti")
    public void adminMembukaMenuAksiBarisPertamaDanMemilihEditPadaCuti() {
        logger.info("Membuka menu aksi baris pertama pada tabel cuti live");
        getCutiPage().openLiveActionMenu(1);
        getCutiPage().clickEditMenu();
    }

    @Maka("sistem menampilkan modal dialog ubah atasan cuti {string}")
    public void sistemMenampilkanModalDialogUbahAtasanCuti(String expectedTitle) {
        Assert.assertTrue(getCutiPage().isUbahAtasanDialogDisplayed(), "Modal dialog ubah atasan cuti tidak tampil!");
        String actualTitle = getCutiPage().getUbahAtasanDialogTitle();
        Assert.assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
                "Judul modal dialog tidak sesuai! Expected: '" + expectedTitle + "', Actual: '" + actualTitle + "'");
    }

    @Dan("modal cuti memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin")
    public void modalCutiMemuatKontrolDropdownPilihanAtasanYangDapatDiubahOlehAdmin() {
        int dropdownCount = getCutiPage().getAtasanDropdownCount();
        logger.info("Jumlah kontrol input/dropdown atasan pada modal cuti: {}", dropdownCount);
        // DEFECT ASSERTION:
        // Modal live pada Laporan Cuti juga hanya menampilkan teks '-' tanpa dropdown!
        Assert.assertTrue(dropdownCount > 0,
                "[DEFECT UI LIVE] Modal 'Ubah atasan user yang request' pada Laporan Cuti TIDAK menyediakan kontrol dropdown/select untuk memilih Atasan 2 atau Atasan 3! Nilai hanya teks statis '-' sehingga tombol 'Ubah Data' tidak dapat digunakan untuk memilih atau mengubah data atasan cuti baru.");
    }

    @Ketika("admin menekan tombol Ubah Data pada modal cuti")
    public void adminMenekanTombolUbahDataPadaModalCuti() {
        Assert.assertTrue(getCutiPage().isUbahDataButtonDisplayed(), "Tombol 'Ubah Data' tidak ditemukan pada modal cuti!");
        getCutiPage().clickUbahDataButton();
    }
}


