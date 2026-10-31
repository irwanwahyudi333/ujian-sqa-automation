# Katalog Komponen & Selector Modul Monitoring — HADIR

Dokumen ini mendokumentasikan secara rinci seluruh elemen antarmuka (UI), penamaan selector CSS/ID, kontrol form pencarian dan filter tanggal, struktur kolom tabel read-only, layout formulir ekspor dokumen, serta endpoint backend terkait untuk seluruh **10 Halaman Modul Monitoring** pada aplikasi **HADIR** (`https://magang.dikahadir.com/monitoring/*` dan mirror offline `http://localhost:8085/monitoring/*`).

Dokumen ini menjadi acuan utama (*Single Source of Truth / SSOT*) dalam pembuatan **Mockup Static-Web**, **Standar Spesifikasi Kasus Uji**, **Page Object Models (POM)**, dan **Step Definitions Cucumber BDD** khusus untuk peran **Auditor Monitoring (`role_id: monitoring`)**.

---

## 1. Daftar 10 Halaman & Target URL

| No | Modul / Halaman | URL Live Staging | URL Offline Mirror | Kategori Klaster | Karakteristik Akses |
|:---:|:---|:---|:---|:---|:---:|
| 1 | **Monitoring Semua** | `https://magang.dikahadir.com/monitoring/semua` | `http://localhost:8085/monitoring/semua` | Klaster 1: Presensi & Ringkasan | Read-Only Audit |
| 2 | **Monitoring Kehadiran** | `https://magang.dikahadir.com/monitoring/kehadiran` | `http://localhost:8085/monitoring/kehadiran` | Klaster 1: Presensi & Ringkasan | Read-Only Audit |
| 3 | **Monitoring Koreksi** | `https://magang.dikahadir.com/monitoring/koreksi` | `http://localhost:8085/monitoring/koreksi` | Klaster 1: Presensi & Ringkasan | Read-Only Audit |
| 4 | **Monitoring Sakit** | `https://magang.dikahadir.com/monitoring/sakit` | `http://localhost:8085/monitoring/sakit` | Klaster 1: Presensi & Ringkasan | Read-Only Audit |
| 5 | **Monitoring Cuti** | `https://magang.dikahadir.com/monitoring/cuti` | `http://localhost:8085/monitoring/cuti` | Klaster 2: Cuti & Lembur | Read-Only Audit |
| 6 | **Monitoring Lembur** | `https://magang.dikahadir.com/monitoring/lembur` | `http://localhost:8085/monitoring/lembur` | Klaster 2: Cuti & Lembur | Read-Only Audit |
| 7 | **Download Report Kehadiran** | `https://magang.dikahadir.com/monitoring/download-reports/kehadiran` | `http://localhost:8085/monitoring/download-reports/kehadiran` | Klaster 3: Ekspor Laporan | Audit Export |
| 8 | **Download Report Cuti** | `https://magang.dikahadir.com/monitoring/download-reports/cuti` | `http://localhost:8085/monitoring/download-reports/cuti` | Klaster 3: Ekspor Laporan | Audit Export |
| 9 | **Download Report Lembur** | `https://magang.dikahadir.com/monitoring/download-reports/lembur` | `http://localhost:8085/monitoring/download-reports/lembur` | Klaster 3: Ekspor Laporan | Audit Export |
| 10 | **Download Report Shifting** | `https://magang.dikahadir.com/monitoring/download-reports/shifting` | `http://localhost:8085/monitoring/download-reports/shifting` | Klaster 3: Ekspor Laporan | Audit Export |

---

## 2. Standar Komponen Umum Layout Monitoring (Auditor Layout)

Portal Monitoring dibangun dengan arsitektur UI Material-UI (MUI) serupa dengan portal Laporan dan Client, namun dengan **restriksi wewenang murni audit (read-only)**:

### A. Sidebar Navigasi Auditor Monitoring
- **Sidebar Container:** `nav.MuiDrawer-root` / `.sidebar`
- **Brand / Header Sidebar:** `.sidebar-brand` (Menampilkan teks `"HADIR MONITORING"`)
- **Badge Indikator Peran:** `#role-indicator` / `.sidebar-role-badge` (Menampilkan `"Auditor Monitoring"`)
- **Menu Navigasi:**
  - `a[href="/monitoring/semua"]` — Ringkasan Semua Presensi
  - `a[href="/monitoring/kehadiran"]` — Log Presensi Harian
  - `a[href="/monitoring/koreksi"]` — Pengajuan Koreksi Jam
  - `a[href="/monitoring/sakit"]` — Rekap Izin Sakit
  - `a[href="/monitoring/cuti"]` — Pengajuan Cuti
  - `a[href="/monitoring/lembur"]` — Pengajuan Lembur
  - **Menu Ekspor (Download Reports):**
    - `a[href="/monitoring/download-reports/kehadiran"]`
    - `a[href="/monitoring/download-reports/cuti"]`
    - `a[href="/monitoring/download-reports/lembur"]`
    - `a[href="/monitoring/download-reports/shifting"]`

### B. Header Halaman & Breadcrumbs
- **Judul Halaman:** `#page-title` / `h1` / `h2` / `.page-title`
- **Breadcrumbs:** `.breadcrumbs` / `.MuiBreadcrumbs-root` (format: `Monitoring / [Nama Modul]`)

### C. Ringkasan Kartu Statistik Presensi (Khusus `/monitoring/semua`)
- **Total Karyawan:** `#stat-total-staf`
- **Hadir Tepat Waktu:** `#stat-hadir-ontime`
- **Terlambat:** `#stat-terlambat`
- **Cuti / Izin:** `#stat-absen`

### D. Filter Bar & Search Form
- **Form Wrapper:** `#form-search` / `.form-search`
- **Input Pencarian:** `#search` / `input[type="search"]` (Placeholder: *"Cari nama karyawan atau NIK..."*)
- **Input Tanggal Mulai:** `#start-date` / `input[name="startDate"]` (Format: `YYYY-MM-DD`)
- **Input Tanggal Selesai:** `#end-date` / `input[name="endDate"]` (Format: `YYYY-MM-DD`)
- **Tombol Cari:** `#btn-search` / `.btn-search` (Background: Biru Primer `#1ba0e2`)
- **Tombol Reset:** `#btn-reset` / `.btn-reset` (Background: Abu/Kuning `#eab308`)
- **Tombol Ekspor Cepat:** `#btn-export` / `.btn-export` (Ekspor grid aktif ke Excel)

### E. Tabel Data Read-Only (Audit Table Grid)
- **Tabel:** `table.table_base` / `#table-monitoring` / `table[aria-label="sticky table"]`
- **Header Kolom:** `thead th` (Warna latar gelap, uppercase)
- **Baris Data:** `tbody tr[data-id]`
- **Avatar Foto / Selfie:** `.avatar-user`, `.thumbnail-selfie-masuk`, `.thumbnail-selfie-keluar`
- **Status Badges:**
  - Disetujui / Hadir: `.badge-approved`, `.badge-hadir` (Hijau)
  - Menunggu Persetujuan: `.badge-pending` (Kuning)
  - Ditolak / Tidak Hadir: `.badge-rejected`, `.badge-alpa` (Merah)

### F. Bar Paginasi
- **Kontainer:** `.pagination-bar`
- **Jumlah Baris:** `select#rows-per-page` (Opsi: 5, 10, 25, 50)
- **Informasi Data:** `#pagination-info` (Contoh: `Menampilkan 1-10 dari 25 data`)
- **Tombol Navigasi:**
  - `#btn-first-page`
  - `#btn-prev-page`
  - `#btn-next-page`
  - `#btn-last-page`

### G. Formulir Download Reports Generator
- **Kontainer Formulir:** `.card-download-report` / `#download-report-card`
- **Filter Departemen:** `select#select-departemen`
- **Filter Tanggal Periode:** `#start-date` dan `#end-date`
- **Tombol Generator Laporan:** `#btn-download-report` / `#btn-export-excel`
- **Notifikasi Feedback:** `#alert-download-feedback` / `#snackbar` (`#snackbar-message`)

---

## 3. Matriks Hak Akses & Batasan Otorisasi (RBAC Auditor)

| Komponen Antarmuka | Akun Admin | Akun Upliner (V2/V3) | Akun Auditor Monitoring | Catatan Validasi Otomasi |
|:---|:---:|:---:|:---:|:---|
| **Pencarian & Filter Tanggal** | ✅ Tersedia | ✅ Tersedia | ✅ **Tersedia** | Pengujian positif |
| **Paginasi & Sorting Tabel** | ✅ Tersedia | ✅ Tersedia | ✅ **Tersedia** | Pengujian positif |
| **Unduh Berkas Laporan (.xlsx)** | ✅ Tersedia | ✅ Tersedia | ✅ **Tersedia** | Pengujian positif |
| **Tombol Approval / Reject** | ✅ Tersedia | ✅ Tersedia (Validasi) | ❌ **TIDAK ADA** | Verifikasi negasi (Least Privilege) |
| **Tombol Tambah / Edit Karyawan** | ✅ Tersedia | ❌ Tidak Ada | ❌ **TIDAK ADA** | Verifikasi negasi |
| **Akses Modifikasi Master Data** | ✅ Tersedia | ❌ Tidak Ada | ❌ **TIDAK ADA** | API mengembalikan 403 Forbidden |

---

## 4. Rincian Komponen per Halaman Monitoring

### 🔹 KLASTER 1: Presensi, Ringkasan & Koreksi Monitoring

#### 1. Monitoring Semua (`/monitoring/semua`)
- **Struktur Kolom Tabel Header:** `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `JABATAN`, `DEPARTEMEN`, `TANGGAL`, `JAM MASUK`, `JAM KELUAR`, `STATUS KEHADIRAN`
- **Karakteristik:** Terdapat 4 kartu statistik metrik kehadiran di bagian atas tabel. Tidak terdapat kolom `AKSI` (murni read-only).

#### 2. Monitoring Kehadiran (`/monitoring/kehadiran`)
- **Struktur Kolom Tabel Header:** `NO`, `FOTO MASUK`, `FOTO KELUAR`, `NAMA KARYAWAN`, `NIK`, `TANGGAL`, `JAM MASUK`, `LOKASI MASUK`, `JAM KELUAR`, `LOKASI KELUAR`, `TOTAL JAM KERJA`, `STATUS`
- **Komponen Spesifik:** Thumbnail selfie presensi masuk/pulang, badge status presensi.

#### 3. Monitoring Koreksi (`/monitoring/koreksi`)
- **Struktur Kolom Tabel Header:** `NO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL ABSEN`, `TIPE KOREKSI`, `JAM SEBELUMNYA`, `JAM DIAJUKAN`, `ALASAN KOREKSI`, `STATUS KOREKSI`
- **Catatan Audit:** Berbeda dengan `/client/koreksi`, pada halaman `/monitoring/koreksi` **tidak ada tombol setujui/tolak**, melainkan hanya menampilkan status audit persetujuan atasan.

#### 4. Monitoring Sakit (`/monitoring/sakit`)
- **Struktur Kolom Tabel Header:** `NO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL MULAI`, `TANGGAL SELESAI`, `DURASI (HARI)`, `DIAGNOSA / KETERANGAN`, `SURAT DOKTER`, `STATUS`
- **Komponen Spesifik:** Tombol tautan pratinjau surat dokter (`a.btn-preview-surat`, `a.btn-download-surat`) untuk audit bukti medis.

---

### 🔹 KLASTER 2: Pengawasan Cuti & Lembur Karyawan

#### 5. Monitoring Cuti (`/monitoring/cuti`)
- **Struktur Kolom Tabel Header:** `NO`, `NAMA KARYAWAN`, `NIK`, `TIPE CUTI`, `TANGGAL MULAI`, `TANGGAL SELESAI`, `DURASI (HARI)`, `ALASAN CUTI`, `STATUS PERSETUJUAN`
- **Catatan Audit:** Menampilkan seluruh pengajuan cuti karyawan lintas divisi, status verifikasi berjenjang (Validator 1 & 2), tanpa tombol approval.

#### 6. Monitoring Lembur (`/monitoring/lembur`)
- **Struktur Kolom Tabel Header:** `NO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL LEMBUR`, `JAM MULAI`, `JAM SELESAI`, `TOTAL JAM`, `TUGAS / AKTIVITAS LEMBUR`, `STATUS KOMPENSASI`
- **Catatan Audit:** Menampilkan kalkulasi total jam kerja lembur karyawan dan status persetujuan.

---

### 🔹 KLASTER 3: Unduh & Ekspor Laporan Rekapitulasi Audit

Seluruh 4 halaman pada klaster ini menggunakan formulir download report standar:
- Formulir: `#download-report-card`
- Input: `#start-date`, `#end-date`, `select#select-departemen`
- Tombol Unduh: `#btn-download-report`
- Notifikasi: `#snackbar`, `#alert-download-feedback`

| No | Modul | Target URL | Judul Formulir Ekspor | Endpoint Backend Terkait |
|:---:|:---|:---|:---|:---|
| 7 | **Download Report Kehadiran** | `/monitoring/download-reports/kehadiran` | Unduh Rekap Presensi Karyawan | `GET /api/monitoring/download-reports/attendance` |
| 8 | **Download Report Cuti** | `/monitoring/download-reports/cuti` | Unduh Rekap Pengambilan Cuti | `GET /api/monitoring/download-reports/leave` |
| 9 | **Download Report Lembur** | `/monitoring/download-reports/lembur` | Unduh Rekapitulasi Lembur | `GET /api/monitoring/download-reports/overtime` |
| 10 | **Download Report Shifting** | `/monitoring/download-reports/shifting` | Unduh Rekap Roster Shifting | `GET /api/monitoring/download-reports/shifting` |

---

## 5. Ringkasan Selector Kunci untuk Pembuatan Page Object

```java
package com.company.sqa.pages;

import org.openqa.selenium.By;

/**
 * Selector Universal Modul Monitoring HADIR (Auditor Monitoring)
 */
public class MonitoringSelectors {
    // Header & Role
    public static final By PAGE_TITLE = By.xpath("//*[@id='page-title'] | //h1 | //h2 | //h3[contains(@class,'page-title')]");
    public static final By ROLE_INDICATOR = By.id("role-indicator");

    // Navigasi Sidebar
    public static final By SIDEBAR_SEMUA = By.cssSelector("a[href='/monitoring/semua']");
    public static final By SIDEBAR_KEHADIRAN = By.cssSelector("a[href='/monitoring/kehadiran']");
    public static final By SIDEBAR_KOREKSI = By.cssSelector("a[href='/monitoring/koreksi']");
    public static final By SIDEBAR_SAKIT = By.cssSelector("a[href='/monitoring/sakit']");
    public static final By SIDEBAR_CUTI = By.cssSelector("a[href='/monitoring/cuti']");
    public static final By SIDEBAR_LEMBUR = By.cssSelector("a[href='/monitoring/lembur']");
    public static final By SIDEBAR_REP_KEHADIRAN = By.cssSelector("a[href='/monitoring/download-reports/kehadiran']");
    public static final By SIDEBAR_REP_CUTI = By.cssSelector("a[href='/monitoring/download-reports/cuti']");
    public static final By SIDEBAR_REP_LEMBUR = By.cssSelector("a[href='/monitoring/download-reports/lembur']");
    public static final By SIDEBAR_REP_SHIFTING = By.cssSelector("a[href='/monitoring/download-reports/shifting']");

    // Filter & Search Bar
    public static final By INPUT_SEARCH = By.cssSelector("input#search, input[type='search'], input[name='search']");
    public static final By INPUT_START_DATE = By.cssSelector("input#start-date, input[name='startDate']");
    public static final By INPUT_END_DATE = By.cssSelector("input#end-date, input[name='endDate']");
    public static final By BTN_SEARCH = By.cssSelector("button#btn-search, .btn-search");
    public static final By BTN_RESET = By.cssSelector("button#btn-reset, .btn-reset");
    public static final By BTN_EXPORT = By.cssSelector("button#btn-export, .btn-export");

    // Tabel & Paginasi
    public static final By TABLE = By.cssSelector("table.table_base, #table-monitoring");
    public static final By TABLE_HEADERS = By.cssSelector("table.table_base thead th, thead th");
    public static final By TABLE_ROWS = By.cssSelector("#table-body tr, table.table_base tbody tr");
    public static final By PAGINATION_INFO = By.id("pagination-info");
    public static final By ROWS_PER_PAGE = By.id("rows-per-page");

    // Formulir Download Reports
    public static final By CARD_DOWNLOAD_REPORT = By.id("download-report-card");
    public static final By SELECT_DEPARTEMEN = By.id("select-departemen");
    public static final By BTN_DOWNLOAD_REPORT = By.cssSelector("button#btn-download-report, button#btn-export-excel");
    public static final By ALERT_DOWNLOAD_FEEDBACK = By.id("alert-download-feedback");

    // Toast / Snackbar
    public static final By SNACKBAR = By.id("snackbar");
    public static final By SNACKBAR_MESSAGE = By.id("snackbar-message");
}
```

