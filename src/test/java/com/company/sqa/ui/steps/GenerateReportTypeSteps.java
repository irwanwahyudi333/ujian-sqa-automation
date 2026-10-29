package com.company.sqa.ui.steps;

import com.company.sqa.pages.GenerateReportTypePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class GenerateReportTypeSteps {
    private static final Logger logger = LogManager.getLogger(GenerateReportTypeSteps.class);
    private GenerateReportTypePage reportTypePage;

    private GenerateReportTypePage getReportTypePage() {
        if (reportTypePage == null) {
            reportTypePage = new GenerateReportTypePage(DriverManager.getDriver());
        }
        return reportTypePage;
    }

    @Dengan("admin membuka halaman generate report type")
    public void adminMembukaHalamanGenerateReportType() {
        logger.info("Membuka halaman Generate Report Type");
        getReportTypePage().open();
        String currentUrl = getReportTypePage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/generate-report-type"),
                "URL halaman generate report type tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data riwayat generate report terlihat pada halaman")
    public void tabelDataRiwayatGenerateReportTerlihatPadaHalaman() {
        Assert.assertTrue(getReportTypePage().isLaporanTableDisplayed(), "Tabel riwayat generate report tidak tampil!");
    }

    @Dan("formulir generate laporan eksekutif dan kontrol filter terlihat")
    public void formulirGenerateLaporanEksekutifDanKontrolFilterTerlihat() {
        Assert.assertTrue(getReportTypePage().isGeneratorFormDisplayed(), "Formulir generator report tidak tampil!");
        Assert.assertTrue(getReportTypePage().isFilterBarDisplayed(), "Filter bar tidak lengkap!");
    }

    @Ketika("admin memasukkan tanggal mulai riwayat report {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiRiwayatReportDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal riwayat report: {} s/d {}", startDate, endDate);
        getReportTypePage().enterStartDate(startDate);
        getReportTypePage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada riwayat report")
    public void adminMenekanTombolSearchPadaRiwayatReport() {
        getReportTypePage().clickSearch();
    }

    @Maka("tabel riwayat report menampilkan data dengan kode {string}")
    public void tabelRiwayatReportMenampilkanDataDenganKode(String expectedCode) {
        Assert.assertTrue(getReportTypePage().isTextPresentInRows(expectedCode),
                "Kode laporan '" + expectedCode + "' tidak ditemukan pada tabel riwayat!");
    }

    @Dan("tabel riwayat report menampilkan {int} baris data")
    public void tabelRiwayatReportMenampilkanBarisData(int expectedRows) {
        int actualRows = getReportTypePage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris riwayat report tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error riwayat report {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorRiwayatReport(String expectedError) {
        Assert.assertTrue(getReportTypePage().isSnackbarVisible(), "Snackbar error riwayat report tidak tampil!");
        String actualMsg = getReportTypePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian riwayat report {string}")
    public void adminMemasukkanKataKunciPencarianRiwayatReport(String keyword) {
        getReportTypePage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada riwayat report")
    public void adminMenekanTombolResetPadaRiwayatReport() {
        getReportTypePage().clickReset();
    }

    @Maka("seluruh data riwayat report kembali ditampilkan pada tabel")
    public void seluruhDataRiwayatReportKembaliDitampilkanPadaTabel() {
        int rows = getReportTypePage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data riwayat report belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter riwayat report")
    public void adminMembukaModalFilterRiwayatReport() {
        getReportTypePage().clickFilterBtn();
    }

    @Maka("modal dialog filter riwayat report terbuka pada layar")
    public void modalDialogFilterRiwayatReportTerbukaPadaLayar() {
        Assert.assertTrue(getReportTypePage().isFilterModalDisplayed(), "Modal filter riwayat report tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen riwayat report {string}")
    public void adminMemilihFilterDepartemenRiwayatReport(String dept) {
        getReportTypePage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe laporan riwayat report {string}")
    public void adminMemilihFilterTipeLaporanRiwayatReport(String typeVal) {
        getReportTypePage().selectModalReportType(typeVal);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter riwayat report")
    public void adminMenekanTombolTerapkanPadaModalFilterRiwayatReport() {
        getReportTypePage().clickApplyFilter();
    }

    @Ketika("admin memilih tipe laporan generate {string}")
    public void adminMemilihTipeLaporanGenerate(String typeVal) {
        getReportTypePage().selectReportType(typeVal);
    }

    @Dan("admin memilih format berkas generate {string}")
    public void adminMemilihFormatBerkasGenerate(String formatVal) {
        getReportTypePage().selectReportFormat(formatVal);
    }

    @Dan("admin menekan tombol Generate Sekarang")
    public void adminMenekanTombolGenerateSekarang() {
        getReportTypePage().clickGenerateReport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses generate {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesGenerate(String expectedMsg) {
        Assert.assertTrue(getReportTypePage().isSnackbarVisible(), "Snackbar sukses generate tidak tampil!");
        String actualMsg = getReportTypePage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin menekan tombol Download berkas pada baris pertama")
    public void adminMenekanTombolDownloadBerkasPadaBarisPertama() {
        getReportTypePage().clickDownloadBtn(1);
    }

    @Ketika("admin mengatur jumlah baris riwayat report menjadi {string} per halaman")
    public void adminMengaturJumlahBarisRiwayatReportMenjadiPerHalaman(String rows) {
        getReportTypePage().selectRowsPerPage(rows);
    }
}

