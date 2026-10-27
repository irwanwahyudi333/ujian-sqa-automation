# language: id
@ui @self-registration @module-search
Fitur: Pengujian Fitur Pencarian Data User Registrasi Mobile
  Sebagai administrator manajemen
  Saya ingin mencari calon karyawan berdasarkan nama dan NIK
  Agar dapat menemukan data pendaftar secara cepat dan akurat

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @SRCH-001
  Skenario: Cari user berdasarkan Nama Lengkap yang ada
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Irwan Wahyudi"
    Dan admin menekan tombol Cari
    Maka baris user bernama "Irwan Wahyudi" tampil pada tabel

  @SRCH-002
  Skenario: Cari user berdasarkan substring nama
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Ahmad"
    Dan admin menekan tombol Cari
    Maka baris user bernama "Ahmad Subagyo" tampil pada tabel

  @SRCH-003
  Skenario: Cari user dengan huruf kecil case-insensitive
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian dengan huruf kecil "irwan"
    Dan admin menekan tombol Cari
    Maka baris user bernama "Irwan Wahyudi" tampil pada tabel

  @SRCH-004
  Skenario: Cari user berdasarkan NIK lengkap yang valid
    Ketika admin memilih tipe pencarian "nik"
    Dan admin memasukkan kata kunci pencarian "3201234567890001"
    Dan admin menekan tombol Cari
    Maka baris user dengan NIK "3201234567890001" tampil pada tabel

  @SRCH-005
  Skenario: Cari user berdasarkan substring NIK
    Ketika admin memilih tipe pencarian "nik"
    Dan admin memasukkan kata kunci pencarian "3201"
    Dan admin menekan tombol Cari
    Maka baris user dengan NIK "3201234567890001" tampil pada tabel

  @SRCH-006
  Skenario: Cari data nama yang tidak ada di sistem
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "PenggunaFiktif999"
    Dan admin menekan tombol Cari
    Maka tabel tidak menampilkan baris data atau kosong

  @SRCH-007
  Skenario: Cari data NIK yang tidak terdaftar
    Ketika admin memilih tipe pencarian "nik"
    Dan admin memasukkan kata kunci pencarian "0000000000000000"
    Dan admin menekan tombol Cari
    Maka tabel tidak menampilkan baris data atau kosong

  @SRCH-008
  Skenario: Masukkan spasi di awal dan akhir kata kunci pencarian
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci spasi "  Irwan Wahyudi  "
    Dan admin menekan tombol Cari
    Maka baris user bernama "Irwan Wahyudi" tampil pada tabel

  @SRCH-009
  Skenario: Tekan tombol Enter pada keyboard di dalam input search
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Siti"
    Dan admin menekan tombol Enter pada input pencarian
    Maka baris user bernama "Siti Nurhaliza" tampil pada tabel

  @SRCH-010
  Skenario: Ganti tipe pencarian dari Nama ke NIK
    Ketika admin memilih tipe pencarian "nik"
    Maka dropdown tipe pencarian memiliki opsi Nama dan Nik

  @SRCH-011
  Skenario: Klik tombol Reset setelah melakukan pencarian
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Ahmad"
    Dan admin menekan tombol Cari
    Dan admin menekan tombol Reset
    Maka input pencarian menjadi kosong
    Dan seluruh data user awal kembali ditampilkan pada tabel

  @SRCH-012
  Skenario: Pencarian dengan karakter spesial
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci karakter khusus "@#$%^&*"
    Dan admin menekan tombol Cari
    Maka tabel tidak menampilkan baris data atau kosong

  @SRCH-013
  Skenario: Pencarian dengan injeksi tag HTML
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci payload "<b>test</b>"
    Dan admin menekan tombol Cari
    Maka tabel tidak menampilkan baris data atau kosong

  @SRCH-014
  Skenario: Pencarian dengan karakter sangat panjang
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci panjang 250 karakter
    Dan admin menekan tombol Cari
    Maka tabel tidak menampilkan baris data atau kosong

  @SRCH-015
  Skenario: Verifikasi update teks paginasi setelah pencarian
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Irwan Wahyudi"
    Dan admin menekan tombol Cari
    Maka label paginasi otomatis terupdate
