# language: id
@ui @divisi @security-boundary
Fitur: Pengujian Keamanan, Batasan Input, dan Ketahanan Sistem Divisi
  Sebagai tim QA dan analis keamanan sistem HADIR
  Saya ingin menguji ketahanan form divisi terhadap serangan XSS, SQL Injection, dan batas karakter
  Agar data organisasi terlindungi dari manipulasi jahat dan celah kerentanan aplikasi

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @SEC-001
  Skenario: Membuka URL divisi tanpa sesi autentikasi yang valid
    Ketika pengguna tanpa sesi login mengakses halaman divisi
    Maka sistem mengarahkan pengguna ke halaman login

  @SEC-002
  Skenario: Uji sanitasi script XSS pada form Tambah Divisi
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "<script>alert('XSS')</script>"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka script XSS tidak dieksekusi di browser
    Dan teks script ditampilkan sebagai string literal yang aman

  @SEC-003
  Skenario: Uji sanitasi vektor XSS berbasis tag HTML event pada form Edit Divisi
    Ketika admin menekan tombol edit langsung pada baris divisi pertama
    Dan admin mengubah nama divisi menjadi "<img src=x onerror=alert(1)>"
    Dan admin menekan tombol Simpan pada modal edit
    Maka modal dialog Edit Divisi tertutup
    Dan tidak ada dialog popup script yang terpicu di peramban web

  @SEC-004
  Skenario: Uji ketahanan terhadap injeksi SQL pada field pencarian divisi
    Ketika admin memasukkan kata kunci pencarian divisi "' OR '1'='1"
    Dan admin menekan tombol Cari divisi
    Maka sistem memproses pencarian dengan aman tanpa memicu kegagalan sistem internal

  @SEC-005
  Skenario: Uji ketahanan terhadap serangan SQL UNION pada kueri filter
    Ketika admin memasukkan kata kunci pencarian divisi "' UNION SELECT 1,2,3--"
    Dan admin menekan tombol Cari divisi
    Maka tabel divisi menampilkan status data nihil tanpa error database

  @SEC-006
  Skenario: Menambahkan divisi dengan batas maksimum panjang 255 karakter
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi 255 karakter alfanumerik
    Dan admin menekan tombol Simpan pada modal tambah
    Maka modal dialog Tambah Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"

  @SEC-007
  Skenario: Validasi penambahan divisi dengan teks melebihi batas panjang 300 karakter
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi 300 karakter teks panjang
    Dan admin menekan tombol Simpan pada modal tambah
    Maka sistem menahan atau membatasi input sesuai kuota maksimal panjang karakter

  @SEC-008
  Skenario: Menambahkan divisi dengan karakter Unicode bahasa non-latin
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi Unicode "開発部門 (R&D Japan)"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka modal dialog Tambah Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"
    Dan divisi bernama "開発部門 (R&D Japan)" muncul pada tabel data divisi

  @SEC-009
  Skenario: Menambahkan divisi dengan karakter simbol emoji
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Divisi Inovasi 🚀💡"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka modal dialog Tambah Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"
    Dan divisi bernama "Divisi Inovasi 🚀💡" tersimpan dengan simbol utuh

  @SEC-010
  Skenario: Penanganan manipulasi ID baris dengan nilai numerik non-eksisten
    Ketika request manipulasi divisi dikirim dengan ID non-eksisten "999999"
    Maka sistem merespons penolakan bahwa data divisi tidak ditemukan

  @SEC-011
  Skenario: Penanganan manipulasi ID dengan format string acak tidak valid
    Ketika request manipulasi divisi dikirim dengan ID tidak valid "abc-invalid"
    Maka sistem merespons penolakan Bad Request tanpa membocorkan stack trace database

  @SEC-012
  Skenario: Penolakan akses manajemen divisi dengan token otorisasi kedaluwarsa
    Ketika request manajemen divisi dikirim dengan token kedaluwarsa
    Maka sistem merespons penolakan status 401 Unauthorized
