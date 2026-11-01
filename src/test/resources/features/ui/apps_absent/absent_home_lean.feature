# language: id
@ui @apps-absent @absent-home @lean
Fitur: Pengujian UI Lean Dashboard Presensi Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses halaman utama presensi (/apps/absent)
  Untuk memastikan profil, metrik kehadiran, navigasi menu cepat, riwayat, dan tombol absen tampil dengan benar

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent"

  @smoke
  Skenario: Verifikasi render elemen profil dan statistik kehadiran utama
    Maka Halaman absent home harus tampil dengan benar
    Dan Sapaan dan subjudul motivasi pengguna harus ditampilkan
    Dan Seluruh kartu metrik kehadiran bulanan harus ditampilkan

  Skenario: Verifikasi ketersediaan menu navigasi cepat dan tombol absen
    Maka Seluruh ikon menu pintas presensi harus ditampilkan
    Dan Bagian history absensi singkat harus ditampilkan
    Dan Tombol absen masuk harus ditampilkan

  Skenario: Navigasi dari menu pintas ke modul riwayat presensi
    Ketika Pengguna menekan tautan selengkapnya pada history absensi
    Maka Pengguna harus diarahkan ke halaman history absensi

  @BUG-ABS-001 @DEFECT-ABS-001 @live
  Skenario: Verifikasi ketersediaan dan transisi tombol Absen Keluar setelah presensi masuk
    Ketika Pengguna menyelesaikan proses Absen Masuk dengan swafoto
    Maka Tombol aksi presensi utama harus bertransisi menjadi "Absen Keluar"
    Dan Tombol aksi presensi tidak boleh menghilang dari layar

  @BUG-ABS-002 @DEFECT-ABS-002 @live
  Skenario: Verifikasi hak akses presensi masuk mandiri tanpa blokir koreksi absensi hari sebelumnya
    Ketika Karyawan hendak melakukan absen masuk pada hari kerja berikutnya
    Maka Karyawan diizinkan melakukan presensi masuk tanpa diblokir kewajiban koreksi absensi hari sebelumnya


