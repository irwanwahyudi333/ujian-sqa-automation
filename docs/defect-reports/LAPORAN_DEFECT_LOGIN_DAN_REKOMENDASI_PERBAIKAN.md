# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Login, Pendaftaran, Ganti Password & Integritas Backend (Allure Report)

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Login, Pendaftaran Magang, Ganti Password (Client Mobile), & Integritas Otorisasi Backend API  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 34 Skenario Unik (26 Skenario UI Selenium WebDriver & 8 Skenario API REST Assured)  
**Hasil:** 29 Passed (85.3%), 5 Failed (Defects Detected Murni dari REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (Critical Security & Business Logic Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) yang terekam pada **Allure Report**, pengujian mencakup verifikasi menyeluruh pada alur autentikasi pengguna serta integritas keamanan backend aplikasi HADIR.

Hasil pengujian menunjukkan kontras yang jelas antara antarmuka pengguna (*front-end*) dan logika server (*back-end*):
1. **Pengujian UI Selenium WebDriver (26 Skenario): 100% Lulus (26 Passed / 0 Failed)**  
   Seluruh alur antarmuka pada modul **Login** (14 skenario), **Pendaftaran Magang** (7 skenario), dan **Ganti Password** (5 skenario) berfungsi secara konsisten, stabil, dan memenuhi seluruh kriteria penerimaan pengguna (*User Acceptance Criteria*). Seluruh elemen input, penyamaran (*masking*) kata sandi, validasi format NIK 16-digit, filter berkas foto, penanganan status pending/suspended, serta navigasi berjalan tanpa kendala visual.
2. **Pengujian Backend API REST Assured (8 Skenario): 3 Passed, 5 Failed (Defects Detected)**  
   Seluruh **5 kegagalan pada pengujian backend murni berasal dari kegagalan asersi (*assertion failure*) terhadap kerentanan sistem nyata**, bukan karena masalah script pengujian otomasi. REST Assured berhasil mendeteksi celah keamanan kritis yang melanggar standar industri OWASP API Security Top 10 dan CWE.

Temuan cacat sistem dikelompokkan ke dalam 3 kategori utama:
1. **Celah Keamanan Otorisasi & Akses Data (OWASP API1 & API5):** Eskalasi hak akses admin mandiri dan kebocoran basis data seluruh staf.
2. **Celah Logika Bisnis & Validasi Objek (OWASP API3 / CWE-915):** *Mass assignment* yang memungkinkan akun aktif otomatis tanpa persetujuan (*approval*) admin.
3. **Standar Keamanan Sesi & Ketahanan Kontrak REST API (CWE-613 & Robustness):** Masa aktif token JWT kedaluwarsa 1.000 hari dan *unhandled server crash (HTTP 500)* pada endpoint unggah berkas.

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-DEF-001** | `PATCH /users/{id}` | Privilege Escalation (OWASP API5) | 🔴 **Critical** | Pengguna biasa berhasil mengubah rolenya menjadi **admin** (`HTTP 200 OK`) | Ditolak dengan `HTTP 403 Forbidden` |
| **SEC-DEF-002** | `GET /users` | BOLA / Data Leakage (OWASP API1) | 🔴 **Critical** | Pengguna biasa dapat melakukan dump seluruh database pengguna (`HTTP 200 OK`) | Ditolak dengan `HTTP 403 Forbidden` |
| **SEC-DEF-003** | `POST /users` | Mass Assignment (OWASP API3) | 🔴 **Critical** | Menerima injeksi `{"isVerified": true}`, akun langsung aktif tanpa approval | Injeksi diabaikan/ditolak; akun wajib berstatus `isVerified: false` |
| **SEC-DEF-004** | Auth JWT (`accessToken`) | Session Expiration (CWE-613) | 🔴 **Critical** | Masa aktif JWT adalah **1.000 hari** (86.400.000 detik) | Masa aktif token akses maksimal 15–30 menit |
| **API-DEF-001** | `POST /upload` | Unhandled Server Crash | 🔴 **Major** | Server mengembalikan `HTTP 500 Internal Server Error` saat format berkas ditolak | Ditangani secara aman dengan `HTTP 400 Bad Request` atau `HTTP 415` |

---

### Matriks Hasil Pengujian Antarmuka UI (Selenium WebDriver)

| Modul Pengujian | Jumlah Skenario | Passed | Failed | Status | Catatan Evaluasi UI |
|---|:---:|:---:|:---:|:---:|---|
| **Modul Login** | 14 | 14 | 0 | 🟢 **100% Lulus** | Form render, toggle password, validasi email/password, happy path, pesan error generik, Firefox cross-browser lolos sempurna. |
| **Modul Pendaftaran Magang** | 7 | 7 | 0 | 🟢 **100% Lulus** | Form register, toggle password, validasi NIK 16 digit, filter ekstensi file foto, validasi field wajib, happy path registrasi stabil. |
| **Modul Ganti Password** | 5 | 5 | 0 | 🟢 **100% Lulus** | Form render, toggle password baru & konfirmasi, validasi password tidak cocok (*mismatch*), validasi submit kosong lolos sempurna. |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. SEC-DEF-001: Eskalasi Hak Akses Mandiri ke Admin (*Privilege Escalation*)
- **Kode Skenario / Tag Allure:** `@approval-integrity @SEC-DEF-001` pada `03_approval_integrity_api.feature`
- **Modul / Endpoint:** Backend API — `PATCH /users/{id}`
- **Kategori Masalah:** OWASP API Security Top 10 — API5:2023 Broken Function Level Authorization
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**  
  Pengguna dengan kredensial staf/magang biasa dapat mengirimkan permintaan HTTP `PATCH /users/{id}` untuk profil dirinya sendiri dengan menyisipkan payload JSON `{"role_id": "admin"}` atau `{"role": "ADMIN"}`. Server memproses pembaruan data tersebut dan merespons dengan status `HTTP 200 OK`. Akibatnya, hak akses pengguna langsung bertransisi menjadi administrator sistem.
- **Dampak Keamanan & Bisnis:**  
  Pengguna tanpa hak istimewa dapat mengambil alih kontrol seluruh sistem HADIR, mengakses data rahasia seluruh instansi, menyetujui absensi fiktif secara sepihak, hingga menghapus rekaman log audit perusahaan.
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: CRITICAL DEFECT TERDETEKSI: Pengguna biasa berhasil eskalasi role menjadi admin! 
  Response code: 200 OK. expected [403] but found [200]
  at org.testng.Assert.fail(Assert.java:111)
  at com.company.sqa.api.steps.ApprovalIntegritySteps.sistemHarusMenolakPerubahanRoleDenganStatus403(ApprovalIntegritySteps.java:68)
  at ✽.Sistem backend harus menolak perubahan role dengan status 403 Forbidden(file:///.../03_approval_integrity_api.feature:46)
  ```
- **Akar Masalah (*Root Cause*):**  
  Controller pembaruan profil pengguna tidak menyaring atribut sensitif (*lack of field filtering*) dan tidak memvalidasi wewenang peran pemohon saat memodifikasi field `role_id` / `role`.
- **Rekomendasi Perbaikan Teknis:**  
  1. Hapus atribut `role` / `role_id` dari daftar field yang dapat dimodifikasi secara mandiri oleh pengguna (*self-service profile update*).
  2. Lindungi modifikasi peran dengan middleware otorisasi berbasis RBAC (*Role-Based Access Control*):
  ```javascript
  // Contoh pencegahan pada User Controller / Service:
  if (req.body.role_id || req.body.role) {
    if (!req.user || req.user.role !== 'SUPERADMIN') {
      return res.status(403).json({
        status: false,
        message: "Akses ditolak: Anda tidak memiliki wewenang untuk mengubah peran pengguna."
      });
    }
  }
  ```

---

### 2. SEC-DEF-002: Kebocoran Data Seluruh Pengguna (*BOLA / Mass Data Leakage*)
- **Kode Skenario / Tag Allure:** `@approval-integrity @SEC-DEF-002` pada `03_approval_integrity_api.feature`
- **Modul / Endpoint:** Backend API — `GET /users`
- **Kategori Masalah:** OWASP API Security Top 10 — API1:2023 Broken Object Level Authorization (BOLA)
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**  
  Endpoint `GET /users` yang mengembalikan daftar seluruh entitas pengguna di dalam basis data dapat diakses secara bebas oleh token pengguna dengan peran staf biasa. Backend tidak melakukan pengecekan hak istimewa peran sebelum mengembalikan seluruh data pengguna.
- **Dampak Keamanan & Bisnis:**  
  Terjadinya pelanggaran berat kepatuhan perlindungan privasi data pribadi (UU Perlindungan Data Pribadi / GDPR). Penyerang dapat melakukan ekstraksi massal (*data scraping*) terhadap NIK, nomor telepon, alamat email, unit divisi, dan status staf di seluruh instansi.
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: CRITICAL DEFECT TERDETEKSI: Endpoint GET /users dapat diakses oleh token pengguna non-admin! 
  Data seluruh karyawan bocor. expected [403] but found [200]
  at org.testng.Assert.fail(Assert.java:111)
  at com.company.sqa.api.steps.ApprovalIntegritySteps.sistemHarusMenolakAksesDaftarPenggunaDenganStatus403(ApprovalIntegritySteps.java:94)
  at ✽.Sistem backend harus menolak akses daftar pengguna dengan status 403 Forbidden(file:///.../03_approval_integrity_api.feature:31)
  ```
- **Akar Masalah (*Root Cause*):**  
  Rute `GET /users` hanya dipasangi middleware verifikasi token umum (*authentication*) tanpa pemeriksaan peran (*role authorization check*).
- **Rekomendasi Perbaikan Teknis:**  
  Pasang filter middleware autentikasi dan peran pada rute `/users`:
  ```javascript
  // Penerapan RBAC Middleware pada Router:
  router.get('/users', authenticateToken, requireRole(['ADMIN', 'SUPERADMIN', 'HRD']), getUsersListController);
  ```
  Untuk kebutuhan antarmuka rekan satu tim di aplikasi mobile, buat endpoint khusus (misal `GET /team/members`) yang hanya menampilkan nama dan posisi divisi tanpa mengekspos NIK, hash password, atau token internal.

---

### 3. SEC-DEF-003: Bypass Persetujuan Akun Baru Melalui Mass Assignment (*Auto-Approval Flaw*)
- **Kode Skenario / Tag Allure:** `@approval-integrity @SEC-DEF-003` pada `03_approval_integrity_api.feature`
- **Modul / Endpoint:** Backend API — `POST /users` (Registrasi Pengguna)
- **Kategori Masalah:** CWE-915: Improperly Controlled Modification of Dynamically-Determined Object Attributes (Mass Assignment)
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**  
  Pada saat pendaftaran akun baru melalui `POST /users`, penyerang menyisipkan atribut `{"isVerified": true}` ke dalam body JSON. Backend memetakan seluruh atribut request body secara mentah (*raw object binding*) langsung ke tabel database tanpa sanitasi DTO (*Data Transfer Object*). Akun yang baru didaftarkan langsung berstatus aktif dan dapat login seketika.
- **Dampak Keamanan & Bisnis:**  
  Prinsip keamanan *Maker-Checker* runtuh. Pendaftar liar atau mantan karyawan yang telah dinonaktifkan dapat mendaftarkan akun baru dan langsung login serta melakukan presensi tanpa perlu diverifikasi dan disetujui oleh admin HRD.
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: CRITICAL DEFECT TERDETEKSI: Backend menerima injeksi 'isVerified: true'! 
  Akun otomatis aktif dan dapat login langsung tanpa approval admin! expected [false] but found [true]
  at org.testng.Assert.fail(Assert.java:111)
  at com.company.sqa.api.steps.ApprovalIntegritySteps.akunBaruHarusTetapBerstatusBelumAktif(ApprovalIntegritySteps.java:125)
  at ✽.Akun baru harus tetap berstatus isVerified false sebelum disetujui admin(file:///.../03_approval_integrity_api.feature:26)
  ```
- **Akar Masalah (*Root Cause*):**  
  Model database meng-assign langsung `req.body` (misal: `UserModel.create(req.body)`) tanpa melakukan whitelist field yang diizinkan untuk registrasi publik.
- **Rekomendasi Perbaikan Teknis:**  
  Terapkan skema sanitasi data ketat (menggunakan DTO / library validator seperti Joi, Zod, atau class-validator) dan tentukan status awal akun secara pasti (*hardcoded*):
  ```typescript
  // DTO Whitelisting pada Controller Registrasi:
  const { nik, fullname, email, password, photo } = req.body;
  
  const newUser = await UserModel.create({
    nik,
    fullname,
    email,
    password: hashedPassword,
    photo,
    isVerified: false, // Wajib bernilai FALSE secara mutlak
    role_id: 'STAFF'   // Peran default staf biasa
  });
  ```

---

### 4. SEC-DEF-004: Masa Aktif Token Sesi Terlalu Panjang (CWE-613)
- **Kode Skenario / Tag Allure:** `@approval-integrity @SEC-DEF-004` pada `03_approval_integrity_api.feature`
- **Modul / Endpoint:** Backend API — Autentikasi JWT (`POST /login` $\rightarrow$ `accessToken`)
- **Kategori Masalah:** CWE-613: Insufficient Session Expiration
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**  
  Klaim kedaluwarsa (`exp`) pada token JWT `accessToken` yang diterbitkan oleh sistem disetel kedaluwarsa setelah 86.400.000 detik, yang setara dengan **1.000 hari (~2,7 tahun)**.
- **Dampak Keamanan & Bisnis:**  
  Melanggar standar industri keamanan sesi perbankan dan enterprise. Token yang tersadap melalui jaringan publik (*man-in-the-middle*), peramban bersama (*shared computer*), atau rekaman log proxy dapat digunakan oleh penyerang dalam jangka waktu tahunan tanpa dapat dicabut (*revoked*).
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: SECURITY DEFECT TERDETEKSI: Masa berlaku JWT accessToken adalah 1000.0 hari (86400000 detik), 
  jauh melampaui standar keamanan industri! expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at com.company.sqa.api.steps.ApprovalIntegritySteps.masaBerlakuTokenHarusSesuaiStandarKeamanan(ApprovalIntegritySteps.java:152)
  at ✽.Durasi kedaluwarsa token JWT tidak boleh melebihi batas maksimal 86400 detik(file:///.../03_approval_integrity_api.feature:36)
  ```
- **Akar Masalah (*Root Cause*):**  
  Konfigurasi parameter masa berlaku token pada fungsi penandatanganan JWT menggunakan nilai statis berlebihan (`expiresIn: 86400000`).
- **Rekomendasi Perbaikan Teknis:**  
  Terapkan arsitektur *Dual-Token*:
  1. Pangkas masa berlaku `accessToken` menjadi maksimal **15–30 menit**:
  ```javascript
  const accessToken = jwt.sign(userPayload, process.env.JWT_SECRET, { expiresIn: '30m' });
  ```
  2. Terbitkan `refreshToken` dengan masa berlaku 7 hari yang disimpan secara aman di dalam cookie browser dengan flag `HttpOnly, Secure, SameSite=Strict`.
  3. Sediakan mekanisme *Token Revocation List* di Redis saat pengguna menekan tombol Logout.

---

### 5. API-DEF-001: Server Melempar Unhandled Exception HTTP 500 pada Endpoint Unggah Berkas
- **Kode Skenario / Tag Allure:** `@approval-integrity @API-DEF-001` pada `03_approval_integrity_api.feature`
- **Modul / Endpoint:** Backend API — `POST /upload`
- **Kategori Masalah:** Ketidakpatuhan Kontrak REST API & Ketahanan Sistem (*API Contract & Robustness*)
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**  
  Ketika klien mengirimkan request unggah berkas dengan ekstensi/tipe MIME terlarang (misalnya berkas skrip HTML `.html` atau teks `.txt`), middleware unggah backend melemparkan *unhandled exception* yang tidak tertangkap, sehingga server merespons dengan kode status `HTTP 500 Internal Server Error`.
- **Dampak Keamanan & Stabilitas:**  
  Respon HTTP 500 menandakan kegagalan *runtime* pada proses backend, yang berpotensi membocorkan struktur direktori (*stack trace leakage*) dan membuat server rentan terhadap serangan penolakan layanan (*Denial of Service*).
- **Hasil Asersi REST Assured (Allure Log / Error Stack Trace):**
  ```text
  java.lang.AssertionError: API CONTRACT DEFECT TERDETEKSI: Backend mengembalikan HTTP 500 Internal Server Error 
  untuk penolakan format berkas, seharusnya HTTP 400/415! expected [400] but found [500]
  at org.testng.Assert.fail(Assert.java:111)
  at com.company.sqa.api.steps.ApprovalIntegritySteps.serverHarusMenolakFormatBerkasSecaraElegan(ApprovalIntegritySteps.java:181)
  at ✽.Response API mengembalikan status kontrak kesalahan klien 400 atau 415 dan bukan 500(file:///.../03_approval_integrity_api.feature:41)
  ```
- **Akar Masalah (*Root Cause*):**  
  Middleware penanganan unggah berkas (Multer) melempar error tak tertangani (*uncaught error*) ke global dispatcher saat tipe file tidak cocok, yang diterjemahkan server default sebagai crash HTTP 500.
- **Rekomendasi Perbaikan Teknis:**  
  Pasang penanganan kesalahan terpusat (*Global Exception Filter*) dan filter tipe berkas multer yang mengembalikan kode status HTTP standar:
  ```javascript
  // Filter berkas pada Middleware Multer:
  const fileFilter = (req, file, cb) => {
    const allowedMimes = ['image/jpeg', 'image/png', 'image/jpg'];
    if (!allowedMimes.includes(file.mimetype)) {
      const error = new Error("Format berkas tidak didukung. Harap unggah berkas gambar JPG/PNG.");
      error.statusCode = 415; // Unsupported Media Type
      return cb(error, false);
    }
    cb(null, true);
  };
  
  // Tangani error pada Global Error Middleware:
  app.use((err, req, res, next) => {
    const status = err.statusCode || 400;
    res.status(status).json({
      status: false,
      message: err.message || "Permintaan tidak valid."
    });
  });
  ```

---

## 4. Kesimpulan & Langkah Tindak Lanjut

1. **Integritas Hasil Pengujian SQA:**
   - Seluruh 26 skenario UI Selenium WebDriver berhasil lulus 100% tanpa satu pun kegagalan visual, tata letak, maupun interaksi input.
   - Sebanyak 5 kegagalan pada pengujian backend REST Assured adalah **cacat sistem nyata (*pure defects*) bernilai tinggi**, bukan cacat skrip pengujian otomasi.
2. **Prioritas Remediasi untuk Tim Pengembang (Dev Team):**
   - **P1 (Mendesak / Critical):** Perbaiki `SEC-DEF-001` (Eskalasi Role Admin), `SEC-DEF-002` (Kebocoran DB User), dan `SEC-DEF-003` (Bypass Verifikasi Akun).
   - **P2 (Penting / High):** Terapkan masa aktif token `SEC-DEF-004` maksimal 30 menit dengan skema *Dual-Token*.
   - **P3 (Penyempurnaan / Medium):** Tangani *exception handling* `API-DEF-001` agar penolakan berkas mengembalikan `HTTP 400/415` alih-alih melempar status `500`.
3. **Laporan Interaktif Allure:**  
   Laporan visual grafik, diagram alur, dan rekaman *log request/response* dapat diakses langsung menggunakan Allure CLI:
   ```bash
   .allure/allure-2.29.0/bin/allure open target/allure-report
   ```
   *atau untuk melakukan serve dinamis:*
   ```bash
   .allure/allure-2.29.0/bin/allure serve target/allure-results
   ```

