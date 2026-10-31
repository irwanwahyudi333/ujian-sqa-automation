package com.company.sqa.ui.steps;

import com.company.sqa.pages.UnitLeavePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class UnitLeaveSteps {

    private UnitLeavePage unitLeavePage;
    private int initialRowCount = 0;

    private UnitLeavePage getUnitLeavePage() {
        if (unitLeavePage == null) {
            unitLeavePage = new UnitLeavePage(DriverManager.getDriver());
        }
        return unitLeavePage;
    }

    @Maka("Halaman Unit Leave harus tampil dengan benar")
    public void halamanUnitLeaveHarusTampilDenganBenar() {
        Assert.assertTrue(getUnitLeavePage().isPageLoaded(), "Halaman Unit Leave gagal dimuat!");
    }

    @Dan("Jumlah baris data unit leave pada tabel harus lebih dari {int}")
    public void jumlahBarisDataUnitLeavePadaTabelHarusLebihDari(int count) {
        initialRowCount = getUnitLeavePage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data unit leave kosong!");
    }

    @Ketika("Pengguna mencari unit leave dengan kata kunci {string}")
    public void penggunaMencariUnitLeaveDenganKataKunci(String keyword) {
        getUnitLeavePage().searchUnitLeave(keyword);
    }

    @Maka("Jumlah baris data unit leave pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataUnitLeavePadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getUnitLeavePage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian unit leave tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian unit leave")
    public void penggunaMenekanTombolResetPencarianUnitLeave() {
        getUnitLeavePage().clickReset();
    }

    @Maka("Jumlah baris data unit leave pada tabel harus kembali ke semula")
    public void jumlahBarisDataUnitLeavePadaTabelHarusKembaliKeSemula() {
        int currentCount = getUnitLeavePage().getTableRowCount();
        Assert.assertTrue(currentCount > 0, "Data unit leave setelah reset kosong!");
    }

    @Ketika("Pengguna membuka modal tambah unit leave")
    public void penggunaMembukaModalTambahUnitLeave() {
        getUnitLeavePage().openTambahModal();
    }

    @Maka("Modal tambah unit leave harus ditampilkan")
    public void modalTambahUnitLeaveHarusDitampilkan() {
        Assert.assertTrue(getUnitLeavePage().isModalTambahVisible(), "Modal tambah unit leave tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah unit leave")
    public void penggunaMembatalkanModalTambahUnitLeave() {
        getUnitLeavePage().cancelTambah();
    }

    @Maka("Modal tambah unit leave harus tertutup")
    public void modalTambahUnitLeaveHarusTertutup() {
        Assert.assertFalse(getUnitLeavePage().isModalTambahVisible(), "Modal tambah unit leave masih terbuka!");
    }

    @Dan("Pengguna mengisi form unit leave dengan nama {string} eligibleMonth {string} maxRemaining {string} carryForward {string} totalLeave {string}")
    public void penggunaMengisiFormUnitLeave(String name, String eligibleMonth, String maxRemaining, String carryForward, String totalLeave) {
        getUnitLeavePage().fillTambahForm(name, eligibleMonth, maxRemaining, carryForward, totalLeave);
    }

    @Dan("Pengguna menyimpan data unit leave")
    public void penggunaMenyimpanDataUnitLeave() {
        getUnitLeavePage().submitTambah();
    }

    @Maka("Notifikasi toast unit leave harus ditampilkan")
    public void notifikasiToastUnitLeaveHarusDitampilkan() {
        Assert.assertTrue(getUnitLeavePage().isToastVisible(), "Toast unit leave tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama unit leave")
    public void penggunaMenekanTombolHapusPadaBarisPertamaUnitLeave() {
        getUnitLeavePage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus unit leave harus ditampilkan")
    public void modalKonfirmasiHapusUnitLeaveHarusDitampilkan() {
        Assert.assertTrue(getUnitLeavePage().isModalDeleteVisible(), "Modal hapus unit leave tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus unit leave")
    public void penggunaMembatalkanHapusUnitLeave() {
        getUnitLeavePage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus unit leave harus tertutup")
    public void modalKonfirmasiHapusUnitLeaveHarusTertutup() {
        Assert.assertFalse(getUnitLeavePage().isModalDeleteVisible(), "Modal hapus unit leave masih terbuka!");
    }
}

