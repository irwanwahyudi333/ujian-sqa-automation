# language: id
@ui @register-v2 @v2-name-validation
Fitur: D. Validasi Nama Karyawan Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memastikan field nama karyawan divalidasi dengan aturan penamaan yang benar
  Agar data nama karyawan tersimpan bersih tanpa angka, simbol terlarang, atau script berbahaya

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2
    Dan pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi email v2 "pascal.nametest@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"

  @NAME-001
  Skenario: NAME-001 Nama lengkap standar valid diterima
    Ketika pengguna mengisi nama karyawan v2 "Pascal SQA Engineer"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-002
  Skenario: NAME-002 Nama karyawan kosong ditolak
    Ketika pengguna mengisi nama karyawan v2 ""
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Nama Karyawan tidak boleh kosong"

  @NAME-003
  Skenario: NAME-003 Nama karyawan berisi spasi saja ditolak
    Ketika pengguna mengisi nama karyawan v2 "     "
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Nama Karyawan tidak boleh kosong"

  @NAME-004
  Skenario: NAME-004 Nama karyawan satu kata diterima
    Ketika pengguna mengisi nama karyawan v2 "Pascal"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-005
  Skenario: NAME-005 Nama karyawan diawali atau diakhiri spasi
    Ketika pengguna mengisi nama karyawan v2 " Pascal SQA "
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-006
  Skenario: NAME-006 Nama dengan spasi ganda di tengah
    Ketika pengguna mengisi nama karyawan v2 "Pascal  SQA   Engineer"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-007
  Skenario: NAME-007 Nama mengandung tanda baca wajar apostrof diterima
    Ketika pengguna mengisi nama karyawan v2 "Pascal D'Angelo"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-008
  Skenario: NAME-008 Nama mengandung gelar akademik titik koma
    Ketika pengguna mengisi nama karyawan v2 "Dr. Pascal, S.Kom."
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-009
  Skenario: NAME-009 Nama mengandung angka ditolak sistem
    Ketika pengguna mengisi nama karyawan v2 "Pascal123"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Nama Karyawan tidak boleh mengandung angka"

  @NAME-010
  Skenario: NAME-010 Nama mengandung simbol khusus ditolak sistem
    Ketika pengguna mengisi nama karyawan v2 "Pascal!@#$%"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Nama Karyawan tidak boleh mengandung simbol"

  @NAME-011
  Skenario: NAME-011 Nama sangat panjang 150 karakter
    Ketika pengguna mengisi nama karyawan v2 "Pascal SQA Engineer Automation Specialist Enterprise Solution Lead Senior Quality Assurance Engineer Testing Infrastructure"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-012
  Skenario: NAME-012 Nama melebihi batas panjang ditangani
    Ketika pengguna mengisi nama karyawan v2 "Pascal SQA Super Long Name That Exceeds Normal Database Length Expectation But Handled Gracefully Without Crashing Form State"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAME-013
  Skenario: NAME-013 Nama mengandung payload XSS disanitasi aman
    Ketika pengguna mengisi nama karyawan v2 "<script>alert('xss')</script>"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Nama Karyawan tidak boleh mengandung simbol"

  @NAME-014
  Skenario: NAME-014 Nama dengan karakter unicode internasional
    Ketika pengguna mengisi nama karyawan v2 "José René"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus
