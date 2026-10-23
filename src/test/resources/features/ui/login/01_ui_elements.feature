# language: id
@ui @module-login @login-ui-elements
Fitur: Pengujian Elemen UI dan Tampilan Halaman Login
  Sebagai sistem verifikasi antarmuka
  Saya ingin memastikan seluruh elemen formulir login tampil dengan benar
  Agar pengguna mendapatkan pengalaman visual dan interaksi yang optimal

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @visual @ui-log-001 @lean
  Skenario: Buka halaman Login melalui URL resmi
    Maka sistem menampilkan halaman login dengan status sukses dan URL sesuai

  @visual @ui-log-002
  Skenario: Verifikasi judul utama halaman Login
    Maka sistem menampilkan judul login "Login"

  @visual @ui-log-003
  Skenario: Verifikasi keberadaan dan label field Email
    Dan field input email terlihat pada halaman login

  @visual @ui-log-004
  Skenario: Verifikasi keberadaan dan label field Password
    Dan field input password terlihat pada halaman login

  @visual @ui-log-005
  Skenario: Verifikasi keberadaan ikon toggle visibility password
    Maka ikon mata toggle password terlihat pada field password

  @visual @ui-log-006
  Skenario: Verifikasi keberadaan tombol Masuk
    Dan tombol Masuk terlihat pada formulir login

  @security @ui-log-007 @lean
  Skenario: Periksa kondisi awal atribut tipe password
    Maka karakter password login dalam kondisi tersembunyi

  @functional @ui-log-008 @lean
  Skenario: Klik ikon mata toggle password satu kali
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan ikon mata toggle password login
    Maka karakter password login berubah menjadi terlihat

  @functional @ui-log-009
  Skenario: Klik ikon mata toggle password dua kali
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan ikon mata toggle password login
    Dan pengguna menekan ikon mata toggle password login kembali
    Maka karakter password login kembali dalam kondisi tersembunyi

  @functional @ui-log-010
  Skenario: Toggle password berulang kali secara konsisten
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna melakukan toggle password sebanyak 5 kali
    Maka karakter password login tetap konsisten dengan nilai yang tidak berubah

  @resilience @ui-log-011
  Skenario: Memuat ulang halaman login
    Ketika pengguna mengisi email login "temporary@dikahadir.com"
    Dan pengguna memuat ulang halaman login
    Maka formulir login kembali bersih dan siap digunakan

  @accessibility @ui-log-012
  Skenario: Navigasi elemen menggunakan tombol Tab keyboard
    Ketika pengguna menekan tombol Tab untuk berpindah antar elemen formulir
    Maka fokus keyboard berpindah secara berurutan pada kontrol formulir login

  @visual @ui-log-013
  Skenario: Indikator visual saat elemen menerima fokus
    Ketika pengguna memfokuskan kursor pada field email
    Maka field email menampilkan indikator border fokus aktif

  @reliability @ui-log-014
  Skenario: Periksa ketiadaan error JavaScript pada browser console
    Maka konsol browser tidak memuat error fatal JavaScript pada halaman login

  @accessibility @ui-log-015
  Skenario: Verifikasi tampilan pada zoom browser 200 persen
    Ketika pengguna mengubah zoom browser menjadi 200 persen
    Maka elemen formulir login tetap utuh dan terbaca tanpa konten terpotong
