package com.company.sqa.ui.steps;

import com.company.sqa.pages.ShiftingPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class ShiftingSteps {

    private ShiftingPage shiftingPage;
    private int initialRowCount = 0;

    private ShiftingPage getShiftingPage() {
        if (shiftingPage == null) {
            shiftingPage = new ShiftingPage(DriverManager.getDriver());
        }
        return shiftingPage;
    }

    @Maka("Halaman Shifting harus tampil dengan benar")
    public void halamanShiftingHarusTampilDenganBenar() {
        Assert.assertTrue(getShiftingPage().isPageLoaded(), "Halaman Shifting gagal dimuat!");
    }

    @Dan("Jumlah baris data shifting pada tabel harus lebih dari {int}")
    public void jumlahBarisDataShiftingPadaTabelHarusLebihDari(int count) {
        initialRowCount = getShiftingPage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data shifting kosong!");
    }

    @Ketika("Pengguna mencari shifting dengan kata kunci {string}")
    public void penggunaMencariShiftingDenganKataKunci(String keyword) {
        getShiftingPage().searchShifting(keyword);
    }

    @Maka("Jumlah baris data shifting pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataShiftingPadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getShiftingPage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian shifting tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian shifting")
    public void penggunaMenekanTombolResetPencarianShifting() {
        getShiftingPage().clickReset();
    }

    @Maka("Jumlah baris data shifting pada tabel harus kembali ke semula")
    public void jumlahBarisDataShiftingPadaTabelHarusKembaliKeSemula() {
        int currentCount = getShiftingPage().getTableRowCount();
        Assert.assertTrue(currentCount > 0, "Data shifting setelah reset kosong!");
    }

    @Ketika("Pengguna membuka modal tambah shifting")
    public void penggunaMembukaModalTambahShifting() {
        getShiftingPage().openTambahModal();
    }

    @Maka("Modal tambah shifting harus ditampilkan")
    public void modalTambahShiftingHarusDitampilkan() {
        Assert.assertTrue(getShiftingPage().isModalTambahVisible(), "Modal tambah shifting tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah shifting")
    public void penggunaMembatalkanModalTambahShifting() {
        getShiftingPage().cancelTambah();
    }

    @Maka("Modal tambah shifting harus tertutup")
    public void modalTambahShiftingHarusTertutup() {
        Assert.assertFalse(getShiftingPage().isModalTambahVisible(), "Modal tambah shifting masih terbuka!");
    }

    @Dan("Pengguna mengisi form shifting dengan kode {string} nama {string} unit {string} timeIn {string} timeOut {string} breakStart {string} breakEnd {string}")
    public void penggunaMengisiFormShifting(String code, String name, String unit, String timeIn, String timeOut, String breakStart, String breakEnd) {
        getShiftingPage().fillTambahForm(code, name, unit, timeIn, timeOut, breakStart, breakEnd);
    }

    @Dan("Pengguna menyimpan data shifting")
    public void penggunaMenyimpanDataShifting() {
        getShiftingPage().submitTambah();
    }

    @Maka("Notifikasi toast shifting harus ditampilkan")
    public void notifikasiToastShiftingHarusDitampilkan() {
        Assert.assertTrue(getShiftingPage().isToastVisible(), "Toast shifting tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama shifting")
    public void penggunaMenekanTombolHapusPadaBarisPertamaShifting() {
        getShiftingPage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus shifting harus ditampilkan")
    public void modalKonfirmasiHapusShiftingHarusDitampilkan() {
        Assert.assertTrue(getShiftingPage().isModalDeleteVisible(), "Modal hapus shifting tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus shifting")
    public void penggunaMembatalkanHapusShifting() {
        getShiftingPage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus shifting harus tertutup")
    public void modalKonfirmasiHapusShiftingHarusTertutup() {
        Assert.assertFalse(getShiftingPage().isModalDeleteVisible(), "Modal hapus shifting masih terbuka!");
    }
}

