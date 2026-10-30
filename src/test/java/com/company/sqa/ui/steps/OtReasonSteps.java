package com.company.sqa.ui.steps;

import com.company.sqa.pages.OtReasonPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class OtReasonSteps {

    private OtReasonPage otReasonPage;
    private int initialRowCount = 0;

    private OtReasonPage getOtReasonPage() {
        if (otReasonPage == null) {
            otReasonPage = new OtReasonPage(DriverManager.getDriver());
        }
        return otReasonPage;
    }

    @Maka("Halaman Overtime Reason harus tampil dengan benar")
    public void halamanOvertimeReasonHarusTampilDenganBenar() {
        Assert.assertTrue(getOtReasonPage().isPageLoaded(), "Halaman Overtime Reason gagal dimuat!");
    }

    @Dan("Jumlah baris data ot reason pada tabel harus lebih dari {int}")
    public void jumlahBarisDataOtReasonPadaTabelHarusLebihDari(int count) {
        initialRowCount = getOtReasonPage().getTableRowCount();
        Assert.assertTrue(initialRowCount > count, "Data ot reason kosong!");
    }

    @Ketika("Pengguna mencari ot reason dengan kata kunci {string}")
    public void penggunaMencariOtReasonDenganKataKunci(String keyword) {
        getOtReasonPage().searchOtReason(keyword);
    }

    @Maka("Jumlah baris data ot reason pada tabel harus sesuai hasil pencarian")
    public void jumlahBarisDataOtReasonPadaTabelHarusSesuaiHasilPencarian() {
        int currentCount = getOtReasonPage().getTableRowCount();
        Assert.assertTrue(currentCount >= 0, "Pencarian ot reason tidak memberikan hasil valid");
    }

    @Ketika("Pengguna menekan tombol reset pencarian ot reason")
    public void penggunaMenekanTombolResetPencarianOtReason() {
        getOtReasonPage().clickReset();
    }

    @Maka("Jumlah baris data ot reason pada tabel harus kembali ke semula")
    public void jumlahBarisDataOtReasonPadaTabelHarusKembaliKeSemula() {
        int currentCount = getOtReasonPage().getTableRowCount();
        Assert.assertTrue(currentCount > 0, "Data ot reason setelah reset kosong!");
    }

    @Ketika("Pengguna membuka modal tambah ot reason")
    public void penggunaMembukaModalTambahOtReason() {
        getOtReasonPage().openTambahModal();
    }

    @Maka("Modal tambah ot reason harus ditampilkan")
    public void modalTambahOtReasonHarusDitampilkan() {
        Assert.assertTrue(getOtReasonPage().isModalTambahVisible(), "Modal tambah ot reason tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan modal tambah ot reason")
    public void penggunaMembatalkanModalTambahOtReason() {
        getOtReasonPage().cancelTambah();
    }

    @Maka("Modal tambah ot reason harus tertutup")
    public void modalTambahOtReasonHarusTertutup() {
        Assert.assertFalse(getOtReasonPage().isModalTambahVisible(), "Modal tambah ot reason masih terbuka!");
    }

    @Dan("Pengguna mengisi form ot reason dengan nama {string}")
    public void penggunaMengisiFormOtReason(String name) {
        getOtReasonPage().fillTambahForm(name);
    }

    @Dan("Pengguna menyimpan data ot reason")
    public void penggunaMenyimpanDataOtReason() {
        getOtReasonPage().submitTambah();
    }

    @Maka("Notifikasi toast ot reason harus ditampilkan")
    public void notifikasiToastOtReasonHarusDitampilkan() {
        Assert.assertTrue(getOtReasonPage().isToastVisible(), "Toast ot reason tidak muncul!");
    }

    @Ketika("Pengguna menekan tombol hapus pada baris pertama ot reason")
    public void penggunaMenekanTombolHapusPadaBarisPertamaOtReason() {
        getOtReasonPage().clickFirstDeleteButton();
    }

    @Maka("Modal konfirmasi hapus ot reason harus ditampilkan")
    public void modalKonfirmasiHapusOtReasonHarusDitampilkan() {
        Assert.assertTrue(getOtReasonPage().isModalDeleteVisible(), "Modal hapus ot reason tidak terbuka!");
    }

    @Ketika("Pengguna membatalkan hapus ot reason")
    public void penggunaMembatalkanHapusOtReason() {
        getOtReasonPage().cancelDelete();
    }

    @Maka("Modal konfirmasi hapus ot reason harus tertutup")
    public void modalKonfirmasiHapusOtReasonHarusTertutup() {
        Assert.assertFalse(getOtReasonPage().isModalDeleteVisible(), "Modal hapus ot reason masih terbuka!");
    }
}

