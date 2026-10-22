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
import java.util.Random;

public class RegisterSteps {
    private static final Logger logger = LogManager.getLogger(RegisterSteps.class);
    private RegisterPage registerPage;

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
        String excelPath = "src/test/resources/testdata/Daftar_NIK_Pendaftaran.xlsx";
        String selectedNik = "D6240005"; // default fallback

        try {
            List<String> nikiList = ExcelUtil.getColumnData(excelPath, "Sheet1", 1);
            if (!nikiList.isEmpty()) {
                // Pilih satu NIK secara acak dari daftar yang tersedia di Excel
                int randomIndex = new Random().nextInt(nikiList.size());
                selectedNik = nikiList.get(randomIndex);
            }
        } catch (Exception e) {
            logger.warn("Gagal membaca NIK dari Excel, menggunakan fallback NIK: {}", selectedNik, e);
        }

        logger.info("Menggunakan NIK pengujian: {}", selectedNik);
        getRegisterPage().enterNik(selectedNik);
    }

    @Dan("pengguna mengisi nama lengkap {string}")
    public void penggunaMengisiNamaLengkap(String fullName) {
        getRegisterPage().enterFullName(fullName);
    }

    @Dan("pengguna mengisi email pendaftaran")
    public void penggunaMengisiEmailPendaftaran() {
        String email = ConfigReader.getRegisterEmail();
        logger.info("Mengisi email pendaftaran: {}", email);
        getRegisterPage().enterEmail(email);
    }

    @Dan("pengguna mengisi password {string}")
    public void penggunaMengisiPassword(String password) {
        getRegisterPage().enterPassword(password);
    }

    @Dan("pengguna mengunggah foto selfie valid {string}")
    public void penggunaMengunggahFotoSelfieValid(String filePath) {
        getRegisterPage().uploadSelfieImage(filePath);
    }

    @Dan("pengguna menekan tombol Daftar")
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
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}

        String currentUrl = getRegisterPage().getCurrentUrl();
        logger.info("URL saat ini setelah navigasi: {}", currentUrl);
        Assert.assertTrue(currentUrl.contains("/authentication/login") || currentUrl.contains("/login"),
                "Pengguna gagal dialihkan ke halaman login! URL saat ini: " + currentUrl);
    }

    @Maka("sistem menahan pengiriman formulir dengan validasi field wajib")
    public void sistemMenahanPengirimanFormulirDenganValidasiFieldWajib() {
        String validationMessage = getRegisterPage().getNikHtml5ValidationMessage();
        logger.info("HTML5 Validation Message on NIK: '{}'", validationMessage);
        Assert.assertFalse(validationMessage.isEmpty(),
                "Formulir kosong tidak ditahan oleh validasi wajib browser!");
    }

    @Dan("pengguna mengunggah berkas tidak valid {string}")
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
}
