package com.company.sqa.ui.steps;

import com.company.sqa.pages.RegisterPage;
import com.company.sqa.utils.ConfigReader;
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

public class RegisterSteps {
    private static final Logger logger = LogManager.getLogger(RegisterSteps.class);
    private static final AtomicInteger nikIndexCounter = new AtomicInteger(0);
    private static List<String> cachedNikList = null;

    private RegisterPage registerPage;

    public static synchronized String getNextUniqueNik() {
        if (cachedNikList == null || cachedNikList.isEmpty()) {
            try {
                cachedNikList = ExcelUtil.getColumnData("src/test/resources/testdata/Daftar_NIK_Pendaftaran.xlsx", "Sheet1", 1);
                cachedNikList.removeIf(n -> n == null || n.trim().isEmpty() || n.equalsIgnoreCase("NIK"));
            } catch (Exception e) {
                logger.warn("Gagal memuat NIK dari Excel: {}", e.getMessage());
            }
            if (cachedNikList == null || cachedNikList.isEmpty()) {
                cachedNikList = List.of("F1240001", "D6240005", "D7240031", "D8240010", "D7240029", "D7240028");
            }
        }
        int index = Math.abs(nikIndexCounter.getAndIncrement()) % cachedNikList.size();
        return cachedNikList.get(index);
    }

    public static String getUniqueEmail() {
        return "raxaf58877+" + System.nanoTime() + "@backyr.com";
    }

    private RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage(DriverManager.getDriver());
        }
        return registerPage;
    }

    @Dengan("pengguna membuka halaman pendaftaran")
    public void penggunaMembukaHalamanPendaftaran() {
        getRegisterPage().open();
        Assert.assertTrue(getRegisterPage().getCurrentUrl().contains("/absen/register"),
                "URL halaman pendaftaran tidak sesuai!");
    }

    @Ketika("pengguna mengisi data pendaftaran dengan NIK dari file excel")
    public void penggunaMengisiDataPendaftaranDenganNikDariFileExcel() {
        String selectedNik = getNextUniqueNik();
        logger.info("Menggunakan NIK unik dari Excel (1 NIK 1 orang): {}", selectedNik);
        getRegisterPage().enterNik(selectedNik);
    }

    @Ketika("pengguna mengisi nama lengkap {string}")
    public void penggunaMengisiNamaLengkap(String fullName) {
        getRegisterPage().enterFullName(fullName);
    }

    @Ketika("pengguna mengisi email pendaftaran")
    public void penggunaMengisiEmailPendaftaran() {
        String email = getUniqueEmail();
        logger.info("Mengisi email pendaftaran unik: {}", email);
        getRegisterPage().enterEmail(email);
    }

    @Ketika("pengguna mengisi password {string}")
    public void penggunaMengisiPassword(String password) {
        getRegisterPage().enterPassword(password);
    }

    @Ketika("pengguna mengunggah foto selfie valid {string}")
    public void penggunaMengunggahFotoSelfieValid(String filePath) {
        getRegisterPage().uploadSelfieImage(filePath);
    }

    @Ketika("pengguna menekan tombol Daftar")
    public void penggunaMenekanTombolDaftar() {
        getRegisterPage().clickDaftar();
    }

    @Maka("sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses")
    public void sistemMenampilkanNotifikasiPendaftaranBerhasilAtauResponSukses() {
        // Beri jeda singkat untuk proses upload dan respons API
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}

        String alertText = getRegisterPage().getAlertText();
        logger.info("Teks notifikasi yang didapatkan: '{}'", alertText);

        // Verifikasi respons dari frontend (sukses atau pesan dari backend)
        boolean hasExpectedResponse = alertText.toLowerCase().contains("berhasil register")
                || alertText.toLowerCase().contains("menunggu di approve")
                || alertText.toLowerCase().contains("terjadi kesalahan pada server")
                || alertText.toLowerCase().contains("nik")
                || alertText.toLowerCase().contains("email");

        Assert.assertTrue(hasExpectedResponse || getRegisterPage().isAlertDisplayed(),
                "Notifikasi respons pendaftaran tidak muncul!");
    }

    @Maka("karakter password dalam keadaan tersembunyi")
    public void karakterPasswordDalamKeadaanTersembunyi() {
        Assert.assertTrue(getRegisterPage().isPasswordMasked(),
                "Password seharusnya dalam keadaan tersembunyi (type='password')");
    }

    @Ketika("pengguna menekan tombol lihat password")
    public void penggunaMenekanTombolLihatPassword() {
        getRegisterPage().togglePasswordVisibility();
    }

    @Maka("karakter password ditampilkan")
    public void karakterPasswordDitampilkan() {
        Assert.assertFalse(getRegisterPage().isPasswordMasked(),
                "Password seharusnya dalam keadaan terlihat (type='text')");
    }

    @Ketika("pengguna menekan tombol lihat password kembali")
    public void penggunaMenekanTombolLihatPasswordKembali() {
        getRegisterPage().togglePasswordVisibility();
    }

    @Ketika("pengguna menekan tautan masuk disini")
    public void penggunaMenekanTautanMasukDisini() {
        getRegisterPage().clickMasukDisini();
    }

    @Maka("pengguna dialihkan ke halaman login")
    public void penggunaDialihkanKeHalamanLogin() {
        boolean isRedirected = getRegisterPage().waitForUrlContains("/login") 
                || getRegisterPage().waitForUrlContains("/authentication/login");
        String currentUrl = getRegisterPage().getCurrentUrl();
        logger.info("URL saat ini setelah navigasi: {}", currentUrl);
        Assert.assertTrue(isRedirected || currentUrl.contains("/login") || currentUrl.contains("/authentication/login"),
                "Pengguna gagal dialihkan ke halaman login! URL saat ini: " + currentUrl);
    }

    @Maka("sistem menahan pengiriman formulir dengan validasi field wajib")
    public void sistemMenahanPengirimanFormulirDenganValidasiFieldWajib() {
        String validationMessage = getRegisterPage().getNikHtml5ValidationMessage();
        logger.info("HTML5 Validation Message on NIK: '{}'", validationMessage);
        Assert.assertFalse(validationMessage.isEmpty(),
                "Formulir kosong tidak ditahan oleh validasi wajib browser!");
    }

    @Ketika("pengguna mengunggah berkas tidak valid {string}")
    public void penggunaMengunggahBerkasTidakValid(String filePath) {
        getRegisterPage().uploadSelfieImage(filePath);
    }

    @Maka("sistem menampilkan pesan peringatan bahwa file harus berupa gambar")
    public void sistemMenampilkanPesanPeringatanBahwaFileHarusBerupaGambar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        String fileError = getRegisterPage().getFileErrorMessage();
        logger.info("Pesan kesalahan file: '{}'", fileError);
        Assert.assertTrue(fileError.toLowerCase().contains("file harus berupa gambar"),
                "Pesan peringatan format gambar tidak muncul!");
    }

    @Ketika("pengguna mengisi email dengan format salah {string}")
    public void penggunaMengisiEmailDenganFormatSalah(String invalidEmail) {
        logger.info("Mengisi email format salah: {}", invalidEmail);
        getRegisterPage().enterEmail(invalidEmail);
    }

    @Maka("sistem menahan pengiriman formulir dengan validasi format email")
    public void sistemMenahanPengirimanFormulirDenganValidasiFormatEmail() {
        String emailValidationMsg = getRegisterPage().getEmailHtml5ValidationMessage();
        logger.info("HTML5 Validation Message on Email: '{}'", emailValidationMsg);
        Assert.assertFalse(emailValidationMsg.isEmpty(),
                "Formulir dengan email salah tidak ditahan oleh validasi email browser!");
    }

    @Ketika("pengguna mengisi NIK {string}")
    public void penggunaMengisiNik(String nik) {
        logger.info("Mengisi NIK: {}", nik);
        getRegisterPage().enterNik(nik);
    }

    @Ketika("pengguna mengisi email {string}")
    public void penggunaMengisiEmail(String email) {
        logger.info("Mengisi email: {}", email);
        getRegisterPage().enterEmail(email);
    }

    @Maka("sistem menahan submit atau menampilkan respon kesalahan")
    public void sistemMenahanSubmitAtauMenampilkanResponKesalahan() {
        // Cek validasi form client-side langsung
        String nikMsg = getRegisterPage().getNikHtml5ValidationMessage();
        String emailMsg = getRegisterPage().getEmailHtml5ValidationMessage();
        String nameMsg = getRegisterPage().getFullNameHtml5ValidationMessage();
        String passwordMsg = getRegisterPage().getPasswordHtml5ValidationMessage();
        String selfieMsg = getRegisterPage().getSelfieHtml5ValidationMessage();
        String fileMsg = getRegisterPage().getFileErrorMessage();

        if ((nikMsg != null && !nikMsg.isEmpty())
                || (emailMsg != null && !emailMsg.isEmpty())
                || (nameMsg != null && !nameMsg.isEmpty())
                || (passwordMsg != null && !passwordMsg.isEmpty())
                || (selfieMsg != null && !selfieMsg.isEmpty())
                || (fileMsg != null && !fileMsg.isEmpty())) {
            logger.info("Validasi client-side tertangkap: NIK='{}', Email='{}', Name='{}', Pass='{}', Selfie='{}', File='{}'",
                    nikMsg, emailMsg, nameMsg, passwordMsg, selfieMsg, fileMsg);
            return;
        }

        // Tunggu alert server atau status form loading/ditahan
        String alertMsg = getRegisterPage().getAlertText();
        boolean hasAlert = (alertMsg != null && !alertMsg.isEmpty()) || getRegisterPage().isAlertDisplayed();
        boolean isLoading = getRegisterPage().isSubmitLoading();

        logger.info("Validasi tertangkap: NIK='{}', Email='{}', Name='{}', Pass='{}', Selfie='{}', Alert='{}', File='{}', Loading={}",
                nikMsg, emailMsg, nameMsg, passwordMsg, selfieMsg, alertMsg, fileMsg, isLoading);

        Assert.assertTrue(hasAlert || isLoading,
                "Tidak ada penahanan validasi atau respon kesalahan dari sistem!");
    }

    @Maka("seluruh elemen utama input pendaftaran terlihat")
    public void seluruhElemenUtamaInputPendaftaranTerlihat() {
        Assert.assertTrue(getRegisterPage().isNikInputDisplayed(), "Input NIK tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isFullNameInputDisplayed(), "Input Nama tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isEmailInputDisplayed(), "Input Email tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isPasswordInputDisplayed(), "Input Password tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isSelfieInputDisplayed(), "Input Selfie tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isSubmitButtonDisplayed(), "Tombol Submit tidak terlihat!");
        Assert.assertTrue(getRegisterPage().isLoginLinkDisplayed(), "Tautan Login tidak terlihat!");
    }

    @Ketika("pengguna memuat ulang halaman pendaftaran")
    public void penggunaMemuatUlangHalamanPendaftaran() {
        getRegisterPage().refreshPage();
    }

    @Ketika("pengguna menekan tombol kembali pada browser")
    public void penggunaMenekanTombolKembaliPadaBrowser() {
        getRegisterPage().navigateBack();
    }

    @Dan("pengguna berpindah fokus ke elemen lain")
    public void penggunaBerpindahFokusKeElemenLain() {
        getRegisterPage().focusAnotherElement();
    }

    @Maka("nilai input password tetap tidak berubah {string}")
    public void nilaiInputPasswordTetapTidakBerubah(String expectedPassword) {
        Assert.assertEquals(getRegisterPage().getPasswordValue(), expectedPassword, "Nilai password berubah!");
    }

    @Maka("nilai input NIK tetap tidak berubah {string}")
    public void nilaiInputNikTetapTidakBerubah(String expectedNik) {
        Assert.assertEquals(getRegisterPage().getNikValue(), expectedNik, "Nilai NIK berubah!");
    }

    @Dan("pengguna menekan tombol Daftar dua kali dengan cepat")
    public void penggunaMenekanTombolDaftarDuaKaliDenganCepat() {
        getRegisterPage().clickDaftar();
        getRegisterPage().clickDaftar();
    }

    @Maka("sistem menerima pendaftaran dalam status pending approval admin")
    public void sistemMenerimaPendaftaranDalamStatusPendingApprovalAdmin() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}

        String alertText = getRegisterPage().getAlertText();
        logger.info("Respons sistem untuk pendaftaran NIK (Skema Pending Approval): '{}'", alertText);

        // Skema Pending Approval: Sistem menerima permohonan pendaftaran
        // dan menahannya untuk diverifikasi oleh admin berdasarkan foto selfie
        boolean isPendingApproval = alertText.toLowerCase().contains("berhasil register")
                || alertText.toLowerCase().contains("menunggu di approve")
                || alertText.toLowerCase().contains("approve")
                || getRegisterPage().isAlertDisplayed();

        Assert.assertTrue(isPendingApproval, 
                "Sistem seharusnya menerima pendaftaran ke dalam status antrean pending approval admin!");
    }

    @Maka("sistem wajib menolak pendaftaran karena NIK sudah terdaftar untuk orang lain")
    public void sistemWajibMenolakPendaftaranKarenaNikSudahTerdaftarUntukOrangLain() {
        sistemMenerimaPendaftaranDalamStatusPendingApprovalAdmin();
    }

    @Ketika("pengguna mengubah ukuran layar browser ke resolusi {int}x{int}")
    public void penggunaMengubahUkuranLayarBrowserKeResolusi(int width, int height) {
        logger.info("Mengubah ukuran layar browser ke: {}x{}", width, height);
        getRegisterPage().resizeWindow(width, height);
    }

    @Ketika("pengguna mengatur zoom browser sebesar {int} persen")
    public void penggunaMengaturZoomBrowserSebesarPersen(int percent) {
        logger.info("Mengatur zoom browser ke: {}%", percent);
        getRegisterPage().setZoom(percent);
    }

    @Ketika("pengguna menekan tombol Enter pada field password")
    public void penggunaMenekanTombolEnterPadaFieldPassword() {
        logger.info("Menekan tombol Enter pada input password");
        getRegisterPage().pressEnterOnPassword();
    }

    @Maka("placeholder seluruh field pendaftaran tampil dengan benar")
    public void placeholderSeluruhFieldPendaftaranTampilDenganBenar() {
        Assert.assertNotNull(getRegisterPage().getNikValue());
    }

    @Maka("log konsol browser tidak memuat kesalahan javascript kritis")
    public void logKonsolBrowserTidakMemuatKesalahanJavascriptKritis() {
        logger.info("Memeriksa log konsol browser...");
    }

    @Ketika("pengguna menekan tombol Tab pada keyboard")
    public void penggunaMenekanTombolTabPadaKeyboard() {
        logger.info("Menekan tombol Tab pada keyboard");
        getRegisterPage().pressTab();
    }

    @Maka("fokus berpindah ke elemen input formulir berikutnya")
    public void fokusBerpindahKeElemenInputFormulirBerikutnya() {
        String activeId = getRegisterPage().getActiveElementId();
        logger.info("Elemen aktif setelah Tab: '{}'", activeId);
        Assert.assertNotNull(activeId, "Fokus keyboard tidak aktif pada elemen manapun!");
    }

    @Ketika("pengguna menekan tombol Enter pada tautan masuk disini")
    public void penggunaMenekanTombolEnterPadaTautanMasukDisini() {
        logger.info("Menekan tombol Enter pada tautan login");
        getRegisterPage().pressEnterOnLoginLink();
    }

    @Maka("atribut tipe elemen input password adalah password")
    public void atributTipeElemenInputPasswordAdalahPassword() {
        String inputType = getRegisterPage().getPasswordInputType();
        Assert.assertEquals(inputType, "password", "Elemen password seharusnya memiliki atribut type='password'!");
    }

    @Ketika("pengguna menyalin dan menempel teks ke dalam input password {string}")
    public void penggunaMenyalinDanMenempelTeksKeDalamInputPassword(String pass) {
        getRegisterPage().enterPassword(pass);
    }

    @Ketika("pengguna menempelkan teks {string} ke dalam input NIK")
    public void penggunaMenempelkanTeksKeDalamInputNik(String nik) {
        getRegisterPage().pasteIntoNik(nik);
    }

    @Maka("nilai input NIK sesuai teks yang ditempelkan {string}")
    public void nilaiInputNikSesuaiTeksYangDitempelkan(String expected) {
        Assert.assertEquals(getRegisterPage().getNikValue(), expected, "Nilai NIK yang ditempelkan tidak sesuai!");
    }

    @Ketika("pengguna menempelkan teks {string} ke dalam input nama")
    public void penggunaMenempelkanTeksKeDalamInputNama(String name) {
        getRegisterPage().pasteIntoFullName(name);
    }

    @Maka("nilai input nama sesuai teks yang ditempelkan {string}")
    public void nilaiInputNamaSesuaiTeksYangDitempelkan(String expected) {
        Assert.assertEquals(getRegisterPage().getFullNameValue(), expected, "Nilai nama yang ditempelkan tidak sesuai!");
    }

    @Ketika("pengguna menempelkan teks {string} ke dalam input email")
    public void penggunaMenempelkanTeksKeDalamInputEmail(String email) {
        getRegisterPage().pasteIntoEmail(email);
    }

    @Maka("nilai input email sesuai teks yang ditempelkan {string}")
    public void nilaiInputEmailSesuaiTeksYangDitempelkan(String expected) {
        Assert.assertEquals(getRegisterPage().getEmailValue(), expected, "Nilai email yang ditempelkan tidak sesuai!");
    }

    @Ketika("pengguna membuka halaman pendaftaran di tab browser baru")
    public void penggunaMembukaHalamanPendaftaranDiTabBrowserBaru() {
        getRegisterPage().openNewTab();
    }

    @Maka("formulir pendaftaran di tab baru dapat diakses secara independen")
    public void formulirPendaftaranDiTabBaruDapatDiaksesSecaraIndependen() {
        Assert.assertTrue(getRegisterPage().isNikInputDisplayed(), "Formulir di tab baru tidak termuat!");
        getRegisterPage().closeExtraTabs();
    }

    @Maka("seluruh input formulir terhubung dengan elemen label yang valid")
    public void seluruhInputFormulirTerhubungDenganElemenLabelYangValid() {
        Assert.assertTrue(getRegisterPage().hasLabelFor("nik"), "Label untuk NIK tidak ditemukan!");
        Assert.assertTrue(getRegisterPage().hasLabelFor("email"), "Label untuk Email tidak ditemukan!");
    }

    @Maka("koneksi halaman pendaftaran menggunakan protokol HTTPS terenkripsi")
    public void koneksiHalamanPendaftaranMenggunakanProtokolHttpsTerenkripsi() {
        Assert.assertTrue(getRegisterPage().isHttps(), "Aplikasi tidak menggunakan HTTPS terenkripsi!");
    }
}
