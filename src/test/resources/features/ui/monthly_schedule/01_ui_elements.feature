# language: id
@ui @monthly-schedule @ui-elements
Fitur: Verifikasi Tampilan dan Elemen Antarmuka Halaman Monthly Schedule
  Sebagai admin manajemen HADIR
  Saya ingin memastikan seluruh elemen antarmuka halaman Monthly Schedule ditampilkan dengan lengkap dan benar
  Agar halaman dapat digunakan untuk mengelola dan memantau jadwal bulanan karyawan

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @UI-001
  Skenario: Membuka halaman Monthly Schedule
    Maka halaman monthly schedule terbuka sempurna
    Dan judul halaman monthly schedule menampilkan "Management Monthly Schedule"

  @UI-002
  Skenario: Verifikasi breadcrumbs navigasi
    Maka jalur breadcrumbs monthly schedule menampilkan "Dashboard > Management > Monthly Schedule"

  @UI-003
  Skenario: Verifikasi card Import Monthly Schedule
    Maka card form import jadwal bulanan ditampilkan
    Dan input file jadwal terlihat
    Dan tombol Import jadwal terlihat
    Dan tombol Download Template terlihat

  @UI-004
  Skenario: Verifikasi tombol Download Template
    Maka tombol Download Template ditampilkan dengan styling hijau dan label jelas

  @UI-005
  Skenario: Verifikasi form pencarian nama karyawan
    Maka field input pencarian nama karyawan ditampilkan dengan placeholder "Nama Karyawan"
    Dan tombol Cari jadwal terlihat

  @UI-006
  Skenario: Verifikasi dropdown filter Unit
    Maka dropdown filter unit kerja ditampilkan
    Dan dropdown memuat opsi "Semua Unit", "Technology", "Human Resources", dan unit lainnya

  @UI-007
  Skenario: Verifikasi tombol Reset filter
    Maka tombol Reset filter pencarian ditampilkan berdampingan dengan filter unit

  @UI-008
  Skenario: Verifikasi header kolom tabel (7 kolom)
    Maka tabel jadwal menampilkan 7 kolom header:
      | NAMA KARYAWAN  |
      | NAMA UNIT      |
      | NAMA SHIFTING  |
      | JAM MULAI      |
      | JAM BERAKHIR   |
      | TANGGAL JADWAL |
      | AKSI           |

  @UI-009
  Skenario: Verifikasi baris data jadwal pada tabel
    Maka tabel jadwal menampilkan minimal satu baris data jadwal
    Dan setiap baris memuat nama karyawan, unit, shift, jam kerja, dan tanggal jadwal

  @UI-010
  Skenario: Verifikasi tombol aksi per baris
    Maka setiap baris pada tabel jadwal memiliki tombol Edit dan tombol Hapus

  @UI-011
  Skenario: Verifikasi kontrol paginasi baris
    Maka kontrol paginasi baris dan informasi jumlah data ditampilkan
    Dan pilihan default baris per halaman jadwal adalah "10"

  @UI-012
  Skenario: Verifikasi tombol navigasi halaman
    Maka tombol navigasi halaman First, Prev, Next, Last ditampilkan pada bar paginasi

  @UI-013
  Skenario: Verifikasi checkbox Dense padding
    Maka checkbox Dense padding ditampilkan di samping bar paginasi

  @UI-014
  Skenario: Verifikasi pesan tabel saat data kosong
    Ketika admin memasukkan kata kunci pencarian jadwal "DataTidakDitemukan999"
    Dan admin menekan tombol Cari jadwal
    Maka tabel jadwal menampilkan pesan "Tidak Ada Data"

  @UI-015
  Skenario: Verifikasi kontainer notifikasi snackbar
    Maka kontainer notifikasi snackbar terpasang di halaman
