# language: id
@api @register-v2-api @v2-api-upload
Fitur: Pengujian API Endpoint Upload Berkas Foto Karyawan v2
  Sebagai Client API / Frontend HADIR Management
  Saya ingin mengunggah berkas foto karyawan ke endpoint upload
  Agar berkas foto tervalidasi dan menghasilkan ID foto yang dapat direferensikan pada registrasi akun

  @PHOTO-016 @API-001
  Skenario: PHOTO-016 API-001 Mengunggah berkas foto format JPG valid
    Ketika client mengunggah berkas foto karyawan valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Maka status respons API upload adalah 200 atau 201
    Dan response API upload memuat ID foto yang terunggah

  @API-001
  Skenario: API-001 Mengunggah berkas foto format PNG valid
    Ketika client mengunggah berkas foto karyawan valid "src/test/resources/testdata/images/register/selfie-valid.png"
    Maka status respons API upload adalah 200 atau 201
    Dan response API upload memuat ID foto yang terunggah

  @API-002
  Skenario: API-002 Mengunggah request upload tanpa menyertakan berkas
    Ketika client mengunggah request upload tanpa berkas
    Maka status respons API upload adalah 400 atau 415

  @API-003
  Skenario: API-003 Mengunggah berkas berkas dilarang format TXT atau PDF
    Ketika client mengunggah berkas foto karyawan valid "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka status respons API upload adalah 400 atau 415

  @API-004
  Skenario: API-004 Mengunggah berkas gambar dengan ukuran melampaui batas 2 MB
    Ketika client mengunggah berkas foto karyawan valid "src/test/resources/testdata/images/register/large-photo-3mb.jpg"
    Maka status respons API upload adalah 413 atau 400
