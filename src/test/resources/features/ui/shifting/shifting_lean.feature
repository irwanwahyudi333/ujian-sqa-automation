# language: id
@ui @management @shifting @lean
Fitur: Pengujian UI Lean Master Shifting
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Shifting
  Untuk memastikan render elemen visual, pencarian pola kerja, serta modal tambah/hapus berfungsi dengan baik sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "shifting"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Shifting
    Maka Halaman Shifting harus tampil dengan benar
    Dan Jumlah baris data shifting pada tabel harus lebih dari 0

  Skenario: Pencarian data shifting berdasarkan kata kunci yang valid
    Ketika Pengguna mencari shifting dengan kata kunci "DIKA"
    Maka Jumlah baris data shifting pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian shifting
    Maka Jumlah baris data shifting pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah shifting
    Ketika Pengguna membuka modal tambah shifting
    Maka Modal tambah shifting harus ditampilkan
    Ketika Pengguna membatalkan modal tambah shifting
    Maka Modal tambah shifting harus tertutup

  Skenario: Tambah data shifting baru melalui modal
    Ketika Pengguna membuka modal tambah shifting
    Dan Pengguna mengisi form shifting dengan kode "SH-TEST-01" nama "Shift Khusus Testing" unit "Technology" timeIn "08:00" timeOut "17:00" breakStart "12:00" breakEnd "13:00"
    Dan Pengguna menyimpan data shifting
    Maka Notifikasi toast shifting harus ditampilkan

  Skenario: Buka dan batalkan modal hapus shifting
    Ketika Pengguna menekan tombol hapus pada baris pertama shifting
    Maka Modal konfirmasi hapus shifting harus ditampilkan
    Ketika Pengguna membatalkan hapus shifting
    Maka Modal konfirmasi hapus shifting harus tertutup

