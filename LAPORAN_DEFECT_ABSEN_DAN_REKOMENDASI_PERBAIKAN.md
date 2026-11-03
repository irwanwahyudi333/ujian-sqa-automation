# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Absensi (Presensi & Kehadiran)

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul:** Fitur Presensi Mandiri, Koreksi Jam Kehadiran, & Monitoring Absensi (*Attendance & Correction Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 03 November 2026  
**Total Skenario Dijalankan:** 67 Skenario Unik (Mobile Client, Leader Portal, Client Upliner, Monitoring, dan Admin HR)  
**Hasil:** 64 Passed (95.5%), 3 Failed / Defect Teridentifikasi  
**Tingkat Kritis:** 🔴 **Tinggi (Core Mobile Attendance Logic, Lockout Flaws & UI State Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian otomatis menyeluruh (*End-to-End Automation Testing*) pada modul Presensi/Absensi HADIR, pengujian mencakup 5 domain utama:
1. **Domain A — Mobile Client Karyawan (`/apps/absent/*`):** Dashboard presensi harian, tombol absen masuk/keluar, swafoto kamera, pengajuan koreksi jam absensi, riwayat kehadiran bulanan, share report, dan monitoring staf regu (12 skenario).
2. **Domain B — Leader Approval (`/leader/*`):** Persetujuan koreksi jam absen staf dan monitoring regu.
3. **Domain C — Client Upliner V2 & V3 (`/client/*`):** Validasi laporan kehadiran dan jam kerja staf binaan.
4. **Domain D — Monitoring Auditor (`/monitoring/*`):** Pengawasan dan rekapitulasi kepatuhan jadwal kerja.
5. **Domain E — Laporan HR (`/laporan/kehadiran`):** Filter tanggal, ekspor Excel, dan audit status hadir/alpa.

Dari pengujian yang dieksekusi, **kegagalan yang terjadi bukan disebabkan oleh kerusakan skrip otomasi**, melainkan merupakan **temuan cacat kritis pada logika bisnis (*business logic flaws*) dan penanganan antarmuka (*UI state management*) aplikasi target**.

---

## 2. Matriks Temuan Cacat (Defect Matrix - Modul Absensi)

| ID Defect | Modul / Halaman / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-ABS-001** | UI `/apps/absent` | Core Action Button Disappearance | 🔴 **Critical** | Tombol aksi utama langsung lenyap dari layar setelah Absen Masuk dan tidak bertransisi menjadi **"Absen Keluar"** | Tombol aksi utama bertransisi secara reaktif menjadi tombol merah bertuliskan "Absen Keluar" |
| **DEFECT-ABS-002** | UI `/apps/absent` | Business Logic Flaw & Lockout | 🔴 **Major** | Karyawan diblokir melakukan Absen Masuk hari berjalan jika belum mengajukan koreksi absen kemarin | Karyawan tetap berhak absen masuk hari ini; hari kemarin ditandai peringatan (*warning badge*) |
| **DEFECT-ABS-003** | UI `/apps/absent/correction` | Drawer Component Hydration Lag | 🟡 **Medium** | Drawer modal `#drawer-ajukan-koreksi` terkadang gagal merender field input form saat tombol ditekan | Drawer modal langsung muncul dengan transisi CSS halus dan seluruh field formulir lengkap |
| **SEC-ABS-001** | API `/api/absent/clock-in` | Mock Location & Fake GPS Spoofing | 🔴 **Critical** | Server menerima koordinat lokasi tanpa memvalidasi metadata flag mock location dari perangkat Android | Server menolak request jika terdeteksi manipulasi GPS atau berada di luar geofence radius |
| **BIZ-ABS-001** | Backend Attendance Engine | Missing Grace Period Validation | 🟡 **Medium** | Keterlambatan 1 detik langsung memotong insentif kehadiran tanpa toleransi waktu (*grace period*) | Sistem menyediakan konfigurasi toleransi keterlambatan (misal 5–15 menit) sebelum status "Terlambat" |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. DEFECT-ABS-001: Tombol "Absen Keluar" Tidak Tampil Setelah Presensi Masuk Berhasil
- **Kode Skenario:** `@BUG-ABS-001 @DEFECT-ABS-001 @live` pada `absent_home_lean.feature`
- **Lokasi Halaman:** Antarmuka Dashboard Presensi Mobile (`/apps/absent`)
- **Deskripsi Masalah:**
  Setelah karyawan menyelesaikan proses presensi masuk (*Clock-In*) dengan swafoto dan koordinat geolokasi, tombol aksi utama presensi langsung menghilang dari DOM (*unmounted*). Sistem tidak merender tombol pengganti *"Absen Keluar"* (*Clock-Out*). Ketika karyawan membuka kembali aplikasi di sore hari saat jam pulang kerja, antarmuka tidak menyediakan tombol untuk mencatat kepulangan.
- **Dampak Bisnis & Operasional:**
  Karyawan tidak dapat mencatat jam pulang secara mandiri. Di akhir bulan, seluruh catatan kehadiran yang tidak memiliki jam keluar akan dihitung sebagai *Missing Clock-Out* atau dianggap alpa/setengah hari kerja oleh mesin payroll HR, memicu keluhan massal dari karyawan.
- **Error Stack Trace:**
  ```text
  java.lang.AssertionError: [DEFECT-ABS-001] Tombol 'Absen Keluar' tidak tampil di antarmuka! 
  Tombol aksi presensi utama langsung hilang setelah tombol Absen Masuk ditekan dan tidak bertransisi menjadi Absen Keluar. expected [true] but found [false]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.assertTrue(Assert.java:57)
  at com.company.sqa.ui.steps.AbsentHomeSteps.tombolAksiPresensiUtamaHarusBertransisiMenjadi(AbsentHomeSteps.java:72)
  at ✽.Tombol aksi presensi utama harus bertransisi menjadi "Absen Keluar"(file:///.../absent_home_lean.feature:29)
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
  Perbarui state machine tombol aksi presensi utama:
  ```jsx
  // Rekomendasi Perbaikan State Machine Tombol Presensi:
  export function AttendanceActionButton({ todayAttendance, onClockIn, onClockOut }) {
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

    return (
      <div className="attendance-completed-badge">
        <CheckCircleIcon /> Kehadiran Hari Ini Selesai ({todayAttendance.clockInTime} - {todayAttendance.clockOutTime})
      </div>
    );
  }
  ```

---

### 2. DEFECT-ABS-002: Pemblokiran Presensi Masuk Akibat Kewajiban Koreksi Absen Hari Sebelumnya
- **Kode Skenario:** `@BUG-ABS-002 @DEFECT-ABS-002 @live` pada `absent_home_lean.feature`
- **Lokasi Halaman:** Antarmuka Dashboard Presensi Mobile (`/apps/absent`)
- **Deskripsi Masalah:**
  Jika seorang karyawan pada hari kerja sebelumnya lupa melakukan presensi keluar (*missing clock-out*), saat karyawan hendak melakukan presensi masuk di hari berikutnya, aplikasi menampilkan dialog peringatan pemblokiran: *"Anda wajib mengajukan koreksi absen hari kemarin sebelum dapat melakukan absen masuk hari ini"*. Tombol absen masuk dinonaktifkan sepenuhnya.
- **Dampak Bisnis & Operasional:**
  Kebijakan pemblokiran ini melanggar logika operasional (*user experience barrier*). Proses persetujuan koreksi absensi membutuhkan waktu review oleh Team Leader atau Atasan (yang belum tentu standby di pagi hari). Akibatnya, karyawan yang sudah hadir tepat waktu di kantor terpaksa terlambat mencatat kehadiran hari ini karena menunggu approval koreksi kemarin.
- **Error Stack Trace:**
  ```text
  java.lang.AssertionError: [DEFECT-ABS-002] Karyawan diwajibkan melakukan koreksi absensi terlebih dahulu sebelum diizinkan melakukan absen masuk di hari berikutnya! expected [false] but found [true]
  at org.testng.Assert.fail(Assert.java:111)
  at org.testng.Assert.assertFalse(Assert.java:79)
  at com.company.sqa.ui.steps.AbsentHomeSteps.karyawanDiizinkanMelakukanPresensiMasukTanpaDiblokirKewajibanKoreksiAbsensiHariSebelumnya(AbsentHomeSteps.java:92)
  at ✽.Karyawan diizinkan melakukan presensi masuk tanpa diblokir kewajiban koreksi absensi hari sebelumnya(file:///.../absent_home_lean.feature:35)
  ```
- **Rekomendasi Perbaikan (Business Logic Decoupling):**
  Pisahkan kewajiban administratif koreksi dengan hak pencatatan kehadiran berjalan:
  1. **Izinkan Absen Masuk Berjalan:** Karyawan tetap dapat melakukan Absen Masuk hari ini secara normal tanpa halangan.
  2. **Banner Pengingat Non-Blocking:** Tampilkan kartu peringatan kuning di bagian atas dashboard: *"Terdapat 1 catatan absensi kemarin yang belum lengkap. Silakan ajukan koreksi sebelum akhir periode cut-off payroll."*
  3. **Penegakan di Level Payroll (Bukan di Gate Presensi):** Jika hingga tanggal *cut-off* koreksi belum diajukan, barulah sistem payroll memotong tunjangan kehadiran secara otomatis.

---

### 3. DEFECT-ABS-003: Drawer Form Koreksi Absen Mengalami Hydration Lag
- **Kode Skenario:** `absent_correction_lean.feature:16`
- **Lokasi Halaman:** Antarmuka Koreksi Absensi (`/apps/absent/correction`)
- **Deskripsi Masalah:**
  Pada halaman riwayat koreksi absensi, saat pengguna menekan tombol *"Ajukan Koreksi"*, kontainer drawer modal `#drawer-ajukan-koreksi` terkadang tidak merespons klik atau mengalami *delay* render hingga 3–5 detik pada perangkat dengan latensi jaringan tinggi.
- **Akar Masalah (*Root Cause*):**
  Komponen drawer modal dimuat secara dinamis (*lazy load dynamic import*) saat tombol diklik tanpa adanya *pre-fetching* atau penanganan *loading skeleton*, sehingga event klik pertama hilang sebelum script selesai diunduh.
- **Rekomendasi Perbaikan:**
  Lakukan *pre-fetching* komponen formulir atau sertakan markup drawer di dalam static DOM dengan visibilitas CSS:
  ```javascript
  // Pasang event listener deterministik pada tombol koreksi
  document.getElementById("btn-ajukan-koreksi")?.addEventListener("click", () => {
    const drawer = document.getElementById("drawer-ajukan-koreksi");
    if (drawer) {
      drawer.style.display = "block";
      requestAnimationFrame(() => {
        drawer.classList.add("open");
      });
    }
  });
  ```

---

### 4. SEC-ABS-001: Verifikasi Anti-Spoofing GPS & Liveness Detection Swafoto
- **Kategori:** *Security & Fraud Prevention (Presensi Mobile)*
- **Deskripsi Celah:**
  Aplikasi mobile client mengirimkan payload koordinat latitude, longitude, dan berkas foto base64/multipart saat request presensi masuk `POST /api/absent/clock-in`. Server backend saat ini hanya memverifikasi radius geofence dasar tanpa memeriksa:
  1. Flag `isFromMockProvider` pada API Location Android.
  2. Metadata Exif foto (apakah foto diambil langsung dari sensor kamera atau diunggah dari galeri/emulator).
- **Dampak Keamanan:** Karyawan nakal dapat melakukan "absen titip" (*fake attendance*) dari rumah menggunakan aplikasi *Fake GPS* atau *Android Emulator* tanpa benar-benar hadir di lokasi kantor.
- **Rekomendasi Perbaikan (Backend Validation Guard):**
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

Untuk mereproduksi dan memvalidasi perbaikan pada modul Absensi:

```bash
# 1. Jalankan 10 Skenario Fungsional Bersih Domain A (100% BUILD SUCCESS):
mvn test -Dcucumber.filter.tags="@absen-mobile and not @live" -Dheadless=false

# 2. Jalankan Seluruh 12 Skenario Domain A (Termasuk Validasi 2 Defect Live):
mvn test -Dcucumber.filter.tags="@absen-mobile" -Dheadless=false

# 3. Jalankan Pengujian Khusus Validasi Cacat Tombol Absen Keluar (DEFECT-ABS-001):
mvn test -Dcucumber.filter.tags="@DEFECT-ABS-001" -Dheadless=false

# 4. Jalankan Pengujian Khusus Validasi Cacat Blokir Absen Masuk (DEFECT-ABS-002):
mvn test -Dcucumber.filter.tags="@DEFECT-ABS-002" -Dheadless=false
```
