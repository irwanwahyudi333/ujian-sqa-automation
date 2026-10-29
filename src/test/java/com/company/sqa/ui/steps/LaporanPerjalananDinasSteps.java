package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanPerjalananDinasPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanPerjalananDinasSteps {
    private static final Logger logger = LogManager.getLogger(LaporanPerjalananDinasSteps.class);
    private LaporanPerjalananDinasPage dinasPage;

    private LaporanPerjalananDinasPage getDinasPage() {
        if (dinasPage == null) {
            dinasPage = new LaporanPerjalananDinasPage(DriverManager.getDriver());
        }
        return dinasPage;
    }

    @Dengan("admin membuka halaman laporan perjalanan dinas")
    public void adminMembukaHalamanLaporanPerjalananDinas() {
        logger.info("Membuka halaman Laporan Perjalanan Dinas");
        getDinasPage().open();
        String currentUrl = getDinasPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/perjalanan-dinas"),
                "URL halaman laporan perjalanan dinas tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan perjalanan dinas terlihat pada halaman")
    public void tabelDataLaporanPerjalananDinasTerlihatPadaHalaman() {
        Assert.assertTrue(getDinasPage().isLaporanTableDisplayed(), "Tabel laporan perjalanan dinas tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter perjalanan dinas terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterPerjalananDinasTerlihat() {
        Assert.assertTrue(getDinasPage().isFilterBarDisplayed(), "Filter bar perjalanan dinas tidak lengkap!");
        Assert.assertTrue(getDinasPage().isExportBtnDisplayed(), "Tombol export perjalanan dinas tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai perjalanan dinas {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiPerjalananDinasDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal perjalanan dinas: {} s/d {}", startDate, endDate);
        getDinasPage().enterStartDate(startDate);
        getDinasPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada perjalanan dinas")
    public void adminMenekanTombolSearchPadaPerjalananDinas() {
        getDinasPage().clickSearch();
    }

    @Maka("tabel perjalanan dinas menampilkan rekaman untuk karyawan {string}")
    public void tabelPerjalananDinasMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getDinasPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel perjalanan dinas!");
    }

    @Dan("tabel perjalanan dinas menampilkan {int} baris data")
    public void tabelPerjalananDinasMenampilkanBarisData(int expectedRows) {
        int actualRows = getDinasPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan perjalanan dinas tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error perjalanan dinas {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorPerjalananDinas(String expectedError) {
        Assert.assertTrue(getDinasPage().isSnackbarVisible(), "Snackbar error perjalanan dinas tidak tampil!");
        String actualMsg = getDinasPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian perjalanan dinas {string}")
    public void adminMemasukkanKataKunciPencarianPerjalananDinas(String keyword) {
        getDinasPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada perjalanan dinas")
    public void adminMenekanTombolResetPadaPerjalananDinas() {
        getDinasPage().clickReset();
    }

    @Maka("seluruh data perjalanan dinas kembali ditampilkan pada tabel")
    public void seluruhDataPerjalananDinasKembaliDitampilkanPadaTabel() {
        int rows = getDinasPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan perjalanan dinas belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter perjalanan dinas")
    public void adminMembukaModalFilterPerjalananDinas() {
        getDinasPage().clickFilterBtn();
    }

    @Maka("modal dialog filter perjalanan dinas terbuka pada layar")
    public void modalDialogFilterPerjalananDinasTerbukaPadaLayar() {
        Assert.assertTrue(getDinasPage().isFilterModalDisplayed(), "Modal filter perjalanan dinas tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen perjalanan dinas {string}")
    public void adminMemilihFilterDepartemenPerjalananDinas(String dept) {
        getDinasPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter status approval dinas {string}")
    public void adminMemilihFilterStatusApprovalDinas(String status) {
        getDinasPage().selectStatus(status);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter perjalanan dinas")
    public void adminMenekanTombolTerapkanPadaModalFilterPerjalananDinas() {
        getDinasPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail perjalanan dinas pada baris ke-{int}")
    public void adminMengklikTombolDetailPerjalananDinasPadaBarisKe(int rowIndex) {
        getDinasPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail perjalanan dinas terbuka pada layar")
    public void modalDialogDetailPerjalananDinasTerbukaPadaLayar() {
        Assert.assertTrue(getDinasPage().isDetailModalDisplayed(), "Modal detail perjalanan dinas tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail perjalanan dinas untuk {string}")
    public void modalMenampilkanJudulDetailPerjalananDinasUntuk(String expectedName) {
        String title = getDinasPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail dinas tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail perjalanan dinas")
    public void adminMenutupModalDetailPerjalananDinas() {
        getDinasPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris perjalanan dinas menjadi {string} per halaman")
    public void adminMengaturJumlahBarisPerjalananDinasMenjadiPerHalaman(String rows) {
        getDinasPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export perjalanan dinas")
    public void adminMenekanTombolExportPerjalananDinas() {
        getDinasPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses perjalanan dinas {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesPerjalananDinas(String expectedMsg) {
        Assert.assertTrue(getDinasPage().isSnackbarVisible(), "Snackbar sukses export perjalanan dinas tidak tampil!");
        String actualMsg = getDinasPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    // ==========================================
    // Step Definitions Khusus Verifikasi Bug Approval & Reject (Live App)
    // ==========================================

    @Ketika("admin mengklik tombol aksi kebab pada baris pertama perjalanan dinas")
    public void adminMengklikTombolAksiKebabPadaBarisPertamaPerjalananDinas() {
        getDinasPage().clickFirstRowKebabAction();
    }

    @Dan("admin memilih opsi Approval Perjalanan Dinas pada menu aksi")
    public void adminMemilihOpsiApprovalPerjalananDinasPadaMenuAksi() {
        getDinasPage().clickApprovalPerjalananDinasMenu();
    }

    @Dan("admin memilih opsi Reject Perjalanan Dinas pada menu aksi")
    public void adminMemilihOpsiRejectPerjalananDinasPadaMenuAksi() {
        getDinasPage().clickRejectPerjalananDinasMenu();
    }

    @Maka("sistem menampilkan dialog konfirmasi persetujuan {string}")
    public void sistemMenampilkanDialogKonfirmasiPersetujuan(String expectedTitle) {
        Assert.assertTrue(getDinasPage().isConfirmDialogDisplayed(), "Dialog konfirmasi approval perjalanan dinas tidak tampil!");
        String actualText = getDinasPage().getConfirmDialogText();
        Assert.assertTrue(actualText.contains(expectedTitle),
                "Judul dialog konfirmasi tidak sesuai! Expected: '" + expectedTitle + "', Actual: '" + actualText + "'");
    }

    @Maka("sistem menampilkan dialog konfirmasi penolakan {string}")
    public void sistemMenampilkanDialogKonfirmasiPenolakan(String expectedTitle) {
        Assert.assertTrue(getDinasPage().isConfirmDialogDisplayed(), "Dialog konfirmasi reject perjalanan dinas tidak tampil!");
        String actualText = getDinasPage().getConfirmDialogText();
        Assert.assertTrue(actualText.contains(expectedTitle),
                "Judul dialog konfirmasi tidak sesuai! Expected: '" + expectedTitle + "', Actual: '" + actualText + "'");
    }

    @Ketika("admin menekan tombol Ya pada dialog konfirmasi perjalanan dinas")
    public void adminMenekanTombolYaPadaDialogKonfirmasiPerjalananDinas() {
        getDinasPage().clickConfirmYaButton();
    }

    @Maka("sistem memproses persetujuan dinas dan menutup dialog konfirmasi")
    public void sistemMemprosesPersetujuanDinasDanMenutupDialogKonfirmasi() {
        boolean isStillOpen = getDinasPage().isConfirmDialogStillOpen();
        Assert.assertFalse(isStillOpen,
                "[DEFECT-DNS-001] Tombol 'Ya' pada modal Approval Perjalanan Dinas tidak berfungsi (Dead Button)! Modal tetap terbuka tanpa ada aksi.");
    }

    @Maka("sistem memproses penolakan dinas dan menutup dialog konfirmasi")
    public void sistemMemprosesPenolakanDinasDanMenutupDialogKonfirmasi() {
        boolean isStillOpen = getDinasPage().isConfirmDialogStillOpen();
        Assert.assertFalse(isStillOpen,
                "[DEFECT-DNS-002] Tombol 'Ya' pada modal Reject Perjalanan Dinas tidak berfungsi (Dead Button)! Modal tetap terbuka tanpa ada aksi.");
    }
}

