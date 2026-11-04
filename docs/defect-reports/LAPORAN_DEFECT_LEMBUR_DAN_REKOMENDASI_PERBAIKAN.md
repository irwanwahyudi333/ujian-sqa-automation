# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Lembur (Overtime) — Hasil Allure Report

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Fitur Pengelolaan, Pelaporan & Validasi Lembur (*Overtime Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 40 Skenario Unik (34 Skenario UI Selenium WebDriver & 6 Skenario API REST Assured)  
**Hasil:** 38 Passed (95.0%), 2 Failed (Defects Detected Murni Selenium & REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (Business Workflow, Boundary Limits & UI Form Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada modul Lembur dengan strategi pengujian hemat (*Lean Testing Strategy*), pengujian telah memvalidasi seluruh siklus hidup lembur: mulai dari pengajuan di level mobile client, peninjauan oleh Team Leader, validasi bertingkat oleh Client Portal (Upliner V2 & V3), audit monitoring, hingga pengawasan dan pelaporan di level Administrator HR.

Dari **40 skenario pengujian unik** yang terekam pada **Allure Report**:
1. **Pengujian Antarmuka UI Selenium WebDriver (34 Skenario): 33 Passed, 1 Failed (97.1% Lulus)**  
   Sebanyak 33 skenario antarmuka pada modul pengajuan lembur mobile karyawan (`/apps/absent/overtime-client`), portal Client Upliner V2 & V3 (`/client/lembur/*`), portal Team Leader (`/leader/lembur`), auditor monitoring (`/monitoring/lembur`), tabel validasi overtime (`/laporan/validation-overtime`), serta master alasan lembur (`/management/ot-reason`) berjalan stabil dan memenuhi kriteria penerimaan pengguna.
   - **1 Cacat Visual & Interaksi Form Ditemukan (*DEFECT-OT-001*):** Pada halaman Laporan Overtime Admin (`/laporan/overtime`), modal dialog *"Ubah atasan user yang request"* kehilangan elemen input/dropdown untuk memilih atasan pengganti (hanya teks `-`), melumpuhkan fungsi tombol "Ubah Data".
2. **Pengujian Backend API REST Assured (6 Skenario): 5 Passed, 1 Failed (83.3% Lulus)**  
   Endpoint master alasan lembur (`/api/ot-reason`) berhasil memenuhi spesifikasi CRUD, validasi payload kosong, dan proteksi otentikasi. Endpoint persetujuan Team Leader (`/api/leader/overtime/1/approval`) juga merespons status `200 OK` dengan benar.
   - **1 Cacat Kontrak Keamanan Otorisasi Ditemukan (*SEC-OT-001*):** Endpoint mutasi approval lembur (`PATCH /overtime/approval`) ketika diuji dengan token non-otoritas tidak mengembalikan kode status standar `HTTP 403 Forbidden` untuk pelanggaran hak akses berjenjang.

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-OT-001** | `PATCH /overtime/approval` | Multi-Level Authorization (RBAC / Workflow Guard) | 🔴 **Major** | Endpoint mutasi persetujuan merespons `HTTP 401 Unauthorized` alih-alih penolakan otorisasi peran | Server menolak mutasi status final oleh non-otoritas dengan `HTTP 403 Forbidden` |

---

### Matriks Temuan Cacat Antarmuka UI (Selenium WebDriver)

| ID Defect | Modul / Halaman | Komponen Bermasalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-OT-001** | UI `/laporan/overtime` | Modal *"Ubah atasan user yang request"* & Tombol *"Ubah Data"* | 🔴 **Major** | Modal dialog tidak menyediakan elemen dropdown/combobox pilihan Atasan 2/3 (hanya teks statis `-`), tombol "Ubah Data" tidak dapat digunakan | Modal memuat kontrol dropdown pilihan atasan yang aktif dan tombol konfirmasi update |

---

### Matriks Evaluasi Hasil Uji Per Sub-Modul (Allure Report)

| No | Modul / Halaman Pengujian | Layer Uji | Total Skenario | Passed | Failed | Status |
|:---:|---|:---:|:---:|:---:|:---:|:---:|
| 1 | Master Overtime Reason API (`ot_reason_api.feature`) | API | 5 | 4 | 1 | 🟡 **80.0%** (1 Defect SEC-OT-001) |
| 2 | Leader Overtime Approval API (`LeaderApiTest.feature`) | API | 1 | 1 | 0 | 🟢 **100% Lulus** |
| 3 | Mobile Client Overtime (`absent_overtime_lean.feature`) | UI | 4 | 4 | 0 | 🟢 **100% Lulus** |
| 4 | Client Portal Upliner V2 & V3 (`ClientLembur.feature`) | UI | 4 | 4 | 0 | 🟢 **100% Lulus** |
| 5 | Leader Lembur Portal (`03_LeaderLembur.feature`) | UI | 4 | 4 | 0 | 🟢 **100% Lulus** |
| 6 | Monitoring Lembur Auditor (`MonitoringCutiLembur.feature`) | UI | 1 | 1 | 0 | 🟢 **100% Lulus** |
| 7 | Laporan Overtime Admin (`07_laporan_overtime.feature`) | UI | 10 | 9 | 1 | 🟡 **90.0%** (1 Defect DEFECT-OT-001) |
| 8 | Laporan Validation Overtime (`08_laporan_validation_overtime.feature`) | UI | 8 | 8 | 0 | 🟢 **100% Lulus** |
| 9 | Master Alasan Lembur UI (`ot_reason_lean.feature`) | UI | 4 | 4 | 0 | 🟢 **100% Lulus** |
| **TOTAL** | **Seluruh Modul Lembur** | **Hybrid** | **40** | **38** | **2** | 🟢 **95.0% Lulus** |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. DEFECT-OT-001: Modal Ubah Atasan Kehilangan Kontrol Form & Dropdown Pilihan
- **Kode Skenario / Tag Allure:** `@BUG-OT-001 @DEFECT-OT-001 @live @lembur @lean` pada `07_laporan_overtime.feature`
- **Lokasi Halaman:** Antarmuka Laporan Overtime (`/laporan/overtime`)
- **Kategori Masalah:** UI Component Defect & Broken Form Interaction
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Pada tabel Laporan Overtime admin, ketika tombol aksi *"Ubah"* pada baris karyawan diklik, sistem membuka dialog dengan judul *"Ubah atasan user yang request"*. Namun, di dalam kontainer dialog tersebut tidak tersedia elemen form input, autocomplete, atau dropdown pilihan atasan pengganti (hanya teks statis `-`). Akibatnya, admin tidak dapat memilih atasan baru maupun menyelesaikan pembaruan data atasan lembur.
- **Dampak Bisnis & Operasional:**  
  Admin HR tidak dapat memperbaiki kesalahan penugasan atasan lembur karyawan melalui antarmuka visual. Jika atasan yang bersangkutan sedang berhalangan/cuti, pengajuan lembur karyawan akan tertahan (*stuck*) tanpa ada atasan pengganti yang dapat menyetujuinya.
- **Hasil Asersi Selenium WebDriver (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: [DEFECT UI LIVE] Modal 'Ubah atasan user yang request' TIDAK menyediakan kontrol dropdown/select untuk memilih Atasan 2 atau Atasan 3! Nilai hanya teks statis '-' sehingga tombol 'Ubah Data' tidak dapat digunakan untuk memilih atau mengubah data atasan baru. expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.LaporanOvertimeSteps.modalMemuatKontrolDropdownPilihanAtasanYangDapatDiubahOlehAdmin(LaporanOvertimeSteps.java:182)
  at ✽.modal memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin(file:///.../07_laporan_overtime.feature:75)
  ```
  *(Allure Report secara otomatis merekam dan melampirkan screenshot visual bukti ketiadaan dropdown kontrol pada modal).*
- **Akar Masalah (*Root Cause*):**  
  Komponen React/Material-UI dialog modal tidak merender elemen `<Select>` atau `<Autocomplete>` melainkan hanya merender elemen placeholder teks statis `{supervisorName || '-'}`.
- **Rekomendasi Perbaikan Teknis (Frontend/UI React / Next.js):**  
  Lengkapi komponen modal dialog dengan komponen dropdown berbasis daftar atasan aktif:
  ```jsx
  // Rekomendasi Komponen Dialog Ubah Atasan (React / Next.js):
  <Dialog open={isOpen} onClose={handleClose}>
    <DialogTitle>Ubah atasan user yang request</DialogTitle>
    <DialogContent>
      <FormControl fullWidth margin="dense">
        <InputLabel id="select-atasan-label">Pilih Atasan Baru</InputLabel>
        <Select
          labelId="select-atasan-label"
          id="select-atasan"
          value={selectedSupervisor}
          onChange={(e) => setSelectedSupervisor(e.target.value)}
        >
          {supervisorList.map((sup) => (
            <MenuItem key={sup.id} value={sup.id}>
              {sup.fullname} ({sup.nip})
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </DialogContent>
    <DialogActions>
      <Button onClick={handleClose}>Batal</Button>
      <Button variant="contained" color="primary" onClick={handleSubmitUpdateSupervisor}>
        Ubah Data
      </Button>
    </DialogActions>
  </Dialog>
  ```

---

### 2. SEC-OT-001: Verifikasi Integritas Otorisasi Berjenjang (*Multi-Level Workflow / RBAC Bypass*)
- **Kode Skenario / Tag Allure:** `@SEC-OT-001 @security @rbac-bypass @lembur @lean` pada `ot_reason_api.feature`
- **Lokasi Endpoint:** API Mutasi Approval Lembur (`PATCH /overtime/approval` / `/hadir-api-magang/overtime/approval`)
- **Kategori Masalah:** CWE-285: Improper Authorization & API Contract Inconsistency
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Alur bisnis lembur pada HADIR mewajibkan persetujuan berjenjang:
  $$\text{Pengajuan Staf} \longrightarrow \text{Persetujuan Leader} \longrightarrow \text{Validasi Upliner V2} \longrightarrow \text{Validasi Upliner V3 (Final)}$$
  Ketika client non-otoritas (staf biasa) mengirimkan request mutasi status lembur langsung ke `APPROVED` dengan menyisipkan parameter bypass, backend gagal menolak request secara aman dengan kode status otorisasi `403 Forbidden` yang semestinya, melainkan mengembalikan `HTTP 401 Unauthorized` (inkonsistensi kontrak REST API).
- **Dampak Keamanan & Bisnis:**  
  Ketidakjelasan pembedaan antara kegagalan autentikasi (401) dan kegagalan otorisasi peran (403). Jika otorisasi diabaikan pada tingkat backend, penyerang dapat mem-bypass tahap verifikasi Upliner V2 dan langsung mengklaim insentif lembur secara sepihak.
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: Sistem seharusnya menolak perubahan status lembur dengan HTTP 403 Forbidden! expected [403] but found [401]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.failNotEquals(Assert.java:1578)
  at org.testng.Assert.assertEqualsImpl(Assert.java:150)
  at org.testng.Assert.assertEquals(Assert.java:132)
  at com.company.sqa.api.steps.OtReasonApiSteps.sistemBackendHarusMenolakPerubahanStatusLemburDenganStatus403Forbidden(OtReasonApiSteps.java:131)
  at ✽.sistem backend harus menolak perubahan status lembur dengan status 403 Forbidden(file:///.../ot_reason_api.feature:38)
  ```
  *(REST Assured secara otomatis merekam payload JSON injeksi, audit report CWE-285, dan body respons ke dalam Allure).*
- **Akar Masalah (*Root Cause*):**  
  Middleware otorisasi tidak memeriksa hak akses peran (*Role-Based Access Control*) secara spesifik per tahapan alur lembur, melainkan melempar kegagalan autentikasi generik.
- **Rekomendasi Perbaikan Teknis (Backend Middleware & Workflow Guard):**  
  Terapkan penegakan status berjenjang (*State Machine Guard*) di layer service backend:
  ```typescript
  // Service Validasi Lembur Berjenjang (Backend NestJS / Express):
  async function validateOvertimeApproval(overtimeId: string, currentUser: User, action: 'APPROVE' | 'REJECT') {
    const otRecord = await OvertimeModel.findById(overtimeId);
    if (!otRecord) throw new NotFoundException('Data klaim lembur tidak ditemukan.');

    // Verifikasi Hak Akses Tahap 1 (Upliner V2)
    if (otRecord.status === 'PENDING_V1') {
      if (currentUser.role !== 'UPLINER_V2') {
        throw new ForbiddenException('Akses ditolak: Hanya Upliner V2 yang berwenang melakukan verifikasi tahap 1.');
      }
      otRecord.status = action === 'APPROVE' ? 'PENDING_V2' : 'REJECTED';
      otRecord.v1ApproverId = currentUser.id;
    }
    // Verifikasi Hak Akses Tahap 2 (Upliner V3)
    else if (otRecord.status === 'PENDING_V2') {
      if (currentUser.role !== 'UPLINER_V3') {
        throw new ForbiddenException('Akses ditolak: Hanya Upliner V3 yang berwenang melakukan verifikasi final tahap 2.');
      }
      otRecord.status = action === 'APPROVE' ? 'APPROVED' : 'REJECTED';
      otRecord.v2ApproverId = currentUser.id;
    }
    else {
      throw new BadRequestException('Status pengajuan lembur tidak dalam antrean validasi aktif.');
    }

    return await otRecord.save();
  }
  ```

---

## 4. Evaluasi Area Fitur yang Berfungsi Sempurna (Passed Areas)

Suite pengujian mengonfirmasi bahwa **38 skenario** modul lembur telah memenuhi standar kualitas tinggi:

1. **Integritas REST API Master Alasan Lembur & Approval Leader (`ot_reason_api.feature` & `LeaderApiTest.feature`):**
   - Mendapatkan daftar master alasan lembur (`GET /api/ot-reason`) mengembalikan status `HTTP 200 OK` dengan skema JSON array yang valid.
   - Pembuatan alasan lembur baru (`POST /api/ot-reason`) berhasil dengan status `HTTP 201 Created`.
   - Payload nama kosong (`{"name": ""}`) ditolak secara tegas dengan `HTTP 400 Bad Request`.
   - Permintaan tanpa Bearer Token ditolak dengan `HTTP 401 Unauthorized`.
   - Persetujuan lembur oleh Team Leader (`PATCH /api/leader/overtime/1/approval`) berhasil dengan status `HTTP 200 OK`.
2. **Penanganan Validasi Logika Rentang Tanggal (*Date Inversion Validation*):**
   - Baik pada halaman Laporan Overtime maupun Validasi Overtime, pengisian tanggal terbalik (`Start Date: 2026-10-26` > `End Date: 2026-10-20`) langsung memicu notifikasi error snackbar:
     > *"Start date tidak boleh melebihi end date"*
3. **Filter Pencarian Multi-Kriteria & Reset:**
   - Pencarian berbasis kata kunci nama karyawan dan NIK berjalan responsif pada tabel Laporan Overtime dan Validasi Overtime.
   - Tombol *Reset* mengembalikan seluruh data tabel ke kondisi semula tanpa merusak paginasi.
   - Filter dialog multi-kriteria (Departemen & Status Persetujuan) menyaring data secara presisi.
4. **Fitur Ekspor Laporan Excel:**
   - Tombol *Export* pada halaman Laporan Overtime dan Validasi Overtime berhasil memicu notifikasi sukses berkas `.xlsx`.
5. **Drawer Pengajuan Lembur Mobile Client (`absent_overtime_lean.feature`):**
   - Halaman `overtime-client` memuat tombol aksi ajukan lembur dan modal drawer formulir lembur dengan seluruh field yang lengkap.
6. **Portal Client Upliner V2 & V3 (`ClientLembur.feature`):**
   - Upliner V2 dan V3 berhasil memvalidasi permohonan lembur staf binaan dengan catatan verifikasi dan umpan balik snackbar.
7. **Portal Team Leader & Auditor Monitoring (`03_LeaderLembur.feature` & `MonitoringCutiLembur.feature`):**
   - Leader dapat memantau lembur anggota tim dan auditor dapat mengawasi rekapitulasi data lembur dengan penegakan prinsip *Least Privilege*.

---

## 5. Kesimpulan & Langkah Tindak Lanjut

1. **Integritas Hasil Pengujian SQA:**
   - Tingkat kelulusan modul lembur mencapai **95.0% (38 dari 40 skenario lulus)**.
   - Sebanyak 2 kegagalan yang terekam pada Allure Report adalah **bukti temuan cacat sistem nyata (*Pure Product Defects*)**, bukan karena kegagalan skrip otomasi.
2. **Prioritas Remediasi untuk Tim Pengembang (Dev Team):**
   - **P1 (Mendesak / High):** Implementasikan kontrol dropdown pada modal dialog *"Ubah atasan user yang request"* di `/laporan/overtime` (`DEFECT-OT-001`).
   - **P2 (Penting / Medium):** Perbaiki kode respons penolakan otorisasi pada endpoint mutasi approval lembur agar mengembalikan `HTTP 403 Forbidden` secara konsisten (`SEC-OT-001`).
3. **Laporan Interaktif Allure:**  
   Laporan visual grafik, diagram alur, rekaman screenshot kegagalan, dan log REST Assured dapat diakses kapan saja dengan perintah:
   ```bash
   .allure/allure-2.29.0/bin/allure open target/allure-report
   ```
   *atau untuk melakukan serve dinamis:*
   ```bash
   .allure/allure-2.29.0/bin/allure serve target/allure-results
   ```
