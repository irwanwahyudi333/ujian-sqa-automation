# Katalog Komponen & Selector Modul Leader — HADIR

Dokumen ini mendokumentasikan secara rinci seluruh elemen antarmuka (UI), penamaan selector CSS/ID, kontrol form pencarian dan filter tanggal, struktur kolom tabel, modal dialog approval/rejection, layout formulir generator ekspor laporan, serta endpoint backend terkait untuk seluruh **11 Halaman Modul Leader** pada aplikasi **HADIR** (`https://magang.dikahadir.com/leader/*` dan mirror offline `http://localhost:8085/leader/*`).

Dokumen ini menjadi acuan utama (*Single Source of Truth / SSOT*) dalam pembuatan **Mockup Static-Web**, **Standar Spesifikasi Kasus Uji (.ai/standards/)**, **Page Object Models (POM)**, dan **Step Definitions Cucumber BDD** khusus untuk peran **Team Leader (`isLeader: true` / Supervisor Regu)**.

---

## 1. Daftar 11 Halaman & Target URL

| No | Modul / Halaman | URL Live Staging | URL Offline Mirror | Kategori Klaster | Karakteristik Akses |
|:---:|:---|:---|:---|:---|:---:|
| 1 | **Leader Semua (Rekap Tim)** | `https://magang.dikahadir.com/leader/all` | `http://localhost:8085/leader/all` | Klaster 1: Presensi & Koreksi | Pengawasan Presensi Regu |
| 2 | **Leader Kehadiran (Log Harian)** | `https://magang.dikahadir.com/leader/kehadiran` | `http://localhost:8085/leader/kehadiran` | Klaster 1: Presensi & Koreksi | Audit Swafoto & Geolokasi |
| 3 | **Leader Koreksi (Koreksi Jam)** | `https://magang.dikahadir.com/leader/koreksi` | `http://localhost:8085/leader/koreksi` | Klaster 1: Presensi & Koreksi | Verifikasi & Approval Jam |
| 4 | **Leader Izin Off (Tukar Libur)** | `https://magang.dikahadir.com/leader/izin-off` | `http://localhost:8085/leader/izin-off` | Klaster 5: Perizinan & Ekspor | Pengajuan & Approval Off |
| 5 | **Leader Generate Report** | `https://magang.dikahadir.com/leader/generate` | `http://localhost:8085/leader/generate` | Klaster 5: Perizinan & Ekspor | Generator Rekapitulasi Tim |
| 6 | **Leader Cuti Semua** | `https://magang.dikahadir.com/leader/cuti/semua` | `http://localhost:8085/leader/cuti/semua` | Klaster 2: Pengelolaan Cuti | Histori Pengajuan Cuti Tim |
| 7 | **Leader Cuti Pending** | `https://magang.dikahadir.com/leader/cuti/pending` | `http://localhost:8085/leader/cuti/pending` | Klaster 2: Pengelolaan Cuti | Antrean Approval Cuti Staf |
| 8 | **Leader Lembur Semua** | `https://magang.dikahadir.com/leader/lembur/semua` | `http://localhost:8085/leader/lembur/semua` | Klaster 3: Pengelolaan Lembur | Rekapitulasi Lembur Regu |
| 9 | **Leader Lembur Pending** | `https://magang.dikahadir.com/leader/lembur/pending` | `http://localhost:8085/leader/lembur/pending` | Klaster 3: Pengelolaan Lembur | Antrean Approval Lembur |
| 10 | **Leader Shifting Semua** | `https://magang.dikahadir.com/leader/shifting/semua` | `http://localhost:8085/leader/shifting/semua` | Klaster 4: Jadwal & Shifting | Roster Pola Shift Kerja Tim |
| 11 | **Leader Shifting Pending** | `https://magang.dikahadir.com/leader/shifting/pending` | `http://localhost:8085/leader/shifting/pending` | Klaster 4: Jadwal & Shifting | Antrean Approval Tukar Shift |

---

## 2. Standar Komponen Umum Layout Leader (Team Leader Layout)

Portal Leader HADIR dibangun menggunakan React dan Material-UI (MUI v5) dengan nuansa tema biru khas HADIR (`#1ba0e2`) dan struktur tata letak konsisten:

### A. Sidebar Navigasi Team Leader
- **Kontainer Sidebar:** `nav.MuiDrawer-root` / `.sidebar` (background `#1ba0e2`, color `#ffffff`)
- **Brand / Header Sidebar:** `.sidebar-brand` (Menampilkan teks `"HADIR LEADER"`)
- **Badge Indikator Peran:** `#role-indicator` / `.sidebar-role-badge` (Menampilkan `"Team Leader"`)
- **Menu Navigasi:**
  - `a[href="/leader/staff/lists"]` — Daftar Anggota Tim Regu (Staff Lists)
  - **Menu Laporan (Dropdown):** `div:contains("Laporan")` &rarr; Sub-menu:
    - `a[href="/leader/all"]` — Rekap Semua Presensi Tim
    - `a[href="/leader/kehadiran"]` — Log Kehadiran & Foto Presensi
    - `a[href="/leader/koreksi"]` — Koreksi Jam Presensi Tim
    - `a[href="/leader/izin-off"]` — Izin Tukar Hari Off
    - `a[href="/leader/generate"]` — Download / Generate Report
  - **Menu Cuti (Dropdown):** `div:contains("Cuti")` &rarr; Sub-menu:
    - `a[href="/leader/cuti/semua"]` — Semua Riwayat Cuti Tim
    - `a[href="/leader/cuti/pending"]` — Antrean Cuti Menunggu Approval
  - **Menu Lembur (Dropdown):** `div:contains("Lembur")` &rarr; Sub-menu:
    - `a[href="/leader/lembur/semua"]` — Semua Rekapitulasi Lembur Tim
    - `a[href="/leader/lembur/pending"]` — Antrean Lembur Menunggu Approval
  - **Menu Shifting (Dropdown):** `div:contains("Shifting")` &rarr; Sub-menu:
    - `a[href="/leader/shifting/semua"]` — Rekap Pola Shift Tim
    - `a[href="/leader/shifting/pending"]` — Antrean Tukar Shift Menunggu Approval

### B. Header Halaman & Breadcrumbs
- **Judul Halaman:** `#page-title` / `h1.page-title` (Contoh: `"HADIR - Leader Semua"`, `"HADIR - Leader Izin Off"`)
- **Breadcrumbs Navigasi:** `.breadcrumbs` / `.MuiBreadcrumbs-root` (format: `Dashboard / Leader / [Sub-Menu]`)
- **Tombol Aksi Utama Header:** `.page-header .btn-group` (Contoh: `#btn-tambah` / Ajukan Izin Off)

### C. Kartu Statistik Presensi (Khusus `/leader/all`)
- **Total Anggota Regu:** `#stat-total-staf`
- **Hadir Tepat Waktu:** `#stat-hadir-ontime`
- **Terlambat:** `#stat-terlambat`
- **Tidak Hadir / Izin / Off:** `#stat-absen`

### D. Filter Bar & Search Form
- **Form Wrapper:** `#form-search` / `.filter-card` / `.filter-bar`
- **Input Pencarian:** `#search` / `#search-input` / `input[placeholder*="Cari"]` (Pencarian NIK atau Nama anggota tim)
- **Dropdown Filter Status:** `#filter-status` / `select.filter-select` (Opsi: `Semua Status`, `PENDING`, `APPROVED`, `REJECTED`)
- **Filter Rentang Tanggal (Date Range):**
  - Tanggal Mulai: `#start-date` / `input[name="startDate"]`
  - Tanggal Selesai: `#end-date` / `input[name="endDate"]`
- **Tombol Cari:** `#btn-search` / `.btn-primary` (Latar biru `#1ba0e2`)
- **Tombol Reset:** `#btn-reset` / `.btn-secondary` (Latar abu-abu / border `#dee3e9`)

### E. Tabel Data Utama (Data Table Card)
- **Kontainer Tabel:** `.table-container` / `.table-card`
- **Elemen Tabel:** `table.data-table` / `table.table_base` / `#table-leader`
- **Header Kolom:** `thead th` (Latar `#f8fafc`, teks kapital tebal `#4a5568`, border bawah)
- **Baris Data:** `tbody tr[data-id]`
- **Avatar & Nama:** `.avatar-wrapper`, `.user-fullname`
- **Badge Status:**
  - Pending / Menunggu: `.badge.badge-pending` (kuning)
  - Approved / Disetujui: `.badge.badge-approved` (hijau)
  - Rejected / Ditolak: `.badge.badge-rejected` (merah)
- **Tombol Aksi Cepat Baris:**
  - Setujui: `.action-btn.approve` / `.btn-approve-action` (ikon centang hijau)
  - Tolak: `.action-btn.reject` / `.btn-reject-action` (ikon silang merah)
  - Detail: `.action-btn.detail` / `.btn-detail-action` (ikon mata/info biru)

### F. Bar Paginasi (Pagination Bar)
- **Kontainer Paginasi:** `.pagination-bar` / `.MuiTablePagination-root`
- **Rows Per Page:** `select#rows-per-page` / `select.rows-select` (Opsi: 5, 10, 25, 50)
- **Indikator Data:** `#pagination-info` (Format: `Menampilkan 1-10 dari 25 data`)
- **Tombol Navigasi:**
  - Halaman Pertama: `#btn-first-page`
  - Halaman Sebelumnya: `#btn-prev-page` / `.page-btn:has(svg)`
  - Halaman Berikutnya: `#btn-next-page` / `.page-btn:has(svg)`
  - Halaman Terakhir: `#btn-last-page`
- **Kontrol Padding Rapat:** `input#dense-switch[type="checkbox"]` (Toggle Dense Padding)

### G. Modal Dialog Approval / Rejection Leader
- **Modal Backdrop/Wrapper:** `.modal-backdrop` / `#modal-action-leader` / `#modal-approval-leader`
- **Judul Modal:** `.modal-title` (Contoh: `"Approval Modal Izin Off"`, `"Persetujuan Cuti Tim"`)
- **Konten Konfirmasi:** `.modal-body` (Rincian nama staf, tanggal, alasan)
- **Form Alasan Penolakan (khusus Reject):** `textarea#reject_reason` / `input#reject_reason` (Wajib diisi bila menolak)
- **Tombol Aksi Modal:**
  - Submit Persetujuan/Penolakan: `#sumbit-approval-request` / `#btn-confirm-approval` (`.btn-primary`)
  - Batal: `#btn-cancel-modal` / `.modal-close` (`.btn-secondary`)

### H. Toast / Snackbar Notifikasi Feedback
- **Kontainer Toast:** `#snackbar` / `.snackbar`
- **Pesan Notifikasi:** `#snackbar-message` / `.snackbar-text`
- **Tombol Tutup:** `#snackbar-close` / `.snackbar-close`
- **Variasi Status:** `.snackbar.success` (hijau), `.snackbar.error` (merah), `.snackbar.info` (biru)

---

## 3. Rincian Komponen Spesifik Per Halaman (11 Halaman)

---

### 🔹 KLASTER 1: Presensi & Koreksi Jam Regu

#### 1. Leader Semua (`/leader/all`)
- **Judul Halaman:** `HADIR - Leader Semua`
- **Tujuan:** Dashboard utama ringkasan metrik presensi harian seluruh anggota tim yang dipimpin oleh Leader.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Semua`
  - Kartu Metrik Tim:
    - Total Staf: `#stat-total-staf`
    - Tepat Waktu: `#stat-hadir-ontime`
    - Terlambat: `#stat-terlambat`
    - Tidak Hadir: `#stat-absen`
  - Filter Bar: Input `#search` (Nama / NIK), Date Range (`#start-date`, `#end-date`), `#btn-search`, `#btn-reset`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `JABATAN`, `TANGGAL`, `JAM MASUK`, `JAM KELUAR`, `STATUS`, `AKSI`
  - Tombol Detail: `.action-btn.detail` (Membuka modal detail presensi `#modal-detail-presensi`)
- **Endpoint API Terkait:**
  - `GET /api/leader/reports/all?startDate={start}&endDate={end}&search={q}&page={p}&limit={l}`
  - `GET /api/leader/statistics`

---

#### 2. Leader Kehadiran (`/leader/kehadiran`)
- **Judul Halaman:** `HADIR - Leader Kehadiran`
- **Tujuan:** Log presensi harian anggota tim regu lengkap dengan swafoto (selfie) masuk/keluar dan geolokasi GPS.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Kehadiran`
  - Filter Bar: Input `#search`, Date Range (`#start-date`, `#end-date`)
  - Header Kolom Tabel: `NO`, `FOTO MASUK`, `FOTO KELUAR`, `NAMA KARYAWAN`, `NIK`, `TANGGAL`, `JAM MASUK`, `LOKASI MASUK`, `JAM KELUAR`, `LOKASI KELUAR`, `TOTAL JAM`, `STATUS`
  - Thumbnail Foto Presensi: `.thumbnail-selfie-masuk`, `.thumbnail-selfie-keluar` (Membuka modal zoom foto `#modal-zoom-photo`)
  - Tautan Peta Geolokasi: `.link-maps-lokasi` (Membuka preview koordinat GPS)
- **Endpoint API Terkait:**
  - `GET /api/leader/reports/attendance?startDate={start}&endDate={end}&search={q}`
  - `GET /api/leader/reports/attendance/export`

---

#### 3. Leader Koreksi (`/leader/koreksi`)
- **Judul Halaman:** `HADIR - Leader Koreksi`
- **Tujuan:** Meninjau, memverifikasi, dan menyetujui permohonan koreksi jam presensi yang diajukan oleh anggota tim regu.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Koreksi`
  - Dropdown Filter Status: `#filter-status` (`Semua`, `PENDING`, `APPROVED`, `REJECTED`)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL ABSEN`, `TIPE KOREKSI`, `JAM LAMA`, `JAM BARU`, `ALASAN KOREKSI`, `STATUS`, `AKSI`
  - Tombol Aksi Langsung: `.btn-approve-koreksi`, `.btn-reject-koreksi`
  - Modal Approval Koreksi: `#modal-approval-koreksi` (Input catatan: `#reject_reason`, Tombol submit: `#sumbit-approval-request`)
- **Endpoint API Terkait:**
  - `GET /api/leader/corrections?status={s}&search={q}&page={p}&limit={l}`
  - `PATCH /api/leader/corrections/{id}` (Body: `{ is_approved: boolean, reject_reason?: string }`)

---

### 🔹 KLASTER 2: Pengelolaan & Validasi Cuti Tim

#### 4. Leader Cuti Semua (`/leader/cuti/semua`)
- **Judul Halaman:** `HADIR - Leader Cuti Semua`
- **Tujuan:** Rekapitulasi historis permohonan cuti seluruh anggota tim (status Disetujui, Ditolak, dan Pending).
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Cuti / Semua`
  - Dropdown Tipe Cuti: `#filter-cuti-type` (Semua, Cuti Tahunan, Sakit, Khusus, Duka, Menikah)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TIPE CUTI`, `TANGGAL MULAI`, `TANGGAL SELESAI`, `JUMLAH HARI`, `ALASAN CUTI`, `STATUS`
- **Endpoint API Terkait:**
  - `GET /api/leader/leave/all?type={t}&startDate={s}&endDate={e}`

---

#### 5. Leader Cuti Pending (`/leader/cuti/pending`)
- **Judul Halaman:** `HADIR - Leader Cuti Pending`
- **Tujuan:** Antrean permohonan cuti baru anggota tim yang menunggu keputusan persetujuan dari Leader.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Cuti / Pending`
  - Badge Counter Antrean: `#badge-count-pending-cuti`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TIPE CUTI`, `RENTANG TANGGAL`, `TOTAL HARI`, `ALASAN`, `TANGGAL PENGAJUAN`, `AKSI`
  - Tombol Aksi: `.action-btn.approve` (Setujui), `.action-btn.reject` (Tolak)
  - Modal Approval Cuti: `#modal-approval-cuti` (Textarea alasan tolak: `#reject_reason`, Submit: `#sumbit-approval-request`)
- **Endpoint API Terkait:**
  - `GET /api/leader/leave/pending?search={q}&page={p}&limit={l}`
  - `PATCH /api/leader/leave/{id}/approval` (Body: `{ is_approved: boolean, reject_reason?: string }`)

---

### 🔹 KLASTER 3: Pengelolaan & Validasi Lembur Tim

#### 6. Leader Lembur Semua (`/leader/lembur/semua`)
- **Judul Halaman:** `HADIR - Leader Lembur Semua`
- **Tujuan:** Rekapitulasi historis lembur seluruh anggota tim (Disetujui, Ditolak, Menunggu).
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Lembur / Semua`
  - Filter Bar: `#search`, `#start-date`, `#end-date`, `#btn-search`, `#btn-reset`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL LEMBUR`, `DURASI (JAM)`, `JAM MULAI`, `JAM SELESAI`, `KOMPENSASI`, `ALASAN LEMBUR`, `STATUS`
- **Endpoint API Terkait:**
  - `GET /api/leader/overtime/all?startDate={s}&endDate={e}&search={q}`

---

#### 7. Leader Lembur Pending (`/leader/lembur/pending`)
- **Judul Halaman:** `HADIR - Leader Lembur Pending`
- **Tujuan:** Antrean klaim permohonan lembur baru anggota tim yang membutuhkan persetujuan Leader.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Lembur / Pending`
  - Badge Counter Antrean: `#badge-count-pending-lembur`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL LEMBUR`, `DURASI`, `JAM AWAL`, `JAM AKHIR`, `ALASAN LEMBUR`, `AKSI`
  - Tombol Aksi: `.action-btn.approve`, `.action-btn.reject`
  - Modal Approval Lembur: `#modal-approval-lembur`
- **Endpoint API Terkait:**
  - `GET /api/leader/overtime/pending?search={q}&page={p}&limit={l}`
  - `PATCH /api/leader/overtime/{id}/approval` (Body: `{ is_approved: boolean, reject_reason?: string }`)

---

### 🔹 KLASTER 4: Pengelolaan Jadwal Kerja & Shifting Regu

#### 8. Leader Shifting Semua (`/leader/shifting/semua`)
- **Judul Halaman:** `HADIR - Leader Shifting Semua`
- **Tujuan:** Rekapitulasi jadwal rotasi kerja dan shift seluruh anggota regu kerja.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Shifting / Semua`
  - Dropdown Filter Pola Shift: `#filter-shift-pola` (Semua, Shift Pagi, Shift Siang, Shift Malam, Normal)
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TANGGAL JADWAL`, `POLA SHIFT`, `JAM MASUK`, `JAM KELUAR`, `LOKASI PENUGASAN`, `STATUS`
- **Endpoint API Terkait:**
  - `GET /api/leader/shifting/all?startDate={s}&endDate={e}&pola={p}`

---

#### 9. Leader Shifting Pending (`/leader/shifting/pending`)
- **Judul Halaman:** `HADIR - Leader Shifting Pending`
- **Tujuan:** Antrean permohonan pertukaran atau perubahan pola shift yang diajukan antar-anggota tim.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Shifting / Pending`
  - Badge Counter Antrean: `#badge-count-pending-shifting`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `SHIFT AWAL`, `SHIFT BARU`, `TANGGAL PERTUKARAN`, `PENGGANTI / PARTNER`, `ALASAN TUKAR`, `AKSI`
  - Tombol Aksi: `.action-btn.approve`, `.action-btn.reject`
  - Modal Approval Shifting: `#modal-approval-shifting`
- **Endpoint API Terkait:**
  - `GET /api/leader/shifting/pending?search={q}&page={p}&limit={l}`
  - `PATCH /api/leader/shifting/{id}/approval` (Body: `{ is_approved: boolean, reject_reason?: string }`)

---

### 🔹 KLASTER 5: Perizinan Khusus & Rekapitulasi Laporan Tim

#### 10. Leader Izin Off (`/leader/izin-off`) — *(🟢 Terverifikasi Selesai)*
- **Judul Halaman:** `HADIR - Leader Izin Off`
- **Tujuan:** Pengajuan izin tukar hari libur / off untuk anggota regu serta persetujuan permohonan izin off tim.
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Izin Off`
  - Tombol Pengajuan Header: `#btn-tambah` (Ajukan Izin Off &rarr; Membuka `#modal-tambah-izin-off`)
  - Filter Controls: `#search-input`, `#filter-status`, `#start-date`, `#end-date`, `#btn-search`, `#btn-reset`
  - Header Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `UPLINER`, `TANGGAL OFF`, `STATUS`, `ALASAN`, `AKSI`
  - Modal Tambah Izin Off: `#modal-tambah-izin-off` (Form: `#nama-pengajuan`, `#upliner-pengajuan`, `#tanggal-off`, `#alasan-off`, Submit: `#btn-submit-tambah`)
  - Modal Approval / Reject: `#modal-approval-izin-off` (Field: `#reject_reason`, Submit: `#sumbit-approval-request`)
  - Modal Detail: `#modal-detail-izin-off`
  - Toggle Dense Padding: `#dense-switch`
- **Endpoint API Terkait:**
  - `GET /api/off-permit?startDate={s}&endDate={e}&status={st}&search={q}`
  - `POST /api/off-permit`
  - `PATCH /api/off-permit/{id}`
  - `DELETE /api/off-permit/{id}`

---

#### 11. Leader Generate Report (`/leader/generate`)
- **Judul Halaman:** `HADIR - Leader Generate Report`
- **Tujuan:** Mengatur parameter rekapitulasi tim (periode tanggal, tipe laporan) dan men-generate berkas (.xlsx/.csv).
- **Elemen Spesifik:**
  - Breadcrumbs: `Dashboard / Leader / Download Report`
  - Form Generator: `#form-generate-leader`
  - Dropdown Tipe Laporan: `#select-report-type` (Opsi: `Rekapitulasi Kehadiran`, `Rekapitulasi Cuti`, `Rekapitulasi Lembur`, `Rekapitulasi Shifting`)
  - Filter Rentang Tanggal: `#start-date-generate`, `#end-date-generate`
  - Pilihan Format Berkas:
    - Radio XLSX: `#radio-format-xlsx` (Checked by default)
    - Radio CSV: `#radio-format-csv`
  - Tombol Download: `#btn-download-report` (`.btn-primary`, teks *"Generate & Download Report"*)
  - Feedback Alert: `#alert-download-feedback` (Alert alert-success *"Laporan berhasil dibuat dan sedang diunduh"*)
- **Endpoint API Terkait:**
  - `POST /api/leader/reports/generate` (Body: `{ report_type: string, start_date: string, end_date: string, format: string }`)
  - `GET /api/leader/reports/download/{task_id}`

