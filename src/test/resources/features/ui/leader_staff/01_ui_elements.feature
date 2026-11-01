# language: id
@ui @leader-staff @staff @ui-elements
Fitur: Verifikasi Kelengkapan Elemen Visual dan Antarmuka Halaman Leader Staff Lists
  Sebagai Leader atau Tim Manajemen
  Saya ingin memastikan seluruh elemen antarmuka halaman daftar staff tampil lengkap dan benar
  Agar proses pemantauan dan pengelolaan anggota tim staf dapat dilakukan dengan optimal

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @UI-001
  Skenario: Memastikan judul halaman leader staff lists tampil benar
    Maka judul halaman leader staff menampilkan "Daftar Staff"

  @UI-002
  Skenario: Memastikan breadcrumbs navigasi menampilkan hirarki yang tepat
    Maka breadcrumbs navigasi leader staff menampilkan "Dashboard / Leader / Staff Lists"

  @UI-003
  Skenario: Memastikan input pencarian staff tampil dengan placeholder yang tepat
    Maka input pencarian leader staff tampil dengan placeholder "Cari berdasarkan nama / NIK"

  @UI-004
  Skenario: Memastikan tombol Filter tampil pada toolbar
    Maka tombol Filter leader staff tampil dan aktif

  @UI-005
  Skenario: Memastikan tombol Reset tampil pada toolbar
    Maka tombol Reset leader staff tampil dan aktif

  @UI-006
  Skenario: Memastikan tombol Search tampil pada toolbar
    Maka tombol Search leader staff tampil dan aktif

  @UI-007
  Skenario: Memastikan tombol Tambah Staff tampil pada toolbar
    Maka tombol Tambah Staff pada header leader staff tampil aktif

  @UI-008
  Skenario: Memastikan header tabel menampilkan sembilan kolom lengkap
    Maka tabel leader staff memuat kolom Foto, NIK, Nama Lengkap, Email, Unit, Jabatan, No. HP, Status, dan Aksi

  @UI-009
  Skenario: Memastikan baris data staff menampilkan informasi yang terstruktur
    Maka baris data leader staff menampilkan avatar inisial, NIK, nama lengkap, email, unit, jabatan, no. hp, dan status

  @UI-010
  Skenario: Memastikan tombol aksi Detail tampil pada baris data
    Maka tombol aksi Detail tampil pada baris data leader staff

  @UI-011
  Skenario: Memastikan tombol aksi Edit tampil pada baris data
    Maka tombol aksi Edit tampil pada baris data leader staff

  @UI-012
  Skenario: Memastikan tombol aksi Hapus tampil pada baris data
    Maka tombol aksi Hapus tampil pada baris data leader staff

  @UI-013
  Skenario: Memastikan kontrol paginasi baris tampil pada bagian bawah tabel
    Maka kontrol paginasi leader staff tampil lengkap di bawah tabel

  @UI-014
  Skenario: Memastikan tombol navigasi halaman tampil pada kontrol paginasi
    Maka tombol navigasi halaman First, Prev, Next, Last leader staff tampil

  @UI-015
  Skenario: Memastikan switch mode Dense padding dapat diaktifkan
    Ketika leader mengaktifkan mode Dense padding leader staff
    Maka tabel leader staff menerapkan format tampilan rapat
