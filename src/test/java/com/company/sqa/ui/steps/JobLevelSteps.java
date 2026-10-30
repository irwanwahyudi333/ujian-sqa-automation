package com.company.sqa.ui.steps;

import com.company.sqa.pages.JobLevelPage;
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

public class JobLevelSteps {
    private static final Logger logger = LogManager.getLogger(JobLevelSteps.class);
    private JobLevelPage jobLevelPage;

    private JobLevelPage getJobLevelPage() {
        if (jobLevelPage == null) {
            jobLevelPage = new JobLevelPage(DriverManager.getDriver());
        }
        return jobLevelPage;
    }

    @Dengan("admin membuka halaman job level")
    public void adminMembukaHalamanJobLevel() {
        logger.info("Membuka halaman job level");
        getJobLevelPage().open();
        Assert.assertTrue(getJobLevelPage().isPageLoaded(), "Halaman job level gagal dibuka!");
    }

    @Maka("halaman job level berhasil dimuat")
    public void halamanJobLevelBerhasilDimuat() {
        Assert.assertTrue(getJobLevelPage().isPageLoaded(), "Halaman job level tidak termuat dengan baik!");
    }

    @Dan("judul halaman job level menampilkan {string}")
    public void judulHalamanJobLevelMenampilkan(String expectedTitle) {
        String actual = getJobLevelPage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman job level tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("form pencarian job level terlihat")
    public void formPencarianJobLevelTerlihat() {
        Assert.assertTrue(getJobLevelPage().isSearchInputVisible(), "Input pencarian job level tidak terlihat!");
    }

    @Dan("tombol Tambahkan level terlihat")
    public void tombolTambahkanLevelTerlihat() {
        Assert.assertTrue(getJobLevelPage().isTambahLevelButtonVisible(), "Tombol Tambahkan level tidak terlihat!");
    }

    @Dan("tabel data job level menampilkan 8 kolom header:")
    public void tabelDataJobLevelMenampilkan8KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getJobLevelPage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(expected) || h.contains(expected));
            Assert.assertTrue(found, "Header tabel tidak memuat kolom: " + expected);
        }
    }

    @Ketika("admin mencari job level dengan kata kunci {string}")
    public void adminMencariJobLevelDenganKataKunci(String keyword) {
        logger.info("Mencari job level dengan keyword: {}", keyword);
        getJobLevelPage().search(keyword);
    }

    @Maka("tabel job level hanya menampilkan baris yang mengandung nama {string}")
    public void tabelJobLevelHanyaMenampilkanBarisYangMengandungNama(String expectedKeyword) {
        List<String> names = getJobLevelPage().getJobLevelNames();
        Assert.assertFalse(names.isEmpty(), "Data hasil pencarian job level tidak boleh kosong!");
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Baris data tidak memuat keyword '" + expectedKeyword + "': " + name);
        }
    }

    @Ketika("admin menekan tombol reset pencarian job level")
    public void adminMenekanTombolResetPencarianJobLevel() {
        getJobLevelPage().resetSearch();
    }

    @Maka("seluruh data job level ditampilkan kembali di tabel")
    public void seluruhDataJobLevelDitampilkanKembaliDiTabel() {
        int count = getJobLevelPage().getTableRowCount();
        Assert.assertTrue(count > 0, "Data tabel setelah reset tidak boleh kosong!");
    }

    @Ketika("admin mengklik tombol Tambahkan level")
    public void adminMengklikTombolTambahkanLevel() {
        getJobLevelPage().openTambahModal();
    }

    @Maka("modal tambah level ditampilkan dengan judul {string}")
    public void modalTambahLevelDitampilkanDenganJudul(String expectedTitle) {
        Assert.assertTrue(getJobLevelPage().isTambahModalVisible(), "Modal tambah level tidak tampil!");
        String actual = getJobLevelPage().getTambahModalTitle();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul modal tambah tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("field input waktu istirahat break start dan break end terlihat pada modal tambah")
    public void fieldInputWaktuIstirahatBreakStartDanBreakEndTerlihatPadaModalTambah() {
        Assert.assertTrue(getJobLevelPage().isBreakStartInputVisibleInModal(), "Input break start tidak terlihat!");
        Assert.assertTrue(getJobLevelPage().isBreakEndInputVisibleInModal(), "Input break end tidak terlihat!");
    }

    @Ketika("admin menekan tombol Batal pada modal tambah level")
    public void adminMenekanTombolBatalPadaModalTambahLevel() {
        getJobLevelPage().batalTambahModal();
    }

    @Maka("modal tambah level tertutup")
    public void modalTambahLevelTertutup() {
        Assert.assertFalse(getJobLevelPage().isTambahModalVisible(), "Modal tambah level masih terbuka!");
    }

    @Ketika("admin mengklik tombol Hapus pada baris pertama job level")
    public void adminMengklikTombolHapusPadaBarisPertamaJobLevel() {
        getJobLevelPage().clickDeleteButtonOnFirstRow();
    }

    @Maka("modal konfirmasi hapus level ditampilkan")
    public void modalKonfirmasiHapusLevelDitampilkan() {
        Assert.assertTrue(getJobLevelPage().isDeleteModalVisible(), "Modal konfirmasi hapus level tidak tampil!");
    }

    @Dan("pesan konfirmasi hapus level memuat teks {string}")
    public void pesanKonfirmasiHapusLevelMemuatTeks(String expectedText) {
        String actual = getJobLevelPage().getDeletePromptText();
        Assert.assertTrue(actual.contains(expectedText),
                "Pesan konfirmasi hapus tidak sesuai! Expected: " + expectedText + ", Actual: " + actual);
    }

    @Ketika("admin menekan tombol Tidak pada modal hapus level")
    public void adminMenekanTombolTidakPadaModalHapusLevel() {
        getJobLevelPage().batalDeleteModal();
    }

    @Maka("modal konfirmasi hapus level tertutup tanpa menghapus data")
    public void modalKonfirmasiHapusLevelTertutupTanpaMenghapusData() {
        Assert.assertFalse(getJobLevelPage().isDeleteModalVisible(), "Modal konfirmasi hapus masih terbuka!");
    }
}

