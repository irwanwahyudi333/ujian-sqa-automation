# language: id
@ui @management @unit-leave @lean
Fitur: Pengujian UI Lean Master Unit Leave
  Sebagai Administrator SQA
  Saya ingin menguji antarmuka Master Aturan Cuti (Unit Leave)
  Untuk memastikan render aturan alokasi cuti, pencarian data, dan interaksi modal tambah/hapus sesuai Skema 1

  Latar Belakang:
    Dengan Pengguna membuka halaman "unit-leave"

  @smoke
  Skenario: Verifikasi render elemen utama pada halaman Unit Leave
    Maka Halaman Unit Leave harus tampil dengan benar
    Dan Jumlah baris data unit leave pada tabel harus lebih dari 0

  Skenario: Pencarian aturan cuti berdasarkan kata kunci
    Ketika Pengguna mencari unit leave dengan kata kunci "Cuti"
    Maka Jumlah baris data unit leave pada tabel harus sesuai hasil pencarian
    Ketika Pengguna menekan tombol reset pencarian unit leave
    Maka Jumlah baris data unit leave pada tabel harus kembali ke semula

  Skenario: Buka dan batalkan modal tambah aturan cuti
    Ketika Pengguna membuka modal tambah unit leave
    Maka Modal tambah unit leave harus ditampilkan
    Ketika Pengguna membatalkan modal tambah unit leave
    Maka Modal tambah unit leave harus tertutup

  Skenario: Tambah data aturan cuti baru melalui modal
    Ketika Pengguna membuka modal tambah unit leave
    Dan Pengguna mengisi form unit leave dengan nama "Cuti Spesial Uji 2026" eligibleMonth "6" maxRemaining "5" carryForward "Maret" totalLeave "12"
    Dan Pengguna menyimpan data unit leave
    Maka Notifikasi toast unit leave harus ditampilkan

  Skenario: Buka dan batalkan modal hapus aturan cuti
    Ketika Pengguna menekan tombol hapus pada baris pertama unit leave
    Maka Modal konfirmasi hapus unit leave harus ditampilkan
    Ketika Pengguna membatalkan hapus unit leave
    Maka Modal konfirmasi hapus unit leave harus tertutup

