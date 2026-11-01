# Dashboard Pemantauan & Tracking Pengujian Modul Leader (Team Leader) — HADIR

Dokumen ini digunakan sebagai papan kendali (*monitoring board*) pelacakan progres pengujian otomatis (UI & API) untuk seluruh **11 Halaman Modul Leader** pada aplikasi **HADIR** (`https://magang.dikahadir.com/leader/*` dan mirror offline `http://localhost:8085/leader/*`). Pengujian ini dirancang khusus untuk memvalidasi hak akses, alur operasional, persetujuan dispensasi, serta rekapitulasi tim yang dipimpin oleh **Team Leader (`isLeader` / Supervisor Regu)**.

---

## 1. Ringkasan Status Keseluruhan

| Total Halaman | Belum Dikerjakan (🔴) | Dalam Pengerjaan (🟡) | Selesai / Terverifikasi (🟢) | Tingkat Kesiapan |
| :---: | :---: | :---: | :---: | :---: |
| **11 Halaman** | **0 (0%)** | **0 (0%)** | **11 (100%)** | **100%** |

*(Catatan: Seluruh 11 halaman modul Leader telah selesai diimplementasikan secara komprehensif mencakup 6 Feature Files UI BDD, 1 Feature File API REST Assured, Page Object Model, Step Definitions, Mirror Static Web Mocks, dan Local Web Server Routing dengan hasil verifikasi **100% Passed**).*  
*(Katalog Komponen: Seluruh elemen antarmuka, selector CSS/ID, modal dialog, dan endpoint 11 halaman Leader terdokumentasi di [`docs/COMPONENTS_LEADER.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/COMPONENTS_LEADER.md)).*  
*(Standar Kasus Uji: Kasus uji terstruktur Lean UI dan Backend API terdokumentasi di [`.ai/standards/case_leader.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/.ai/standards/case_leader.md)).*

---

## 2. Profil Akun Uji & Karakteristik Hak Akses Leader

Peran Leader pada sistem Hadir merupakan peranan hierarki operasional tim (**Team Leader / Supervisor Unit**) yang bertugas mengawasi presensi bawahan langsung, memproses permohonan persetujuan (approval), dan men-generate laporan regu:

| Parameter Otorisasi | Detail Hak Akses Peran Leader |
|:---|:---|
| **Role Sistem Utama** | **`isLeader: true`** (Flag Peran Fungsional) / Akun Supervisor Divisi |
| **Landing URL Default** | `https://magang.dikahadir.com/leader/all` |
| **Lingkup Wewenang Utama** | 1. Memantau kehadiran, keterlambatan, dan jam kerja tim regu.<br>2. Meninjau dan menyetujui pengajuan koreksi jam absensi tim.<br>3. Menyetujui dispensasi hari libur / tukar off (`/leader/izin-off`).<br>4. Memvalidasi permohonan cuti dan lembur regu (status pending).<br>5. Menyetujui pertukaran pola shift kerja karyawan (`/leader/shifting/pending`).<br>6. Men-generate dan mengunduh berkas laporan rekapitulasi tim (`/leader/generate`). |
| **Batasan Akses (RBAC)** | Terisolasi secara horizontal pada tim/regu binaan; tidak memiliki hak akses master data tenant admin (`/management/*`), modul root vendor superadmin, maupun data tim di luar regunya. |

---

## 3. Matriks Pemantauan 11 Halaman Modul Leader

| No | Modul / Halaman | Target URL | Kategori Klaster | Static-Web Mock | Standar Test Case | Feature File | Step Defs | Page Object | Status UI (@lean) | Status API | Status Akhir |
|:--:|:---|:---|:---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **Leader Semua (Rekap Tim)** | `https://magang.dikahadir.com/leader/all` | Presensi & Koreksi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 2 | **Leader Kehadiran (Log Harian)** | `https://magang.dikahadir.com/leader/kehadiran` | Presensi & Koreksi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 3 | **Leader Koreksi (Koreksi Jam)** | `https://magang.dikahadir.com/leader/koreksi` | Presensi & Koreksi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 4 | **Leader Izin Off (Tukar Off Tim)** | `https://magang.dikahadir.com/leader/izin-off` | Perizinan & Generator | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 5 | **Leader Generate (Rekap Tim)** | `https://magang.dikahadir.com/leader/generate` | Perizinan & Generator | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 6 | **Leader Cuti Semua** | `https://magang.dikahadir.com/leader/cuti/semua` | Cuti & Dispensasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 7 | **Leader Cuti Pending** | `https://magang.dikahadir.com/leader/cuti/pending` | Cuti & Dispensasi | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 8 | **Leader Lembur Semua** | `https://magang.dikahadir.com/leader/lembur/semua` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 9 | **Leader Lembur Pending** | `https://magang.dikahadir.com/leader/lembur/pending` | Lembur & Overtime | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 10 | **Leader Shifting Semua** | `https://magang.dikahadir.com/leader/shifting/semua` | Jadwal & Shifting | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |
| 11 | **Leader Shifting Pending** | `https://magang.dikahadir.com/leader/shifting/pending` | Jadwal & Shifting | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Selesai | 🟢 Lolos | 🟢 Lolos | 🟢 Lulus 100% |

---

## 4. Pengelompokan Klaster Pengerjaan (Phased Roadmap - Skema 1 Lean Testing)

Sesuai aturan `AGENTS.md` mengenai **Skema 1 (Piramida Uji Hemat / Lean Testing)**, pengujian 11 halaman modul Leader dibagi ke dalam 5 klaster fungsional agar efisien, terstruktur, dan hemat kuota peramban:

### 🔹 Klaster 1: Presensi & Koreksi Jam Regu (3 Halaman)
Fokus pada pengawasan harian presensi anggota regu, tinjauan foto/lokasi kehadiran, dan pengajuan/persetujuan koreksi jam absen:
- [x] 1. `/leader/all` — Ringkasan metrik dan rekapitulasi presensi seluruh anggota tim regu (🟢 Selesai)
- [x] 2. `/leader/kehadiran` — Log riwayat kehadiran harian, jam masuk/keluar, dan foto swafoto (🟢 Selesai)
- [x] 3. `/leader/koreksi` — Daftar dan persetujuan pengajuan perbaikan jam absensi staf binaan (🟢 Selesai)

### 🔹 Klaster 2: Pengelolaan & Validasi Cuti Tim (2 Halaman)
Fokus pada audit permohonan hak cuti anggota tim dan alur persetujuan leader:
- [x] 4. `/leader/cuti/semua` — Riwayat seluruh permohonan cuti staf (Disetujui, Ditolak, Pending) (🟢 Selesai)
- [x] 5. `/leader/cuti/pending` — Antrean verifikasi dan tindakan persetujuan (Approve/Reject) cuti staf regu (🟢 Selesai)

### 🔹 Klaster 3: Pengelolaan & Validasi Lembur Tim (2 Halaman)
Fokus pada kontrol durasi lembur, alasan lembur, dan verifikasi kompensasi lembur regu:
- [x] 6. `/leader/lembur/semua` — Rekapitulasi permohonan lembur seluruh anggota tim binaan (🟢 Selesai)
- [x] 7. `/leader/lembur/pending` — Antrean verifikasi dan persetujuan klaim lembur staf regu (🟢 Selesai)

### 🔹 Klaster 4: Pengelolaan Jadwal Kerja & Shifting Regu (2 Halaman)
Fokus pada rotasi jam kerja, jadwal shift tim, dan persetujuan penukaran shift:
- [x] 8. `/leader/shifting/semua` — Rekapitulasi jadwal rotasi kerja/shift seluruh anggota tim (🟢 Selesai)
- [x] 9. `/leader/shifting/pending` — Antrean permohonan penukaran atau perubahan jadwal shifting tim (🟢 Selesai)

### 🔹 Klaster 5: Perizinan Khusus & Rekapitulasi Laporan Tim (2 Halaman)
Fokus pada penyesuaian hak hari libur (izin off) dan generator rekap laporan berkala:
- [x] 10. `/leader/izin-off` — Pengajuan, verifikasi, dan persetujuan hari libur/off anggota regu (🟢 Selesai)
- [x] 11. `/leader/generate` — Generator rekapitulasi data kehadiran dan ekspor laporan berkala tim (.xlsx) (🟢 Selesai)

---

## 5. Standar Pengujian Setiap Halaman (Definition of Done - DoD)

Setiap halaman modul Leader memenuhi kriteria berikut:
1. **Inspeksi Komponen Visual:** Memverifikasi struktur judul halaman, breadcrumbs, search bar NIK/Nama staf, filter tanggal/status, tabel data, pagination, dan tombol aksi (Approve/Reject/Export).
2. **Katalog Komponen:** Mendokumentasikan CSS selector, ID elemen, dropdown, dan modal dialog ke dalam katalog dokumentasi ([`docs/COMPONENTS_LEADER.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/docs/COMPONENTS_LEADER.md)).
3. **Standar Kasus Uji (.ai/standards/):** Menetapkan skenario pengujian fungsional P1, P2, P3 lengkap dengan langkah uji dan hasil yang diharapkan ([`.ai/standards/case_leader.md`](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/.ai/standards/case_leader.md)).
4. **Skenario Lean UI (`@lean`):** Dibatasi pada alur kritis antarmuka (Load halaman, Filter tanggal, Pencarian NIK/Nama tim, Pagination, Buka modal verifikasi/dialog approval, Tindakan aksi).
5. **API & Data Boundary (`@api`):** Menangani seluruh permutasi data, boundary limit, validasi payload status approval, status code HTTP (200, 201, 204, 400, 401, 403), serta proteksi otorisasi RBAC dan Anti-IDOR.
6. **Implementasi Page Object & Step Definitions:** Kode automasi modular, *reusable*, serta terintegrasi dengan driver manager dan server mock.
7. **Verifikasi Lolos Allure Report:** Seluruh skenario pengujian berhasil dieksekusi tanpa *flakiness* pada Allure Report.

---

## 6. Strategi Pengujian Khusus Peran Leader (Team Leader Security & RBAC)

1. **Isolasi Data Tim (Anti-IDOR / Team Boundary):**
   - Memastikan Leader hanya dapat melihat dan menyetujui permohonan dari anggota tim/regunya sendiri.
   - Menguji API dengan ID staf regu lain untuk memverifikasi penolakan dengan status **HTTP 403 (Forbidden)** atau data kosong.
2. **Dual-Path Execution (Offline Mirror & Live Web):**
   - Memanfaatkan mirror static-web offline (`http://localhost:8085/leader/*`) untuk eksekusi cepat dan hemat kuota.
   - Memvalidasi alur kritis pada lingkungan live (`https://magang.dikahadir.com/leader/*`) menggunakan sesi terautentikasi.
3. **Pemisahan Peran UI vs API:**
   - Variasi input masif, pengujian karakter khusus (SQLi/XSS), batas panjang teks, dan format tanggal invalid dieksekusi di level **REST Assured API (`@api`)**.
   - Level **Selenium UI (`@lean`)** berfokus murni pada stabilitas antarmuka, visibilitas badge status, dan alur interaksi pengguna (*user experience*).

---

## 7. Panduan Eksekusi Pengujian Otomasi

Berikut perintah Maven yang digunakan untuk mengeksekusi pengujian modul Leader:

```bash
# Eksekusi seluruh skenario Lean UI modul Leader (17 Skenario)
mvn test -Dtest=TestRunnerUi -Dcucumber.filter.tags="@leader and @lean"

# Eksekusi seluruh skenario API modul Leader (10 Skenario)
mvn test -Dtest=TestRunnerApi -Dcucumber.filter.tags="@leader"

# Eksekusi seluruh pengujian modul Leader Izin Off
mvn test -Dcucumber.filter.tags="@leader-izin-off"

# Menghasilkan dan membuka Allure Report interaktif
mvn allure:serve
```

---

## 8. Ringkasan Status Eksekusi Pengujian

| Komponen Pengujian | Runner | Total Skenario | Status Lolos | Persentase |
|:---|:---|:---:|:---:|:---:|
| **Local Web Server Routing** | TestNG (`LocalWebServerRoutingTest`) | 16 | 16 Lolos | 100% |
| **Selenium UI Lean** | Cucumber + TestNG (`TestRunnerUi`) | 17 | 17 Lolos | 100% |
| **REST Assured API** | Cucumber + TestNG (`TestRunnerApi`) | 10 | 10 Lolos | 100% |
| **Total Pengujian Modul Leader** | **Hybrid Automation** | **43** | **43 Lolos** | **100% Passed** |

- **Direktori Laporan:** `target/surefire-reports`, `target/cucumber-reports/`, dan `target/site/`
- **Total Cacat (Failed / Product Defects):** 0 Defect
