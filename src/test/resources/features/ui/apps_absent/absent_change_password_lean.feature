# language: id
@ui @apps-absent @absent-change-password @lean
Fitur: Pengujian UI Lean Ubah Kata Sandi Client Mobile
  Sebagai Pengguna Client Hadir
  Saya ingin mengakses modul ganti password (/apps/absent/change-password)
  Untuk mengganti kata sandi akun dan memverifikasi toggle visibilitas password serta validasi form

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/change-password"

  @smoke
  Skenario: Verifikasi render halaman ubah kata sandi
    Maka Halaman absent change password harus tampil dengan benar
    Dan Tombol kembali pada change password harus ditampilkan
    Dan Seluruh field formulir change password harus tersedia

  Skenario: Verifikasi fungsionalitas toggle visibilitas password
    Ketika Pengguna memasukkan password baru "Rahasia123!"
    Dan Pengguna menekan toggle visibilitas password baru
    Maka Tipe input password baru harus berubah menjadi "text"
    Dan Pengguna menekan kembali toggle visibilitas password baru
    Maka Tipe input password baru harus berubah menjadi "password"
