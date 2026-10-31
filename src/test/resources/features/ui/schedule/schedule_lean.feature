# language: id
@ui @management @schedule @lean
Fitur: Pengujian UI Lean Master Schedule
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Jadwal Kerja (Schedule)
  Untuk memastikan render tabel jadwal, filter pencarian, dan modal tambah/hapus berfungsi sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "schedule"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Schedule
    Maka Halaman Schedule harus tampil dengan benar
    Dan Jumlah baris data schedule pada tabel harus lebih dari 0

  Skenario: Pencarian jadwal berdasarkan kata kunci
    Ketika Pengguna mencari schedule dengan kata kunci "Outing"
    Maka Jumlah baris data schedule pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian schedule
    Maka Jumlah baris data schedule pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah schedule
    Ketika Pengguna membuka modal tambah schedule
    Maka Modal tambah schedule harus ditampilkan
    Ketika Pengguna membatalkan modal tambah schedule
    Maka Modal tambah schedule harus tertutup

  Skenario: Tambah jadwal kerja baru melalui modal
    Ketika Pengguna membuka modal tambah schedule
    Dan Pengguna mengisi form schedule dengan nama "Jadwal Uji Otomasi" tipe "Jadwal kerja tetap" hariKerja "Senin - Jumat"
    Dan Pengguna menyimpan data schedule
    Maka Notifikasi toast schedule harus ditampilkan

  Skenario: Buka dan batalkan modal hapus schedule
    Ketika Pengguna menekan tombol hapus pada baris pertama schedule
    Maka Modal konfirmasi hapus schedule harus ditampilkan
    Ketika Pengguna membatalkan hapus schedule
    Maka Modal konfirmasi hapus schedule harus tertutup

