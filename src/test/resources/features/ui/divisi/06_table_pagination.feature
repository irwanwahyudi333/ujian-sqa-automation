# language: id
@ui @divisi @table-pagination
Fitur: Integritas Tabel Data dan Kontrol Paginasi Halaman Divisi
  Sebagai admin manajemen HADIR
  Saya ingin memeriksa struktur tabel data divisi dan menavigasi baris halaman data
  Agar data divisi tersaji secara rapi, konsisten, dan mudah dieksplorasi

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @TBL-001
  Skenario: Memeriksa kelengkapan 4 kolom data pada tabel divisi
    Maka tabel data divisi menampilkan 4 kolom header:
      | NAMA Divisi    |
      | TANGGAL BUAT   |
      | TANGGAL UPDATE |
      | Aksi           |
    Dan setiap baris data memuat nama divisi, tanggal buat, tanggal update, dan aksi

  @TBL-002
  Skenario: Memeriksa format penulisan pada kolom Tanggal Buat
    Maka kolom Tanggal Buat menampilkan format tanggal "DD MMM YYYY" dan jam "HH:mm:ss"

  @TBL-003
  Skenario: Memeriksa format penulisan pada kolom Tanggal Update
    Maka kolom Tanggal Update menampilkan format tanggal "DD MMM YYYY" dan jam "HH:mm:ss"

  @TBL-004
  Skenario: Memeriksa tampilan baris jika nilai tanggal kosong atau null
    Maka cell tanggal yang kosong menampilkan karakter strip "-" tanpa error antarmuka

  @TBL-005
  Skenario: Memeriksa efek visual hover pada baris tabel divisi
    Ketika admin mengarahkan kursor ke salah satu baris tabel divisi
    Maka baris tabel tersebut mengalami perubahan latar belakang efek hover

  @TBL-006
  Skenario: Memeriksa perataan teks pada kolom tabel data divisi
    Maka kolom Nama dan Tanggal rata kiri serta kolom Aksi rata tengah

  @TBL-007
  Skenario: Memeriksa perilaku rendering nama divisi teks panjang pada tabel
    Maka nama divisi dengan teks panjang tampil rapi tanpa merusak tata letak tabel

  @TBL-008
  Skenario: Menutup menu aksi 3-titik saat mengklik di luar menu
    Ketika admin menekan tombol menu 3-titik pada baris pertama
    Dan admin mengklik area luar menu aksi
    Maka menu aksi 3-titik otomatis tertutup

  @TBL-009
  Skenario: Membuka menu aksi 3-titik pada baris lain secara bergantian
    Ketika admin menekan tombol menu 3-titik pada baris pertama
    Dan admin menekan tombol menu 3-titik pada baris kedua
    Maka menu aksi pada baris pertama tertutup dan menu aksi baris kedua terbuka

  @TBL-010
  Skenario: Memeriksa keberadaan atribut data-id pada baris tabel divisi
    Maka setiap baris pada tabel divisi memiliki atribut data-id yang valid

  @PAG-001
  Skenario: Memeriksa pilihan opsi rows per page pada dropdown paginasi
    Maka dropdown rows per page menyediakan opsi "5", "10", dan "25"

  @PAG-002
  Skenario: Mengubah rows per page menjadi 5 data per halaman
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Maka jumlah baris yang tampil pada tabel divisi maksimal 5 baris
    Dan label displayed rows mencakup "1-5 of"

  @PAG-003
  Skenario: Mengubah rows per page menjadi 25 data per halaman
    Ketika admin memilih jumlah baris per halaman divisi "25"
    Maka seluruh data divisi tampil dalam satu halaman
    Dan jumlah baris yang tampil sesuai dengan total data

  @PAG-004
  Skenario: Memeriksa status tombol navigasi di halaman pertama
    Maka tombol First Page dan Previous Page berstatus dinonaktifkan di halaman pertama

  @PAG-005
  Skenario: Menavigasi ke halaman berikutnya dengan tombol Next Page
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Dan admin menekan tombol Next Page pada paginasi divisi
    Maka tabel divisi menampilkan data halaman kedua
    Dan tombol Previous Page menjadi aktif

  @PAG-006
  Skenario: Menavigasi kembali ke halaman sebelumnya dengan tombol Previous Page
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Dan admin menekan tombol Next Page pada paginasi divisi
    Dan admin menekan tombol Previous Page pada paginasi divisi
    Maka tabel divisi kembali menampilkan data halaman pertama

  @PAG-007
  Skenario: Melompat langsung ke halaman terakhir dengan tombol Last Page
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Dan admin menekan tombol Last Page pada paginasi divisi
    Maka tabel divisi menampilkan data pada halaman terakhir
    Dan tombol Next Page berstatus dinonaktifkan

  @PAG-008
  Skenario: Melompat kembali ke halaman pertama dari halaman terakhir dengan tombol First Page
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Dan admin menekan tombol Last Page pada paginasi divisi
    Dan admin menekan tombol First Page pada paginasi divisi
    Maka tabel divisi kembali ke halaman pertama
    Dan tombol First Page berstatus dinonaktifkan

  @PAG-009
  Skenario: Memeriksa status tombol navigasi saat berada di halaman terakhir
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Dan admin menekan tombol Last Page pada paginasi divisi
    Maka tombol Next Page dan Last Page berstatus dinonaktifkan

  @PAG-010
  Skenario: Memeriksa akurasi pembaruan teks displayed rows saat berpindah halaman
    Ketika admin memilih jumlah baris per halaman divisi "5"
    Maka label displayed rows menampilkan "1-5 of"
    Ketika admin menekan tombol Next Page pada paginasi divisi
    Maka label displayed rows menampilkan rentang halaman berikutnya
