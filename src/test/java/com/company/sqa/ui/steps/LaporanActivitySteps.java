package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanActivityPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanActivitySteps {
    private static final Logger logger = LogManager.getLogger(LaporanActivitySteps.class);
    private LaporanActivityPage activityPage;

    private LaporanActivityPage getActivityPage() {
        if (activityPage == null) {
            activityPage = new LaporanActivityPage(DriverManager.getDriver());
        }
        return activityPage;
    }

    @Dengan("admin membuka halaman laporan activity")
    public void adminMembukaHalamanLaporanActivity() {
        logger.info("Membuka halaman laporan activity");
        getActivityPage().open();
        String currentUrl = getActivityPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/activity"),
                "URL halaman laporan activity tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan activity terlihat pada halaman")
    public void tabelDataLaporanActivityTerlihatPadaHalaman() {
        Assert.assertTrue(getActivityPage().isLaporanTableDisplayed(), "Tabel laporan activity tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter activity terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterActivityTerlihat() {
        Assert.assertTrue(getActivityPage().isFilterBarDisplayed(), "Filter bar laporan activity tidak lengkap!");
        Assert.assertTrue(getActivityPage().isExportBtnDisplayed(), "Tombol export activity tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai activity {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiActivityDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal activity: {} s/d {}", startDate, endDate);
        getActivityPage().enterStartDate(startDate);
        getActivityPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada activity")
    public void adminMenekanTombolSearchPadaActivity() {
        getActivityPage().clickSearch();
    }

    @Maka("tabel activity menampilkan rekaman untuk karyawan {string}")
    public void tabelActivityMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getActivityPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel activity!");
    }

    @Dan("tabel activity menampilkan {int} baris data")
    public void tabelActivityMenampilkanBarisData(int expectedRows) {
        int actualRows = getActivityPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris activity tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error activity {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorActivity(String expectedMsg) {
        Assert.assertTrue(getActivityPage().isSnackbarVisible(), "Snackbar error activity tidak muncul!");
        String actualMsg = getActivityPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian activity {string}")
    public void adminMemasukkanKataKunciPencarianActivity(String keyword) {
        getActivityPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada activity")
    public void adminMenekanTombolResetPadaActivity() {
        getActivityPage().clickReset();
    }

    @Maka("seluruh data aktivitas kembali ditampilkan pada tabel")
    public void seluruhDataAktivitasKembaliDitampilkanPadaTabel() {
        int rows = getActivityPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan activity belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter activity")
    public void adminMembukaModalFilterActivity() {
        getActivityPage().clickFilterBtn();
    }

    @Maka("modal dialog filter activity terbuka pada layar")
    public void modalDialogFilterActivityTerbukaPadaLayar() {
        Assert.assertTrue(getActivityPage().isFilterModalDisplayed(), "Modal filter activity tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen activity {string}")
    public void adminMemilihFilterDepartemenActivity(String dept) {
        getActivityPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe aktivitas {string}")
    public void adminMemilihFilterTipeAktivitas(String type) {
        getActivityPage().selectTipeAktivitas(type);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter activity")
    public void adminMenekanTombolTerapkanPadaModalFilterActivity() {
        getActivityPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail activity pada baris ke-{int}")
    public void adminMengklikTombolDetailActivityPadaBarisKe(int rowIndex) {
        getActivityPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail activity terbuka pada layar")
    public void modalDialogDetailActivityTerbukaPadaLayar() {
        Assert.assertTrue(getActivityPage().isDetailModalDisplayed(), "Modal detail activity tidak terbuka!");
    }

    @Dan("modal menampilkan nama karyawan {string} dan tipe aktivitas {string}")
    public void modalMenampilkanNamaKaryawanDanTipeAktivitas(String expectedName, String expectedType) {
        String employee = getActivityPage().getDetailEmployee();
        String type = getActivityPage().getDetailType();
        Assert.assertTrue(employee.contains(expectedName), "Nama karyawan di modal detail tidak cocok! Aktual: " + employee);
        Assert.assertTrue(type.contains(expectedType), "Tipe aktivitas di modal detail tidak cocok! Aktual: " + type);
    }

    @Ketika("admin menutup modal detail activity")
    public void adminMenutupModalDetailActivity() {
        getActivityPage().clickCloseDetail();
    }

    @Ketika("admin menekan tombol Export activity")
    public void adminMenekanTombolExportActivity() {
        getActivityPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses activity {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesActivity(String expectedMsg) {
        Assert.assertTrue(getActivityPage().isSnackbarVisible(), "Snackbar sukses tidak muncul!");
        String actualMsg = getActivityPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak cocok! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengubah jumlah baris per halaman activity menjadi {string}")
    public void adminMengubahJumlahBarisPerHalamanActivityMenjadi(String rows) {
        getActivityPage().selectRowsPerPage(rows);
    }

    @Dan("admin menekan tombol halaman berikutnya pada activity")
    public void adminMenekanTombolHalamanBerikutnyaPadaActivity() {
        getActivityPage().clickNextPage();
    }

    @Maka("informasi paginasi activity menampilkan {string}")
    public void informasiPaginasiActivityMenampilkan(String expectedInfo) {
        String info = getActivityPage().getPaginationInfo();
        Assert.assertTrue(info.contains(expectedInfo),
                "Informasi paginasi tidak sesuai! Expected contains: '" + expectedInfo + "', Actual: '" + info + "'");
    }
}

