# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror  
**Tanggal Pengujian:** 02 November 2026  
**Total Skenario Dijalankan:** 350 Skenario  
**Hasil:** 333 Passed (95.1%), 17 Failed (Defects Detected)  
**Tingkat Kritis:** 🔴 **Tinggi (Critical Security & Business Logic Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian komprehensif (Hybrid UI Selenium WebDriver & REST Assured API Testing), seluruh **17 kegagalan pengujian (*failures*) bukan disebabkan oleh kerusakan script otomasi**, melainkan merupakan **bukti temuan cacat sistem (*real defects*) dan kerentanan keamanan nyata** pada aplikasi target.

Temuan ini dikelompokkan ke dalam 4 kategori utama:
1. **Celah Keamanan Kritis Backend (OWASP API Security Top 10)**
2. **Celah Logika Bisnis Pendaftaran (Business Logic Flaws)**
3. **Ketidakpatuhan Kontrak REST API & Penanganan Error (API Contract & Error Handling)**
4. **Cacat Antarmuka Pengguna & Navigasi Riwayat Browser (UI / UX State Management)**

---

## 2. Matriks Temuan Cacat (Defect Matrix)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-DEF-001** | `PATCH /users/{id}` | Privilege Escalation (OWASP API5) | 🔴 **Critical** | Pengguna biasa berhasil mengubah rolenya menjadi **admin** (`HTTP 200 OK`) | Ditolak dengan `HTTP 403 Forbidden` |
| **SEC-DEF-002** | `GET /users` | BOLA / Data Leakage (OWASP API1) | 🔴 **Critical** | Pengguna biasa dapat melakukan dump seluruh database pengguna (`HTTP 200 OK`) | Ditolak dengan `HTTP 403 Forbidden` |
| **SEC-DEF-003** | `POST /users` | Mass Assignment (OWASP API3) | 🔴 **Critical** | Menerima injeksi `{"isVerified": true}`, akun langsung aktif tanpa approval | Injeksi diabaikan/ditolak; akun wajib `isVerified: false` |
| **SEC-DEF-004** | Auth JWT (`exp`) | Session Expiration (CWE-613) | 🔴 **Critical** | Masa aktif JWT adalah **1.000 hari** (86.400.000 detik) | Masa aktif akses token maksimal 15–60 menit |
| **BIZ-DEF-001** | `POST /users` | Mandatory Biometrics Bypass | 🔴 **Major** | Pendaftaran tanpa foto selfie diloloskan dengan `HTTP 201 Created` | Ditolak dengan `HTTP 400 Bad Request` |
| **API-DEF-001** | `POST /upload` & `POST /users` | Unhandled Server Crash | 🔴 **Major** | Server mengembalikan `HTTP 500 Internal Server Error` untuk berkas/input salah | Dikendalikan dengan `HTTP 400` atau `HTTP 415` |
| **API-DEF-002** | `POST /login` | Generic Error & Contract Violation | 🟡 **Medium** | Server mengembalikan `HTTP 401 Unauthorized` untuk segala tipe invalid payload | Validasi format mengembalikan `HTTP 400 Bad Request` |
| **UI-DEF-001** | `/absen/register` | Browser History Navigation State | 🟡 **Medium** | Tekan tombol *Back* browser menghilangkan input NIK / blank state | Form state tetap mempertahankan elemen input |
| **UI-DEF-002** | `/apps/absent/change-password` | Visual Typo Label | 🟢 **Minor** | Label input tertulis `"Confrim Password"` | Diperbaiki menjadi `"Confirm Password"` |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. SEC-DEF-001: Eskalasi Hak Akses ke Admin (*Privilege Escalation*)
- **Deskripsi:** Pengguna dengan level otorisasi staf/magang biasa dapat mengirimkan request `PATCH` ke `/users/{id}` miliknya sendiri dengan menyisipkan properti `{"role_id": "admin"}`. Server merespons `200 OK` dan memperbarui peran pengguna tersebut menjadi admin.
- **Dampak Keamanan:** Siapapun dapat mengambil alih hak kendali sistem, mengubah jadwal kerja, menyetujui absensi fiktif, hingga menghapus data.
- **Rekomendasi Perbaikan:**
  ```javascript
  // Contoh pencegahan di Backend Controller / Service:
  if (req.body.role_id && req.user.role !== 'SUPERADMIN') {
    return res.status(403).json({
      status: false,
      message: "Akses ditolak: Anda tidak memiliki wewenang untuk mengubah peran pengguna."
    });
  }
  ```
  - Pisahkan endpoint manajemen peran ke rute khusus admin (misal `/admin/users/{id}/role`).
  - Hapus field `role_id` dari whitelist payload modifikasi profil mandiri.

---

### 2. SEC-DEF-002: Kebocoran Data Seluruh Pengguna (*BOLA / Data Dumping*)
- **Deskripsi:** Endpoint `GET /users` yang mengembalikan daftar seluruh pengguna di database dapat diakses oleh token pengguna biasa tanpa pembatasan peran.
- **Dampak Keamanan:** Terjadi pelanggaran privasi data (GDPR / UU PDP). Penyerang dapat menyedot NIK, nomor telepon, alamat email, dan identitas seluruh staf perusahaan.
- **Rekomendasi Perbaikan:**
  - Pasang middleware otorisasi berbasis *Role-Based Access Control* (RBAC) pada endpoint `GET /users`:
  ```javascript
  router.get('/users', authenticateToken, requireRole(['ADMIN', 'HRD']), getUsersController);
  ```
  - Untuk kebutuhan direktori kontak tim di level mobile client, sediakan endpoint terpisah (misal `/teams/members`) yang hanya menampilkan profil rekan satu divisi dan menyaring data sensitif (tanpa NIK lengkap, password hash, atau token internal).

---

### 3. SEC-DEF-003: Bypass Verifikasi Akun (*Mass Assignment*)
- **Deskripsi:** Saat pendaftaran akun baru melalui `POST /users`, klien dapat menyisipkan properti `{"isVerified": true}`. Backend memetakan seluruh properti JSON body langsung ke database (*mass assignment*).
- **Dampak Keamanan:** Pendaftar ilegal dapat langsung login dan absen tanpa perlu melalui proses peninjauan dan persetujuan (*approval*) oleh admin HRD.
- **Rekomendasi Perbaikan:**
  - Terapkan DTO (*Data Transfer Object*) atau skema sanitasi data (misal menggunakan Joi, Zod, atau class-validator) yang secara eksplisit hanya mengekstrak field pendaftaran yang diizinkan:
  ```typescript
  // Hanya ambil field yang sah
  const { nik, fullname, email, password, photo } = req.body;
  // Tetapkan status approval secara hardcoded
  const newUser = await UserModel.create({
    nik, fullname, email, password: hashedPassword, photo,
    isVerified: false, // Nilai default wajib false
    role_id: 'STAFF'
  });
  ```

---

### 4. SEC-DEF-004: Masa Aktif Token JWT Tidak Masuk Akal (CWE-613)
- **Deskripsi:** Claim `exp` pada JWT `accessToken` disetel kedaluwarsa setelah 86.400.000 detik (~1.000 hari).
- **Dampak Keamanan:** Pelanggaran standar industri keamanan sesi. Jika token tersadap di jaringan publik atau perangkat bersama, penyerang memiliki akses tidak terbatas selama 3 tahun.
- **Rekomendasi Perbaikan:**
  - Ubah masa berlaku `accessToken` menjadi maksimal **15–60 menit**:
  ```javascript
  const accessToken = jwt.sign(payload, SECRET_KEY, { expiresIn: '30m' });
  ```
  - Gunakan arsitektur *Dual Token*:
    - **Access Token:** Masa hidup pendek (15–30 menit), digunakan untuk otorisasi API harian.
    - **Refresh Token:** Masa hidup 7 hari, disimpan pada `httpOnly, Secure, SameSite=Strict` Cookie untuk memperbarui Access Token baru.
  - Sediakan mekanisme *Token Blacklist* di Redis untuk menghanguskan token saat pengguna menekan tombol Logout.

---

### 5. BIZ-DEF-001: Pendaftaran Lolos Tanpa Foto Selfie (*Mandatory Selfie Bypass*)
- **Deskripsi:** Backend meloloskan pendaftaran tanpa berkas foto selfie (`photo: null`) dengan mengembalikan status `HTTP 201 Created`.
- **Dampak Bisnis:** Fitur verifikasi wajah dan kehadiran berbasis biometrik HADIR kehilangan data dasar foto karyawan, sehingga sistem presensi foto tidak dapat memvalidasi identitas asli di kemudian hari.
- **Rekomendasi Perbaikan:**
  - Tambahkan validasi mutlak di controller pendaftaran:
  ```javascript
  if (!req.file && !req.body.photo) {
    return res.status(400).json({
      status: false,
      message: "Upload foto selfie wajib diisi untuk pendaftaran akun."
    });
  }
  ```

---

### 6. API-DEF-001: Server Melempar Unhandled Exception (*HTTP 500*)
- **Deskripsi:** Saat klien mengunggah file non-gambar (misal `.txt`, `.pdf`) atau memasukkan data NIK dengan karakter sangat panjang ekstrem, backend melempar `HTTP 500 Internal Server Error`.
- **Dampak Keamanan & Stabilitas:** Indikasi adanya *unhandled exception* (seperti `NullPointerException`, `IndexOutOfBoundsException`, atau kegagalan parsing multipart). Pada kondisi tertentu, ini dapat dimanfaatkan untuk serangan *Denial of Service* (DoS).
- **Rekomendasi Perbaikan:**
  - Pasang interceptor validasi berkas pada layer multer/upload:
  ```javascript
  const fileFilter = (req, file, cb) => {
    if (!file.mimetype.startsWith('image/')) {
      return cb(new CustomValidationError("Format berkas harus berupa gambar (JPG/PNG)."), false);
    }
    cb(null, true);
  };
  ```
  - Pasang *Global Exception Handler* terpusat agar kesalahan klien menghasilkan respon `HTTP 400 Bad Request` atau `HTTP 415 Unsupported Media Type` yang bersih tanpa melempar status 500.

---

### 7. API-DEF-002: Respon Error Login Generik (*Generic HTTP 401*)
- **Deskripsi:** Endpoint `POST /login` mengembalikan `HTTP 401 Unauthorized` untuk seluruh kesalahan, termasuk saat body request kosong (`{}`), field email tidak dikirim, atau payload melebihi ukuran batas.
- **Dampak:** Melanggar standar kontrak RESTful API. Klien antarmuka tidak dapat membedakan antara request yang rusak (*Bad Request*) dan kegagalan autentikasi (*Wrong Password*).
- **Rekomendasi Perbaikan:**
  - Validasi kelengkapan data terlebih dahulu sebelum melakukan verifikasi ke database:
    - Jika format JSON invalid atau field wajib kurang $\rightarrow$ `HTTP 400 Bad Request`.
    - Jika ukuran body melebihi batas $\rightarrow$ `HTTP 413 Payload Too Large`.
    - Jika format Content-Type bukan JSON $\rightarrow$ `HTTP 415 Unsupported Media Type`.
    - HANYA jika kredensial email/password salah $\rightarrow$ `HTTP 401 Unauthorized`.

---

### 8. UI-DEF-001 & UI-DEF-002: Cacat Antarmuka & Navigasi Riwayat Browser
- **Deskripsi UI-DEF-001:** Navigasi tombol *Back* browser pada SPA pendaftaran menyebabkan halaman berpindah ke state kosong (*blank*), sehingga input NIK tidak terlihat.
  - **Perbaikan:** Gunakan event handling router Next.js (`beforePopState`) untuk memastikan state formulir tetap utuh atau diarahkan ke rute fallback yang valid.
- **Deskripsi UI-DEF-002:** Typo teks label pada formulir ganti password: `"Confrim Password"`.
  - **Perbaikan:** Ubah teks label menjadi `"Confirm Password"` pada komponen formulir ganti kata sandi.

---

## 4. Kesimpulan & Langkah Tindak Lanjut

1. **Integritas Hasil Pengujian:** Seluruh 17 kegagalan ini adalah temuan bernilai tinggi yang berhasil dideteksi oleh suite otomasi SQA. Skenario uji otomasi telah bekerja dengan tepat sesuai fungsinya.
2. **Prioritas Remediasi untuk Tim Pengembang (Dev Team):**
   - **P1 (Mendesak):** Perbaiki `SEC-DEF-001` (Eskalasi Admin), `SEC-DEF-002` (Kebocoran DB), dan `SEC-DEF-003` (Bypass Verifikasi).
   - **P2 (Penting):** Sesuaikan masa berlaku JWT `SEC-DEF-004` dan validasi wajib foto selfie `BIZ-DEF-001`.
   - **P3 (Penyempurnaan):** Tangani *exception handling* `API-DEF-001`, standarisasi status code `API-DEF-002`, dan perbaiki typo `UI-DEF-002`.
3. **Laporan Interaktif Allure:** Laporan visual grafik, durasi eksekusi, dan rekaman screenshot pendukung dapat diakses menggunakan perintah:
   ```bash
   allure serve target/allure-results
   ```

