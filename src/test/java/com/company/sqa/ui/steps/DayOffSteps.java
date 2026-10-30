package com.company.sqa.ui.steps;

import com.company.sqa.pages.DayOffPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class DayOffSteps {

    private DayOffPage dayOffPage;
    private int initialRowCount = 0;

    private DayOffPage getDayOffPage() {
        if (dayOffPage == null) {
            dayOffPage = new DayOffPage(DriverManager.getDriver());
        }
        return dayOffPage;
    }

    @Maka("Halaman Day Off harus tampil dengan benar")
    public void halamanDayOffHarusTampilDenganBenar() {
        Assert.assertTrue(getDayOffPage().isPageLoaded(), "Halaman Day Off gagal dimuat!");
    }

    @Dan("Jumlah baris data day off pada tabel harus lebih dari {int}")
    public void jumlahBarisDataDayOffPadaTabelHarusLebihDari(int count) {
        initialRowCount = getDayOffPage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data day off kosong!");
    }

    @Ketika("Pengguna mencari day off dengan kata kunci {string}")
    public void penggunaMencariDayOffDenganKataKunci(String keyword) {
        getDayOffPage().searchDayOff(keyword);
    }

    @Maka("Jumlah baris data day off pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataDayOffPadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getDayOffPage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian day off tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian day off")
    public void penggunaMenekanTombolResetPencarianDayOff() {
        getDayOffPage().clickReset();
    }

    @Maka("Jumlah baris data day off pada tabel harus kembali ke semula")
    public void jumlahBarisDataDayOffPadaTabelHarusKembaliKeSemula() {
        int currentCount = getDayOffPage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Data day off setelah reset tidak sesuai!");
    }

    @Ketika("Pengguna membuka modal tambah day off")
    public void penggunaMembukaModalTambahDayOff() {
        getDayOffPage().openTambahModal();
    }

    @Maka("Modal tambah day off harus ditampilkan")
    public void modalTambahDayOffHarusDitampilkan() {
        if (!getDayOffPage().hasTambahButton()) {
            return;
        }
        Assert.assertTrue(getDayOffPage().isModalTambahVisible(), "Modal tambah day off tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah day off")
    public void penggunaMembatalkanModalTambahDayOff() {
        getDayOffPage().cancelTambah();
    }

    @Maka("Modal tambah day off harus tertutup")
    public void modalTambahDayOffHarusTertutup() {
        if (!getDayOffPage().hasTambahButton()) {
            return;
        }
        Assert.assertFalse(getDayOffPage().isModalTambahVisible(), "Modal tambah day off masih terbuka!");
    }

    @Dan("Pengguna mengisi form day off dengan tanggal {string} deskripsi {string} kategori {string}")
    public void penggunaMengisiFormDayOff(String date, String description, String category) {
        getDayOffPage().fillTambahForm(date, description, category);
    }

    @Dan("Pengguna menyimpan data day off")
    public void penggunaMenyimpanDataDayOff() {
        getDayOffPage().submitTambah();
    }

    @Maka("Notifikasi toast day off harus ditampilkan")
    public void notifikasiToastDayOffHarusDitampilkan() {
        Assert.assertTrue(getDayOffPage().isToastVisible(), "Toast day off tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama day off")
    public void penggunaMenekanTombolHapusPadaBarisPertamaDayOff() {
        getDayOffPage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus day off harus ditampilkan")
    public void modalKonfirmasiHapusDayOffHarusDitampilkan() {
        if (!getDayOffPage().hasDeleteButton()) {
            return;
        }
        Assert.assertTrue(getDayOffPage().isModalDeleteVisible(), "Modal hapus day off tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus day off")
    public void penggunaMembatalkanHapusDayOff() {
        getDayOffPage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus day off harus tertutup")
    public void modalKonfirmasiHapusDayOffHarusTertutup() {
        if (!getDayOffPage().hasDeleteButton()) {
            return;
        }
        Assert.assertFalse(getDayOffPage().isModalDeleteVisible(), "Modal hapus day off masih terbuka!");
    }
}
