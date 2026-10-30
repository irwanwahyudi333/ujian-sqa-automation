package com.company.sqa.ui.steps;

import com.company.sqa.pages.PositionPage;
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

public class PositionSteps {
    private static final Logger logger = LogManager.getLogger(PositionSteps.class);
    private PositionPage positionPage;

    private PositionPage getPositionPage() {
        if (positionPage == null) {
            positionPage = new PositionPage(DriverManager.getDriver());
        }
        return positionPage;
    }

    @Dengan("admin membuka halaman position")
    public void adminMembukaHalamanPosition() {
        logger.info("Membuka halaman position");
        getPositionPage().open();
        Assert.assertTrue(getPositionPage().isPageLoaded(), "Halaman position gagal dibuka!");
    }

    @Maka("halaman position berhasil dimuat")
    public void halamanPositionBerhasilDimuat() {
        Assert.assertTrue(getPositionPage().isPageLoaded(), "Halaman position tidak termuat dengan baik!");
    }

    @Dan("judul halaman position menampilkan {string}")
    public void judulHalamanPositionMenampilkan(String expectedTitle) {
        String actual = getPositionPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman position tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian position terlihat")
    public void formPencarianPositionTerlihat() {
        Assert.assertTrue(getPositionPage().isSearchInputVisible(), "Input pencarian position tidak terlihat!");
    }

    @Dan("tombol Tambahkan position terlihat")
    public void tombolTambahkanPositionTerlihat() {
        Assert.assertTrue(getPositionPage().isTambahPositionButtonVisible(), "Tombol Tambahkan position tidak terlihat!");
    }

    @Dan("tabel data position menampilkan 6 kolom header:")
    public void tabelDataPositionMenampilkan6KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getPositionPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari posisi dengan kata kunci {string}")
    public void adminMencariPosisiDenganKataKunci(String keyword) {
        logger.info("Mencari posisi dengan keyword: {}", keyword);
        getPositionPage().search(keyword);
    }

    @Maka("tabel position hanya menampilkan baris yang mengandung nama {string}")
    public void tabelPositionHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getPositionPage().getPositionNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian posisi tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian position")
    public void adminMenekanTombolResetPencarianPosition() {
        getPositionPage().resetSearch();
    }

    @Maka("seluruh data position ditampilkan kembali di tabel")
    public void seluruhDataPositionDitampilkanKembaliDiTabel() {
        int count = getPositionPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan position")
    public void adminMengklikTombolTambahkanPosition() {
        getPositionPage().openTambahModal();
    }

    @Maka("modal tambah position ditampilkan dengan judul {string}")
    public void modalTambahPositionDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getPositionPage().isTambahModalVisible(), "Modal tambah position tidak tampil!");
        String actual = getPositionPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("dropdown departemen pada modal tambah position terlihat")
    public void dropdownDepartemenPadaModalTambahPositionTerlihat() {
        Assert.assertTrue(getPositionPage().isDepartementDropdownVisibleInModal(), "Dropdown departemen tidak terlihat!");
    }

    @Ketika("admin menekan tombol Batal pada modal tambah position")
    public void adminMenekanTombolBatalPadaModalTambahPosition() {
        getPositionPage().batalTambahModal();
    }

    @Maka("modal tambah position tertutup")
    public void modalTambahPositionTertutup() {
        Assert.assertFalse(getPositionPage().isTambahModalVisible(), "Modal tambah position masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama position")
    public void adminMengklikTombolHapusPadaBarisPertamaPosition() {
        getPositionPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus position ditampilkan")
    public void modalKonfirmasiHapusPositionDitampilkan() {
        Assert.assertTrue(getPositionPage().isDeleteModalVisible(), "Modal konfirmasi hapus position tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus position memuat teks {string}")
    public void pesanKonfirmasiHapusPositionMemuatTeks(String expectedText) {
        String actual = getPositionPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus tidak sesuai! Expected: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus position")
    public void adminMenekanTombolTidakPadaModalHapusPosition() {
        getPositionPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus position tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusPositionTertutupTanpaMenghapusData() {
        Assert.assertFalse(getPositionPage().isDeleteModalVisible(), "Modal konfirmasi hapus masih terbuka!");
    }
}

