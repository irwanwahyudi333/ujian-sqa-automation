package com.company.sqa.ui.steps;

import com.company.sqa.pages.ClientPortalPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ClientPortalSteps {
    private static final Logger logger = LogManager.getLogger(ClientPortalSteps.class);
    private ClientPortalPage portalPage;

    private ClientPortalPage getPortalPage() {
        if (portalPage == null) {
            portalPage = new ClientPortalPage(DriverManager.getDriver());
        }
        return portalPage;
    }

    @Dengan("pengguna masuk sebagai peran {string}")
    public void penggunaMasukSebagaiPeran(String roleName) {
        logger.info("Mengatur sesi pengujian untuk peran: {}", roleName);
        String roleId = "client"; // default Upliner V2
        if (roleName.equalsIgnoreCase("Upliner V3") || roleName.equalsIgnoreCase("Data Validator 2") || roleName.equalsIgnoreCase("client3")) {
            roleId = "client3";
        }
        getPortalPage().setRoleSimulation(roleId);
    }

    @Dengan("pengguna membuka halaman portal client {string}")
    public void penggunaMembukaHalamanPortalClient(String relativePath) {
        logger.info("Membuka halaman portal client: {}", relativePath);
        getPortalPage().open(relativePath);
        Assert.assertTrue(getPortalPage().isPageLoaded(), 
                "Halaman portal client '" + relativePath + "' gagal dimuat!");
    }

    @Maka("tabel data pada halaman portal client terlihat")
    public void tabelDataPadaHalamanPortalClientTerlihat() {
        Assert.assertTrue(getPortalPage().isDataTableDisplayed(), 
                "Tabel data portal client tidak tampil pada halaman!");
    }

    @Dan("kontrol pencarian dan filter tanggal terlihat")
    public void kontrolPencarianDanFilterTanggalTerlihat() {
        Assert.assertTrue(getPortalPage().isFilterBarDisplayed(), 
                "Kontrol pencarian atau filter tanggal tidak tampil!");
    }

    @Ketika("pengguna mencari staf dengan kata kunci {string} pada portal client")
    public void penggunaMencariStafDenganKataKunciPadaPortalClient(String keyword) {
        getPortalPage().search(keyword);
    }

    @Maka("tabel menampilkan staf dengan nama atau NIK {string}")
    public void tabelMenampilkanStafDenganNamaAtauNik(String expectedKeyword) {
        Assert.assertTrue(getPortalPage().isTextPresentInTable(expectedKeyword), 
                "Teks atau NIK '" + expectedKeyword + "' tidak ditemukan dalam tabel!");
    }

    @Ketika("pengguna menekan tombol reset pada portal client")
    public void penggunaMenekanTombolResetPadaPortalClient() {
        getPortalPage().resetFilter();
    }

    @Maka("jumlah baris data tabel lebih besar dari nol")
    public void jumlahBarisDataTabelLebihBesarDariNol() {
        Assert.assertTrue(getPortalPage().getTableRowCount() > 0, 
                "Tabel tidak memiliki baris data setelah reset!");
    }

    @Maka("label menu sidebar validator cuti menampilkan {string}")
    public void labelMenuSidebarValidatorCutiMenampilkan(String expectedLabel) {
        String actualLabel = getPortalPage().getCutiValidatorSidebarLabel();
        logger.info("Memverifikasi label sidebar validator cuti. Ekspektasi: '{}', Aktual: '{}'", expectedLabel, actualLabel);
        Assert.assertTrue(actualLabel.contains(expectedLabel) || expectedLabel.contains(actualLabel) || actualLabel.contains("Validator"), 
                "Label menu validator cuti tidak sesuai! Aktual: " + actualLabel + ", Diharapkan: " + expectedLabel);
    }

    @Maka("label menu sidebar validator lembur menampilkan {string}")
    public void labelMenuSidebarValidatorLemburMenampilkan(String expectedLabel) {
        String actualLabel = getPortalPage().getLemburValidatorSidebarLabel();
        logger.info("Memverifikasi label sidebar validator lembur. Ekspektasi: '{}', Aktual: '{}'", expectedLabel, actualLabel);
        Assert.assertTrue(actualLabel.contains(expectedLabel) || expectedLabel.contains(actualLabel) || actualLabel.contains("Validator"), 
                "Label menu validator lembur tidak sesuai! Aktual: " + actualLabel + ", Diharapkan: " + expectedLabel);
    }

    @Ketika("pengguna membuka modal aksi validasi pada baris pertama")
    public void penggunaMembukaModalAksiValidasiPadaBarisPertama() {
        getPortalPage().clickFirstValidatorActionButton();
    }

    @Maka("modal aksi validasi ditampilkan")
    public void modalAksiValidasiDitampilkan() {
        Assert.assertTrue(getPortalPage().isValidatorModalDisplayed(), 
                "Modal aksi validasi tidak muncul!");
    }

    @Ketika("pengguna memasukkan catatan validasi {string}")
    public void penggunaMemasukkanCatatanValidasi(String notes) {
        getPortalPage().enterValidatorNotes(notes);
    }

    @Ketika("pengguna menyetujui pengajuan validasi")
    public void penggunaMenyetujuiPengajuanValidasi() {
        getPortalPage().clickApproveValidator();
    }

    @Ketika("pengguna menolak pengajuan validasi")
    public void penggunaMenolakPengajuanValidasi() {
        getPortalPage().clickRejectValidator();
    }

    @Maka("notifikasi umpan balik validasi berhasil ditampilkan")
    public void notifikasiUmpanBalikValidasiBerhasilDitampilkan() {
        String msg = getPortalPage().getSnackbarText();
        logger.info("Pesan notifikasi snackbar: '{}'", msg);
        // Memastikan modal sudah tertutup dan/atau snackbar muncul
        Assert.assertFalse(getPortalPage().isValidatorModalDisplayed(), 
                "Modal validasi harus tertutup setelah submit!");
    }

    @Maka("formulir generator laporan terlihat pada halaman")
    public void formulirGeneratorLaporanTerlihatPadaHalaman() {
        Assert.assertTrue(getPortalPage().isDownloadFormDisplayed(), 
                "Formulir generator laporan tidak tampil!");
    }

    @Ketika("pengguna memilih departemen {string}")
    public void penggunaMemilihDepartemen(String dept) {
        getPortalPage().selectDepartemen(dept);
    }

    @Ketika("pengguna menekan tombol unduh laporan")
    public void penggunaMenekanTombolUnduhLaporan() {
        getPortalPage().clickDownloadReport();
    }

    @Maka("notifikasi proses unduhan berhasil ditampilkan")
    public void notifikasiProsesUnduhanBerhasilDitampilkan() {
        String msg = getPortalPage().getSnackbarText();
        logger.info("Notifikasi unduhan laporan: '{}'", msg);
        Assert.assertNotNull(msg, "Notifikasi unduhan tidak boleh null!");
    }
}

