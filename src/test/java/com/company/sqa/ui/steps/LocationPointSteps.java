package com.company.sqa.ui.steps;

import com.company.sqa.pages.LocationPointPage;
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

public class LocationPointSteps {
    private static final Logger logger = LogManager.getLogger(LocationPointSteps.class);
    private LocationPointPage locationPointPage;

    private LocationPointPage getLocationPointPage() {
        if (locationPointPage == null) {
            locationPointPage = new LocationPointPage(DriverManager.getDriver());
        }
        return locationPointPage;
    }

    @Dengan("admin membuka halaman location point")
    public void adminMembukaHalamanLocationPoint() {
        logger.info("Membuka halaman location point");
        getLocationPointPage().open();
        Assert.assertTrue(getLocationPointPage().isPageLoaded(), "Halaman location point gagal dibuka!");
    }

    @Maka("halaman location point berhasil dimuat")
    public void halamanLocationPointBerhasilDimuat() {
        Assert.assertTrue(getLocationPointPage().isPageLoaded(), "Halaman location point tidak termuat dengan baik!");
    }

    @Dan("judul halaman location point menampilkan {string}")
    public void judulHalamanLocationPointMenampilkan(String expectedTitle) {
        String actual = getLocationPointPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman location point tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian location point terlihat")
    public void formPencarianLocationPointTerlihat() {
        Assert.assertTrue(getLocationPointPage().isSearchInputVisible(), "Input pencarian location point tidak terlihat!");
    }

    @Dan("tombol Tambahkan lokasi terlihat")
    public void tombolTambahkanLokasiTerlihat() {
        Assert.assertTrue(getLocationPointPage().isTambahLokasiButtonVisible(), "Tombol Tambahkan lokasi tidak terlihat!");
    }

    @Dan("tabel data location point menampilkan 7 kolom header:")
    public void tabelDataLocationPointMenampilkan7KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getLocationPointPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari lokasi presensi dengan kata kunci {string}")
    public void adminMencariLokasiPresensiDenganKataKunci(String keyword) {
        logger.info("Mencari lokasi presensi dengan keyword: {}", keyword);
        getLocationPointPage().search(keyword);
    }

    @Maka("tabel location point hanya menampilkan baris yang mengandung nama {string}")
    public void tabelLocationPointHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getLocationPointPage().getLocationNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian lokasi tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian location point")
    public void adminMenekanTombolResetPencarianLocationPoint() {
        getLocationPointPage().resetSearch();
    }

    @Maka("seluruh data location point ditampilkan kembali di tabel")
    public void seluruhDataLocationPointDitampilkanKembaliDiTabel() {
        int count = getLocationPointPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan lokasi")
    public void adminMengklikTombolTambahkanLokasi() {
        getLocationPointPage().openTambahModal();
    }

    @Maka("modal tambah lokasi ditampilkan dengan judul {string}")
    public void modalTambahLokasiDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getLocationPointPage().isTambahModalVisible(), "Modal tambah lokasi tidak tampil!");
        String actual = getLocationPointPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah lokasi tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("field input latitude, longitude, dan radius terlihat pada modal tambah lokasi")
    public void fieldInputLatitudeLongitudeDanRadiusTerlihatPadaModalTambahLokasi() {
        Assert.assertTrue(getLocationPointPage().areCoordinateInputsVisible(), "Input koordinat lokasi tidak terlihat!");
    }

    @Ketika("admin menekan tombol Batal pada modal tambah lokasi")
    public void adminMenekanTombolBatalPadaModalTambahLokasi() {
        getLocationPointPage().batalTambahModal();
    }

    @Maka("modal tambah lokasi tertutup")
    public void modalTambahLokasiTertutup() {
        Assert.assertFalse(getLocationPointPage().isTambahModalVisible(), "Modal tambah lokasi masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama lokasi")
    public void adminMengklikTombolHapusPadaBarisPertamaLokasi() {
        getLocationPointPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus lokasi ditampilkan")
    public void modalKonfirmasiHapusLokasiDitampilkan() {
        Assert.assertTrue(getLocationPointPage().isDeleteModalVisible(), "Modal konfirmasi hapus lokasi tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus lokasi memuat teks {string}")
    public void pesanKonfirmasiHapusLokasiMemuatTeks(String expectedText) {
        String actual = getLocationPointPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus lokasi tidak sesuai! Expected: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus lokasi")
    public void adminMenekanTombolTidakPadaModalHapusLokasi() {
        getLocationPointPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus lokasi tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusLokasiTertutupTanpaMenghapusData() {
        Assert.assertFalse(getLocationPointPage().isDeleteModalVisible(), "Modal konfirmasi hapus lokasi masih terbuka!");
    }
}

