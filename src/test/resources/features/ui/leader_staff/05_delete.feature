# language: id
@ui @leader-staff @staff @delete
Fitur: Pengujian Peninjauan Detail dan Penghapusan Staff
  Sebagai Leader
  Saya ingin meninjau detail lengkap staf dan menghapus penugasan staf yang tidak lagi aktif
  Agar komposisi tim staf terpelihara dengan rapi dan terkontrol

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @DEL-001
  Skenario: Membuka modal dialog Detail Informasi Staff
    Ketika leader menekan tombol Detail pada baris staf dengan NIK "STF-001"
    Maka modal detail informasi staff ditampilkan
    Dan detail modal menampilkan NIK "STF-001" dan nama "Ahmad Fauzi"

  @DEL-002
  Skenario: Menutup modal detail staff via tombol Tutup
    Ketika leader menekan tombol Detail pada baris staf dengan NIK "STF-001"
    Dan leader menekan tombol Tutup pada modal detail staff
    Maka modal detail informasi staff tertutup

  @DEL-003
  Skenario: Menutup modal detail staff via tombol keyboard Escape
    Ketika leader menekan tombol Detail pada baris staf dengan NIK "STF-001"
    Dan leader menekan tombol keyboard Escape
    Maka modal detail informasi staff tertutup

  @DEL-004
  Skenario: Membuka modal dialog konfirmasi Hapus Staff
    Ketika leader menekan tombol Hapus pada baris staf dengan NIK "STF-001"
    Maka modal konfirmasi hapus staff ditampilkan
    Dan teks konfirmasi hapus memuat "Apakah anda yakin ingin menghapus staff ini dari daftar anggota tim leader?"

  @DEL-005
  Skenario: Pembatalan penghapusan staf via tombol Batal
    Ketika leader menekan tombol Hapus pada baris staf dengan NIK "STF-001"
    Dan leader menekan tombol Batal pada modal hapus staff
    Maka modal konfirmasi hapus staff tertutup
    Dan staf dengan NIK "STF-001" tetap ada di tabel leader staff

  @DEL-006
  Skenario: Menutup modal hapus staff via tombol keyboard Escape
    Ketika leader menekan tombol Hapus pada baris staf dengan NIK "STF-001"
    Dan leader menekan tombol keyboard Escape
    Maka modal konfirmasi hapus staff tertutup

  @DEL-007
  Skenario: Mengonfirmasi penghapusan staf hingga data terhapus dari tabel
    Ketika leader menekan tombol Hapus pada baris staf dengan NIK "STF-001"
    Dan leader menekan tombol Konfirmasi Hapus pada modal hapus staff
    Maka modal konfirmasi hapus staff tertutup
    Dan notifikasi toast leader staff menampilkan pesan "Berhasil menghapus staff"
    Dan staf dengan NIK "STF-001" tidak lagi ada di tabel leader staff
