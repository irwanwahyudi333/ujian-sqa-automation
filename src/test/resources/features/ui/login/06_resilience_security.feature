# language: id
@ui @module-login @login-resilience-security
Fitur: Pengujian Keamanan, Ketahanan, dan Resiliensi Autentikasi Login
  Sebagai sistem keamanan aplikasi
  Saya ingin memastikan seluruh mekanisme login kebal terhadap ancaman siber
  Agar data akun pengguna dan stabilitas server selalu terlindungi

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @security @sec-log-001
  Skenario: Injeksi SQL pada field Email ditangani sebagai teks aman
    Ketika pengguna mengisi email login "' OR '1'='1"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menolak autentikasi dan memperlakukan payload SQL sebagai teks data

  @security @sec-log-002
  Skenario: Injeksi SQL pada field Password ditangani secara aman
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "' OR '1'='1 --"
    Dan pengguna menekan tombol Masuk
    Maka sistem menolak autentikasi dan memperlakukan payload SQL sebagai teks data

  @security @sec-log-003
  Skenario: Injeksi XSS pada field Email disanitasi oleh sistem
    Ketika pengguna mengisi email login "<script>alert('XSS')</script>"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka skrip XSS tidak dieksekusi pada peramban pengguna

  @security @sec-log-004 @lean
  Skenario: Pesan kesalahan generik mencegah user enumeration
    Ketika pengguna membandingkan respon email tidak terdaftar dan password salah
    Maka sistem menampilkan pesan kesalahan generik yang seragam tanpa membocorkan keberadaan akun

  @security @sec-log-005
  Skenario: Proteksi Brute Force terhadap percobaan login berulang kali
    Ketika terjadi percobaan login gagal beruntun dalam waktu singkat
    Maka sistem menerapkan pembatasan laju pengiriman request atau tantangan keamanan

  @security @sec-log-006
  Skenario: Kebijakan penguncian akun sementara setelah kegagalan berulang
    Ketika akun mengalami kegagalan login melebihi batas toleransi
    Maka sistem mengunci akses akun sementara waktu demi keamanan

  @security @sec-log-007
  Skenario: Transmisi formulir login wajib melalui protokol HTTPS aman
    Maka halaman login ditransmisikan melalui protokol jaringan yang terenkripsi

  @security @sec-log-008
  Skenario: Kredensial password dilindungi dengan enkripsi saat transmisi
    Maka pengiriman kata sandi terlindungi enkripsi pada lapisan transmisi data

  @security @sec-log-009
  Skenario: Ketiadaan data sensitif password pada respons autentikasi
    Maka sistem tidak pernah mengembalikan kata sandi ataupun nilai rahasia pada respons client

  @security @sec-log-010
  Skenario: Atribut keamanan Cookie Sesi bertipe HttpOnly dan Secure
    Maka cookie autentikasi yang diterbitkan dilengkapi atribut keamanan yang memadai

  @security @sec-log-011
  Skenario: Proteksi terhadap serangan Session Fixation
    Maka sistem menerbitkan pengidentifikasi sesi baru saat login berhasil

  @security @sec-log-012
  Skenario: Penolakan akses menggunakan token JWT yang telah kadaluarsa
    Ketika token autentikasi yang digunakan telah melewati masa berlaku
    Maka sistem menolak permintaan otorisasi dengan status unauthorized

  @security @sec-log-013
  Skenario: Penolakan akses menggunakan token JWT dengan tanda tangan dimanipulasi
    Ketika token autentikasi mengalami perubahan data tanpa tanda tangan sah
    Maka sistem mendeteksi manipulasi token dan menolak akses

  @security @sec-log-014
  Skenario: Pemeriksaan konfigurasi header keamanan respons login
    Maka respons aplikasi memuat header proteksi konten dan frame keamanan

  @security @sec-log-015
  Skenario: Pengiriman request login langsung tanpa perantara antarmuka browser
    Ketika request login dikirimkan secara langsung ke backend
    Maka aturan validasi keamanan backend tetap ditegakkan dengan ketat

  @security @sec-log-016
  Skenario: Penanganan Parameter Pollution pada data login
    Ketika request login memuat parameter duplikat
    Maka sistem memproses request secara konsisten tanpa ambiguitas data

  @security @sec-log-017
  Skenario: Proteksi terhadap serangan Cross-Site Request Forgery
    Maka sistem memvalidasi keabsahan origin dan token permintaan autentikasi

  @security @sec-log-018
  Skenario: Pencegahan penyimpanan cache pada respons autentikasi
    Maka respons halaman login memuat instruksi pencegahan caching

  @resilience @res-log-001
  Skenario: Ketahanan UI dalam menangani respon lambat dari backend
    Ketika server autentikasi mengalami keterlambatan respon
    Maka antarmuka login menampilkan indikator proses dan menangani timeout secara terkontrol

  @resilience @res-log-002
  Skenario: Penanganan antarmuka saat koneksi jaringan terputus
    Ketika terjadi kegagalan jaringan saat mengirim formulir login
    Maka sistem menampilkan notifikasi gangguan koneksi secara informatif

  @resilience @res-log-003
  Skenario: Penanganan kesalahan server internal HTTP 500
    Ketika server mengalami kendala internal 500 saat proses login
    Maka sistem menampilkan pesan kesalahan umum tanpa membocorkan trace kode

  @resilience @res-log-004
  Skenario: Penanganan kondisi server sedang dalam pemeliharaan HTTP 502 atau 503
    Ketika layanan autentikasi sedang dalam masa pemeliharaan
    Maka antarmuka menampilkan pemberitahuan bahwa layanan sedang tidak tersedia

  @resilience @res-log-005
  Skenario: Pengiriman formulir login berulang kali tidak mengunci form selamanya
    Ketika proses login sebelumnya mengalami kegagalan
    Dan pengguna mencoba mengisi ulang formulir login
    Maka antarmuka langsung merespons percobaan baru tanpa terkunci

  @resilience @res-log-006
  Skenario: Pemeriksaan keamanan log pengujian tidak mengekspos password plaintext
    Maka seluruh catatan log pengujian login tidak memuat kata sandi terbuka

  @resilience @res-log-007
  Skenario: Eksekusi pengujian login bersifat thread-safe pada lingkungan multi-thread
    Maka suite pengujian login dapat dijalankan paralel tanpa konflik driver

  @resilience @res-log-008
  Skenario: Eksekusi suite pengujian login berulang kali menghasilkan status konsisten
    Maka suite pengujian login memiliki repeatability yang tinggi tanpa kegagalan semu

  @resilience @res-log-009
  Skenario: Penanganan respons server dengan format tidak valid
    Ketika respons dari server tidak sesuai format yang diharapkan
    Maka antarmuka menangani kegagalan parsing secara anggun tanpa crash

  @resilience @res-log-010
  Skenario: Pembersihan sesi uji setelah pengujian selesai
    Maka sesi dan data uji login dibersihkan secara otomatis oleh framework
