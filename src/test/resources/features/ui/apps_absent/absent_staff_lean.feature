# language: id
@ui @apps-absent @absent-staff @lean
Fitur: Pengujian UI Lean Daftar Anggota Tim Client Mobile
  Sebagai Upliner atau Management
  Saya ingin mengakses modul staff (/apps/absent/staff)
  Untuk melihat daftar staf tim dan memastikan elemen halaman ter-render dengan baik

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/staff"

  @smoke
  Skenario: Verifikasi render halaman daftar staf tim
    Maka Halaman absent staff harus tampil dengan benar
    Dan Tombol kembali pada halaman staff harus ditampilkan

