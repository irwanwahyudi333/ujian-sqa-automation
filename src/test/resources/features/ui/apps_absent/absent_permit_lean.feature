# language: id
@ui @apps-absent @absent-permit @lean
Fitur: Pengujian UI Lean Pengajuan Izin Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses modul pengajuan izin (/apps/absent/permit)
  Untuk memeriksa 3 tab kategori izin dan membuka formulir modal pengajuan izin

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/permit"

  @smoke
  Skenario: Verifikasi render halaman izin dan 3 tab kategori
    Maka Halaman absent permit harus tampil dengan benar
    Dan Seluruh tab kategori izin terlambat pulang cepat dan izin off harus ditampilkan

  Skenario: Verifikasi perpindahan tab kategori izin
    Ketika Pengguna berpindah ke tab izin "Pulang Cepat"
    Dan Pengguna berpindah ke tab izin "Izin Off"
    Dan Pengguna berpindah ke tab izin "Terlambat"
    Maka Tombol ajukan izin harus ditampilkan

  Skenario: Verifikasi pembukaan modal formulir pengajuan izin
    Ketika Pengguna menekan tombol ajukan izin
    Maka Modal drawer form izin harus ditampilkan
    Dan Seluruh field formulir izin harus tersedia

