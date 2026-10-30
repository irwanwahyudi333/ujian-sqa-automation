package com.company.sqa.ui.steps;

import com.company.sqa.pages.DepartemensPage;
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

public class DepartemensSteps {
    private static final Logger logger = LogManager.getLogger(DepartemensSteps.class);
    private DepartemensPage departemensPage;

    private DepartemensPage getDepartemensPage() {
        if (departemensPage == null) {
            departemensPage = new DepartemensPage(DriverManager.getDriver());
        }
        return departemensPage;
    }

    @Dengan("admin membuka halaman departemens")
    public void adminMembukaHalamanDepartemens() {
        logger.info("Membuka halaman departemens");
        getDepartemensPage().open();
        Assert.assertTrue(getDepartemensPage().isPageLoaded(), "Halaman departemens gagal dibuka!");
    }

    @Maka("halaman departemens berhasil dimuat")
    public void halamanDepartemensBerhasilDimuat() {
        Assert.assertTrue(getDepartemensPage().isPageLoaded(), "Halaman departemens tidak termuat dengan baik!");
    }

    @Dan("judul halaman departemens menampilkan {string}")
    public void judulHalamanDepartemensMenampilkan(String expectedTitle) {
        String actual = getDepartemensPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman departemens tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian departemens terlihat")
    public void formPencarianDepartemensTerlihat() {
        Assert.assertTrue(getDepartemensPage().isSearchInputVisible(), "Input pencarian departemens tidak terlihat!");
    }

    @Dan("tombol Tambahkan departemen terlihat")
    public void tombolTambahkanDepartemenTerlihat() {
        Assert.assertTrue(getDepartemensPage().isTambahDepartemenButtonVisible(), "Tombol Tambahkan departemen tidak terlihat!");
    }

    @Dan("tabel data departemens menampilkan 6 kolom header:")
    public void tabelDataDepartemensMenampilkan6KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getDepartemensPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari departemen dengan kata kunci {string}")
    public void adminMencariDepartemenDenganKataKunci(String keyword) {
        logger.info("Mencari departemen dengan keyword: {}", keyword);
        getDepartemensPage().search(keyword);
    }

    @Maka("tabel departemens hanya menampilkan baris yang mengandung nama {string}")
    public void tabelDepartemensHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getDepartemensPage().getDepartmentNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian departemen tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian departemen")
    public void adminMenekanTombolResetPencarianDepartemen() {
        getDepartemensPage().resetSearch();
    }

    @Maka("seluruh data departemen ditampilkan kembali di tabel")
    public void seluruhDataDepartemenDitampilkanKembaliDiTabel() {
        int count = getDepartemensPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan departemen")
    public void adminMengklikTombolTambahkanDepartemen() {
        getDepartemensPage().openTambahModal();
    }

    @Maka("modal tambah departemen ditampilkan dengan judul {string}")
    public void modalTambahDepartemenDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getDepartemensPage().isTambahModalVisible(), "Modal tambah departemen tidak tampil!");
        String actual = getDepartemensPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah departemen tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Batal pada modal tambah departemen")
    public void adminMenekanTombolBatalPadaModalTambahDepartemen() {
        getDepartemensPage().batalTambahModal();
    }

    @Maka("modal tambah departemen tertutup")
    public void modalTambahDepartemenTertutup() {
        Assert.assertFalse(getDepartemensPage().isTambahModalVisible(), "Modal tambah departemen masih terbuka!");
    }

    @Ketika("admin mengklik tombol Atur Virtual ID pada baris pertama departemen")
    public void adminMengklikTombolAturVirtualIdPadaBarisPertamaDepartemen() {
        getDepartemensPage().openVirtualIdModalOnFirstRow();
    }

    @Maka("modal dialog Atur Virtual ID ditampilkan")
    public void modalDialogAturVirtualIdDitampilkan() {
        Assert.assertTrue(getDepartemensPage().isVirtualIdModalVisible(), "Modal Atur Virtual ID tidak tampil!");
    }

    @Ketika("admin menutup modal Atur Virtual ID")
    public void adminMenutupModalAturVirtualId() {
        getDepartemensPage().batalVirtualIdModal();
    }

    @Maka("modal dialog Atur Virtual ID tertutup")
    public void modalDialogAturVirtualIdTertutup() {
        Assert.assertFalse(getDepartemensPage().isVirtualIdModalVisible(), "Modal Atur Virtual ID masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama departemen")
    public void adminMengklikTombolHapusPadaBarisPertamaDepartemen() {
        getDepartemensPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus departemen ditampilkan")
    public void modalKonfirmasiHapusDepartemenDitampilkan() {
        Assert.assertTrue(getDepartemensPage().isDeleteModalVisible(), "Modal konfirmasi hapus departemen tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus departemen memuat teks {string}")
    public void pesanKonfirmasiHapusDepartemenMemuatTeks(String expectedText) {
        String actual = getDepartemensPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus tidak sesuai! Expected: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus departemen")
    public void adminMenekanTombolTidakPadaModalHapusDepartemen() {
        getDepartemensPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus departemen tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusDepartemenTertutupTanpaMenghapusData() {
        Assert.assertFalse(getDepartemensPage().isDeleteModalVisible(), "Modal konfirmasi hapus masih terbuka!");
    }
}

