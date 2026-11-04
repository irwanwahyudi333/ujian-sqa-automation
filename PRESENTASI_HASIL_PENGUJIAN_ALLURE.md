---
marp: true
theme: default
paginate: true
header: "SQA Automation Testing — Aplikasi HADIR"
footer: "Laporan Hasil Allure Report | 04 November 2026"
size: 16:9
style: |
  section {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 40px;
    font-size: 20px;
  }
  h1 {
    color: #1a365d;
    font-size: 36px;
  }
  h2 {
    color: #2b6cb0;
    font-size: 28px;
  }
  h3 {
    color: #2d3748;
    font-size: 22px;
  }
  table {
    font-size: 16px;
    width: 100%;
  }
  th {
    background-color: #2b6cb0;
    color: white;
  }
  .highlight-pass {
    color: #22543d;
    background-color: #c6f6d5;
    padding: 2px 6px;
    border-radius: 4px;
    font-weight: bold;
  }
  .highlight-fail {
    color: #742a2a;
    background-color: #fed7d7;
    padding: 2px 6px;
    border-radius: 4px;
    font-weight: bold;
  }
  code {
    background-color: #edf2f7;
    color: #c53030;
    font-size: 16px;
  }
  pre {
    background-color: #1a202c;
    color: #edf2f7;
    font-size: 14px;
    padding: 10px;
    border-radius: 6px;
  }
---

<!-- Slide 1: Cover Slide -->
# Laporan Hasil Pengujian Otomasi SQA
## Modul Login, Pendaftaran, Ganti Password & Integritas API Backend

**Target Aplikasi:** `https://magang.dikahadir.com` (Live) & Local Mirror  
**Framework:** Hybrid SQA Automation (Selenium WebDriver 4.25 & REST Assured 5.5)  
**Metodologi:** Lean Testing Strategy (Skema 1 - Piramida Uji Hemat)  
**Tanggal Pengujian:** 04 November 2026  
**Penyusun:** Tim SQA Engineer

---

<!-- Slide 2: Agenda -->
# Agenda Presentasi

1. **Ringkasan Eksekutif & Metrik Pengujian**
   - Skor kelulusan, perbandingan UI vs API, dan profil eksekusi Allure.
2. **Cakupan Pengujian UI Selenium WebDriver**
   - Validasi Login, Pendaftaran Magang, dan Ganti Password Client Mobile.
3. **Analisis Temuan Cacat Kritis Backend (REST Assured)**
   - 5 Temuan Cacat Keamanan & Kontrak API (OWASP API Top 10 & CWE).
4. **Rincian Teknis Bukti Cacat & Dampak Bisnis**
   - Rekaman *assertion failure*, *payload injection*, dan respons server.
5. **Rekomendasi Perbaikan Sistem & Roadmap Remediasi**
   - Rekomendasi teknis backend controller, middleware, dan DTO sanitizer.

---

<!-- Slide 3: Executive Summary -->
# Ringkasan Eksekutif Hasil Allure Report

Berdasarkan eksekusi pengujian hybrid otomatis pada 34 skenario unik:

- **Total Skenario Diuji:** 34 Skenario Unik
- **Status Lulus (Passed):** <span class="highlight-pass">29 Skenario (85.3%)</span>
- **Cacat Terdeteksi (Defects):** <span class="highlight-fail">5 Skenario (14.7%)</span>
- **Tingkat Kritis:** 🔴 **Kritis (Broken Authorization, Mass Assignment & Session Expiration)**

> **Catatan Penting SQA:**  
> Seluruh **29 skenario UI Selenium lulus 100%**. Seluruh **5 kegagalan murni berasal dari verifikasi keamanan dan kontrak HTTP REST Assured** pada backend API, membuktikan keberhasilan pengujian dalam menemukan kerentanan nyata (*zero false positives*).

---

<!-- Slide 4: Testing Architecture -->
# Arsitektur Piramida Pengujian Lean (Skema 1)

```
                       /\
                      /  \     UI Testing (Selenium) — 26 Skenario
                     / UI \    • Login (14 TC), Register (7 TC), Ganti Pass (5 TC)
                    /------\   • Fokus: Visual render, form input, masking, Happy Path
                   /        \
                  /   API    \ API Testing (REST Assured) — 8 Skenario
                 /  Testing   \ • Kontrak HTTP, batasan duplikasi, dan security audit
                /--------------\ • 5 Temuan Kerentanan Kritis Teridentifikasi
```

- **Keuntungan Skema 1:**
  - Waktu eksekusi sangat efisien (~5 menit untuk UI, ~50 detik untuk API).
  - Browser tidak dibebani oleh puluhan permutasi data masif.
  - Boundary input dan penetrasi celah keamanan didelegasikan langsung ke level HTTP request.

---

<!-- Slide 5: UI Selenium Results -->
# Hasil Pengujian UI Selenium (26/26 PASSED — 100%)

Semua alur antarmuka visual berfungsi dengan sempurna pada peramban Firefox:

| Modul UI | Skenario Diuji | Status | Cakupan Utama |
|---|:---:|:---:|---|
| **Login** (`/authentication/login`) | 14 TC | <span class="highlight-pass">100% PASS</span> | Render form, toggle mata password, validasi email kosong/salah, proteksi brute-force/pending account, dan UX generic error |
| **Register** (`/absen/register`) | 7 TC | <span class="highlight-pass">100% PASS</span> | Validasi 16-digit NIK, unggah foto selfie valid/invalid, toggle password, Happy Path submit, dan form kosong |
| **Ganti Password** (`/apps/absent/change-password`) | 5 TC | <span class="highlight-pass">100% PASS</span> | Render antarmuka mobile, toggle password baru & konfirmasi, validasi *password mismatch*, dan submit kosong |

---

<!-- Slide 6: API REST Assured Results -->
# Hasil Pengujian API REST Assured (8 Skenario)

Pengujian backend API pada berkas `03_approval_integrity_api.feature`:

| Skenario Pengujian API | Status | Kategori Pengujian |
|---|:---:|---|
| Penolakan NIK Duplikat pada Endpoint Update/Approval | <span class="highlight-pass">PASSED</span> | Data Integrity (400/409) |
| Penolakan Email Duplikat pada Endpoint Update/Approval | <span class="highlight-pass">PASSED</span> | Data Integrity (400/409) |
| Aksesibilitas Berkas Foto Selfie di Static Uploads | <span class="highlight-pass">PASSED</span> | Asset Accessibility (200 OK) |
| Pencegahan Modifikasi Akun Lain & Dumping Seluruh DB | <span class="highlight-fail">FAILED</span> | **BOLA / OWASP API1 (SEC-DEF-002)** |
| Penolakan Injeksi Atribut `isVerified: true` Akun Baru | <span class="highlight-fail">FAILED</span> | **Mass Assignment (SEC-DEF-003)** |
| Penolakan Eskalasi Peran Menjadi Admin via `PATCH` | <span class="highlight-fail">FAILED</span> | **Privilege Escalation (SEC-DEF-001)** |
| Validasi Batas Masa Aktif Token JWT Sesi Autentikasi | <span class="highlight-fail">FAILED</span> | **Session Expiration (SEC-DEF-004)** |
| Kontrak Error 400/415 saat Mengunggah Berkas Ilegal | <span class="highlight-fail">FAILED</span> | **Server Crash 500 (API-DEF-001)** |

---

<!-- Slide 7: Defect Matrix Overview -->
# Matriks 5 Temuan Cacat Kritis Backend HADIR

| ID Cacat | Endpoint Target | Klasifikasi Kerentanan | Severity | Dampak Bisnis |
|---|---|---|:---:|---|
| **SEC-DEF-001** | `PATCH /users/{id}` | Vertical Privilege Escalation (CWE-269) | 🔴 **Critical** | Pengguna biasa dapat mengambil alih hak kendali sistem menjadi Administrator |
| **SEC-DEF-002** | `GET /users` | Broken Object Level Auth / BOLA (OWASP API1) | 🔴 **Critical** | Seluruh basis data karyawan (NIK, Email, Profil) dapat di-dump oleh akun biasa |
| **SEC-DEF-003** | `POST /users` | Mass Assignment Vulnerability (CWE-915) | 🔴 **Critical** | Pendaftar ilegal membypass approval admin dan swafoto biometric |
| **SEC-DEF-004** | Payload JWT `exp` | Insufficient Session Expiration (CWE-613) | 🔴 **Critical** | Masa aktif JWT berlaku selama **1.000 hari**, rentan pembajakan sesi |
| **API-DEF-001** | `POST /upload` | Unhandled Exception / Contract Violation | 🟡 **Major** | Server mengalami crash `HTTP 500` saat validasi format berkas |

---

<!-- Slide 8: Defect Detail 1 (Privilege Escalation) -->
# Cacat #1: Vertical Privilege Escalation (SEC-DEF-001)

- **Endpoint:** `PATCH /users/{id}`
- **Severity:** 🔴 **Critical (CWE-269 / OWASP API5)**
- **Hasil Asersi REST Assured:**
  ```text
  CRITICAL BUG TERDETEKSI: Pengguna biasa berhasil mengubah status menjadi admin 
  via PATCH /users/{id} (HTTP 200, role_id: admin, is_admin: false)!
  ```
- **Akar Masalah:**
  Backend tidak menerapkan verifikasi peran (*authorization guard*) saat memproses mutasi field `role_id` pada pembaruan profil mandiri.
- **Dampak:**
  Akun staf/magang dapat mempromosikan dirinya sendiri menjadi Administrator, memanipulasi absensi, dan mengakses menu kontrol manajerial.

---

<!-- Slide 9: Defect Detail 2 (BOLA Data Dumping) -->
# Cacat #2: BOLA / Data Leakage Database Karyawan (SEC-DEF-002)

- **Endpoint:** `GET /users`
- **Severity:** 🔴 **Critical (OWASP API1 / CWE-639)**
- **Hasil Asersi REST Assured:**
  ```text
  CRITICAL BUG TERDETEKSI: Pengguna biasa dapat melakukan dump seluruh database pengguna 
  via GET /users (HTTP 200 OK)! Seharusnya ditolak 403 Forbidden!
  ```
- **Akar Masalah:**
  Endpoint daftar pengguna internal tidak dibatasi oleh middleware RBAC (*Role-Based Access Control*), sehingga token user level terendah dapat membacanya.
- **Dampak:**
  Pelanggaran kepatuhan privasi data (UU PDP / GDPR). Seluruh data identitas staf perusahaan (NIK, nomor telepon, divisi) bocor ke publik.

---

<!-- Slide 10: Defect Detail 3 (Mass Assignment) -->
# Cacat #3: Mass Assignment Bypass Verifikasi (SEC-DEF-003)

- **Endpoint:** `POST /users`
- **Severity:** 🔴 **Critical (CWE-915 / OWASP API3)**
- **Hasil Asersi REST Assured:**
  ```text
  CRITICAL BUG TERDETEKSI: Backend menerima injeksi 'isVerified: true'! 
  Akun otomatis aktif dan dapat login langsung tanpa approval admin!
  ```
- **Akar Masalah:**
  Pendaftaran akun memetakan objek JSON langsung ke model basis data (*raw object binding*) tanpa penyaringan DTO (*Data Transfer Object*).
- **Dampak:**
  Prinsip keamanan *Maker-Checker* runtuh. Pendaftar gelap dapat langsung aktif dan melakukan presensi tanpa perlu disetujui HRD.

---

<!-- Slide 11: Defect Detail 4 (JWT Expiration) -->
# Cacat #4: Masa Aktif Token Sesi Terlalu Panjang (SEC-DEF-004)

- **Endpoint:** Token JWT Login (`accessToken`)
- **Severity:** 🔴 **Critical (CWE-613)**
- **Hasil Asersi REST Assured:**
  ```text
  SECURITY DEFECT TERDETEKSI: Masa berlaku JWT accessToken adalah 1000.0 hari 
  (86400000 detik), jauh melampaui standar keamanan industri!
  ```
- **Akar Masalah:**
  Konfigurasi signing JWT menyetel masa berlaku `expiresIn: 86400000` (dihitung sebagai detik, bukan milidetik = ~27,3 tahun atau ~1.000 hari).
- **Dampak:**
  Token yang tersadap atau tertinggal di peramban publik tidak dapat di-revoke dan dapat disalahgunakan penyerang dalam jangka waktu bertahun-tahun.

---

<!-- Slide 12: Defect Detail 5 (500 Server Crash) -->
# Cacat #5: API Contract Crash HTTP 500 (API-DEF-001)

- **Endpoint:** `POST /upload`
- **Severity:** 🟡 **Major (API Contract & Robustness)**
- **Hasil Asersi REST Assured:**
  ```text
  API CONTRACT DEFECT TERDETEKSI: Backend mengembalikan HTTP 500 Internal Server Error 
  untuk penolakan format berkas, seharusnya HTTP 400/415!
  ```
- **Akar Masalah:**
  Komponen middleware upload berkas melemparkan *unhandled exception* ke level root server saat tipe MIME tidak valid diterima.
- **Dampak:**
  Server rentan terhadap eksploitasi *Denial of Service (DoS)* dan membocorkan arsitektur internal server kepada pihak eksternal.

---

<!-- Slide 13: Recommendations -->
# Rekomendasi Solusi & Remediasi Sistem

1. **Implementasi DTO Whitelisting (Mencegah Mass Assignment & Role Injection):**
   - Gunakan skema sanitasi data ketat (Zod / Joi / class-validator). Tolak field `isVerified` dan `role_id` dari payload registrasi pendaftar.
2. **Penegakan RBAC Middleware pada Seluruh Endpoint Sensitif:**
   - Batasi `GET /users` dan modifikasi peran hanya untuk token dengan peran `ROLE_ADMIN` atau `ROLE_HRD`.
3. **Penerapan Dual-Token Session Architecture:**
   - Pangkas masa aktif `accessToken` menjadi **15–30 menit**.
   - Gunakan `refreshToken` (masa aktif 7 hari) yang disimpan pada Cookie `HttpOnly, Secure, SameSite=Strict`.
4. **Global Exception Filter pada Controller Upload:**
   - Tangani penolakan tipe berkas secara anggun dengan mengembalikan `HTTP 400 Bad Request` atau `HTTP 415 Unsupported Media Type`.

---

<!-- Slide 14: Conclusion & Next Steps -->
# Kesimpulan & Rencana Tindak Lanjut

- **Kualitas Antarmuka UI:**
  - Antarmuka web Login, Pendaftaran, dan Ganti Password berada dalam **kondisi sangat prima dan stabil (100% Lulus)**.
- **Fokus Utama Perbaikan:**
  - Fokus perbaikan dialihkan sepenuhnya ke sisi **Backend API Controller & Middleware Keamanan**.
- **Rencana Tindak Lanjut SQA:**
  1. Menyerahkan laporan temuan cacat resmi ini kepada tim Backend & Security Engineering.
  2. Melakukan *re-testing / regression testing* otomatis menggunakan suite `@module-approval-integrity` setelah perbaikan backend di-*deploy*.

---

<!-- Slide 15: Q&A -->
# Terima Kasih

### Ada Pertanyaan atau Diskusi?

**Tautan Laporan Lengkap:**
- Laporan Defect Formal: `LAPORAN_DEFECT_DAN_REKOMENDASI_PERBAIKAN.md`
- Hasil Mentah Allure: `target/allure-results/`
- Dashboard Visual Interaktif: Jalankan `allure serve target/allure-results`
