package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentPermitPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentPermitSteps {

    private AbsentPermitPage absentPermitPage;

    private AbsentPermitPage getAbsentPermitPage() {
        if (absentPermitPage == null) {
            absentPermitPage = new AbsentPermitPage(DriverManager.getDriver());
        }
        return absentPermitPage;
    }

    @Maka("Halaman absent permit harus tampil dengan benar")
    public void halamanAbsentPermitHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentPermitPage().isPageLoaded(), "Halaman Absent Permit gagal dimuat!");
    }

    @Dan("Seluruh tab kategori izin terlambat pulang cepat dan izin off harus ditampilkan")
    public void seluruhTabKategoriIzinTerlambatPulangCepatDanIzinOffHarusDitampilkan() {
        Assert.assertTrue(getAbsentPermitPage().areTabsDisplayed(), "Tab kategori izin tidak lengkap!");
    }

    @Ketika("Pengguna berpindah ke tab izin {string}")
    public void penggunaBerpindahKeTabIzin(String tabName) {
        if (tabName.equalsIgnoreCase("Pulang Cepat")) {
            getAbsentPermitPage().clickTabPulangCepat();
        } else if (tabName.equalsIgnoreCase("Izin Off")) {
            getAbsentPermitPage().clickTabIzinOff();
        } else {
            getAbsentPermitPage().clickTabTerlambat();
        }
    }

    @Maka("Tombol ajukan izin harus ditampilkan")
    public void tombolAjukanIzinHarusDitampilkan() {
        Assert.assertTrue(getAbsentPermitPage().isBtnAjukanIzinDisplayed(), "Tombol Ajukan Izin tidak tampil!");
    }

    @Ketika("Pengguna menekan tombol ajukan izin")
    public void penggunaMenekanTombolAjukanIzin() {
        getAbsentPermitPage().openModalAjukanIzin();
    }

    @Maka("Modal drawer form izin harus ditampilkan")
    public void modalDrawerFormIzinHarusDitampilkan() {
        Assert.assertTrue(getAbsentPermitPage().isModalDrawerDisplayed(), "Modal drawer Ajukan Izin tidak tampil!");
    }

    @Dan("Seluruh field formulir izin harus tersedia")
    public void seluruhFieldFormulirIzinHarusTersedia() {
        Assert.assertTrue(getAbsentPermitPage().areModalFormFieldsDisplayed(), "Field formulir modal izin tidak lengkap!");
    }
}

