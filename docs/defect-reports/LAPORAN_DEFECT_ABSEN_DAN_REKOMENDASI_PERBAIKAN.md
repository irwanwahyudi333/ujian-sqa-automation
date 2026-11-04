# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Absensi (Presensi & Koreksi Jam Kerja) — Hasil Allure Report

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul Diuji:** Fitur Presensi Mandiri, Koreksi Jam Kehadiran, & Monitoring Absensi (*Attendance, Correction & Attendance Monitoring Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 04 November 2026  
**Total Skenario Dijalankan:** 27 Skenario Unik (22 Skenario UI Selenium WebDriver & 5 Skenario API REST Assured)  
**Hasil:** 24 Passed (88.9%), 3 Failed (11.1% — Defects Detected Murni Selenium & REST Assured)  
**Tingkat Kritis:** 🔴 **Tinggi (Core Action Button Unmounting, Attendance Blocking Gate & Geolocation Anti-Spoofing Vulnerability)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomasi hybrid (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada seluruh fitur Presensi dan Koreksi Absensi (*Attendance & Correction Management*) dengan strategi pengujian hemat (*Lean Testing Strategy - Skema 1*), pengujian telah memvalidasi seluruh siklus hidup presensi: mulai dari antarmuka dashboard mobile karyawan (`/apps/absent`), permohonan koreksi jam kerja (`/apps/absent/correction`), peninjauan riwayat presensi harian (`/apps/absent/activity`), konsolidasi laporan koreksi admin HR (`/laporan/koreksi`), portal verifikasi Client Upliner (`/client/kehadiran` & `/client/koreksi`), audit pemantauan auditor monitoring (`/monitoring/kehadiran` & `/monitoring/koreksi`), hingga endpoint API backend REST Assured (`/api/absent/*`).

Dari **27 skenario pengujian unik** yang terekam secara komprehensif pada **Allure Report**:
1. **Pengujian Antarmuka UI Selenium WebDriver (22 Skenario): 20 Passed, 2 Failed (90.9% Lulus)**  
   Sebanyak 20 skenario antarmuka pada pelaporan admin HR, portal verifikasi Client Upliner, pemantauan auditor monitoring, dan elemen form koreksi mobile berjalan stabil dan memenuhi kriteria fungsional.
   - **2 Cacat Antarmuka & Logika Bisnis Ditemukan:**
     - *DEFECT-ABS-001:* Pada halaman dashboard presensi mobile (`/apps/absent`), tombol aksi utama langsung menghilang dari DOM (*unmounted*) setelah Absen Masuk dan tidak bertransisi menjadi **"Absen Keluar"**, menyebabkan karyawan tidak dapat mencatat jam kepulangan secara mandiri di sore hari.
     - *DEFECT-ABS-002:* Pada halaman dashboard presensi mobile (`/apps/absent`), sistem menerapkan pemblokiran keras (*hard blocking gate*) yang melarang karyawan melakukan Absen Masuk hari berjalan jika belum mengajukan koreksi atas catatan absensi hari sebelumnya.
2. **Pengujian Backend API REST Assured (5 Skenario): 4 Passed, 1 Failed (80.0% Lulus)**  
   Endpoint API rekapitulasi presensi (`GET /api/absent/history`) dan permohonan koreksi (`POST /api/absent/correction`) memenuhi spesifikasi pengambilan data berotentikasi (`HTTP 200`), pembuatan request baru (`HTTP 201`), validasi batas permohonan lampau (*cut-off guard* -> `HTTP 400`), dan penolakan request tanpa token otorisasi (`HTTP 401`).
   - **1 Cacat Keamanan & Kontrak API Kritis Ditemukan (*SEC-ABS-001*):** Endpoint presensi masuk (`POST /api/absent/clock-in`) tidak memverifikasi integritas geolokasi atau flag *Mock Location* (Fake GPS). Server menerima data manipulasi lokasi dan mengembalikan `HTTP 200 OK` alih-alih menolak dengan penanganan error bersih `HTTP 400 Bad Request` atau `HTTP 403 Forbidden`.

---

## 2. Matriks Temuan Cacat (Defect Matrix)

### Matriks Temuan Cacat Backend (REST Assured)

| ID Defect | Modul / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **SEC-ABS-001** | `POST /api/absent/clock-in` | Mock Location & Fake GPS Spoofing Vulnerability | 🔴 **Critical** | Server menerima koordinat lokasi palsu dengan flag `isMockLocation: true` dan merespons `HTTP 200 OK` | Server menolak presensi jika terdeteksi Fake GPS/mock provider dengan kode `HTTP 400` atau `HTTP 403` |

---

### Matriks Temuan Cacat Antarmuka UI (Selenium WebDriver)

| ID Defect | Modul / Halaman | Komponen Bermasalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-ABS-001** | UI `/apps/absent` | Core Attendance Button State Management | 🔴 **Critical** | Tombol aksi utama langsung lenyap dari layar setelah Absen Masuk dan tidak bertransisi menjadi **"Absen Keluar"** | Tombol aksi utama bertransisi secara reaktif menjadi tombol merah bertuliskan "Absen Keluar" |
| **DEFECT-ABS-002** | UI `/apps/absent` | Business Logic Flaw & Attendance Hard Blocking | 🔴 **Major** | Karyawan diblokir melakukan Absen Masuk hari berjalan jika terdapat *missing clock-out* kemarin yang belum dikoreksi | Karyawan tetap berhak mencatat presensi masuk hari berjalan; catatan kemarin ditandai peringatan non-blocking |

---

### Matriks Evaluasi Hasil Uji Per Sub-Modul (Allure Report)

| No | Modul / Halaman Pengujian | Layer Uji | Total Skenario | Passed | Failed | Status Kelulusan |
|:---:|---|:---:|:---:|:---:|:---:|:---:|
| 1 | API Presensi Kehadiran & Koreksi (`absensi_api.feature`) | API | 5 | 4 | 1 | 🟡 **80.0%** (1 Defect SEC-ABS-001) |
| 2 | Dashboard Presensi Mobile (`absent_home_lean.feature`) | UI | 5 | 3 | 2 | 🟡 **60.0%** (2 Defects DEFECT-ABS-001/002) |
| 3 | Koreksi Absen Mobile (`absent_correction_lean.feature`) | UI | 2 | 2 | 0 | 🟢 **100% Lulus** |
| 4 | Riwayat Presensi Mobile (`absent_activity_lean.feature`) | UI | 2 | 2 | 0 | 🟢 **100% Lulus** |
| 5 | Laporan Koreksi Admin HR (`12_laporan_koreksi.feature`) | UI | 9 | 9 | 0 | 🟢 **100% Lulus** |
| 6 | Portal Client Kehadiran & Koreksi (`ClientLaporan.feature`) | UI | 2 | 2 | 0 | 🟢 **100% Lulus** |
| 7 | Auditor Monitoring Presensi (`MonitoringPresensi.feature`) | UI | 2 | 2 | 0 | 🟢 **100% Lulus** |
| **TOTAL** | **Seluruh Fitur Modul Absensi (Presensi & Koreksi)** | **Hybrid** | **27** | **24** | **3** | 🟢 **88.9% Lulus** |

---

## 3. Rincian Temuan Cacat & Rekomendasi Perbaikan

### 1. DEFECT-ABS-001: Tombol "Absen Keluar" Tidak Muncul Setelah Presensi Masuk Berhasil
- **Kode Skenario:** `@BUG-ABS-001 @DEFECT-ABS-001 @live` pada [absent_home_lean.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/apps_absent/absent_home_lean.feature#L26-L31)
- **Lokasi Halaman:** Antarmuka Dashboard Presensi Mobile (`/apps/absent`)
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**
  Setelah karyawan menyelesaikan proses Absen Masuk (*Clock-In*), tombol aksi presensi utama di bagian bawah antarmuka langsung menghilang sepenuhnya dari DOM (*unmounted*). Sistem tidak merender tombol pengganti bertuliskan *"Absen Keluar"* (*Clock-Out*). Ketika karyawan membuka kembali aplikasi di sore hari saat jam pulang kerja, antarmuka tidak menyediakan tombol apapun untuk mencatat kepulangan.
- **Dampak Bisnis & Operasional:**
  Karyawan tidak dapat mencatat jam pulang secara mandiri. Di akhir bulan, seluruh catatan kehadiran tanpa jam pulang akan terhitung sebagai *Missing Clock-Out* atau dianggap alpa/setengah hari kerja oleh mesin payroll HR, memicu keluhan massal dari karyawan dan pemotongan tunjangan yang tidak adil.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: [DEFECT-ABS-001] Tombol 'Absen Keluar' tidak tampil di antarmuka! 
  Tombol aksi presensi utama langsung hilang setelah tombol Absen Masuk ditekan dan tidak bertransisi menjadi Absen Keluar. 
  expected [true] but found [false]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertTrue(Assert.java:57)
  	at com.company.sqa.ui.steps.AbsentHomeSteps.tombolAksiPresensiUtamaHarusBertransisiMenjadi(AbsentHomeSteps.java:72)
  	at ✽.Tombol aksi presensi utama harus bertransisi menjadi "Absen Keluar"(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/apps_absent/absent_home_lean.feature:29)
  ```
- **Akar Masalah (*Root Cause*):**
  Logika kondisional pada komponen React/Next.js dashboard presensi:
  ```jsx
  // Kode Frontend Bermasalah:
  {attendanceStatus === 'NOT_CLOCKED_IN' ? (
    <Button onClick={handleClockIn}>Absen Masuk</Button>
  ) : null} // <-- Ketika status CLOCKED_IN, tombol di-unmount dan tidak ada alternatif tombol Absen Keluar
  ```
- **Rekomendasi Perbaikan (Frontend React / Next.js):**
  Perbarui *finite state machine* (FSM) tombol aksi presensi utama:
  ```jsx
  // Rekomendasi Perbaikan State Machine Tombol Presensi:
  export function AttendanceActionButton({ todayAttendance, onClockIn, onClockOut }) {
    // 1. Kondisi Belum Absen Masuk
    if (!todayAttendance || !todayAttendance.clockInTime) {
      return (
        <button 
          id="btn-clock-in" 
          className="btn-attendance btn-green"
          onClick={onClockIn}
        >
          Absen Masuk
        </button>
      );
    }

    // 2. Kondisi Sudah Masuk tapi Belum Keluar
    if (todayAttendance.clockInTime && !todayAttendance.clockOutTime) {
      return (
        <button 
          id="btn-clock-out" 
          className="btn-attendance btn-red"
          onClick={onClockOut}
        >
          Absen Keluar
        </button>
      );
    }

    // 3. Kondisi Presensi Hari Ini Selesai
    return (
      <div className="attendance-completed-badge">
        <CheckCircleIcon /> Kehadiran Hari Ini Selesai ({todayAttendance.clockInTime} - {todayAttendance.clockOutTime})
      </div>
    );
  }
  ```

---

### 2. DEFECT-ABS-002: Pemblokiran Presensi Masuk Akibat Kewajiban Koreksi Absen Hari Sebelumnya
- **Kode Skenario:** `@BUG-ABS-002 @DEFECT-ABS-002 @live` pada [absent_home_lean.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/apps_absent/absent_home_lean.feature#L32-L36)
- **Lokasi Halaman:** Antarmuka Dashboard Presensi Mobile (`/apps/absent`)
- **Severity:** 🔴 **Major**
- **Deskripsi Masalah:**
  Jika seorang karyawan pada hari kerja sebelumnya lupa melakukan presensi keluar (*missing clock-out*), saat karyawan hendak melakukan presensi masuk di hari berikutnya, aplikasi menampilkan dialog pemblokiran keras (*hard blocking gate*): *"Anda wajib mengajukan koreksi absen hari kemarin sebelum dapat melakukan absen masuk hari ini"*. Tombol absen masuk dinonaktifkan sepenuhnya.
- **Dampak Bisnis & Operasional:**
  Kebijakan pemblokiran ini melanggar alur operasional kerja yang wajar (*user experience barrier*). Proses persetujuan koreksi absensi membutuhkan waktu review oleh Team Leader atau Atasan Langsung (yang belum tentu standby di pagi hari). Akibatnya, karyawan yang sudah tiba tepat waktu di kantor terpaksa terlambat mencatat kehadiran hari ini karena menunggu approval koreksi hari kemarin.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: [DEFECT-ABS-002] Karyawan diwajibkan melakukan koreksi absensi terlebih dahulu sebelum diizinkan melakukan absen masuk di hari berikutnya! 
  expected [false] but found [true]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertFalse(Assert.java:79)
  	at com.company.sqa.ui.steps.AbsentHomeSteps.karyawanDiizinkanMelakukanPresensiMasukTanpaDiblokirKewajibanKoreksiAbsensiHariSebelumnya(AbsentHomeSteps.java:92)
  	at ✽.Karyawan diizinkan melakukan presensi masuk tanpa diblokir kewajiban koreksi absensi hari sebelumnya(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/ui/apps_absent/absent_home_lean.feature:35)
  ```
- **Rekomendasi Perbaikan (Business Logic Decoupling):**
  Pisahkan kewajiban administratif koreksi dengan hak pencatatan kehadiran berjalan:
  1. **Izinkan Absen Masuk Berjalan Secara Independen:** Karyawan tetap berhak mencatat jam masuk hari ini secara normal tanpa halangan.
  2. **Banner Pengingat Non-Blocking:** Tampilkan kartu peringatan kuning di bagian atas dashboard: *"Terdapat 1 catatan absensi kemarin yang belum lengkap. Silakan ajukan koreksi sebelum periode cut-off payroll."*
  3. **Penegakan di Level Payroll (Bukan di Gate Presensi Pintu Masuk):** Jika hingga tanggal *cut-off* koreksi belum diajukan atau disetujui, barulah sistem payroll memotong tunjangan kehadiran secara otomatis.

---

### 3. SEC-ABS-001: Ketiadaan Validasi Anti-Spoofing Geolokasi (Mock Location / Fake GPS)
- **Kode Skenario:** `@SEC-ABS-001 @security @mock-location @absensi @lean` pada [absensi_api.feature](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/absensi/absensi_api.feature#L36-L53)
- **Modul / Endpoint:** `POST /api/absent/clock-in`
- **Severity:** 🔴 **Critical**
- **Deskripsi Masalah:**
  Aplikasi mobile client mengirimkan payload koordinat latitude, longitude, dan berkas swafoto kamera saat request presensi masuk `POST /api/absent/clock-in`. Server backend saat ini hanya memverifikasi radius geofence dasar tanpa memeriksa:
  1. Flag `isFromMockProvider` / `isMockLocation` dari Location API Android.
  2. Integritas lingkungan perangkat (Google Play Integrity API / Apple App Attest).
  Server merespons dengan `HTTP 200 OK` (presensi berhasil) meskipun payload menandakan koordinat berasal dari aplikasi lokasi tiruan (*mock provider*).
- **Dampak Keamanan:**
  Karyawan dapat melakukan manipulasi kehadiran ("absen titip" / *fake attendance*) dari rumah atau lokasi luar kantor menggunakan aplikasi *Fake GPS* atau *Android Emulator* tanpa benar-benar hadir secara fisik di area kerja.
- **Error Stack Trace (Allure Report):**
  ```text
  java.lang.AssertionError: [SEC-ABS-001] Endpoint presensi masuk tidak memvalidasi flag mock location / Fake GPS! 
  Server menerima presensi palsu dan mengembalikan status HTTP 200 OK alih-alih menolak dengan HTTP 400 Bad Request atau HTTP 403 Forbidden. 
  expected [true] but found [false]
  	at org.testng.Assert.fail(Assert.java:111)
  	at org.testng.Assert.failNotEquals(Assert.java:1578)
  	at org.testng.Assert.assertTrue(Assert.java:57)
  	at com.company.sqa.api.steps.AbsensiApiSteps.sistemBackendHarusMenolakPresensiDenganStatus400BadRequestAtau403Forbidden(AbsensiApiSteps.java:72)
  	at ✽.sistem backend harus menolak presensi dengan status 400 Bad Request atau 403 Forbidden(file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/resources/features/api/absensi/absensi_api.feature:52)
  ```
- **Rekomendasi Perbaikan (Backend Validation Guard):**
  Terapkan middleware validasi integritas presensi mobile pada backend:
  ```typescript
  // Middleware Validasi Integritas Presensi Mobile:
  export function validateAttendanceSecurity(req: Request, res: Response, next: NextFunction) {
    const { latitude, longitude, isMockLocation, photoMetadata, deviceIntegrityToken } = req.body;

    // 1. Tolak jika perangkat melaporkan Mock Location aktif
    if (isMockLocation === true) {
      return res.status(403).json({
        status: false,
        code: "FRAUD_MOCK_LOCATION_DETECTED",
        message: "Presensi ditolak: Terdeteksi aplikasi lokasi tiruan (Fake GPS) aktif pada perangkat."
      });
    }

    // 2. Verifikasi Google Play Integrity API / Apple App Attest
    if (!verifyDeviceIntegrity(deviceIntegrityToken)) {
      return res.status(403).json({
        status: false,
        code: "UNTRUSTED_DEVICE_ENVIRONMENT",
        message: "Presensi ditolak: Aplikasi berjalan di lingkungan emulator atau perangkat telah di-root."
      });
    }

    // 3. Verifikasi Geofence Radius Kantor (Maksimal 50 Meter dari Titik Pusat Kantor)
    const distanceMeters = calculateHaversineDistance(
      { lat: latitude, lng: longitude },
      { lat: OFFICE_LOCATION.lat, lng: OFFICE_LOCATION.lng }
    );

    if (distanceMeters > OFFICE_LOCATION.radiusMeters) {
      return res.status(400).json({
        status: false,
        code: "OUT_OF_GEOFENCE_RADIUS",
        message: `Presensi ditolak: Anda berada di luar radius kantor (${Math.round(distanceMeters)}m dari batas ${OFFICE_LOCATION.radiusMeters}m).`
      });
    }

    next();
  }
  ```

---

## 4. Panduan Eksekusi Uji Regresi Modul Absensi

Untuk mereproduksi, memverifikasi, dan memastikan perbaikan cacat pada modul Absensi:

```bash
# 1. Jalankan Seluruh Skenario Lean Modul Absensi (UI + API):
mvn clean test -Dcucumber.filter.tags="@absensi and @lean"

# 2. Jalankan Skenario Fungsional Bersih (Tanpa Cacat Yang Diketahui):
mvn test -Dcucumber.filter.tags="@absensi and @lean and not @DEFECT-ABS-001 and not @DEFECT-ABS-002 and not @SEC-ABS-001"

# 3. Jalankan Pengujian Khusus Validasi Cacat Tombol Absen Keluar (DEFECT-ABS-001):
mvn test -Dcucumber.filter.tags="@DEFECT-ABS-001"

# 4. Jalankan Pengujian Khusus Validasi Cacat Blokir Absen Masuk (DEFECT-ABS-002):
mvn test -Dcucumber.filter.tags="@DEFECT-ABS-002"

# 5. Jalankan Pengujian Khusus Validasi Cacat Keamanan Mock Location (SEC-ABS-001):
mvn test -Dcucumber.filter.tags="@SEC-ABS-001"

# 6. Generate dan Buka Allure Report:
.allure/allure-2.29.0/bin/allure generate target/allure-results -o target/allure-report --clean
.allure/allure-2.29.0/bin/allure open target/allure-report
```

---

## 5. Kesimpulan & Rencana Tindak Lanjut

1. **Prioritas Perbaikan #1 (DEFECT-ABS-001):** Tim frontend web mobile wajib memperbaiki state machine tombol presensi agar setelah proses Absen Masuk selesai, tombol segera bertransisi menjadi "Absen Keluar" sehingga karyawan dapat mencatat kepulangan di sore hari.
2. **Prioritas Perbaikan #2 (SEC-ABS-001):** Tim backend wajib mengimplementasikan pemeriksaan parameter `isMockLocation` dan integrasi Google Play Integrity API untuk mencegah kecurangan absensi titip / Fake GPS.
3. **Prioritas Perbaikan #3 (DEFECT-ABS-002):** Tim produk dan UX disarankan menghapus pemblokiran presensi masuk mandiri dan menggantinya dengan pengingat non-blocking agar tidak mengganggu operasional kehadiran harian karyawan.
