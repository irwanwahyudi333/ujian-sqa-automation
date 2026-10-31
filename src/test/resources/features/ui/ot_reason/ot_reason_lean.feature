# language: id
@ui @management @ot-reason @lean
Fitur: Pengujian UI Lean Master Overtime Reason
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Alasan Lembur (OT Reason)
  Untuk memastikan render daftar alasan lembur, pencarian teks, dan dialog tambah/hapus berfungsi sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "ot-reason"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Overtime Reason
    Maka Halaman Overtime Reason harus tampil dengan benar
    Dan Jumlah baris data ot reason pada tabel harus lebih dari 0

  Skenario: Pencarian alasan lembur berdasarkan kata kunci
    Ketika Pengguna mencari ot reason dengan kata kunci "Maintenance"
    Maka Jumlah baris data ot reason pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian ot reason
    Maka Jumlah baris data ot reason pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah overtime reason
    Ketika Pengguna membuka modal tambah ot reason
    Maka Modal tambah ot reason harus ditampilkan
    Ketika Pengguna membatalkan modal tambah ot reason
    Maka Modal tambah ot reason harus tertutup

  Skenario: Tambah data alasan lembur baru melalui modal
    Ketika Pengguna membuka modal tambah ot reason
    Dan Pengguna mengisi form ot reason dengan nama "Insiden Keamanan Server Darurat"
    Dan Pengguna menyimpan data ot reason
    Maka Notifikasi toast ot reason harus ditampilkan

  Skenario: Buka dan batalkan modal hapus overtime reason
    Ketika Pengguna menekan tombol hapus pada baris pertama ot reason
    Maka Modal konfirmasi hapus ot reason harus ditampilkan
    Ketika Pengguna membatalkan hapus ot reason
    Maka Modal konfirmasi hapus ot reason harus tertutup

