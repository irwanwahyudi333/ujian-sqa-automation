# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Cuti (Leave Management) — Hasil Allure Report

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Fitur Pengelolaan, Permohonan, & Validasi Cuti (*Leave Management: Master Unit Leave, Mobile Request, Laporan Cuti, Sisa Cuti, Leader Approval, & Client Upliner*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 40 Skenario Unik (34 Skenario UI Selenium WebDriver & 6 Skenario API REST Assured)  
**Hasil:** 36 Passed (90.0%), 4 Failed (10.0% — Defects Detected Murni Selenium & REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (RBAC Approval Bypass, Missing Form Controls, Modal Dismissal Flaw & Missing Toast Feedback)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada seluruh siklus hidup tata kelola Cuti (*Leave Management*) dengan strategi pengujian hemat (*Lean Testing Strategy - Skema 1*), pengujian telah memvalidasi seluruh alur: mulai dari formulir permohonan mobile karyawan (`/apps/absent/leave-client`), pengaturan master aturan kuota cuti (`/management/unit-leave`), persetujuan regu oleh Team Leader (`/leader/cuti/*`), validasi berjenjang oleh Client Upliner V2 & V3 (`/client/cuti/*`), audit pemantauan monitoring presensi (`/monitoring/cuti`), hingga konsolidasi laporan cuti dan kalkulasi sisa saldo cuti tahunan di level Administrator HR (`/laporan/cuti` dan `/laporan/sisa-cuti`).

Dari **40 skenario pengujian unik** yang terekam secara komprehensif pada **Allure Report**:
1. **Pengujian Antarmuka UI Selenium WebDriver (34 Skenario): 31 Passed, 3 Failed (91.2% Lulus)**  
   Sebanyak 31 skenario antarmuka pada pelaporan admin HR, portal verifikasi Client Upliner, pemantauan auditor monitoring, antarmuka mobile karyawan, dan manajemen aturan cuti berjalan stabil dan memenuhi kriteria penerimaan pengguna: layout tabel, filter rentang tanggal presisi, penolakan input tanggal terbalik (*Start Date > End Date*), pencarian nama karyawan dan NIK, tombol reset, dialog filter multi-kriteria, popup detail permohonan, kontrol paginasi baris per halaman, hingga ekspor laporan ke Excel (`.xlsx`).
   - **3 Cacat Antarmuka & Logika Bisnis Ditemukan:**
     - *DEFECT-CUTI-001:* Pada halaman Laporan Cuti Administrator (`/laporan/cuti`), modal dialog aksi *"Ubah atasan user yang request"* kehilangan elemen form dropdown pilihan atasan baru (hanya menampilkan teks statis `-`), sehingga tombol submit *"Ubah Data"* tidak dapat memproses pengalihan wewenang approval cuti.
     - *DEFECT-CUTI-002:* Pada halaman Master Unit Leave (`/management/unit-leave`), aksi penambahan aturan cuti baru melalui modal form tidak memicu notifikasi toast umpan balik visual (*silent completion / missing toast notification*).
     - *DEFECT-CUTI-003:* Pada halaman Master Unit Leave (`/management/unit-leave`), tombol "Batal" pada modal konfirmasi hapus aturan cuti gagal melepaskan kontainer dialog (*lingering modal overlay*), sehingga modal tetap terdeteksi aktif pada DOM.
2. **Pengujian Backend API REST Assured (6 Skenario): 5 Passed, 1 Failed (83.3% Lulus)**  
   Endpoint API master aturan cuti (`/api/unit-leave`) dan endpoint persetujuan cuti oleh Team Leader (`/api/leader/leave/1/approval`) memenuhi spesifikasi pengambilan data berotentikasi (`HTTP 200`), pembuatan aturan baru (`HTTP 201`), validasi batas kuota cuti bernilai negatif (`HTTP 400`), penolakan request tanpa token otorisasi (`HTTP 401`), dan persetujuan pengajuan cuti tim oleh Leader.
   - **1 Cacat Keamanan & Kontrak Otorisasi Kritis Ditemukan (*SEC-CUTI-001*):** Endpoint mutasi persetujuan cuti (`PATCH /api/leader/leave/1/approval`) rentan terhadap celah *Broken Object Level Authorization* / RBAC bypass (OWASP API5). Server mengembalikan kode `HTTP 200 OK` dan meloloskan approval saat ditembak oleh token staf biasa tanpa wewenang Team Leader, alih-alih menolak dengan `HTTP 403 Forbidden`.

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-CUTI-001** | `PATCH /api/leader/leave/1/approval` | Broken Object Level Authorization (OWASP API5) / RBAC Bypass | 🔴 **Critical** | Server meloloskan approval cuti dan merespons `HTTP 200 OK` saat diakses staf non-leader tanpa wewenang | Server memvalidasi peran pengguna dan menolak mutasi approval dari non-leader dengan `HTTP 403 Forbidden` |

---

### Matriks Temuan Cacat Antarmuka UI (Selenium WebDriver)

| ID Defect | Modul / Halaman | Komponen Bermasalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-CUTI-001** | UI `/laporan/cuti` | Modal Dialog Ubah Atasan (*Missing Form Controls*) | 🔴 **Major** | Modal "Ubah atasan user yang request" tidak memuat dropdown seleksi atasan baru (hanya teks statis `-`) | Modal menyediakan dropdown data atasan aktif dan tombol submit dapat memperbarui data |
| **DEFECT-CUTI-002** | UI `/management/unit-leave` | Notifikasi Toast Feedback (*Missing UI Feedback*) | 🟡 **Medium** | Sistem tidak memunculkan notifikasi toast setelah pengguna menyimpan data aturan cuti baru | Sistem memunculkan toast notifikasi sukses (misal: "Aturan cuti berhasil ditambahkan") |
| **DEFECT-CUTI-003** | UI `/management/unit-leave` | Modal Konfirmasi Hapus (*Modal Dismissal Flaw*) | 🟡 **Medium** | Modal konfirmasi hapus tetap terbuka di DOM setelah tombol "Batal" ditekan | Modal konfirmasi hapus tertutup tuntas (`display: none` / unmounted) saat dibatalkan |

---

### Matriks Evaluasi Hasil Uji Per Sub-Modul (Allure Report)

| No | Modul / Halaman Pengujian | Layer Uji | Total Skenario | Passed | Failed | Status Kelulusan |
|:---:|---|:---:|:---:|:---:|:---:|:---:|
| 1 | API Master Unit Leave & Otorisasi ([`unit_leave_api.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/unit_leave/unit_leave_api.feature)) | API | 5 | 4 | 1 | 🟡 **80.0%** (1 Defect SEC-CUTI-001) |
| 2 | Leader API Approval Cuti ([`LeaderApiTest.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/leader/LeaderApiTest.feature)) | API | 1 | 1 | 0 | 🟢 **100% Lulus** |
| 3 | Pengajuan Cuti Mobile ([`absent_leave_lean.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/apps_absent/absent_leave_lean.feature)) | UI | 2 | 2 | 0 | 🟢 **100% Lulus** |
| 4 | Master Aturan Cuti / Unit Leave ([`unit_leave_lean.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/unit_leave/unit_leave_lean.feature)) | UI | 5 | 3 | 2 | 🟡 **60.0%** (2 Defects DEFECT-CUTI-002/003) |
| 5 | Laporan Cuti Admin HR ([`05_laporan_cuti.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/laporan/05_laporan_cuti.feature)) | UI | 10 | 9 | 1 | 🟡 **90.0%** (1 Defect DEFECT-CUTI-001) |
| 6 | Laporan Sisa Cuti Admin HR ([`16_laporan_sisa_cuti.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/laporan/16_laporan_sisa_cuti.feature)) | UI | 9 | 9 | 0 | 🟢 **100% Lulus** |
| 7 | Portal Leader Approval Cuti ([`02_LeaderCuti.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/leader/02_LeaderCuti.feature)) | UI | 3 | 3 | 0 | 🟢 **100% Lulus** |
| 8 | Portal Client Validasi Cuti ([`ClientCuti.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/client/ClientCuti.feature)) | UI | 4 | 4 | 0 | 🟢 **100% Lulus** |
| 9 | Auditor Monitoring Cuti ([`MonitoringCutiLembur.feature`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/monitoring/MonitoringCutiLembur.feature)) | UI | 1 | 1 | 0 | 🟢 **100% Lulus** |
| **TOTAL** | **Seluruh Fitur Modul Cuti (Leave Management)** | **Hybrid** | **40** | **36** | **4** | 🟢 **90.0% Lulus** |

---

## 3. Rincian Temuan Cacat & Rekomendasi Perbaikan

### 1. DEFECT-CUTI-001: Modal Dialog Ubah Atasan Cuti Kehilangan Kontrol Dropdown Pilihan
- **Kode Skenario:** `@BUG-CUTI-001 @DEFECT-CUTI-001 @live` pada [05_laporan_cuti.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/laporan/05_laporan_cuti.feature#L71-L78)
- **Lokasi Halaman:** Antarmuka Laporan Cuti Administrator (`/laporan/cuti`)
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**
  Pada tabel laporan cuti admin, ketika admin membuka menu aksi baris pertama dan memilih aksi *"Edit"* (*Ubah Data*), sistem menampilkan kontainer dialog dengan judul *"Ubah atasan user yang request"*. Namun, di dalam modal dialog tersebut tidak ditemukan elemen input form, autocomplete, maupun dropdown seleksi atasan baru (hanya menampilkan teks statis `-`). Tombol submit *"Ubah Data"* tidak dapat memproses pembaruan atasan pengganti.
- **Dampak Bisnis & Operasional:**
  Admin HR tidak dapat mengalihkan wewenang persetujuan cuti jika atasan langsung karyawan sedang mengambil cuti panjang atau berhalangan hadir. Akibatnya, permohonan hak cuti tahunan karyawan tertahan tanpa ada pejabat berwenang yang dapat memprosesnya.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: [DEFECT UI LIVE] Modal 'Ubah atasan user yang request' pada Laporan Cuti TIDAK menyediakan kontrol dropdown/select untuk memilih Atasan 2 atau Atasan 3! 
  Nilai hanya teks statis '-' sehingga tombol 'Ubah Data' tidak dapat digunakan untuk memilih atau mengubah data atasan cuti baru. 
  expected [true] but found [false]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertTrue(Assert.java:57)
  	at com.company.sqa.ui.steps.LaporanCutiSteps.modalCutiMemuatKontrolDropdownPilihanAtasanYangDapatDiubahOlehAdmin(LaporanCutiSteps.java:182)
  	at ✽.modal cuti memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/laporan/05_laporan_cuti.feature:75)
  ```
- **Rekomendasi Perbaikan (Frontend React / Next.js):**
  Lengkapi modal dialog ubah atasan cuti dengan kontrol dropdown atasan aktif:
  ```jsx
  // Rekomendasi Komponen Dialog Ubah Atasan Cuti (MUI / React):
  <Dialog open={isModalOpen} onClose={handleCloseModal} maxWidth="sm" fullWidth>
    <DialogTitle>Ubah atasan user yang request</DialogTitle>
    <DialogContent dividers>
      <Typography variant="body2" color="textSecondary" gutterBottom>
        Pilih atasan baru untuk memproses persetujuan cuti karyawan: <b>{employeeName}</b>
      </Typography>
      <FormControl fullWidth margin="normal">
        <InputLabel id="select-supervisor-label">Atasan Pengganti</InputLabel>
        <Select
          labelId="select-supervisor-label"
          id="select-supervisor"
          value={newSupervisorId}
          onChange={(e) => setNewSupervisorId(e.target.value)}
          required
        >
          {activeSupervisors.map((spv) => (
            <MenuItem key={spv.id} value={spv.id}>
              {spv.fullname} — {spv.department} ({spv.nip})
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </DialogContent>
    <DialogActions>
      <Button onClick={handleCloseModal} color="inherit">Batal</Button>
      <Button 
        variant="contained" 
        color="primary" 
        onClick={handleSaveSupervisorChange}
        disabled={!newSupervisorId || isSubmitting}
      >
        {isSubmitting ? "Menyimpan..." : "Ubah Data"}
      </Button>
    </DialogActions>
  </Dialog>
  ```

---

### 2. SEC-CUTI-001: Bypass Otorisasi Persetujuan Cuti oleh Non-Leader (Broken Access Control)
- **Kode Skenario:** `@SEC-CUTI-001 @security @rbac @cuti @lean` pada [unit_leave_api.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/unit_leave/unit_leave_api.feature#L43-L46)
- **Modul / Endpoint:** `PATCH /api/leader/leave/1/approval`
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**
  Endpoint mutasi persetujuan cuti tim dapat diakses secara langsung oleh token pengguna biasa (staf/karyawan non-leader). Server backend memproses mutasi status menjadi `APPROVED` dan merespons `HTTP 200 OK` tanpa memverifikasi apakah akun pemohon memiliki flag `isLeader: true` atau terdaftar sebagai atasan langsung dari karyawan yang bersangkutan.
- **Dampak Keamanan:**
  Karyawan nakal dapat menyetujui permohonan cutinya sendiri atau permohonan cuti rekan kerjanya menggunakan skrip/cURL tanpa perlu persetujuan dari atasan berwenang (pelanggaran prinsip integritas persetujuan berjenjang).
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: [SEC-CUTI-001] Endpoint mutasi persetujuan cuti rentan terhadap Broken Access Control (OWASP API5)! 
  Server mengembalikan HTTP 200 OK dan meloloskan persetujuan tanpa memvalidasi hierarki peran Team Leader alih-alih menolak dengan HTTP 403 Forbidden. 
  expected [403] but found [200]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertEqualsImpl(Assert.java:150)
  	at org.testng.Assert.assertEquals(Assert.java:132)
  	at org.testng.Assert.assertEquals(Assert.java:1419)
  	at org.testng.Assert.assertEquals(Assert.java:1383)
  	at com.company.sqa.api.steps.UnitLeaveApiSteps.sistemBackendHarusMenolakPermohonanPersetujuanDenganStatus403Forbidden(UnitLeaveApiSteps.java:74)
  	at ✽.sistem backend harus menolak permohonan persetujuan dengan status 403 Forbidden(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/unit_leave/unit_leave_api.feature:46)
  ```
- **Rekomendasi Perbaikan (Backend RBAC Guard):**
  Pasang guard otorisasi berbasis peran dan verifikasi relasi atasan pada controller approval:
  ```typescript
  // Middleware Verifikasi Otoritas Leader:
  export async function verifyLeaveApprovalAuthority(req: Request, res: Response, next: NextFunction) {
    const leaveId = req.params.id;
    const currentUser = req.user;

    // 1. Validasi Peran Minimal Leader / HR Admin
    if (!currentUser.isLeader && currentUser.role !== 'ADMIN') {
      return res.status(403).json({
        status: false,
        code: "UNAUTHORIZED_LEADER_ROLE",
        message: "Akses ditolak: Anda tidak memiliki wewenang Team Leader untuk menyetujui permohonan cuti."
      });
    }

    // 2. Validasi Relasi Kepemimpinan (Hanya atasan langsung yang dapat menyetujui)
    const leaveRequest = await LeaveModel.findById(leaveId);
    if (!leaveRequest || leaveRequest.supervisor_id !== currentUser.id) {
      return res.status(403).json({
        status: false,
        code: "SUPERVISOR_MISMATCH",
        message: "Akses ditolak: Karyawan pemohon bukan merupakan bawahan langsung dalam regu Anda."
      });
    }

    next();
  }
  ```

---

### 3. DEFECT-CUTI-002: Ketiadaan Umpan Balik Visual Toast Saat Tambah Aturan Cuti
- **Kode Skenario:** `unit_leave_lean.feature:28` pada [unit_leave_lean.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/unit_leave/unit_leave_lean.feature#L28-L33)
- **Lokasi Halaman:** Master Unit Leave (`/management/unit-leave`)
- **Severity:** 🟡 **Medium**
- **Deskripsi Masalah:**
  Saat administrator menyimpan aturan cuti baru via modal formulir, sistem tidak menampilkan notifikasi toast (*toast feedback*) atau snackbar yang mengonfirmasi keberhasilan penambahan data. Halaman hanya menutup modal tanpa memberikan indikasi status operasi.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: Toast unit leave tidak muncul! expected [true] but found [false]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertTrue(Assert.java:57)
  	at com.company.sqa.ui.steps.UnitLeaveSteps.notifikasiToastUnitLeaveHarusDitampilkan(UnitLeaveSteps.java:87)
  	at ✽.Notifikasi toast unit leave harus ditampilkan(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/unit_leave/unit_leave_lean.feature:32)
  ```
- **Rekomendasi Perbaikan:**
  Tambahkan pemanggilan toast notification library (seperti React-Toastify atau MUI Snackbar) pada callback sukses penyimpanan data form unit leave:
  ```javascript
  const handleSaveUnitLeave = async (data) => {
    try {
      await api.post('/api/unit-leave', data);
      toast.success("Aturan cuti berhasil ditambahkan!");
      closeModal();
      refetchData();
    } catch (err) {
      toast.error(err.response?.data?.message || "Gagal menyimpan aturan cuti");
    }
  };
  ```

---

### 4. DEFECT-CUTI-003: Modal Konfirmasi Hapus Gagal Tertutup Saat Dibatalkan
- **Kode Skenario:** `unit_leave_lean.feature:34` pada [unit_leave_lean.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/unit_leave/unit_leave_lean.feature#L34-L39)
- **Lokasi Halaman:** Master Unit Leave (`/management/unit-leave`)
- **Severity:** 🟡 **Medium**
- **Deskripsi Masalah:**
  Saat administrator menekan tombol "Batal" pada modal konfirmasi hapus aturan cuti, kontainer dialog `#modal-delete` tidak menutup secara tuntas. Elemen dialog tetap berstatus terlihat pada DOM (*lingering overlay*), menghalangi interaksi pengguna pada tabel data.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: Modal hapus unit leave masih terbuka! expected [false] but found [true]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertFalse(Assert.java:79)
  	at com.company.sqa.ui.steps.UnitLeaveSteps.modalKonfirmasiHapusUnitLeaveHarusTertutup(UnitLeaveSteps.java:107)
  	at ✽.Modal konfirmasi hapus unit leave harus tertutup(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/unit_leave/unit_leave_lean.feature:38)
  ```
- **Rekomendasi Perbaikan:**
  Pastikan event handler tombol batal melakukan reset state modal secara menyeluruh:
  ```javascript
  document.getElementById("btn-cancel-delete")?.addEventListener("click", () => {
    const modalDelete = document.getElementById("modal-delete");
    if (modalDelete) {
      modalDelete.classList.remove("active");
      modalDelete.style.display = "none";
    }
  });
  ```

---

## 4. Panduan Eksekusi Uji Regresi Modul Cuti

Untuk mereproduksi dan memverifikasi perbaikan cacat pada modul Cuti:

```bash
# 1. Jalankan Seluruh Skenario Lean Modul Cuti (UI + API):
mvn clean test -Dcucumber.filter.tags="@cuti and @lean"

# 2. Jalankan Skenario Fungsional Bersih (Tanpa Cacat Yang Diketahui):
mvn test -Dcucumber.filter.tags="@cuti and @lean and not @DEFECT-CUTI-001 and not @SEC-CUTI-001"

# 3. Jalankan Pengujian Khusus Validasi Cacat Modal Ubah Atasan (DEFECT-CUTI-001):
mvn test -Dcucumber.filter.tags="@DEFECT-CUTI-001"

# 4. Jalankan Pengujian Khusus Validasi Keamanan Otorisasi Approval Cuti (SEC-CUTI-001):
mvn test -Dcucumber.filter.tags="@SEC-CUTI-001"

# 5. Generate dan Buka Allure Report:
.allure/allure-2.29.0/bin/allure generate target/allure-results -o target/allure-report --clean
.allure/allure-2.29.0/bin/allure open target/allure-report
```

---

## 5. Kesimpulan & Rencana Tindak Lanjut

1. **Prioritas Keamanan #1 (SEC-CUTI-001):** Tim backend wajib memperketat endpoint `PATCH /api/leader/leave/1/approval` dengan middleware otorisasi berbasis peran agar staf biasa tidak dapat memanipulasi persetujuan cuti secara sepihak.
2. **Prioritas Antarmuka #2 (DEFECT-CUTI-001):** Tim frontend wajib mengimplementasikan dropdown seleksi atasan baru pada modal *"Ubah atasan user yang request"* di `/laporan/cuti` agar proses delegasi wewenang approval dapat berfungsi normal.
3. **Prioritas UX #3 (DEFECT-CUTI-002 & DEFECT-CUTI-003):** Perbaiki state management modal dan pasang toast feedback pada modul Master Unit Leave untuk menjamin transparansi status penyimpanan dan kelancaran navigasi dialog.
