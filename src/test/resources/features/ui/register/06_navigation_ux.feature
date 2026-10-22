# language: id
@ui @module-navigation-ux
Fitur: Pengujian Navigasi Lanjutan dan Tampilan Responsif (UX/Compatibility)
  Sebagai pengguna aplikasi di berbagai perangkat
  Saya ingin memastikan formulir pendaftaran tampil responsif dan tautan navigasi berfungsi
  Agar aplikasi dapat diakses dengan baik di seluruh resolusi layar

  Latar Belakang:
    Dengan pengguna membuka halaman pendaftaran

  @ux-001
  Skenario: Menampilkan formulir pendaftaran pada resolusi layar Desktop (1920x1080)
    Ketika pengguna mengubah ukuran layar browser ke resolusi 1920x1080
    Maka seluruh elemen utama input pendaftaran terlihat

  @ux-002
  Skenario: Menampilkan formulir pendaftaran pada resolusi layar Tablet (768x1024)
    Ketika pengguna mengubah ukuran layar browser ke resolusi 768x1024
    Maka seluruh elemen utama input pendaftaran terlihat

  @ux-003
  Skenario: Menampilkan formulir pendaftaran pada resolusi layar Ponsel (375x667)
    Ketika pengguna mengubah ukuran layar browser ke resolusi 375x667
    Maka seluruh elemen utama input pendaftaran terlihat

  @nav-004
  Skenario: Memeriksa URL route halaman login setelah navigasi
    Ketika pengguna menekan tautan masuk disini
    Maka pengguna dialihkan ke halaman login

  @nav-005
  Skenario: Akses halaman pendaftaran langsung melalui URL
    Maka seluruh elemen utama input pendaftaran terlihat

  @nav-003
  Skenario: Akses tautan login menggunakan tombol keyboard Enter
    Ketika pengguna menekan tombol Enter pada tautan masuk disini
    Maka pengguna dialihkan ke halaman login

  @accessibility @a11y-001
  Skenario: Aksesibilitas keyboard pada seluruh elemen formulir pendaftaran
    Ketika pengguna menekan tombol Tab pada keyboard
    Maka fokus berpindah ke elemen input formulir berikutnya

  @accessibility @a11y-002
  Skenario: Aksesibilitas label elemen formulir pendaftaran
    Maka seluruh input formulir terhubung dengan elemen label yang valid

  @accessibility @a11y-005
  Skenario: Aksesibilitas tampilan pendaftaran saat zoom browser 200 persen
    Ketika pengguna mengatur zoom browser sebesar 200 persen
    Maka seluruh elemen utama input pendaftaran terlihat

  @ux-004
  Skenario: Tampilan formulir pendaftaran saat menerima pesan validasi error
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @ux-005
  Skenario: Penanganan input teks nama dan email panjang pada tampilan formulir
    Ketika pengguna mengisi nama lengkap "NamaPenggunaSangatPanjangSekaliMelebihiBatasWajarKarakterInputFormulirPendaftaranSistemAplikasiHadirMagang"
    Dan pengguna mengisi email "qa.user.very.long.email.address.beyond.reasonable.limit.for.testing@backyr.com"
    Maka seluruh elemen utama input pendaftaran terlihat

  @compatibility @comp-001 @comp-002
  Skenario: Kompatibilitas tampilan formulir pendaftaran pada browser target
    Maka seluruh elemen utama input pendaftaran terlihat

  @compatibility @comp-004
  Skenario: Kompatibilitas responsivitas pada resolusi perangkat laptop (1366x768)
    Ketika pengguna mengubah ukuran layar browser ke resolusi 1366x768
    Maka seluruh elemen utama input pendaftaran terlihat

  @accessibility @a11y-003
  Skenario: Keterbacaan dan asosiasi pesan kesalahan formulir pada field yang sesuai
    Ketika pengguna menekan tombol Daftar
    Maka sistem menahan submit atau menampilkan respon kesalahan

  @accessibility @a11y-004
  Skenario: Kontras visual dan keterbacaan elemen kontrol formulir pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat

  @compatibility @comp-003
  Skenario: Konsistensi state formulir pendaftaran saat navigasi browser Back dan Forward
    Ketika pengguna memuat ulang halaman pendaftaran
    Maka seluruh elemen utama input pendaftaran terlihat
