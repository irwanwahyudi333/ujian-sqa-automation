# language: id
@ui @module-password-selfie
Fitur: Validasi Password dan Berkas Upload Foto Selfie
  Sebagai sistem keamanan aplikasi
  Saya ingin memastikan kata sandi dan berkas selfie yang diunggah memenuhi kriteria keamanan
  Agar akun terproteksi dan verifikasi biometrik valid

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @positive @file-002 @pass-002
  Skenario: Upload berkas foto selfie format JPG valid dengan password standar 8 karakter
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rd123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @file-003 @pass-007
  Skenario: Upload berkas foto selfie format PNG valid dengan password kombinasi kompleks
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User PNG"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rd#2026!"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.png"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @file-001
  Skenario: Pendaftaran tanpa memilih berkas selfie (pemeriksaan perilaku field selfie opsional)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test No File"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @negative @file-005 @lean
  Skenario: Validasi upload berkas format bukan gambar TXT
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Invalid File"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah berkas tidak valid "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka sistem menampilkan pesan peringatan bahwa file harus berupa gambar

  @negative @file-004
  Skenario: Validasi upload dokumen format PDF ditolak
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Invalid PDF"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah berkas tidak valid "src/test/resources/testdata/images/common/invalid-format.pdf"
    Maka sistem menampilkan pesan peringatan bahwa file harus berupa gambar

  @negative @file-015
  Skenario: Mengganti pilihan berkas upload berkali-kali
    Ketika pengguna mengunggah berkas tidak valid "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka sistem menampilkan pesan peringatan bahwa file harus berupa gambar
    Ketika pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Replace File"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @negative @pass-matrix
  Skenario konsep: Validasi variasi input password (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Password"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "<password_input>"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

    Contoh:
      | test_id  | password_input                                               | keterangan                          |
      | PASS-001 |                                                              | Password kosong                     |
      | PASS-003 | 123                                                          | Password terlalu pendek             |
      | PASS-004 | passwordhanyahuruf                                           | Password hanya huruf tanpa angka    |
      | PASS-005 | 12345678                                                     | Password hanya angka                |
      | PASS-006 | P@ssw0rdPanjangSekaliMelebihiBatasStandarAplikasi1234567890! | Password sangat panjang             |
      | PASS-008 | P@ss w0rd 123                                                | Password mengandung spasi           |
      | PASS-009 | P@ssw0rdé123                                                 | Password mengandung karakter nonASCII|
      | PASS-016 | ' OR '1'='1                                                  | Password payload SQL injection      |
      | PASS-017 |  P@ssw0rd123                                                 | Password diawali spasi              |
      | PASS-018 | !@#$%^&*()_+                                                 | Password seluruhnya karakter simbol |

  @pass-013
  Skenario: Karakter password tidak terhapus ketika fokus berpindah elemen
    Ketika pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna berpindah fokus ke elemen lain
    Maka nilai input password tetap tidak berubah "P@ssw0rdMagang123"

  @pass-014
  Skenario: Menempel teks ke dalam input password
    Ketika pengguna menyalin dan menempel teks ke dalam input password "P@ssw0rdMagang123"
    Maka nilai input password tetap tidak berubah "P@ssw0rdMagang123"

  @pass-015
  Skenario: Memeriksa elemen password di DOM memiliki atribut type password
    Maka atribut tipe elemen input password adalah password

  @negative @file-matrix
  Skenario konsep: Validasi upload berkas variasi khusus (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test File User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah berkas tidak valid "<berkas_path>"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

    Contoh:
      | test_id  | berkas_path                                                                                        | keterangan                     |
      | FILE-006 | src/test/resources/testdata/images/register/zero-byte.jpg                                          | Berkas gambar 0 byte           |
      | FILE-007 | src/test/resources/testdata/images/register/selfie-valid.jpg                                       | Berkas melebihi batas ukuran   |
      | FILE-008 | src/test/resources/testdata/images/register/selfie-valid.png                                       | Berkas resolusi tinggi         |
      | FILE-009 | src/test/resources/testdata/images/register/sample-long-name-image-file-for-testing-input-length-boundary.jpg | Berkas nama sangat panjang |
      | FILE-010 | src/test/resources/testdata/images/register/sample image with spaces.jpg                           | Berkas nama berspasi           |
      | FILE-011 | src/test/resources/testdata/images/register/sample.php.jpg                                         | Berkas ekstensi ganda          |
      | FILE-012 | src/test/resources/testdata/images/register/sample.gif                                             | Berkas traversal path          |
      | FILE-013 | src/test/resources/testdata/images/register/sample.gif                                             | Berkas format GIF              |
      | FILE-014 | src/test/resources/testdata/images/register/sample.svg                                             | Berkas format SVG              |

  @file-016 @file-017 @file-018
  Skenario: Interaksi visual dan konfirmasi berkas selfie pada antarmuka pendaftaran
    Ketika pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Maka seluruh elemen utama input pendaftaran terlihat

  @file-019 @file-020
  Skenario: Integritas berkas selfie saat dikirimkan bersama formulir pendaftaran
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test File Integrity"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @negative @file-022
  Skenario: Pengiriman formulir pendaftaran saat berkas selfie gagal diunggah
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Failed Upload"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan
