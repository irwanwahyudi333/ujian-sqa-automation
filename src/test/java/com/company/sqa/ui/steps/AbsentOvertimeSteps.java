package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentOvertimePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentOvertimeSteps {

    private AbsentOvertimePage absentOvertimePage;

    private AbsentOvertimePage getAbsentOvertimePage() {
        if (absentOvertimePage == null) {
            absentOvertimePage = new AbsentOvertimePage(DriverManager.getDriver());
        }
        return absentOvertimePage;
    }

    @Maka("Halaman absent overtime harus tampil dengan benar")
    public void halamanAbsentOvertimeHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentOvertimePage().isPageLoaded(), "Halaman Absent Overtime gagal dimuat!");
    }

    @Dan("Tombol ajukan lembur harus ditampilkan")
    public void tombolAjukanLemburHarusDitampilkan() {
        Assert.assertTrue(getAbsentOvertimePage().isBtnAjukanLemburDisplayed(), "Tombol Ajukan Lembur tidak tampil!");
    }

    @Ketika("Pengguna menekan tombol ajukan lembur")
    public void penggunaMenekanTombolAjukanLembur() {
        getAbsentOvertimePage().openModalAjukanLembur();
    }

    @Maka("Modal drawer form lembur harus ditampilkan")
    public void modalDrawerFormLemburHarusDitampilkan() {
        Assert.assertTrue(getAbsentOvertimePage().isModalDrawerDisplayed(), "Modal drawer Ajukan Lembur tidak tampil!");
    }

    @Dan("Seluruh field formulir lembur harus tersedia")
    public void seluruhFieldFormulirLemburHarusTersedia() {
        Assert.assertTrue(getAbsentOvertimePage().areModalFormFieldsDisplayed(), "Field formulir modal lembur tidak lengkap!");
    }
}

