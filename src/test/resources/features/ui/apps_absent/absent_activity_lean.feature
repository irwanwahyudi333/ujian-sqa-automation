# language: id
@ui @apps-absent @absent-activity @lean
Fitur: Pengujian UI Lean Riwayat Presensi Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses halaman riwayat presensi (/apps/absent/activity)
  Untuk memverifikasi daftar catatan kehadiran, foto check-in, dan navigasi kembali

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/activity"

  @smoke
  Skenario: Verifikasi render header dan tombol kembali riwayat absensi
    Maka Halaman absent activity harus tampil dengan benar
    Dan Tombol kembali pada history absensi harus ditampilkan

  Skenario: Verifikasi render kartu daftar riwayat kehadiran
    Maka Daftar kartu riwayat absensi harus ditampilkan dengan data valid
    Dan Jumlah kartu riwayat absensi harus lebih dari 0

