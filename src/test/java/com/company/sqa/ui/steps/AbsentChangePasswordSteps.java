package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentChangePasswordPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentChangePasswordSteps {

    private AbsentChangePasswordPage absentChangePasswordPage;

    private AbsentChangePasswordPage getAbsentChangePasswordPage() {
        if (absentChangePasswordPage == null) {
            absentChangePasswordPage = new AbsentChangePasswordPage(DriverManager.getDriver());
        }
        return absentChangePasswordPage;
    }

    @Maka("Halaman absent change password harus tampil dengan benar")
    public void halamanAbsentChangePasswordHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentChangePasswordPage().isPageLoaded(), "Halaman Absent Change Password gagal dimuat!");
    }

    @Dan("Tombol kembali pada change password harus ditampilkan")
    public void tombolKembaliPadaChangePasswordHarusDitampilkan() {
        Assert.assertTrue(getAbsentChangePasswordPage().isBtnBackDisplayed(), "Tombol Kembali pada change password tidak tampil!");
    }

    @Dan("Seluruh field formulir change password harus tersedia")
    public void seluruhFieldFormulirChangePasswordHarusTersedia() {
        Assert.assertTrue(getAbsentChangePasswordPage().areFormFieldsDisplayed(), "Field formulir change password tidak lengkap!");
    }

    @Ketika("Pengguna memasukkan password baru {string}")
    public void penggunaMemasukkanPasswordBaru(String password) {
        getAbsentChangePasswordPage().enterNewPassword(password);
    }

    @Dan("Pengguna menekan toggle visibilitas password baru")
    public void penggunaMenekanToggleVisibilitasPasswordBaru() {
        getAbsentChangePasswordPage().clickToggleNewPassword();
    }

    @Dan("Pengguna menekan kembali toggle visibilitas password baru")
    public void penggunaMenekanKembaliToggleVisibilitasPasswordBaru() {
        getAbsentChangePasswordPage().clickToggleNewPassword();
    }

    @Maka("Tipe input password baru harus berubah menjadi {string}")
    public void tipeInputPasswordBaruHarusBerubahMenjadi(String expectedType) {
        String actualType = getAbsentChangePasswordPage().getNewPasswordInputType();
        Assert.assertEquals(actualType, expectedType, "Tipe input password tidak sesuai!");
    }
}
