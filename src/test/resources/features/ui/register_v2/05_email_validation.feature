# language: id
@ui @register-v2 @v2-email-validation
Fitur: E. Validasi Email Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memastikan field email mematuhi standar format RFC dan unik di sistem
  Agar notifikasi dan kredensial login terkirim ke alamat email yang valid dan tidak terjadi akun ganda

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2
    Dan pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"

  @EMAIL-001
  Skenario: EMAIL-001 Email valid belum terdaftar diterima
    Ketika pengguna mengisi email v2 "pascal.email1@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-002
  Skenario: EMAIL-002 Email kosong ditolak sistem
    Ketika pengguna mengisi email v2 ""
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Email tidak boleh kosong"

  @EMAIL-003
  Skenario: EMAIL-003 Email tanpa simbol at ditolak
    Ketika pengguna mengisi email v2 "pascal.dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-004
  Skenario: EMAIL-004 Email tanpa nama domain ditolak
    Ketika pengguna mengisi email v2 "pascal@"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-005
  Skenario: EMAIL-005 Email tanpa username local part ditolak
    Ketika pengguna mengisi email v2 "@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-006
  Skenario: EMAIL-006 Email dengan simbol at ganda ditolak
    Ketika pengguna mengisi email v2 "pascal@@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-007
  Skenario: EMAIL-007 Email mengandung spasi di tengah ditolak
    Ketika pengguna mengisi email v2 "pascal test@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-008
  Skenario: EMAIL-008 Email diawali atau diakhiri spasi disanitasi
    Ketika pengguna mengisi email v2 " pascal@dikahadir.com "
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-009
  Skenario: EMAIL-009 Email menggunakan subdomain valid
    Ketika pengguna mengisi email v2 "pascal@qa.dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-010
  Skenario: EMAIL-010 Email menggunakan tanda plus tagging
    Ketika pengguna mengisi email v2 "pascal+test@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-011
  Skenario: EMAIL-011 Email menggunakan huruf kapital dinormalisasi
    Ketika pengguna mengisi email v2 "Pascal.QA@DikaHadir.COM"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-012
  Skenario: EMAIL-012 Email domain tanpa TLD titik ditolak
    Ketika pengguna mengisi email v2 "pascal@localhost"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-013
  Skenario: EMAIL-013 Email duplikat yang sudah terdaftar ditolak
    Ketika pengguna mengisi email v2 "duplikat@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Email sudah terdaftar"

  @EMAIL-014
  Skenario: EMAIL-014 Email duplikat variasi huruf besar tetap ditolak
    Ketika pengguna mengisi email v2 "DUPLIKAT@DIKAHADIR.COM"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Email sudah terdaftar"

  @EMAIL-015
  Skenario: EMAIL-015 Email disposable domain ditangani terkontrol
    Ketika pengguna mengisi email v2 "pascal@mailinator.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-016
  Skenario: EMAIL-016 Email sangat panjang
    Ketika pengguna mengisi email v2 "pascal.sqa.automation.engineer.lead.testing.specialist.very.long.email@dikahadir.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @EMAIL-017
  Skenario: EMAIL-017 Email berisi SQL Injection ditolak format
    Ketika pengguna mengisi email v2 "' OR '1'='1'@test.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"

  @EMAIL-018
  Skenario: EMAIL-018 Email berisi tag script ditolak format
    Ketika pengguna mengisi email v2 "<script>@test.com"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Format email tidak valid"
