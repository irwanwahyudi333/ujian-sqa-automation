package com.company.sqa.ui.steps;

import com.company.sqa.pages.LaporanSisaCutiPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LaporanSisaCutiSteps {
    private static final Logger logger = LogManager.getLogger(LaporanSisaCutiSteps.class);
    private LaporanSisaCutiPage sisaCutiPage;

    private LaporanSisaCutiPage getSisaCutiPage() {
        if (sisaCutiPage == null) {
            sisaCutiPage = new LaporanSisaCutiPage(DriverManager.getDriver());
        }
        return sisaCutiPage;
    }

    @Dengan("admin membuka halaman laporan sisa cuti")
    public void adminMembukaHalamanLaporanSisaCuti() {
        logger.info("Membuka halaman Laporan Sisa Cuti");
        getSisaCutiPage().open();
        String currentUrl = getSisaCutiPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/laporan/sisa-cuti"),
                "URL halaman laporan sisa cuti tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data laporan sisa cuti terlihat pada halaman")
    public void tabelDataLaporanSisaCutiTerlihatPadaHalaman() {
        Assert.assertTrue(getSisaCutiPage().isLaporanTableDisplayed(), "Tabel laporan sisa cuti tidak tampil!");
    }

    @Dan("elemen kontrol tanggal, pencarian, dan tombol filter sisa cuti terlihat")
    public void elemenKontrolTanggalPencarianDanTombolFilterSisaCutiTerlihat() {
        Assert.assertTrue(getSisaCutiPage().isFilterBarDisplayed(), "Filter bar sisa cuti tidak lengkap!");
        Assert.assertTrue(getSisaCutiPage().isExportBtnDisplayed(), "Tombol export sisa cuti tidak tampil!");
        Assert.assertTrue(getSisaCutiPage().isSyncCutiBtnDisplayed(), "Tombol sync cuti tidak tampil!");
    }

    @Ketika("admin memasukkan tanggal mulai sisa cuti {string} dan tanggal akhir {string}")
    public void adminMemasukkanTanggalMulaiSisaCutiDanTanggalAkhir(String startDate, String endDate) {
        logger.info("Memasukkan rentang tanggal sisa cuti: {} s/d {}", startDate, endDate);
        getSisaCutiPage().enterStartDate(startDate);
        getSisaCutiPage().enterEndDate(endDate);
    }

    @Dan("admin menekan tombol Search pada sisa cuti")
    public void adminMenekanTombolSearchPadaSisaCuti() {
        getSisaCutiPage().clickSearch();
    }

    @Maka("tabel sisa cuti menampilkan rekaman untuk karyawan {string}")
    public void tabelSisaCutiMenampilkanRekamanUntukKaryawan(String expectedName) {
        Assert.assertTrue(getSisaCutiPage().isEmployeeNamePresent(expectedName),
                "Karyawan '" + expectedName + "' tidak ditemukan pada tabel sisa cuti!");
    }

    @Dan("tabel sisa cuti menampilkan {int} baris data")
    public void tabelSisaCutiMenampilkanBarisData(int expectedRows) {
        int actualRows = getSisaCutiPage().getRowCount();
        Assert.assertEquals(actualRows, expectedRows, "Jumlah baris laporan sisa cuti tidak sesuai!");
    }

    @Maka("sistem menampilkan notifikasi snackbar error sisa cuti {string}")
    public void sistemMenampilkanNotifikasiSnackbarErrorSisaCuti(String expectedError) {
        Assert.assertTrue(getSisaCutiPage().isSnackbarVisible(), "Snackbar error sisa cuti tidak tampil!");
        String actualMsg = getSisaCutiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedError),
                "Pesan error snackbar tidak sesuai! Expected: '" + expectedError + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin memasukkan kata kunci pencarian sisa cuti {string}")
    public void adminMemasukkanKataKunciPencarianSisaCuti(String keyword) {
        getSisaCutiPage().enterSearch(keyword);
    }

    @Ketika("admin menekan tombol Reset pada sisa cuti")
    public void adminMenekanTombolResetPadaSisaCuti() {
        getSisaCutiPage().clickReset();
    }

    @Maka("seluruh data sisa cuti kembali ditampilkan pada tabel")
    public void seluruhDataSisaCutiKembaliDitampilkanPadaTabel() {
        int rows = getSisaCutiPage().getRowCount();
        Assert.assertTrue(rows >= 10, "Data laporan sisa cuti belum direset secara penuh! Baris: " + rows);
    }

    @Ketika("admin membuka modal filter sisa cuti")
    public void adminMembukaModalFilterSisaCuti() {
        getSisaCutiPage().clickFilterBtn();
    }

    @Maka("modal dialog filter sisa cuti terbuka pada layar")
    public void modalDialogFilterSisaCutiTerbukaPadaLayar() {
        Assert.assertTrue(getSisaCutiPage().isFilterModalDisplayed(), "Modal filter sisa cuti tidak terbuka!");
    }

    @Ketika("admin memilih filter departemen sisa cuti {string}")
    public void adminMemilihFilterDepartemenSisaCuti(String dept) {
        getSisaCutiPage().selectDepartemen(dept);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter sisa cuti")
    public void adminMenekanTombolTerapkanPadaModalFilterSisaCuti() {
        getSisaCutiPage().clickApplyFilter();
    }

    @Ketika("admin menekan tombol Sync Kuota Cuti")
    public void adminMenekanTombolSyncKuotaCuti() {
        getSisaCutiPage().clickSyncCuti();
    }

    @Maka("sistem menampilkan notifikasi snackbar sukses sisa cuti {string}")
    public void sistemMenampilkanNotifikasiSnackbarSuksesSisaCuti(String expectedMsg) {
        Assert.assertTrue(getSisaCutiPage().isSnackbarVisible(), "Snackbar sukses sisa cuti tidak tampil!");
        String actualMsg = getSisaCutiPage().getSnackbarMessage();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan sukses snackbar sisa cuti tidak sesuai! Expected: '" + expectedMsg + "', Actual: '" + actualMsg + "'");
    }

    @Ketika("admin mengatur jumlah baris sisa cuti menjadi {string} per halaman")
    public void adminMengaturJumlahBarisSisaCutiMenjadiPerHalaman(String rows) {
        getSisaCutiPage().selectRowsPerPage(rows);
    }

    @Ketika("admin menekan tombol Export sisa cuti")
    public void adminMenekanTombolExportSisaCuti() {
        getSisaCutiPage().clickExport();
    }
}

