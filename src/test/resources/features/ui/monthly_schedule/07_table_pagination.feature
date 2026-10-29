# language: id
@ui @monthly-schedule @table-pagination
Fitur: Navigasi Paginasi dan Pengaturan Kerapatan Tabel Jadwal
  Sebagai admin manajemen HADIR
  Saya ingin mengatur jumlah baris per halaman dan berpindah halaman data
  Agar saya dapat menelusuri data jadwal karyawan dalam jumlah besar dengan nyaman

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @PAG-001
  Skenario: Ubah baris per halaman menjadi 5
    Ketika admin memilih opsi baris per halaman "5"
    Maka jumlah baris yang tampil pada tabel maksimal 5 baris
    Dan label paginasi memuat teks "1-5 of"

  @PAG-002
  Skenario: Ubah baris per halaman menjadi 10
    Ketika admin memilih opsi baris per halaman "10"
    Maka jumlah baris yang tampil pada tabel maksimal 10 baris
    Dan label paginasi memuat teks "1-10 of"

  @PAG-003
  Skenario: Ubah baris per halaman menjadi 25
    Ketika admin memilih opsi baris per halaman "25"
    Maka seluruh baris hingga 25 data ditampilkan pada satu halaman

  @PAG-004
  Skenario: Navigasi ke halaman selanjutnya (Next Page)
    Ketika admin menekan tombol Next page
    Maka tabel menampilkan data halaman berikutnya
    Dan label paginasi memperbarui rentang baris yang aktif

  @PAG-005
  Skenario: Navigasi ke halaman sebelumnya (Previous Page)
    Ketika admin berada di halaman kedua tabel
    Dan admin menekan tombol Previous page
    Maka tabel menampilkan kembali data halaman pertama

  @PAG-006
  Skenario: Navigasi langsung ke halaman terakhir (Last Page)
    Ketika admin menekan tombol Last page
    Maka tabel langsung menampilkan halaman terakhir dari seluruh data

  @PAG-007
  Skenario: Navigasi langsung ke halaman pertama (First Page)
    Ketika admin berada di halaman terakhir tabel
    Dan admin menekan tombol First page
    Maka tabel langsung kembali ke halaman pertama

  @PAG-008
  Skenario: Tombol Prev & First disabled di halaman pertama
    Maka tombol Previous page berstatus disabled
    Dan tombol First page berstatus disabled

  @PAG-009
  Skenario: Tombol Next & Last disabled di halaman terakhir
    Ketika admin berpindah ke halaman terakhir tabel
    Maka tombol Next page berstatus disabled
    Dan tombol Last page berstatus disabled

  @PAG-010
  Skenario: Akurasi teks displayed rows halaman 1
    Maka label paginasi menampilkan rentang baris awal "1-10 of 12"

  @PAG-011
  Skenario: Akurasi teks displayed rows halaman 2
    Ketika admin menekan tombol Next page
    Maka label paginasi menampilkan rentang baris lanjutan "11-12 of 12"

  @PAG-012
  Skenario: Toggle checkbox Dense padding aktif
    Ketika admin mencentang opsi Dense padding
    Maka padding baris tabel berubah menjadi lebih rapat

  @PAG-013
  Skenario: Toggle checkbox Dense padding non-aktif
    Ketika admin menghapus centang opsi Dense padding
    Maka padding baris tabel kembali ke ukuran normal

  @PAG-014
  Skenario: Reset halaman saat mengubah rows per page
    Ketika admin berada di halaman kedua tabel
    Dan admin mengubah baris per halaman menjadi "25"
    Maka paginasi otomatis kembali ke halaman pertama

  @PAG-015
  Skenario: Konsistensi data tabel saat navigasi bolak-balik
    Ketika admin bernavigasi bolak-balik antara halaman 1 dan 2
    Maka data setiap halaman tetap konsisten dan tidak mengalami duplikasi
