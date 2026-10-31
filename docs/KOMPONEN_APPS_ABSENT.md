# Pemetaan Komponen UI — Modul Client Mobile Hadir (`/apps/absent/*`)

Dokumen ini memuat inventarisasi dan pemetaan teknis seluruh **komponen UI, form input, tombol aksi, modal dialog, dan selector** dari 10 halaman antarmuka web client/mobile Hadir (`https://magang.dikahadir.com/apps/absent/*`). Seluruh berkas HTML DOM render dan JavaScript Chunks Next.js telah diunduh dan tersimpan di `src/test/resources/static-web/apps/absent/`.

---

## Ringkasan Eksekutif 10 Halaman Target

| No | Modul / Halaman | URL Target | Berkas Static Render | Hak Akses Utama | Karakteristik Komponen Utama |
|:--:|:----------------|:-----------|:---------------------|:----------------|:-----------------------------|
| 1 | **Home / Dashboard Presensi** | `/apps/absent` | `apps/absent/index.html` | All Authenticated (`staff`, `admin`, `client`) | Kartu ringkasan presensi (WFO, Cuti, Sakit, WFH, Lembur, Terlambat, Pulang Cepat), tombol quick menu, histori singkat, dan tombol CTA **Absen Masuk**. |
| 2 | **Riwayat Presensi (Activity)** | `/apps/absent/activity` | `apps/absent/activity.html` | All Authenticated | Header dengan tombol kembali, daftar kartu riwayat absensi (foto selfie check-in, status, jam masuk/pulang, dan catatan). |
| 3 | **Koreksi Absen** | `/apps/absent/correction` | `apps/absent/correction.html`<br>`correction_modal.html` | All Authenticated | Header, list pengajuan koreksi (badge status Approved, Rejected, Pending), dan modal drawer **Ajukan Koreksi** dengan date-time picker (Jam Masuk & Jam Keluar). |
| 4 | **Pengajuan Izin** | `/apps/absent/permit` | `apps/absent/permit.html`<br>`permit_modal.html` | All Authenticated | Tab filter (`Terlambat`, `Pulang Cepat`, `Izin Off`), list riwayat izin per tab, dan modal drawer **Ajukan Izin** dengan date picker, time picker, dan textarea keterangan. |
| 5 | **Lembur Client** | `/apps/absent/overtime-client` | `apps/absent/overtime-client.html`<br>`overtime_modal.html` | Staff / Client | List riwayat lembur, counter total, dan modal drawer **Ajukan Lembur** (Jam masuk, Jam keluar, catatan tugas lembur). |
| 6 | **Cuti Client** | `/apps/absent/leave-client` | `apps/absent/leave-client.html`<br>`leave_modal.html` | Staff / Client | Tab form vs info cuti, combobox tipe cuti, pemilih rentang tanggal (date range), catatan, dan tombol submit. |
| 7 | **Izin Sakit** | `/apps/absent/sick` | `apps/absent/sick.html`<br>`sick_modal.html` | Staff / Client | List pengajuan sakit dengan thumbnail foto bukti surat dokter, dan modal **Ajukan Request Sakit** (rentang tanggal & input upload file surat). |
| 8 | **Download Share Report** | `/apps/absent/reports/share_report` | `apps/absent/reports/share_report.html` | Upliner / Client / Leader | Layout download laporan tim dengan dropdown periode (`Harian / day`, `Mingguan / week`, `Bulanan / month`) dan tombol submit ekspor XLSX. |
| 9 | **Daftar Anggota Tim (Staff)** | `/apps/absent/staff` | `apps/absent/staff.html` | Upliner / Client / Leader | Infinite scroll list kartu anggota tim, foto profil, status presensi harian, dan bottom-sheet modal **Informasi Cuti Staff**. |
| 10 | **Ubah Password** | `/apps/absent/change-password` | `apps/absent/change-password.html` | All Authenticated | Form ubah kata sandi dengan field `Password` & `Confrim Password` (disertai tombol toggle visibility mata) dan tombol `Confirm New Password`. |

---

## Rincian Spesifikasi Komponen per Halaman

### 1. Halaman Home Presensi (`/apps/absent`)
- **Header Profil Pengguna:**
  - Avatar / User Photo: `img[alt="photo user"]`
  - Nama Pengguna: `p:has-text("Hai,")` atau `MuiTypography-root`
  - Subtitle: `p:has-text("Have a nice day !")`
  - Tombol Profil / Menu: `button > img[alt="menu"]`
- **Kartu Ringkasan "Kehadiranmu":**
  - Pemilih Bulan & Tahun: Button `October` & `2026`
  - Metrik Counter:
    - WFO: `img[alt="wfo"]` + count
    - Cuti: `img[alt="leave"]` + count
    - Sakit: `img[alt="sick"]` + count
    - WFH: `img[alt="wfh"]` + count
    - Lembur: `img[alt="overtime"]` + count
    - Terlambat: `img[alt="late"]` + count
    - Pulang Cepat: `img[alt="early_leave"]` + count
- **Menu Navigasi Cepat (Quick Access Grid):**
  - Menu Absensi: `img[alt="Absensi"]` -> navigasi kamera selfie
  - Menu Koreksi Absen: `img[alt="Koreksi Absen"]` -> navigasi ke `/apps/absent/correction`
  - Menu Izin: `img[alt="Izin"]` -> navigasi ke `/apps/absent/permit`
  - Menu Lembur: `img[alt="Lembur"]` -> navigasi ke `/apps/absent/overtime-client`
  - Menu Cuti: `img[alt="Cuti"]` -> navigasi ke `/apps/absent/leave-client`
  - Menu Sakit: `img[alt="Sakit"]` -> navigasi ke `/apps/absent/sick`
- **CTA Utama:**
  - Tombol Absen: `button:has-text("Absen Masuk")` (Class: `MuiButton-containedPrimary css-ctijcz`)

---

### 2. Halaman Riwayat Presensi (`/apps/absent/activity`)
- **Header:**
  - Tombol Back: `button:has(svg)` (ikon chevron kembali)
  - Judul: `p:has-text("History Absensi")`
- **Daftar Riwayat (List Cards):**
  - Tiap item memuat:
    - Foto Selfie: `img[alt="Photo Check In"]`
    - Nama Karyawan: `p:has-text("Admin Hadir")`
    - Hari & Tanggal: `p` (misal: "Minggu, 05 July 2026")
    - Tipe Presensi: Badge / text ("Work From Office", "Work From Home", "Sakit")
    - Jam Kerja: Format "Masuk pukul 08:30 - 05:00"
    - Catatan: "Notes : ..."

---

### 3. Halaman Koreksi Absen (`/apps/absent/correction`)
- **Header & Ringkasan:**
  - Tombol Kembali: `button:has-text("Kembali")`
  - Judul Halaman: `p:has-text("Halaman Koreksi")`
  - Label Total: `p:has-text("List Koreksi")` & `p:has-text("Total :")`
- **List Riwayat Koreksi:**
  - Status Tag: `Approved` (hijau), `Rejected` (merah), `Pending` (oranye)
  - Approval Note: "Approved by : Admin Hadir"
  - Jam Masuk & Jam Keluar yang diajukan
- **Tombol Buka Form:**
  - Button CTA: `button:has-text("Ajukan Koreksi")`
- **Komponen Form Modal Drawer (`correction_modal.html`):**
  - Judul Modal: `p:has-text("Ajukan Koreksi Absen")`
  - Input Jam Masuk:
    - Textbox: `input[placeholder="dd mm yyyy, hh:mm"]` (Field 1)
    - Datepicker trigger: `button[aria-label="Choose date"]`
  - Input Jam Keluar:
    - Textbox: `input[placeholder="dd mm yyyy, hh:mm"]` (Field 2)
    - Datepicker trigger: `button[aria-label="Choose date"]`
  - Tombol Aksi Form:
    - Reset: `button:has-text("Reset")`
    - Submit: `button:has-text("Ajukan")`

---

### 4. Halaman Pengajuan Izin (`/apps/absent/permit`)
- **Header & Tab Navigasi:**
  - Tab 1: `button[role="tab"]:has-text("Terlambat")`
  - Tab 2: `button[role="tab"]:has-text("Pulang Cepat")`
  - Tab 3: `button[role="tab"]:has-text("Izin Off")`
- **Tombol Buka Form:**
  - Button CTA: `button:has-text("Ajukan Izin Terlambat")` / `Ajukan Izin Pulang Cepat` / `Ajukan Izin Off`
- **Komponen Form Modal Drawer (`permit_modal.html`):**
  - Input Tanggal:
    - Textbox: `input[placeholder="mm/dd/yyyy"]`
    - Kalender button: `button[aria-label="Choose date"]`
  - Input Jam:
    - Textbox: `input[placeholder="14:00"]`
    - Jam button: `button[aria-label="Choose time"]`
  - Input Keterangan:
    - Textarea/Textbox: `input[placeholder="Keterangan"]`
  - Tombol Aksi Form:
    - Reset: `button:has-text("Reset")`
    - Submit: `button:has-text("Ajukan")`

---

### 5. Halaman Lembur Client (`/apps/absent/overtime-client`)
- **Header & List Counter:**
  - Judul: `p:has-text("Halaman Lembur")`
  - Counter: `p:has-text("Total :")`
- **Tombol Buka Form:**
  - Button CTA: `button:has-text("Ajukan Lembur")`
- **Komponen Form Modal Drawer (`overtime_modal.html`):**
  - Input Jam Masuk Lembur:
    - Textbox: `input[placeholder="dd mm yyyy, hh:mm"]`
    - Datepicker trigger: `button[aria-label="Choose date"]`
  - Input Jam Keluar Lembur:
    - Textbox: `input[placeholder="dd mm yyyy, hh:mm"]`
    - Datepicker trigger: `button[aria-label="Choose date"]`
  - Catatan Lembur:
    - Textbox: `input[placeholder="Catatan"]`
  - Tombol Aksi Form:
    - Reset: `button:has-text("Reset")`
    - Submit: `button:has-text("Ajukan")`

---

### 6. Halaman Cuti Client (`/apps/absent/leave-client`)
- **Header & List Counter:**
  - Judul: `p:has-text("Halaman Cuti")`
  - Counter: `p:has-text("Total :")`
- **Tombol Buka Form:**
  - Button CTA: `button:has-text("Ajukan Cuti")`
- **Komponen Form Modal Drawer (`leave_modal.html`):**
  - Tab Form vs Info:
    - Tab "Form Cuti"
    - Tab "Info Cuti"
  - Dropdown Tipe Cuti:
    - Combobox: `div[role="combobox"]` / `Pilih Tipe Cuti` / `Pilih Tipe Absen`
  - Pemilih Rentang Tanggal (Date Range):
    - Kontainer rentang tanggal `p:has-text("-")`
  - Catatan Cuti:
    - Textbox: `input[placeholder="Catatan"]`
  - Tombol Aksi Form:
    - Reset: `button:has-text("Reset")`
    - Submit: `button:has-text("Ajukan")`

---

### 7. Halaman Izin Sakit (`/apps/absent/sick`)
- **Header & Riwayat:**
  - Judul: `p:has-text("Halaman Sakit")`
  - Kartu Riwayat: memuat thumbnail surat dokter (`img[alt="Bukti surat sakit"]`), rentang tanggal sakit, dan tanggal pengajuan.
- **Tombol Buka Form:**
  - Button CTA: `button:has-text("Ajukan Sakit")`
- **Komponen Form Modal Drawer (`sick_modal.html`):**
  - Pemilih Tanggal Sakit: Date range picker
  - Upload Surat Sakit:
    - Input file button: `button:has-text("Upload")` (`input[type="file"]`)
  - Tombol Aksi Form:
    - Reset: `button:has-text("Reset")`
    - Submit: `button:has-text("Ajukan")`

---

### 8. Halaman Download Share Report (`/apps/absent/reports/share_report`)
- **Layout & Header:**
  - Title: "Download Report"
  - Back Button: Ikon kembali
- **Form Download Laporan:**
  - Form ID: `#form-share-report`
  - Label: `label#type_report` ("Pilih tipe report")
  - Select Dropdown: `select[name="share_report"]`
    - Opsi 1: Disabled placeholder ("Pilih tipe report")
    - Opsi 2: `day` (Harian)
    - Opsi 3: `week` (Mingguan)
    - Opsi 4: `month` (Bulanan)
  - Pesan Error Validasi: `p#error-message` ("Tipe report harus dipilih!")
  - Tombol Submit CTA:
    - Button: `button[type="submit"]` / `button#btn-download-report`
    - Label State: "Download Report" (default), "Loading..." (saat generate)
    - SnackBar Notifikasi: "Berhasil download report"

---

### 9. Halaman Daftar Anggota Tim (`/apps/absent/staff`)
- **Header:**
  - Judul: `p:has-text("View Staff")`
  - Tombol Kembali: Ikon chevron left
- **Kontainer List Staf (Infinite Scroll):**
  - Event listener scroll: saat mencapai batas bawah memicu pagination `limit + 10`
  - Komponen Item Anggota Tim:
    - Avatar staf
    - Nama lengkap staf
    - NIK & Divisi
    - Status presensi hari ini
  - Pesan Status: "Loading..." atau "Tidak ada lagi data"
- **Drawer Bottom Sheet "Informasi Cuti Staff":**
  - Header: `p:has-text("Informasi Cuti Staff")`
  - Rincian jatah cuti, cuti bersama, sisa cuti tahunan staf terkait.

---

### 10. Halaman Ubah Kata Sandi (`/apps/absent/change-password`)
- **Header:**
  - Tombol: `button:has-text("Kembali")`
  - Judul: `p:has-text("Change Password")`
  - Subjudul: "Pastikan password baru kamu tidak sama seperti password sebelumnya!."
- **Input Password Baru:**
  - Label: `Password`
  - Textbox: `input[placeholder="********"]` (Field 1)
  - Ikon Toggle Visibility: `button[aria-label="toggle password visibility"]` (Field 1)
  - Helper error text (jika format tidak memenuhi syarat)
- **Input Konfirmasi Password Baru:**
  - Label: `Confrim Password` (typo pada UI sistem Hadir)
  - Textbox: `input[placeholder="********"]` (Field 2)
  - Ikon Toggle Visibility: `button[aria-label="toggle password visibility"]` (Field 2)
  - Helper error text (jika tidak cocok)
- **Tombol Submit CTA:**
  - Button: `button:has-text("Confirm New Password")` (Class: `MuiButton-contained`)

---

## Ringkasan Lokasi Berkas Komponen

Seluruh berkas HTML render statis dan chunk JavaScript Next.js yang menjadi bahan baku automasi uji disimpan secara lokal pada jalur berikut:

```
src/test/resources/static-web/
└── apps/
    └── absent/
        ├── index.html                  # 1. Home Presensi
        ├── activity.html               # 2. Riwayat Presensi
        ├── correction.html             # 3. Halaman Koreksi
        ├── correction_modal.html       # 3b. Modal Form Koreksi
        ├── permit.html                 # 4. Halaman Izin
        ├── permit_modal.html           # 4b. Modal Form Izin
        ├── overtime-client.html        # 5. Halaman Lembur
        ├── overtime_modal.html         # 5b. Modal Form Lembur
        ├── leave-client.html           # 6. Halaman Cuti
        ├── leave_modal.html            # 6b. Modal Form Cuti
        ├── sick.html                   # 7. Halaman Sakit
        ├── sick_modal.html             # 7b. Modal Form Sakit
        ├── staff.html                  # 9. View Staff
        ├── change-password.html        # 10. Change Password
        └── reports/
            └── share_report.html       # 8. Download Share Report
```

