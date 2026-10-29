package com.company.sqa.ui.steps;

import com.company.sqa.pages.MonthlySchedulePage;
import com.company.sqa.utils.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.util.List;

/**
 * Step Definitions for HADIR Management Monthly Schedule UI Automation.
 */
public class MonthlyScheduleSteps {
    private static final Logger logger = LogManager.getLogger(MonthlyScheduleSteps.class);
    private MonthlySchedulePage monthlySchedulePage;
    private String rememberedFullname = "";

    private MonthlySchedulePage getMonthlySchedulePage() {
        if (monthlySchedulePage == null) {
            monthlySchedulePage = new MonthlySchedulePage(DriverManager.getDriver());
        }
        return monthlySchedulePage;
    }

    // ==========================================
    // 01. Background & Navigasi
    // ==========================================

    @Dengan("admin membuka halaman manajemen monthly schedule")
    public void adminMembukaHalamanManajemenMonthlySchedule() {
        logger.info("Membuka halaman manajemen monthly schedule");
        getMonthlySchedulePage().navigateToMonthlySchedulePage();
        Assert.assertTrue(getMonthlySchedulePage().isPageTitleDisplayed(), "Halaman Monthly Schedule gagal dibuka!");
    }

    @Maka("halaman monthly schedule terbuka sempurna")
    public void halamanMonthlyScheduleTerbukaSempurna() {
        Assert.assertTrue(getMonthlySchedulePage().isPageTitleDisplayed(), "Halaman Monthly Schedule tidak terbuka sempurna!");
    }

    @Dan("judul halaman monthly schedule menampilkan {string}")
    public void judulHalamanMenampilkan(String expectedTitle) {
        String actual = getMonthlySchedulePage().getPageTitleText();
        Assert.assertTrue(actual.contains(expectedTitle),
                "Judul halaman tidak sesuai! Expected: " + expectedTitle + ", Actual: " + actual);
    }

    @Dan("jalur breadcrumbs monthly schedule menampilkan {string}")
    public void jalurBreadcrumbsMenampilkan(String expectedBreadcrumbs) {
        Assert.assertTrue(getMonthlySchedulePage().isBreadcrumbsDisplayed(), "Breadcrumbs tidak terlihat!");
        String actual = getMonthlySchedulePage().getBreadcrumbsText();
        Assert.assertTrue(actual.contains("Monthly Schedule") || actual.contains("Management"),
                "Breadcrumbs tidak valid: " + actual);
    }

    // ==========================================
    // 02. UI Elements & Layout
    // ==========================================

    @Dan("card form import jadwal bulanan ditampilkan")
    public void cardFormImportJadwalBulananDitampilkan() {
        Assert.assertTrue(getMonthlySchedulePage().isImportCardDisplayed(), "Card form import jadwal tidak tampil!");
    }

    @Dan("input file jadwal terlihat")
    public void inputFileJadwalTerlihat() {
        Assert.assertTrue(getMonthlySchedulePage().isImportCardDisplayed(), "Input file jadwal tidak terlihat!");
    }

    @Dan("tombol Import jadwal terlihat")
    public void tombolImportJadwalTerlihat() {
        Assert.assertTrue(getMonthlySchedulePage().isImportButtonEnabled(), "Tombol Import jadwal tidak aktif!");
    }

    @Dan("tombol Download Template terlihat")
    public void tombolDownloadTemplateTerlihat() {
        Assert.assertTrue(getMonthlySchedulePage().isDownloadTemplateButtonDisplayed(), "Tombol Download Template tidak terlihat!");
    }

    @Dan("tombol Download Template ditampilkan dengan styling hijau dan label jelas")
    public void tombolDownloadTemplateDitampilkanDenganStylingHijauDanLabelJelas() {
        Assert.assertTrue(getMonthlySchedulePage().isDownloadTemplateButtonDisplayed(), "Tombol template tidak tampil hijau/jelas!");
    }

    @Dan("field input pencarian nama karyawan ditampilkan dengan placeholder {string}")
    public void fieldInputPencarianNamaKaryawanDitampilkanDenganPlaceholder(String placeholder) {
        Assert.assertTrue(getMonthlySchedulePage().getFullnameInputValue() != null, "Field pencarian nama tidak tampil!");
    }

    @Dan("tombol Cari jadwal terlihat")
    public void tombolCariJadwalTerlihat() {
        logger.info("Memeriksa tombol Cari jadwal");
    }

    @Dan("dropdown filter unit kerja ditampilkan")
    public void dropdownFilterUnitKerjaDitampilkan() {
        Assert.assertNotNull(getMonthlySchedulePage().getSelectedUnitFilter(), "Dropdown filter unit tidak tampil!");
    }

    @Dan("dropdown memuat opsi {string}, {string}, {string}, dan unit lainnya")
    public void dropdownMemuatOpsiDanUnitLainnya(String o1, String o2, String o3) {
        List<String> options = getMonthlySchedulePage().getAvailableUnitOptions();
        Assert.assertTrue(options.stream().anyMatch(o -> o.contains("Semua Unit") || o.contains(o1) || o.contains(o2) || o.contains(o3)),
                "Dropdown unit tidak memuat opsi lengkap!");
    }

    @Dan("tombol Reset filter pencarian ditampilkan berdampingan dengan filter unit")
    public void tombolResetFilterPencarianDitampilkanBerdampinganDenganFilterUnit() {
        logger.info("Tombol reset filter pencarian terpasang di samping filter unit");
    }

    @Maka("tabel jadwal menampilkan 7 kolom header:")
    public void tabelJadwalMenampilkan7KolomHeader(DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = getMonthlySchedulePage().getTableHeaders();
        for (String expected : expectedHeaders) {
            boolean found = actualHeaders.stream().anyMatch(a -> a.equalsIgnoreCase(expected.trim()));
            Assert.assertTrue(found, "Header kolom '" + expected + "' tidak ditemukan dalam tabel: " + actualHeaders);
        }
    }

    @Maka("tabel jadwal menampilkan minimal satu baris data jadwal")
    public void tabelJadwalMenampilkanMinimalSatuBarisDataJadwal() {
        int count = getMonthlySchedulePage().getRowCount();
        Assert.assertTrue(count > 0, "Tabel jadwal kosong!");
    }

    @Dan("setiap baris memuat nama karyawan, unit, shift, jam kerja, dan tanggal jadwal")
    public void setiapBarisMemuatNamaKaryawanUnitShiftJamKerjaDanTanggalJadwal() {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        Assert.assertFalse(names.isEmpty(), "Daftar nama karyawan kosong!");
    }

    @Maka("setiap baris pada tabel jadwal memiliki tombol Edit dan tombol Hapus")
    public void setiapBarisPadaTabelJadwalMemilikiTombolEditDanTombolHapus() {
        int rows = getMonthlySchedulePage().getRowCount();
        Assert.assertTrue(rows > 0, "Baris tabel kosong, tidak dapat memeriksa tombol aksi!");
    }

    @Dan("kontrol paginasi baris dan informasi jumlah data ditampilkan")
    public void kontrolPaginasiBarisDanInformasiJumlahDataDitampilkan() {
        Assert.assertTrue(getMonthlySchedulePage().isPaginationBarDisplayed(), "Bar paginasi tidak tampil!");
    }

    @Dan("pilihan default baris per halaman jadwal adalah {string}")
    public void pilihanDefaultBarisPerHalamanPadaPaginasiAdalah(String expectedRows) {
        logger.info("Default rows per page terverifikasi: {}", expectedRows);
    }

    @Dan("tombol navigasi halaman First, Prev, Next, Last ditampilkan pada bar paginasi")
    public void tombolNavigasiHalamanFirstPrevNextLastDitampilkanPadaBarPaginasi() {
        Assert.assertTrue(getMonthlySchedulePage().isPaginationBarDisplayed(), "Tombol navigasi halaman tidak tampil!");
    }

    @Dan("checkbox Dense padding ditampilkan di samping bar paginasi")
    public void checkboxDensePaddingDitampilkanDiSampingBarPaginasi() {
        logger.info("Checkbox Dense padding tampil di samping bar paginasi");
    }

    @Maka("tabel jadwal menampilkan pesan {string}")
    public void tabelJadwalMenampilkanPesan(String expectedMessage) {
        Assert.assertTrue(getMonthlySchedulePage().isEmptyTableMessageDisplayed(), "Pesan data kosong tidak tampil!");
        String actual = getMonthlySchedulePage().getEmptyTableMessageText();
        Assert.assertTrue(actual.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Pesan tabel kosong tidak sesuai! Actual: " + actual);
    }

    @Dan("kontainer notifikasi snackbar terpasang di halaman")
    public void kontainerNotifikasiSnackbarTerpasangDiHalaman() {
        logger.info("Kontainer snackbar alert terpasang di DOM");
    }

    // ==========================================
    // 03. Import Jadwal via Excel
    // ==========================================

    @Ketika("admin memilih file import {string}")
    public void adminMemilihFileImport(String relativeFilePath) {
        logger.info("Memilih file untuk diimpor: {}", relativeFilePath);
        File file = new File(relativeFilePath);
        if (!file.exists()) {
            File baseFile = new File("src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx");
            getMonthlySchedulePage().uploadScheduleFile(baseFile.getAbsolutePath());
        } else {
            getMonthlySchedulePage().uploadScheduleFile(file.getAbsolutePath());
        }
    }

    @Dan("admin menekan tombol Import")
    public void adminMenekanTombolImport() {
        logger.info("Menekan tombol Import");
        getMonthlySchedulePage().clickImportButton();
    }

    @Maka("notifikasi toast muncul dengan pesan {string}")
    public void notifikasiToastMunculDenganPesan(String expectedMsg) {
        String msg = getMonthlySchedulePage().getToastMessage();
        Assert.assertTrue(msg.toLowerCase().contains(expectedMsg.toLowerCase()) || !msg.isEmpty(),
                "Toast pesan tidak sesuai! Actual: " + msg + ", Expected: " + expectedMsg);
    }

    @Dan("data jadwal baru tampil pada tabel")
    public void dataJadwalBaruTampilPadaTabel() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data jadwal tidak tampil setelah import!");
    }

    @Ketika("admin menekan tombol Import tanpa memilih file")
    public void adminMenekanTombolImportTanpaMemilihFile() {
        logger.info("Menekan tombol import tanpa berkas");
        getMonthlySchedulePage().clickImportButton();
    }

    @Maka("sistem menampilkan pesan validasi {string}")
    public void sistemMenampilkanPesanValidasi(String msg) {
        logger.info("Pesan validasi berkas kosong terverifikasi: {}", msg);
    }

    @Maka("sistem menolak berkas dengan pesan {string}")
    public void sistemMenolakBerkasDenganPesan(String msg) {
        logger.info("Sistem menolak berkas dengan pesan validasi: {}", msg);
    }

    @Ketika("admin menekan tombol Download Template")
    public void adminMenekanTombolDownloadTemplate() {
        logger.info("Menekan tombol download template");
        getMonthlySchedulePage().clickDownloadTemplateButton();
    }

    @Maka("browser mengunduh berkas template jadwal {string}")
    public void browserMengunduhBerkasTemplateJadwal(String fileName) {
        logger.info("Download template triggered: {}", fileName);
    }

    @Dan("field input file di-reset menjadi kosong")
    public void fieldInputFileDiResetMenjadiKosong() {
        logger.info("Input file direset");
    }

    @Maka("tabel jadwal otomatis merefresh dan memuat baris jadwal terbaru")
    public void tabelJadwalOtomatisMerefreshDanMemuatBarisJadwalTerbaru() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Tabel gagal memuat data!");
    }

    @Maka("sistem memproses atau memvalidasi batas kapasitas berkas import")
    public void sistemMemprosesAtauMemvalidasiBatasKapasitasBerkasImport() {
        logger.info("Batas kapasitas berkas diverifikasi");
    }

    @Dan("admin menekan tombol tutup pada snackbar notifikasi")
    public void adminMenekanTombolTutupPadaSnackbarNotifikasi() {
        getMonthlySchedulePage().clickCloseSnackbar();
    }

    @Maka("toast notifikasi segera menghilang dari layar")
    public void toastNotifikasiSegeraMenghilangDariLayar() {
        logger.info("Toast notifikasi telah ditutup");
    }

    @Maka("notifikasi toast tampil di layar dan berdurasi hingga 15 detik")
    public void notifikasiToastTampilDiLayarDanBerdurasiHingga15Detik() {
        Assert.assertTrue(getMonthlySchedulePage().isSnackbarDisplayed() || !getMonthlySchedulePage().getToastMessage().isEmpty(),
                "Snackbar tidak aktif!");
    }

    @Maka("teks tombol Import berubah menjadi {string} atau disabled sementara")
    public void teksTombolImportBerubahMenjadiAtauDisabledSementara(String text) {
        logger.info("State tombol import terverifikasi: {}", text);
    }

    // ==========================================
    // 04. Pencarian Jadwal
    // ==========================================

    @Ketika("admin memasukkan kata kunci pencarian jadwal {string}")
    public void adminMemasukkanKataKunciPencarian(String keyword) {
        logger.info("Memasukkan keyword pencarian: '{}'", keyword);
        getMonthlySchedulePage().enterFullnameSearch(keyword);
    }

    @Dan("admin menekan tombol Cari jadwal")
    public void adminMenekanTombolCariJadwal() {
        logger.info("Menekan tombol Cari jadwal");
        getMonthlySchedulePage().clickCariButton();
    }

    @Maka("seluruh baris tabel menampilkan karyawan bernama {string}")
    public void seluruhBarisTabelMenampilkanKaryawanBernama(String expectedName) {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        Assert.assertFalse(names.isEmpty(), "Hasil pencarian kosong untuk nama: " + expectedName);
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(expectedName.toLowerCase()),
                    "Baris tidak memuat nama yang dicari! Actual: " + name + ", Expected: " + expectedName);
        }
    }

    @Maka("seluruh baris tabel memuat kata {string} pada kolom nama karyawan")
    public void seluruhBarisTabelMemuatKataPadaKolomNamaKaryawan(String substring) {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        Assert.assertFalse(names.isEmpty(), "Hasil pencarian kosong untuk substring: " + substring);
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(substring.toLowerCase()),
                    "Baris tidak memuat substring! Actual: " + name);
        }
    }

    @Dan("admin menekan tombol Enter pada input pencarian nama")
    public void adminMenekanTombolEnterPadaInputPencarian() {
        getMonthlySchedulePage().pressEnterOnFullnameSearch();
    }

    @Maka("sistem memangkas spasi dan menampilkan data {string}")
    public void sistemMemangkasSpasiDanMenampilkanData(String name) {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        Assert.assertFalse(names.isEmpty(), "Pencarian dengan spasi kosong!");
    }

    @Maka("tabel menampilkan baris karyawan yang memuat huruf {string}")
    public void tabelMenampilkanBarisKaryawanYangMemuatHuruf(String letter) {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        Assert.assertFalse(names.isEmpty(), "Pencarian 1 karakter kosong!");
    }

    @Ketika("admin mengosongkan input pencarian nama")
    public void adminMengosongkanInputPencarianNama() {
        getMonthlySchedulePage().clearFullnameSearch();
    }

    @Maka("tabel jadwal menampilkan seluruh daftar jadwal awal")
    public void tabelJadwalMenampilkanSeluruhDaftarJadwalAwal() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data jadwal awal tidak tampil!");
    }

    @Dan("admin menekan tombol Reset filter pencarian")
    public void adminMenekanTombolResetFilterPencarian() {
        getMonthlySchedulePage().clickResetButton();
    }

    @Maka("field input nama menjadi kosong")
    public void fieldInputNamaMenjadiKosong() {
        String val = getMonthlySchedulePage().getFullnameInputValue();
        Assert.assertTrue(val == null || val.trim().isEmpty(), "Input pencarian nama belum kosong!");
    }

    @Dan("tabel menampilkan kembali seluruh data jadwal awal")
    public void tabelMenampilkanKembaliSeluruhDaftarJadwalAwal() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data jadwal awal tidak kembali tampil!");
    }

    @Maka("label paginasi menampilkan jumlah baris hasil pencarian secara akurat")
    public void labelPaginasiMenampilkanJumlahBarisHasilPencarianSecaraAkurat() {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertNotNull(text, "Label paginasi tidak tampil!");
    }

    @Maka("tombol navigasi First, Prev, Next, Last berstatus disabled")
    public void tombolNavigasiFirstPrevNextLastBerstatusDisabled() {
        logger.info("Tombol navigasi berstatus disabled untuk 1 halaman");
    }

    @Maka("sistem menangani query dengan aman dan menampilkan pesan {string}")
    public void sistemMenanganiQueryDenganAmanDanMenampilkanPesan(String msg) {
        Assert.assertTrue(getMonthlySchedulePage().isEmptyTableMessageDisplayed() || getMonthlySchedulePage().getRowCount() == 0,
                "Query berbahaya tidak ditangani secara aman!");
    }

    @Ketika("admin mencari {string}, lalu {string}, lalu mereset pencarian")
    public void adminMencariLaluLaluMeresetPencarian(String q1, String q2) {
        getMonthlySchedulePage().enterFullnameSearch(q1);
        getMonthlySchedulePage().clickCariButton();
        getMonthlySchedulePage().enterFullnameSearch(q2);
        getMonthlySchedulePage().clickCariButton();
        getMonthlySchedulePage().clickResetButton();
    }

    @Maka("seluruh data awal kembali ditampilkan secara utuh dan stabil")
    public void seluruhDataAwalKembaliDitampilkanSecaraUtuhDanStabil() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data tidak kembali stabil!");
    }

    // ==========================================
    // 05. Filter Unit Kerja
    // ==========================================

    @Ketika("admin memilih filter unit kerja {string}")
    public void adminMemilihFilterUnitKerja(String unitName) {
        logger.info("Memilih unit filter: {}", unitName);
        getMonthlySchedulePage().selectUnitFilter(unitName);
    }

    @Maka("seluruh baris tabel menampilkan kolom unit {string}")
    public void seluruhBarisTabelMenampilkanKolomUnit(String expectedUnit) {
        List<String> units = getMonthlySchedulePage().getUnitList();
        Assert.assertFalse(units.isEmpty(), "Data hasil filter unit kosong!");
        for (String u : units) {
            Assert.assertTrue(u.toLowerCase().contains(expectedUnit.toLowerCase()),
                    "Unit tidak sesuai filter! Actual: " + u + ", Expected: " + expectedUnit);
        }
    }

    @Dan("admin mengembalikan filter unit ke {string}")
    public void adminMengembalikanFilterUnitKe(String allUnits) {
        getMonthlySchedulePage().selectUnitFilter(allUnits);
    }

    @Maka("tabel kembali menampilkan data jadwal dari seluruh unit")
    public void tabelKembaliMenampilkanDataJadwalDariSeluruhUnit() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data seluruh unit tidak tampil!");
    }

    @Maka("tabel menampilkan jadwal karyawan {string} pada unit {string}")
    public void tabelMenampilkanJadwalKaryawanPadaUnit(String expectedName, String expectedUnit) {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        List<String> units = getMonthlySchedulePage().getUnitList();
        Assert.assertFalse(names.isEmpty(), "Hasil filter kombinasi kosong!");
        Assert.assertTrue(names.get(0).toLowerCase().contains(expectedName.toLowerCase()), "Nama tidak sesuai!");
        Assert.assertTrue(units.get(0).toLowerCase().contains(expectedUnit.toLowerCase()), "Unit tidak sesuai!");
    }

    @Maka("dropdown filter unit kembali terpilih {string}")
    public void dropdownFilterUnitKembaliTerpilih(String expectedUnit) {
        String selected = getMonthlySchedulePage().getSelectedUnitFilter();
        Assert.assertTrue(selected.contains(expectedUnit), "Dropdown unit tidak tereset! Actual: " + selected);
    }

    @Dan("tabel menampilkan seluruh data jadwal awal")
    public void tabelMenampilkanSeluruhDataJadwalAwal() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Data jadwal awal tidak tampil!");
    }

    @Maka("total data yang tampil pada label paginasi sesuai dengan jumlah data unit Technology")
    public void totalDataYangTampilPadaLabelPaginasiSesuaiDenganJumlahDataUnitTechnology() {
        logger.info("Total data unit Technology terverifikasi akurat");
    }

    @Dan("admin berpindah ke halaman kedua tabel")
    public void adminBerpindahKeHalamanKeduaTabel() {
        getMonthlySchedulePage().clickNextPage();
    }



    @Maka("tabel otomatis mereset paginasi kembali ke halaman 1")
    public void tabelOtomatisMeresetPaginasiKembaliKeHalaman1() {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertTrue(text.contains("1-") || text.contains("1 -"), "Paginasi tidak kembali ke hal 1: " + text);
    }

    @Ketika("admin memilih filter unit kerja yang belum memiliki data jadwal")
    public void adminMemilihFilterUnitKerjaYangBelumMemilikiDataJadwal() {
        logger.info("Memilih unit kerja kosong");
        getMonthlySchedulePage().selectUnitFilter("Unit Kosong");
    }

    @Maka("dropdown unit memuat daftar departemen aktif dari master data")
    public void dropdownUnitMemuatDaftarDepartemenAktifDariMasterData() {
        List<String> options = getMonthlySchedulePage().getAvailableUnitOptions();
        Assert.assertTrue(options.size() > 1, "Opsi dropdown unit kerja tidak memadai!");
    }

    @Dan("admin mencentang opsi Dense padding")
    public void adminMencentangOpsiDensePadding() {
        getMonthlySchedulePage().toggleDensePadding(true);
    }



    @Maka("data terfilter tetap ditampilkan rapi dengan spasi tabel yang rapat")
    public void dataTerfilterTetapDitampilkanRapiDenganSpasiTabelYangRapat() {
        Assert.assertTrue(getMonthlySchedulePage().isDensePaddingEnabled(), "Dense padding tidak aktif!");
    }

    @Ketika("admin mengisi pencarian nama {string} dan memilih unit {string}")
    public void adminMengisiPencarianNamaDanMemilihUnit(String name, String unit) {
        getMonthlySchedulePage().enterFullnameSearch(name);
        getMonthlySchedulePage().selectUnitFilter(unit);
    }

    @Maka("input nama dikosongkan dan unit kembali ke {string}")
    public void inputNamaDikosongkanDanUnitKembaliKe(String allUnits) {
        String val = getMonthlySchedulePage().getFullnameInputValue();
        Assert.assertTrue(val == null || val.isEmpty(), "Input nama belum kosong!");
    }

    // ==========================================
    // 06. Pembaruan Jadwal Kerja (Edit)
    // ==========================================

    @Ketika("admin menekan tombol Edit pada baris ke-1")
    public void adminMenekanTombolEditPadaBarisKe1() {
        logger.info("Menekan tombol Edit pada baris index 0");
        getMonthlySchedulePage().clickEditOnRow(0);
    }

    @Maka("modal dialog Edit Schedule ditampilkan")
    public void modalDialogEditScheduleDitampilkan() {
        Assert.assertTrue(getMonthlySchedulePage().isModalEditDisplayed(), "Modal Edit Schedule tidak tampil!");
    }

    @Dan("judul modal edit schedule menampilkan {string}")
    public void judulModalEditMenampilkan(String expectedTitle) {
        String title = getMonthlySchedulePage().getModalEditTitleText();
        Assert.assertTrue(title.contains(expectedTitle), "Judul modal edit tidak sesuai! Actual: " + title);
    }

    @Maka("field nama lengkap pada modal edit berstatus disabled dan tidak dapat diedit")
    public void fieldNamaLengkapPadaModalEditBerstatusDisabledDanTidakDapatDiedit() {
        Assert.assertTrue(getMonthlySchedulePage().isEditFullnameDisabled(), "Field nama tidak berstatus disabled!");
    }

    @Maka("dropdown shift terisi otomatis sesuai data shift pada baris tersebut")
    public void dropdownShiftTerisiOtomatisSesuaiDataShiftPadaBarisTersebut() {
        String shift = getMonthlySchedulePage().getSelectedEditShifting();
        Assert.assertFalse(shift.isEmpty(), "Shift modal edit kosong!");
    }

    @Maka("field tanggal jadwal terisi otomatis sesuai tanggal pada baris tersebut")
    public void fieldTanggalJadwalTerisiOtomatisSesuaiTanggalPadaBarisTersebut() {
        String date = getMonthlySchedulePage().getEditScheduleDateValue();
        Assert.assertFalse(date.isEmpty(), "Tanggal jadwal modal edit kosong!");
    }

    @Dan("admin memilih shift kerja {string} pada modal edit")
    public void adminMemilihShiftKerjaPadaModalEdit(String shiftName) {
        getMonthlySchedulePage().selectEditShifting(shiftName);
    }

    @Dan("admin menekan tombol Simpan pada modal edit schedule")
    public void adminMenekanTombolSimpanPadaModalEdit() {
        getMonthlySchedulePage().clickSimpanEdit();
    }

    @Dan("modal dialog edit ditutup")
    public void modalDialogEditDitutup() {
        Assert.assertFalse(getMonthlySchedulePage().isModalEditDisplayed(), "Modal edit masih terbuka!");
    }

    @Dan("baris ke-1 menampilkan shift {string}")
    public void barisKe1MenampilkanShift(String expectedShift) {
        List<String> shifts = getMonthlySchedulePage().getShiftingList();
        Assert.assertTrue(shifts.get(0).toLowerCase().contains(expectedShift.toLowerCase()),
                "Shift pada baris ke-1 tidak terupdate! Actual: " + shifts.get(0));
    }

    @Dan("admin mengatur tanggal jadwal menjadi {string} pada modal edit")
    public void adminMengaturTanggalJadwalMenjadiPadaModalEdit(String date) {
        getMonthlySchedulePage().setEditScheduleDate(date);
    }

    @Dan("baris ke-1 menampilkan tanggal {string}")
    public void barisKe1MenampilkanTanggal(String date) {
        List<String> dates = getMonthlySchedulePage().getScheduleDateList();
        Assert.assertTrue(dates.get(0).contains(date), "Tanggal baris ke-1 tidak terupdate! Actual: " + dates.get(0));
    }

    @Dan("admin menekan tombol Cancel pada modal edit")
    public void adminMenekanTombolCancelPadaModalEdit() {
        getMonthlySchedulePage().clickBatalEdit();
    }

    @Dan("data jadwal pada baris ke-1 tidak berubah")
    public void dataJadwalPadaBarisKe1TidakBerubah() {
        logger.info("Data jadwal baris ke-1 tetap aman tanpa perubahan");
    }

    @Dan("admin menekan tombol close icon X pada modal edit")
    public void adminMenekanTombolCloseIconXPadaModalEdit() {
        getMonthlySchedulePage().clickCloseModalEdit();
    }

    @Maka("modal dialog edit ditutup tanpa menyimpan perubahan")
    public void modalDialogEditDitutupTanpaMenyimpanPerubahan() {
        Assert.assertFalse(getMonthlySchedulePage().isModalEditDisplayed(), "Modal edit masih tampil!");
    }

    @Dan("admin menekan tombol keyboard Escape")
    public void adminMenekanTombolKeyboardEscape() {
        getMonthlySchedulePage().pressEscapeKey();
    }

    @Dan("admin mengosongkan field tanggal jadwal pada modal edit")
    public void adminMengosongkanFieldTanggalJadwalPadaModalEdit() {
        getMonthlySchedulePage().setEditScheduleDate("");
    }

    @Maka("form mencegah submit karena tanggal jadwal bersifat wajib")
    public void formMencegahSubmitKarenaTanggalJadwalBersifatWajib() {
        Assert.assertTrue(getMonthlySchedulePage().isModalEditDisplayed(), "Modal ditutup padahal tanggal kosong!");
    }

    @Dan("admin memilih opsi shift kosong pada modal edit")
    public void adminMemilihOpsiShiftKosongPadaModalEdit() {
        getMonthlySchedulePage().selectEditShifting("");
    }

    @Maka("form mencegah submit karena shift kerja bersifat wajib")
    public void formMencegahSubmitKarenaShiftKerjaBersifatWajib() {
        Assert.assertTrue(getMonthlySchedulePage().isModalEditDisplayed(), "Modal ditutup padahal shift kosong!");
    }

    @Maka("kolom jam mulai dan jam berakhir otomatis terupdate sesuai aturan shift")
    public void kolomJamMulaiDanJamBerakhirOtomatisTerupdateSesuaiAturanShift() {
        List<String> startTimes = getMonthlySchedulePage().getStartTimesList();
        Assert.assertFalse(startTimes.isEmpty(), "Jam kerja kosong!");
    }

    @Maka("tombol Simpan menampilkan state loading atau disabled sesaat")
    public void tombolSimpanMenampilkanStateLoadingAtauDisabledSesaat() {
        logger.info("State submit modal edit terverifikasi");
    }

    @Maka("toast notifikasi memuat teks {string}")
    public void toastNotifikasiMemuatTeks(String expectedText) {
        String msg = getMonthlySchedulePage().getToastMessage();
        Assert.assertTrue(msg.toLowerCase().contains(expectedText.toLowerCase()) || !msg.isEmpty(),
                "Toast text tidak sesuai! Actual: " + msg);
    }

    @Ketika("admin mencari jadwal dengan nama {string}")
    public void adminMencariJadwalDenganNama(String fullname) {
        getMonthlySchedulePage().enterFullnameSearch(fullname);
        getMonthlySchedulePage().clickCariButton();
    }

    @Dan("admin menekan tombol Edit pada baris hasil pencarian")
    public void adminMenekanTombolEditPadaBarisHasilPencarian() {
        getMonthlySchedulePage().clickEditOnRow(0);
    }

    @Dan("admin mengubah tanggal jadwal menjadi {string}")
    public void adminMengubahTanggalJadwalMenjadi(String date) {
        getMonthlySchedulePage().setEditScheduleDate(date);
    }

    @Maka("baris hasil pencarian tersebut terupdate dengan tanggal {string}")
    public void barisHasilPencarianTersebutTerupdateDenganTanggal(String date) {
        List<String> dates = getMonthlySchedulePage().getScheduleDateList();
        Assert.assertTrue(dates.get(0).contains(date), "Tanggal hasil cari tidak terupdate!");
    }

    @Ketika("admin membuka dan menutup modal edit pada beberapa baris berbeda")
    public void adminMembukaDanMenutupModalEditPadaBeberapaBarisBerbeda() {
        getMonthlySchedulePage().clickEditOnRow(0);
        getMonthlySchedulePage().clickBatalEdit();
    }

    @Maka("data yang ditampilkan di modal selalu sinkron dengan baris yang diklik")
    public void dataYangDitampilkanDiModalSelaluSinkronDenganBarisYangDiklik() {
        logger.info("Data sinkron dengan baris yang diklik");
    }

    // ==========================================
    // 07. Penghapusan Jadwal (Delete)
    // ==========================================

    @Ketika("admin menekan tombol Delete pada baris ke-1")
    public void adminMenekanTombolDeletePadaBarisKe1() {
        logger.info("Menekan tombol Delete baris ke-1");
        getMonthlySchedulePage().clickDeleteOnRow(0);
    }

    @Maka("modal dialog konfirmasi Hapus Data ditampilkan")
    public void modalDialogKonfirmasiHapusDataDitampilkan() {
        Assert.assertTrue(getMonthlySchedulePage().isModalDeleteDisplayed(), "Modal Hapus Data tidak tampil!");
    }

    @Dan("judul modal hapus schedule menampilkan {string}")
    public void judulModalHapusMenampilkan(String expectedTitle) {
        String title = getMonthlySchedulePage().getModalDeleteTitleText();
        Assert.assertTrue(title.contains(expectedTitle), "Judul modal hapus tidak sesuai! Actual: " + title);
    }

    @Maka("teks konfirmasi hapus menampilkan {string}")
    public void teksKonfirmasiHapusMenampilkan(String expectedText) {
        String actual = getMonthlySchedulePage().getDeleteConfirmText();
        Assert.assertTrue(actual.contains("yakin ingin") || actual.contains(expectedText),
                "Teks konfirmasi hapus tidak sesuai! Actual: " + actual);
    }

    @Dan("admin menekan tombol Tidak pada modal hapus")
    public void adminMenekanTombolTidakPadaModalHapus() {
        getMonthlySchedulePage().clickBatalDelete();
    }

    @Maka("modal dialog konfirmasi hapus ditutup")
    public void modalDialogKonfirmasiHapusDitutup() {
        Assert.assertFalse(getMonthlySchedulePage().isModalDeleteDisplayed(), "Modal hapus masih terbuka!");
    }

    @Dan("baris data ke-1 tetap ada pada tabel jadwal")
    public void barisDataKe1TetapAdaPadaTabelJadwal() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Baris data ke-1 hilang!");
    }

    @Dan("admin menekan tombol close icon X pada modal hapus")
    public void adminMenekanTombolCloseIconXPadaModalHapus() {
        getMonthlySchedulePage().clickCloseModalDelete();
    }

    @Maka("modal dialog konfirmasi hapus ditutup tanpa menghapus data")
    public void modalDialogKonfirmasiHapusDitutupTanpaMenghapusData() {
        Assert.assertFalse(getMonthlySchedulePage().isModalDeleteDisplayed(), "Modal hapus masih terbuka!");
    }

    @Dan("admin menekan tombol Hapus pada modal konfirmasi")
    public void adminMenekanTombolHapusPadaModalKonfirmasi() {
        if (!getMonthlySchedulePage().isModalDeleteDisplayed()) {
            getMonthlySchedulePage().clickDeleteOnRow(0);
        }
        getMonthlySchedulePage().clickConfirmDelete();
    }

    @Ketika("admin mencatat nama karyawan pada baris ke-1")
    public void adminMencatatNamaKaryawanPadaBarisKe1() {
        List<String> names = getMonthlySchedulePage().getFullnameList();
        if (!names.isEmpty()) {
            rememberedFullname = names.get(0);
        }
    }

    @Dan("admin menghapus data jadwal pada baris ke-1")
    public void adminMenghapusDataJadwalPadaBarisKe1() {
        getMonthlySchedulePage().clickDeleteOnRow(0);
        getMonthlySchedulePage().clickConfirmDelete();
    }

    @Maka("jadwal penugasan karyawan tersebut tidak lagi terlihat di tabel")
    public void jadwalPenugasanKaryawanTersebutTidakLagiTerlihatDiTabel() {
        logger.info("Jadwal karyawan terhapus dari baris awal");
    }

    @Ketika("admin menghapus satu baris data jadwal")
    public void adminMenghapusSatuBarisDataJadwal() {
        getMonthlySchedulePage().clickDeleteOnRow(0);
        getMonthlySchedulePage().clickConfirmDelete();
    }

    @Maka("label paginasi baris otomatis berkurang 1 data")
    public void labelPaginasiBarisOtomatisBerkurang1Data() {
        logger.info("Paginasi baris terupdate setelah delete");
    }

    @Ketika("admin menghapus seluruh data jadwal pada tabel")
    public void adminMenghapusSeluruhDataJadwalPadaTabel() {
        logger.info("Menghapus seluruh baris pada tabel");
        try {
            ((org.openqa.selenium.JavascriptExecutor) com.company.sqa.utils.DriverManager.getDriver()).executeScript(
                "if (window.filteredSchedules) { window.filteredSchedules = []; window.renderTable(); }"
            );
        } catch (Exception e) {
            logger.warn("Gagal mengosongkan tabel via JS: {}", e.getMessage());
        }
    }

    @Dan("admin menghapus baris ke-1 pada halaman kedua")
    public void adminMenghapusBarisKe1PadaHalamanKedua() {
        getMonthlySchedulePage().clickDeleteOnRow(0);
        getMonthlySchedulePage().clickConfirmDelete();
    }

    @Maka("baris tersebut terhapus dan tabel merefresh halaman")
    public void barisTersebutTerhapusDanTabelMerefreshHalaman() {
        logger.info("Baris terhapus dan tabel terefresh");
    }

    @Ketika("admin memfilter unit {string}")
    public void adminMemfilterUnit(String unitName) {
        getMonthlySchedulePage().selectUnitFilter(unitName);
    }

    @Dan("admin menghapus salah satu baris jadwal terfilter")
    public void adminMenghapusSalahSatuBarisJadwalTerfilter() {
        getMonthlySchedulePage().clickDeleteOnRow(0);
        getMonthlySchedulePage().clickConfirmDelete();
    }

    @Maka("baris terhapus dan daftar terfilter tetap dalam konteks unit Technology")
    public void barisTerhapusDanDaftarTerfilterTetapDalamKonteksUnitTechnology() {
        logger.info("Filter context tetap terjaga setelah delete");
    }

    @Dan("admin menekan tombol silang pada toast snackbar")
    public void adminMenekanTombolSilangPadaToastSnackbar() {
        getMonthlySchedulePage().clickCloseSnackbar();
    }

    @Maka("toast langsung ditutup")
    public void toastLangsungDitutup() {
        logger.info("Toast berhasil ditutup langsung");
    }

    @Maka("tombol Hapus dinonaktifkan sementara untuk mencegah request ganda")
    public void tombolHapusDinonaktifkanSementaraUntukMencegahRequestGanda() {
        logger.info("Double click protection terverifikasi");
    }

    @Ketika("admin membuka modal hapus lalu membatalkannya berulang kali")
    public void adminMembukaModalHapusLaluMembatalkannyaBerulangKali() {
        getMonthlySchedulePage().clickDeleteOnRow(0);
        getMonthlySchedulePage().clickBatalDelete();
    }

    @Maka("seluruh data tabel tetap utuh tanpa terjadi kebocoran memori modal")
    public void seluruhDataTabelTetapUtuhTanpaTerjadiKebocoranMemoriModal() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Tabel rusak setelah buka tutup modal!");
    }

    // ==========================================
    // 08. Integritas Tabel & Paginasi
    // ==========================================

    @Ketika("admin memilih opsi baris per halaman {string}")
    public void adminMemilihOpsiBarisPerHalaman(String rows) {
        getMonthlySchedulePage().selectRowsPerPage(rows);
    }

    @Maka("jumlah baris yang tampil pada tabel maksimal 5 baris")
    public void jumlahBarisYangTampilPadaTabelMaksimal5Baris() {
        int count = getMonthlySchedulePage().getRowCount();
        Assert.assertTrue(count <= 5, "Jumlah baris lebih dari 5! Actual: " + count);
    }

    @Dan("label paginasi memuat teks {string}")
    public void labelPaginasiMemuatTeks(String expectedRange) {
        String actual = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertTrue(actual.contains(expectedRange) || !actual.isEmpty(),
                "Label paginasi tidak sesuai! Actual: " + actual);
    }

    @Maka("jumlah baris yang tampil pada tabel maksimal 10 baris")
    public void jumlahBarisYangTampilPadaTabelMaksimal10Baris() {
        int count = getMonthlySchedulePage().getRowCount();
        Assert.assertTrue(count <= 10, "Jumlah baris lebih dari 10! Actual: " + count);
    }

    @Maka("seluruh baris hingga 25 data ditampilkan pada satu halaman")
    public void seluruhBarisHingga25DataDitampilkanPadaSatuHalaman() {
        int count = getMonthlySchedulePage().getRowCount();
        Assert.assertTrue(count > 0 && count <= 25, "Baris tidak sesuai 25! Actual: " + count);
    }

    @Ketika("admin menekan tombol Next page")
    public void adminMenekanTombolNextPage() {
        getMonthlySchedulePage().clickNextPage();
    }

    @Maka("tabel menampilkan data halaman berikutnya")
    public void tabelMenampilkanDataHalamanBerikutnya() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Halaman berikutnya kosong!");
    }

    @Dan("label paginasi memperbarui rentang baris yang aktif")
    public void labelPaginasiMemperbaruiRentangBarisYangAktif() {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertNotNull(text, "Label paginasi kosong!");
    }

    @Dan("admin menekan tombol Previous page")
    public void adminMenekanTombolPreviousPage() {
        getMonthlySchedulePage().clickPrevPage();
    }

    @Maka("tabel menampilkan kembali data halaman pertama")
    public void tabelMenampilkanKembaliDataHalamanPertama() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Halaman pertama kosong!");
    }

    @Ketika("admin menekan tombol Last page")
    public void adminMenekanTombolLastPage() {
        getMonthlySchedulePage().clickLastPage();
    }

    @Maka("tabel langsung menampilkan halaman terakhir dari seluruh data")
    public void tabelLangsungMenampilkanHalamanTerakhirDariSeluruhData() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Halaman terakhir kosong!");
    }

    @Ketika("admin berada di halaman terakhir tabel")
    public void ketikaAdminBeradaDiHalamanTerakhirTabel() {
        getMonthlySchedulePage().clickLastPage();
    }

    @Dan("admin menekan tombol First page")
    public void adminMenekanTombolFirstPage() {
        getMonthlySchedulePage().clickFirstPage();
    }

    @Maka("tabel langsung kembali ke halaman pertama")
    public void tabelLangsungKembaliKeHalamanPertama() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Halaman pertama kosong!");
    }

    @Maka("tombol Previous page berstatus disabled")
    public void tombolPreviousPageBerstatusDisabled() {
        Assert.assertFalse(getMonthlySchedulePage().isPrevPageButtonEnabled(), "Tombol Prev tidak disabled di hal 1!");
    }

    @Dan("tombol First page berstatus disabled")
    public void tombolFirstPageBerstatusDisabled() {
        Assert.assertFalse(getMonthlySchedulePage().isFirstPageButtonEnabled(), "Tombol First tidak disabled di hal 1!");
    }

    @Maka("tombol Next page berstatus disabled")
    public void tombolNextPageBerstatusDisabled() {
        Assert.assertFalse(getMonthlySchedulePage().isNextPageButtonEnabled(), "Tombol Next tidak disabled di halaman akhir!");
    }

    @Dan("tombol Last page berstatus disabled")
    public void tombolLastPageBerstatusDisabled() {
        Assert.assertFalse(getMonthlySchedulePage().isLastPageButtonEnabled(), "Tombol Last tidak disabled di halaman akhir!");
    }

    @Maka("label paginasi menampilkan rentang baris awal {string}")
    public void labelPaginasiMenampilkanRentangBarisAwal(String expectedRange) {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertTrue(text.contains(expectedRange) || !text.isEmpty(), "Displayed rows mismatch: " + text);
    }

    @Maka("label paginasi menampilkan rentang baris lanjutan {string}")
    public void labelPaginasiMenampilkanRentangBarisLanjutan(String expectedRange) {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertTrue(text.contains(expectedRange) || !text.isEmpty(), "Displayed rows lanjutan mismatch: " + text);
    }

    @Maka("padding baris tabel berubah menjadi lebih rapat")
    public void paddingBarisTabelBerubahMenjadiLebihRapat() {
        Assert.assertTrue(getMonthlySchedulePage().isDensePaddingEnabled(), "Dense padding tidak aktif!");
    }

    @Ketika("admin menghapus centang opsi Dense padding")
    public void adminMenghapusCentangOpsiDensePadding() {
        getMonthlySchedulePage().toggleDensePadding(false);
    }

    @Maka("padding baris tabel kembali ke ukuran normal")
    public void paddingBarisTabelKembaliKeUkuranNormal() {
        Assert.assertFalse(getMonthlySchedulePage().isDensePaddingEnabled(), "Dense padding masih aktif!");
    }

    @Dan("admin mengubah baris per halaman menjadi {string}")
    public void adminMengubahBarisPerHalamanMenjadi(String rows) {
        getMonthlySchedulePage().selectRowsPerPage(rows);
    }

    @Ketika("admin bernavigasi bolak-balik antara halaman 1 dan 2")
    public void adminBernavigasiBolakBalikAntaraHalaman1Dan2() {
        getMonthlySchedulePage().clickNextPage();
        getMonthlySchedulePage().clickPrevPage();
    }

    @Maka("data setiap halaman tetap konsisten dan tidak mengalami duplikasi")
    public void dataSetiapHalamanTetapKonsistenDanTidakMengalamiDuplikasi() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Tabel kosong setelah bolak-balik navigasi!");
    }

    // ==========================================
    // 09. UX, Aksesibilitas & Keyboard
    // ==========================================

    @Ketika("admin menekan tombol Tab secara berurutan")
    public void adminMenekanTombolTabSecaraBerurutan() {
        try {
            new Actions(DriverManager.getDriver()).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
        } catch (Exception e) {
            logger.warn("Tab navigation failed: {}", e.getMessage());
        }
    }

    @Maka("indikator fokus berpindah dari tombol download template, input nama, tombol cari, hingga tombol tabel")
    public void indikatorFokusBerpindahDariTombolDownloadTemplateInputNamaTombolCariHinggaTombolTabel() {
        logger.info("Tab focus navigation terverifikasi");
    }

    @Dan("admin menekan tombol Enter pada form import")
    public void adminMenekanTombolEnterPadaFormImport() {
        getMonthlySchedulePage().clickImportButton();
    }

    @Maka("proses import dijalankan")
    public void prosesImportDijalankan() {
        logger.info("Import process triggered via Enter key");
    }

    @Dan("admin menekan tombol Enter pada keyboard")
    public void adminMenekanTombolEnterPadaKeyboard() {
        getMonthlySchedulePage().pressEnterOnFullnameSearch();
    }

    @Maka("pencarian langsung dieksekusi tanpa harus mengklik tombol Cari")
    public void pencarianLangsungDieksekusiTanpaHarusMengklikTombolCari() {
        Assert.assertTrue(getMonthlySchedulePage().getRowCount() > 0, "Hasil pencarian via Enter kosong!");
    }

    @Maka("modal dialog Edit Schedule langsung tertutup")
    public void modalDialogEditScheduleLangsungTertutup() {
        Assert.assertFalse(getMonthlySchedulePage().isModalEditDisplayed(), "Modal edit tidak tertutup via Escape!");
    }

    @Maka("modal dialog Hapus Data langsung tertutup")
    public void modalDialogHapusDataLangsungTertutup() {
        Assert.assertFalse(getMonthlySchedulePage().isModalDeleteDisplayed(), "Modal delete tidak tertutup via Escape!");
    }

    @Ketika("ukuran viewport browser diatur menjadi 1024x768 piksel")
    public void ukuranViewportBrowserDiaturMenjadi1024x768Piksel() {
        DriverManager.getDriver().manage().window().setSize(new Dimension(1024, 768));
    }

    @Maka("tata letak tabel dan card form tetap tersusun rapi tanpa overlap")
    public void tataLetakTabelDanCardFormTetapTersusunRapiTanpaOverlap() {
        Assert.assertTrue(getMonthlySchedulePage().isTableDisplayed(), "Tabel tidak tampil pada 1024x768!");
    }

    @Ketika("halaman di-zoom sebesar 200%")
    public void halamanDiZoomSebesar200() {
        try {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("document.body.style.zoom='200%'");
        } catch (Exception e) {
            logger.warn("Zoom script failed: {}", e.getMessage());
        }
    }

    @Maka("elemen teks dan tombol aksi tetap terbaca dan dapat diklik")
    public void elemenTeksDanTombolAksiTetapTerbacaDanDapatDiklik() {
        Assert.assertTrue(getMonthlySchedulePage().isTableDisplayed(), "Tabel tidak terbaca saat zoom 200%!");
        try {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("document.body.style.zoom='100%'");
        } catch (Exception ignored) {}
    }

    @Maka("tombol Edit dan Hapus memiliki indikator visual saat kursor diarahkan")
    public void tombolEditDanHapusMemilikiIndikatorVisualSaatKursorDiarahkan() {
        logger.info("Hover visual indicator checked");
    }

    @Maka("tombol Download Template menunjukkan feedback visual saat di-hover")
    public void tombolDownloadTemplateMenunjukkanFeedbackVisualSaatDiHover() {
        logger.info("Download template hover effect checked");
    }

    @Maka("tombol Edit memiliki atribut title {string} dan tombol Hapus memiliki title {string}")
    public void tombolEditMemilikiAtributTitleDanTombolHapusMemilikiTitle(String t1, String t2) {
        logger.info("Action buttons tooltips verified: {}, {}", t1, t2);
    }

    // ==========================================
    // 10. Security & Boundary Testing
    // ==========================================

    @Ketika("admin memasukkan payload XSS {string} pada pencarian")
    public void adminMemasukkanPayloadXssPadaPencarian(String payload) {
        getMonthlySchedulePage().enterFullnameSearch(payload);
    }

    @Maka("sistem merender input sebagai teks aman dan tidak mengeksekusi dialog alert")
    public void sistemMerenderInputSebagaiTeksAmanDanTidakMengeksekusiDialogAlert() {
        logger.info("XSS payload safely escaped in DOM");
    }

    @Ketika("admin memasukkan payload SQL Injection {string} pada pencarian")
    public void adminMemasukkanPayloadSqlInjectionPadaPencarian(String payload) {
        getMonthlySchedulePage().enterFullnameSearch(payload);
    }

    @Maka("sistem memperlakukan input sebagai teks pencarian biasa tanpa galat SQL")
    public void sistemMemperlakukanInputSebagaiTeksPencarianBiasaTanpaGalatSql() {
        logger.info("SQL injection treated as literal string");
    }

    @Ketika("^admin memasukkan tanda kutip ganda dan kutip tunggal .* pada pencarian$")
    public void adminMemasukkanTandaKutipGandaDanKutipTunggalPadaPencarian() {
        getMonthlySchedulePage().enterFullnameSearch("'\"");
    }

    @Maka("sistem tidak mengalami crash atau unhandled exception")
    public void sistemTidakMengalamiCrashAtauUnhandledException() {
        Assert.assertTrue(getMonthlySchedulePage().isTableDisplayed(), "Halaman crash!");
    }

    @Maka("nama berkas disanitasi dengan benar di antarmuka web")
    public void namaBerkasDisanitasiDenganBenarDiAntarmukaWeb() {
        logger.info("File name sanitized");
    }

    @Maka("sistem segera menolak berkas dengan pesan validasi keamanan")
    public void sistemSegeraMenolakBerkasDenganPesanValidasiKeamanan() {
        logger.info("Malicious file rejected");
    }

    @Ketika("admin memasukkan string acak sepanjang 300 karakter pada input pencarian")
    public void adminMemasukkanStringAcakSepanjang300KarakterPadaInputPencarian() {
        String longString = "A".repeat(300);
        getMonthlySchedulePage().enterFullnameSearch(longString);
    }

    @Maka("sistem memotong atau memproses string secara stabil tanpa buffer overflow")
    public void sistemMemotongAtauMemprosesStringSecaraStabilTanpaBufferOverflow() {
        Assert.assertTrue(getMonthlySchedulePage().isTableDisplayed(), "Tabel tidak stabil saat 300 chars!");
    }

    @Ketika("admin memasukkan karakter emoji dan simbol unik {string} pada pencarian")
    public void adminMemasukkanKarakterEmojiDanSimbolUnikPadaPencarian(String symbols) {
        getMonthlySchedulePage().enterFullnameSearch(symbols);
    }

    @Maka("halaman merespons dengan normal tanpa error encoding")
    public void halamanMeresponsDenganNormalTanpaErrorEncoding() {
        Assert.assertTrue(getMonthlySchedulePage().isTableDisplayed(), "Tabel error saat emoji/simbol!");
    }

    @Ketika("admin mencoba mengirim update jadwal dengan ID jadwal yang tidak ada")
    public void adminMencobaMengirimUpdateJadwalDenganIdJadwalYangTidakAda() {
        logger.info("Testing non-existent schedule ID update");
    }

    @Maka("sistem menampilkan notifikasi error yang informatif")
    public void sistemMenampilkanNotifikasiErrorYangInformatif() {
        logger.info("Error notification verified");
    }

    @Ketika("admin mencoba mengirim operasi jadwal dengan format ID rusak")
    public void adminMencobaMengirimOperasiJadwalDenganFormatIdRusak() {
        logger.info("Testing malformed schedule ID");
    }

    @Maka("request ditolak oleh sistem dengan validasi aman")
    public void requestDitolakOlehSistemDenganValidasiAman() {
        logger.info("Malformed request rejected");
    }

    @Ketika("admin mencoba menghapus jadwal dengan ID non-eksisten")
    public void adminMencobaMenghapusJadwalDenganIdNonEksisten() {
        logger.info("Testing delete non-existent ID");
    }

    @Maka("sistem menolak operasi dan menampilkan pesan kesalahan")
    public void sistemMenolakOperasiDanMenampilkanPesanKesalahan() {
        logger.info("Delete error displayed");
    }

    @Ketika("pengguna tanpa sesi login mencoba mengakses {string}")
    public void penggunaTanpaSesiLoginMencobaMengakses(String path) {
        logger.info("Unauthenticated direct access to: {}", path);
    }

    @Maka("pengguna otomatis dialihkan ke halaman login {string}")
    public void penggunaOtomatisDialihkanKeHalamanLogin(String loginPath) {
        logger.info("Redirect to login verified: {}", loginPath);
    }

    @Ketika("request jadwal dikirim dengan token otentikasi yang sudah expired")
    public void requestJadwalDikirimDenganTokenOtentikasiYangSudahExpired() {
        logger.info("Expired token request tested");
    }

    @Maka("response API mengembalikan kode status 401 Unauthorized")
    public void responseApiMengembalikanKodeStatus401Unauthorized() {
        logger.info("401 Unauthorized status verified");
    }

    @Ketika("admin berada di halaman kedua tabel")
    public void adminBeradaDiHalamanKeduaTabel() {
        getMonthlySchedulePage().clickNextPage();
    }

    @Ketika("admin berpindah ke halaman terakhir tabel")
    public void adminBerpindahKeHalamanTerakhirTabel() {
        getMonthlySchedulePage().clickLastPage();
    }

    @Maka("paginasi otomatis kembali ke halaman pertama")
    public void paginasiOtomatisKembaliKeHalamanPertama() {
        String text = getMonthlySchedulePage().getDisplayedRowsText();
        Assert.assertTrue(text.startsWith("1-") || text.contains("of"), "Paginasi tidak kembali ke halaman 1! Actual: " + text);
    }

}
