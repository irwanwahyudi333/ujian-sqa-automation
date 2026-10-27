# language: id
@ui @divisi @search-divisi
Fitur: Pencarian Data Divisi Berdasarkan Nama
  Sebagai admin manajemen HADIR
  Saya ingin mencari divisi berdasarkan nama
  Agar dapat menemukan divisi yang dicari dengan cepat dan efisien

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @SRCH-001
  Skenario: Mencari divisi berdasarkan nama lengkap yang terdaftar
    Ketika admin memasukkan kata kunci pencarian divisi "Technology"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi hanya menampilkan baris dengan nama "Technology"

  @SRCH-002
  Skenario: Mencari divisi berdasarkan substring nama
    Ketika admin memasukkan kata kunci pencarian divisi "Tech"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menampilkan divisi yang mengandung kata "Tech"

  @SRCH-003
  Skenario: Mencari divisi menggunakan huruf kecil (case-insensitive)
    Ketika admin memasukkan kata kunci pencarian divisi "human resources"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menampilkan divisi bernama "Human Resources"

  @SRCH-004
  Skenario: Mencari divisi menggunakan huruf kapital semua (uppercase)
    Ketika admin memasukkan kata kunci pencarian divisi "FINANCE"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menampilkan divisi bernama "Finance & Accounting"

  @SRCH-005
  Skenario: Mencari divisi dengan nama yang tidak terdaftar di sistem
    Ketika admin memasukkan kata kunci pencarian divisi "DivisiFiktif999XYZ"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menampilkan pesan bahwa data divisi tidak ditemukan

  @SRCH-006
  Skenario: Mencari divisi dengan spasi di awal dan akhir kata kunci
    Ketika admin memasukkan kata kunci pencarian divisi "   Operations   "
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka sistem melakukan trim dan tabel divisi menampilkan divisi "Operations"

  @SRCH-007
  Skenario: Menjalankan pencarian divisi dengan menekan tombol Enter pada keyboard
    Ketika admin memasukkan kata kunci pencarian divisi "Quality Assurance"
    Dan admin menekan tombol Enter pada field input pencarian divisi
    Maka tabel divisi menampilkan divisi bernama "Quality Assurance"

  @SRCH-008
  Skenario: Mengosongkan pencarian dan memulihkan data dengan tombol Reset
    Ketika admin memasukkan kata kunci pencarian divisi "Marketing"
    Dan admin menekan tombol Cari pada pencarian divisi
    Ketika admin menekan tombol Reset pada pencarian divisi
    Maka input pencarian divisi menjadi kosong
    Dan seluruh data divisi awal kembali ditampilkan pada tabel

  @SRCH-009
  Skenario: Menekan tombol Reset saat field pencarian sudah kosong
    Ketika admin menekan tombol Reset pada pencarian divisi
    Maka tabel divisi tetap menampilkan seluruh daftar data divisi secara stabil

  @SRCH-010
  Skenario: Mencari divisi dengan karakter simbol ampersand (&)
    Ketika admin memasukkan kata kunci pencarian divisi "&"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menampilkan divisi yang mengandung simbol "&"

  @SRCH-011
  Skenario: Mencari divisi dengan kata kunci 1 karakter
    Ketika admin memasukkan kata kunci pencarian divisi "P"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka tabel divisi menyaring baris divisi yang memuat huruf "P"

  @SRCH-012
  Skenario: Mencari divisi dengan karakter numerik
    Ketika admin memasukkan kata kunci pencarian divisi "4.0"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka sistem menangani pencarian numerik secara aman tanpa error

  @SRCH-013
  Skenario: Mencari divisi dengan injeksi tag HTML
    Ketika admin memasukkan kata kunci pencarian divisi "<b>Tech</b>"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka sistem memperlakukan input sebagai string teks biasa tanpa eksekusi tag HTML

  @SRCH-014
  Skenario: Memeriksa pembaruan teks paginasi setelah melakukan pencarian
    Ketika admin memasukkan kata kunci pencarian divisi "Technology"
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka label informasi paginasi divisi memperbarui jumlah total data yang cocok

  @SRCH-015
  Skenario: Mengosongkan field input lalu menekan tombol Search
    Ketika admin mengosongkan input pencarian divisi
    Dan admin menekan tombol Cari pada pencarian divisi
    Maka seluruh data divisi awal ditampilkan kembali secara lengkap
