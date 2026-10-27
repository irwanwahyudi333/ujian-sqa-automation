# language: id
@ui @divisi @ui-elements
Fitur: Verifikasi Tampilan dan Elemen Antarmuka Halaman Divisi
  Sebagai admin manajemen HADIR
  Saya ingin memastikan seluruh elemen antarmuka halaman Divisi ditampilkan dengan lengkap dan benar
  Agar halaman dapat digunakan untuk mengelola data divisi perusahaan

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @UI-001
  Skenario: Membuka URL halaman divisi dengan sesi terautentikasi
    Maka halaman divisi terbuka sempurna
    Dan judul halaman menampilkan "Divisi"

  @UI-002
  Skenario: Memeriksa judul halaman dan navigasi breadcrumbs
    Maka judul halaman menampilkan "Divisi"
    Dan jalur breadcrumbs menampilkan "Dashboard / Management / Divisi"

  @UI-003
  Skenario: Memeriksa keberadaan form pencarian divisi
    Maka form pencarian divisi ditampilkan di halaman
    Dan input pencarian divisi terlihat
    Dan tombol Cari divisi terlihat
    Dan tombol Reset pencarian divisi terlihat

  @UI-004
  Skenario: Memeriksa teks placeholder pada field input pencarian
    Maka input pencarian divisi memiliki placeholder "cari berdasarkan nama"

  @UI-005
  Skenario: Memeriksa keberadaan tombol Tambahkan Divisi
    Maka tombol Tambahkan divisi ditampilkan dengan styling warna primary

  @UI-006
  Skenario: Memeriksa kolom header pada tabel data divisi
    Maka tabel data divisi menampilkan 4 kolom header:
      | NAMA Divisi    |
      | TANGGAL BUAT   |
      | TANGGAL UPDATE |
      | Aksi           |

  @UI-007
  Skenario: Memeriksa styling header tabel data divisi
    Maka header tabel divisi berlatar belakang gelap dan teks berwarna putih

  @UI-008
  Skenario: Memeriksa kelengkapan baris data pada tabel divisi
    Maka tabel data divisi menampilkan minimal satu baris data divisi
    Dan setiap baris memuat nama divisi, tanggal buat, tanggal update, dan aksi

  @UI-009
  Skenario: Memeriksa keberadaan tombol Edit langsung pada setiap baris
    Maka setiap baris pada tabel divisi memiliki tombol ikon Edit

  @UI-010
  Skenario: Memeriksa keberadaan tombol Hapus langsung pada setiap baris
    Maka setiap baris pada tabel divisi memiliki tombol ikon Hapus berwarna merah

  @UI-011
  Skenario: Memeriksa tombol menu 3-titik aksi pada setiap baris
    Maka setiap baris pada tabel divisi memiliki tombol aksi menu 3-titik

  @UI-012
  Skenario: Memeriksa keberadaan bar paginasi di bawah tabel
    Maka bar paginasi tabel divisi ditampilkan
    Dan kontrol pilihan baris per halaman terlihat
    Dan label informasi jumlah baris ditampilkan
    Dan tombol navigasi halaman terlihat

  @UI-013
  Skenario: Memeriksa nilai default baris per halaman
    Maka pilihan default baris per halaman pada paginasi adalah "10"

  @UI-014
  Skenario: Memuat ulang halaman divisi
    Ketika admin memuat ulang halaman divisi
    Maka seluruh komponen utama halaman divisi tetap ditampilkan dengan utuh

  @UI-015
  Skenario: Memeriksa tidak adanya error JavaScript pada konsol browser
    Maka tidak terdapat pesan error JavaScript kritis pada konsol browser
