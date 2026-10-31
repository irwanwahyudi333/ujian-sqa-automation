package com.company.sqa.ui.steps;

import com.company.sqa.pages.ClientUplinerPage;
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

public class ClientUplinerSteps {
    private static final Logger logger = LogManager.getLogger(ClientUplinerSteps.class);
    private ClientUplinerPage clientUplinerPage;

    private ClientUplinerPage getClientUplinerPage() {
        if (clientUplinerPage == null) {
            clientUplinerPage = new ClientUplinerPage(DriverManager.getDriver());
        }
        return clientUplinerPage;
    }

    @Dengan("admin membuka halaman client upliner")
    public void adminMembukaHalamanClientUpliner() {
        logger.info("Membuka halaman client upliner");
        getClientUplinerPage().open();
        Assert.assertTrue(getClientUplinerPage().isPageLoaded(), "Halaman client upliner gagal dibuka!");
    }

    @Maka("halaman client upliner berhasil dimuat")
    public void halamanClientUplinerBerhasilDimuat() {
        Assert.assertTrue(getClientUplinerPage().isPageLoaded(), "Halaman client upliner tidak termuat dengan baik!");
    }

    @Dan("judul halaman client upliner menampilkan {string}")
    public void judulHalamanClientUplinerMenampilkan(String expectedTitle) {
        String actual = getClientUplinerPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman client upliner tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian client upliner terlihat")
    public void formPencarianClientUplinerTerlihat() {
        Assert.assertTrue(getClientUplinerPage().isSearchInputVisible(), "Input pencarian client upliner tidak terlihat!");
    }

    @Dan("tombol Tambahkan upliner terlihat")
    public void tombolTambahkanUplinerTerlihat() {
        Assert.assertTrue(getClientUplinerPage().isTambahUplinerButtonVisible(), "Tombol Tambahkan upliner tidak terlihat!");
    }

    @Dan("tabel data client upliner menampilkan 8 kolom header:")
    public void tabelDataClientUplinerMenampilkan8KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getClientUplinerPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari client upliner dengan kata kunci {string}")
    public void adminMencariClientUplinerDenganKataKunci(String keyword) {
        logger.info("Mencari client upliner dengan keyword: {}", keyword);
        getClientUplinerPage().search(keyword);
    }

    @Maka("tabel client upliner hanya menampilkan baris yang mengandung nama {string}")
    public void tabelClientUplinerHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getClientUplinerPage().getUplinerNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian client upliner tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian client upliner")
    public void adminMenekanTombolResetPencarianClientUpliner() {
        getClientUplinerPage().resetSearch();
    }

    @Maka("seluruh data client upliner ditampilkan kembali di tabel")
    public void seluruhDataClientUplinerDitampilkanKembaliDiTabel() {
        int count = getClientUplinerPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan upliner")
    public void adminMengklikTombolTambahkanUpliner() {
        getClientUplinerPage().openTambahModal();
    }

    @Maka("modal tambah upliner ditampilkan dengan judul {string}")
    public void modalTambahUplinerDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getClientUplinerPage().isTambahModalVisible(), "Modal tambah upliner tidak tampil!");
        String actual = getClientUplinerPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Batal pada modal tambah upliner")
    public void adminMenekanTombolBatalPadaModalTambahUpliner() {
        getClientUplinerPage().batalTambahModal();
    }

    @Maka("modal tambah upliner tertutup")
    public void modalTambahUplinerTertutup() {
        Assert.assertFalse(getClientUplinerPage().isTambahModalVisible(), "Modal tambah upliner masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama upliner")
    public void adminMengklikTombolHapusPadaBarisPertamaUpliner() {
        getClientUplinerPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus upliner ditampilkan")
    public void modalKonfirmasiHapusUplinerDitampilkan() {
        Assert.assertTrue(getClientUplinerPage().isDeleteModalVisible(), "Modal konfirmasi hapus upliner tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus upliner memuat teks {string}")
    public void pesanKonfirmasiHapusUplinerMemuatTeks(String expectedText) {
        String actual = getClientUplinerPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus tidak sesuai! Expected to contain: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus upliner")
    public void adminMenekanTombolTidakPadaModalHapusUpliner() {
        getClientUplinerPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus upliner tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusUplinerTertutupTanpaMenghapusData() {
        Assert.assertFalse(getClientUplinerPage().isDeleteModalVisible(), "Modal konfirmasi hapus masih terbuka!");
    }
}

