# Dashboard Pemantauan & Tracking Pengujian Modul Management — HADIR

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh 18 halaman modul Management pada aplikasi **HADIR** (`https://magang.dikahadir.com/management/*`).

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Belum Dikerjakan (🔴) | Dalam Pengerjaan (🟡) | Selesai / Terverifikasi (🟢) |
| :---: | :---: | :---: | :---: |
| **18 Halaman** | **0** | **0** | **18 (100%)** |

*(Catatan: Seluruh 18 halaman modul Management: `/management/user`, `/management/user-v2/register`, `/management/self-registration`, `/management/divisi`, `/management/departemens`, `/management/departements-settings`, `/management/position`, `/management/job-level`, `/management/client-upliner`, `/management/location-point`, `/management/shifting`, `/management/schedule`, `/management/calendar`, `/management/unit-leave`, `/management/day-off`, `/management/monthly-schedule`, `/management/user-monitoring`, dan `/management/ot-reason` telah memiliki implementasi automation testing lengkap).*  
*(Standar Test Case: Seluruh 18 modul telah memiliki dokumen standar uji resmi di folder [`.ai/standards/case_management_*.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/.ai/standards/)).*  
*(Katalog Komponen: Seluruh elemen antarmuka, selector CSS/ID, modal dialog, dan endpoint 18 halaman management telah didokumentasikan di [`docs/COMPONENTS_MANAGEMENT.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/COMPONENTS_MANAGEMENT.md)).*

---

## 2. Matriks Pemantauan 18 Halaman Modul Management

| No | Modul / Halaman Management | Target URL | Kategori Modul | Static-Web Mock | Standar Test Case | Feature File | Step Defs | Page Object | Status Pengerjaan |
| :---: | :--- | :--- | :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | **Management User** | `https://magang.dikahadir.com/management/user` | Karyawan & Akun | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 2 | **Register User V2** | `https://magang.dikahadir.com/management/user-v2/register` | Karyawan & Onboarding | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 3 | **Self Registration** | `https://magang.dikahadir.com/management/self-registration` | Karyawan & Verifikasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 4 | **Divisi** | `https://magang.dikahadir.com/management/divisi` | Struktur Organisasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 5 | **Departemens** | `https://magang.dikahadir.com/management/departemens` | Struktur Organisasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 6 | **Departements Settings** | `https://magang.dikahadir.com/management/departements-settings` | Struktur Organisasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 7 | **Position** | `https://magang.dikahadir.com/management/position` | Jabatan & Peran | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 8 | **Client Upliner** | `https://magang.dikahadir.com/management/client-upliner` | Klien & Relasi Atasan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 9 | **Location Point** | `https://magang.dikahadir.com/management/location-point` | Presensi & Geofencing | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 10 | **Job Level** | `https://magang.dikahadir.com/management/job-level` | Jenjang & Tingkat Kerja | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 11 | **Shifting** | `https://magang.dikahadir.com/management/shifting` | Jadwal & Pola Shift | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 12 | **Schedule** | `https://magang.dikahadir.com/management/schedule` | Jadwal Kerja Reguler | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 13 | **Calendar** | `https://magang.dikahadir.com/management/calendar` | Kalender Kerja & Libur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 14 | **Unit Leave** | `https://magang.dikahadir.com/management/unit-leave` | Kuota & Alokasi Cuti | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 15 | **Day Off** | `https://magang.dikahadir.com/management/day-off` | Hari Libur Non-Kerja | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 16 | **Monthly Schedule** | `https://magang.dikahadir.com/management/monthly-schedule` | Roster & Jadwal Bulanan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 17 | **User Monitoring** | `https://magang.dikahadir.com/management/user-monitoring` | Pemantauan Karyawan | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |
| 18 | **OT Reason** | `https://magang.dikahadir.com/management/ot-reason` | Alasan & Aturan Lembur | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai |

---

## 3. Pengelompokan Klaster Pengerjaan (Phased Roadmap)

Agar pengujian berjalan efisien, terstruktur, dan mengikuti strategi **Lean Testing (Skema 1)**, pengerjaan dikelompokkan ke dalam 5 klaster fungsional:

### 🔹 Klaster 1: Manajemen Karyawan & Registrasi (4 Halaman) — 🟢 100% Selesai
Fokus pada pendaftaran, verifikasi pendaftaran mandiri, direktori user aktif, pembaruan profil, dan pemantauan akun:
- [x] 1. `management/user` (Daftar Pengguna / Management User) — [`ManagementUserPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/ManagementUserPage.java)
- [x] 2. `management/user-v2/register` (Registrasi Karyawan V2) — [`RegisterUserV2Page.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/RegisterUserV2Page.java)
- [x] 3. `management/self-registration` (Verifikasi Pendaftaran Mandiri) — [`SelfRegistrationPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/SelfRegistrationPage.java)
- [x] 4. `management/user-monitoring` (Pemantauan Pengguna Aktif) — [`UserMonitoringPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/UserMonitoringPage.java)

### 🔹 Klaster 2: Struktur Organisasi & Hierarki (5 Halaman) — 🟢 100% Selesai
Fokus pada pembagian unit kerja divisi, departemen, konfigurasi departemen, penjenjangan karir, dan posisi jabatan:
- [x] 5. `management/divisi` (Master Divisi) — [`DivisiPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/DivisiPage.java)
- [x] 6. `management/departemens` (Master Departemen) — [`DepartemensPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/DepartemensPage.java)
- [x] 7. `management/departements-settings` (Pengaturan Konfigurasi Departemen) — [`DepartementsSettingsPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/DepartementsSettingsPage.java)
- [x] 8. `management/position` (Master Jabatan / Posisi Kerja) — [`PositionPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/PositionPage.java)
- [x] 9. `management/job-level` (Master Level & Golongan Karyawan) — [`JobLevelPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/JobLevelPage.java)

### 🔹 Klaster 3: Lokasi Presensi & Relasi Klien (2 Halaman) — 🟢 100% Selesai
Fokus pada penugasan atasan klien dan penetapan titik lokasi absensi berbasis koordinat/geofencing:
- [x] 10. `management/client-upliner` (Relasi Klien & Atasan Upliner) — [`ClientUplinerPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/ClientUplinerPage.java)
- [x] 11. `management/location-point` (Master Titik Lokasi Presensi / Geofencing) — [`LocationPointPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/LocationPointPage.java)

### 🔹 Klaster 4: Jam Kerja, Shifting & Penjadwalan (4 Halaman) — 🟢 100% Selesai
Fokus pada master shift kerja, template jadwal jam kerja harian/mingguan, roster bulanan karyawan, dan alasan lembur:
- [x] 12. `management/shifting` (Master & Pengaturan Shift) — [`ShiftingPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/ShiftingPage.java)
- [x] 13. `management/schedule` (Master Jadwal Jam Kerja) — [`SchedulePage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/SchedulePage.java)
- [x] 14. `management/monthly-schedule` (Jadwal Kerja Bulanan Karyawan) — [`MonthlySchedulePage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/MonthlySchedulePage.java)
- [x] 15. `management/ot-reason` (Master Alasan Lembur / Overtime Reason) — [`OtReasonPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/OtReasonPage.java)

### 🔹 Klaster 5: Kalender & Hak Libur/Cuti (3 Halaman) — 🟢 100% Selesai
Fokus pada kalender operasional perusahaan, hari libur nasional/cuti bersama, dan alokasi unit kuota cuti:
- [x] 16. `management/calendar` (Kalender Kerja Perusahaan) — [`CalendarPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/CalendarPage.java)
- [x] 17. `management/day-off` (Pengaturan Hari Libur / Day Off) — [`DayOffPage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/DayOffPage.java)
- [x] 18. `management/unit-leave` (Unit & Kuota Hak Cuti) — [`UnitLeavePage.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/pages/UnitLeavePage.java)

---

## 4. Standar Pengujian Setiap Halaman (Definition of Done)

Setiap halaman modul Management yang dikerjakan wajib memenuhi kriteria standar kualitas SQA berikut sebelum statusnya diubah menjadi 🟢 **Selesai**:

1. **Inspeksi Elemen Visual UI:**
   - Memverifikasi render elemen utama: judul modul/header, input pencarian (search bar), dropdown filter/tipe, tombol aksi (Tambah, Filter, Reset, Export), struktur kolom tabel, badge status, dan kontrol paginasi.
2. **Skenario Lean UI (`@lean`):**
   - Mengikuti prinsip Skema 1 (Piramida Uji Hemat): dibatasi hanya pada alur kritis antarmuka (Load halaman, Pencarian Happy Path, Pembukaan/Penutupan Modal Dialog, Toggle Switch, dan Validasi Error Alert).
   - Dilarang membuat puluhan variasi input teks di level browser.
3. **API & Data Boundary (`@api`):**
   - Menangani seluruh variasi data masif, pengujian batasan boundary, validasi otorisasi peran (RBAC admin vs non-admin), uji SQL Injection & XSS pada parameter pencarian, dan verifikasi struktur response JSON.
4. **Offline Mirror / Static-Web Mock:**
   - Halaman statis HTML tersedia di `src/test/resources/static-web/management/` dan terdaftar pada routing [`LocalWebServer.java`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/utils/LocalWebServer.java) untuk pengujian lokal cepat tanpa dependensi internet.
5. **Verifikasi Allure Report:**
   - Seluruh test case terdaftar dan lolos uji (atau mencatat defect terverifikasi) pada laporan Allure Report.
6. **Pembaruan Tracking:**
   - Ceklis dan matriks pemantauan pada file ini diperbarui secara berkala sesuai perkembangan aktual.

---

## 5. Daftar Temuan Potensi Cacat & Keamanan Teridentifikasi (Defect Register)

Berikut adalah catatan temuan cacat, kerentanan, atau potensi anomali yang telah diidentifikasi pada modul Management:

| ID Defect / Anomali | Modul / Halaman | Komponen Terdampak | Deskripsi Cacat / Risiko | Severity | Status |
|---|---|---|---|---|---|
| **BUG-MGMT-001** | `/management/user` | Kolom Foto Karyawan | URL foto berformat hardcoded dev `uploads/{photo}` berpotensi HTTP 404 jika storage cloud tidak terhubung. Butuh handling fallback avatar inisial nama. | 🟡 Medium | ⚠️ Teridentifikasi |
| **BUG-MGMT-002** | `/management/user` | Sakelar Switch Active & User Project | Tidak adanya mekanisme *optimistic rollback* jika API `PATCH /api/users/{id}` gagal di backend. Sakelar tetap bergeser di UI meski server error. | 🔴 Major | ⚠️ Teridentifikasi |
| **BUG-MGMT-003** | `/management/user` | Tombol Hapus User (`DELETE`) | Risiko *orphan records* jika user yang dihapus merupakan atasan aktif (*upliner*) bagi bawahan lain. | 🔴 Major | ⚠️ Teridentifikasi |
| **BUG-MGMT-004** | `/management/user` | Export Users Excel | Ketiadaan debounce dan sanitasi wildcard pada query `nik[$like]` dan `fullname[$like]` berpotensi membebani server backend saat data masif. | 🟡 Medium | ⚠️ Teridentifikasi |
| **BUG-MGMT-005** | `/management/user` | Endpoint API User | Kerentanan *Broken Object Level Authorization (BOLA)* jika endpoint API tidak memverifikasi role admin di middleware. | 🔴 Critical | ⚠️ Teridentifikasi |
| **BUG-MGMT-006** | `/management/*` | Sesi Enkripsi Frontend | Hardcoded password enkripsi sesi pada bundel JavaScript klien berpotensi kebocoran session cookie. | 🔴 Critical | ❌ Confirmed Leakage |

---

## 6. Panduan Eksekusi Pengujian

### Menjalankan Uji UI Kritis (Lean Suite)
```bash
mvn test -Dtest=TestRunnerUi -Dcucumber.filter.tags="@lean and @management"
```

### Menjalankan Uji API Terkait
```bash
mvn test -Dtest=TestRunnerApi -Dcucumber.filter.tags="@api and @management"
```

### Menjalankan Pengujian per Klaster Tertentu
```bash
# Contoh untuk Klaster Divisi
mvn test -Dcucumber.filter.tags="@divisi"

# Contoh untuk Klaster Monthly Schedule
mvn test -Dcucumber.filter.tags="@monthly-schedule"

# Contoh untuk Klaster Management User
mvn test -Dcucumber.filter.tags="@management-user"
```

### Menghasilkan dan Membuka Allure Report
```bash
mvn allure:report
mvn allure:serve
```

