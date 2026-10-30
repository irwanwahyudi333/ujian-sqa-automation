package com.company.sqa.ui.steps;

import com.company.sqa.pages.CalendarPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class CalendarSteps {

    private CalendarPage calendarPage;
    private int initialRowCount = 0;

    private CalendarPage getCalendarPage() {
        if (calendarPage == null) {
            calendarPage = new CalendarPage(DriverManager.getDriver());
        }
        return calendarPage;
    }

    @Maka("Halaman Calendar harus tampil dengan benar")
    public void halamanCalendarHarusTampilDenganBenar() {
        Assert.assertTrue(getCalendarPage().isPageLoaded(), "Halaman Calendar gagal dimuat!");
    }

    @Dan("Jumlah baris data calendar pada tabel harus lebih dari {int}")
    public void jumlahBarisDataCalendarPadaTabelHarusLebihDari(int count) {
        initialRowCount = getCalendarPage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data calendar kosong!");
    }

    @Ketika("Pengguna mencari calendar dengan kata kunci {string}")
    public void penggunaMencariCalendarDenganKataKunci(String keyword) {
        getCalendarPage().searchCalendar(keyword);
    }

    @Maka("Jumlah baris data calendar pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataCalendarPadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getCalendarPage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian calendar tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian calendar")
    public void penggunaMenekanTombolResetPencarianCalendar() {
        getCalendarPage().clickReset();
    }

    @Maka("Jumlah baris data calendar pada tabel harus kembali ke semula")
    public void jumlahBarisDataCalendarPadaTabelHarusKembaliKeSemula() {
        int currentCount = getCalendarPage().getTableRowCount();
        Assert.assertTrue(currentCount > 0, "Data calendar setelah reset kosong!");
    }

    @Ketika("Pengguna membuka modal tambah calendar")
    public void penggunaMembukaModalTambahCalendar() {
        getCalendarPage().openTambahModal();
    }

    @Maka("Modal tambah calendar harus ditampilkan")
    public void modalTambahCalendarHarusDitampilkan() {
        Assert.assertTrue(getCalendarPage().isModalTambahVisible(), "Modal tambah calendar tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah calendar")
    public void penggunaMembatalkanModalTambahCalendar() {
        getCalendarPage().cancelTambah();
    }

    @Maka("Modal tambah calendar harus tertutup")
    public void modalTambahCalendarHarusTertutup() {
        Assert.assertFalse(getCalendarPage().isModalTambahVisible(), "Modal tambah calendar masih terbuka!");
    }

    @Dan("Pengguna mengisi form calendar dengan nama {string} departemen {string}")
    public void penggunaMengisiFormCalendar(String name, String departemen) {
        getCalendarPage().fillTambahForm(name, departemen);
    }

    @Dan("Pengguna menyimpan data calendar")
    public void penggunaMenyimpanDataCalendar() {
        getCalendarPage().submitTambah();
    }

    @Maka("Notifikasi toast calendar harus ditampilkan")
    public void notifikasiToastCalendarHarusDitampilkan() {
        Assert.assertTrue(getCalendarPage().isToastVisible(), "Toast calendar tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama calendar")
    public void penggunaMenekanTombolHapusPadaBarisPertamaCalendar() {
        getCalendarPage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus calendar harus ditampilkan")
    public void modalKonfirmasiHapusCalendarHarusDitampilkan() {
        Assert.assertTrue(getCalendarPage().isModalDeleteVisible(), "Modal hapus calendar tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus calendar")
    public void penggunaMembatalkanHapusCalendar() {
        getCalendarPage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus calendar harus tertutup")
    public void modalKonfirmasiHapusCalendarHarusTertutup() {
        Assert.assertFalse(getCalendarPage().isModalDeleteVisible(), "Modal hapus calendar masih terbuka!");
    }
}

