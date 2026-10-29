# language: id
@ui @monthly-schedule @filter-schedule
Fitur: Filter Jadwal Berdasarkan Unit Kerja / Departemen
  Sebagai admin manajemen HADIR
  Saya ingin menyaring jadwal kerja berdasarkan unit atau departemen
  Agar dapat mengawasi penugasan jadwal divisi tertentu secara terfokus

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @FLTR-001
  Skenario: Filter unit "Technology"
    Ketika admin memilih filter unit kerja "Technology"
    Maka seluruh baris tabel menampilkan kolom unit "Technology"

  @FLTR-002
  Skenario: Filter unit "Human Resources"
    Ketika admin memilih filter unit kerja "Human Resources"
    Maka seluruh baris tabel menampilkan kolom unit "Human Resources"

  @FLTR-003
  Skenario: Filter unit "Finance & Accounting"
    Ketika admin memilih filter unit kerja "Finance & Accounting"
    Maka seluruh baris tabel menampilkan kolom unit "Finance & Accounting"

  @FLTR-004
  Skenario: Filter unit "Marketing"
    Ketika admin memilih filter unit kerja "Marketing"
    Maka seluruh baris tabel menampilkan kolom unit "Marketing"

  @FLTR-005
  Skenario: Filter unit "Operations"
    Ketika admin memilih filter unit kerja "Operations"
    Maka seluruh baris tabel menampilkan kolom unit "Operations"

  @FLTR-006
  Skenario: Kembalikan filter ke "Semua Unit"
    Ketika admin memilih filter unit kerja "Technology"
    Dan admin mengembalikan filter unit ke "Semua Unit"
    Maka tabel kembali menampilkan data jadwal dari seluruh unit

  @FLTR-007
  Skenario: Kombinasi pencarian nama dan filter unit cocok
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad Subagyo"
    Dan admin memilih filter unit kerja "Technology"
    Dan admin menekan tombol Cari jadwal
    Maka tabel menampilkan jadwal karyawan "Ahmad Subagyo" pada unit "Technology"

  @FLTR-008
  Skenario: Kombinasi nama dan unit yang tidak cocok
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad Subagyo"
    Dan admin memilih filter unit kerja "Human Resources"
    Dan admin menekan tombol Cari jadwal
    Maka tabel jadwal menampilkan pesan "Tidak Ada Data"

  @FLTR-009
  Skenario: Tombol Reset mengembalikan filter unit
    Ketika admin memilih filter unit kerja "Finance & Accounting"
    Dan admin menekan tombol Reset filter pencarian
    Maka dropdown filter unit kembali terpilih "Semua Unit"
    Dan tabel menampilkan seluruh data jadwal awal

  @FLTR-010
  Skenario: Akurasi jumlah data terfilter
    Ketika admin memilih filter unit kerja "Technology"
    Maka total data yang tampil pada label paginasi sesuai dengan jumlah data unit Technology

  @FLTR-011
  Skenario: Perubahan opsi unit saat berada di halaman kedua
    Ketika admin berpindah ke halaman kedua tabel
    Dan admin memilih filter unit kerja "Human Resources"
    Maka tabel otomatis mereset paginasi kembali ke halaman 1

  @FLTR-012
  Skenario: Filter unit dengan data kosong di database
    Ketika admin memilih filter unit kerja yang belum memiliki data jadwal
    Maka tabel jadwal menampilkan pesan "Tidak Ada Data"

  @FLTR-013
  Skenario: Konsistensi dropdown opsi unit
    Maka dropdown unit memuat daftar departemen aktif dari master data

  @FLTR-014
  Skenario: Integrasi filter unit dengan toggle dense padding
    Ketika admin memilih filter unit kerja "Technology"
    Dan admin mencentang opsi Dense padding
    Maka data terfilter tetap ditampilkan rapi dengan spasi tabel yang rapat

  @FLTR-015
  Skenario: Reset filter ganda secara simultan
    Ketika admin mengisi pencarian nama "Budi" dan memilih unit "Operations"
    Dan admin menekan tombol Reset filter pencarian
    Maka input nama dikosongkan dan unit kembali ke "Semua Unit"
