package com.company.sqa.ui.steps;

import com.company.sqa.pages.AbsentSickPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class AbsentSickSteps {

    private static final Logger logger = LogManager.getLogger(AbsentSickSteps.class);
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

    @Maka("Kartu riwayat sakit harus menampilkan tanggal sakit yang dipilih bukan tanggal dan jam pengajuan saat ini")
    public void kartuRiwayatSakitHarusMenampilkanTanggalSakitYangDipilihBukanTanggalDanJamPengajuanSaatIni() {
        boolean hasBindingIssue = getAbsentSickPage().areSickDatesRenderingCurrentTimestampInsteadOfSickDates();
        Assert.assertFalse(hasBindingIssue,
                "[DEFECT-SCK-001] Total request bertambah satu, namun entri 'Dari tanggal' & 'Sampai tanggal' menampilkan tanggal dan jam pengajuan (timestamp), bukan tanggal sakit yang dipilih!");
    }

    @Ketika("Pengguna membuka modal form sakit dan menekan tombol ajukan tanpa mengunggah berkas surat dokter")
    public void penggunaMembukaModalFormSakitDanMenekanTombolAjukanTanpaMengunggahBerkasSuratDokter() {
        getAbsentSickPage().openModalAjukanSakit();
        getAbsentSickPage().clickSubmitAjukan();
    }

    @Maka("Sistem harus menolak pengajuan dengan validasi kewajiban lampiran surat dokter")
    public void sistemHarusMenolakPengajuanDenganValidasiKewajibanLampiranSuratDokter() {
        boolean isRequired = getAbsentSickPage().isSuratSakitAttachmentRequired();
        Assert.assertTrue(isRequired,
                "[DEFECT-SCK-002] Pengajuan izin sakit diterima tanpa lampiran surat sakit (tidak ada validasi kewajiban upload surat dokter)!");
    }

    @Ketika("Pengguna mengajukan izin sakit dengan tanggal lampau atau backdate")
    public void penggunaMengajukanIzinSakitDenganTanggalLampauAtauBackdate() {
        logger.info("Memeriksa respons antarmuka terhadap pengajuan tanggal lampau (backdate)");
    }

    @Maka("Sistem harus menampilkan konfirmasi peringatan backdate sebelum permohonan disetujui untuk disimpan")
    public void sistemHarusMenampilkanKonfirmasiPeringatanBackdateSebelumPermohonanDisetujuiUntukDisimpan() {
        boolean warningDisplayed = getAbsentSickPage().isBackdateWarningDisplayed();
        Assert.assertTrue(warningDisplayed,
                "[DEFECT-SCK-003] Pengajuan diterima tanpa peringatan! Entri backdate langsung masuk ke daftar dengan status 'Menunggu Approval'.");
    }
}

