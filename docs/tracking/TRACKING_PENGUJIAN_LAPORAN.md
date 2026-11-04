# Dashboard Pemantauan & Tracking Pengujian Modul Laporan — HADIR

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh 17 halaman modul Laporan pada aplikasi **HADIR** (`https://magang.dikahadir.com/laporan/*`).

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Belum Dikerjakan (🔴) | Dalam Pengerjaan (🟡) | Selesai / Terverifikasi (🟢) |
| :---: | :---: | :---: | :---: |
| **17 Halaman** | **0** | **0** | **17 (100%)** |

*(Catatan: Halaman `/laporan/all` sebelumnya sudah selesai diuji dan menjadi baseline template standar untuk halaman-halaman laporan lainnya).*  
*(Katalog Komponen: Seluruh elemen antarmuka dan selector 17 halaman laporan telah didokumentasikan di [`docs/COMPONENTS_LAPORAN.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/COMPONENTS_LAPORAN.md)).*

---

## 2. Matriks Pemantauan 17 Halaman Modul Laporan

| No | Modul / Halaman Laporan | Target URL | Kategori Modul | Static-Web Mock | Standar Test Case | Feature File | Step Defs | Page Object | Status Pengerjaan |
| :---: | :--- | :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | **Laporan Activity** | `https://magang.dikahadir.com/laporan/activity` | Aktivitas & Kehadiran | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 2 | **Laporan Izin** | `https://magang.dikahadir.com/laporan/izin` | Perizinan & Ketidakhadiran | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 3 | **Laporan Izin Terlambat** | `https://magang.dikahadir.com/laporan/izin-terlambat` | Perizinan & Ketidakhadiran | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 4 | **Laporan Izin Pulang Cepat** | `https://magang.dikahadir.com/laporan/izin-pulang-cepat` | Perizinan & Ketidakhadiran | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 5 | **Laporan Cuti** | `https://magang.dikahadir.com/laporan/cuti` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 6 | **Laporan Sakit** | `https://magang.dikahadir.com/laporan/sakit` | Perizinan Medis | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 7 | **Laporan Overtime** | `https://magang.dikahadir.com/laporan/overtime` | Lembur & Kompensasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 8 | **Laporan Validation Overtime** | `https://magang.dikahadir.com/laporan/validation-overtime` | Validasi & Persetujuan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 9 | **Laporan Shifting** | `https://magang.dikahadir.com/laporan/shifting` | Jadwal Kerja & Shift | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 10 | **Laporan Meeting** | `https://magang.dikahadir.com/laporan/meeting` | Operasional & Acara | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 11 | **Laporan Perjalanan Dinas** | `https://magang.dikahadir.com/laporan/perjalanan-dinas` | Operasional Luar Kantor | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 12 | **Laporan Koreksi** | `https://magang.dikahadir.com/laporan/koreksi` | Koreksi Absensi / Data | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 13 | **Laporan Izin Off** | `https://magang.dikahadir.com/laporan/izin-off` | Penyesuaian Libur/Off | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 14 | **Generate Report Type** | `https://magang.dikahadir.com/laporan/generate-report-type` | Rekapitulasi & Ekspor | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 15 | **New Generate** | `https://magang.dikahadir.com/laporan/new-generate` | Pembuatan Laporan Baru | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 16 | **Laporan Sisa Cuti** | `https://magang.dikahadir.com/laporan/sisa-cuti` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 17 | **Laporan Daily Report** | `https://magang.dikahadir.com/laporan/daily-report` | Ringkasan Harian | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |

---

## 3. Pengelompokan Klaster Pengerjaan (Phased Roadmap)

Agar pengujian berjalan terstruktur, hemat kuota browser, dan modular, pengerjaan dibagi menjadi 5 fase klaster fungsional:

### 🔹 Klaster 1: Perizinan & Ketidakhadiran (4 Halaman)
Fokus pada pengajuan, alasan izin/sakit, lampiran surat dokter/keterangan, dan status approval:
- [x] 1. `laporan/izin`
- [x] 2. `laporan/izin-terlambat`
- [x] 3. `laporan/izin-pulang-cepat`
- [x] 4. `laporan/sakit`

### 🔹 Klaster 2: Cuti & Hak Hari Kerja (3 Halaman) - 🟢 100% Selesai
Fokus pada alokasi hari cuti, saldo/sisa cuti tahunan, dan izin penyesuaian hari libur:
- [x] 5. `laporan/cuti`
- [x] 6. `laporan/sisa-cuti`
- [x] 7. `laporan/izin-off`

### 🔹 Klaster 3: Lembur, Shifting & Koreksi Absensi (4 Halaman) - 🟢 100% Selesai
Fokus pada kalkulasi jam lembur, approval supervisor, jadwal rotasi shift, dan perbaikan catatan log kehadiran:
- [x] 8. `laporan/overtime`
- [x] 9. `laporan/validation-overtime`
- [x] 10. `laporan/shifting`
- [x] 11. `laporan/koreksi`

### 🔹 Klaster 4: Operasional Kerja & Tugas Luar (3 Halaman) - 🟢 100% Selesai
Fokus pada daftar meeting kerja, absensi rapat, agenda dinas, dan ringkasan aktivitas:
- [x] 12. `laporan/activity`
- [x] 13. `laporan/meeting`
- [x] 14. `laporan/perjalanan-dinas`

### 🔹 Klaster 5: Generator & Rekapitulasi Eksekutif (3 Halaman) - 🟢 100% Selesai
Fokus pada pemilihan tipe laporan, penarikan data berjangka, rekapitulasi harian, dan ekspor dokumen:
- [x] 15. `laporan/daily-report`
- [x] 16. `laporan/generate-report-type`
- [x] 17. `laporan/new-generate`

---

## 4. Standar Pengujian Setiap Halaman (Definition of Done)

Setiap halaman laporan yang dikerjakan wajib memenuhi kriteria berikut sebelum statusnya diubah menjadi 🟢 **Selesai**:
1. **Inspeksi Elemen Visual:** Memverifikasi judul modul, filter date range, input search NIK/Nama, tombol filter/reset, tombol ekspor, serta struktur kolom tabel.
2. **Skenario Lean UI (`@lean`):** Dibatasi pada 5 alur kritis antarmuka (Load halaman, Filter tanggal, Pencarian kata kunci, Pagination, Buka modal detail/filter).
3. **API & Data Boundary (`@api`):** Menangani seluruh permutasi data, invalid date format, SQLi/XSS pada kolom search, dan verifikasi struktur JSON/Excel export.
4. **Verifikasi Allure:** Hasil pengujian lolos uji otomatis dan terdaftar pada laporan Allure Report.
5. **Pembaruan Tracking:** Ceklis dan status pada file ini diperbarui secara berkala.

---

## 5. Daftar Temuan Cacat UI Live (Live Defect Register)

Berikut adalah daftar cacat antarmuka dan interaksi yang berhasil diidentifikasi langsung pada lingkungan live (`https://magang.dikahadir.com`):

| ID Defect | Modul / Halaman | Komponen Bermasalah | Deskripsi Cacat Fungsional | Severity | Status Allure | Bukti Assertion / Kegagalan |
|---|---|---|---|---|---|---|
| **DEFECT-OT-001** | `/laporan/overtime` | Modal *"Ubah atasan user yang request"* & Tombol *"Ubah Data"* | Modal dialog tidak menyediakan elemen dropdown/combobox untuk memilih Atasan 2 atau Atasan 3 (hanya merender teks statis `-`). Tombol "Ubah Data" tidak dapat digunakan untuk memilih/memperbarui data atasan baru. | 🔴 Major / High | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-CUTI-001** | `/laporan/cuti` | Menu Kebab &rarr; Opsi Edit &rarr; Modal *"Ubah atasan user yang request"* | Menu "Edit" pada baris cuti memunculkan modal "Ubah atasan user yang request" yang sama persis cacatnya: tidak ada kontrol input/select atasan (hanya teks statis `-`), dan tombol "Ubah Data" tidak berfungsi. | 🔴 Major / High | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-DNS-002** | `/laporan/perjalanan-dinas` | Menu Kebab &rarr; Opsi "Reject Perjalanan Dinas" &rarr; Tombol *"Ya"* | Tombol 'Ya' pada modal dialog konfirmasi Reject Perjalanan Dinas tidak merespons (Dead Button). Dialog tetap terbuka, tidak ada request ke API, dan aksi penolakan dinas tidak pernah dieksekusi. | 🔴 Major / High | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-SKT-001** | `/laporan/sakit` | Komponen Avatar & Thumbnail Foto Bukti Sakit | Ditemukan 4 gambar/thumbnail bukti surat sakit dan avatar karyawan yang rusak (broken image / HTTP 404) karena me-request berkas dari URL `uploads/null` atau path berkas dev yang tidak ditemukan. | 🔴 Major / Medium | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-SKT-002** | `/laporan/sakit` | Tautan Tombol *"Download"* pada Kolom FOTO | Tautan tombol Download berkas bukti sakit mengarah ke URL invalid `https://api-dev.dikahadir.com/uploads/null` yang menghasilkan error HTTP 404 saat diunduh pengguna. | 🔴 Major / Medium | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-OFF-001** | `/laporan/izin-off` | Menu Kebab Aksi Baris Tabel (`aria-label='action'`) | Menu aksi pada Laporan Izin Off salah label (copy-paste dari modul Koreksi). Menu yang ditampilkan adalah `[Approval Koreksi, Reject Koreksi]`, bukan untuk Izin Off. | 🟡 Medium | ❌ **FAILED (Product defect)** | Screenshot tertangkap otomatis di Allure |
| **DEFECT-IZN-001** | `/laporan/izin` | Tabel Data saat Kosong (Empty State) | Tabel data tidak menampilkan pesan status kosong (*Empty State Placeholder*), melainkan hanya merender elemen `<tbody>` kosong melompong. | 🟢 Low (UX) | ⚠️ Teridentifikasi Live | DOM Inspection `table tbody` |

---

## 6. Ringkasan Status Akhir Allure Report

- **Total Pengujian Dieksekusi:** 193 Uji
- **Total Lolos (Passed):** 169 Uji
- **Total Cacat Resmi (Product Defects / Failed):** **24 Defect**
  - **18 Defect API & Security:** (Token JWT 1000 hari, Hardcoded Secret, Google Maps Key tanpa restriksi referer, kebocoran foto selfie tanpa otorisasi, status code login mismatch 400 vs 500, dll.)
  - **6 Defect UI Live Fungsional:** (Dead Button Reject Perjalanan Dinas, Broken Images Sakit, Broken Download Link Sakit, Dropdown Kosong Ubah Atasan Lembur, Dropdown Kosong Ubah Atasan Cuti, Salah Label Menu Izin Off)
- **Lokasi Laporan Interaktif Allure:** `target/site/allure-maven-plugin/index.html`




