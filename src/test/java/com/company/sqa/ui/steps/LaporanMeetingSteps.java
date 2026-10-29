package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanMeetingPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanMeetingSteps {
    private static final Logger logger = LogManager.getLogger(LaporanMeetingSteps.class);
    private LaporanMeetingPage meetingPage;

    private LaporanMeetingPage getMeetingPage() {
        if (meetingPage == null) {
            meetingPage = new LaporanMeetingPage(DriverManager.getDriver());
        }
        return meetingPage;
    }

    @Dengan("admin membuka halaman laporan meeting")
    public void adminMembukaHalamanLaporanMeeting() {
        logger.info("Membuka halaman Laporan Meeting");
        getMeetingPage().open();
        String currentUrl = getMeetingPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/meeting"),
                "URL halaman laporan meeting tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan meeting terlihat pada halaman")
    public void tabelDataLaporanMeetingTerlihatPadaHalaman() {
        Assert.assertTrue(getMeetingPage().isLaporanTableDisplayed(), "Tabel laporan meeting tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter meeting terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterMeetingTerlihat() {
        Assert.assertTrue(getMeetingPage().isFilterBarDisplayed(), "Filter bar meeting tidak lengkap!");
        Assert.assertTrue(getMeetingPage().isExportBtnDisplayed(), "Tombol export meeting tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai meeting {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiMeetingDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal meeting: {} s/d {}", startDate, endDate);
        getMeetingPage().enterStartDate(startDate);
        getMeetingPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada meeting")
    public void adminMenekanTombolSearchPadaMeeting() {
        getMeetingPage().clickSearch();
    }

    @Maka("tabel meeting menampilkan rekaman topik {string}")
    public void tabelMeetingMenampilkanRekamanTopik(String expectedTopic) {
        Assert.assertTrue(getMeetingPage().isMeetingTopicPresent(expectedTopic),
                "Topik meeting '" + expectedTopic + "' tidak ditemukan pada tabel meeting!");
    }

    @Dan("tabel meeting menampilkan {int} baris data")
    public void tabelMeetingMenampilkanBarisData(int expectedRows) {
        int actualRows = getMeetingPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan meeting tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error meeting {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorMeeting(String expectedError) {
        Assert.assertTrue(getMeetingPage().isSnackbarVisible(), "Snackbar error meeting tidak tampil!");
        String actualMsg = getMeetingPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian meeting {string}")
    public void adminMemasukkanKataKunciPencarianMeeting(String keyword) {
        getMeetingPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada meeting")
    public void adminMenekanTombolResetPadaMeeting() {
        getMeetingPage().clickReset();
    }

    @Maka("seluruh data meeting kembali ditampilkan pada tabel")
    public void seluruhDataMeetingKembaliDitampilkanPadaTabel() {
        int rows = getMeetingPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan meeting belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter meeting")
    public void adminMembukaModalFilterMeeting() {
        getMeetingPage().clickFilterBtn();
    }

    @Maka("modal dialog filter meeting terbuka pada layar")
    public void modalDialogFilterMeetingTerbukaPadaLayar() {
        Assert.assertTrue(getMeetingPage().isFilterModalDisplayed(), "Modal filter meeting tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen meeting {string}")
    public void adminMemilihFilterDepartemenMeeting(String dept) {
        getMeetingPage().selectDepartemen(dept);
    }

    @Dan("admin memilih filter tipe meeting {string}")
    public void adminMemilihFilterTipeMeeting(String tipe) {
        getMeetingPage().selectTipeMeeting(tipe);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter meeting")
    public void adminMenekanTombolTerapkanPadaModalFilterMeeting() {
        getMeetingPage().clickApplyFilter();
    }

    @Ketika("admin mengklik tombol detail meeting pada baris ke-{int}")
    public void adminMengklikTombolDetailMeetingPadaBarisKe(int rowIndex) {
        getMeetingPage().clickDetailBtn(rowIndex);
    }

    @Maka("modal dialog detail meeting terbuka pada layar")
    public void modalDialogDetailMeetingTerbukaPadaLayar() {
        Assert.assertTrue(getMeetingPage().isDetailModalDisplayed(), "Modal detail meeting tidak terbuka!");
    }

    @Dan("modal menampilkan judul detail meeting untuk {string}")
    public void modalMenampilkanJudulDetailMeetingUntuk(String expectedName) {
        String title = getMeetingPage().getDetailModalTitle();
        Assert.assertTrue(title.contains(expectedName),
                "Judul modal detail meeting tidak cocok! Expected: '" + expectedName + "', Actual: '" + title + "'");
    }

    @Ketika("admin menutup modal detail meeting")
    public void adminMenutupModalDetailMeeting() {
        getMeetingPage().clickCloseDetail();
    }

    @Ketika("admin mengatur jumlah baris meeting menjadi {string} per halaman")
    public void adminMengaturJumlahBarisMeetingMenjadiPerHalaman(String rows) {
        getMeetingPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export meeting")
    public void adminMenekanTombolExportMeeting() {
        getMeetingPage().clickExport();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses meeting {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesMeeting(String expectedMsg) {
        Assert.assertTrue(getMeetingPage().isSnackbarVisible(), "Snackbar sukses export meeting tidak tampil!");
        String actualMsg = getMeetingPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan export snackbar tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }
}

