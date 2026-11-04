# Dashboard Pemantauan & Tracking Pengujian Modul Monitoring (Auditor Monitoring) — HADIR

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh **10 Halaman Modul Monitoring** pada aplikasi **HADIR** (`https://magang.dikahadir.com/monitoring/*` dan mirror offline `http://localhost:8085/monitoring/*`). Pengujian ini secara spesifik menggunakan akun dengan peran **Auditor Monitoring / User Monitoring (`role_id: monitoring`)**.

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Cakupan Skenario Peran | Belum Dikerjakan (🔴) | Dalam Pengerjaan (🟡) | Selesai / Terverifikasi (🟢) |
| :---: | :---: | :---: | :---: | :---: |
| **10 Halaman** | **10 Modul Auditor Monitoring** | **0 (0%)** | **0 (0%)** | **10 (100%)** |

*(Catatan: Akun Auditor Monitoring dirancang sebagai peran audit/pengawas bertipe Read-Only. Auditor memiliki hak akses memantau seluruh presensi karyawan secara lintas unit/mitra dan mengunduh berkas laporan, namun **tidak memiliki hak wewenang modifikasi data master, tombol approval, atau pembatalan permohonan**).*

---

## 2. Profil Akun Uji Auditor Monitoring

Pengujian modul Monitoring dijalankan menggunakan akun resmi Auditor Monitoring yang terdaftar aktif pada sistem HADIR:

| Parameter Akun | Detail Informasi Akun Uji |
|:---|:---|
| **Nama Akun / Pengguna** | **Auditor Monitoring SQA Test** |
| **Peran Resmi (Role)** | **User Monitoring (`monitoring`)** |
| **Email Akun Uji** | `sqa.monitoring@dikahadir.com` |
| **Password Akun Uji** | `P@ssw0rdMonitoring123!` |
| **Landing URL Default** | `https://magang.dikahadir.com/monitoring/semua` |
| **Karakteristik Hak Akses** | **Read-Only & Audit Export** (Akses pengawasan presensi dan generator rekapitulasi laporan tanpa hak mutasi/approval data) |

---

## 3. Matriks Pemantauan 10 Halaman Modul Monitoring

| No | Modul / Halaman | Target URL | Kategori Klaster | Static-Web Mock | Standar Test Case | Feature File | Step Defs | Page Object | Status Uji Auditor | Status Akhir |
|:--:|:---|:---|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **Monitoring Semua** | `https://magang.dikahadir.com/monitoring/semua` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 2 | **Monitoring Kehadiran** | `https://magang.dikahadir.com/monitoring/kehadiran` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 3 | **Monitoring Koreksi** | `https://magang.dikahadir.com/monitoring/koreksi` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 4 | **Monitoring Sakit** | `https://magang.dikahadir.com/monitoring/sakit` | Presensi & Ringkasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 5 | **Monitoring Cuti** | `https://magang.dikahadir.com/monitoring/cuti` | Cuti & Hak Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 6 | **Monitoring Lembur** | `https://magang.dikahadir.com/monitoring/lembur` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 7 | **Download Report Kehadiran** | `https://magang.dikahadir.com/monitoring/download-reports/kehadiran` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 8 | **Download Report Cuti** | `https://magang.dikahadir.com/monitoring/download-reports/cuti` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 9 | **Download Report Lembur** | `https://magang.dikahadir.com/monitoring/download-reports/lembur` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |
| 10 | **Download Report Shifting** | `https://magang.dikahadir.com/monitoring/download-reports/shifting` | Ekspor & Unduh Rekap | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lulus 100% |

---

## 4. Pengelompokan Klaster Pengerjaan (Phased Roadmap)

Untuk menjaga pengujian tetap terarah, terukur, dan hemat kuota sesuai **Skema 1 - Piramida Uji Hemat (Lean Testing)**, 10 modul monitoring dikelompokkan ke dalam 3 klaster fungsional:

### 🔹 Klaster 1: Presensi, Ringkasan & Koreksi Monitoring (4 Halaman)
Fokus pada audit pemantauan presensi karyawan, peninjauan jam masuk/pulang, dan pelacakan surat sakit:
- [x] 1. `/monitoring/semua` — Dashboard rekapitulasi metrik kehadiran seluruh karyawan
- [x] 2. `/monitoring/kehadiran` — Pemantauan log kehadiran harian dan swafoto presensi (selfie)
- [x] 3. `/monitoring/koreksi` — Pemantauan riwayat dan status pengajuan koreksi jam absensi
- [x] 4. `/monitoring/sakit` — Pemantauan rekapitulasi izin sakit serta kelengkapan dokumen surat medis

### 🔹 Klaster 2: Pengawasan Cuti & Lembur Karyawan (2 Halaman)
Fokus pada audit pengambilan hak libur tahunan dan rekapitulasi jam kompensasi lembur:
- [x] 5. `/monitoring/cuti` — Pemantauan riwayat pengajuan cuti dan status persetujuan atasan
- [x] 6. `/monitoring/lembur` — Pemantauan akumulasi jam lembur dan status verifikasi kompensasi

### 🔹 Klaster 3: Unduh & Ekspor Laporan Rekapitulasi Audit (4 Halaman)
Fokus pada pengujian formulir filter rentang tanggal operasional, departemen, dan unduhan berkas laporan (.xlsx):
- [x] 7. `/monitoring/download-reports/kehadiran` — Unduh rekapitulasi absensi karyawan untuk audit internal
- [x] 8. `/monitoring/download-reports/cuti` — Unduh rekapitulasi pemakaian kuota cuti
- [x] 9. `/monitoring/download-reports/lembur` — Unduh laporan akumulasi jam lembur seluruh unit
- [x] 10. `/monitoring/download-reports/shifting` — Unduh rekap roster jadwal kerja shift karyawan

---

## 5. Strategi Pengujian Khusus Peran Auditor Monitoring

Mengikuti prinsip keamanan **Role-Based Access Control (RBAC)** dan asas *Least Privilege*:

1. **Uji Fungsionalitas Read-Only (Positive Path):**
   - Memverifikasi kemampuan akun `monitoring` dalam memuat 10 halaman target tanpa hambatan otorisasi (HTTP 200).
   - Memvalidasi fungsi pencarian NIK/Nama staf pada kolom `#search`.
   - Memvalidasi penyaringan data berdasarkan rentang tanggal (`#start-date` & `#end-date`).
   - Memvalidasi interaksi pagination (perpindahan halaman dan perubahan baris per halaman).
   - Memvalidasi kemampuan mengekspor laporan pada menu `/monitoring/download-reports/*`.
2. **Uji Integritas Keamanan & Batasan Akses (Negative / Least Privilege Audit):**
   - Memastikan **TIDAK ADA tombol modifikasi master data** (seperti tombol Tambah Karyawan, Edit, atau Hapus).
   - Memastikan **TIDAK ADA tombol aksi approval/reject** pada tabel presensi, koreksi, cuti, atau lembur (berbeda dengan portal Client Upliner atau Admin).
   - Memverifikasi pada sisi API REST Assured bahwa akun `monitoring` ditolak (HTTP 403 Forbidden) apabila mencoba melakukan request mutasi data (`POST`, `PUT`, `DELETE`).

---

## 6. Riwayat Eksekusi & Log Kemajuan

| Tanggal | Tahapan | Deskripsi Pengerjaan | Status |
|:---|:---|:---|:---:|
| 31 Okt 2026 | Inisialisasi | Pembuatan papan kendali pelacakan 10 halaman monitoring (`TRACKING_PENGUJIAN_MONITORING.md`) | 🟢 Selesai |
| 31 Okt 2026 | Katalog Komponen | Dokumentasi selector CSS/ID dan struktur layout modul monitoring (`docs/COMPONENTS_MONITORING.md`) | 🟢 Selesai |
| 31 Okt 2026 | Mockup Static-Web | Pembuatan berkas mirror offline 10 halaman di `static-web/monitoring/` & routing `LocalWebServer` | 🟢 Selesai |
| 31 Okt 2026 | Standar Test Case | Penyusunan 3 berkas spesifikasi standar uji dan taksonomi bug di `.ai/standards/` | 🟢 Selesai |
| 31 Okt 2026 | Otomasi UI & POM | Pembuatan `MonitoringPage.java`, `MonitoringSteps.java`, dan 3 berkas feature Cucumber BDD | 🟢 Selesai |
| 31 Okt 2026 | Eksekusi Pengujian | Eksekusi 10 skenario `@monitoring and @lean` dengan akun `sqa.monitoring@dikahadir.com` (10/10 Lolos) | 🟢 Lulus 100% |

