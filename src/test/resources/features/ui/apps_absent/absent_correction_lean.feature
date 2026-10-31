# language: id
@ui @apps-absent @absent-correction @lean
Fitur: Pengujian UI Lean Koreksi Absensi Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses modul koreksi absensi (/apps/absent/correction)
  Untuk memeriksa riwayat koreksi dan membuka formulir modal pengajuan koreksi jam masuk/keluar

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/correction"

  @smoke
  Skenario: Verifikasi render halaman koreksi dan tombol pengajuan
    Maka Halaman absent correction harus tampil dengan benar
    Dan Tombol ajukan koreksi harus ditampilkan

  Skenario: Verifikasi pembukaan modal formulir pengajuan koreksi
    Ketika Pengguna menekan tombol ajukan koreksi
    Maka Modal drawer form koreksi absen harus ditampilkan
    Dan Seluruh field formulir koreksi absen harus tersedia

