# language: id
@ui @module-login @login-ux-a11y
Fitur: Pengujian UX, Responsivitas, Kompatibilitas, dan Aksesibilitas Halaman Login
  Sebagai sistem antarmuka modern
  Saya ingin memastikan form login responsif dan memenuhi standar aksesibilitas
  Agar seluruh pengguna dapat menggunakan halaman login di berbagai perangkat

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @ux @ux-log-001
  Skenario: Tampilan formulir login pada layar Desktop 1920x1080
    Ketika browser diatur ke resolusi Desktop 1920x1080
    Maka formulir login tampil terpusat dengan proporsi yang rapi

  @ux @ux-log-002
  Skenario: Tampilan formulir login pada layar Laptop 1366x768
    Ketika browser diatur ke resolusi Laptop 1366x768
    Maka seluruh elemen formulir login terlihat jelas tanpa terpotong

  @ux @ux-log-003
  Skenario: Tampilan formulir login pada layar Tablet 768x1024
    Ketika browser diatur ke resolusi Tablet 768x1024
    Maka formulir login menyesuaikan lebar layar secara responsif

  @ux @ux-log-004 @lean
  Skenario: Tampilan formulir login pada layar Ponsel 375x667
    Ketika browser diatur ke resolusi Ponsel 375x667
    Maka formulir login tampil rapi tanpa scroll horizontal yang tidak semestinya

  @ux @ux-log-005
  Skenario: Perilaku keyboard virtual pada resolusi perangkat mobile
    Ketika browser diatur ke resolusi Ponsel 375x667
    Dan pengguna memfokuskan kursor pada field password
    Maka tombol Masuk tetap dapat dijangkau di layar

  @ux @ux-log-006
  Skenario: Tampilan pesan kesalahan panjang pada formulir login
    Ketika sistem menampilkan pesan error teks panjang pada form login
    Maka teks error terbungkus dengan rapi tanpa merusak tata letak input

  @compatibility @ux-log-007
  Skenario: Kompatibilitas formulir login pada peramban Google Chrome
    Maka seluruh fungsi input dan interaksi login kompatibel dengan browser

  @compatibility @ux-log-008 @lean
  Skenario: Kompatibilitas formulir login pada peramban Mozilla Firefox
    Maka seluruh fungsi input dan interaksi login kompatibel dengan browser

  @compatibility @ux-log-009
  Skenario: Kompatibilitas formulir login pada peramban Microsoft Edge
    Maka seluruh fungsi input dan interaksi login kompatibel dengan browser

  @compatibility @ux-log-010
  Skenario: Kompatibilitas formulir login pada peramban Apple Safari WebKit
    Maka seluruh fungsi input dan interaksi login kompatibel dengan browser

  @visual @ux-log-011
  Skenario: Kontras visual teks dan tombol pada tema biru HADIR
    Maka tombol Masuk memiliki kontras visual yang memadai terhadap background formulir

  @ux @ux-log-012
  Skenario: Navigasi Back dan Forward browser pada form login
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna menekan navigasi back lalu forward pada browser
    Maka halaman login tetap stabil dan siap menerima input

  @a11y @a11y-log-001
  Skenario: Aksesibilitas penuh formulir login hanya dengan keyboard
    Ketika pengguna menavigasi form login hanya menggunakan keyboard
    Maka seluruh kontrol email, password, toggle, dan tombol Masuk dapat diakses

  @a11y @a11y-log-002
  Skenario: Asosiasi programatis label dengan field input
    Maka label Email dan Password terhubung secara programatis dengan atribut for dan id

  @a11y @a11y-log-003
  Skenario: Aksesibilitas tombol toggle visibility password
    Maka tombol toggle password memiliki atribut label deskriptif untuk pembaca layar

  @a11y @a11y-log-004
  Skenario: Aksesibilitas pesan kesalahan menggunakan role alert
    Maka kontainer pesan error login memiliki atribut role alert

  @a11y @a11y-log-005
  Skenario: Aksesibilitas tombol Masuk sebagai elemen button standar
    Maka tombol Masuk menggunakan elemen button yang sah dengan tipe submit

  @a11y @a11y-log-006
  Skenario: Standar kontras rasio warna teks login memenuhi pedoman aksesibilitas
    Maka warna teks judul dan label memenuhi standar kontras keterbacaan

  @a11y @a11y-log-007
  Skenario: Keterbacaan elemen formulir login oleh teknologi pembaca layar
    Maka elemen input formulir login menyediakan informasi semantik yang lengkap

  @a11y @a11y-log-008
  Skenario: Penyesuaian tata letak login saat ukuran teks diperbesar
    Ketika pengguna memperbesar ukuran teks pada browser
    Maka tata letak formulir login tetap fleksibel dan tidak saling tumpang tindih
