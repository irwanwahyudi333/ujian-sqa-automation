# language: id
@ui
Fitur: Pengujian Halaman Pendaftaran User (Register)
  Sebagai calon pengguna magang
  Saya ingin mendaftarkan akun baru melalui halaman registrasi
  Agar saya dapat mengakses sistem aplikasi Hadir

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @positive @reg-01
  Skenario: Registrasi pengguna baru berhasil dengan data valid dan foto selfie
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test User"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah foto selfie valid "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna menekan tombol Daftar
    Maka sistem menampilkan notifikasi pendaftaran berhasil atau respon sukses

  @positive @reg-02
  Skenario: Toggle visibilitas karakter password
    Ketika pengguna mengisi password "Rahasia123!"
    Maka karakter password dalam keadaan tersembunyi
    Ketika pengguna menekan tombol lihat password
    Maka karakter password ditampilkan
    Ketika pengguna menekan tombol lihat password kembali
    Maka karakter password dalam keadaan tersembunyi

  @positive @reg-03
  Skenario: Navigasi ke halaman login dari tautan pendaftaran
    Ketika pengguna menekan tautan masuk disini
    Maka pengguna dialihkan ke halaman login

  @negative @reg-04
  Skenario: Validasi formulir pendaftaran saat dikirim dalam keadaan kosong
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan pengiriman formulir dengan validasi field wajib

  @negative @reg-05
  Skenario: Validasi upload berkas dengan format bukan gambar
    Ketika pengguna mengisi data pendaftaran dengan NIK dari file excel
    Dan pengguna mengisi nama lengkap "SQA Test Invalid File"
    Dan pengguna mengisi email pendaftaran
    Dan pengguna mengisi password "P@ssw0rdMagang123"
    Dan pengguna mengunggah berkas tidak valid "src/test/resources/testdata/images/common/invalid-format.txt"
    Maka sistem menampilkan pesan peringatan bahwa file harus berupa gambar
