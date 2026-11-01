# language: id
@ui @user-monitoring @search
Fitur: Pencarian Data User Monitoring Berdasarkan Nama dan Filter Unit
  Sebagai admin manajemen HADIR
  Saya ingin mencari user monitoring berdasarkan nama lengkap, substring, maupun filter unit kerja
  Agar dapat menemukan data monitoring karyawan dengan cepat dan akurat

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @SRC-001
  Skenario: Cari user dengan nama lengkap tepat
    Ketika admin memasukkan kata kunci pencarian user monitoring "Ahmad Fauzi"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring hanya menampilkan baris dengan nama "Ahmad Fauzi"

  @SRC-002
  Skenario: Cari user dengan potongan nama awal (substring)
    Ketika admin memasukkan kata kunci pencarian user monitoring "Ahmad"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user yang mengandung kata "Ahmad"

  @SRC-003
  Skenario: Cari user dengan huruf kecil semua (case-insensitive)
    Ketika admin memasukkan kata kunci pencarian user monitoring "bella saphira"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user bernama "Bella Saphira"

  @SRC-004
  Skenario: Cari user dengan huruf kapital semua (uppercase)
    Ketika admin memasukkan kata kunci pencarian user monitoring "CAHYO"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user bernama "Cahyo Nugroho"

  @SRC-005
  Skenario: Cari user dengan alamat email
    Ketika admin memasukkan kata kunci pencarian user monitoring "dewi.anggraini@hadir.com"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user bernama "Dewi Anggraini"

  @SRC-006
  Skenario: Cari user dengan nama tidak terdaftar di sistem
    Ketika admin memasukkan kata kunci pencarian user monitoring "NamaFiktif999XYZ"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan pesan "Tidak Ada Data"

  @SRC-007
  Skenario: Menjalankan pencarian dengan menekan tombol Enter pada keyboard
    Ketika admin memasukkan kata kunci pencarian user monitoring "Eko"
    Dan admin menekan tombol Enter pada field input pencarian user monitoring
    Maka tabel user monitoring menampilkan user yang mengandung kata "Eko"

  @SRC-008
  Skenario: Cari user dengan spasi di awal dan akhir kata kunci
    Ketika admin memasukkan kata kunci pencarian user monitoring "   Farhan   "
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user bernama "Farhan Maulana"

  @SRC-009
  Skenario: Mengosongkan hasil pencarian dan mengembalikan seluruh data via tombol Reset
    Ketika admin memasukkan kata kunci pencarian user monitoring "Ahmad"
    Dan admin menekan tombol Search user monitoring
    Dan admin menekan tombol Reset pencarian user monitoring
    Maka field input pencarian user monitoring menjadi kosong
    Dan tabel user monitoring menampilkan kembali seluruh data awal

  @SRC-010
  Skenario: Pencarian dengan kata kunci 1 karakter
    Ketika admin memasukkan kata kunci pencarian user monitoring "a"
    Dan admin menekan tombol Search user monitoring
    Maka tabel user monitoring menampilkan user yang mengandung kata "a"

  @SRC-011
  Skenario: Membuka modal filter unit
    Ketika admin menekan tombol Filter Unit user monitoring
    Maka modal dialog Filter User Monitoring terbuka
    Dan judul modal filter menampilkan "Filter User Monitoring"

  @SRC-012
  Skenario: Filter user monitoring berdasarkan unit Technology
    Ketika admin menekan tombol Filter Unit user monitoring
    Dan admin memilih unit "Technology" pada modal filter
    Dan admin menekan tombol Terapkan pada modal filter
    Maka modal dialog Filter User Monitoring tertutup
    Dan seluruh user pada tabel memiliki badge unit "Technology"

  @SRC-013
  Skenario: Filter user monitoring pada unit tanpa data
    Ketika admin menekan tombol Filter Unit user monitoring
    Dan admin memilih unit "Unit Kosong" pada modal filter
    Dan admin menekan tombol Terapkan pada modal filter
    Maka tabel user monitoring menampilkan pesan "Tidak Ada Data"

  @SRC-014
  Skenario: Batalkan filter unit via tombol Batal
    Ketika admin menekan tombol Filter Unit user monitoring
    Dan admin memilih unit "Technology" pada modal filter
    Dan admin menekan tombol Batal pada modal filter user monitoring
    Maka modal dialog Filter User Monitoring tertutup
    Dan tabel user monitoring tetap menampilkan seluruh unit data

  @SRC-015
  Skenario: Tutup modal filter unit via tombol silang (X)
    Ketika admin menekan tombol Filter Unit user monitoring
    Dan admin menekan tombol silang tutup pada modal filter
    Maka modal dialog Filter User Monitoring tertutup tanpa menerapkan filter
