# language: id
@ui @user-monitoring @ui-elements
Fitur: Verifikasi Tampilan dan Elemen Antarmuka Halaman User Monitoring
  Sebagai admin manajemen HADIR
  Saya ingin memastikan seluruh elemen antarmuka halaman User Monitoring ditampilkan dengan lengkap dan benar
  Agar halaman dapat digunakan untuk memantau dan mengelola data pengguna monitoring operasional

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @UI-001
  Skenario: Membuka halaman User Monitoring
    Maka halaman user monitoring terbuka sempurna
    Dan judul halaman user monitoring menampilkan "User Monitoring"

  @UI-002
  Skenario: Verifikasi breadcrumbs navigasi
    Maka jalur breadcrumbs user monitoring menampilkan "Management / User Monitoring"

  @UI-003
  Skenario: Verifikasi form pencarian nama user
    Maka field input pencarian user monitoring memiliki placeholder "cari berdasarkan nama"
    Dan tombol Cari user monitoring terlihat

  @UI-004
  Skenario: Verifikasi tombol Filter Unit
    Maka tombol Filter Unit user monitoring ditampilkan

  @UI-005
  Skenario: Verifikasi tombol Reset pencarian
    Maka tombol Reset filter pencarian user monitoring terlihat

  @UI-006
  Skenario: Verifikasi tombol Search
    Maka tombol Search user monitoring ditampilkan dengan label "Search"

  @UI-007
  Skenario: Verifikasi tombol Tambahkan user monitoring
    Maka tombol Tambahkan user monitoring ditampilkan dengan label "Tambahkan"

  @UI-008
  Skenario: Verifikasi header kolom tabel (6 kolom)
    Maka tabel user monitoring menampilkan 6 kolom header:
      | FOTO           |
      | NAMA           |
      | EMAIL          |
      | UNIT           |
      | TANGGAL DIBUAT |
      | AKSI           |

  @UI-009
  Skenario: Verifikasi baris data user monitoring pada tabel
    Maka tabel user monitoring menampilkan minimal satu baris data
    Dan setiap baris memuat foto, nama, email, unit, dan tanggal dibuat

  @UI-010
  Skenario: Verifikasi tombol aksi Edit per baris
    Maka setiap baris pada tabel user monitoring memiliki tombol Edit

  @UI-011
  Skenario: Verifikasi tombol aksi Delete per baris
    Maka setiap baris pada tabel user monitoring memiliki tombol Hapus

  @UI-012
  Skenario: Verifikasi kontrol paginasi baris
    Maka kontrol paginasi baris dan informasi jumlah data user monitoring ditampilkan
    Dan pilihan default baris per halaman user monitoring adalah "10"

  @UI-013
  Skenario: Verifikasi tombol navigasi halaman
    Maka tombol navigasi halaman First, Prev, Next, Last ditampilkan pada paginasi user monitoring

  @UI-014
  Skenario: Verifikasi checkbox Dense padding
    Maka checkbox Dense padding ditampilkan di bar paginasi user monitoring

  @UI-015
  Skenario: Verifikasi pesan tabel saat data kosong
    Ketika admin memasukkan kata kunci pencarian user monitoring "NamaFiktif999XYZ"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan pesan "Tidak Ada Data"

  @UI-016
  Skenario: Verifikasi kontainer notifikasi snackbar
    Maka kontainer notifikasi snackbar user monitoring terpasang di halaman
