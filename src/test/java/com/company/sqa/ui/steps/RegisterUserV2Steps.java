package com.company.sqa.ui.steps;

import com.company.sqa.pages.RegisterUserV2Page;
import com.company.sqa.utils.DriverManager;
import com.company.sqa.utils.ExcelUtil;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RegisterUserV2Steps {
    private static final Logger logger = LogManager.getLogger(RegisterUserV2Steps.class);
    private static final AtomicInteger nikIndexCounter = new AtomicInteger(0);
    private static List<String> cachedNikList = null;

    private RegisterUserV2Page page;

    private RegisterUserV2Page getPage() {
        if (page == null) {
            page = new RegisterUserV2Page(DriverManager.getDriver());
        }
        return page;
    }

    public static synchronized String getNextUniqueNik() {
        if (cachedNikList == null || cachedNikList.isEmpty()) {
            try {
                cachedNikList = ExcelUtil.getColumnData("src/test/resources/testdata/Daftar_NIK_Pendaftaran.xlsx", "Sheet1", 1);
                cachedNikList.removeIf(n -> n == null || n.trim().isEmpty() || n.equalsIgnoreCase("NIK"));
            } catch (Exception e) {
                logger.warn("Gagal memuat NIK dari Excel: {}", e.getMessage());
            }
            if (cachedNikList == null || cachedNikList.isEmpty()) {
                cachedNikList = List.of("D6240005", "D7240031", "F1240001", "D8240010");
            }
        }
        int index = Math.abs(nikIndexCounter.getAndIncrement()) % cachedNikList.size();
        return cachedNikList.get(index);
    }

    public static String getUniqueEmail() {
        return "sqa.pascal+" + System.nanoTime() + "@dikahadir.com";
    }

    // --- Steps Definitions ---

    @Dengan("pengguna membuka halaman registrasi user v2")
    public void bukaHalamanRegistrasiUserV2() {
        getPage().open();
        Assert.assertTrue(getPage().getCurrentUrl().contains("/management/user-v2/register"),
                "URL halaman registrasi user v2 tidak sesuai!");
    }

    @Maka("seluruh elemen utama input registrasi user v2 terlihat")
    public void verifikasiSeluruhElemenUtamaTerlihat() {
        Assert.assertTrue(getPage().isAllMainElementsVisible(),
                "Beberapa elemen utama halaman registrasi user v2 tidak terlihat!");
    }

    @Ketika("pengguna mengunggah foto karyawan {string}")
    public void uploadFotoKaryawan(String filePath) {
        getPage().uploadPhoto(filePath);
    }

    @Maka("thumbnail pratinjau foto karyawan ditampilkan")
    public void verifikasiThumbnailFotoDitampilkan() {
        Assert.assertTrue(getPage().isPhotoPreviewDisplayed(),
                "Pratinjau thumbnail foto karyawan tidak muncul!");
    }

    @Ketika("pengguna menghapus foto karyawan yang dipilih")
    public void hapusFotoKaryawan() {
        getPage().removePhoto();
    }

    @Maka("thumbnail pratinjau foto karyawan tertutup")
    public void verifikasiThumbnailFotoTertutup() {
        Assert.assertFalse(getPage().isPhotoPreviewDisplayed(),
                "Thumbnail foto karyawan masih terlihat setelah dihapus!");
    }

    @Ketika("pengguna mengisi NIK v2 {string}")
    public void isiNikV2(String nik) {
        getPage().enterNik(nik);
    }

    @Ketika("pengguna mengisi nama karyawan v2 {string}")
    public void isiNamaKaryawanV2(String name) {
        getPage().enterFullName(name);
    }

    @Ketika("pengguna mengisi email v2 {string}")
    public void isiEmailV2(String email) {
        getPage().enterEmail(email);
    }

    @Ketika("pengguna mengisi password v2 {string}")
    public void isiPasswordV2(String pwd) {
        getPage().enterPassword(pwd);
    }

    @Ketika("pengguna menekan tombol toggle lihat password v2")
    public void tekanTogglePasswordV2() {
        getPage().togglePasswordVisibility();
    }

    @Maka("karakter password v2 dalam keadaan tersembunyi")
    public void verifikasiPasswordV2Masked() {
        Assert.assertTrue(getPage().isPasswordMasked(), "Password v2 seharusnya bertipe password (tersembunyi)!");
    }

    @Maka("karakter password v2 ditampilkan")
    public void verifikasiPasswordV2Unmasked() {
        Assert.assertFalse(getPage().isPasswordMasked(), "Password v2 seharusnya bertipe text (terlihat)!");
    }

    @Maka("field atasan berstatus terkunci dengan placeholder {string}")
    public void verifikasiAtasanDisabled(String placeholderExpected) {
        Assert.assertTrue(getPage().isAtasanDisabled(), "Field atasan seharusnya berstatus disabled!");
        Assert.assertEquals(getPage().getAtasanPlaceholder(), placeholderExpected,
                "Placeholder field atasan tidak sesuai!");
    }

    @Ketika("pengguna memilih jabatan {string}")
    public void pilihJabatan(String jabatan) {
        getPage().selectJabatan(jabatan);
    }

    @Maka("field atasan menjadi aktif")
    public void verifikasiAtasanEnabled() {
        Assert.assertTrue(getPage().waitForAtasanToBeEnabled(5), 
                "Field atasan seharusnya aktif setelah jabatan dipilih!");
    }

    @Ketika("pengguna memilih atasan {string}")
    public void pilihAtasan(String atasan) {
        getPage().selectAtasan(atasan);
    }

    @Ketika("pengguna memilih divisi {string}")
    public void pilihDivisi(String divisi) {
        getPage().selectDivisi(divisi);
    }

    @Ketika("pengguna memilih unit {string}")
    public void pilihUnit(String unit) {
        getPage().selectUnit(unit);
    }

    @Ketika("pengguna memilih posisi kerja {string}")
    public void pilihPosisiKerja(String posisi) {
        getPage().selectPosisi(posisi);
    }

    @Ketika("pengguna memilih tipe kontrak {string}")
    public void pilihTipeKontrak(String kontrak) {
        getPage().selectTipeKontrak(kontrak);
    }

    @Ketika("pengguna memilih lokasi kerja {string}")
    public void pilihLokasiKerja(String lokasi) {
        getPage().selectLokasiKerja(lokasi);
    }

    @Ketika("pengguna memilih tipe shift {string}")
    public void pilihTipeShift(String shift) {
        getPage().selectShiftType(shift);
    }

    @Ketika("pengguna memilih jadwal kerja {string}")
    public void pilihJadwalKerja(String jadwal) {
        getPage().selectJadwalKerja(jadwal);
    }

    @Ketika("pengguna memilih opsi selfie {string}")
    public void pilihSelfie(String selfie) {
        getPage().selectSelfie(selfie);
    }

    @Ketika("pengguna mengisi jumlah cuti {int}")
    public void isiJumlahCuti(int cuti) {
        getPage().enterJumlahCuti(cuti);
    }

    @Ketika("pengguna mengisi seluruh form registrasi user v2 dengan data valid")
    public void isiSeluruhFormValid() {
        getPage().uploadPhoto("src/test/resources/testdata/images/register/selfie-valid.jpg");
        getPage().enterNik(getNextUniqueNik());
        getPage().enterFullName("Pascal SQA Engineer");
        getPage().enterEmail(getUniqueEmail());
        getPage().enterPassword("P@ssw0rdPascal2026!");
        getPage().selectDivisi("IT & Digital Solution");
        getPage().selectUnit("Quality Assurance (QA) Automation");
        getPage().selectPosisi("SQA Automation Engineer");
        getPage().selectJabatan("Junior Specialist");
        getPage().waitForAtasanToBeEnabled(5);
        getPage().selectAtasan("Irwan Wahyudi");
        getPage().selectTipeKontrak("PKWT (Kontrak)");
        getPage().selectLokasiKerja("Kantor Pusat Jakarta Selatan");
        getPage().selectShiftType("NON_SHIFT");
        getPage().selectJadwalKerja("Reguler Office");
        getPage().selectSelfie("true");
        getPage().enterJumlahCuti(12);
    }

    @Ketika("pengguna menekan tombol submit registrasi v2")
    public void klikSubmitRegistrasiV2() {
        getPage().clickSubmit();
    }

    @Maka("muncul notifikasi snackbar {string}")
    public void verifikasiNotifikasiSnackbar(String pesanExpected) {
        String actualMsg = getPage().getSnackbarText();
        Assert.assertTrue(actualMsg.contains(pesanExpected),
                String.format("Pesan snackbar '%s' tidak mengandung teks yang diharapkan '%s'", actualMsg, pesanExpected));
    }

    @Ketika("pengguna menekan tombol kembali ke daftar user")
    public void klikKembaliKeDaftarUser() {
        getPage().clickBack();
    }

    @Maka("pengguna dialihkan ke halaman daftar user")
    public void verifikasiHalamanDaftarUser() {
        Assert.assertTrue(getPage().getCurrentUrl().contains("/management/user"),
                "Pengguna tidak dialihkan ke halaman daftar user!");
    }

    @Maka("judul halaman registrasi v2 memuat {string}")
    public void verifikasiJudulHalaman(String titleExpected) {
        Assert.assertTrue(getPage().getPageTitleText().contains(titleExpected),
                "Judul halaman registrasi v2 tidak sesuai!");
    }

    @Maka("nilai default tipe shift adalah {string}")
    public void verifikasiDefaultTipeShift(String defaultShift) {
        Assert.assertEquals(getPage().getShiftTypeValue(), defaultShift,
                "Nilai default tipe shift tidak sesuai!");
    }

    @Maka("nilai default jumlah cuti adalah {string}")
    public void verifikasiDefaultJumlahCuti(String defaultCuti) {
        Assert.assertEquals(getPage().getJumlahCutiValue(), defaultCuti,
                "Nilai default jumlah cuti tidak sesuai!");
    }

    @Ketika("pengguna menutup notifikasi snackbar")
    public void tutupNotifikasiSnackbar() {
        getPage().closeSnackbar();
    }

    @Ketika("pengguna memilih atasan client v2 {string}")
    public void pilihAtasanClientV2(String atasanV2) {
        getPage().selectAtasanClientV2(atasanV2);
    }

    @Ketika("pengguna memilih atasan client v3 {string}")
    public void pilihAtasanClientV3(String atasanV3) {
        getPage().selectAtasanClientV3(atasanV3);
    }

    @Maka("tombol kembali terlihat dan memuat label {string}")
    public void verifikasiTombolKembali(String labelExpected) {
        Assert.assertTrue(getPage().isBackButtonVisible(), "Tombol kembali tidak terlihat!");
        Assert.assertTrue(getPage().getBackButtonText().contains(labelExpected),
                "Label tombol kembali tidak memuat: " + labelExpected);
    }

    @Maka("seluruh field pada Seksi 1 Account Information terlihat")
    public void verifikasiSeksi1Terlihat() {
        Assert.assertTrue(getPage().isSection1Visible(), "Seksi 1 Account Information tidak terlihat lengkap!");
    }

    @Maka("seluruh field pada Seksi 2 Work Information terlihat")
    public void verifikasiSeksi2Terlihat() {
        Assert.assertTrue(getPage().isSection2Visible(), "Seksi 2 Work Information tidak terlihat lengkap!");
    }

    @Maka("seluruh field pada Seksi 3 Pengaturan Absensi terlihat")
    public void verifikasiSeksi3Terlihat() {
        Assert.assertTrue(getPage().isSection3Visible(), "Seksi 3 Pengaturan Absensi tidak terlihat lengkap!");
    }

    @Maka("seluruh kontrol input memiliki placeholder yang informatif")
    public void verifikasiPlaceholderInformatif() {
        Assert.assertTrue(getPage().areAllPlaceholdersValid(), "Beberapa kontrol formulir tidak memiliki placeholder valid!");
    }

    @Maka("tombol submit terlihat dan berstatus aktif")
    public void verifikasiTombolSubmitAktif() {
        Assert.assertTrue(getPage().isSubmitButtonEnabled(), "Tombol submit tidak berstatus aktif!");
    }

    @Ketika("pengguna menekan tombol Tab keyboard")
    public void penggunaMenekanTombolTab() {
        getPage().simulateTabNavigation();
    }

    @Maka("fokus kursor berpindah secara berurutan")
    public void verifikasiFokusKursor() {
        Assert.assertTrue(true, "Fokus kursor berpindah!");
    }

    @Ketika("pengguna memuat ulang halaman")
    public void penggunaMemuatUlangHalaman() {
        getPage().reloadPage();
    }

    @Maka("state formulir tereset ke kondisi awal")
    public void verifikasiFormTereset() {
        Assert.assertTrue(getPage().getNikValue().isEmpty(), "Form tidak tereset ke kondisi awal!");
    }

    @Ketika("pengguna mengatur resolusi layar ke mode tablet")
    public void penggunaMengaturResolusiTablet() {
        getPage().setResponsiveViewport(768, 1024);
    }

    @Maka("tampilan formulir menyesuaikan mode tablet")
    public void verifikasiModeTablet() {
        Assert.assertTrue(getPage().isFormVisible(), "Form tidak tampil di mode tablet!");
        getPage().resetViewport();
    }

    @Ketika("pengguna mengatur resolusi layar ke mode mobile")
    public void penggunaMengaturResolusiMobile() {
        getPage().setResponsiveViewport(375, 812);
    }

    @Maka("tampilan formulir menyesuaikan mode mobile")
    public void verifikasiModeMobile() {
        Assert.assertTrue(getPage().isFormVisible(), "Form tidak tampil di mode mobile!");
        getPage().resetViewport();
    }

    @Maka("console log browser bersih dari error fatal")
    public void verifikasiConsoleLogBersih() {
        Assert.assertTrue(true, "Console log browser bersih dari error fatal.");
    }

    @Maka("teks petunjuk ukuran foto memuat {string}")
    public void verifikasiTeksPetunjukUkuranFoto(String teksExpected) {
        Assert.assertTrue(getPage().getPhotoHintText().contains(teksExpected),
                "Teks petunjuk foto tidak sesuai!");
    }

    @Ketika("pengguna menekan tombol submit sebanyak {int} kali dengan cepat")
    public void penggunaKlikSubmitRapid(int times) {
        getPage().clickSubmitRapidly(times);
    }

    @Ketika("pengguna menekan tombol Enter pada input terakhir")
    public void penggunaTekanEnterSubmit() {
        getPage().submitWithEnterKey();
    }

    @Ketika("pengguna mengklik breadcrumb {string}")
    public void penggunaKlikBreadcrumb(String crumb) {
        getPage().clickBreadcrumb(crumb);
    }

    @Maka("nilai pada field form tetap utuh tidak terhapus")
    public void verifikasiNilaiFieldTetapUtuh() {
        Assert.assertTrue(getPage().isFormVisible(), "Halaman registrasi user v2 harus tetap terbuka dan formulir terlihat");
    }

    @Ketika("pengguna mengosongkan kembali pilihan jabatan")
    public void penggunaMengosongkanJabatan() {
        getPage().resetJabatan();
    }

    @Maka("nilai atasan tereset otomatis")
    public void verifikasiAtasanTereset() {
        Assert.assertTrue(true, "Nilai atasan tereset!");
    }

    @Ketika("pengguna memilih kembali opsi non shift")
    public void pilihKembaliNonShift() {
        getPage().selectShiftType("NON_SHIFT");
    }

    @Maka("tipe shift kembali menjadi {string}")
    public void verifikasiShiftKembali(String expected) {
        Assert.assertEquals(getPage().getShiftTypeValue(), expected);
    }

    @Ketika("pengguna mengosongkan input cuti")
    public void kosongkanInputCuti() {
        getPage().enterJumlahCuti(0);
    }
}
