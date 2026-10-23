# language: id
@ui @self-registration @module-table
Fitur: Pengujian Interaksi Tabel dan Seleksi Baris Data User
  Sebagai administrator manajemen
  Saya ingin memilih baris data secara tunggal maupun massal
  Agar dapat memproses pembaruan data registrasi dengan efisien

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @TBL-001
  Skenario: Klik checkbox pada salah satu baris user
    Ketika admin mencentang baris user pertama
    Maka toolbar aksi massal tampil pada layar
    Dan counter checklist menampilkan "1 Di checklist"

  @TBL-002
  Skenario: Klik kembali checkbox yang sudah tercentang
    Ketika admin mencentang baris user pertama
    Dan admin mengklik kembali checkbox baris user pertama
    Maka checkbox baris user pertama tidak tercentang

  @TBL-003
  Skenario: Centang checkbox Select All pada header tabel
    Ketika admin mencentang checkbox Select All
    Maka seluruh baris data tercentang
    Dan counter checklist menampilkan seluruh baris tercentang

  @TBL-004
  Skenario: Hilangkan centang pada checkbox Select All
    Ketika admin mencentang checkbox Select All
    Dan admin menghilangkan centang pada checkbox Select All
    Maka seluruh baris data tidak tercentang

  @TBL-005
  Skenario: Centang beberapa baris secara acak
    Ketika admin mencentang beberapa baris secara acak
    Maka checkbox Select All berada pada status indeterminate

  @TBL-006
  Skenario: Centang semua baris satu per satu
    Ketika admin mencentang seluruh baris satu per satu
    Maka seluruh baris data tercentang

  @TBL-007
  Skenario: Hilangkan centang pada satu baris saat semua baris tercentang
    Ketika admin mencentang checkbox Select All
    Dan admin menghilangkan centang satu baris saat seluruh baris tercentang
    Maka checkbox Select All berada pada status indeterminate

  @TBL-008
  Skenario: Verifikasi data Nama Lengkap pada tabel
    Maka kolom nama lengkap memiliki gaya teks tebal

  @TBL-009
  Skenario: Verifikasi data NIK pada tabel
    Maka kolom NIK menampilkan angka 8 atau 16 karakter

  @TBL-010
  Skenario: Verifikasi data Email pada tabel
    Maka kolom email menampilkan alamat email valid

  @TBL-011
  Skenario: Verifikasi data Jabatan Divisi dan Unit HRIS
    Maka kolom jabatan divisi unit berada pada posisi rata tengah

  @TBL-012
  Skenario: Hover kursor mouse di atas baris tabel
    Ketika admin mengarahkan kursor mouse ke atas baris tabel
    Maka latar baris berubah dengan efek sorot lembut

  @TBL-013
  Skenario: Seleksi baris setelah melakukan pencarian
    Ketika admin memilih tipe pencarian "fullname"
    Dan admin memasukkan kata kunci pencarian "Irwan Wahyudi"
    Dan admin menekan tombol Cari
    Dan admin mencentang baris user pertama
    Maka seleksi baris hanya berlaku pada baris hasil pencarian

  @TBL-014
  Skenario: Ganti halaman paginasi saat ada baris tercentang
    Ketika admin mencentang baris user pertama
    Dan admin memilih jumlah baris per halaman "5"
    Maka jumlah baris yang tampil maksimal 5 baris
