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

