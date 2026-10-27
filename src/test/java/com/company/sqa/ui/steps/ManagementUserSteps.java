package com.company.sqa.ui.steps;

import com.company.sqa.pages.ManagementUserPage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class ManagementUserSteps {
    private static final Logger logger = LogManager.getLogger(ManagementUserSteps.class);
    private ManagementUserPage managementUserPage;

    private ManagementUserPage getManagementUserPage() {
        if (managementUserPage == null) {
            managementUserPage = new ManagementUserPage(DriverManager.getDriver());
        }
        return managementUserPage;
    }

    @Dengan("admin membuka halaman management user")
    public void adminMembukaHalamanManagementUser() {
        logger.info("Membuka halaman management user");
        getManagementUserPage().open();
        String currentUrl = getManagementUserPage().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/management/user"),
                "URL halaman management user tidak sesuai! Aktual: " + currentUrl);
    }

    @Maka("tabel data karyawan terlihat pada halaman")
    public void tabelDataKaryawanTerlihatPadaHalaman() {
        Assert.assertTrue(getManagementUserPage().isTableDisplayed(), "Tabel data karyawan tidak tampil!");
    }

    @Dan("elemen bar pencarian dan tombol filter user terlihat")
    public void elemenBarPencarianDanTombolFilterUserTerlihat() {
        Assert.assertTrue(getManagementUserPage().isSearchTypeSelectDisplayed(), "Dropdown tipe pencarian tidak tampil!");
        Assert.assertTrue(getManagementUserPage().isSearchInputDisplayed(), "Input pencarian tidak tampil!");
        Assert.assertTrue(getManagementUserPage().isSearchBtnDisplayed(), "Tombol search tidak tampil!");
        Assert.assertTrue(getManagementUserPage().isResetBtnDisplayed(), "Tombol reset tidak tampil!");
        Assert.assertTrue(getManagementUserPage().isFilterBtnDisplayed(), "Tombol filter tidak tampil!");
        Assert.assertTrue(getManagementUserPage().isExportBtnDisplayed(), "Tombol export tidak tampil!");
    }

    @Ketika("admin memilih tipe pencarian user {string}")
    public void adminMemilihTipePencarianUser(String typeValue) {
        getManagementUserPage().selectSearchType(typeValue);
    }

    @Dan("admin memasukkan kata kunci pencarian user {string}")
    public void adminMemasukkanKataKunciPencarianUser(String keyword) {
        getManagementUserPage().enterSearchQuery(keyword);
    }

    @Dan("admin menekan tombol Search pada form user")
    public void adminMenekanTombolSearchPadaFormUser() {
        getManagementUserPage().clickSearch();
    }

    @Maka("tabel menampilkan karyawan bernama {string}")
    public void tabelMenampilkanKaryawanBernama(String expectedName) {
        List<String> names = getManagementUserPage().getAllVisibleUserNames();
        Assert.assertTrue(names.contains(expectedName),
                "Nama karyawan '" + expectedName + "' tidak ditemukan dalam tabel! Daftar aktual: " + names);
    }

    @Dan("tabel hanya menampilkan {int} baris data karyawan")
    public void tabelHanyaMenampilkanBarisDataKaryawan(int expectedCount) {
        int actualCount = getManagementUserPage().getVisibleUserRowCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Jumlah baris karyawan tidak sesuai! Diharapkan: " + expectedCount + ", Aktual: " + actualCount);
    }

    @Ketika("admin menekan tombol Reset pencarian user")
    public void adminMenekanTombolResetPencarianUser() {
        getManagementUserPage().clickReset();
    }

    @Maka("seluruh data karyawan kembali ditampilkan pada tabel")
    public void seluruhDataKaryawanKembaliDitampilkanPadaTabel() {
        int rowCount = getManagementUserPage().getVisibleUserRowCount();
        Assert.assertTrue(rowCount >= 4, "Data tabel belum ter-reset sempurna! Baris aktual: " + rowCount);
    }

    @Ketika("admin membuka modal filter user")
    public void adminMembukaModalFilterUser() {
        getManagementUserPage().clickFilterButton();
    }

    @Maka("modal dialog filter user terbuka pada layar")
    public void modalDialogFilterUserTerbukaPadaLayar() {
        Assert.assertTrue(getManagementUserPage().isFilterModalDisplayed(), "Modal dialog filter tidak terbuka!");
    }

    @Ketika("admin memilih filter status user {string}")
    public void adminMemilihFilterStatusUser(String statusValue) {
        getManagementUserPage().selectFilterStatus(statusValue);
    }

    @Dan("admin menekan tombol Terapkan pada modal filter user")
    public void adminMenekanTombolTerapkanPadaModalFilterUser() {
        getManagementUserPage().clickApplyFilter();
    }

    @Ketika("admin mengubah status switch Active pada user dengan id {string}")
    public void adminMengubahStatusSwitchActivePadaUserDenganId(String userId) {
        getManagementUserPage().toggleActiveSwitch(userId);
    }

    @Ketika("admin mengubah status switch User Project pada user dengan id {string}")
    public void adminMengubahStatusSwitchUserProjectPadaUserDenganId(String userId) {
        getManagementUserPage().toggleProjectSwitch(userId);
    }

    @Maka("sistem menampilkan notifikasi snackbar {string}")
    public void sistemMenampilkanNotifikasiSnackbar(String expectedMsg) {
        Assert.assertTrue(getManagementUserPage().isSnackbarDisplayed(), "Snackbar notifikasi tidak tampil!");
        String actualMsg = getManagementUserPage().getSnackbarText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan snackbar tidak sesuai! Diharapkan mengandung: '" + expectedMsg + "', Aktual: '" + actualMsg + "'");
    }

    @Ketika("admin menekan tombol Sync Approval pada user dengan id {string}")
    public void adminMenekanTombolSyncApprovalPadaUserDenganId(String userId) {
        getManagementUserPage().clickSyncApproval(userId);
    }

    @Ketika("admin menekan tombol Lihat Sisa Cuti pada user dengan id {string}")
    public void adminMenekanTombolLihatSisaCutiPadaUserDenganId(String userId) {
        getManagementUserPage().clickLeaveInfo(userId);
    }

    @Maka("modal dialog sisa cuti terbuka menampilkan judul {string}")
    public void modalDialogSisaCutiTerbukaMenampilkanJudul(String expectedTitle) {
        Assert.assertTrue(getManagementUserPage().isLeaveModalDisplayed(), "Modal sisa cuti tidak terbuka!");
        String actualTitle = getManagementUserPage().getLeaveModalTitleText();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Judul modal sisa cuti tidak sesuai! Aktual: " + actualTitle);
    }

    @Dan("admin menutup dialog sisa cuti")
    public void adminMenutupDialogSisaCuti() {
        getManagementUserPage().closeLeaveModal();
    }

    @Ketika("admin menekan tombol Hapus pada user dengan id {string}")
    public void adminMenekanTombolHapusPadaUserDenganId(String userId) {
        getManagementUserPage().clickDeleteUser(userId);
    }

    @Maka("modal konfirmasi hapus terbuka bertuliskan {string}")
    public void modalKonfirmasiHapusTerbukaBertuliskan(String expectedMsg) {
        Assert.assertTrue(getManagementUserPage().isDeleteModalDisplayed(), "Modal konfirmasi hapus tidak terbuka!");
        String actualMsg = getManagementUserPage().getDeleteModalMessageText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Pesan konfirmasi hapus tidak sesuai! Aktual: " + actualMsg);
    }

    @Ketika("admin membatalkan penghapusan user")
    public void adminMembatalkanPenghapusanUser() {
        getManagementUserPage().cancelDelete();
    }

    @Ketika("admin mengonfirmasi penghapusan user")
    public void adminMengonfirmasiPenghapusanUser() {
        getManagementUserPage().confirmDelete();
    }

    @Maka("user dengan id {string} tidak lagi berada pada tabel")
    public void userDenganIdTidakLagiBeradaPadaTabel(String userId) {
        List<String> names = getManagementUserPage().getAllVisibleUserNames();
        Assert.assertFalse(names.contains("Dewi Sartika"),
                "User yang dihapus masih terlihat di tabel!");
    }

    @Ketika("admin menekan tombol Export data user")
    public void adminMenekanTombolExportDataUser() {
        getManagementUserPage().clickExportUsers();
    }
}
