# language: id
@ui @management @day-off @lean
Fitur: Pengujian UI Lean Master Day Off
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Hari Libur (Day Off)
  Untuk memastikan render tanggal hari libur, pencarian teks, dan dialog tambah/hapus sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "day-off"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Day Off
    Maka Halaman Day Off harus tampil dengan benar
    Dan Jumlah baris data day off pada tabel harus lebih dari 0

  Skenario: Pencarian hari libur berdasarkan kata kunci
    Ketika Pengguna mencari day off dengan kata kunci "Nasional"
    Maka Jumlah baris data day off pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian day off
    Maka Jumlah baris data day off pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah hari libur
    Ketika Pengguna membuka modal tambah day off
    Maka Modal tambah day off harus ditampilkan
    Ketika Pengguna membatalkan modal tambah day off
    Maka Modal tambah day off harus tertutup

  Skenario: Tambah data hari libur baru melalui modal
    Ketika Pengguna membuka modal tambah day off
    Dan Pengguna mengisi form day off dengan tanggal "2026-12-25" deskripsi "Hari Natal 2026" kategori "Liburan Nasional"
    Dan Pengguna menyimpan data day off
    Maka Notifikasi toast day off harus ditampilkan

  Skenario: Buka dan batalkan modal hapus hari libur
    Ketika Pengguna menekan tombol hapus pada baris pertama day off
    Maka Modal konfirmasi hapus day off harus ditampilkan
    Ketika Pengguna membatalkan hapus day off
    Maka Modal konfirmasi hapus day off harus tertutup

