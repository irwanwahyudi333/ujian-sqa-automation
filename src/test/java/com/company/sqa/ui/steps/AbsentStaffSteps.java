package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentStaffPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentStaffSteps {

    private AbsentStaffPage absentStaffPage;

    private AbsentStaffPage getAbsentStaffPage() {
        if (absentStaffPage == null) {
            absentStaffPage = new AbsentStaffPage(DriverManager.getDriver());
        }
        return absentStaffPage;
    }

    @Maka("Halaman absent staff harus tampil dengan benar")
    public void halamanAbsentStaffHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentStaffPage().isPageLoaded(), "Halaman Absent Staff gagal dimuat!");
    }

    @Dan("Tombol kembali pada halaman staff harus ditampilkan")
    public void tombolKembaliPadaHalamanStaffHarusDitampilkan() {
        Assert.assertTrue(getAbsentStaffPage().isBtnBackDisplayed(), "Tombol Kembali pada halaman staff tidak tampil!");
    }
}

