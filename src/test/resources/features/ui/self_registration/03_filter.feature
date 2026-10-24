# language: id
@ui @self-registration @module-filter
Fitur: Pengujian Fitur Filter Modal Data User Registrasi Mobile
  Sebagai administrator manajemen
  Saya ingin menyaring data pendaftar melalui modal filter multi-kriteria
  Agar dapat mengelompokkan calon karyawan berdasarkan organisasi dan status

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @FLTR-001
  Skenario: Klik tombol Filter pada toolbar
    Ketika admin menekan tombol Filter
    Maka modal filter user terbuka

  @FLTR-002
  Skenario: Periksa seluruh field input pada modal Filter User
    Ketika admin menekan tombol Filter
    Maka modal filter user terbuka
    Dan seluruh field input modal filter user terlihat

  @FLTR-003
  Skenario: Terapkan filter berdasarkan Unit HRIS yang valid
    Ketika admin menerapkan filter Unit HRIS "SQA Core"
    Maka baris user dengan unit "SQA Core" tampil pada tabel

  @FLTR-004
  Skenario: Terapkan filter berdasarkan Status Aktif
    Ketika admin menerapkan filter status "1"
    Maka baris user pertama tetap ada pada tabel

  @FLTR-005
  Skenario: Terapkan filter berdasarkan Status Tidak Aktif
    Ketika admin menerapkan filter status "0"
    Maka sistem menampilkan notifikasi "Filter diterapkan"

  @FLTR-006
  Skenario: Terapkan kombinasi filter Unit HRIS dan Status
    Ketika admin menerapkan kombinasi filter unit "SQA Core" dan status "1"
    Maka baris user dengan unit "SQA Core" tampil pada tabel

  @FLTR-007
  Skenario: Filter data dengan kriteria yang tidak menghasilkan data
    Ketika admin menerapkan filter Unit HRIS "UnitTidakPernahAda"
    Maka tabel tidak menampilkan baris data atau kosong

  @FLTR-008
  Skenario: Klik tombol Batal pada modal Filter
    Ketika admin menekan tombol Filter
    Dan admin menekan tombol Batal pada modal filter
    Maka modal filter user tertutup
    Dan seluruh data user awal kembali ditampilkan pada tabel

  @FLTR-009
  Skenario: Klik ikon silang close button di kanan atas modal
    Ketika admin menekan tombol Filter
    Dan admin menutup modal filter melalui tombol silang
    Maka modal filter user tertutup

  @FLTR-010
  Skenario: Klik area overlay backdrop di luar modal
    Ketika admin menekan tombol Filter
    Dan admin menutup modal filter melalui klik backdrop
    Maka modal filter user tertutup

  @FLTR-011
  Skenario: Klik tombol Reset di bar pencarian utama saat filter aktif
    Ketika admin menerapkan filter Unit HRIS "SQA Core"
    Dan admin menekan tombol Reset
    Maka seluruh data user awal kembali ditampilkan pada tabel

  @FLTR-012
  Skenario: Buka kembali modal filter setelah menerapkan filter
    Ketika admin menerapkan filter Unit HRIS "SQA Core"
    Dan admin menekan tombol Filter
    Maka nilai input filter sebelumnya tetap tersimpan

  @FLTR-013
  Skenario: Notifikasi toast setelah filter diterapkan
    Ketika admin menerapkan filter Unit HRIS "SQA Core"
    Maka sistem menampilkan notifikasi "Filter diterapkan"

  @FLTR-014
  Skenario: Tekan tombol Escape saat modal filter terbuka
    Ketika admin menekan tombol Filter
    Dan admin menekan tombol Escape saat modal filter terbuka
    Maka modal filter user tertutup
