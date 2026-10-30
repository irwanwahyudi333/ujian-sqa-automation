package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentCorrectionPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentCorrectionSteps {

    private AbsentCorrectionPage absentCorrectionPage;

    private AbsentCorrectionPage getAbsentCorrectionPage() {
        if (absentCorrectionPage == null) {
            absentCorrectionPage = new AbsentCorrectionPage(DriverManager.getDriver());
        }
        return absentCorrectionPage;
    }

    @Maka("Halaman absent correction harus tampil dengan benar")
    public void halamanAbsentCorrectionHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentCorrectionPage().isPageLoaded(), "Halaman Absent Correction gagal dimuat!");
    }

    @Dan("Tombol ajukan koreksi harus ditampilkan")
    public void tombolAjukanKoreksiHarusDitampilkan() {
        Assert.assertTrue(getAbsentCorrectionPage().isBtnAjukanKoreksiDisplayed(), "Tombol Ajukan Koreksi tidak tampil!");
    }

    @Ketika("Pengguna menekan tombol ajukan koreksi")
    public void penggunaMenekanTombolAjukanKoreksi() {
        getAbsentCorrectionPage().openModalAjukanKoreksi();
    }

    @Maka("Modal drawer form koreksi absen harus ditampilkan")
    public void modalDrawerFormKoreksiAbsenHarusDitampilkan() {
        Assert.assertTrue(getAbsentCorrectionPage().isModalDrawerDisplayed(), "Modal drawer Ajukan Koreksi tidak tampil!");
    }

    @Dan("Seluruh field formulir koreksi absen harus tersedia")
    public void seluruhFieldFormulirKoreksiAbsenHarusTersedia() {
        Assert.assertTrue(getAbsentCorrectionPage().areModalFormFieldsDisplayed(), "Field formulir modal koreksi tidak lengkap!");
    }
}

