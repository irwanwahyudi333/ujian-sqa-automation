package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentActivityPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentActivitySteps {

    private AbsentActivityPage absentActivityPage;

    private AbsentActivityPage getAbsentActivityPage() {
        if (absentActivityPage == null) {
            absentActivityPage = new AbsentActivityPage(DriverManager.getDriver());
        }
        return absentActivityPage;
    }

    @Maka("Halaman absent activity harus tampil dengan benar")
    public void halamanAbsentActivityHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentActivityPage().isPageHeaderTitleDisplayed(), "Judul header History Absensi tidak tampil!");
    }

    @Dan("Tombol kembali pada history absensi harus ditampilkan")
    public void tombolKembaliPadaHistoryAbsensiHarusDitampilkan() {
        Assert.assertTrue(getAbsentActivityPage().isBtnBackDisplayed(), "Tombol kembali pada history absensi tidak tampil!");
    }

    @Maka("Daftar kartu riwayat absensi harus ditampilkan dengan data valid")
    public void daftarKartuRiwayatAbsensiHarusDitampilkanDenganDataValid() {
        Assert.assertTrue(getAbsentActivityPage().areActivityCardsDisplayed(), "Kartu riwayat absensi tidak tampil!");
    }

    @Dan("Jumlah kartu riwayat absensi harus lebih dari {int}")
    public void jumlahKartuRiwayatAbsensiHarusLebihDari(int count) {
        int actual = getAbsentActivityPage().getActivityCardsCount();
        Assert.assertTrue(actual > count, "Jumlah kartu riwayat absensi " + actual + " tidak lebih dari " + count);
    }
}

