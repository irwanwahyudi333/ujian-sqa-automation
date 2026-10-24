# language: id
@ui @self-registration @module-nav-pagination
Fitur: Pengujian Navigasi Detail dan Kontrol Paginasi
  Sebagai administrator manajemen
  Saya ingin bernavigasi ke detail user dan mengontrol jumlah baris per halaman
  Agar eksplorasi data pendaftar berjalan fleksibel dan terstruktur

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @NAV-001
  Skenario: Klik tombol lihat pada baris data user
    Ketika admin menekan tombol lihat pada baris data user
    Maka sistem mengarahkan ke halaman detail user

  @NAV-002
  Skenario: Buka link detail user di tab baru browser
    Ketika admin membuka link detail user di tab baru
    Maka tab baru memuat halaman edit user

  @NAV-003
  Skenario: Kembali ke halaman registrasi mandiri via tombol browser Back
    Ketika admin menekan tombol lihat pada baris data user
    Dan admin kembali ke halaman sebelumnya via tombol back browser
    Maka halaman self-registration kembali aktif

  @PAG-001
  Skenario: Ubah pilihan rows per page menjadi 5
    Ketika admin memilih jumlah baris per halaman "5"
    Maka jumlah baris yang tampil maksimal 5 baris

  @PAG-002
  Skenario: Ubah pilihan rows per page menjadi 25
    Ketika admin memilih jumlah baris per halaman "25"
    Maka jumlah baris yang tampil maksimal 25 baris

  @PAG-003
  Skenario: Periksa status tombol navigasi di halaman pertama
    Maka tombol First Page dan Previous Page berstatus disabled di halaman pertama

  @PAG-004
  Skenario: Klik tombol Next Page saat terdapat lebih dari 1 halaman
    Ketika admin memilih jumlah baris per halaman "5"
    Dan admin menekan tombol Next Page
    Maka label paginasi memuat informasi jumlah data

  @PAG-005
  Skenario: Klik tombol Last Page
    Ketika admin memilih jumlah baris per halaman "5"
    Dan admin menekan tombol Last Page
    Maka label paginasi memuat informasi jumlah data

  @PAG-006
  Skenario: Klik tombol Previous Page dari halaman kedua
    Ketika admin memilih jumlah baris per halaman "5"
    Dan admin menekan tombol Next Page
    Dan admin menekan tombol Previous Page
    Maka label paginasi memuat informasi jumlah data

  @PAG-007
  Skenario: Klik tombol First Page dari halaman terakhir
    Ketika admin memilih jumlah baris per halaman "5"
    Dan admin menekan tombol Last Page
    Dan admin menekan tombol First Page
    Maka label paginasi memuat informasi jumlah data

  @PAG-008
  Skenario: Verifikasi akurasi teks label displayed rows
    Maka label paginasi memuat informasi jumlah data
