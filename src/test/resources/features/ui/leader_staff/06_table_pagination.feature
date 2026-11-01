# language: id
@ui @leader-staff @staff @table-pagination
Fitur: Pengujian Paginasi dan Navigasi Tabel Staff
  Sebagai Leader
  Saya ingin mengatur jumlah baris per halaman dan menavigasi antar halaman data staf
  Agar pembacaan data anggota tim staf lebih nyaman dan terstruktur

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @PAG-001
  Skenario: Memastikan informasi rentang baris default di halaman pertama
    Maka informasi paginasi leader staff menampilkan "1-10 of 12"

  @PAG-002
  Skenario: Mengubah rows per page menjadi 5 baris
    Ketika leader memilih rows per page "5" pada tabel leader staff
    Maka jumlah baris data pada tabel leader staff adalah 5
    Dan informasi paginasi leader staff menampilkan "1-5 of 12"

  @PAG-003
  Skenario: Mengubah rows per page menjadi 25 baris
    Ketika leader memilih rows per page "25" pada tabel leader staff
    Maka jumlah baris data pada tabel leader staff adalah 12
    Dan informasi paginasi leader staff menampilkan "1-12 of 12"

  @PAG-004
  Skenario: Navigasi ke halaman berikutnya via tombol Next
    Ketika leader menekan tombol Next page leader staff
    Maka informasi paginasi leader staff menampilkan "11-12 of 12"

  @PAG-005
  Skenario: Navigasi kembali ke halaman sebelumnya via tombol Prev
    Ketika leader menekan tombol Next page leader staff
    Dan leader menekan tombol Prev page leader staff
    Maka informasi paginasi leader staff menampilkan "1-10 of 12"

  @PAG-006
  Skenario: Memastikan tombol First dan Prev disabled di halaman pertama
    Maka tombol Prev page leader staff berstatus disabled
    Dan tombol First page leader staff berstatus disabled

  @PAG-007
  Skenario: Navigasi langsung ke halaman terakhir via tombol Last
    Ketika leader menekan tombol Last page leader staff
    Maka tombol Next page leader staff berstatus disabled
    Dan tombol Last page leader staff berstatus disabled

  @PAG-008
  Skenario: Navigasi langsung ke halaman pertama via tombol First
    Ketika leader menekan tombol Last page leader staff
    Dan leader menekan tombol First page leader staff
    Maka informasi paginasi leader staff menampilkan "1-10 of 12"
