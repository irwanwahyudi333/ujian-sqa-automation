package com.company.sqa.ui.steps;

import com.company.sqa.pages.DepartementsSettingsPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class DepartementsSettingsSteps {
    private static final Logger logger = LogManager.getLogger(DepartementsSettingsSteps.class);
    private DepartementsSettingsPage departementsSettingsPage;

    private DepartementsSettingsPage getDepartementsSettingsPage() {
        if (departementsSettingsPage == null) {
            departementsSettingsPage = new DepartementsSettingsPage(DriverManager.getDriver());
        }
        return departementsSettingsPage;
    }

    @Dengan("admin membuka halaman departements settings")
    public void adminMembukaHalamanDepartementsSettings() {
        logger.info("Membuka halaman departements settings");
        getDepartementsSettingsPage().open();
        Assert.assertTrue(getDepartementsSettingsPage().isPageLoaded(), "Halaman departements settings gagal dibuka!");
    }

    @Maka("halaman departements settings berhasil dimuat")
    public void halamanDepartementsSettingsBerhasilDimuat() {
        Assert.assertTrue(getDepartementsSettingsPage().isPageLoaded(), "Halaman departements settings tidak termuat dengan baik!");
    }

    @Dan("judul halaman departements settings menampilkan {string}")
    public void judulHalamanDepartementsSettingsMenampilkan(String expectedTitle) {
        String actual = getDepartementsSettingsPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman departements settings tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian departements settings terlihat")
    public void formPencarianDepartementsSettingsTerlihat() {
        Assert.assertTrue(getDepartementsSettingsPage().isSearchInputVisible(), "Input pencarian departements settings tidak terlihat!");
    }

    @Dan("tombol Tambah Departemens Setting terlihat")
    public void tombolTambahDepartemensSettingTerlihat() {
        Assert.assertTrue(getDepartementsSettingsPage().isTambahSettingButtonVisible(), "Tombol Tambah Departemens Setting tidak terlihat!");
    }

    @Dan("tabel data departements settings menampilkan 6 kolom header:")
    public void tabelDataDepartementsSettingsMenampilkan6KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getDepartementsSettingsPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mengklik toggle switch status pada baris pertama departemens setting")
    public void adminMengklikToggleSwitchStatusPadaBarisPertamaDepartemensSetting() {
        getDepartementsSettingsPage().toggleStatusOnFirstRow();
    }

    @Maka("status switch berubah dan notifikasi feedback departemens setting ditampilkan")
    public void statusSwitchBerubahDanNotifikasiFeedbackDepartemensSettingDitampilkan() {
        Assert.assertTrue(getDepartementsSettingsPage().isSnackbarVisible(), "Snackbar notifikasi feedback tidak tampil!");
    }

    @Ketika("admin mencari setting departemen dengan kata kunci {string}")
    public void adminMencariSettingDepartemenDenganKataKunci(String keyword) {
        logger.info("Mencari setting departemen dengan keyword: {}", keyword);
        getDepartementsSettingsPage().search(keyword);
    }

    @Maka("tabel departemens settings hanya menampilkan baris yang mengandung nama {string}")
    public void tabelDepartemensSettingsHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getDepartementsSettingsPage().getSettingNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian setting departemen tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian departemens settings")
    public void adminMenekanTombolResetPencarianDepartemensSettings() {
        getDepartementsSettingsPage().resetSearch();
    }

    @Maka("seluruh data departemens settings ditampilkan kembali di tabel")
    public void seluruhDataDepartemensSettingsDitampilkanKembaliDiTabel() {
        int count = getDepartementsSettingsPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambah Departemens Setting")
    public void adminMengklikTombolTambahDepartemensSetting() {
        getDepartementsSettingsPage().openTambahModal();
    }

    @Maka("modal tambah setting ditampilkan dengan judul {string}")
    public void modalTambahSettingDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getDepartementsSettingsPage().isTambahModalVisible(), "Modal tambah setting tidak tampil!");
        String actual = getDepartementsSettingsPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah setting tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Batal pada modal tambah setting")
    public void adminMenekanTombolBatalPadaModalTambahSetting() {
        getDepartementsSettingsPage().batalTambahModal();
    }

    @Maka("modal tambah setting tertutup")
    public void modalTambahSettingTertutup() {
        Assert.assertFalse(getDepartementsSettingsPage().isTambahModalVisible(), "Modal tambah setting masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama departemens setting")
    public void adminMengklikTombolHapusPadaBarisPertamaDepartemensSetting() {
        getDepartementsSettingsPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus setting ditampilkan")
    public void modalKonfirmasiHapusSettingDitampilkan() {
        Assert.assertTrue(getDepartementsSettingsPage().isDeleteModalVisible(), "Modal konfirmasi hapus setting tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus setting memuat teks {string}")
    public void pesanKonfirmasiHapusSettingMemuatTeks(String expectedText) {
        String actual = getDepartementsSettingsPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus setting tidak sesuai! Expected: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus setting")
    public void adminMenekanTombolTidakPadaModalHapusSetting() {
        getDepartementsSettingsPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus setting tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusSettingTertutupTanpaMenghapusData() {
        Assert.assertFalse(getDepartementsSettingsPage().isDeleteModalVisible(), "Modal konfirmasi hapus setting masih terbuka!");
    }
}

