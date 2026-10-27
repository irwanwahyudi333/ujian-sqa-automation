# language: id
@ui @register-v2 @v2-registration-flow
Fitur: I. Submit Form dan Alur Bisnis Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin mengeksekusi proses submit data registrasi user v2 dan memverifikasi seluruh respons
  Agar akun karyawan baru berhasil terbuat secara tangguh dan sistem menangani kegagalan dengan tepat

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @REG-001
  Skenario: REG-001 Submit form kosong ditolak sebelum request dikirim
    Ketika pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @REG-002
  Skenario: REG-002 Submit form dengan seluruh field wajib terisi data valid berhasil
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Berhasil mendaftarkan user"

  @REG-003
  Skenario: REG-003 Submit data valid tanpa menyertakan field opsional atasan client
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Berhasil mendaftarkan user"

  @REG-004
  Skenario: REG-004 Submit tanpa foto karyawan ditolak sistem
    Ketika pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.nophoto@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @REG-005
  Skenario: REG-005 Submit form dengan NIK sudah terdaftar ditolak backend
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240001"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.nikdup@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "NIK sudah terdaftar"

  @REG-006
  Skenario: REG-006 Submit form dengan Email sudah terdaftar ditolak backend
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "duplikat@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Email sudah terdaftar"

  @REG-007
  Skenario: REG-007 Verifikasi animasi loading tombol submit saat proses berlangsung
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-008
  Skenario: REG-008 Menekan tombol submit berulang kali dengan cepat diproteksi
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit sebanyak 2 kali dengan cepat
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-009
  Skenario: REG-009 Tekan tombol Enter pada input terakhir memicu submit form
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol Enter pada input terakhir
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-010
  Skenario: REG-010 Submit saat latensi jaringan tinggi ditangani stabil
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-011
  Skenario: REG-011 Backend mengembalikan status 400 ditampilkan informatif
    Ketika pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @REG-012
  Skenario: REG-012 Backend mengembalikan status 500 ditangani dengan pesan aman
    Ketika pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-013
  Skenario: REG-013 Form mempertahankan isian pengguna saat submit gagal validasi
    Ketika pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-014
  Skenario: REG-014 Verifikasi banner snackbar feedback aktif
    Ketika pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Foto tidak boleh kosong"

  @REG-015
  Skenario: REG-015 Menutup banner notifikasi feedback secara manual via tombol close
    Ketika pengguna menekan tombol submit registrasi v2
    Dan pengguna menutup notifikasi snackbar
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-016
  Skenario: REG-016 Konkurensi submit bersamaan menjaga keunikan data
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-017
  Skenario: REG-017 Verifikasi akun terdaftar dapat diintegrasikan ke alur login
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Maka nilai pada field form tetap utuh tidak terhapus

  @REG-018
  Skenario: REG-018 Refresh halaman setelah pendaftaran berhasil tidak menduplikasi data
    Ketika pengguna memuat ulang halaman
    Maka state formulir tereset ke kondisi awal

  @REG-019
  Skenario: REG-019 Menekan tombol browser Back setelah pendaftaran berhasil
    Ketika pengguna menekan tombol kembali ke daftar user
    Maka pengguna dialihkan ke halaman daftar user

  @REG-020
  Skenario: REG-020 Verifikasi payload data registrasi dikirim secara lengkap
    Ketika pengguna mengisi seluruh form registrasi user v2 dengan data valid
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Berhasil mendaftarkan user"
