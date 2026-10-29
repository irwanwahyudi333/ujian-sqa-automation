package com.company.sqa.ui.steps;

import com.company.sqa.pages.NewGeneratePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class NewGenerateSteps {
    private static final Logger logger = LogManager.getLogger(NewGenerateSteps.class);
    private NewGeneratePage newGeneratePage;

    private NewGeneratePage getNewGeneratePage() {
        if (newGeneratePage == null) {
            newGeneratePage = new NewGeneratePage(DriverManager.getDriver());
        }
        return newGeneratePage;
    }

    @Dengan("admin membuka halaman new generate")
    public void adminMembukaHalamanNewGenerate() {
        logger.info("Membuka halaman New Generate");
        getNewGeneratePage().open();
        String currentUrl = getNewGeneratePage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/new-generate"),
                "URL halaman new generate tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data riwayat template terlihat pada halaman")
    public void tabelDataRiwayatTemplateTerlihatPadaHalaman() {
        Assert.assertTrue(getNewGeneratePage().isLaporanTableDisplayed(), "Tabel riwayat template tidak tampil!");
    }

    @Dan("formulir dynamic builder template dan kontrol filter terlihat")
    public void formulirDynamicBuilderTemplateDanKontrolFilterTerlihat() {
        Assert.assertTrue(getNewGeneratePage().isBuilderFormDisplayed(), "Formulir builder template tidak tampil!");
        Assert.assertTrue(getNewGeneratePage().isFilterBarDisplayed(), "Filter bar tidak lengkap!");
    }

    @Ketika("admin memasukkan tanggal mulai riwayat template {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiRiwayatTemplateDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal riwayat template: {} s/d {}", startDate, endDate);
        getNewGeneratePage().enterStartDate(startDate);
        getNewGeneratePage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada riwayat template")
    public void adminMenekanTombolSearchPadaRiwayatTemplate() {
        getNewGeneratePage().clickSearch();
    }

    @Maka("tabel riwayat template menampilkan data dengan kode {string}")
    public void tabelRiwayatTemplateMenampilkanDataDenganKode(String expectedCode) {
        Assert.assertTrue(getNewGeneratePage().isTextPresentInRows(expectedCode),
                "Kode template '" + expectedCode + "' tidak ditemukan pada tabel riwayat!");
    }

    @Dan("tabel riwayat template menampilkan {int} baris data")
    public void tabelRiwayatTemplateMenampilkanBarisData(int expectedRows) {
        int actualRows = getNewGeneratePage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris riwayat template tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error riwayat template {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorRiwayatTemplate(String expectedError) {
        Assert.assertTrue(getNewGeneratePage().isSnackbarVisible(), "Snackbar error riwayat template tidak tampil!");
        String actualMsg = getNewGeneratePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian riwayat template {string}")
    public void adminMemasukkanKataKunciPencarianRiwayatTemplate(String keyword) {
        getNewGeneratePage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada riwayat template")
    public void adminMenekanTombolResetPadaRiwayatTemplate() {
        getNewGeneratePage().clickReset();
    }

    @Maka("seluruh data riwayat template kembali ditampilkan pada tabel")
    public void seluruhDataRiwayatTemplateKembaliDitampilkanPadaTabel() {
        int rows = getNewGeneratePage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data riwayat template belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter riwayat template")
    public void adminMembukaModalFilterRiwayatTemplate() {
        getNewGeneratePage().clickFilterBtn();
    }

    @Maka("modal dialog filter riwayat template terbuka pada layar")
    public void modalDialogFilterRiwayatTemplateTerbukaPadaLayar() {
        Assert.assertTrue(getNewGeneratePage().isFilterModalDisplayed(), "Modal filter riwayat template tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen riwayat template {string}")
    public void adminMemilihFilterDepartemenRiwayatTemplate(String dept) {
        getNewGeneratePage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter format riwayat template {string}")
    public void adminMemilihFilterFormatRiwayatTemplate(String formatVal) {
        getNewGeneratePage().selectFormat(formatVal);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter riwayat template")
    public void adminMenekanTombolTerapkanPadaModalFilterRiwayatTemplate() {
        getNewGeneratePage().clickApplyFilter();
    }

    @Ketika("admin memasukkan nama template laporan kustom {string}")
    public void adminMemasukkanNamaTemplateLaporanKustom(String name) {
        getNewGeneratePage().enterCustomReportName(name);
    }

    @Dan("admin menekan tombol Simpan & Generate Template")
    public void adminMenekanTombolSimpanGenerateTemplate() {
        getNewGeneratePage().clickCreateGenerate();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses template {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesTemplate(String expectedMsg) {
        Assert.assertTrue(getNewGeneratePage().isSnackbarVisible(), "Snackbar sukses template tidak tampil!");
        String actualMsg = getNewGeneratePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin menekan tombol Download template pada baris pertama")
    public void adminMenekanTombolDownloadTemplatePadaBarisPertama() {
        getNewGeneratePage().clickDownloadBtn(1);
    }

    @Maka("sistem menampilkan notifikasi snackbar info template {string}")
    public void sistemMenampilkanNotifikasiSnackbarInfoTemplate(String expectedMsg) {
        Assert.assertTrue(getNewGeneratePage().isSnackbarVisible(), "Snackbar info template tidak tampil!");
        String actualMsg = getNewGeneratePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan info snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengatur jumlah baris riwayat template menjadi {string} per halaman")
    public void adminMengaturJumlahBarisRiwayatTemplateMenjadiPerHalaman(String rows) {
        getNewGeneratePage().selectRowsPerPage(rows);
    }
}

