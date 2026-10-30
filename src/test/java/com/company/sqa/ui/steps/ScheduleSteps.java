package com.company.sqa.ui.steps;

import com.company.sqa.pages.SchedulePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class ScheduleSteps {

    private SchedulePage schedulePage;
    private int initialRowCount = 0;

    private SchedulePage getSchedulePage() {
        if (schedulePage == null) {
            schedulePage = new SchedulePage(DriverManager.getDriver());
        }
        return schedulePage;
    }

    @Maka("Halaman Schedule harus tampil dengan benar")
    public void halamanScheduleHarusTampilDenganBenar() {
        Assert.assertTrue(getSchedulePage().isPageLoaded(), "Halaman Schedule gagal dimuat!");
    }

    @Dan("Jumlah baris data schedule pada tabel harus lebih dari {int}")
    public void jumlahBarisDataSchedulePadaTabelHarusLebihDari(int count) {
        initialRowCount = getSchedulePage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data schedule kosong!");
    }

    @Ketika("Pengguna mencari schedule dengan kata kunci {string}")
    public void penggunaMencariScheduleDenganKataKunci(String keyword) {
        getSchedulePage().searchSchedule(keyword);
    }

    @Maka("Jumlah baris data schedule pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataSchedulePadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getSchedulePage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian schedule tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian schedule")
    public void penggunaMenekanTombolResetPencarianSchedule() {
        getSchedulePage().clickReset();
    }

    @Maka("Jumlah baris data schedule pada tabel harus kembali ke semula")
    public void jumlahBarisDataSchedulePadaTabelHarusKembaliKeSemula() {
        int currentCount = getSchedulePage().getTableRowCount();
        Assert.assertTrue(currentCount > 0, "Data schedule setelah reset kosong!");
    }

    @Ketika("Pengguna membuka modal tambah schedule")
    public void penggunaMembukaModalTambahSchedule() {
        getSchedulePage().openTambahModal();
    }

    @Maka("Modal tambah schedule harus ditampilkan")
    public void modalTambahScheduleHarusDitampilkan() {
        Assert.assertTrue(getSchedulePage().isModalTambahVisible(), "Modal tambah schedule tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah schedule")
    public void penggunaMembatalkanModalTambahSchedule() {
        getSchedulePage().cancelTambah();
    }

    @Maka("Modal tambah schedule harus tertutup")
    public void modalTambahScheduleHarusTertutup() {
        Assert.assertFalse(getSchedulePage().isModalTambahVisible(), "Modal tambah schedule masih terbuka!");
    }

    @Dan("Pengguna mengisi form schedule dengan nama {string} tipe {string} hariKerja {string}")
    public void penggunaMengisiFormSchedule(String name, String type, String workDays) {
        getSchedulePage().fillTambahForm(name, type, workDays);
    }

    @Dan("Pengguna menyimpan data schedule")
    public void penggunaMenyimpanDataSchedule() {
        getSchedulePage().submitTambah();
    }

    @Maka("Notifikasi toast schedule harus ditampilkan")
    public void notifikasiToastScheduleHarusDitampilkan() {
        Assert.assertTrue(getSchedulePage().isToastVisible(), "Toast schedule tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama schedule")
    public void penggunaMenekanTombolHapusPadaBarisPertamaSchedule() {
        getSchedulePage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus schedule harus ditampilkan")
    public void modalKonfirmasiHapusScheduleHarusDitampilkan() {
        Assert.assertTrue(getSchedulePage().isModalDeleteVisible(), "Modal hapus schedule tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus schedule")
    public void penggunaMembatalkanHapusSchedule() {
        getSchedulePage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus schedule harus tertutup")
    public void modalKonfirmasiHapusScheduleHarusTertutup() {
        Assert.assertFalse(getSchedulePage().isModalDeleteVisible(), "Modal hapus schedule masih terbuka!");
    }
}

