package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentHomePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class AbsentHomeSteps {

    private static final Logger logger = LogManager.getLogger(AbsentHomeSteps.class);
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

    @Ketika("Pengguna menyelesaikan proses Absen Masuk dengan swafoto")
    public void penggunaMenyelesaikanProsesAbsenMasukDenganSwafoto() {
        logger.info("Mengevaluasi transisi status tombol setelah proses Absen Masuk");
    }

    @Maka("Tombol aksi presensi utama harus bertransisi menjadi {string}")
    public void tombolAksiPresensiUtamaHarusBertransisiMenjadi(String expectedButtonText) {
        boolean isDisplayed = getAbsentHomePage().isBtnAbsenKeluarDisplayed();
        Assert.assertTrue(isDisplayed,
                "[DEFECT-ABS-001] Tombol '" + expectedButtonText + "' tidak tampil di antarmuka! " +
                "Tombol aksi presensi utama langsung hilang setelah tombol Absen Masuk ditekan dan tidak bertransisi menjadi Absen Keluar.");
    }

    @Dan("Tombol aksi presensi tidak boleh menghilang dari layar")
    public void tombolAksiPresensiTidakBolehMenghilangDariLayar() {
        boolean disappeared = getAbsentHomePage().isAbsenButtonDisappearedAfterCheckIn();
        Assert.assertFalse(disappeared,
                "[DEFECT-ABS-001] Tombol aksi presensi hilang sepenuhnya dari layar dashboard presensi!");
    }

    @Ketika("Karyawan hendak melakukan absen masuk pada hari kerja berikutnya")
    public void karyawanHendakMelakukanAbsenMasukPadaHariKerjaBerikutnya() {
        logger.info("Memeriksa status otorisasi izin absen masuk karyawan pada hari berikutnya");
    }

    @Maka("Karyawan diizinkan melakukan presensi masuk tanpa diblokir kewajiban koreksi absensi hari sebelumnya")
    public void karyawanDiizinkanMelakukanPresensiMasukTanpaDiblokirKewajibanKoreksiAbsensiHariSebelumnya() {
        boolean blocked = getAbsentHomePage().isKaryawanBlockedByKoreksiHariSebelumnya();
        Assert.assertFalse(blocked,
                "[DEFECT-ABS-002] Karyawan diwajibkan melakukan koreksi absensi terlebih dahulu sebelum diizinkan melakukan absen masuk di hari berikutnya!");
    }
}

