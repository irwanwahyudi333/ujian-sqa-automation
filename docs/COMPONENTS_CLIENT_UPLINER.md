# Katalog Komponen & Selector Modul Client & Upliner — HADIR

Dokumen ini mendokumentasikan secara rinci seluruh elemen antarmuka (UI), penamaan selector CSS/ID, kontrol form, filter tanggal, tab status, struktur kolom tabel, modal dialog approval, serta endpoint backend terkait untuk seluruh **17 Halaman Modul Client & Upliner** pada aplikasi **HADIR** (`https://magang.dikahadir.com/client/*` dan mirror offline `http://localhost:8085/client/*`).

Dokumen ini menjadi acuan utama (*Single Source of Truth / SSOT*) dalam pembuatan **Page Object Models (POM)**, **Step Definitions Cucumber**, dan **Skenario Pengujian Gherkin** komparatif untuk dua peran:
- **Upliner V2 (`role_id: client`)** — Bertindak sebagai **Data Validator 1**
- **Upliner V3 (`role_id: client3`)** — Bertindak sebagai **Data Validator 2**

---

## 1. Daftar 17 Halaman & Target URL

| No | Modul / Halaman | URL Live Staging | URL Offline Mirror | Kategori Klaster | Status Komponen |
|:---:|:---|:---|:---|:---|:---:|
| 1 | **Laporan Semua Client** | `https://magang.dikahadir.com/client/all` | `http://localhost:8085/client/all` | Klaster 1: Presensi & Koreksi | 🟢 Lengkap |
| 2 | **Laporan Kehadiran** | `https://magang.dikahadir.com/client/kehadiran` | `http://localhost:8085/client/kehadiran` | Klaster 1: Presensi & Koreksi | 🟢 Lengkap |
| 3 | **Laporan Koreksi** | `https://magang.dikahadir.com/client/koreksi` | `http://localhost:8085/client/koreksi` | Klaster 1: Presensi & Koreksi | 🟢 Lengkap |
| 4 | **Laporan Sakit** | `https://magang.dikahadir.com/client/sick` | `http://localhost:8085/client/sick` | Klaster 1: Presensi & Koreksi | 🟢 Lengkap |
| 5 | **Cuti Semua** | `https://magang.dikahadir.com/client/cuti/semua` | `http://localhost:8085/client/cuti/semua` | Klaster 2: Pengelolaan Cuti | 🟢 Lengkap |
| 6 | **Cuti Pending** | `https://magang.dikahadir.com/client/cuti/pending` | `http://localhost:8085/client/cuti/pending` | Klaster 2: Pengelolaan Cuti | 🟢 Lengkap |
| 7 | **Cuti Validator** | `https://magang.dikahadir.com/client/cuti/validator` | `http://localhost:8085/client/cuti/validator` | Klaster 2: Pengelolaan Cuti | 🟢 Lengkap |
| 8 | **Lembur Semua** | `https://magang.dikahadir.com/client/lembur/semua` | `http://localhost:8085/client/lembur/semua` | Klaster 3: Pengelolaan Lembur | 🟢 Lengkap |
| 9 | **Lembur Pending** | `https://magang.dikahadir.com/client/lembur/pending` | `http://localhost:8085/client/lembur/pending` | Klaster 3: Pengelolaan Lembur | 🟢 Lengkap |
| 10 | **Lembur Validator** | `https://magang.dikahadir.com/client/lembur/validator` | `http://localhost:8085/client/lembur/validator` | Klaster 3: Pengelolaan Lembur | 🟢 Lengkap |
| 11 | **Shifting Semua** | `https://magang.dikahadir.com/client/shifting/semua` | `http://localhost:8085/client/shifting/semua` | Klaster 4: Pola Kerja Shift | 🟢 Lengkap |
| 12 | **Shifting Pending** | `https://magang.dikahadir.com/client/shifting/pending` | `http://localhost:8085/client/shifting/pending` | Klaster 4: Pola Kerja Shift | 🟢 Lengkap |
| 13 | **Download Report Kehadiran** | `https://magang.dikahadir.com/client/download-reports/kehadiran` | `http://localhost:8085/client/download-reports/kehadiran` | Klaster 5: Ekspor Laporan | 🟢 Lengkap |
| 14 | **Download Report Cuti** | `https://magang.dikahadir.com/client/download-reports/cuti` | `http://localhost:8085/client/download-reports/cuti` | Klaster 5: Ekspor Laporan | 🟢 Lengkap |
| 15 | **Download Report Sisa Cuti** | `https://magang.dikahadir.com/client/download-reports/sisa-cuti` | `http://localhost:8085/client/download-reports/sisa-cuti` | Klaster 5: Ekspor Laporan | 🟢 Lengkap |
| 16 | **Download Report Lembur** | `https://magang.dikahadir.com/client/download-reports/lembur` | `http://localhost:8085/client/download-reports/lembur` | Klaster 5: Ekspor Laporan | 🟢 Lengkap |
| 17 | **Download Report Shifting** | `https://magang.dikahadir.com/client/download-reports/shifting` | `http://localhost:8085/client/download-reports/shifting` | Klaster 5: Ekspor Laporan | 🟢 Lengkap |

---

## 2. Standar Komponen Umum (Universal Common Layout)

Portal Client HADIR dibangun menggunakan React dan Material-UI (MUI v5) dengan pola antarmuka konsisten:

### A. Sidebar Navigasi Khusus Client
- **Kontainer:** `nav.MuiDrawer-root` / `.sidebar-container`
- **Menu Staff:** `a[href="/client/staff/lists"]` (Daftar karyawan di bawah mitra)
- **Menu Laporan (Dropdown):** `div:contains("Laporan")` &rarr; Sub-menu:
  - `a[href="/client/all"]` (Semua)
  - `a[href="/client/kehadiran"]` (Kehadiran)
  - `a[href="/client/koreksi"]` (Koreksi)
  - `a[href="/client/sick"]` (Sakit)
- **Menu Cuti (Dropdown):** `div:contains("Cuti")` &rarr; Sub-menu:
  - `a[href="/client/cuti/semua"]` (Semua)
  - `a[href="/client/cuti/pending"]` (Pending)
  - `a[href="/client/cuti/validator"]`:
    - Label Upliner V2: **"Data Validator 1"**
    - Label Upliner V3: **"Data Validator 2"**
- **Menu Lembur (Dropdown):** `div:contains("Lembur")` &rarr; Sub-menu:
  - `a[href="/client/lembur/semua"]` (Semua)
  - `a[href="/client/lembur/pending"]` (Pending)
  - `a[href="/client/lembur/validator"]`:
    - Label Upliner V2: **"Data Validator 1"**
    - Label Upliner V3: **"Data Validator 2"**
- **Menu Shifting (Dropdown):** `div:contains("Shifting")` &rarr; Sub-menu:
  - `a[href="/client/shifting/semua"]` (Semua)
  - `a[href="/client/shifting/pending"]` (Pending)
- **Menu Download Reports (Dropdown):** `div:contains("Download Reports")` &rarr; Sub-menu:
  - `a[href="/client/download-reports/kehadiran"]`
  - `a[href="/client/download-reports/cuti"]`
  - `a[href="/client/download-reports/sisa-cuti"]`
  - `a[href="/client/download-reports/lembur"]`
  - `a[href="/client/download-reports/shifting"]`

### B. Filter Card & Search Bar
- **Kontainer:** `.filter-card` / `.MuiCard-root`
- **Form Pencarian:** `#form-search` / `.form-search`
- **Input Search:** `#search` / `input[placeholder*="cari"]` (atribut `type="search"`)
- **Filter Tanggal (Date Range):**
  - Tanggal Mulai: `#start-date` / `input[name="startDate"]`
  - Tanggal Selesai: `#end-date` / `input[name="endDate"]`
- **Tombol Cari:** `#btn-search` / `.btn-search` (latar biru `#1ba0e2`)
- **Tombol Reset:** `#btn-reset` / `.btn-reset` (warna aksen `#eab308`)

### C. Tabel Data Tabel Utama (Data Table Card)
- **Tabel:** `table.table_base.MuiTable-root` / `#table-client`
- **Header:** `thead > tr > th` (latar abu tua gelap `#3D3D3D`, teks putih tebal uppercase)
- **Baris Data:** `tbody > tr[data-id]`
- **Avatar/Foto:** `.avatar-user`, `img[alt="user-photo"]`
- **Badge Status:**
  - Approved / Disetujui: `.badge-success` (latar hijau muda, teks hijau tua)
  - Pending / Menunggu: `.badge-warning` (latar kuning muda, teks oranye/kuning)
  - Rejected / Ditolak: `.badge-danger` (latar merah muda, teks merah tua)

### D. Bar Paginasi (Pagination Bar)
- **Kontainer:** `.pagination-bar` / `.MuiTablePagination-root`
- **Rows Per Page:** `select#rows-per-page` (opsi: 5, 10, 25)
- **Indikator Data:** `#pagination-info` (format: `Menampilkan 1-10 dari 25 data`)
- **Navigasi:**
  - `#btn-first-page` (&laquo;)
  - `#btn-prev-page` (&lsaquo;)
  - `#btn-next-page` (&rsaquo;)
  - `#btn-last-page` (&raquo;)

### E. Modal Dialog Approval & Reject (Validator Action)
- **Modal Wrapper:** `#modal-action-validator.modal-overlay`
- **Header:** `#modal-title` (*"Persetujuan Permohonan"*)
- **Body:** `#modal-body` (Rincian nama staf, tanggal, jenis cuti/lembur, dan textarea catatan `#input-catatan`)
- **Tombol Aksi:**
  - Setujui: `#btn-approve-validator` (`.btn-success`)
  - Tolak: `#btn-reject-validator` (`.btn-danger`)
  - Batal: `#btn-cancel-validator` (`.btn-secondary`)

---

## 3. Rincian Komponen Spesifik Per Halaman

---

### 🔹 KLASTER 1: Presensi, Ringkasan & Koreksi Client

#### 1. Laporan Semua Client (`/client/all`)
- **Judul Halaman:** `HADIR - Laporan Semua Client`
- **Tujuan:** Dashboard utama ringkasan presensi harian seluruh staf bawahan klien.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Client / Semua`
  - Widget Statistik Ringkasan:
    - Total Karyawan Mitra: `#stat-total-staf`
    - Hadir Tepat Waktu: `#stat-hadir-ontime`
    - Terlambat: `#stat-terlambat`
    - Tidak Hadir / Izin / Cuti: `#stat-absen`
  - Filter Bar: Input `#search` (Nama / NIK), Date Range (`#start-date`, `#end-date`)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA`, `NIK`, `JABATAN`, `TANGGAL`, `JAM MASUK`, `JAM KELUAR`, `STATUS`, `AKSI`
  - Tombol Detail: `.btn-detail-row` (Membuka modal `#modal-detail-presensi`)
- **Endpoint API Terkait:**
  - `GET /api/client/reports/all?startDate={start}&endDate={end}&search={q}&page={p}&limit={l}`
  - `GET /api/client/statistics`

---

#### 2. Laporan Kehadiran (`/client/kehadiran`)
- **Judul Halaman:** `HADIR - Laporan Kehadiran`
- **Tujuan:** Log presensi harian karyawan lengkap dengan koordinat geolokasi dan swafoto.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `FOTO MASUK`, `FOTO KELUAR`, `NAMA KARYAWAN`, `NIK`, `TANGGAL`, `JAM MASUK`, `LOKASI MASUK`, `JAM KELUAR`, `LOKASI KELUAR`, `TOTAL JAM KERJA`, `STATUS`
  - Thumbnail Foto Selfie Presensi: `.thumbnail-selfie-masuk`, `.thumbnail-selfie-keluar` (membuka modal pembesar foto `#modal-zoom-photo`)
  - Tautan Peta Geofence: `.link-maps-lokasi` (membuka modal koordinat peta Google Maps)
- **Endpoint API Terkait:**
  - `GET /api/client/reports/attendance?startDate={start}&endDate={end}`
  - `GET /api/client/reports/attendance/export`

---

#### 3. Laporan Koreksi (`/client/koreksi`)
- **Judul Halaman:** `HADIR - Laporan Koreksi`
- **Tujuan:** Meninjau dan memverifikasi permohonan koreksi jam masuk/keluar yang diajukan staf.
- **Elemen Spesifik:**
  - Filter Status: Dropdown `#filter-status` (Semua, Pending, Approved, Rejected)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL ABSEN`, `TIPE KOREKSI` (Jam Masuk / Jam Keluar), `JAM SEMULA`, `JAM DIAJUKAN`, `ALASAN KOREKSI`, `STATUS`, `AKSI`
  - Tombol Approval Cepat: `.btn-approve-koreksi`, `.btn-reject-koreksi`
  - Modal Detail Koreksi: `#modal-detail-koreksi`
- **Endpoint API Terkait:**
  - `GET /api/client/corrections`
  - `POST /api/client/corrections/{id}/approve`
  - `POST /api/client/corrections/{id}/reject`

---

#### 4. Laporan Sakit (`/client/sick`)
- **Judul Halaman:** `HADIR - Laporan Sakit`
- **Tujuan:** Memantau pengajuan izin sakit staf mitra dan memeriksa kelengkapan surat dokter.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL MULAI`, `TANGGAL SELESAI`, `DURASI (HARI)`, `ALASAN SAKIT`, `SURAT DOKTER`, `STATUS`, `AKSI`
  - Komponen Berkas Surat Dokter:
    - Thumbnail: `.thumbnail-surat-dokter`
    - Tombol Download Surat: `a.btn-download-surat` (verifikasi penanganan HTTP 200 vs 404)
  - Modal Preview Bukti Medis: `#modal-preview-surat`
- **Endpoint API Terkait:**
  - `GET /api/client/sickness`
  - `GET /api/client/sickness/{id}/document`

---

### 🔹 KLASTER 2: Pengelolaan & Validasi Cuti Client

#### 5. Cuti Semua (`/client/cuti/semua`)
- **Judul Halaman:** `HADIR - Daftar Seluruh Cuti Client`
- **Tujuan:** Rekapitulasi historis permohonan cuti seluruh karyawan mitra di bawah pengawasan.
- **Elemen Spesifik:**
  - Dropdown Tipe Cuti: `#filter-cuti-type` (Tahunan, Melahirkan, Khitanan, Pernikahan, Duka, dll.)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TIPE CUTI`, `TANGGAL MULAI`, `TANGGAL SELESAI`, `JUMLAH HARI`, `KETERANGAN`, `APPROVER / VALIDATOR`, `STATUS`
- **Endpoint API Terkait:**
  - `GET /api/client/leave/all`

---

#### 6. Cuti Pending (`/client/cuti/pending`)
- **Judul Halaman:** `HADIR - Permohonan Cuti Pending`
- **Tujuan:** Antrean permohonan cuti baru staf yang masih memerlukan validasi atasan/mitra.
- **Elemen Spesifik:**
  - Counter Antrean Pending: `#badge-count-pending-cuti`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA`, `NIK`, `TIPE CUTI`, `RENTANG TANGGAL`, `TOTAL HARI`, `ALASAN`, `TANGGAL PENGAJUAN`, `AKSI`
  - Tombol Review Cuti: `.btn-review-cuti` (mengarahkan atau memicu modal verifikasi)
- **Endpoint API Terkait:**
  - `GET /api/client/leave/pending`

---

#### 7. Cuti Validator (`/client/cuti/validator`)
- **Judul Halaman:** `HADIR - Validasi Cuti Berjenjang`
- **Tujuan:** Antarmuka aksi validasi dan tanda tangan elektronik persetujuan cuti berjenjang.
- **Perilaku Berdasarkan Role:**
  - **Upliner V2 (`role: client`):** Bertindak sebagai **Data Validator 1**; data yang disetujui berpindah ke status *Pending Validator 2*.
  - **Upliner V3 (`role: client3`):** Bertindak sebagai **Data Validator 2**; memvalidasi data yang telah disetujui oleh Validator 1 untuk approval final.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `NAMA KARYAWAN`, `NIK`, `TIPE CUTI`, `TANGGAL CUTI`, `VALIDATOR 1 STATUS`, `VALIDATOR 2 STATUS`, `STATUS AKHIR`, `AKSI VALIDASI`
  - Tombol Validasi Baris:
    - Setujui: `button.btn-validator-approve`
    - Tolak / Kembalikan: `button.btn-validator-reject`
  - Modal Konfirmasi Validasi: `#modal-validator-confirm` (Input textarea catatan `#validator-notes`, Tombol `#btn-submit-validation`)
- **Endpoint API Terkait:**
  - `GET /api/client/leave/validation-list`
  - `POST /api/client/leave/validate` (Payload: `{ leave_id, status: "APPROVED"|"REJECTED", validator_level: 1|2, notes }`)

---

### 🔹 KLASTER 3: Pengelolaan & Validasi Lembur Client

#### 8. Lembur Semua (`/client/lembur/semua`)
- **Judul Halaman:** `HADIR - Seluruh Permohonan Lembur`
- **Tujuan:** Rekapitulasi pengajuan jam kerja lembur (*overtime*) seluruh staf mitra.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL LEMBUR`, `JAM MULAI`, `JAM SELESAI`, `DURASI (JAM)`, `URAIAN TUGAS LEMBUR`, `STATUS`
  - Filter Range Jam & Tanggal
- **Endpoint API Terkait:**
  - `GET /api/client/overtime/all`

---

#### 9. Lembur Pending (`/client/lembur/pending`)
- **Judul Halaman:** `HADIR - Klaim Lembur Pending`
- **Tujuan:** Antrean klaim lembur staf yang belum divalidasi.
- **Elemen Spesifik:**
  - Counter Pending: `#badge-count-pending-lembur`
  - Header Kolom Tabel: `NO`, `NAMA`, `NIK`, `TANGGAL`, `DURASI`, `DESKRIPSI TUGAS`, `TANGGAL SUBMIT`, `AKSI`
  - Tombol Aksi Cepat: `.btn-quick-approve-ot`, `.btn-quick-reject-ot`
- **Endpoint API Terkait:**
  - `GET /api/client/overtime/pending`

---

#### 10. Lembur Validator (`/client/lembur/validator`)
- **Judul Halaman:** `HADIR - Validasi Lembur Berjenjang`
- **Tujuan:** Modul verifikasi kompensasi lembur bertingkat (Validator 1 & 2).
- **Perilaku Berdasarkan Role:**
  - **Upliner V2 (`role: client`):** Mengeksekusi approval **Validator 1**.
  - **Upliner V3 (`role: client3`):** Mengeksekusi approval **Validator 2**.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL LEMBUR`, `DURASI`, `TUGAS LEMBUR`, `VALIDATOR 1`, `VALIDATOR 2`, `STATUS`, `AKSI VALIDASI`
  - Modal Validasi Lembur: `#modal-validate-overtime` (`#ot-approval-notes`, `#btn-confirm-ot-approval`, `#btn-confirm-ot-reject`)
- **Endpoint API Terkait:**
  - `GET /api/client/overtime/validation-list`
  - `POST /api/client/overtime/validate` (Payload: `{ overtime_id, action: "APPROVE"|"REJECT", validator_step: 1|2, remarks }`)

---

### 🔹 KLASTER 4: Pengelolaan Jadwal Kerja & Shifting

#### 11. Shifting Semua (`/client/shifting/semua`)
- **Judul Halaman:** `HADIR - Seluruh Jadwal Shifting Staf`
- **Tujuan:** Monitoring alokasi shift kerja regu dan penyesuaian jam kerja staf klien.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL BERLAKU`, `POLA SHIFT LAMA`, `POLA SHIFT BARU`, `STATUS PENGAJUAN`
  - Filter Pola Shift: Dropdown `#filter-shift-type` (Pagi, Siang, Malam, Normal)
- **Endpoint API Terkait:**
  - `GET /api/client/shifting/all`

---

#### 12. Shifting Pending (`/client/shifting/pending`)
- **Judul Halaman:** `HADIR - Pengajuan Shifting Pending`
- **Tujuan:** Memproses permohonan pertukaran atau perubahan jadwal shift antar staf regu.
- **Elemen Spesifik:**
  - Header Kolom Tabel: `NO`, `NAMA PEMOHON`, `NIK`, `SHIFT AWAL`, `SHIFT PENGAJUAN`, `ALASAN TUKAR SHIFT`, `STATUS`, `AKSI`
  - Tombol Persetujuan Shift: `.btn-approve-shift`, `.btn-reject-shift`
  - Modal Konfirmasi Shift: `#modal-confirm-shift`
- **Endpoint API Terkait:**
  - `GET /api/client/shifting/pending`
  - `POST /api/client/shifting/{id}/approve`
  - `POST /api/client/shifting/{id}/reject`

---

### 🔹 KLASTER 5: Download & Rekapitulasi Laporan Client

Seluruh modul Klaster 5 menggunakan formulir generator laporan terstandarisasi untuk ekspor dokumen (.xlsx):

#### Layout Formulir Generator Standar:
- **Kontainer Card:** `.card-download-report` / `#download-report-card`
- **Input Tanggal Mulai:** `#start-date` / `input[name="startDate"]` (Format: `YYYY-MM-DD` atau datepicker)
- **Input Tanggal Selesai:** `#end-date` / `input[name="endDate"]` (Format: `YYYY-MM-DD`)
- **Pilihan Departemen / Unit:** `select#select-departemen` / Autocomplete `#departemen-filter`
- **Pilihan Format File:** Radio button `#radio-format-xlsx` (Excel), `#radio-format-csv` (CSV)
- **Tombol Ekspor Dokumen:** `#btn-download-report` / `#btn-export-excel` (warna primer biru dengan ikon download)
- **Indikator Loading Ekspor:** `.spinner-download`, `#loading-progress`
- **Feedback Unduhan:** `#alert-download-feedback` (Toast info unduhan berhasil)

#### Rincian Halaman & Endpoint Ekspor:
| No | Modul | Target URL | Judul Modul Ekspor | Endpoint Backend Generator |
|:---:|:---|:---|:---|:---|
| 13 | **Download Report Kehadiran** | `/client/download-reports/kehadiran` | Unduh Laporan Kehadiran Karyawan Mitra | `GET /api/client/download-reports/attendance?startDate={s}&endDate={e}` |
| 14 | **Download Report Cuti** | `/client/download-reports/cuti` | Unduh Rekapitulasi Pengambilan Cuti | `GET /api/client/download-reports/leave?startDate={s}&endDate={e}` |
| 15 | **Download Report Sisa Cuti** | `/client/download-reports/sisa-cuti` | Unduh Saldo Sisa Kuota Cuti Tahunan | `GET /api/client/download-reports/leave-balance` |
| 16 | **Download Report Lembur** | `/client/download-reports/lembur` | Unduh Rekapitulasi Jam Kerja Lembur | `GET /api/client/download-reports/overtime?startDate={s}&endDate={e}` |
| 17 | **Download Report Shifting** | `/client/download-reports/shifting` | Unduh Roster & Jadwal Shifting Tim | `GET /api/client/download-reports/shifting?startDate={s}&endDate={e}` |

---

## 4. Matriks Perbedaan Komponen UI: Upliner V2 vs Upliner V3

| Komponen Antarmuka | Upliner V2 (`role: client`) | Upliner V3 (`role: client3`) | Lokasi Elemen |
|:---|:---|:---|:---|
| **Label Menu Sidebar Cuti** | Menampilkan menu item: **`Data Validator 1`** | Menampilkan menu item: **`Data Validator 2`** | Sidebar &rarr; Menu Cuti &rarr; Item ke-3 |
| **Label Menu Sidebar Lembur** | Menampilkan menu item: **`Data Validator 1`** | Menampilkan menu item: **`Data Validator 2`** | Sidebar &rarr; Menu Lembur &rarr; Item ke-3 |
| **Aksi Tombol Validasi Cuti** | Memvalidasi tahap 1 (`validator_level: 1`), status menjadi *Approved V1* | Memvalidasi tahap 2 (`validator_level: 2`), status menjadi *Approved Final* | `/client/cuti/validator` &rarr; Kolom Aksi |
| **Aksi Tombol Validasi Lembur** | Memvalidasi tahap 1 (`validator_step: 1`), status menjadi *Approved V1* | Memvalidasi tahap 2 (`validator_step: 2`), status menjadi *Approved Final* | `/client/lembur/validator` &rarr; Kolom Aksi |
| **Cakupan Data Staf di Tabel** | Staf yang terhubung ke `upliner2_id` | Staf yang terhubung ke `upliner3_id` | Seluruh 17 tabel data `/client/*` |

---

## 5. Ringkasan Selector Kunci untuk Pembuatan Page Object

```java
// ==========================================
// Selector Universal Portal Client HADIR
// ==========================================
public class ClientPortalSelectors {
    // Navigasi Sidebar
    public static final By SIDEBAR_STAFF = By.cssSelector("a[href='/client/staff/lists']");
    public static final By SIDEBAR_LAPORAN_ALL = By.cssSelector("a[href='/client/all']");
    public static final By SIDEBAR_KEHADIRAN = By.cssSelector("a[href='/client/kehadiran']");
    public static final By SIDEBAR_KOREKSI = By.cssSelector("a[href='/client/koreksi']");
    public static final By SIDEBAR_SAKIT = By.cssSelector("a[href='/client/sick']");
    public static final By SIDEBAR_CUTI_SEMUA = By.cssSelector("a[href='/client/cuti/semua']");
    public static final By SIDEBAR_CUTI_PENDING = By.cssSelector("a[href='/client/cuti/pending']");
    public static final By SIDEBAR_CUTI_VALIDATOR = By.cssSelector("a[href='/client/cuti/validator']");
    public static final By SIDEBAR_LEMBUR_SEMUA = By.cssSelector("a[href='/client/lembur/semua']");
    public static final By SIDEBAR_LEMBUR_PENDING = By.cssSelector("a[href='/client/lembur/pending']");
    public static final By SIDEBAR_LEMBUR_VALIDATOR = By.cssSelector("a[href='/client/lembur/validator']");
    public static final By SIDEBAR_SHIFTING_SEMUA = By.cssSelector("a[href='/client/shifting/semua']");
    public static final By SIDEBAR_SHIFTING_PENDING = By.cssSelector("a[href='/client/shifting/pending']");
    
    // Filter & Search
    public static final By INPUT_SEARCH = By.id("search");
    public static final By INPUT_START_DATE = By.id("start-date");
    public static final By INPUT_END_DATE = By.id("end-date");
    public static final By BTN_SEARCH = By.id("btn-search");
    public static final By BTN_RESET = By.id("btn-reset");
    
    // Tabel & Paginasi
    public static final By DATA_TABLE = By.cssSelector("table.table_base, #table-client");
    public static final By TABLE_ROWS = By.cssSelector("tbody > tr");
    public static final By SELECT_ROWS_PER_PAGE = By.id("rows-per-page");
    public static final By PAGINATION_INFO = By.id("pagination-info");
    
    // Validator Approval Modal
    public static final By MODAL_VALIDATOR = By.id("modal-action-validator");
    public static final By TEXTAREA_VALIDATOR_NOTES = By.id("validator-notes");
    public static final By BTN_APPROVE_VALIDATOR = By.id("btn-approve-validator");
    public static final By BTN_REJECT_VALIDATOR = By.id("btn-reject-validator");
    public static final By BTN_CANCEL_VALIDATOR = By.id("btn-cancel-validator");
    
    // Download Reports Form
    public static final By SELECT_DEPARTEMEN = By.id("select-departemen");
    public static final By BTN_DOWNLOAD_REPORT = By.id("btn-download-report");
}
```

