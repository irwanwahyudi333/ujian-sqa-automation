# language: id
@ui @module-name-email
Fitur: Validasi Field Nama Lengkap dan Email pada Formulir Pendaftaran
  Sebagai sistem verifikasi pendaftaran
  Saya ingin memastikan format nama lengkap dan alamat email yang diinput valid
  Agar komunikasi dan verifikasi akun dapat berjalan dengan baik

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @positive @name-001 @email-001
  Skenario: Pendaftaran menggunakan nama lengkap dan email valid
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "Budi Santoso"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @name-valid-variations
  Skenario konsep: Pendaftaran dengan variasi nama yang sah (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "<nama_input>"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

    Contoh:
      | test_id  | nama_input  | keterangan                             |
      | NAME-005 | Budi        | Nama hanya satu kata                   |
      | NAME-006 | D'Angelo    | Nama dengan tanda apostrof/tanda hubung|
      | NAME-007 | José        | Nama dengan karakter aksen non-ASCII   |

  @positive @email-valid-variations
  Skenario konsep: Pendaftaran dengan variasi alamat email yang sah (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email "<email_input>"
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

    Contoh:
      | test_id   | email_input                   | keterangan                          |
      | EMAIL-008 | user@corp.co.id               | Email dengan subdomain korporat     |
      | EMAIL-009 | user+sqa@backyr.com           | Email dengan tag karakter plus (+)  |
      | EMAIL-010 | User.QA@backyr.com            | Email dengan kombinasi huruf besar  |

  @negative @name-matrix
  Skenario konsep: Validasi variasi input nama lengkap tidak valid (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "<nama_input>"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

    Contoh:
      | test_id  | nama_input                                                                                                  | keterangan                           |
      | NAME-002 |                                                                                                             | Nama kosong                          |
      | NAME-003 |                                                                                                             | Nama spasi saja                      |
      | NAME-004 |  Budi Santoso                                                                                               | Nama diawali spasi                   |
      | NAME-008 | Budi123                                                                                                     | Nama mengandung angka                |
      | NAME-009 | Budi@#$%!                                                                                                   | Nama berisi simbol liar              |
      | NAME-010 | NamaPenggunaSangatPanjangSekaliMelebihiBatasWajarKarakterInputFormulirPendaftaranSistemAplikasiHadirMagang | Nama panjang melebihi batas wajar    |
      | NAME-011 | <script>alert(1)</script>                                                                                  | Nama payload script XSS              |
      | NAME-012 | עִבְרִית                                                                                                   | Nama karakter non-Latin / RTL        |

  @negative @email-matrix
  Skenario konsep: Validasi variasi format email tidak valid (<test_id> - <keterangan>)
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Email"
    Dan pengguna mengisi email "<email_input>"
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

    Contoh:
      | test_id   | email_input                                                                      | keterangan                             |
      | EMAIL-002 |                                                                                  | Email kosong                           |
      | EMAIL-003 | qa-invalid-email                                                                 | Email tanpa simbol @                   |
      | EMAIL-004 | qa-user@                                                                         | Email tanpa domain                     |
      | EMAIL-005 | @backyr.com                                                                      | Email tanpa username (local-part)      |
      | EMAIL-006 | qa@@backyr.com                                                                   | Email dengan dua simbol @              |
      | EMAIL-007 | qa test@backyr.com                                                               | Email dengan spasi di tengah           |
      | EMAIL-011 | qa.user.very.long.email.address.beyond.reasonable.limit.for.testing@backyr.com   | Email panjang melebihi batas wajar     |
      | EMAIL-012 | qa@localhost                                                                     | Email domain lokal tanpa titik         |
      | EMAIL-013 | user@domain                                                                      | Email tanpa top level domain           |
      | EMAIL-014 | raxaf58877@backyr.com                                                            | Email duplikat yang sudah terdaftar    |
      | EMAIL-015 | usér@backyr.com                                                                  | Email mengandung karakter beraksen     |
      | EMAIL-016 | \"user\"@backyr.com                                                              | Email dengan tanda kutip ganda         |
      | EMAIL-017 | ' OR '1'='1@backyr.com                                                           | Email payload SQL injection            |
      | EMAIL-018 | .user@backyr.com                                                                 | Email diawali tanda titik              |
