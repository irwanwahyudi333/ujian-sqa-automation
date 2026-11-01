# language: id
@ui @leader-izin-off @izin-off @ui-elements
Fitur: Verifikasi Kelengkapan Elemen Visual dan Antarmuka Halaman Leader Izin Off
  Sebagai Leader atau Tim Manajemen
  Saya ingin memastikan seluruh elemen antarmuka halaman izin off tampil lengkap dan benar
  Agar proses peninjauan dan persetujuan izin off dapat dilakukan tanpa kendala

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @UI-001
  Skenario: Memastikan judul halaman leader izin off tampil benar
    Maka judul halaman leader izin off menampilkan "Izin Off (Leader)"

  @UI-002
  Skenario: Memastikan breadcrumbs navigasi menampilkan hirarki yang tepat
    Maka breadcrumbs navigasi leader izin off menampilkan "Dashboard / Leader / Izin Off"

  @UI-003
  Skenario: Memastikan input pencarian nama karyawan tampil dengan placeholder yang tepat
    Maka input pencarian leader izin off tampil dengan placeholder "Cari nama karyawan..."

  @UI-004
  Skenario: Memastikan dropdown filter status menampilkan pilihan lengkap
    Maka dropdown filter status leader izin off memuat opsi "Semua Status"

  @UI-005
  Skenario: Memastikan kontrol filter rentang tanggal tampil
    Maka filter rentang tanggal leader izin off tampil di toolbar pencarian

  @UI-006
  Skenario: Memastikan tombol Search tampil pada toolbar
    Maka tombol Search leader izin off tampil dan dapat diklik

  @UI-007
  Skenario: Memastikan tombol Reset tampil pada toolbar
    Maka tombol Reset leader izin off tampil dan dapat diklik

  @UI-008
  Skenario: Memastikan tombol Ajukan Izin Off tampil pada header
    Maka tombol Ajukan Izin Off pada header leader izin off tampil aktif

  @UI-009
  Skenario: Memastikan header tabel menampilkan tujuh kolom lengkap
    Maka tabel leader izin off memuat kolom Foto, Nama Karyawan, Upliner, Tanggal Off, Status, Alasan, dan Aksi

  @UI-010
  Skenario: Memastikan baris data permohonan menampilkan informasi yang terstruktur
    Maka baris data leader izin off menampilkan avatar inisial, nama karyawan, upliner, tanggal off, dan status

  @UI-011
  Skenario: Memastikan tombol aksi Setujui tampil pada baris data
    Maka tombol aksi Setujui tampil pada setiap baris permohonan leader izin off

  @UI-012
  Skenario: Memastikan tombol aksi Tolak tampil pada baris data
    Maka tombol aksi Tolak tampil pada setiap baris permohonan leader izin off

  @UI-013
  Skenario: Memastikan tombol aksi Detail tampil pada baris data
    Maka tombol aksi Detail tampil pada setiap baris permohonan leader izin off

  @UI-014
  Skenario: Memastikan informasi dan kontrol paginasi tampil pada bagian bawah tabel
    Maka kontrol paginasi leader izin off tampil lengkap di bawah tabel

  @UI-015
  Skenario: Memastikan switch mode Dense padding dapat diaktifkan
    Ketika leader mengaktifkan switch Dense padding leader izin off
    Maka tabel leader izin off menerapkan format tampilan rapat
