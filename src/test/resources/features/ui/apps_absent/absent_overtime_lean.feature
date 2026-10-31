# language: id
@ui @apps-absent @absent-overtime @lean
Fitur: Pengujian UI Lean Pengajuan Lembur Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses modul lembur client (/apps/absent/overtime-client)
  Untuk memeriksa antarmuka lembur dan membuka formulir modal pengajuan jam lembur

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/overtime-client"

  @smoke
  Skenario: Verifikasi render halaman lembur dan tombol pengajuan
    Maka Halaman absent overtime harus tampil dengan benar
    Dan Tombol ajukan lembur harus ditampilkan

  Skenario: Verifikasi pembukaan modal formulir pengajuan lembur
    Ketika Pengguna menekan tombol ajukan lembur
    Maka Modal drawer form lembur harus ditampilkan
    Dan Seluruh field formulir lembur harus tersedia

