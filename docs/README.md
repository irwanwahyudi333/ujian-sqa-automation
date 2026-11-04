# 📚 Indeks Dokumentasi Proyek SQA Automation

Folder `docs/` ini merupakan pusat dokumentasi resmi untuk proyek pengujian otomasi sistem HADIR, memuat katalog komponen antarmuka, laporan defect, tracking pengujian, dan arsitektur kode.

---

## 🗂️ Struktur & Panduan Navigasi Dokumen

### 1. 📋 [Defect Reports & Rekomendasi Perbaikan](defect-reports/) (`docs/defect-reports/`)
Kumpulan laporan temuan bug defect pengujian E2E (UI & API), analisis akar masalah (*root cause*), dan rekomendasi perbaikan untuk tim developer:
- [`LAPORAN_DEFECT_LOGIN_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_LOGIN_DAN_REKOMENDASI_PERBAIKAN.md) — Defect autentikasi, lockout, dan enkripsi RSA.
- [`LAPORAN_DEFECT_ABSEN_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_ABSEN_DAN_REKOMENDASI_PERBAIKAN.md) — Defect modul absensi masuk/pulang, selfie, dan radius GPS.
- [`LAPORAN_DEFECT_IZIN_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_IZIN_DAN_REKOMENDASI_PERBAIKAN.md) — Defect pengajuan izin terlambat, pulang cepat, dan izin off.
- [`LAPORAN_DEFECT_CUTI_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_CUTI_DAN_REKOMENDASI_PERBAIKAN.md) — Defect pengajuan cuti tahunan, kuota sisa cuti, dan validasi approval.
- [`LAPORAN_DEFECT_LEMBUR_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_LEMBUR_DAN_REKOMENDASI_PERBAIKAN.md) — Defect pengajuan lembur, kalkulasi durasi, dan kompensasi.
- [`LAPORAN_DEFECT_SAKIT_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_SAKIT_DAN_REKOMENDASI_PERBAIKAN.md) — Defect klaim sakit, upload surat dokter, dan file security.
- [`LAPORAN_DEFECT_DAN_REKOMENDASI_PERBAIKAN.md`](defect-reports/LAPORAN_DEFECT_DAN_REKOMENDASI_PERBAIKAN.md) — Laporan defect historis komprehensif.
- [`PRESENTASI_HASIL_PENGUJIAN_ALLURE.md`](defect-reports/PRESENTASI_HASIL_PENGUJIAN_ALLURE.md) — Format slide presentasi executive summary hasil pengujian Allure.

### 2. 📊 [Tracking Pengujian Modul](tracking/) (`docs/tracking/`)
Papan kendali pelacakan cakupan skenario pengujian, status eksekusi, dan matriks traceability antar modul:
- [`TRACKING_PENGUJIAN_APPS_ABSENT.md`](tracking/TRACKING_PENGUJIAN_APPS_ABSENT.md) — Pelacakan modul antarmuka mobile/client `/apps/absent/*`.
- [`TRACKING_PENGUJIAN_LEADER.md`](tracking/TRACKING_PENGUJIAN_LEADER.md) — Pelacakan modul operasional Team Leader (11 halaman).
- [`TRACKING_PENGUJIAN_MANAGEMENT.md`](tracking/TRACKING_PENGUJIAN_MANAGEMENT.md) — Pelacakan modul manajemen user, divisi, unit, dan posisi.
- [`TRACKING_PENGUJIAN_CLIENT_UPLINER.md`](tracking/TRACKING_PENGUJIAN_CLIENT_UPLINER.md) — Pelacakan modul Client Upliner V2/V3.
- [`TRACKING_PENGUJIAN_MONITORING.md`](tracking/TRACKING_PENGUJIAN_MONITORING.md) — Pelacakan modul Auditor Monitoring & Reporting (10 halaman).
- [`TRACKING_PENGUJIAN_LAPORAN.md`](tracking/TRACKING_PENGUJIAN_LAPORAN.md) — Pelacakan modul pelaporan admin (17 halaman).

### 3. 🧩 Katalog Komponen UI (`docs/COMPONENTS_*.md`)
Inventarisasi lengkap selector CSS, ID elemen, dropdown, modal dialog, dan payload endpoint per modul:
- [`COMPONENTS_CLIENT_UPLINER.md`](COMPONENTS_CLIENT_UPLINER.md) — Spesifikasi elemen modul Client Upliner.
- [`COMPONENTS_LEADER.md`](COMPONENTS_LEADER.md) — Spesifikasi elemen modul Team Leader.
- [`COMPONENTS_MANAGEMENT.md`](COMPONENTS_MANAGEMENT.md) — Spesifikasi elemen modul Manajemen Karyawan.
- [`COMPONENTS_MONITORING.md`](COMPONENTS_MONITORING.md) — Spesifikasi elemen modul Auditor Monitoring.
- [`COMPONENTS_REGISTER_USER_V2.md`](COMPONENTS_REGISTER_USER_V2.md) — Spesifikasi elemen formulir Registrasi Karyawan v2.
- [`KOMPONEN_APPS_ABSENT.md`](KOMPONEN_APPS_ABSENT.md) — Spesifikasi elemen antarmuka Apps Absent.
- [`ROLES_DAN_MANAJEMEN_PENGGUNA.md`](ROLES_DAN_MANAJEMEN_PENGGUNA.md) — Matriks RBAC hak akses peran pengguna.

### 4. 🏗️ Panduan Teknis Arsitektur
- [`PANDUAN_ARSITEKTUR_SRC.md`](PANDUAN_ARSITEKTUR_SRC.md) — Standar struktur arsitektur folder `src/`, pemisahan concern UI & API, ThreadLocal WebDriver, dan best practices.
- [`alur_pengujian_hadir.html`](alur_pengujian_hadir.html) — Poster interaktif visualisasi alur pengujian otomasi E2E platform HADIR.

