# language: id
@ui @management @calendar @lean
Fitur: Pengujian UI Lean Master Calendar
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Kalender Kerja
  Untuk memastikan render kalender unit kerja, pencarian teks, dan dialog tambah/hapus berfungsi sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "calendar"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Calendar
    Maka Halaman Calendar harus tampil dengan benar
    Dan Jumlah baris data calendar pada tabel harus lebih dari 0

  Skenario: Pencarian kalender berdasarkan kata kunci
    Ketika Pengguna mencari calendar dengan kata kunci "hello"
    Maka Jumlah baris data calendar pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian calendar
    Maka Jumlah baris data calendar pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah calendar
    Ketika Pengguna membuka modal tambah calendar
    Maka Modal tambah calendar harus ditampilkan
    Ketika Pengguna membatalkan modal tambah calendar
    Maka Modal tambah calendar harus tertutup

  Skenario: Tambah data kalender unit baru melalui modal
    Ketika Pengguna membuka modal tambah calendar
    Dan Pengguna mengisi form calendar dengan nama "Kalender Divisi QA 2026" departemen "IT Development"
    Dan Pengguna menyimpan data calendar
    Maka Notifikasi toast calendar harus ditampilkan

  Skenario: Buka dan batalkan modal hapus calendar
    Ketika Pengguna menekan tombol hapus pada baris pertama calendar
    Maka Modal konfirmasi hapus calendar harus ditampilkan
    Ketika Pengguna membatalkan hapus calendar
    Maka Modal konfirmasi hapus calendar harus tertutup

