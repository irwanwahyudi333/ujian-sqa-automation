# language: id
@ui @module-login @login-password-validation
Fitur: Validasi Field Password pada Halaman Login
  Sebagai sistem autentikasi
  Saya ingin memastikan field kata sandi terlindungi dan divalidasi dengan benar
  Agar data rahasia pengguna aman dari kebocoran dan penyalahgunaan

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @positive @pass-log-001 @lean
  Skenario: Input password yang sesuai dengan akun
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem berhasil login dan mengalihkan pengguna ke dashboard

  @negative @pass-log-002 @lean
  Skenario: Submit saat field Password kosong
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Password wajib diisi"

  @negative @pass-log-003
  Skenario: Submit field Password hanya berisi spasi
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "   "
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Password wajib diisi"

  @negative @pass-log-004 @lean
  Skenario: Input password salah
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongP@ssw0rd999"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @negative @pass-log-005
  Skenario: Password dengan spasi di awal atau akhir tidak di-trim sembarangan
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login " P@ssw0rdMagang123 "
    Dan pengguna menekan tombol Masuk
    Maka sistem menolak autentikasi karena perbedaan karakter spasi password

  @security @pass-log-008
  Skenario: Karakter password tidak hilang saat kursor berpindah fokus
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna memfokuskan kursor pada field email
    Maka field password tetap mempertahankan nilai teks yang telah dimasukkan

  @functional @pass-log-009
  Skenario: Menempelkan teks ke dalam input password dari clipboard
    Ketika pengguna menempelkan teks password "P@ssw0rdMagang123" ke dalam field password
    Maka teks password berhasil ditempel dan tersamarkan dengan benar

  @security @pass-log-010
  Skenario: Atribut keamanan password pada DOM browser bertipe password
    Maka elemen password di DOM memiliki atribut type bernilai password

  @security @pass-log-011
  Skenario: Karakter password tidak bocor pada URL browser
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka URL browser tidak memuat parameter password dalam bentuk plaintext

  @security @pass-log-012
  Skenario: Sensor password pada log otomatisasi dan laporan pengujian
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Maka log pengujian wajib menyamarkan password sebagai nilai protected

  @functional @pass-log-013
  Skenario: Penanganan status tombol Caps Lock aktif pada input password
    Ketika pengguna mengaktifkan status Caps Lock pada field password
    Maka sistem siap menerima input karakter password tanpa crash antarmuka

  @security @pass-log-014
  Skenario: Nilai password dikosongkan kembali setelah autentikasi gagal
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongPassword123"
    Dan pengguna menekan tombol Masuk
    Maka field password otomatis dikosongkan kembali demi keamanan

  @security @pass-log-015
  Skenario: Password mengandung karakter kutip tunggal dan ganda ditangani sebagai string aman
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ss'w\"ord123"
    Dan pengguna menekan tombol Masuk
    Maka sistem memproses karakter kutip secara aman tanpa menyebabkan kegagalan parsing

  @functional @password-matrix
  Skenario konsep: Pengujian variasi karakter sandi khusus (<test_id> - <keterangan>)
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "<password_input>"
    Dan pengguna menekan tombol Masuk
    Maka sistem memproses autentikasi kata sandi secara terkontrol

    Contoh:
      | test_id       | password_input                                                                                                   | keterangan                         |
      | PASS-LOG-006  | P@$$w0rd!#%&*()_+=~`{}[]:;'<>?,./                                                                                | Password simbol kompleks           |
      | PASS-LOG-007  | PasswordSangatPanjangSekaliMelebihiBatasWajar128KarakterAlfanumerikKombinasiUntukUjiKetahananBufferOverflowSistemHadir1234567890 | Password panjang ekstrem 128+ char |
