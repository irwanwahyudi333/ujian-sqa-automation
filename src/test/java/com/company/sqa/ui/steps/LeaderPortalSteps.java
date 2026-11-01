package com.company.sqa.ui.steps;

import com.company.sqa.pages.LeaderPortalPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LeaderPortalSteps {
    private static final Logger logger = LogManager.getLogger(LeaderPortalSteps.class);
    private LeaderPortalPage leaderPortalPage;

    private LeaderPortalPage getLeaderPortalPage() {
        if (leaderPortalPage == null) {
            leaderPortalPage = new LeaderPortalPage(DriverManager.getDriver());
        }
        return leaderPortalPage;
    }

    @Dengan("leader membuka halaman portal leader {string}")
    public void leaderMembukaHalamanPortalLeader(String relativePath) {
        logger.info("Leader membuka halaman portal: {}", relativePath);
        getLeaderPortalPage().open(relativePath);
        Assert.assertTrue(getLeaderPortalPage().isPageLoaded(),
                "Halaman portal leader '" + relativePath + "' gagal dimuat!");
    }

    @Maka("tabel data pada halaman portal leader terlihat")
    public void tabelDataPadaHalamanPortalLeaderTerlihat() {
        Assert.assertTrue(getLeaderPortalPage().isDataTableDisplayed(),
                "Tabel data portal leader tidak tampil pada halaman!");
    }

    @Dan("kontrol pencarian dan filter tanggal leader terlihat")
    public void kontrolPencarianDanFilterTanggalLeaderTerlihat() {
        Assert.assertTrue(getLeaderPortalPage().isFilterBarDisplayed(),
                "Kontrol pencarian atau filter tanggal leader tidak tampil!");
    }

    @Dan("kartu statistik presensi regu terlihat pada dashboard leader")
    public void kartuStatistikPresensiReguTerlihatPadaDashboardLeader() {
        Assert.assertTrue(getLeaderPortalPage().areStatCardsDisplayed(),
                "Kartu statistik presensi tidak tampil pada dashboard leader!");
    }

    @Ketika("leader mencari data dengan kata kunci {string} pada portal leader")
    public void leaderMencariDataDenganKataKunciPadaPortalLeader(String keyword) {
        getLeaderPortalPage().search(keyword);
    }

    @Maka("tabel leader menampilkan baris dengan data {string}")
    public void tabelLeaderMenampilkanBarisDenganData(String expectedData) {
        Assert.assertTrue(getLeaderPortalPage().isTextPresentInTable(expectedData),
                "Data atau nama staf '" + expectedData + "' tidak ditemukan dalam tabel leader!");
    }

    @Ketika("leader menekan tombol reset pada portal leader")
    public void leaderMenekanTombolResetPadaPortalLeader() {
        getLeaderPortalPage().resetFilter();
    }

    @Maka("jumlah baris data tabel leader lebih besar dari nol")
    public void jumlahBarisDataTabelLeaderLebihBesarDariNol() {
        Assert.assertTrue(getLeaderPortalPage().getTableRowCount() > 0,
                "Tabel leader tidak memiliki baris data!");
    }

    @Ketika("leader menyaring data berdasarkan status {string} pada portal leader")
    public void leaderMenyaringDataBerdasarkanStatusPadaPortalLeader(String status) {
        getLeaderPortalPage().filterByStatus(status);
    }

    @Ketika("leader membuka modal persetujuan pada baris pertama dengan tindakan {string}")
    public void leaderMembukaModalPersetujuanPadaBarisPertamaDenganTindakan(String action) {
        getLeaderPortalPage().openFirstApprovalModal(action);
    }

    @Maka("modal persetujuan leader ditampilkan")
    public void modalPersetujuanLeaderDitampilkan() {
        Assert.assertTrue(getLeaderPortalPage().isApprovalModalDisplayed(),
                "Modal persetujuan leader tidak tampil!");
    }

    @Dan("leader mengisi alasan penolakan pada portal leader {string}")
    public void leaderMengisiAlasanPenolakan(String reason) {
        getLeaderPortalPage().fillRejectReason(reason);
    }

    @Ketika("leader mengonfirmasi tindakan persetujuan modal")
    public void leaderMengonfirmasiTindakanPersetujuanModal() {
        getLeaderPortalPage().submitApproval();
    }

    @Maka("formulir generator laporan leader terlihat pada halaman")
    public void formulirGeneratorLaporanLeaderTerlihatPadaHalaman() {
        Assert.assertTrue(getLeaderPortalPage().isGenerateFormDisplayed(),
                "Formulir generator laporan leader tidak tampil!");
    }

    @Ketika("leader menekan tombol generate dan download laporan")
    public void leaderMenekanTombolGenerateDanDownloadLaporan() {
        getLeaderPortalPage().submitGenerateReport();
    }

    @Maka("notifikasi sukses pembuatan laporan leader ditampilkan")
    public void notifikasiSuksesPembuatanLaporanLeaderDitampilkan() {
        Assert.assertTrue(getLeaderPortalPage().isGenerateSuccessAlertDisplayed(),
                "Notifikasi sukses download laporan leader tidak tampil!");
    }
}
