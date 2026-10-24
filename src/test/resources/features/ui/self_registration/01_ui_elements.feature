# language: id
@ui @self-registration @module-ui-elements
Fitur: Pengujian Elemen UI dan Tampilan Halaman Self-Registration
  Sebagai administrator manajemen
  Saya ingin memastikan seluruh elemen antarmuka halaman pendaftaran mandiri tersedia dan berfungsi
  Agar monitoring dan peninjauan calon pendaftar dapat dilakukan dengan optimal

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @UI-001 @smoke
  Skenario: Buka URL self-registration dengan sesi terautentikasi
    Maka sistem menampilkan judul halaman "Data User Registrasi Mobile"
    Dan tabel data pengguna terlihat pada halaman

  @UI-002
  Skenario: Periksa keberadaan bar pencarian dan elemen di dalamnya
    Maka dropdown tipe pencarian terlihat
    Dan input teks pencarian terlihat
    Dan tombol Cari, Filter, dan Reset terlihat

  @UI-003
  Skenario: Periksa opsi dropdown tipe pencarian
    Maka dropdown tipe pencarian memiliki opsi Nama dan Nik

  @UI-004
  Skenario: Periksa placeholder pada input pencarian
    Maka input pencarian memiliki placeholder "Search"

  @UI-005
  Skenario: Periksa header tabel data pengguna
    Maka tabel memuat kolom Nama, Nik, Email, Jabatan, Divisi, Unit, dan Aksi
    Dan checkbox Select All terlihat pada header tabel

  @UI-006
  Skenario: Periksa kondisi awal toolbar aksi massal
    Maka kondisi awal toolbar aksi massal tidak terlihat

  @UI-007
  Skenario: Periksa ketersediaan baris data pada tabel
    Maka baris user pertama tetap ada pada tabel

  @UI-008
  Skenario: Periksa ikon aksi pada setiap baris data
    Maka setiap baris data memiliki tombol lihat dan tombol hapus

  @UI-009
  Skenario: Periksa tooltip tombol aksi pada tabel
    Maka tombol aksi memiliki tooltip yang sesuai

  @UI-010
  Skenario: Periksa bar paginasi di bawah tabel
    Maka bar paginasi menampilkan dropdown dan tombol navigasi

  @UI-011
  Skenario: Periksa default pilihan baris per halaman
    Maka default baris per halaman bernilai "10"

  @UI-012
  Skenario: Periksa format tampilan tanggal pada tabel
    Maka format tanggal dibuat dan diupdate sesuai standar

  @UI-013
  Skenario: Periksa konsol browser saat memuat halaman
    Maka tidak terdapat pesan error kritis pada konsol

  @UI-014
  Skenario: Muat ulang halaman self-registration
    Ketika admin memuat ulang halaman
    Maka sistem menampilkan judul halaman "Data User Registrasi Mobile"
    Dan tabel data pengguna terlihat pada halaman

  @UI-015
  Skenario: Periksa font dan styling tema Flexy Next.js
    Maka styling dan tipografi tema sesuai standar
