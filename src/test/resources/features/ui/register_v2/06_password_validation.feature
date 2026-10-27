# language: id
@ui @register-v2 @v2-password-validation
Fitur: F. Validasi Password dan Toggle Visibilitas Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memastikan keamanan password, fungsionalitas toggle intip sandi, dan perlindungan privasi
  Agar kredensial akun terlindungi dari kebocoran visual maupun pencatatan log tidak aman

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2
    Dan pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.passtest@dikahadir.com"

  @PASS-001
  Skenario: PASS-001 Password kosong ditolak sistem
    Ketika pengguna mengisi password v2 ""
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Password tidak boleh kosong"

  @PASS-002
  Skenario: PASS-002 Password valid memenuhi kriteria kompleksitas
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-003
  Skenario: PASS-003 Karakter password dalam kondisi masked tersembunyi
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Maka karakter password v2 dalam keadaan tersembunyi

  @PASS-004
  Skenario: PASS-004 Klik toggle visibilitas sandi satu kali membuka karakter
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol toggle lihat password v2
    Maka karakter password v2 ditampilkan

  @PASS-005
  Skenario: PASS-005 Klik toggle visibilitas sandi kedua kali menyembunyikan kembali
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol toggle lihat password v2
    Dan pengguna menekan tombol toggle lihat password v2
    Maka karakter password v2 dalam keadaan tersembunyi

  @PASS-006
  Skenario: PASS-006 Konsistensi nilai string sandi saat toggle visibilitas dilakukan
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol toggle lihat password v2
    Dan pengguna menekan tombol toggle lihat password v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-007
  Skenario: PASS-007 Password terlalu pendek kurang dari 6 karakter ditolak
    Ketika pengguna mengisi password v2 "12345"
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Password minimal 6 karakter"

  @PASS-008
  Skenario: PASS-008 Password tanpa angka atau simbol diproses sesuai aturan
    Ketika pengguna mengisi password v2 "hanyahurufbiasa"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-009
  Skenario: PASS-009 Password mengandung spasi
    Ketika pengguna mengisi password v2 "P@ssw 0rd 2026"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-010
  Skenario: PASS-010 Password dengan simbol lengkap diproses benar
    Ketika pengguna mengisi password v2 "!@#$%^&*()_+-="
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-011
  Skenario: PASS-011 Password sangat panjang 100 karakter
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!P@ssw0rdPascal2026!P@ssw0rdPascal2026!P@ssw0rdPascal2026!P@ssw0rdPascal2026!P@ssw0rd"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-012
  Skenario: PASS-012 Pindah fokus dari field password tidak mereset nilai
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol Tab keyboard
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-013
  Skenario: PASS-013 Nilai password tetap utuh saat field lain memicu error
    Ketika pengguna mengisi NIK v2 "D624005"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna menekan tombol submit registrasi v2
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-014
  Skenario: PASS-014 Password tidak bocor pada console browser
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Maka console log browser bersih dari error fatal

  @PASS-015
  Skenario: PASS-015 Password pada response API tidak diekspos
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Maka nilai pada field form tetap utuh tidak terhapus

  @PASS-016
  Skenario: PASS-016 Password pada log framework tercatat sebagai protected
    Ketika pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Maka nilai pada field form tetap utuh tidak terhapus
