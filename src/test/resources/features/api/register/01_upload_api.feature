# language: id
@api @module-upload-api @lean
Fitur: Pengujian Endpoint REST API Upload Berkas Foto Selfie
  Sebagai sistem backend HADIR
  Saya ingin memvalidasi endpoint unggah foto selfie
  Agar hanya berkas gambar yang sah yang dapat disimpan di server

  @positive @api-001 @file-002
  Skenario: Upload berkas foto selfie format JPG valid melalui API
    Ketika client mengunggah file selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg" ke endpoint upload
    Maka response API mengembalikan status sukses 200 atau 201
    Dan response API memuat data id foto yang terunggah

  @positive @api-002 @file-003
  Skenario: Upload berkas foto selfie format PNG valid melalui API
    Ketika client mengunggah file selfie valid "src/test/resources/testdata/images/register/selfie-valid.png" ke endpoint upload
    Maka response API mengembalikan status sukses 200 atau 201
    Dan response API memuat data id foto yang terunggah

  @negative @api-011 @file-005
  Skenario: Upload berkas teks TXT ditolak oleh endpoint upload
    Ketika client mengunggah berkas non-gambar "src/test/resources/testdata/images/common/invalid-format.txt" ke endpoint upload
    Maka response API mengembalikan status penolakan 400 atau 500

  @negative @api-011 @file-004
  Skenario: Upload dokumen PDF ditolak oleh endpoint upload
    Ketika client mengunggah berkas non-gambar "src/test/resources/testdata/images/common/invalid-format.pdf" ke endpoint upload
    Maka response API mengembalikan status penolakan 400 atau 500

  @negative @api-015
  Skenario: Request JSON ke endpoint upload multipart FormData
    Ketika client mengirim request registrasi dengan header Content-Type salah "application/json"
    Maka response API mengembalikan status penolakan kesalahan klien atau server

  @security @sec-003
  Skenario: Upload file dengan ekstensi dimanipulasi
    Ketika client mengunggah berkas non-gambar "src/test/resources/testdata/images/register/sample.php.jpg" ke endpoint upload
    Maka response API mengembalikan status penolakan 400 atau 500

  @security @sec-004
  Skenario: Upload file dengan nama mengandung path traversal
    Ketika client mengunggah berkas non-gambar "src/test/resources/testdata/images/register/sample.gif" ke endpoint upload
    Maka response API mengembalikan status penolakan 400 atau 500

  @security @sec-013 @file-021
  Skenario: Verifikasi otorisasi akses file selfie yang telah terunggah
    Ketika client mengunggah file selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg" ke endpoint upload
    Dan response API memuat data id foto yang terunggah
    Maka header response API memiliki konfigurasi keamanan yang sesuai
