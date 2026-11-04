# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Sakit (Sick Leave) — Hasil Allure Report

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Fitur Pengajuan, Pelaporan, Validasi Medis & Monitoring Izin Sakit (*Sick Leave & Medical Certificate Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 23 Skenario Unik (18 Skenario UI Selenium WebDriver & 5 Skenario API REST Assured)  
**Hasil:** 18 Passed (78.3%), 5 Failed (Defects Detected Murni Selenium & REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (Path Traversal CWE-22, Data Binding Timestamp, Missing Medical Attachment Validation & Date Boundary Logic)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada modul Izin Sakit dengan strategi pengujian hemat (*Lean Testing Strategy - Skema 1*), pengujian telah memvalidasi seluruh alur tata kelola izin sakit: mulai dari formulir pengajuan mobile karyawan (`/apps/absent/sick`), audit pemantauan monitoring presensi (`/monitoring/sakit`), portal validasi Client Upliner (`/client/sick`), hingga konsolidasi laporan izin sakit dan surat dokter di tingkat Administrator HR (`/laporan/sakit`).

Dari **23 skenario pengujian unik** yang terekam secara komprehensif pada **Allure Report**:
1. **Pengujian Antarmuka UI Selenium WebDriver (18 Skenario): 14 Passed, 4 Failed (77.8% Lulus)**  
   Sebanyak 14 skenario antarmuka pada pelaporan admin, portal verifikasi Client Upliner V2/V3, portal pemantauan auditor monitoring, dan elemen dasar formulir mobile berjalan stabil.
   - **4 Cacat Antarmuka & Validasi Form Ditemukan:**
     - *DEFECT-SCK-001:* Pada kartu riwayat permohonan sakit mobile (`/apps/absent/sick`), label "Dari tanggal" & "Sampai Tanggal" menampilkan jam pengajuan (*timestamp*) bukan tanggal sakit yang dipilih.
     - *DEFECT-SCK-002:* Formulir pengajuan sakit dapat dikirim tanpa mengunggah berkas surat keterangan dokter (ketiadaan validasi *required attachment*).
     - *DEFECT-SCK-003:* Pengajuan izin sakit tanggal lampau (*backdate*) langsung disimpan tanpa konfirmasi dialog peringatan batas kebijakan perusahaan.
     - *DEFECT-SKT-003 (DTR-SKT-002):* Pada halaman Laporan Sakit admin (`/laporan/sakit`), kueri pencarian dengan tanggal terbalik (*Start Date > End Date*) tidak memunculkan notifikasi snackbar galat (*silent failure*).
2. **Pengujian Backend API REST Assured (5 Skenario): 4 Passed, 1 Failed (80.0% Lulus)**  
   Endpoint master data izin sakit (`/api/sakit`) memenuhi kriteria pengambilan data berotentikasi valid (`HTTP 200 OK`), pembuatan permohonan baru (`HTTP 201 Created`), proteksi token kosong (`HTTP 401 Unauthorized`), dan validasi payload dasar.
   - **1 Cacat Keamanan & Kontrak API Kritis Ditemukan (*SEC-SKT-001*):** Endpoint pengunduhan berkas surat dokter rentan terhadap manipulasi parameter *Path Traversal / Local File Inclusion* (CWE-22). Backend mengalami *unhandled server crash* (`HTTP 500 Internal Server Error`) saat disisipi urutan `../../../../etc/passwd` alih-alih menolak dengan penanganan error bersih (`HTTP 400` atau `HTTP 403`).

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-SKT-001** | `GET /api/sakit/document?file=...` | Path Traversal (CWE-22) & Server Crash (CWE-755) | 🔴 **Critical** | Server melempar `HTTP 500 Internal Server Error` saat parameter berkas disisipi `../../../../etc/passwd` | Input traversal disanitasi; request ditolak aman dengan `HTTP 400 Bad Request` atau `HTTP 403 Forbidden` |

---

### Matriks Temuan Cacat Antarmuka UI (Selenium WebDriver)

| ID Defect | Modul / Halaman | Komponen Bermasalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-SCK-001** | UI `/apps/absent/sick` | Kartu Riwayat Izin Sakit (*Data Binding*) | 🔴 **Major** | Entri "Dari tanggal" & "Sampai Tanggal" menampilkan tanggal dan jam pengajuan (timestamp: `2026-10-30, 15:34`), bukan tanggal sakit pilihan | Kartu riwayat merender tanggal sakit yang diajukan (`sick_request_date_from` & `sick_request_date_to`) |
| **DEFECT-SCK-002** | UI `/apps/absent/sick` | Drawer Form Ajukan Sakit (*Medical Upload*) | 🔴 **Major** | Pengajuan sakit lolos tanpa lampiran berkas surat dokter (`input[type='file']` tanpa atribut `required`) | Sistem memblokir submit dan menampilkan validasi error "Surat sakit dokter wajib diunggah" |
| **DEFECT-SCK-003** | UI `/apps/absent/sick` | Kebijakan Pengajuan Lampau (*Backdate Guard*) | 🟡 **Medium** | Pengajuan izin tanggal lampau (*backdate*) langsung tersimpan dengan status "Menunggu Approval" tanpa modal konfirmasi peringatan | Menampilkan modal konfirmasi dialog peringatan izin backdate sebelum data disimpan |
| **DEFECT-SKT-003** | UI `/laporan/sakit` | Bilah Filter Tanggal (*Date Inversion Validation*) | 🟡 **Medium** | Filter tanggal terbalik (*Start Date > End Date*) tidak memunculkan notifikasi snackbar error (*silent failure*) | Muncul notifikasi snackbar error "Start date tidak boleh melebihi end date" |

---

### Matriks Evaluasi Hasil Uji Per Sub-Modul (Allure Report)

| No | Modul / Halaman Pengujian | Layer Uji | Total Skenario | Passed | Failed | Status Kelulusan |
|:---:|---|:---:|:---:|:---:|:---:|:---:|
| 1 | API Rekapitulasi Izin Sakit (`sakit_api.feature`) | API | 5 | 4 | 1 | 🟡 **80.0%** (1 Defect SEC-SKT-001) |
| 2 | Pengajuan Sakit Client Mobile (`absent_sick_lean.feature`) | UI | 5 | 2 | 3 | 🔴 **40.0%** (3 Defects SCK-001/002/003) |
| 3 | Laporan Sakit HR Admin (`06_laporan_sakit.feature`) | UI | 11 | 10 | 1 | 🟢 **90.9%** (1 Defect DEFECT-SKT-003) |
| 4 | Client Portal Upliner (`ClientLaporan.feature`) | UI | 1 | 1 | 0 | 🟢 **100% Lulus** |
| 5 | Monitoring Presensi Auditor (`MonitoringPresensi.feature`) | UI | 1 | 1 | 0 | 🟢 **100% Lulus** |
| **TOTAL** | **Seluruh Modul Sakit** | **Hybrid** | **23** | **18** | **5** | 🟢 **78.3% Lulus** |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. SEC-SKT-001: Kerentanan Path Traversal pada Endpoint Pengunduhan Dokumen Medis
- **Kode Skenario / Tag Allure:** `@SEC-SKT-001 @security @path-traversal @sakit @lean` pada `sakit_api.feature`
- **Lokasi Endpoint:** API Pengunduhan Berkas Surat Dokter (`GET /api/sakit/document?file=...` / `/hadir-api-magang/sakit/document`)
- **Kategori Masalah:** CWE-22: Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal') & CWE-755: Improper Handling of Exceptional Conditions
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**  
  Saat klien mengirimkan parameter nama berkas surat dokter yang dimanipulasi dengan karakter dot-dot-slash:
  $$\text{Payload Traversal:} \quad \text{"../../../../etc/passwd"}$$
  backend tidak melakukan validasi sanitasi kanonikal path, melainkan meneruskan string mentah ke file system accessor, mengakibatkan *unhandled exception* yang merespons `HTTP 500 Internal Server Error`.
- **Dampak Keamanan:**  
  Jika file system memiliki izin baca yang luas, penyerang dapat mengeksfiltrasi berkas konfigurasi rahasia (*environment variables*, kunci privat JWT, berkas konfigurasi database, atau kredensial sistem). Selain itu, respon 500 menandakan *unhandled exception* yang membuka celah serangan *Denial of Service* (DoS).
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [SEC-SKT-001] Endpoint pengunduhan berkas medis rentan terhadap Path Traversal (CWE-22)! Server mengalami unhandled exception dan merespons HTTP 500 alih-alih menolak secara aman dengan HTTP 400 Bad Request atau HTTP 403 Forbidden. expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.api.steps.SakitApiSteps.sistemBackendHarusMenolakPermintaanPengunduhanBerkasDenganStatus400BadRequestAtau403Forbidden(SakitApiSteps.java:72)
  at ✽.sistem backend harus menolak permintaan pengunduhan berkas dengan status 400 Bad Request atau 403 Forbidden(file:///.../sakit_api.feature:44)
  ```
- **Akar Masalah (*Root Cause*):**  
  File handler controller menggunakan penggabungan path sederhana (`path.join(UPLOAD_DIR, req.query.file)`) tanpa memverifikasi apakah path hasil resolusi berada di dalam direktori penyimpanan berkas yang sah.
- **Rekomendasi Perbaikan Teknis (Backend Node.js / Express / NestJS):**  
  Gunakan metode pembersihan nama berkas berbasis `path.basename` atau validasi kanonikal path:
  ```typescript
  import path from 'path';
  import { BadRequestException, ForbiddenException } from '@nestjs/common';

  function safeGetMedicalDocument(requestedFile: string, baseStorageDir: string): string {
    // 1. Ekstrak hanya nama berkas murni tanpa direktori traversal
    const safeFilename = path.basename(requestedFile);
    if (safeFilename !== requestedFile || requestedFile.includes('..')) {
      throw new BadRequestException('Karakter nama berkas tidak valid atau terdeteksi path traversal.');
    }

    // 2. Verifikasi resolusi path kanonikal berada di dalam direktori unggahan
    const resolvedPath = path.resolve(baseStorageDir, safeFilename);
    if (!resolvedPath.startsWith(path.resolve(baseStorageDir))) {
      throw new ForbiddenException('Akses ke direktori di luar batas penyimpanan berkas ditolak.');
    }

    return resolvedPath;
  }
  ```

---

### 2. DEFECT-SCK-001: Data Binding Salah pada Kartu Riwayat Izin Sakit (Menampilkan Timestamp Pengajuan)
- **Kode Skenario / Tag Allure:** `@BUG-SCK-001 @DEFECT-SCK-001 @data-binding @live @sakit @lean` pada `absent_sick_lean.feature`
- **Lokasi Halaman:** Antarmuka Riwayat Permohonan Sakit Mobile (`/apps/absent/sick`)
- **Kategori Masalah:** UI Component Defect & Incorrect Property Binding
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Pada kartu riwayat permohonan sakit karyawan, properti visual "Dari tanggal" dan "Sampai Tanggal" terisi nilai waktu saat ini beserta jam menit (*timestamp* pengajuan seperti `2026-10-30, 15:34`), bukan tanggal izin sakit yang dipilih oleh pengguna saat mengisi formulir pengajuan (`2026-07-05`).
- **Dampak Bisnis & Operasional:**  
  Karyawan dan atasan tertipu oleh informasi tanggal pada kartu riwayat. Atasan menyangka karyawan mengajukan izin sakit pada jam dan hari ini, padahal karyawan mengajukan tanggal sakit yang berbeda.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [DEFECT-SCK-001] Total request bertambah satu, namun entri 'Dari tanggal' & 'Sampai tanggal' menampilkan tanggal dan jam pengajuan (timestamp), bukan tanggal sakit yang dipilih! expected [false] but found [true]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertFalse(Assert.java:79)
  at com.company.sqa.ui.steps.AbsentSickSteps.kartuRiwayatSakitHarusMenampilkanTanggalSakitYangDipilihBukanTanggalDanJamPengajuanSaatIni(AbsentSickSteps.java:57)
  at ✽.Kartu riwayat sakit harus menampilkan tanggal sakit yang dipilih bukan tanggal dan jam pengajuan saat ini(file:///.../absent_sick_lean.feature:24)
  ```
- **Akar Masalah (*Root Cause*):**  
  Komponen React melakukan binding ke properti `item.created_at` atau `new Date()` alih-alih memetakan properti `item.sick_request_date_from` dan `item.sick_request_date_to`.
- **Rekomendasi Perbaikan Teknis (Frontend React / Next.js):**
  ```jsx
  // Komponen Kartu Riwayat Sakit (Koreksi Binding Data):
  <Box className="user-sick-card-info">
    <Typography variant="body2" color="primary" fontWeight="bold">
      {item.user?.fullname || 'Nama Karyawan'}
    </Typography>
    {/* Koreksi: Binding ke sick_request_date bukan timestamp created_at */}
    <Typography variant="caption">
      Dari tanggal : {formatDate(item.sick_request_date_from)}
    </Typography>
    <Typography variant="caption">
      Sampai Tanggal : {formatDate(item.sick_request_date_to)}
    </Typography>
    <Typography variant="caption" color="textSecondary">
      Tanggal Pengajuan : {formatDate(item.created_at)}
    </Typography>
  </Box>
  ```

---

### 3. DEFECT-SCK-002: Formulir Pengajuan Izin Sakit Diterima Tanpa Lampiran Surat Dokter
- **Kode Skenario / Tag Allure:** `@BUG-SCK-002 @DEFECT-SCK-002 @negative @live @sakit @lean` pada `absent_sick_lean.feature`
- **Lokasi Halaman:** Drawer Modal Formulir Izin Sakit (`/apps/absent/sick`)
- **Kategori Masalah:** Missing Mandatory Form Validation & Medical Compliance Flaw
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Pengguna dapat membuka drawer pengajuan izin sakit dan menekan tombol *"Ajukan"* tanpa melampirkan berkas bukti surat keterangan dokter. Elemen `<input type="file">` tidak memiliki atribut `required` dan form submission tidak menghentikan proses saat berkas kosong.
- **Dampak Bisnis & Kepatuhan Medis:**  
  Karyawan dapat mengklaim ketidakhadiran sakit tanpa surat dokter yang sah. HRD mengalami kesulitan verifikasi medis dan berisiko terjadinya manipulasi ketidakhadiran kerja secara masif.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [DEFECT-SCK-002] Pengajuan izin sakit diterima tanpa lampiran surat sakit (tidak ada validasi kewajiban upload surat dokter)! expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.AbsentSickSteps.sistemHarusMenolakPengajuanDenganValidasiKewajibanLampiranSuratDokter(AbsentSickSteps.java:70)
  at ✽.Sistem harus menolak pengajuan dengan validasi kewajiban lampiran surat dokter(file:///.../absent_sick_lean.feature:29)
  ```
- **Akar Masalah (*Root Cause*):**  
  Ketiadaan validasi skema form (Formik / React Hook Form / Yup / Zod) yang mewajibkan objek `file` tidak boleh `null` saat status izin bernilai `"SAKIT"`.
- **Rekomendasi Perbaikan Teknis (Formik / React Hook Form Validation):**
  ```javascript
  const sickValidationSchema = Yup.object().shape({
    startDate: Yup.date().required('Tanggal mulai sakit wajib diisi'),
    endDate: Yup.date().min(Yup.ref('startDate'), 'Tanggal selesai tidak boleh sebelum tanggal mulai').required('Tanggal selesai wajib diisi'),
    doctorCertificate: Yup.mixed()
      .required('Surat keterangan sakit dari dokter wajib dilampirkan.')
      .test('fileSize', 'Ukuran berkas maksimal 5MB', (value) => value && value.size <= 5 * 1024 * 1024)
      .test('fileType', 'Format berkas harus JPG, PNG, atau PDF', (value) =>
        value && ['image/jpeg', 'image/png', 'application/pdf'].includes(value.type)
      )
  });
  ```

---

### 4. DEFECT-SCK-003: Absennya Konfirmasi Peringatan Kebijakan Izin Sakit Tanggal Lampau (*Backdate*)
- **Kode Skenario / Tag Allure:** `@BUG-SCK-003 @DEFECT-SCK-003 @boundary @policy @live @sakit @lean` pada `absent_sick_lean.feature`
- **Lokasi Halaman:** Formulir Pengajuan Sakit (`/apps/absent/sick`)
- **Kategori Masalah:** Business Workflow & Policy Enforcement Defect
- **Severity:** 🟡 **Medium**
- **Deskripsi Masalah:**  
  Ketika karyawan mengajukan izin sakit untuk tanggal di masa lampau (*backdate*, misal sakit 5 hari lalu), sistem langsung memasukkan permohonan ke antrean "Menunggu Approval" tanpa menampilkan dialog konfirmasi peringatan batas waktu klaim backdate (kebijakan perusahaan mensyaratkan batas maksimal H+2).
- **Dampak Bisnis:**  
  Pengajuan sakit tanggal lampau tanpa konfirmasi menimbulkan sengketa absensi dan ketidaksesuaian rekapitulasi gaji bulanan (*payroll cutoff*).
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [DEFECT-SCK-003] Pengajuan diterima tanpa peringatan! Entri backdate langsung masuk ke daftar dengan status 'Menunggu Approval'. expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.AbsentSickSteps.sistemHarusMenampilkanKonfirmasiPeringatanBackdateSebelumPermohonanDisetujuiUntukDisimpan(AbsentSickSteps.java:82)
  at ✽.Sistem harus menampilkan konfirmasi peringatan backdate sebelum permohonan disetujui untuk disimpan(file:///.../absent_sick_lean.feature:34)
  ```
- **Rekomendasi Perbaikan Teknis:**  
  Pasang *interceptor dialog* sebelum form dikirimkan jika tanggal sakit yang diajukan $< \text{Hari Ini}$:
  ```jsx
  if (selectedStartDate < todayStart) {
    setShowBackdateWarningDialog(true);
    return; // Tunggu konfirmasi eksplisit dari pengguna
  }
  ```

---

### 5. DEFECT-SKT-003: Ketiadaan Notifikasi Snackbar Error untuk Input Logika Tanggal Terbalik
- **Kode Skenario / Tag Allure:** `@DTR-SKT-002 @negative @ui @laporan @sakit @lean` pada `06_laporan_sakit.feature`
- **Lokasi Halaman:** Antarmuka Laporan Sakit HR Admin (`/laporan/sakit`)
- **Kategori Masalah:** Client-Side Form Validation & Silent Query Execution
- **Severity:** 🟡 **Medium**
- **Deskripsi Masalah:**  
  Pada bilah filter halaman Laporan Sakit, ketika admin memasukkan tanggal mulai yang lebih besar daripada tanggal akhir (`Start Date: 2026-10-26` dan `End Date: 2026-10-20`) kemudian menekan tombol *Search*, sistem tidak menampilkan notifikasi snackbar galat `"Start date tidak boleh melebihi end date"`.
- **Dampak Operasional:**  
  Terjadi inkonsistensi antarmuka sistem. Pada modul Laporan Lembur (`/laporan/overtime`), kesalahan tanggal terbalik memunculkan snackbar error merah secara responsif, sedangkan pada modul Laporan Sakit sistem melakukan *silent failure* sehingga pengguna bingung apakah pencarian gagal atau tidak ada data.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: Snackbar error sakit tidak tampil! expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.LaporanSakitSteps.sistemMenampilkanNotifikasiSnackbarErrorSakit(LaporanSakitSteps.java:70)
  at ✽.sistem menampilkan notifikasi snackbar error sakit "Start date tidak boleh melebihi end date"(file:///.../06_laporan_sakit.feature:27)
  ```
- **Rekomendasi Perbaikan Teknis (Frontend JavaScript / React Handler):**
  ```javascript
  const handleSearchClick = () => {
    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
      showSnackbar("Start date tidak boleh melebihi end date", "error");
      return; // Cegah kueri data
    }
    fetchLaporanSakitData({ startDate, endDate, keyword: searchQuery });
  };
  ```

---

## 4. Evaluasi Area Fitur yang Berfungsi Sempurna (Passed Areas)

Suite pengujian mengonfirmasi bahwa **18 skenario** modul sakit telah memenuhi standar kualitas tinggi:

1. **Integritas REST API Rekapitulasi Izin Sakit (`sakit_api.feature`):**
   - Kueri data riwayat sakit (`GET /api/sakit`) berhasil mengembalikan status `HTTP 200 OK` dan body array JSON yang terstruktur.
   - Pembuatan izin sakit berdokumen lengkap (`POST /api/sakit`) berhasil diproses dengan status `HTTP 201 Created`.
   - Payload tanggal terbalik pada API berhasil ditolak secara akurat dengan status `HTTP 400 Bad Request`.
   - Permintaan tanpa Bearer Token otorisasi ditolak secara tegas dengan status `HTTP 401 Unauthorized`.
2. **Ketersediaan Komponen Antarmuka Laporan Sakit Admin (`06_laporan_sakit.feature`):**
   - Halaman Laporan Sakit (`/laporan/sakit`) merender 11 kolom tabel secara rapi beserta kontrol pencarian, kalender filter, dan tombol export.
   - Penyaringan tanggal presisi satu hari kerja (`2026-10-26`) menampilkan data karyawan "Irwan Wahyudi".
   - Pencarian nama karyawan dan pembersihan data via tombol *Reset* berfungsi dengan responsivitas tinggi.
   - Modal filter multi-kriteria (Departemen "Quality Assurance" & Status "Verified") menyaring data tabel secara presisi.
   - Paginasi baris tabel (5 baris dan 10 baris) berjalan lancar.
   - Tombol *Export* berhasil mengunduh berkas laporan rekapitulasi ke dalam format Excel (`.xlsx`).
   - Gambar thumbnail bukti sakit dan avatar karyawan yang sah ter-render dengan sempurna tanpa *broken image*.
   - Tautan unduh berkas bukti surat sakit yang valid mengarah ke URL dokumen yang dapat diakses.
3. **Portal Client Upliner V2 & V3 (`ClientLaporan.feature`):**
   - Verifikasi pemantauan izin sakit karyawan binaan pada rute `/client/sick` berhasil menampilkan staf binaan "Clarissa" sesuai filter peran.
4. **Monitoring Presensi Auditor (`MonitoringPresensi.feature`):**
   - Auditor monitoring berhasil mengakses rekapitulasi izin sakit pada rute `/monitoring/sakit` dengan hak akses baca-saja (*read-only*) tanpa tombol aksi manipulasi data.

---

## 5. Kesimpulan & Langkah Tindak Lanjut

1. **Integritas Hasil Pengujian SQA:**
   - Tingkat kelulusan Modul Sakit mencapai **78.3% (18 dari 23 skenario lulus)**.
   - Seluruh **5 kegagalan (*failures*)** yang terekam pada Allure Report adalah **bukti temuan cacat sistem nyata (*Pure Product, Security & Validation Defects*)**, bukan kegagalan skrip otomasi.
2. **Prioritas Remediasi untuk Tim Pengembang (Dev Team):**
   - **P1 (Mendesak / Critical Security):** Pasang sanitasi path kanonikal pada endpoint pengunduhan berkas medis untuk menutup kerentanan *Path Traversal* (`SEC-SKT-001`).
   - **P2 (Penting / High):** Perbaiki data binding tanggal pada kartu riwayat sakit mobile (`DEFECT-SCK-001`) dan pasang validasi kewajiban lampiran surat dokter (`DEFECT-SCK-002`).
   - **P3 (Penyempurnaan / Medium):** Tambahkan dialog peringatan batas pengajuan backdate (`DEFECT-SCK-003`) dan standarisasi notifikasi snackbar error tanggal terbalik pada Laporan Sakit (`DEFECT-SKT-003`).
3. **Laporan Interaktif Allure:**  
   Laporan visual grafik, diagram alur, rekaman screenshot kegagalan, dan log REST Assured dapat diakses kapan saja dengan perintah:
   ```bash
   .allure/allure-2.29.0/bin/allure open target/allure-report
   ```
   *atau menyajikan report secara dinamis dari raw results:*
   ```bash
   .allure/allure-2.29.0/bin/allure serve target/allure-results
   ```

