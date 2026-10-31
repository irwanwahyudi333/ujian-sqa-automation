# language: id
@ui @apps-absent @absent-leave @lean
Fitur: Pengujian UI Lean Pengajuan Cuti Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses modul cuti client (/apps/absent/leave-client)
  Untuk memeriksa antarmuka permohonan cuti dan membuka formulir modal pengajuan cuti

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/leave-client"

  @smoke
  Skenario: Verifikasi render halaman cuti dan tombol pengajuan
    Maka Halaman absent leave harus tampil dengan benar
    Dan Tombol ajukan cuti harus ditampilkan

  Skenario: Verifikasi pembukaan modal formulir pengajuan cuti
    Ketika Pengguna menekan tombol ajukan cuti
    Maka Modal drawer form cuti harus ditampilkan
    Dan Seluruh field formulir cuti harus tersedia

