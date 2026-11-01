# language: id
@ui @user-monitoring @delete
Fitur: Penghapusan Data User Monitoring (Delete User)
  Sebagai admin manajemen HADIR
  Saya ingin menghapus akun user monitoring yang tidak lagi bertugas
  Agar daftar monitoring karyawan tetap akurat dan relevan

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @DEL-001
  Skenario: Membuka modal dialog konfirmasi Hapus User Monitoring
    Ketika admin menekan tombol hapus pada baris user "Ahmad Fauzi"
    Maka modal dialog konfirmasi Hapus User Monitoring terbuka
    Dan judul modal hapus user monitoring menampilkan "Hapus User Monitoring"

  @DEL-002
  Skenario: Verifikasi teks konfirmasi pada modal dialog hapus
    Ketika admin menekan tombol hapus pada baris user "Ahmad Fauzi"
    Maka teks konfirmasi hapus user monitoring menampilkan "Apakah anda yakin ingin menghapus user monitoring ini?"

  @DEL-003
  Skenario: Menghapus user monitoring dengan konfirmasi tombol Hapus
    Ketika admin menekan tombol hapus pada baris user "Ahmad Fauzi"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Maka modal dialog konfirmasi Hapus User Monitoring tertutup
    Dan notifikasi toast user monitoring menampilkan pesan "Berhasil menghapus user monitoring"
    Dan user bernama "Ahmad Fauzi" tidak ada lagi di tabel user monitoring

  @DEL-004
  Skenario: Membatalkan penghapusan user monitoring via tombol Tidak / Batal
    Ketika admin menekan tombol hapus pada baris user "Bella Saphira"
    Dan admin menekan tombol Tidak pada modal hapus user monitoring
    Maka modal dialog konfirmasi Hapus User Monitoring tertutup
    Dan user bernama "Bella Saphira" tetap ada pada tabel user monitoring

  @DEL-005
  Skenario: Menutup modal dialog hapus via tombol silang (X)
    Ketika admin menekan tombol hapus pada baris user "Cahyo Nugroho"
    Dan admin menekan tombol silang tutup pada modal hapus user monitoring
    Maka modal dialog konfirmasi Hapus User Monitoring tertutup tanpa menghapus data
    Dan user bernama "Cahyo Nugroho" tetap ada pada tabel user monitoring

  @DEL-006
  Skenario: Menutup modal dialog hapus via tombol Escape pada keyboard
    Ketika admin menekan tombol hapus pada baris user "Dewi Anggraini"
    Dan admin menekan tombol keyboard Escape pada user monitoring
    Maka modal dialog konfirmasi Hapus User Monitoring tertutup seketika
    Dan user bernama "Dewi Anggraini" tetap ada pada tabel user monitoring

  @DEL-007
  Skenario: Pembaruan total count paginasi setelah hapus user monitoring
    Ketika admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Maka jumlah baris user monitoring di tabel berkurang 1

  @DEL-008
  Skenario: Hapus user monitoring pada halaman kedua tabel
    Ketika admin berpindah ke halaman 2 pada paginasi user monitoring
    Dan admin menekan tombol hapus pada baris user monitoring pertama di halaman 2
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Maka baris user tersebut terhapus dari tabel

  @DEL-009
  Skenario: Hapus user saat kondisi filter unit aktif
    Ketika admin menekan tombol Filter Unit user monitoring
    Dan admin memilih unit "Technology" pada modal filter
    Dan admin menekan tombol Terapkan pada modal filter
    Dan admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Maka user terhapus dan filter unit "Technology" tetap aktif

  @DEL-010
  Skenario: Verifikasi pesan toast notifikasi sukses hapus user
    Ketika admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Maka notifikasi toast user monitoring menampilkan pesan "Berhasil menghapus user monitoring"

  @DEL-011
  Skenario: Penutupan notifikasi toast hapus secara manual
    Ketika admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus user monitoring
    Dan admin menekan tombol silang tutup pada toast notifikasi user monitoring
    Maka toast notifikasi user monitoring tertutup seketika

  @DEL-012
  Skenario: Pembatalan hapus berkali-kali tanpa memengaruhi integritas data
    Ketika admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol Tidak pada modal hapus user monitoring
    Dan admin menekan tombol hapus pada baris user monitoring pertama
    Dan admin menekan tombol Tidak pada modal hapus user monitoring
    Maka seluruh data user monitoring di tabel tetap lengkap dan utuh

  @DEL-013
  Skenario: Memastikan hidden input ID terisi pada modal hapus
    Ketika admin menekan tombol hapus pada baris user monitoring pertama
    Maka hidden input ID pada modal hapus memiliki nilai ID yang valid
