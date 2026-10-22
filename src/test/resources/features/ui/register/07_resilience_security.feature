# language: id
@ui @module-resilience-security
Fitur: Pengujian Ketahanan (Resilience) dan Keamanan Antarmuka Pendaftaran
  Sebagai tim Quality Assurance dan Keamanan Sistem
  Saya ingin memastikan antarmuka pendaftaran tangguh menghadapi error dan menerapkan standar keamanan
  Agar sistem andal dan data pendaftar aman dari ancaman siber

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @security @sec-008
  Skenario: Memeriksa transmisi halaman pendaftaran menggunakan koneksi HTTPS aman
    Maka koneksi halaman pendaftaran menggunakan protokol HTTPS terenkripsi

  @resilience @res-005
  Skenario: Pengiriman formulir berulang kali setelah penolakan validasi tidak mengunci form
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Retry User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @resilience @res-008 @repeatability
  Skenario: Pendaftaran independen untuk menjamin repeatability suite pengujian
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Repeatable User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @security @sec-007 @res-007
  Skenario: Pemeriksaan keamanan log dan laporan pengujian tidak mengekspos password plaintext
    Ketika pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna berpindah fokus ke elemen lain
    Maka nilai input password tetap tidak berubah "P@ssw0rdMagang123"

  @resilience @res-001 @res-002
  Skenario: Ketahanan UI dalam menangani respon lambat atau abnormal dari backend
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @resilience @res-003 @res-004
  Skenario: Penanganan UI saat terjadi gangguan koneksi jaringan pada form registrasi
    Ketika pengguna memuat ulang halaman pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat

  @resilience @res-006
  Skenario: Konsistensi aturan validasi antara antarmuka dan backend pendaftaran
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @resilience @res-009 @res-010
  Skenario: Isolasi data uji dan verifikasi kebersihan data pengujian
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Data Isolation User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses
