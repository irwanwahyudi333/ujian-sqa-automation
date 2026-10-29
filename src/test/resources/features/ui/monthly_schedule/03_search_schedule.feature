# language: id
@ui @monthly-schedule @search-schedule
Fitur: Pencarian Jadwal Kerja Bulanan Karyawan
  Sebagai admin manajemen HADIR
  Saya ingin mencari jadwal kerja berdasarkan nama karyawan
  Agar saya dapat memeriksa jadwal kerja karyawan tertentu dengan cepat

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @SRCH-001
  Skenario: Cari jadwal dengan nama karyawan lengkap
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad Subagyo"
    Dan admin menekan tombol Cari jadwal
    Maka seluruh baris tabel menampilkan karyawan bernama "Ahmad Subagyo"

  @SRCH-002
  Skenario: Cari jadwal dengan substring nama awal
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad"
    Dan admin menekan tombol Cari jadwal
    Maka seluruh baris tabel memuat kata "Ahmad" pada kolom nama karyawan

  @SRCH-003
  Skenario: Cari jadwal dengan substring nama tengah/akhir
    Ketika admin memasukkan kata kunci pencarian jadwal "Subagyo"
    Dan admin menekan tombol Cari jadwal
    Maka seluruh baris tabel memuat kata "Subagyo" pada kolom nama karyawan

  @SRCH-004
  Skenario: Cari jadwal dengan huruf kecil (lowercase)
    Ketika admin memasukkan kata kunci pencarian jadwal "ahmad subagyo"
    Dan admin menekan tombol Cari jadwal
    Maka seluruh baris tabel menampilkan karyawan bernama "Ahmad Subagyo"

  @SRCH-005
  Skenario: Cari jadwal dengan huruf kapital (UPPERCASE)
    Ketika admin memasukkan kata kunci pencarian jadwal "AHMAD SUBAGYO"
    Dan admin menekan tombol Cari jadwal
    Maka seluruh baris tabel menampilkan karyawan bernama "Ahmad Subagyo"

  @SRCH-006
  Skenario: Tekan tombol Enter pada input nama
    Ketika admin memasukkan kata kunci pencarian jadwal "Siti Nurhaliza"
    Dan admin menekan tombol Enter pada input pencarian nama
    Maka seluruh baris tabel menampilkan karyawan bernama "Siti Nurhaliza"

  @SRCH-007
  Skenario: Cari jadwal dengan spasi di awal/akhir
    Ketika admin memasukkan kata kunci pencarian jadwal "  Ahmad Subagyo  "
    Dan admin menekan tombol Cari jadwal
    Maka sistem memangkas spasi dan menampilkan data "Ahmad Subagyo"

  @SRCH-008
  Skenario: Cari jadwal dengan nama tidak terdaftar
    Ketika admin memasukkan kata kunci pencarian jadwal "NamaFiktif999XYZ"
    Dan admin menekan tombol Cari jadwal
    Maka tabel jadwal menampilkan pesan "Tidak Ada Data"

  @SRCH-009
  Skenario: Cari jadwal dengan 1 karakter
    Ketika admin memasukkan kata kunci pencarian jadwal "A"
    Dan admin menekan tombol Cari jadwal
    Maka tabel menampilkan baris karyawan yang memuat huruf "A"

  @SRCH-010
  Skenario: Cari jadwal dengan query kosong
    Ketika admin mengosongkan input pencarian nama
    Dan admin menekan tombol Cari jadwal
    Maka tabel jadwal menampilkan seluruh daftar jadwal awal

  @SRCH-011
  Skenario: Reset pencarian via tombol Reset
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad Subagyo"
    Dan admin menekan tombol Cari jadwal
    Dan admin menekan tombol Reset filter pencarian
    Maka field input nama menjadi kosong
    Dan tabel menampilkan kembali seluruh data jadwal awal

  @SRCH-012
  Skenario: Akurasi label paginasi saat pencarian
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad"
    Dan admin menekan tombol Cari jadwal
    Maka label paginasi menampilkan jumlah baris hasil pencarian secara akurat

  @SRCH-013
  Skenario: Navigasi halaman saat hasil cari 1 halaman
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad Subagyo"
    Dan admin menekan tombol Cari jadwal
    Maka tombol navigasi First, Prev, Next, Last berstatus disabled

  @SRCH-014
  Skenario: Cari jadwal dengan simbol khusus
    Ketika admin memasukkan kata kunci pencarian jadwal "@#$%^"
    Dan admin menekan tombol Cari jadwal
    Maka sistem menangani query dengan aman dan menampilkan pesan "Tidak Ada Data"

  @SRCH-015
  Skenario: Konsistensi data tabel setelah berulang kali cari
    Ketika admin mencari "Ahmad", lalu "Siti", lalu mereset pencarian
    Maka seluruh data awal kembali ditampilkan secara utuh dan stabil
