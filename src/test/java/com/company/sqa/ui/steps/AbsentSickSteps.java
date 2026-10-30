package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentSickPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentSickSteps {

    private AbsentSickPage absentSickPage;

    private AbsentSickPage getAbsentSickPage() {
        if (absentSickPage == null) {
            absentSickPage = new AbsentSickPage(DriverManager.getDriver());
        }
        return absentSickPage;
    }

    @Maka("Halaman absent sick harus tampil dengan benar")
    public void halamanAbsentSickHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentSickPage().isPageLoaded(), "Halaman Absent Sick gagal dimuat!");
    }

    @Dan("Tombol ajukan sakit harus ditampilkan")
    public void tombolAjukanSakitHarusDitampilkan() {
        Assert.assertTrue(getAbsentSickPage().isBtnAjukanSakitDisplayed(), "Tombol Ajukan Sakit tidak tampil!");
    }

    @Dan("Thumbnail bukti surat sakit harus ditampilkan")
    public void thumbnailBuktiSuratSakitHarusDitampilkan() {
        Assert.assertTrue(getAbsentSickPage().areThumbnailsDisplayed(), "Thumbnail bukti surat sakit tidak tampil!");
    }

    @Ketika("Pengguna menekan tombol ajukan sakit")
    public void penggunaMenekanTombolAjukanSakit() {
        getAbsentSickPage().openModalAjukanSakit();
    }

    @Maka("Modal drawer form sakit harus ditampilkan")
    public void modalDrawerFormSakitHarusDitampilkan() {
        Assert.assertTrue(getAbsentSickPage().isModalDrawerDisplayed(), "Modal drawer Ajukan Sakit tidak tampil!");
    }

    @Dan("Seluruh field formulir sakit dan upload berkas harus tersedia")
    public void seluruhFieldFormulirSakitDanUploadBerkasHarusTersedia() {
        Assert.assertTrue(getAbsentSickPage().areModalFormFieldsDisplayed(), "Field formulir modal sakit tidak lengkap!");
    }
}

