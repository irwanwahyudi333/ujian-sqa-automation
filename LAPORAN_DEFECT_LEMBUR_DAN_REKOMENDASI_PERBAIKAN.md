# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Lembur (Overtime)

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul:** Fitur Pengelolaan & Validasi Lembur (*Overtime Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 02 November 2026  
**Total Skenario Dijalankan:** 38 Skenario Unik (33 Skenario UI Selenium, 5 Skenario REST API)  
**Hasil:** 36 Passed (94.7%), 2 Failed (UI State Management), 1 Live Defect Teridentifikasi  
**Tingkat Kritis:** 🔴 **Tinggi (Business Workflow & UI/UX State Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian hybrid otomatis (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada modul Lembur, pengujian telah memvalidasi seluruh siklus hidup lembur: mulai dari pengajuan di level mobile client, peninjauan oleh Team Leader, validasi bertingkat oleh Client Portal (Upliner V2 & V3), hingga pengawasan dan pelaporan di level Administrator/Auditor HR.

Dari **38 skenario pengujian unik** yang dieksekusi:
- **36 Skenario (94.7%) Berhasil Lolos (*Passed*):** Mencakup validasi kontrak API, filter pencarian presisi, penolakan logika tanggal terbalik, kontrol paginasi, pembukaan drawer formulir, serta ekspor berkas Excel.
- **2 Skenario Gagal (*Failures*):** Ditemukan pada modul Portal Client (`ClientLembur.feature`), di mana modal dialog aksi persetujuan validasi (*Validator Action Modal*) tidak menutup atau mengalami *state transition lag* setelah tombol aksi disubmit.
- **1 Cacat Kritis Live Target (*Live Defect*):** Ditemukan pada halaman Laporan Overtime Admin (`07_laporan_overtime.feature`), di mana modal aksi "Ubah atasan user yang request" kehilangan kontrol dropdown dan input form untuk memproses pergantian atasan.

---

## 2. Matriks Temuan Cacat (Defect Matrix - Modul Lembur)

| ID Defect | Modul / Halaman / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-OT-001** | UI `/laporan/overtime` | Missing Form Controls & Actions | 🔴 **Major** | Modal "Ubah atasan user yang request" tidak memuat dropdown pilihan atasan baru | Modal memuat dropdown data atasan yang aktif dan tombol konfirmasi update |
| **DEFECT-OT-002** | UI `/client/lembur/validator` (Tahap 1) | UI State & Modal Dismissal | 🟡 **Medium** | Modal `#modal-action-validator` tetap terdeteksi aktif setelah Upliner V2 menyetujui klaim | Modal wajib tertutup otomatis (`display: none` / unmount) setelah aksi submit berhasil |
| **DEFECT-OT-003** | UI `/client/lembur/validator` (Tahap 2) | UI State & Modal Dismissal | 🟡 **Medium** | Modal `#modal-action-validator` tetap terdeteksi aktif setelah Upliner V3 menyetujui klaim | Modal wajib tertutup otomatis (`display: none` / unmount) setelah aksi submit berhasil |
| **SEC-OT-001** | API `/api/ot-reason` & Mutasi Persetujuan | Multi-Level Authorization (RBAC / Anti-IDOR) | 🟡 **Medium** | Validasi token RBAC wajib memastikan hierarki Upliner V2 tidak dapat mem-bypass tahap Upliner V3 | Backend menolak mutasi status final jika tahap verifikasi prasyarat belum disetujui |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. DEFECT-OT-001: Modal Ubah Atasan Kehilangan Kontrol Form & Dropdown Pilihan
- **Kode Skenario:** `@BUG-OT-001 @DEFECT-OT-001 @live` pada `07_laporan_overtime.feature`
- **Lokasi Halaman:** Antarmuka Laporan Overtime (`/laporan/overtime`)
- **Deskripsi Masalah:**
  Pada tabel laporan lembur admin, ketika tombol aksi "Ubah Data" pada baris karyawan diklik, sistem membuka dialog dengan judul *"Ubah atasan user yang request"*. Namun, di dalam kontainer dialog tersebut tidak tersedia elemen form input, autocomplete, atau dropdown pilihan atasan pengganti. Akibatnya, admin tidak dapat memilih atasan baru maupun menyelesaikan pembaruan data.
- **Dampak Bisnis & Operasional:**
  Admin HR tidak dapat memperbaiki kesalahan penugasan atasan lembur karyawan melalui antarmuka visual. Jika atasan yang bersangkutan sedang berhalangan/cuti, pengajuan lembur karyawan akan tertahan (*stuck*) tanpa ada atasan pengganti yang dapat menyetujuinya.
- **Error Stack Trace:**
  ```text
  org.openqa.selenium.TimeoutException: 
  Expected condition failed: waiting for visibility of element located by By.xpath: //div[@role='dialog']
  at com.company.sqa.pages.LaporanOvertimePage.clickRowActionButton(LaporanOvertimePage.java:232)
  ```
- **Rekomendasi Perbaikan (Frontend/UI):**
  Lengkapi komponen modal dialog dengan komponen dropdown berbasis data atasan aktif:
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

### 2. DEFECT-OT-002 & DEFECT-OT-003: Modal Validasi Tidak Menutup Pasca-Submit Persetujuan
- **Kode Skenario:** `@tc-cl-ot-v2-01` & `@tc-cl-ot-v3-01` pada `ClientLembur.feature`
- **Lokasi Halaman:** Portal Client Validator Lembur (`/client/lembur/validator`)
- **Deskripsi Masalah:**
  Saat pengguna dengan peran Upliner V2 (Validator 1) atau Upliner V3 (Validator 2) membuka modal validasi lembur, mengisi catatan, dan menekan tombol *"Setujui Lembur"* (`#btn-approve-validator`), notifikasi umpan balik berhasil dipicu, tetapi modal dialog `#modal-action-validator` tetap berstatus terlihat pada DOM dan tidak tertutup sempurna (*lingering modal*).
- **Dampak UX & Pengujian:**
  Pengguna terhalang untuk melanjutkan peninjauan baris data lembur berikutnya karena layar masih tertutup *modal overlay*. Dalam otomasi pengujian, hal ini menyebabkan asersi kegagalan:
  ```text
  java.lang.AssertionError: Modal validasi harus tertutup setelah submit! expected [false] but found [true]
  at com.company.sqa.ui.steps.ClientPortalSteps.notifikasiUmpanBalikValidasiBerhasilDitampilkan(ClientPortalSteps.java:123)
  ```
- **Akar Masalah (*Root Cause*):**
  1. Kontainer `.modal-overlay` menggunakan transisi CSS `visibility: hidden; opacity: 0; transition: all 0.2s ease;` tanpa mengubah properti `display: none` atau me-unmount elemen dari React virtual DOM.
  2. Fungsi penutup modal tidak dipanggil secara deterministik jika terjadi *race condition* antara proses penulisan *state* tabel dan *event loop* penutupan dialog.
- **Rekomendasi Perbaikan (Frontend JavaScript & CSS):**
  Pastikan penutupan modal dialog mengubah *display style* menjadi `none` atau menggunakan unmount bersyarat:
  ```javascript
  function submitOtValidation(act) {
    const item = rawData.find(d => d.id === activeOtId);
    if (item) {
      // 1. Mutasi status data
      item.status = act === 'APPROVE' ? 'Approved' : 'Rejected';
      
      // 2. Tutup modal secara tuntas dan hapus overlay
      const modalEl = document.getElementById("modal-action-validator");
      modalEl.classList.remove("active");
      modalEl.style.display = "none"; // Pastikan display none agar tidak terdeteksi oleh WebDriver
      
      // 3. Tampilkan feedback snackbar dan render ulang tabel
      showSnackbar("Klaim lembur berhasil diproses!");
      renderTable();
    }
  }
  ```

---

### 3. SEC-OT-001: Verifikasi Integritas Otorisasi Berjenjang (*Multi-Level Workflow*)
- **Deskripsi & Risiko Keamanan:**
  Alur bisnis lembur pada HADIR mewajibkan persetujuan berjenjang:
  $$\text{Pengajuan Staf} \longrightarrow \text{Persetujuan Leader} \longrightarrow \text{Validasi Upliner V2} \longrightarrow \text{Validasi Upliner V3 (Final)}$$
  Jika endpoint backend tidak memverifikasi riwayat status prasyarat saat request `POST /api/overtime/approval` diterima, terdapat potensi kerentanan **BOLA (*Broken Object Level Authorization*)** atau **IDOR**, di mana pengguna biasa atau Validator 1 dapat langsung mengubah status klaim menjadi `Approved` secara final.
- **Rekomendasi Perbaikan (Backend Middleware & Workflow Guard):**
  ```typescript
  // Service Validasi Lembur Berjenjang (Backend NestJS / Express):
  async function validateOvertimeApproval(overtimeId: string, currentUser: User, action: 'APPROVE' | 'REJECT') {
    const otRecord = await OvertimeModel.findById(overtimeId);
    if (!otRecord) throw new NotFoundException('Data klaim lembur tidak ditemukan.');

    // Verifikasi Hak Akses Tahap 1 (Upliner V2)
    if (otRecord.status === 'PENDING_V1') {
      if (currentUser.role !== 'UPLINER_V2') {
        throw new ForbiddenException('Hanya Upliner V2 yang berwenang melakukan verifikasi tahap 1.');
      }
      otRecord.status = action === 'APPROVE' ? 'PENDING_V2' : 'REJECTED';
      otRecord.v1ApproverId = currentUser.id;
    }
    // Verifikasi Hak Akses Tahap 2 (Upliner V3)
    else if (otRecord.status === 'PENDING_V2') {
      if (currentUser.role !== 'UPLINER_V3') {
        throw new ForbiddenException('Hanya Upliner V3 yang berwenang melakukan verifikasi final tahap 2.');
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

Suite pengujian mengonfirmasi bahwa **36 skenario** lembur telah memenuhi standar kualitas tinggi:

1. **Integritas REST API Master Alasan Lembur (`ot_reason_api.feature`):**
   - Mendapatkan daftar master alasan lembur (`GET /api/ot-reason`) mengembalikan status `HTTP 200 OK` dengan skema JSON array yang valid.
   - Pembuatan alasan lembur baru (`POST /api/ot-reason`) berhasil dengan status `HTTP 201 Created`.
   - Payload nama kosong (`{"name": ""}`) ditolak secara tegas dengan `HTTP 400 Bad Request`.
   - Permintaan tanpa Bearer Token ditolak dengan `HTTP 401 Unauthorized`.
2. **Penanganan Validasi Logika Rentang Tanggal (*Date Inversion Validation*):**
   - Baik pada halaman Laporan Overtime maupun Validasi Overtime, pengisian tanggal terbalik (`Start Date: 2026-10-26` > `End Date: 2026-10-20`) langsung memicu notifikasi error:
     > *"Start date tidak boleh melebihi end date"*
3. **Filter Pencarian Multi-Kriteria & Reset:**
   - Pencarian berbasis kata kunci nama karyawan dan NIK berjalan responsif.
   - Tombol *Reset* mengembalikan seluruh data tabel ke kondisi semula tanpa merusak paginasi.
   - Filter dialog multi-kriteria (Departemen & Status Persetujuan) menyaring data dengan tepat.
4. **Fitur Ekspor Laporan Excel:**
   - Tombol *Export* pada halaman Laporan Overtime dan Validasi Overtime berhasil memicu notifikasi sukses berkas `.xlsx`.
5. **Drawer Pengajuan Lembur Mobile Client (`absent_overtime_lean.feature`):**
   - Halaman `overtime-client` memuat tombol aksi ajukan lembur dan modal drawer formulir lembur dengan seluruh field yang lengkap.

---

## 5. Kesimpulan & Langkah Tindak Lanjut

1. **Stabilitas Modul Lembur:**
   Modul lembur memiliki tingkat kelulusan yang tinggi (**94.7%**), menunjukkan bahwa logika bisnis dasar, filter data, paginasi, ekspor berkas, dan endpoint API master telah terimplementasi dengan kokoh.
2. **Prioritas Tindak Lanjut untuk Tim Pengembang (Dev Team):**
   - **P1 (High):** Implementasikan kontrol dropdown pada modal *"Ubah atasan user yang request"* di `/laporan/overtime` agar admin dapat melakukan perubahan atasan secara visual.
   - **P2 (Medium):** Perbaiki transisi penutupan modal dialog validasi di `/client/lembur/validator` dengan memastikan elemen dialihkan ke `display: none` pasca aksi submit.
   - **P3 (Hardening):** Pastikan backend API memberlakukan *strict state validation* pada alur persetujuan lembur berjenjang guna mencegah eksploitasi IDOR/BOLA.
3. **Menjalankan Pengujian Regresi Modul Lembur:**
   ```bash
   mvn test -Dcucumber.filter.tags="@lembur" -Dheadless=false
   ```
4. **Membuka Laporan Interaktif Allure:**
   ```bash
   allure serve target/allure-results
   ```
