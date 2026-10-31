# Katalog Komponen & Selector Modul Management — HADIR

Dokumen ini mendokumentasikan secara rinci seluruh elemen antarmuka (UI), penamaan selector CSS/ID, kontrol form, struktur kolom tabel, modal dialog, dan endpoint backend terkait untuk seluruh **18 Halaman Modul Management** pada aplikasi **HADIR** (`https://magang.dikahadir.com/management/*` dan mirror offline `http://localhost:8085/management/*`).

Dokumen ini menjadi acuan utama (*Single Source of Truth*) dalam pembuatan **Page Object Models (POM)**, **Step Definitions Cucumber**, dan **Skenario Pengujian Gherkin**.

---

## 1. Daftar 18 Halaman & Target URL

| No | Modul / Halaman | URL Live Staging | URL Offline Mirror | Status Komponen |
|:---:|:---|:---|:---|:---:|
| 1 | **Management User** | `https://magang.dikahadir.com/management/user` | `http://localhost:8085/management/user` | 🟢 Lengkap |
| 2 | **Register User V2** | `https://magang.dikahadir.com/management/user-v2/register` | `http://localhost:8085/management/user-v2/register` | 🟢 Lengkap |
| 3 | **Self Registration** | `https://magang.dikahadir.com/management/self-registration` | `http://localhost:8085/management/self-registration` | 🟢 Lengkap |
| 4 | **Divisi** | `https://magang.dikahadir.com/management/divisi` | `http://localhost:8085/management/divisi` | 🟢 Lengkap |
| 5 | **Departemens** | `https://magang.dikahadir.com/management/departemens` | `http://localhost:8085/management/departemens` | 🟢 Lengkap |
| 6 | **Departements Settings** | `https://magang.dikahadir.com/management/departements-settings` | `http://localhost:8085/management/departements-settings` | 🟢 Lengkap |
| 7 | **Position** | `https://magang.dikahadir.com/management/position` | `http://localhost:8085/management/position` | 🟢 Lengkap |
| 8 | **Client Upliner** | `https://magang.dikahadir.com/management/client-upliner` | `http://localhost:8085/management/client-upliner` | 🟢 Lengkap |
| 9 | **Location Point** | `https://magang.dikahadir.com/management/location-point` | `http://localhost:8085/management/location-point` | 🟢 Lengkap |
| 10 | **Job Level** | `https://magang.dikahadir.com/management/job-level` | `http://localhost:8085/management/job-level` | 🟢 Lengkap |
| 11 | **Shifting** | `https://magang.dikahadir.com/management/shifting` | `http://localhost:8085/management/shifting` | 🟢 Lengkap |
| 12 | **Schedule** | `https://magang.dikahadir.com/management/schedule` | `http://localhost:8085/management/schedule` | 🟢 Lengkap |
| 13 | **Calendar** | `https://magang.dikahadir.com/management/calendar` | `http://localhost:8085/management/calendar` | 🟢 Lengkap |
| 14 | **Unit Leave** | `https://magang.dikahadir.com/management/unit-leave` | `http://localhost:8085/management/unit-leave` | 🟢 Lengkap |
| 15 | **Day Off** | `https://magang.dikahadir.com/management/day-off` | `http://localhost:8085/management/day-off` | 🟢 Lengkap |
| 16 | **Monthly Schedule** | `https://magang.dikahadir.com/management/monthly-schedule` | `http://localhost:8085/management/monthly-schedule` | 🟢 Lengkap |
| 17 | **User Monitoring** | `https://magang.dikahadir.com/management/user-monitoring` | `http://localhost:8085/management/user-monitoring` | 🟢 Lengkap |
| 18 | **OT Reason** | `https://magang.dikahadir.com/management/ot-reason` | `http://localhost:8085/management/ot-reason` | 🟢 Lengkap |

---

## 2. Standar Komponen Umum (Universal Common Layout)

Setiap halaman modul Management menggunakan pola tata letak antarmuka standar Material-UI (MUI):

### A. Kontrol Filter & Pencarian (Search Bar Card)
- **Kontainer:** `.filter-card > .filter-search-container`
- **Form Pencarian:** `#form-search` / `.form-search`
- **Wrapper Input:** `.input-search-wrapper` (dengan ikon SVG search)
- **Input Search:** `#search` / `.input-search` (atribut `type="search"`, `aria-label`)
- **Tombol Reset:** `#btn-reset` / `.btn-reset` (warna teks/garis emas `#eab308`)
- **Tombol Search:** `#btn-search` / `.btn-search` (warna primer biru `#1ba0e2`)
- **Tombol Tambah:** `.button-add` / `.btn-primary`

### B. Tabel Data Karyawan / Master (Data Table Card)
- **Kontainer Tabel:** `.table-card` & `.table-container`
- **Tabel:** `table.table_base.MuiTable-root`
- **Header:** `thead > tr.MuiTableRow-head > th` (latar `#3D3D3D`, teks putih tebal uppercase)
- **Baris Data:** `tbody > tr.MuiTableRow-root[data-id]`
- **Aksi Baris:** `.action-container`:
  - Tombol Edit: `button.icon-btn.btn-edit-direct` (ikon pensil biru)
  - Tombol Hapus: `button.icon-btn.btn-delete-direct` (ikon tempat sampah merah)

### C. Bar Navigasi Paginasi (Pagination Bar)
- **Kontainer Footer:** `.pagination-bar.MuiTablePagination-root`
- **Rows Per Page Dropdown:** `select#rows-per-page.MuiTablePagination-select` (opsi: 5, 10, 25)
- **Indikator Jumlah Data:** `#displayed-rows.displayed-rows` (format: `1-10 of 10` atau `0-0 of 0`)
- **Tombol Navigasi:**
  - Halaman Pertama: `button#btn-first-page.page-nav-btn`
  - Halaman Sebelumnya: `button#btn-prev-page.page-nav-btn`
  - Halaman Berikutnya: `button#btn-next-page.page-nav-btn`
  - Halaman Terakhir: `button#btn-last-page.page-nav-btn`

### D. Modal Dialog Standar
- **Backdrop:** `.modal-overlay[role="dialog"]` (kelas `.open` saat ditampilkan)
- **Kotak Dialog:** `.modal-dialog` (animasi slide dari bawah)
- **Header Dialog:** `.modal-header > h2.modal-title` & `button.modal-close-btn`
- **Body Dialog:** `.modal-body` (berisi field formulir `.form-group`)
- **Footer Dialog:** `.modal-footer`:
  - Tombol Batal / Tidak: `.btn.btn-secondary`
  - Tombol Simpan / Tambah: `.btn.btn-primary` / `.btn.btn-danger` (konfirmasi hapus)

### E. Snackbar Notifikasi Feedback
- **Elemen:** `#alert-snackbar.snackbar` (kelas `.open` saat aktif, `.error` untuk kegagalan)
- **Teks Notifikasi:** `#snackbar-message`

---

## 3. Rincian Komponen Spesifik Per Halaman

---

### 1. Management User (`/management/user`)
- **Judul Dokumen:** `HADIR - Management User`
- **Elemen Spesifik:**
  - Dropdown Tipe Pencarian: `#search-type` (opsi: `"Nama"`, `"Nik"`)
  - Input Pencarian: `#search`
  - Tombol Filter Multi-Kriteria: `#btn-filter`
  - Tombol Export: `#btn-export-users` / `.button-add`
  - Toggle Switch: Active Switch & User Project Switch (`.MuiSwitch-root`)
  - Aksi Tambahan: Tombol Sync Approval, Tombol Sisa Cuti (`#leave-modal`)
- **Endpoint API:**
  - Daftar User: `GET /api/users` / `GET /hadir-api-magang/users`
  - Update User: `PATCH /api/users/{id}`
  - Delete User: `DELETE /api/users/{id}`
  - Export: `GET /api/users/export-users`

---

### 2. Register User V2 (`/management/user-v2/register`)
- **Judul Dokumen:** `HADIR - Pendaftaran Karyawan V2`
- **Elemen Spesifik:**
  - Upload Foto Profil / Selfie: `#photo-upload`
  - Input NIK: `#nik`
  - Input Nama Lengkap: `#fullname`
  - Input Email: `#email`
  - Input Password: `#password`
  - Dropdown Divisi: `#select-divisi`
  - Dropdown Departemen: `#select-departemen`
  - Dropdown Posisi: `#select-position`
  - Dropdown Job Level: `#select-job-level`
  - Dropdown Lokasi Presensi: `#select-location-point`
  - Tombol Registrasi: `#btn-register`
- **Endpoint API:**
  - Upload Foto: `POST /api/upload-selfie`
  - Submit Register: `POST /hadir-api-magang/users/register`

---

### 3. Self Registration (`/management/self-registration`)
- **Judul Dokumen:** `HADIR - Self Registration`
- **Elemen Spesifik:**
  - Filter Status Verifikasi: `Semua`, `Pending`, `Approved`, `Rejected`
  - Checkbox Batch Selection: `#select-all-users`, `.user-row-checkbox`
  - Tombol Batch Action: `#btn-batch-approve`, `#btn-batch-reject`, `#btn-batch-delete`
  - Aksi Navigasi Edit: Link menuju `/management/user-v2/edit/{id}`
- **Endpoint API:**
  - Daftar Registrasi Mandiri: `GET /api/self-registrations`
  - Approval Mandiri: `POST /api/self-registration/approve`
  - Reject Mandiri: `POST /api/self-registration/reject`

---

### 4. Divisi (`/management/divisi`)
- **Judul Dokumen:** `HADIR - Divisi`
- **Elemen Spesifik:**
  - Tombol Tambah: `#btn-tambah-divisi.button-add`
  - Modal Tambah: `#modal-tambah-divisi` & Form `#form-tambah-divisi` (`input#name`)
  - Modal Edit: `#modal-edit-divisi` & Form `#form-edit-divisi` (`input#edit-name`)
  - Modal Hapus: `#modal-delete-divisi` (`#btn-konfirmasi-delete`)
- **Endpoint API:**
  - Daftar Divisi: `GET /api/divisi`
  - Tambah Divisi: `POST /api/divisi`
  - Edit Divisi: `PATCH /api/divisi/{id}`
  - Hapus Divisi: `DELETE /api/divisi/{id}`

---

### 5. Departemens (`/management/departemens`)
- **Judul Dokumen:** `HADIR - Departemens`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-departemen.button-add`
  - Kolom Tabel: `NO`, `NAMA DEPARTEMEN`, `TANGGAL BUAT`, `TANGGAL UPDATE`, `ATUR VIRTUAL ID`, `AKSI`
  - Modal Virtual ID: `#modal-virtual-id` (Input File Logo `#foto-logo`, Picker Warna `#footer-color`)
  - Toast Notifikasi: `"Berhasil Menambahkan Departemen"`, `"Berhasil Mengatur Virtual ID"`
- **Endpoint API:**
  - CRUD Departemens: `GET /api/departemens/`, `POST /api/departemens/`, `PATCH /api/departemens/{id}`, `DELETE /api/departemens/{id}`
  - Type Shift Detail: `/management/unit/type-shift/[id]`

---

### 6. Departements Settings (`/management/departements-settings`)
- **Judul Dokumen:** `HADIR - Departements Settings`
- **Elemen Spesifik:**
  - Tombol Tambah: `#btn-tambah-setting` (Teks: `"Tambah Departemens Setting"`)
  - Kolom Tabel: `NO`, `NAMA DEPARTEMENS`, `TANGGAL BUAT`, `TANGGAL UPDATE`, `STATUS` (Switch Active), `AKSI`
  - Modal Tambah: `#modal-tambah-setting` (`input#name`)
  - Modal Hapus: `#modal-delete-setting` (Pesan: `"Apakah anda ingin menghapus departements [Nama]?"`)
  - Toast Notifikasi: `"Berhasil Menambahkan Departemens"`, `"Berhasil Delete Departements"`
- **Endpoint API:**
  - Setting Endpoints: `GET /api/departements-settings`, `POST /api/departements-settings`, `DELETE /api/departements-settings/{id}`

---

### 7. Position (`/management/position`)
- **Judul Dokumen:** `HADIR - Position`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-position`
  - Kolom Tabel: `NO`, `NAMA POSITION`, `NAMA DEPARTEMENT`, `TANGGAL BUAT`, `TANGGAL UPDATE`, `AKSI`
  - Modal Tambah: `#modal-tambah-position` (`input#name`, `select#departement`)
  - Modal Edit: `#modal-edit-position` (`input#edit-name`, `select#edit-departement`)
  - Modal Hapus: `#modal-delete-position` (`"Apakah anda ingin menghapus Position [Nama]?"`)
  - Toast Notifikasi: `"Berhasil Menambahkan Position"`, `"Berhasil Edit Posisi"`, `"Berhasil Delete Position"`
- **Endpoint API:**
  - Position Endpoints: `GET /api/position`, `POST /api/position`, `PATCH /api/position/{id}`, `DELETE /api/position/{id}`

---

### 8. Client Upliner (`/management/client-upliner`)
- **Judul Dokumen:** `HADIR - Client Upliner`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-upliner`
  - Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TIPE` (Klien V2/V3, Upliner V2/V3), `ROLE`, `TANGGAL BUAT`, `AKSI`
  - Modal Hapus: `#modal-delete-upliner` (Pesan: `"Apakah anda ingin menghapus User [Nama]? Data akan terhapus selamanya dan tidak bisa dikembalikan"`)
  - Toast Notifikasi: `"Berhasil menghapus user"`
- **Endpoint API:**
  - Users Endpoint: `GET /api/users/`, `DELETE /api/users/{id}`
  - Rute Form Terkait: `/management/client-upliner/register`, `/management/client-upliner/register-upliner-bca`

---

### 9. Location Point (`/management/location-point`)
- **Judul Dokumen:** `HADIR - Location Point`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-lokasi`
  - Kolom Tabel: `NO`, `NAMA LOKASI`, `LATITUDE`, `LONGITUDE`, `MAKSIMAL RADIUS (METER)`, `DESCRIPTION`, `AKSI`
  - Modal Tambah: `#modal-tambah-lokasi` (`#name`, `#latitude`, `#longitude`, `#radius`, `#description`)
  - Modal Edit: `#modal-edit-lokasi` (`#edit-name`, `#edit-latitude`, `#edit-longitude`, `#edit-radius`, `#edit-description`)
  - Modal Hapus: `#modal-delete-lokasi` (`"Apakah anda ingin menghapus Location Point [Nama]?"`)
  - Toast Notifikasi: `"Berhasil Tambah Location Point"`, `"Berhasil Edit Location Point"`, `"Berhasil Delete Location Point"`
- **Endpoint API:**
  - Location Endpoints: `GET /api/locations`, `POST /api/locations`, `PATCH /api/locations/{id}`, `DELETE /api/locations/{id}`

---

### 10. Job Level (`/management/job-level`)
- **Judul Dokumen:** `HADIR - Job Level`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan level"`
  - Tombol Tambah: `#btn-tambah-level`
  - Kolom Tabel: `NO`, `LEVEL / JABATAN`, `DESCRIPTION`, `BREAK START`, `BREAK END`, `AKTIF TRAKING`, `STATUS`, `AKSI`
  - Modal Tambah: `#modal-tambah-level` (`#level`, `#description`, `#break_start`, `#break_end`)
  - Modal Hapus: `#modal-delete-level`
  - Toast Notifikasi: `"Berhasil Menambahkan Job Level"`, `"Berhasil Mengubah Job Level"`, `"Berhasil Menghapus Job Level"`
- **Endpoint API:**
  - Job Level Endpoints: `GET /api/job-levels`, `POST /api/job-levels`, `PATCH /api/job-levels/{id}`, `DELETE /api/job-levels/{id}`

---

### 11. Shifting (`/management/shifting`)
- **Judul Dokumen:** `HADIR - Shifting`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Dropdown Filter Unit: `select#unit` / `select#select-unit`
  - Tombol Tambah: `#btn-tambah-shifting`
  - Kolom Tabel: `NO`, `KODE SHIFTING`, `NAMA SHIFTING`, `UNIT / DEPARTEMEN`, `JAM MASUK`, `JAM KELUAR`, `BREAK`, `AKSI`
  - Modal Tambah: `#modal-tambah-shifting` (`#code`, `#name`, `#unit`, `#time_in`, `#time_out`, `#break_start`, `#break_end`)
  - Modal Hapus: `#modal-delete-shifting` (`"Apakah anda ingin menghapus Shifting [Nama]?"`)
  - Validasi Input: `"Kode Shifting Harus Diisi!"`, `"Nama shifting harus diisi!"`, `"Jam masuk harus diisi!"`, `"Maksidal kode shifting adalah 15 huruf"`
  - Toast Notifikasi: `"Berhasil Menambahkan Shifting"`, `"Berhasil Edit Shifting"`, `"Berhasil Delete Shifting"`
- **Endpoint API:**
  - Shifting Endpoints: `GET /api/shiftings`, `POST /api/shiftings`, `PATCH /api/shiftings/{id}`, `DELETE /api/shiftings/{id}`, `GET /api/departements`

---

### 12. Schedule (`/management/schedule`)
- **Judul Dokumen:** `HADIR - Schedule`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-jadwal`
  - Kolom Tabel: `NO`, `NAMA JADWAL`, `TIPE JADWAL` (Jadwal kerja tetap, Jadwal kerja flexible, Jadwal kerja shift), `HARI KERJA`, `TANGGAL BUAT`, `AKSI`
  - Modal Tambah: `#modal-tambah-jadwal` (`#name`, `#type`, `#work_days`)
  - Modal Hapus: `#modal-delete-jadwal` (`"Apakah anda ingin menghapus jadwal [Nama]?"`)
  - Validasi Input: `"Isi terlebih dahulu hari kerja"`, `"Silahkan pilih jadwal"`
  - Toast Notifikasi: `"Berhasil menambahkan jadwal"`, `"Berhasil mengubah jadwal"`, `"Berhasil hapus jadwal"`
- **Endpoint API:**
  - Schedule Endpoints: `GET /api/schedule`, `POST /api/schedule`, `PATCH /api/schedule/{id}`, `DELETE /api/schedule/{id}`

---

### 13. Calendar (`/management/calendar`)
- **Judul Dokumen:** `HADIR - Calendar`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-kalender` (Teks: `"Tambahkan Kalender"`)
  - Kolom Tabel: `NO`, `NAMA KALENDER UNIT`, `DEPARTEMEN TERKAIT`, `TOTAL EVENT / CUTI`, `TANGGAL BUAT`, `AKSI`
  - Modal Tambah: `#modal-tambah-kalender` (`#name`, `#departemen`)
  - Modal Hapus: `#modal-delete-kalender` (`"Apakah anda ingin menghapus Event [Nama]?"`)
  - Toast Notifikasi: `"Berhasil Menambahkan Tanggal Cuti"`, `"Berhasil Menghapus Event Date"`
- **Endpoint API:**
  - Calendar Endpoints: `GET /api/calendar`, `POST /api/calendar`, `DELETE /api/calendar/{id}`, `GET /api/departemens/`
  - Rute Tanggal Event: `/management/calendar-date/[id]`

---

### 14. Unit Leave (`/management/unit-leave`)
- **Judul Dokumen:** `HADIR - Unit Leave`
- **Elemen Spesifik:**
  - Placeholder Search: `"Cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-aturan` (Teks: `"Tambahkan Aturan Cuti"`)
  - Kolom Tabel: `NO`, `NAMA ATURAN CUTI`, `ELIGIBLE PENGATURAN CUTI`, `MAKSIMAL SISA CUTI`, `BULAN BATAS SISA CUTI`, `TOTAL CUTI`, `AKSI`
  - Modal Tambah: `#modal-tambah-aturan` (`#name`, `#eligible_month`, `#max_remaining`, `#carry_forward_month`, `#total_leave`)
  - Modal Hapus: `#modal-delete-aturan` (`"Apakah anda yakin ingin menghapus aturan cuti [Nama]?"`)
  - Validasi Input: `"Nama aturan cuti harus diisi!"`, `"Maksimal sisa cuti harus diisi!"`, `"Tanggal batas sisa cuti harus diisi!"`
  - Toast Notifikasi: `"Sukses Mengubah Aturan Cuti"`, `"Berhasil Menghapus Aturan Cuti"`
- **Endpoint API:**
  - Unit Leave Endpoints: `GET /api/unit-leave`, `POST /api/unit-leave`, `PATCH /api/unit-leave/{id}`, `DELETE /api/unit-leave/{id}`

---

### 15. Day Off (`/management/day-off`)
- **Judul Dokumen:** `HADIR - Day Off`
- **Elemen Spesifik:**
  - Placeholder Search: `"Cari hari libur..."`
  - Tombol Tambah: `#btn-tambah-dayoff`
  - Kolom Tabel: `NO`, `TANGGAL`, `DESKRIPSI LIBUR`, `KATEGORI LIBUR` (Liburan Nasional / Cuti Bersama), `STATUS`, `AKSI`
  - Modal Tambah: `#modal-tambah-dayoff` (`#date`, `#description`, `#category`)
  - Toast Notifikasi: `"Berhasil Menambahkan Hari Libur"`, `"Berhasil Menghapus Hari Libur"`
- **Endpoint API:**
  - Day Off Endpoints: `GET /api/day-off`, `POST /api/day-off`, `DELETE /api/day-off/{id}`

---

### 16. Monthly Schedule (`/management/monthly-schedule`)
- **Judul Dokumen:** `HADIR - Monthly Schedule`
- **Elemen Spesifik:**
  - Bagian Import Roster Excel: `#dropzone-import`, Input File `#file-import-schedule`, Tombol `#btn-upload-schedule`
  - Download Template Excel: `#btn-download-template`
  - Filter Bulan & Tahun: `#select-month`, `#select-year`
  - Filter Unit / Departemen: `#select-filter-unit`
  - Kolom Tabel: `No`, `Karyawan`, `NIK`, `Unit`, `Jadwal Shift`, `Tanggal Kerja`, `Aksi`
  - Modal Edit Jadwal: `#modal-edit-schedule`
  - Modal Hapus Jadwal: `#modal-delete-schedule`
- **Endpoint API:**
  - Import Schedule: `POST /api/monthly-schedule/import`
  - Schedule List: `GET /api/monthly-schedules`

---

### 17. User Monitoring (`/management/user-monitoring`)
- **Judul Dokumen:** `HADIR - User Monitoring`
- **Elemen Spesifik:**
  - Placeholder Search: `"cari berdasarkan nama"`
  - Tombol Tambah: `#btn-tambah-monitoring`
  - Kolom Tabel: `NO`, `FOTO`, `NAMA KARYAWAN`, `NIK`, `TERAKHIR AKTIF`, `LOKASI GEOFENCE`, `STATUS`, `AKSI`
  - Modal Tambah: `#modal-tambah-monitoring` (`#fullname`, `#nik`, `#location`)
  - Modal Hapus: `#modal-delete-monitoring` (`"Apakah anda ingin menghapus monitoring user [Nama]?"`)
  - Toast Notifikasi: `"Berhasil Menambahkan Monitoring User"`, `"Berhasil Menghapus Monitoring User"`
- **Endpoint API:**
  - Monitoring Endpoints: `GET /api/user-monitoring`, `POST /api/user-monitoring`, `DELETE /api/user-monitoring/{id}`
  - Rute Tambah/Edit: `/management/user-monitoring/add`, `/management/user-monitoring/edit/[id]`

---

### 18. OT Reason (`/management/ot-reason`)
- **Judul Dokumen:** `HADIR - Overtime Reason`
- **Elemen Spesifik:**
  - Placeholder Search: `"Cari overtime reason..."`
  - Tombol Tambah: `#btn-tambah-ot` (Teks: `"Tambahkan Overtime Reason"`)
  - Kolom Tabel: `NO`, `NAMA OVERTIME REASON`, `TANGGAL BUAT`, `TANGGAL UPDATE`, `AKSI`
  - Modal Tambah: `#modal-tambah-ot` (`input#name`, tombol `"Tambah"`)
  - Modal Edit: `#modal-edit-ot` (`input#edit-name`, tombol `"Simpan"`)
  - Modal Hapus: `#modal-delete-ot` (Pesan: `"Apakah overtime reason [Nama] akan dihapus?"`, tombol `"Tutup"`, `"Hapus"`)
  - Toast Notifikasi: `"Berhasil menambahkan overtime reason"`, `"Berhasil mengubah overtime reason"`, `"Berhasil menghapus overtime reason"`
- **Endpoint API:**
  - OT Reason Endpoints: `GET /api/ot-reason`, `POST /api/ot-reason`, `PATCH /api/ot-reason/{id}`, `DELETE /api/ot-reason/{id}`
  - Rute Data Detail: `/management/ot-reason/[id]/ot-reason-data`

