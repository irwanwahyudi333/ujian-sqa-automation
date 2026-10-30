package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentLeavePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentLeaveSteps {

    private AbsentLeavePage absentLeavePage;

    private AbsentLeavePage getAbsentLeavePage() {
        if (absentLeavePage == null) {
            absentLeavePage = new AbsentLeavePage(DriverManager.getDriver());
        }
        return absentLeavePage;
    }

    @Maka("Halaman absent leave harus tampil dengan benar")
    public void halamanAbsentLeaveHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentLeavePage().isPageLoaded(), "Halaman Absent Leave gagal dimuat!");
    }

    @Dan("Tombol ajukan cuti harus ditampilkan")
    public void tombolAjukanCutiHarusDitampilkan() {
        Assert.assertTrue(getAbsentLeavePage().isBtnAjukanCutiDisplayed(), "Tombol Ajukan Cuti tidak tampil!");
    }

    @Ketika("Pengguna menekan tombol ajukan cuti")
    public void penggunaMenekanTombolAjukanCuti() {
        getAbsentLeavePage().openModalAjukanCuti();
    }

    @Maka("Modal drawer form cuti harus ditampilkan")
    public void modalDrawerFormCutiHarusDitampilkan() {
        Assert.assertTrue(getAbsentLeavePage().isModalDrawerDisplayed(), "Modal drawer Ajukan Cuti tidak tampil!");
    }

    @Dan("Seluruh field formulir cuti harus tersedia")
    public void seluruhFieldFormulirCutiHarusTersedia() {
        Assert.assertTrue(getAbsentLeavePage().areModalFormFieldsDisplayed(), "Field formulir modal cuti tidak lengkap!");
    }
}

