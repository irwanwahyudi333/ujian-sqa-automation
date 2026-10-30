# Dashboard Pemantauan & Tracking Pengujian Modul Client Mobile Hadir (`/apps/absent/*`)

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh **10 halaman modul Client Mobile Web Hadir** (`https://magang.dikahadir.com/apps/absent/*`).

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Scraping Komponen & Static-Web | Standar Test Case (.ai) | Feature File BDD | Step Definitions & POM | Live UI Execution |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **10 Halaman** | 🟢 **10 / 10 (100%)** | 🟢 **10 / 10 (100%)** | 🟢 **10 / 10 (100%)** | 🟢 **10 / 10 (100%)** | 🟢 **10 / 10 (100% PASSED)** |

- **Katalog Komponen:** Seluruh elemen antarmuka, selector, dan modal dialog telah didokumentasikan di [`docs/KOMPONEN_APPS_ABSENT.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/KOMPONEN_APPS_ABSENT.md).
- **Standar Pengujian (.ai):** Berkas standar acuan kasus uji resmi telah didefinisikan di [`.ai/standards/case_apps_absent.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/.ai/standards/case_apps_absent.md).
- **Allure Report:** Laporan visual interaktif pengujian telah berhasil di-generate di `target/site/allure-report`.

---

## 2. Matriks Pemantauan 10 Halaman Modul Apps Absent

| No | Modul / Halaman | Target URL | Kategori Fungsi | Static-Web Mock | Standar Case (.ai) | Feature File | Step Defs & POM | Status Pengujian Live UI |
| :---: | :--- | :--- | :--- | :---: | :---: | :---: | :---: | :---: |
| 1 | **Home / Dashboard** | `/apps/absent` | Ringkasan Presensi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (3 Skenario) |
| 2 | **Activity** | `/apps/absent/activity` | Riwayat Swafoto | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 3 | **Correction** | `/apps/absent/correction` | Koreksi Jam Absen | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 4 | **Permit** | `/apps/absent/permit` | Izin 3 Kategori | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (3 Skenario) |
| 5 | **Overtime Client** | `/apps/absent/overtime-client` | Pengajuan Lembur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 6 | **Leave Client** | `/apps/absent/leave-client` | Pengajuan Cuti | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 7 | **Sick** | `/apps/absent/sick` | Izin Sakit & Surat | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 8 | **Share Report** | `/apps/absent/reports/share_report` | Ekspor Excel Tim | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |
| 9 | **Staff** | `/apps/absent/staff` | Direktori Tim | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (1 Skenario) |
| 10 | **Change Password** | `/apps/absent/change-password` | Keamanan Akun | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 **100% PASSED** (2 Skenario) |

---

## 3. Strategi Pembagian Klaster Pengujian (Lean Roadmap)

Sesuai aturan `AGENTS.md` (Skema 1 - Lean Testing Strategy `@lean`), seluruh 10 modul telah diimplementasikan dan diuji secara menyeluruh:

### 🔹 Klaster 1: Portal Utama & Riwayat Presensi (2 Halaman) — 🟢 100% PASSED
- `apps/absent` (Home) — Render visual kehadiran, kartu counter, tombol navigasi
- `apps/absent/activity` (Activity / Riwayat) — Render list swafoto presensi dan tombol kembali

### 🔹 Klaster 2: Pengajuan Dispensasi & Perizinan (5 Halaman) — 🟢 100% PASSED
- `apps/absent/correction` (Koreksi) — Modal drawer koreksi jam hadir, tombol reset & submit
- `apps/absent/permit` (Izin: Terlambat, Pulang Cepat, Off) — Tab switcher 3 kategori, modal pengajuan
- `apps/absent/overtime-client` (Lembur) — List overtime, modal pengajuan lembur
- `apps/absent/leave-client` (Cuti) — Form cuti, picker tipe cuti, date range
- `apps/absent/sick` (Sakit & Bukti) — List riwayat sakit, modal upload surat dokter

### 🔹 Klaster 3: Pengawasan Tim, Laporan & Keamanan Akun (3 Halaman) — 🟢 100% PASSED
- `apps/absent/reports/share_report` (Download Report Upliner) — Verifikasi proteksi role otorisasi SEC-REP-001 (Server-Side Props Next.js)
- `apps/absent/staff` (View Staff & Kuota Cuti) — Render struktur tim bawahan
- `apps/absent/change-password` (Ubah Password) — Form ganti kata sandi & validasi toggle visibilitas password
