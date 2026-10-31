package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentHomePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.testng.Assert;

public class AbsentHomeSteps {

    private AbsentHomePage absentHomePage;

    private AbsentHomePage getAbsentHomePage() {
        if (absentHomePage == null) {
            absentHomePage = new AbsentHomePage(DriverManager.getDriver());
        }
        return absentHomePage;
    }

    @Maka("Halaman absent home harus tampil dengan benar")
    public void halamanAbsentHomeHarusTampilDenganBenar() {
        Assert.assertTrue(getAbsentHomePage().isUserGreetingDisplayed(), "Sapaan pengguna pada Absent Home tidak tampil!");
    }

    @Dan("Sapaan dan subjudul motivasi pengguna harus ditampilkan")
    public void sapaanDanSubjudulMotivasiPenggunaHarusDitampilkan() {
        Assert.assertTrue(getAbsentHomePage().isSubtitleDisplayed(), "Subjudul motivasi tidak tampil!");
    }

    @Dan("Seluruh kartu metrik kehadiran bulanan harus ditampilkan")
    public void seluruhKartuMetrikKehadiranBulananHarusDitampilkan() {
        Assert.assertTrue(getAbsentHomePage().areAttendanceMetricsDisplayed(), "Kartu metrik kehadiran tidak lengkap!");
    }

    @Maka("Seluruh ikon menu pintas presensi harus ditampilkan")
    public void seluruhIkonMenuPintasPresensiHarusDitampilkan() {
        Assert.assertTrue(getAbsentHomePage().areQuickMenusDisplayed(), "Ikon menu pintas presensi tidak lengkap!");
    }

    @Dan("Bagian history absensi singkat harus ditampilkan")
    public void bagianHistoryAbsensiSingkatHarusDitampilkan() {
        Assert.assertTrue(getAbsentHomePage().isHistorySectionDisplayed(), "Bagian history absensi singkat tidak tampil!");
    }

    @Dan("Tombol absen masuk harus ditampilkan")
    public void tombolAbsenMasukHarusDitampilkan() {
        Assert.assertTrue(getAbsentHomePage().isBtnAbsenMasukDisplayed(), "Tombol Absen Masuk tidak tampil!");
    }

    @Ketika("Pengguna menekan tautan selengkapnya pada history absensi")
    public void penggunaMenekanTautanSelengkapnyaPadaHistoryAbsensi() {
        getAbsentHomePage().clickHistoryMore();
    }

    @Maka("Pengguna harus diarahkan ke halaman history absensi")
    public void penggunaHarusDiarahkanKeHalamanHistoryAbsensi() {
        Assert.assertTrue(getAbsentHomePage().waitForUrlContains("/apps/absent/activity"), "Gagal diarahkan ke halaman activity!");
    }
}

