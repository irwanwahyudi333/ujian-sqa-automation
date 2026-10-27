# language: id
@ui @register-v2 @v2-nik-validation
Fitur: C. Validasi NIK (8 Karakter Alfanumerik) Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memastikan field NIK mematuhi aturan tepat 8 karakter alfanumerik dan unik
  Agar integritas data identitas karyawan terjaga secara konsisten

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2
    Dan pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.niktest@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"

  @NIK-001
  Skenario: NIK-001 NIK valid alfanumerik D6240005
    Ketika pengguna mengisi NIK v2 "D6240005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-002
  Skenario: NIK-002 NIK valid alfanumerik D7240031
    Ketika pengguna mengisi NIK v2 "D7240031"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-003
  Skenario: NIK-003 NIK kosong ditolak sistem
    Ketika pengguna mengisi NIK v2 ""
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK tidak boleh kosong"

  @NIK-004
  Skenario: NIK-004 NIK kurang dari 8 karakter 7 digit ditolak
    Ketika pengguna mengisi NIK v2 "D624005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK harus 8 karakter alfanumerik"

  @NIK-005
  Skenario: NIK-005 NIK lebih dari 8 karakter 9 digit ditolak
    Ketika pengguna mengisi NIK v2 "D62400055"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK harus 8 karakter alfanumerik"

  @NIK-006
  Skenario: NIK-006 NIK 8 karakter angka saja diproses
    Ketika pengguna mengisi NIK v2 "12345678"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-007
  Skenario: NIK-007 NIK 8 karakter huruf saja diproses
    Ketika pengguna mengisi NIK v2 "ABCDEFGH"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-008
  Skenario: NIK-008 NIK mengandung simbol strip ditolak
    Ketika pengguna mengisi NIK v2 "D624-005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK hanya boleh karakter alfanumerik"

  @NIK-009
  Skenario: NIK-009 NIK mengandung spasi di tengah ditolak
    Ketika pengguna mengisi NIK v2 "D624 005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK hanya boleh karakter alfanumerik"

  @NIK-010
  Skenario: NIK-010 NIK diawali spasi disanitasi atau divalidasi
    Ketika pengguna mengisi NIK v2 " D6240005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-011
  Skenario: NIK-011 NIK diakhiri spasi disanitasi atau divalidasi
    Ketika pengguna mengisi NIK v2 "D6240005 "
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-012
  Skenario: NIK-012 NIK huruf kecil dinormalisasi secara konsisten
    Ketika pengguna mengisi NIK v2 "d6240005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-013
  Skenario: NIK-013 NIK kombinasi huruf besar dan kecil
    Ketika pengguna mengisi NIK v2 "d624Ab05"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @NIK-014
  Skenario: NIK-014 NIK mengandung karakter non ASCII beraksen ditolak
    Ketika pengguna mengisi NIK v2 "D62400é5"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK hanya boleh karakter alfanumerik"

  @NIK-015
  Skenario: NIK-015 NIK mengandung karakter emoji ditolak aman
    Ketika pengguna mengisi NIK v2 "D624😀05"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK hanya boleh karakter alfanumerik"

  @NIK-016
  Skenario: NIK-016 NIK duplikat yang sudah terdaftar ditolak backend
    Ketika pengguna mengisi NIK v2 "D6240001"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK sudah terdaftar"

  @NIK-017
  Skenario: NIK-017 NIK duplikat variasi huruf kecil tetap ditolak
    Ketika pengguna mengisi NIK v2 "d6240001"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK sudah terdaftar"

  @NIK-018
  Skenario: NIK-018 NIK berupa payload SQL Injection ditolak aman
    Ketika pengguna mengisi NIK v2 "' OR '1'='1"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK harus 8 karakter alfanumerik"

  @NIK-019
  Skenario: NIK-019 NIK berupa string panjang ekstrem ditolak batas 8 karakter
    Ketika pengguna mengisi NIK v2 "D6240005D6240005D6240005"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK harus 8 karakter alfanumerik"

  @NIK-020
  Skenario: NIK-020 NIK berupa karakter spasi saja ditolak
    Ketika pengguna mengisi NIK v2 "        "
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK tidak boleh kosong"
