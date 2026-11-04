# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Izin (Permit Management) — Hasil Allure Report

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Fitur Pengelolaan, Pelaporan & Validasi Perizinan Karyawan (*Permit Management: Izin Reguler, Izin Terlambat, Izin Pulang Cepat, & Izin Off*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 49 Skenario Unik (37 Skenario UI Selenium WebDriver & 12 Skenario API REST Assured)  
**Hasil:** 46 Passed (93.9%), 3 Failed (6.1% - Defects Detected Murni Selenium & REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (RBAC Multi-Level Approval Bypass, UI Contextual Label Defect & Form Date Boundary Logic)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada seluruh keluarga fitur Perizinan (*Permit Management*) dengan strategi pengujian hemat (*Lean Testing Strategy - Skema 1*), pengujian telah memvalidasi seluruh siklus hidup perizinan: mulai dari permohonan izin reguler, penyesuaian jadwal libur (*day-off swap / izin off*), pemantauan keterlambatan masuk shift, hingga izin pulang mendahului jam operasional.

Dari **49 skenario pengujian unik** yang terekam secara komprehensif pada **Allure Report**:
1. **Pengujian Antarmuka UI Selenium WebDriver (37 Skenario): 35 Passed, 2 Failed (94.6% Lulus)**  
   Sebanyak 35 skenario antarmuka pada 4 sub-modul laporan admin (`/laporan/izin`, `/laporan/izin-terlambat`, `/laporan/izin-pulang-cepat`, dan `/laporan/izin-off`) berjalan stabil dan memenuhi kriteria penerimaan pengguna: layout 11 kolom tabel, filter rentang tanggal presisi, pencarian nama dan NIK, tombol reset, dialog filter multi-kriteria, popup detail permohonan, kontrol paginasi baris, hingga ekspor berkas Excel (`.xlsx`).
   - **2 Cacat Antarmuka & Validasi Ditemukan:**
     - *DEFECT-OFF-001:* Pada halaman Laporan Izin Off admin (`/laporan/izin-off`), menu popover aksi baris pertama memuat label yang salah konteks (*copy-paste* dari modul Koreksi Absensi), yaitu **"Approval Koreksi"** dan **"Reject Koreksi"** alih-alih opsi izin off.
     - *DEFECT-OFF-002 (DTR-OFF-002):* Pada halaman Laporan Izin Off admin (`/laporan/izin-off`), kueri pencarian dengan tanggal terbalik (*Start Date > End Date*) tidak memunculkan notifikasi snackbar galat (*silent failure*).
2. **Pengujian Backend API REST Assured (12 Skenario): 11 Passed, 1 Failed (91.7% Lulus)**  
   Endpoint API rekapitulasi izin (`/api/izin`) dan endpoint manajemen izin off oleh supervisor (`/api/off-permit/`) memenuhi spesifikasi kueri GET, filter status PENDING, mutasi status PATCH persetujuan/penolakan, pembuatan POST data valid, pembatalan DELETE, serta paginasi data.
   - **1 Cacat Kontrak Keamanan Otorisasi Ditemukan (*SEC-IZN-001*):** Endpoint persetujuan izin off (`PATCH /api/off-permit/101`) ketika diuji dengan token staf non-otoritas tidak menolak secara tepat dengan kode status standar `HTTP 403 Forbidden`, melainkan mengembalikan `HTTP 401 Unauthorized` (inkonsistensi kontrak keamanan API RBAC).

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-IZN-001** | `PATCH /api/off-permit/101` | Role-Based Access Control (RBAC) & Multi-Level Workflow Guard | 🔴 **Major** | Endpoint mutasi persetujuan merespons `HTTP 401 Unauthorized` saat diakses staf non-leader | Server memvalidasi hak akses peran dan menolak mutasi approval oleh non-leader dengan `HTTP 403 Forbidden` |

---

### Matriks Temuan Cacat Antarmuka UI (Selenium WebDriver)

| ID Defect | Modul / Halaman | Komponen Bermasalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-OFF-001** | UI `/laporan/izin-off` | Menu Popover Aksi Baris Tabel (*Contextual Action Menu*) | 🔴 **Major** | Menu popover memuat label *copy-paste* dari modul Koreksi: `"Approval Koreksi"` dan `"Reject Koreksi"` | Menu popover menampilkan label aksi yang sesuai: `"Approval Izin Off"` dan `"Reject Izin Off"` |
| **DEFECT-OFF-002** | UI `/laporan/izin-off` | Bilah Filter Tanggal (*Date Inversion Validation*) | 🟡 **Medium** | Kueri pencarian dengan tanggal terbalik (*Start > End*) tidak memunculkan notifikasi snackbar error (*silent failure*) | Muncul notifikasi snackbar error `"Start date tidak boleh melebihi end date"` secara konsisten |

---

### Matriks Evaluasi Hasil Uji Per Sub-Modul (Allure Report)

| No | Modul / Halaman Pengujian | Layer Uji | Total Skenario | Passed | Failed | Status Kelulusan |
|:---:|---|:---:|:---:|:---:|:---:|:---:|
| 1 | API Rekapitulasi & Permohonan Izin (`izin_api.feature`) | API | 4 | 4 | 0 | 🟢 **100% Lulus** |
| 2 | Leader Izin Off API (`01_leader_izin_off_api.feature`) | API | 8 | 7 | 1 | 🟡 **87.5%** (1 Defect SEC-IZN-001) |
| 3 | Laporan Izin Karyawan Reguler (`02_laporan_izin.feature`) | UI | 9 | 9 | 0 | 🟢 **100% Lulus** |
| 4 | Laporan Izin Terlambat (`03_laporan_izin_terlambat.feature`) | UI | 9 | 9 | 0 | 🟢 **100% Lulus** |
| 5 | Laporan Izin Pulang Cepat (`04_laporan_izin_pulang_cepat.feature`) | UI | 9 | 9 | 0 | 🟢 **100% Lulus** |
| 6 | Laporan Izin Off Admin (`13_laporan_izin_off.feature`) | UI | 10 | 8 | 2 | 🟡 **80.0%** (2 Defects DEFECT-OFF-001/002) |
| **TOTAL** | **Seluruh Fitur Izin (Permit Management)** | **Hybrid** | **49** | **46** | **3** | 🟢 **93.9% Lulus** |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. SEC-IZN-001: Inkonsistensi Otorisasi RBAC pada Endpoint Persetujuan Izin Off
- **Kode Skenario / Tag Allure:** `@SEC-IZN-001 @security @rbac-bypass @izin @lean` pada `01_leader_izin_off_api.feature`
- **Lokasi Endpoint:** API Persetujuan Izin Off (`PATCH /api/off-permit/101` / `/hadir-api-magang/off-permit/approval`)
- **Kategori Masalah:** CWE-285: Improper Authorization & Inconsistent REST API Status Code
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Permohonan izin off (*day-off swap*) memerlukan persetujuan berjenjang oleh Team Leader atau Supervisor Divisi:
  $$\text{Pengajuan Staf} \longrightarrow \text{Verifikasi Jadwal Shift} \longrightarrow \text{Persetujuan Leader/HR}$$
  Ketika pengguna terautentikasi dengan token staf biasa (tanpa hak wewenang leader) mengirimkan request `PATCH` untuk menyetujui izin off langsung ke status `APPROVED`, backend tidak mengembalikan kode status otorisasi peran yang semestinya (`HTTP 403 Forbidden`), melainkan mengembalikan `HTTP 401 Unauthorized`.
- **Dampak Keamanan & Tata Kelola API:**  
  Pelanggaran standar RFC 7235 dan OWASP API Security. Ketiadaan pembedaan yang jelas antara kegagalan otentikasi (401 - *Who are you?*) dan kegagalan otorisasi hak akses (403 - *You don't have permission to do this!*). Hal ini menyulitkan integrasi aplikasi client mobile dan web frontend dalam menangani alur *Access Denied* pengguna.
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [SEC-IZN-001] Endpoint persetujuan izin off tidak menerapkan Role-Based Access Control (RBAC) bertingkat secara tepat! Server mengembalikan status HTTP 401 Unauthorized alih-alih HTTP 403 Forbidden. expected [403] but found [401]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertEqualsImpl(Assert.java:150)
  at org.testng.Assert.assertEquals(Assert.java:132)
  at com.company.sqa.api.steps.LeaderIzinOffApiSteps.sistemBackendHarusMenolakPerubahanStatusIzinOffDenganStatus403Forbidden(LeaderIzinOffApiSteps.java:88)
  at ✽.sistem backend harus menolak perubahan status izin off dengan status 403 Forbidden(file:///.../01_leader_izin_off_api.feature:47)
  ```
- **Akar Masalah (*Root Cause*):**  
  Middleware otentikasi dan otorisasi tidak dipisahkan. Saat pemeriksaan peran (*role check*) gagal, handler controller secara keliru melempar `UnauthorizedException` alih-alih `ForbiddenException`.
- **Rekomendasi Perbaikan Teknis (Backend NestJS / Express RBAC Guard):**  
  Terapkan guard otorisasi berbasis peran secara tegas:
  ```typescript
  // Guard Otorisasi Leader / Supervisor (NestJS):
  @UseGuards(JwtAuthGuard, RolesGuard)
  @Roles('TEAM_LEADER', 'SUPERVISOR', 'HR_ADMIN')
  @Patch('/api/off-permit/:id/approval')
  async approveOffPermit(
    @Param('id') id: string,
    @Body() approvalDto: OffPermitApprovalDto,
    @CurrentUser() user: UserEntity
  ) {
    // Verifikasi apakah user berwenang atas tim karyawan yang bersangkutan
    const permit = await this.offPermitService.findById(id);
    if (!permit) throw new NotFoundException('Data izin off tidak ditemukan.');

    if (user.role !== 'HR_ADMIN' && permit.leaderId !== user.id) {
      throw new ForbiddenException('Akses ditolak: Anda tidak memiliki wewenang menyetujui izin off anggota tim lain.');
    }

    return await this.offPermitService.updateApprovalStatus(id, approvalDto.status, user.id);
  }
  ```

---

### 2. DEFECT-OFF-001: Label Salah Konteks pada Menu Aksi Baris Tabel Laporan Izin Off
- **Kode Skenario / Tag Allure:** `@BUG-OFF-001 @DEFECT-OFF-001 @live @izin @lean` pada `13_laporan_izin_off.feature`
- **Lokasi Halaman:** Antarmuka Laporan Izin Off HR Admin (`/laporan/izin-off`)
- **Kategori Masalah:** UI Component Defect & Broken Contextual Copy-Paste
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Pada baris data tabel Laporan Izin Off, ketika admin mengklik tombol aksi (*action icon button*), sistem membuka popover menu vertikal. Namun, item menu aksi tersebut menampilkan label:
  - `"Approval Koreksi"`
  - `"Reject Koreksi"`
  Label ini merupakan teks hasil *copy-paste* dari modul Koreksi Absensi (`/laporan/koreksi`) yang tidak disesuaikan dengan konteks fitur Izin Off.
- **Dampak Pengguna & Operasional:**  
  Membingungkan tim HR dan administrator sistem. Administrator ragu apakah tindakan tersebut akan memproses jadwal izin off atau justru mengubah rekaman koreksi absensi jam kerja karyawan.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [DEFECT-OFF-001] Menu aksi pada Laporan Izin Off salah label (copy-paste dari modul Koreksi)! Ditemukan menu: [Approval Koreksi, Reject Koreksi] expected [false] but found [true]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertFalse(Assert.java:79)
  at com.company.sqa.ui.steps.LaporanIzinOffSteps.menuPopoverAksiTidakMemuatLabelCopyPaste(LaporanIzinOffSteps.java:159)
  at ✽.menu popover aksi tidak memuat label copy-paste "Approval Koreksi" atau "Reject Koreksi"(file:///.../13_laporan_izin_off.feature:71)
  ```
- **Akar Masalah (*Root Cause*):**  
  Komponen React popover menu pada halaman `/laporan/izin-off` menggunakan kembali (*reused*) sub-komponen `<CorrectionActionMenu />` tanpa parameter kustomisasi label aksi izin off.
- **Rekomendasi Perbaikan Teknis (Frontend React / Material-UI):**  
  Sesuaikan label menu item pada komponen popover:
  ```jsx
  // Rekomendasi Komponen Action Menu Izin Off (React):
  <Menu
    id="off-permit-action-menu"
    anchorEl={anchorEl}
    open={Boolean(anchorEl)}
    onClose={handleClose}
  >
    <MenuItem onClick={() => handleApproveOffPermit(selectedRow.id)}>
      <ListItemIcon><CheckIcon fontSize="small" color="success" /></ListItemIcon>
      <ListItemText primary="Approval Izin Off" />
    </MenuItem>
    <MenuItem onClick={() => handleRejectOffPermit(selectedRow.id)}>
      <ListItemIcon><CloseIcon fontSize="small" color="error" /></ListItemIcon>
      <ListItemText primary="Reject Izin Off" />
    </MenuItem>
  </Menu>
  ```

---

### 3. DEFECT-OFF-002: Ketiadaan Notifikasi Snackbar Error untuk Input Logika Tanggal Terbalik
- **Kode Skenario / Tag Allure:** `@DTR-OFF-002 @negative @ui @laporan @izin-off @izin @lean` pada `13_laporan_izin_off.feature`
- **Lokasi Halaman:** Antarmuka Laporan Izin Off HR Admin (`/laporan/izin-off`)
- **Kategori Masalah:** Client-Side Form Validation & Silent Query Execution
- **Severity:** 🟡 **Medium**
- **Deskripsi Masalah:**  
  Pada bilah filter halaman Laporan Izin Off, ketika admin memasukkan tanggal mulai yang lebih besar daripada tanggal akhir (`Start Date: 2026-10-26` dan `End Date: 2026-10-20`) kemudian menekan tombol *Search*, sistem tidak menampilkan notifikasi snackbar error `"Start date tidak boleh melebihi end date"`.
- **Dampak Operasional:**  
  Terjadi inkonsistensi perilaku antarmuka. Pada modul Laporan Izin Reguler (`/laporan/izin`), Laporan Izin Terlambat (`/laporan/izin-terlambat`), dan Laporan Izin Pulang Cepat (`/laporan/izin-pulang-cepat`), kesalahan input tanggal terbalik memunculkan snackbar error merah secara presisi. Namun pada modul Izin Off, sistem melakukan *silent failure* sehingga pengguna tidak mendapatkan umpan balik atas kesalahan inputnya.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: Snackbar error izin off tidak tampil! expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.LaporanIzinOffSteps.sistemMenampilkanNotifikasiSnackbarErrorIzinOff(LaporanIzinOffSteps.java:70)
  at ✽.sistem menampilkan notifikasi snackbar error izin off "Start date tidak boleh melebihi end date"(file:///.../13_laporan_izin_off.feature:27)
  ```
- **Rekomendasi Perbaikan Teknis (Frontend JavaScript / React Handler):**  
  Tambahkan validasi tanggal sebelum memicu pengambilan data tabel:
  ```javascript
  const handleSearchOffPermit = () => {
    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
      showSnackbar("Start date tidak boleh melebihi end date", "error");
      return; // Cegah kueri data ke server
    }
    fetchIzinOffData({ startDate, endDate, searchKeyword });
  };
  ```

---

## 4. Evaluasi Area Fitur yang Berfungsi Sempurna (Passed Areas)

Suite pengujian mengonfirmasi bahwa **46 skenario** pada fitur izin telah memenuhi standar mutu fungsional:

1. **Integritas REST API Permohonan & Rekapitulasi Izin (`izin_api.feature` & `01_leader_izin_off_api.feature`):**
   - Kueri data riwayat izin (`GET /api/izin`) berhasil mengembalikan status `HTTP 200 OK` dengan skema array JSON yang valid.
   - Pembuatan pengajuan izin baru (`POST /api/izin`) berhasil dengan status `HTTP 201 Created`.
   - Validasi logika tanggal terbalik pada payload API izin ditolak dengan status `HTTP 400 Bad Request`.
   - Permintaan tanpa token otorisasi ditolak secara aman dengan status `HTTP 401 Unauthorized`.
   - Kueri daftar izin off tim leader (`GET /api/off-permit/`) berhasil memuat data dengan status `200 OK`.
   - Filter query status pending (`GET /api/off-permit/?status=PENDING`) dan paginasi data (`?page=1&limit=5`) berfungsi dengan benar.
   - Aksi mutasi persetujuan (`PATCH`), penolakan (`PATCH`), penambahan (`POST`), dan pembatalan (`DELETE`) izin off oleh pengguna berwenang berhasil mengembalikan status sukses `200/204/201`.
2. **Laporan Izin Karyawan Reguler (`02_laporan_izin.feature` — 100% Lulus):**
   - Halaman `/laporan/izin` merender 11 kolom tabel dan bilah kontrol filter lengkap.
   - Filter tanggal presisi satu hari kerja (`2026-10-26`) menampilkan data izin "Irwan Wahyudi".
   - Penolakan tanggal terbalik berhasil memicu snackbar error *"Start date tidak boleh melebihi end date"*.
   - Pencarian nama "Irwan" dan pembersihan data via tombol *Reset* berfungsi dengan responsif.
   - Dialog filter multi-kriteria (Departemen QA & Tipe Izin: *Izin Pribadi*) menyaring data dengan akurat.
   - Modal detail izin menampilkan judul detail izin untuk "Irwan Wahyudi".
   - Paginasi baris tabel (5 baris) dan tombol *Export* Excel (.xlsx) sukses memicu feedback notifikasi.
3. **Laporan Izin Terlambat (`03_laporan_izin_terlambat.feature` — 100% Lulus):**
   - Halaman `/laporan/izin-terlambat` memuat data keterlambatan karyawan dengan filter tanggal presisi, penolakan tanggal terbalik, filter status *Approved*, modal detail, kontrol baris per halaman, dan ekspor berkas Excel.
4. **Laporan Izin Pulang Cepat (`04_laporan_izin_pulang_cepat.feature` — 100% Lulus):**
   - Halaman `/laporan/izin-pulang-cepat` memvalidasi catatan karyawan pulang mendahului jam kerja shift dengan filter tanggal, penolakan tanggal terbalik, pencarian nama, modal filter departemen, modal detail, dan ekspor laporan Excel.
5. **Fitur Pendukung Laporan Izin Off (`13_laporan_izin_off.feature` — 8 Skenario Lulus):**
   - Halaman `/laporan/izin-off` merender tabel data, pencarian nama, tombol reset, filter departemen, aksi tombol *Approve* baris pertama dengan snackbar sukses, paginasi baris, dan ekspor laporan Excel.

---

## 5. Kesimpulan & Langkah Tindak Lanjut

1. **Integritas Hasil Pengujian SQA:**
   - Tingkat kelulusan Fitur Izin mencapai **93.9% (46 dari 49 skenario lulus)**.
   - Seluruh **3 kegagalan (*failures*)** yang terekam pada Allure Report adalah **bukti temuan cacat sistem nyata (*Pure Product, Security & Validation Defects*)**, bukan kegagalan skrip otomasi pengujian.
2. **Prioritas Remediasi untuk Tim Pengembang (Dev Team):**
   - **P1 (Mendesak / High):** Perbaiki label popover menu aksi pada `/laporan/izin-off` agar tidak menampilkan label copy-paste modul koreksi (`DEFECT-OFF-001`).
   - **P2 (Penting / High):** Terapkan penegakan otorisasi peran pada endpoint persetujuan izin off agar mengembalikan kode standar `HTTP 403 Forbidden` (`SEC-IZN-001`).
   - **P3 (Penyempurnaan / Medium):** Tambahkan penanganan validasi snackbar error untuk input tanggal terbalik pada bilah filter Laporan Izin Off (`DEFECT-OFF-002`).
3. **Laporan Interaktif Allure:**  
   Laporan visual grafik, diagram alur, rekaman screenshot kegagalan, dan log REST Assured dapat diakses kapan saja dengan perintah:
   ```bash
   .allure/allure-2.29.0/bin/allure open target/allure-report
   ```
   *atau menyajikan report secara dinamis dari raw results:*
   ```bash
   .allure/allure-2.29.0/bin/allure serve target/allure-results
   ```

