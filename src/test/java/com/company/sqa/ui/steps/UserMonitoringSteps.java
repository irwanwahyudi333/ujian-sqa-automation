package com.company.sqa.ui.steps;

import com.company.sqa.pages.UserMonitoringPage;
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

public class UserMonitoringSteps {
    private static final Logger logger = LogManager.getLogger(UserMonitoringSteps.class);
    private UserMonitoringPage userMonitoringPage;

    private UserMonitoringPage getUserMonitoringPage() {
        if (userMonitoringPage == null) {
            userMonitoringPage = new UserMonitoringPage(DriverManager.getDriver());
        }
        return userMonitoringPage;
    }

    @Dengan("admin membuka halaman user monitoring")
    public void adminMembukaHalamanUserMonitoring() {
        logger.info("Membuka halaman user monitoring");
        getUserMonitoringPage().open();
        Assert.assertTrue(getUserMonitoringPage().isPageLoaded(), "Halaman user monitoring gagal dibuka!");
    }

    @Maka("halaman user monitoring berhasil dimuat")
    public void halamanUserMonitoringBerhasilDimuat() {
        Assert.assertTrue(getUserMonitoringPage().isPageLoaded(), "Halaman user monitoring tidak termuat dengan baik!");
    }

    @Dan("judul halaman user monitoring menampilkan {string}")
    public void judulHalamanUserMonitoringMenampilkan(String expectedTitle) {
        String actual = getUserMonitoringPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman user monitoring tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian user monitoring terlihat")
    public void formPencarianUserMonitoringTerlihat() {
        Assert.assertTrue(getUserMonitoringPage().isSearchInputVisible(), "Input pencarian user monitoring tidak terlihat!");
        Assert.assertTrue(getUserMonitoringPage().isSearchButtonVisible(), "Tombol search user monitoring tidak terlihat!");
        Assert.assertTrue(getUserMonitoringPage().isResetButtonVisible(), "Tombol reset user monitoring tidak terlihat!");
    }

    @Dan("tombol Tambahkan monitoring terlihat")
    public void tombolTambahkanMonitoringTerlihat() {
        Assert.assertTrue(getUserMonitoringPage().isTambahMonitoringButtonVisible(), "Tombol Tambahkan monitoring tidak terlihat!");
    }

    @Dan("tabel data user monitoring menampilkan 8 kolom header:")
    public void tabelDataUserMonitoringMenampilkan8KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getUserMonitoringPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari user monitoring dengan kata kunci {string}")
    public void adminMencariUserMonitoringDenganKataKunci(String keyword) {
        logger.info("Mencari user monitoring dengan kata kunci: {}", keyword);
        getUserMonitoringPage().search(keyword);
    }

    @Maka("tabel user monitoring hanya menampilkan baris yang mengandung nama {string}")
    public void tabelUserMonitoringHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getUserMonitoringPage().getUserNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian user monitoring tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian monitoring")
    public void adminMenekanTombolResetPencarianMonitoring() {
        getUserMonitoringPage().resetSearch();
    }

    @Maka("seluruh data user monitoring ditampilkan kembali di tabel")
    public void seluruhDataUserMonitoringDitampilkanKembaliDiTabel() {
        int count = getUserMonitoringPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset pencarian tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan monitoring")
    public void adminMengklikTombolTambahkanMonitoring() {
        getUserMonitoringPage().openTambahModal();
    }

    @Maka("modal tambah user monitoring ditampilkan dengan judul {string}")
    public void modalTambahUserMonitoringDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getUserMonitoringPage().isTambahModalVisible(), "Modal tambah user monitoring tidak tampil!");
        String actual = getUserMonitoringPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Batal pada modal tambah monitoring")
    public void adminMenekanTombolBatalPadaModalTambahMonitoring() {
        getUserMonitoringPage().batalTambahModal();
    }

    @Maka("modal tambah user monitoring tertutup")
    public void modalTambahUserMonitoringTertutup() {
        Assert.assertFalse(getUserMonitoringPage().isTambahModalVisible(), "Modal tambah monitoring masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama monitoring")
    public void adminMengklikTombolHapusPadaBarisPertamaMonitoring() {
        getUserMonitoringPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus monitoring ditampilkan")
    public void modalKonfirmasiHapusMonitoringDitampilkan() {
        Assert.assertTrue(getUserMonitoringPage().isDeleteModalVisible(), "Modal konfirmasi hapus monitoring tidak tampil!");
    }

    @Dan("pesan konfirmasi memuat teks {string}")
    public void pesanKonfirmasiMemuatTeks(String expectedText) {
        String actual = getUserMonitoringPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus tidak sesuai! Expected to contain: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus monitoring")
    public void adminMenekanTombolTidakPadaModalHapusMonitoring() {
        getUserMonitoringPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus monitoring tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusMonitoringTertutupTanpaMenghapusData() {
        Assert.assertFalse(getUserMonitoringPage().isDeleteModalVisible(), "Modal konfirmasi hapus masih terbuka!");
    }
}

