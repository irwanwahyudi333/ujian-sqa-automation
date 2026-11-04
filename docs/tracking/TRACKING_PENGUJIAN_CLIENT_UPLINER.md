# Dashboard Pemantauan & Tracking Pengujian Modul Client & Upliner (V2 & V3) — HADIR

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh 17 halaman modul Client & Upliner pada aplikasi **HADIR** (`https://magang.dikahadir.com/client/*`). Pengujian ini dirancang khusus untuk menguji **dua peran sekaligus secara komparatif**, yaitu **Upliner V2 (Data Validator 1)** dan **Upliner V3 (Data Validator 2)**.

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Cakupan Matriks Uji | Belum Dikerjakan (🔴) | Dalam Pengerjaan (🟡) | Selesai / Terverifikasi (🟢) |
| :---: | :---: | :---: | :---: | :---: |
| **17 Halaman** | **34 Skenario Role (17 Upliner V2 + 17 Upliner V3)** | **0 (0%)** | **0 (0%)** | **17 (100%)** |

*(Catatan: Rute URL antara Upliner V2 dan Upliner V3 adalah identik (`/client/*`), namun dibedakan berdasarkan `role_id` akun sesi (`client` vs `client3`) serta hak akses bertingkat pada tab Validator dan penugasan staf bawahan).*  
*(Katalog Komponen: Seluruh elemen antarmuka, selector CSS/ID, modal dialog, dan endpoint 17 halaman client & upliner telah didokumentasikan di [`docs/COMPONENTS_CLIENT_UPLINER.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/COMPONENTS_CLIENT_UPLINER.md)).*

---

## 2. Profil Akun Uji Otorisasi Ganda (Dual Role Matrix)

Pengujian modul Client & Upliner dijalankan menggunakan dua akun uji resmi yang telah terdaftar aktif pada sistem HADIR:

| No | Peran / Tingkatan | Kode `role_id` | Email Akun Uji | Password Akun Uji | Peran dalam Alur Persetujuan | Landing URL |
|:--:|:------------------|:--------------:|:---------------|:------------------|:-----------------------------|:------------|
| 1 | **Upliner V2** | `client` | `sqa.upliner.v2@dikahadir.com` | `P@ssw0rdUpliner123!` | **Data Validator 1** (Verifikasi Awal Tingkat 1) | `/client/all` |
| 2 | **Upliner V3** | `client3` | `sqa.upliner.v3@dikahadir.com` | `P@ssw0rdUpliner123!` | **Data Validator 2** (Verifikasi Lanjutan Tingkat 2) | `/client/all` |

---

## 3. Matriks Pemantauan 17 Halaman Modul Client & Upliner

| No | Modul / Halaman | Target URL | Kategori Klaster | Static-Web Mock | Standar Test Case | Feature File | Step Defs | Page Object | Status V2 | Status V3 | Status Akhir |
|:--:|:---|:---|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **Laporan Semua Client** | `https://magang.dikahadir.com/client/all` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 2 | **Laporan Kehadiran** | `https://magang.dikahadir.com/client/kehadiran` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 3 | **Laporan Koreksi** | `https://magang.dikahadir.com/client/koreksi` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 4 | **Laporan Sakit** | `https://magang.dikahadir.com/client/sick` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 5 | **Cuti Semua** | `https://magang.dikahadir.com/client/cuti/semua` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 6 | **Cuti Pending** | `https://magang.dikahadir.com/client/cuti/pending` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 7 | **Cuti Validator** | `https://magang.dikahadir.com/client/cuti/validator` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 8 | **Lembur Semua** | `https://magang.dikahadir.com/client/lembur/semua` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 9 | **Lembur Pending** | `https://magang.dikahadir.com/client/lembur/pending` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 10 | **Lembur Validator** | `https://magang.dikahadir.com/client/lembur/validator` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 11 | **Shifting Semua** | `https://magang.dikahadir.com/client/shifting/semua` | Jadwal & Pola Shift | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 12 | **Shifting Pending** | `https://magang.dikahadir.com/client/shifting/pending` | Jadwal & Pola Shift | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 13 | **Download Report Kehadiran** | `https://magang.dikahadir.com/client/download-reports/kehadiran` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 14 | **Download Report Cuti** | `https://magang.dikahadir.com/client/download-reports/cuti` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 15 | **Download Report Sisa Cuti** | `https://magang.dikahadir.com/client/download-reports/sisa-cuti` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 16 | **Download Report Lembur** | `https://magang.dikahadir.com/client/download-reports/lembur` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 17 | **Download Report Shifting** | `https://magang.dikahadir.com/client/download-reports/shifting` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |

---

## 4. Pengelompokan Klaster Pengerjaan (Phased Roadmap)

Agar pengujian berjalan modular, hemat kuota browser, dan terstruktur sesuai kaidah **Lean Testing (Skema 1)**, pengerjaan dipecah menjadi 5 klaster fungsional:

### 🔹 Klaster 1: Presensi, Ringkasan & Koreksi Client (4 Halaman)
Fokus pada pemantauan presensi staf mitra, tinjauan bukti sakit, serta koreksi jam absensi tim:
- [x] 1. `/client/all` — Ringkasan rekapitulasi kehadiran staf mitra
- [x] 2. `/client/kehadiran` — Laporan presensi harian staf klien
- [x] 3. `/client/koreksi` — Pengajuan perbaikan jam presensi tim
- [x] 4. `/client/sick` — Pengajuan izin sakit staf beserta surat keterangan dokter

### 🔹 Klaster 2: Pengelolaan & Validasi Cuti Client (3 Halaman)
Fokus pada visibilitas cuti staf dan persetujuan bertingkat Validator 1 (V2) vs Validator 2 (V3):
- [x] 5. `/client/cuti/semua` — Riwayat seluruh cuti staf klien
- [x] 6. `/client/cuti/pending` — Daftar antrean permohonan cuti baru
- [x] 7. `/client/cuti/validator` — Aksi verifikasi & persetujuan berjenjang cuti (Data Validator 1 & 2)

### 🔹 Klaster 3: Pengelolaan & Validasi Lembur Client (3 Halaman)
Fokus pada audit durasi lembur tim dan validasi kompensasi lembur berjenjang:
- [x] 8. `/client/lembur/semua` — Rekapitulasi permohonan lembur seluruh staf
- [x] 9. `/client/lembur/pending` — Antrean permohonan klaim lembur pending
- [x] 10. `/client/lembur/validator` — Aksi verifikasi & persetujuan klaim lembur (Data Validator 1 & 2)

### 🔹 Klaster 4: Pengelolaan Jadwal Kerja & Shifting (2 Halaman)
Fokus pada monitoring pertukaran shift kerja regu staf klien:
- [x] 11. `/client/shifting/semua` — Rekap seluruh rotasi jadwal kerja/shift
- [x] 12. `/client/shifting/pending` — Pengajuan penukaran atau perubahan pola shift yang menunggu persetujuan

### 🔹 Klaster 5: Download & Rekapitulasi Laporan Client (5 Halaman)
Fokus pada parameter filter periode tanggal, pemilihan departemen, dan ekspor dokumen (.xlsx/.csv):
- [x] 13. `/client/download-reports/kehadiran` — Unduh rekapitulasi presensi tim
- [x] 14. `/client/download-reports/cuti` — Unduh laporan pengambilan cuti
- [x] 15. `/client/download-reports/sisa-cuti` — Unduh saldo sisa kuota cuti tahunan
- [x] 16. `/client/download-reports/lembur` — Unduh ringkasan jam dan biaya lembur
- [x] 17. `/client/download-reports/shifting` — Unduh roster jadwal shifting tim

---

## 5. Strategi Pengujian Ganda (Lean Testing & Multi-Role)

Mengikuti kebijakan **Skema 1 (Piramida Uji Hemat / Lean Testing)** dan aturan arsitektur peran HADIR:

1. **Uji Komparatif Dual-Role:**
   - **Upliner V2 (`role_id: client`):** Menguji kemampuan bertindak sebagai **Validator 1**, melihat staf yang terhubung pada `upliner2_id`, dan menjalankan verifikasi tahap awal.
   - **Upliner V3 (`role_id: client3`):** Menguji kemampuan bertindak sebagai **Validator 2**, melihat staf yang terhubung pada `upliner3_id`, dan melakukan approval lanjutan setelah disetujui Validator 1.
2. **UI Testing Selenium (`@lean`):**
   - Dibatasi hanya pada alur kritis antarmuka (pemuatan halaman, interaksi tab filter, navigasi paginasi, pembukaan modal verifikasi/dialog approval, dan verifikasi header tabel).
   - Pengujian UI dijalankan bergantian untuk sesi akun Upliner V2 dan Upliner V3 guna memvalidasi visibilitas elemen validator yang tepat.
3. **API Testing REST Assured (`@api`):**
   - Menangani pengujian status HTTP (200, 401, 403), validasi batas tanggal, skema respons JSON, payload persetujuan/penolakan data, serta proteksi otorisasi jika akun staf biasa mencoba mengakses rute `/client/*`.
4. **Isolasi Data Staf (Security RBAC):**
   - Memastikan akun Upliner V2/V3 dari satu unit kerja mitra tidak dapat melihat atau menyetujui pengajuan staf milik mitra/divisi lain (*IDOR / Horizontal Privilege Escalation*).

---

## 6. Standar Pengujian Setiap Halaman (Definition of Done)

Setiap halaman modul Client & Upliner wajib memenuhi kriteria berikut sebelum statusnya diubah menjadi 🟢 **Selesai**:
1. **Inspeksi Elemen & Komponen Visual:** Memverifikasi struktur judul halaman, breadcrumbs, search bar, filter tanggal, tab status (*Semua/Pending/Validator*), dan kolom tabel.
2. **Katalog Komponen:** Didokumentasikan secara rapi pada file referensi komponen UI.
3. **Standar Test Case (`.ai/standards/`):** Menyusun katalog skenario P1, P2, P3 lengkap dengan langkah uji dan hasil yang diharapkan.
4. **Feature File Cucumber (`.feature`):** Dibuat dengan format Gherkin bahasa Indonesia dan alokasi tag yang terstruktur (`@ui`, `@api`, `@client`, `@upliner-v2`, `@upliner-v3`, `@lean`).
5. **Implementasi Page Object & Step Definitions:** Kode automasi modular, *reusable*, serta terintegrasi dengan `SessionHelper` dan WebDriver manager.
6. **Eksekusi Lolos Allure Report:** Seluruh skenario pengujian berhasil dieksekusi tanpa flakiness pada Allure Report.

---

## 7. Daftar Temuan Cacat Client Upliner (Defect Register)

Berikut adalah tabel pencatatan kendala fungsional dan cacat produk yang ditemukan selama pengujian berlangsung:

| ID Defect | Modul / Halaman | Komponen Bermasalah | Deskripsi Cacat Fungsional | Severity | Role Terdampak | Status Allure |
|:---:|:---|:---|:---|:---:|:---:|:---:|
| *(Belum ada)* | - | - | - | - | - | - |

---

## 8. Ringkasan Status Eksekusi Allure

- **Total Pengujian Client & Upliner Direncanakan:** 0 Uji
- **Total Lolos (Passed):** 0 Uji
- **Total Cacat (Failed / Product Defects):** 0 Defect
- **Target Laporan Interaktif Allure:** `target/site/allure-maven-plugin/index.html`

