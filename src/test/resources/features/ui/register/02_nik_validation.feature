# language: id
@ui @module-nik
Fitur: Validasi Format dan Nilai NIK pada Formulir Pendaftaran
  Sebagai sistem verifikasi pendaftaran
  Saya ingin memastikan NIK yang diinput sesuai aturan 8 karakter alfanumerik
  Agar integritas data identitas pengguna magang terjamin

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @positive @nik-001
  Skenario: Pendaftaran menggunakan NIK valid resmi dari berkas Excel
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @nik-016
  Skenario: Pendaftaran menggunakan NIK valid 8 karakter lainnya dari berkas Excel
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Secondary User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @nik-017
  Skenario: Pendaftaran orang berbeda menggunakan NIK yang sama (Alur Pending Approval Admin)
    Ketika pengguna mengisi NIK "D6240005"
    Dan pengguna mengisi nama lengkap "Pengguna Baru NIK Sama"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menerima pendaftaran dalam status pending approval admin

  @negative @nik-matrix
  Skenario konsep: Pengujian variasi format NIK tidak valid (<test_id> - <keterangan>)
    Ketika pengguna mengisi NIK "<nik_input>"
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

    Contoh:
      | test_id | nik_input   | keterangan                                |
      | NIK-002 | D624005     | NIK 7 karakter (kurang dari 8 char)       |
      | NIK-003 | D62400055   | NIK 9 karakter (melebihi 8 char)          |
      | NIK-004 |             | NIK kosong                                |
      | NIK-005 | 12345678    | NIK hanya angka 8 digit                   |
      | NIK-006 | ABCDEFGH    | NIK hanya huruf 8 char                    |
      | NIK-007 | D624-005    | NIK mengandung simbol tanda hubung        |
      | NIK-008 | D624 005    | NIK mengandung spasi di tengah            |
      | NIK-009 |  D6240005   | NIK diawali spasi                         |
      | NIK-010 | D6240005    | NIK diakhiri spasi                        |
      | NIK-011 | d6240005    | NIK seluruhnya huruf kecil                |
      | NIK-012 | d624Ab05    | NIK kombinasi huruf besar dan kecil       |
      | NIK-013 | D62400é5    | NIK mengandung karakter non-ASCII         |
      | NIK-014 | D624😀05    | NIK mengandung karakter emoji             |
      | NIK-015 | D624\t005   | NIK mengandung whitespace karakter khusus |
      | NIK-018 | d6240005    | NIK case sensitivity duplikasi            |
      | NIK-019 | ' OR '1'='1 | NIK payload SQL injection                 |
