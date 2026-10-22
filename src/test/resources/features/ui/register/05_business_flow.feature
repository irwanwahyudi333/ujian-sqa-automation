# language: id
@ui @module-business-flow
Fitur: Pengujian Alur Bisnis dan Submit Formulir Pendaftaran
  Sebagai calon pengguna magang
  Saya ingin mengirimkan formulir registrasi
  Agar sistem memproses pembuatan akun sesuai aturan bisnis pendaftaran

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @positive @reg-006 @reg-021 @lean
  Skenario: Pendaftaran berhasil dengan seluruh field lengkap dan valid
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @negative @reg-001 @lean
  Skenario: Pengiriman formulir pendaftaran saat seluruh field kosong
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan pengiriman formulir dengan validasi field wajib

  @negative @reg-002
  Skenario: Pengiriman formulir hanya mengisi NIK valid
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @reg-003
  Skenario: Pengiriman formulir hanya mengisi nama lengkap valid
    Ketika pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @reg-004
  Skenario: Pengiriman formulir hanya mengisi email valid
    Ketika pengguna mengisi email pendaftaran
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @reg-005
  Skenario: Pengiriman formulir hanya mengisi password valid
    Ketika pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @reg-007
  Skenario: Pengiriman formulir dengan satu field tidak valid
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email dengan format salah "qa-invalid-email.com"
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan pengiriman formulir dengan validasi format email

  @reg-008 @reg-010
  Skenario: Pengiriman formulir dengan menekan tombol submit dua kali cepat
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar dua kali dengan cepat
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @reg-009
  Skenario: Pengiriman formulir dengan menekan tombol Enter pada field password
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Enter User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Enter pada field password
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @reg-018
  Skenario: Pendaftaran menggunakan NIK yang sudah terdaftar tetapi email baru (Alur Pending Approval Admin)
    Ketika pengguna mengisi NIK "D6240005"
    Dan pengguna mengisi nama lengkap "Pengguna Baru NIK Sama"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menerima pendaftaran dalam status pending approval admin

  @negative @reg-019
  Skenario: Pendaftaran menggunakan email yang sudah terdaftar tetapi NIK baru
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Existing Email"
    Dan pengguna mengisi email "raxaf58877@backyr.com"
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @reg-020
  Skenario: Pendaftaran menggunakan kombinasi NIK dan email yang keduanya sudah terdaftar
    Ketika pengguna mengisi NIK "D6240005"
    Dan pengguna mengisi nama lengkap "Pengguna Terdaftar"
    Dan pengguna mengisi email "raxaf58877@backyr.com"
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-011
  Skenario: Memuat ulang halaman setelah mengisi sebagian field formulir
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Half Filled User"
    Ketika pengguna memuat ulang halaman pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat

  @reg-012
  Skenario: Menekan tombol kembali setelah proses submit
    Ketika pengguna menekan tombol kembali pada browser
    Maka seluruh elemen utama input pendaftaran terlihat

  @reg-013
  Skenario: Membuka formulir pendaftaran di beberapa tab browser secara paralel
    Ketika pengguna membuka halaman pendaftaran di tab browser baru
    Maka formulir pendaftaran di tab baru dapat diakses secara independen

  @reg-016
  Skenario: Pendaftaran dengan data NIK dan Nama diapit spasi (auto-trimming)
    Ketika pengguna mengisi NIK " D6240005 "
    Dan pengguna mengisi nama lengkap " SQA Trimming User "
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-014 @resilience
  Skenario: Pengiriman formulir pendaftaran saat koneksi internet terputus
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Offline User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-015 @resilience
  Skenario: Pengiriman formulir saat server merespons lambat atau timeout
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Timeout User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-017
  Skenario: Pendaftaran menggunakan kombinasi karakter huruf besar dan kecil pada input
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "Budi SanToso"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @reg-022
  Skenario: Pemeriksaan keterkaitan pesan kesalahan pada field formulir tertentu
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-023
  Skenario: Formulir mempertahankan nilai input data non-sensitif setelah submit gagal
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Persistent Input"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @reg-024 @reg-025 @e2e
  Skenario: Pendaftaran akun baru lalu navigasi menuju proses autentikasi
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA EndToEnd User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses
