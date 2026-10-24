# language: id
@ui @self-registration @module-delete-user
Fitur: Pengujian Hapus Data User Registrasi Mobile
  Sebagai administrator manajemen
  Saya ingin menghapus pendaftar mandiri yang tidak memenuhi kualifikasi
  Agar basis data pendaftar tetap bersih dan terverifikasi

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @DEL-001
  Skenario: Klik ikon hapus pada baris user pertama
    Ketika admin menekan tombol hapus pada baris user pertama
    Maka modal konfirmasi hapus terbuka

  @DEL-002
  Skenario: Periksa teks konfirmasi pada modal hapus
    Ketika admin menekan tombol hapus pada baris user pertama
    Maka modal konfirmasi hapus terbuka
    Dan deskripsi konfirmasi memuat teks "Apakah anda ingin hapus user"

  @DEL-003
  Skenario: Klik tombol Tidak pada modal hapus
    Ketika admin menekan tombol hapus pada baris user pertama
    Dan admin menekan tombol Tidak pada modal konfirmasi
    Maka modal konfirmasi hapus tertutup
    Dan baris user pertama tetap ada pada tabel

  @DEL-004
  Skenario: Klik ikon close silang di header modal hapus
    Ketika admin menekan tombol hapus pada baris user pertama
    Dan admin menekan tombol close silang pada modal konfirmasi hapus
    Maka modal konfirmasi hapus tertutup

  @DEL-005
  Skenario: Klik tombol Ya untuk konfirmasi hapus user
    Ketika admin menekan tombol hapus pada baris user terakhir
    Dan admin menekan tombol Ya pada modal konfirmasi
    Maka modal konfirmasi hapus tertutup
    Dan sistem menampilkan notifikasi "Berhasil Delete User"

  @DEL-006
  Skenario: Periksa total counter paginasi setelah data dihapus
    Ketika admin menekan tombol hapus pada baris user terakhir
    Dan admin menekan tombol Ya pada modal konfirmasi
    Maka counter paginasi berkurang 1 data

  @DEL-007
  Skenario: Hapus user yang checkbox-nya sedang tercentang
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol hapus pada baris user pertama
    Dan admin menekan tombol Ya pada modal konfirmasi
    Maka counter checklist berkurang sesuai baris yang dihapus

  @DEL-008
  Skenario: Hapus seluruh baris data hingga tabel kosong
    Ketika admin menghapus seluruh data pada tabel
    Maka tabel menampilkan kondisi kosong tanpa data

  @DEL-009
  Skenario: Konfirmasi hapus saat backend mengembalikan status 500
    Ketika admin menekan tombol hapus pada baris user pertama
    Dan admin mengonfirmasi hapus saat backend gagal
    Maka sistem menampilkan notifikasi "Berhasil Delete User"

  @DEL-010
  Skenario: Tekan tombol Escape saat modal konfirmasi hapus terbuka
    Ketika admin menekan tombol hapus pada baris user pertama
    Dan admin menekan tombol Escape saat modal konfirmasi hapus terbuka
    Maka modal konfirmasi hapus tertutup
