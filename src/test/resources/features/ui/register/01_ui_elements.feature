# language: id
@ui @module-ui-elements
Fitur: Pengujian Elemen UI dan Tampilan Halaman Pendaftaran
  Sebagai calon pengguna magang
  Saya ingin memastikan seluruh elemen antarmuka pendaftaran tersedia dan berfungsi
  Agar proses pendaftaran dapat dilakukan dengan lancar

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @ui-001
  Skenario: Membuka halaman pendaftaran melalui URL yang benar
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-002
  Skenario: Memverifikasi ketersediaan seluruh field input utama formulir pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-004
  Skenario: Memverifikasi ketersediaan dan fungsi visual tombol Daftar
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-009
  Skenario: Memverifikasi keberadaan tautan masuk disini pada formulir
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-003
  Skenario: Memeriksa label dan placeholder pada seluruh field formulir pendaftaran
    Maka placeholder seluruh field pendaftaran tampil dengan benar

  @ui-006 @pass-010
  Skenario: Memeriksa kondisi karakter password saat awal halaman dibuka
    Ketika pengguna mengisi password "Rahasia123!"
    Maka karakter password dalam keadaan tersembunyi

  @ui-007 @pass-011
  Skenario: Menampilkan password menggunakan toggle ikon mata
    Ketika pengguna mengisi password "Rahasia123!"
    Dan pengguna menekan tombol lihat password
    Maka karakter password ditampilkan

  @ui-008 @pass-012
  Skenario: Mengganti visibilitas password berulang kali secara konsisten
    Ketika pengguna mengisi password "Rahasia123!"
    Dan pengguna menekan tombol lihat password
    Maka karakter password ditampilkan
    Ketika pengguna menekan tombol lihat password kembali
    Maka karakter password dalam keadaan tersembunyi

  @ui-010
  Skenario: Memuat ulang halaman pendaftaran
    Ketika pengguna memuat ulang halaman pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-011
  Skenario: Buka halaman pendaftaran dengan zoom browser 200 persen
    Ketika pengguna mengatur zoom browser sebesar 200 persen
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-012
  Skenario: Memeriksa konsol browser saat membuka halaman
    Maka log konsol browser tidak memuat kesalahan javascript kritis

  @ui-005 @nav-001
  Skenario: Navigasi ke halaman login melalui tautan masuk disini
    Ketika pengguna menekan tautan masuk disini
    Maka pengguna dialihkan ke halaman login

  @nav-002
  Skenario: Navigasi kembali ke halaman daftar dari halaman login
    Ketika pengguna menekan tautan masuk disini
    Maka pengguna dialihkan ke halaman login
    Ketika pengguna menekan tombol kembali pada browser
    Maka seluruh elemen utama input pendaftaran terlihat

  @ui-013
  Skenario: Navigasi elemen formulir pendaftaran menggunakan tombol Tab keyboard
    Ketika pengguna menekan tombol Tab pada keyboard
    Maka fokus berpindah ke elemen input formulir berikutnya

  @ui-014
  Skenario: Menempel teks ke dalam field formulir dari clipboard
    Ketika pengguna menempelkan teks "D6240005" ke dalam input NIK
    Maka nilai input NIK sesuai teks yang ditempelkan "D6240005"

  @ui-015
  Skenario: Memastikan label formulir terhubung dengan field input yang tepat
    Maka seluruh input formulir terhubung dengan elemen label yang valid
