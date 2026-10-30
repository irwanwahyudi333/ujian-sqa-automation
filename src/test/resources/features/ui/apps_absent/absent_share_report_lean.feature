# language: id
@ui @apps-absent @absent-share-report @lean
Fitur: Pengujian UI Lean Modul Share Report Client Mobile
  Sebagai Karyawan atau Upliner
  Saya ingin mengakses modul share report (/apps/absent/reports/share_report)
  Untuk memverifikasi proteksi hak akses dan render komponen unduh laporan

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/reports/share_report"

  @smoke
  Skenario: Verifikasi akses halaman share report dengan kontrol otorisasi
    Maka Halaman share report atau halaman proteksi presensi harus ditampilkan
    Dan Header navigasi harus tersedia

  Skenario: Verifikasi formulir unduh laporan share report
    Ketika Pengguna berada pada halaman formulir share report
    Maka Tombol download report harus ditampilkan
