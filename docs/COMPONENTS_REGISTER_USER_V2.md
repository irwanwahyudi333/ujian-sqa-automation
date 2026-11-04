# Katalog Komponen: Registrasi User v2
**URL Sumber:** `https://magang.dikahadir.com/management/user-v2/register`  
**Tipe Halaman:** Manajemen Administrasi Karyawan (Management Portal)  
**Teknologi Asli:** Next.js, React, Material-UI (MUI v5), Feather Icons  

---

## 📋 Ringkasan Struktur Halaman

Halaman formulir pendaftaran karyawan v2 terdiri dari 3 blok seksi utama, ditambah area navigasi header dan tombol aksi form:

1. **Header & Notifikasi:** Judul halaman (*Registrasi User*), Breadcrumb kembali ke `/management/user`, dan *Snackbar / Alert Banner*.
2. **Seksi 1: Account Information:** Foto karyawan, NIK, Nama Lengkap, Email, Password, serta toggle visibilitas sandi.
3. **Seksi 2: Work Information:** Divisi, Unit, Posisi Kerja, Jabatan, Atasan Langsung, Atasan Client V2, Atasan Client V3, dan Tipe Kontrak.
4. **Seksi 3: Pengaturan Absensi:** Lokasi Kerja, Tipe Shift, Jadwal Kerja, Pengaturan Wajib Selfie, dan Jumlah Cuti.
5. **Aksi Formulir:** Tombol Submit pendaftaran dan Batal.

---

## 🧩 Rincian Komponen & Spesifikasi Elemen

### 1. Header & Notification

| Komponen | Tag Elemen | Atribut ID / Kelas | Deskripsi / Nilai Default |
| :--- | :--- | :--- | :--- |
| **Page Title** | `h4` / `h5` | `.page-title` | `"Registrasi User"` |
| **Breadcrumb Back** | `a` / `button` | `#btn-back` | Navigasi kembali ke `/management/user` |
| **Alert Snackbar** | `div` | `#snackbar-alert`, `role="alert"` | Muncul saat validasi gagal / registrasi berhasil |
| **Alert Message** | `span` | `#snackbar-message` | Pesan feedback (misal: `"Foto tidak boleh kosong"`, `"Berhasil mendaftarkan user"`) |
| **Alert Close Btn** | `button` | `#snackbar-close` | Tombol `x` untuk menutup notifikasi |

---

### 2. Seksi 1: Account Information

Header Seksi: Berlatar `primary.light`, teks `primary.main`, memuat ikon `alert-circle` dan judul `"Account Information"`.

| Label Form | Tag Elemen | ID | Name | Type | Placeholder / Keterangan | Validasi / Aturan |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Upload Foto Karyawan** | `input` | `#logo` / `#photo-upload` | `logo` | `file` | Accept `image/*` | Wajib diisi, ukuran maksimal `< 2 MB` |
| **Upload Button** | `label` / `button` | `#btn-upload-photo` | - | `button` | `"Upload"` | Memicu file picker |
| **Foto Hint Text** | `p` / `span` | `.file-hint` | - | - | `"* File harus dibawah 2 MB"` | Teks panduan upload foto |
| **Foto Preview** | `img` | `#photo-preview` | - | - | Thumbnail gambar yang dipilih | Tampil setelah file dipilih |
| **NIK** | `input` | `#nik` | `nik` | `text` | `"NIK"` | Wajib diisi, 8 karakter alfanumerik |
| **Nama Karyawan** | `input` | `#fullname` | `fullname` | `text` | `"Nama Karyawan"` | Wajib diisi, teks nama lengkap |
| **Email** | `input` | `#email` | `email` | `email` | `"Email"` | Wajib diisi, format email valid |
| **Password** | `input` | `#password` | `password` | `password` | `"Password"` | Wajib diisi, mendukung masking sandi |
| **Toggle Password** | `button` | `#toggle-password` | - | `button` | Ikon `eye` / `eye-off` | Menampilkan atau menyembunyikan sandi |

---

### 3. Seksi 2: Work Information

Header Seksi: Berlatar `primary.light`, teks `primary.main`, memuat ikon `alert-circle` dan judul `"Work Information"`.

| Label Form | Tag Elemen | ID | Name / Key | Tipe Kontrol | Placeholder / Pilihan | Keterangan Relasi |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Divisi** | `input` + `div` | `#divisi` | `division_id` | Autocomplete / Dropdown | `"Pilih Divisi"` | Memuat daftar divisi perusahaan |
| **Unit** | `input` + `div` | `#unit` | `job_departement_id` | Autocomplete / Dropdown | `"Pilih Unit"` | Memuat daftar departemen / unit |
| **Posisi Kerja** | `input` + `div` | `#posisi-kerja` | `job_position_id` | Autocomplete / Dropdown | `"Pilih Posisi Kerja"` | Memuat daftar role / posisi pekerjaan |
| **Jabatan** | `input` + `div` | `#jabatan` | `job_level_id` | Autocomplete / Dropdown | `"Pilih Jabatan"` | Menentukan level hierarki karyawan |
| **Atasan** | `input` + `div` | `#atasan` | `upliner_id` | Autocomplete / Dropdown | `"Cari Atasan (optional)"` | *Disabled* jika jabatan belum dipilih (`"Pilih Jabatan Dulu"`) |
| **Atasan Client V2** | `input` + `div` | `#atasan-v2` | `upliner2_id` | Autocomplete / Dropdown | `"Pilih Atasan Client V2 (Optional)"` | Opsional, atasan tingkat klien kedua |
| **Atasan Client V3** | `input` + `div` | `#atasan-v3` | `upliner3_id` | Autocomplete / Dropdown | `"Pilih Atasan Client V3 (Optional)"` | Opsional, atasan tingkat klien ketiga |
| **Tipe Kontrak** | `input` + `div` | `#tipe-kontrak` | `employee_type_id` | Autocomplete / Dropdown | `"Pilih Tipe Kontrak"` | PKWT, PKWTT, Magang, Freelance |

---

### 4. Seksi 3: Pengaturan Absensi

Header Seksi: Berlatar `primary.light`, teks `primary.main`, memuat ikon `alert-circle` dan judul `"Pengaturan Absensi"`.

| Label Form | Tag Elemen | ID | Name / Key | Tipe Kontrol | Opsi Nilai | Placeholder / Keterangan |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Lokasi Kerja** | `input` + `div` | `#lokasi-kerja` | `location_point_id` | Autocomplete / Dropdown | ID Lokasi Kantor | `"Pilih Lokasi Kerja"` |
| **Tipe Shift** | `select` | `#shift_type` | `shift_type` | Select Picker | `SHIFT` ("Shift")<br>`NON_SHIFT` ("Non Shift") | Default: `"NON_SHIFT"` |
| **Jadwal Kerja** | `input` + `div` | `#jadwal-kerja` | `schedule_id` | Autocomplete / Dropdown | ID Jadwal Kerja | `"Pilih Jadwal Kerja"` |
| **Selfie** | `select` | `#required_selfie` | `required_selfie` | Select Picker | `true` ("Selfie")<br>`false` ("No Selfie") | Penegakan wajib selfie presensi |
| **Jumlah Cuti** | `input` | `#jumlah-cuti` | `title` / `leave_amount` | Number Input | Angka `0 - 99` | Default: `0`, inputProps: max 2 digit |

---

### 5. Seksi 4: Aksi Formulir

| Komponen | Tag Elemen | ID | Tipe | Teks / Icon | Perilaku |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Tombol Submit** | `button` | `#submit` | `submit` | `"Submit"` | Memvalidasi formulir, mengunggah foto ke `/upload`, lalu mengirim payload ke `/api/v2/users` |
| **Tombol Batal** | `a` / `button` | `#btn-cancel` | `button` | `"Batal"` | Membatalkan input dan mengarahkan kembali ke `/management/user` |

---

## 🚀 Panduan Otomasi Pengujian (Selenium WebDriver Locators)

Berikut adalah ringkasan locator Java Selenium yang direkomendasikan untuk implementasi Page Object Model (`RegisterV2Page.java`):

```java
// Seksi 1: Account Information
By photoInput      = By.cssSelector("input[name='logo']");
By photoUploadBtn  = By.id("btn-upload-photo");
By nikInput        = By.id("nik");
By fullnameInput   = By.id("fullname");
By emailInput      = By.id("email");
By passwordInput   = By.id("password");
By passwordToggle  = By.id("toggle-password");

// Seksi 2: Work Information
By divisiInput     = By.id("divisi");
By unitInput       = By.id("unit");
By posisiKerjaInput= By.id("posisi-kerja");
By jabatanInput    = By.id("jabatan");
By atasanInput     = By.id("atasan");
By atasanV2Input   = By.id("atasan-v2");
By atasanV3Input   = By.id("atasan-v3");
By tipeKontrakInput= By.id("tipe-kontrak");

// Seksi 3: Pengaturan Absensi
By lokasiKerjaInput= By.id("lokasi-kerja");
By shiftTypeSelect = By.id("shift_type");
By jadwalKerjaInput= By.id("jadwal-kerja");
By selfieSelect    = By.id("required_selfie");
By jumlahCutiInput = By.id("jumlah-cuti");

// Seksi Aksi & Feedback
By submitButton    = By.id("submit");
By cancelButton    = By.id("btn-cancel");
By snackbarAlert   = By.id("snackbar-alert");
By snackbarMessage = By.id("snackbar-message");
```
