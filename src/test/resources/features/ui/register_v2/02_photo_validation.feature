# language: id
@ui @register-v2 @v2-photo-validation
Fitur: B. Validasi Upload Foto Karyawan Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin sistem memvalidasi berkas foto karyawan yang diunggah
  Agar hanya berkas gambar valid di bawah 2 MB yang diterima sistem

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @PHOTO-001
  Skenario: PHOTO-001 Submit form tanpa mengunggah foto karyawan
    Ketika pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.photo1@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @PHOTO-002
  Skenario: PHOTO-002 Unggah foto format JPG valid di bawah 2 MB
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-003
  Skenario: PHOTO-003 Unggah foto format PNG valid di bawah 2 MB
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.png"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-004
  Skenario: PHOTO-004 Unggah foto format JPEG valid di bawah 2 MB
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-005
  Skenario: PHOTO-005 Unggah berkas dokumen TXT ditolak sistem
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka muncul notifikasi snackbar "* File harus berupa gambar"

  @PHOTO-006
  Skenario: PHOTO-006 Unggah berkas dokumen PDF ditolak sistem
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/common/invalid-format.pdf"
    Maka muncul notifikasi snackbar "* File harus berupa gambar"

  @PHOTO-007
  Skenario: PHOTO-007 Unggah foto dengan ukuran melebihi batas 2 MB
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/large-photo-3mb.jpg"
    Maka muncul notifikasi snackbar "Ukuran berkas melebihi batas maksimal 2 MB"

  @PHOTO-008
  Skenario: PHOTO-008 Unggah foto dengan ukuran batas presisi di bawah 2 MB
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-009
  Skenario: PHOTO-009 Mengganti foto yang telah dipilih dengan foto baru
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.png"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-010
  Skenario: PHOTO-010 Menghapus foto melalui tombol remove
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menghapus foto karyawan yang dipilih
    Maka thumbnail pratinjau foto karyawan tertutup

  @PHOTO-011
  Skenario: PHOTO-011 Submit setelah foto dihapus ditolak sistem
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menghapus foto karyawan yang dipilih
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @PHOTO-012
  Skenario: PHOTO-012 Unggah berkas kosong 0 byte ditolak
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/zero-byte.jpg"
    Maka muncul notifikasi snackbar "* File harus berupa gambar"

  @PHOTO-013
  Skenario: PHOTO-013 Unggah berkas gambar korup ditangani secara terkontrol
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka muncul notifikasi snackbar "* File harus berupa gambar"

  @PHOTO-014
  Skenario: PHOTO-014 Unggah berkas dengan nama panjang dan spasi berhasil
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/sample image with spaces.jpg"
    Maka thumbnail pratinjau foto karyawan ditampilkan

  @PHOTO-015
  Skenario: PHOTO-015 Verifikasi teks petunjuk ukuran file upload
    Maka teks petunjuk ukuran foto memuat "* File harus dibawah 2 MB"
