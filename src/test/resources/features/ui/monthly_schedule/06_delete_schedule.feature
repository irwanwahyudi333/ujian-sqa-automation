# language: id
@ui @monthly-schedule @delete-schedule
Fitur: Penghapusan Penugasan Jadwal Kerja Bulanan (Delete Schedule)
  Sebagai admin manajemen HADIR
  Saya ingin menghapus penugasan jadwal kerja karyawan
  Agar data jadwal yang dibatalkan tidak lagi tercatat dalam operasional

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @DEL-001
  Skenario: Membuka modal konfirmasi Hapus Data
    Ketika admin menekan tombol Delete pada baris ke-1
    Maka modal dialog konfirmasi Hapus Data ditampilkan
    Dan judul modal hapus schedule menampilkan "Hapus Data"

  @DEL-002
  Skenario: Verifikasi teks konfirmasi pada modal hapus
    Ketika admin menekan tombol Delete pada baris ke-1
    Maka teks konfirmasi hapus menampilkan "Apakah anda yakin ingin menhapus data ini ?"

  @DEL-003
  Skenario: Batalkan penghapusan via tombol Tidak
    Ketika admin menekan tombol Delete pada baris ke-1
    Dan admin menekan tombol Tidak pada modal hapus
    Maka modal dialog konfirmasi hapus ditutup
    Dan baris data ke-1 tetap ada pada tabel jadwal

  @DEL-004
  Skenario: Tutup modal hapus via tombol icon X
    Ketika admin menekan tombol Delete pada baris ke-1
    Dan admin menekan tombol close icon X pada modal hapus
    Maka modal dialog konfirmasi hapus ditutup tanpa menghapus data

  @DEL-005
  Skenario: Tutup modal hapus via tombol Escape
    Ketika admin menekan tombol Delete pada baris ke-1
    Dan admin menekan tombol keyboard Escape
    Maka modal dialog konfirmasi hapus ditutup tanpa menghapus data

  @DEL-006
  Skenario: Konfirmasi hapus jadwal kerja via tombol Hapus
    Ketika admin menekan tombol Delete pada baris ke-1
    Dan admin menekan tombol Hapus pada modal konfirmasi
    Maka notifikasi toast muncul dengan pesan "Berhasil menghapus data schedule"
    Dan modal dialog konfirmasi hapus ditutup

  @DEL-007
  Skenario: Verifikasi baris benar-benar hilang dari tabel
    Ketika admin mencatat nama karyawan pada baris ke-1
    Dan admin menghapus data jadwal pada baris ke-1
    Maka jadwal penugasan karyawan tersebut tidak lagi terlihat di tabel

  @DEL-008
  Skenario: Pembaruan total count paginasi setelah hapus
    Ketika admin menghapus satu baris data jadwal
    Maka label paginasi baris otomatis berkurang 1 data

  @DEL-009
  Skenario: Hapus seluruh data hingga tabel kosong
    Ketika admin menghapus seluruh data jadwal pada tabel
    Maka tabel jadwal menampilkan pesan "Tidak Ada Data"

  @DEL-010
  Skenario: Hapus baris data yang ada di halaman kedua
    Ketika admin berpindah ke halaman kedua tabel
    Dan admin menghapus baris ke-1 pada halaman kedua
    Maka baris tersebut terhapus dan tabel merefresh halaman

  @DEL-011
  Skenario: Verifikasi teks pesan toast sukses hapus
    Ketika admin menghapus satu baris data jadwal
    Maka toast notifikasi memuat teks "Berhasil menghapus data schedule"

  @DEL-012
  Skenario: Hapus jadwal saat hasil filter aktif
    Ketika admin memfilter unit "Technology"
    Dan admin menghapus salah satu baris jadwal terfilter
    Maka baris terhapus dan daftar terfilter tetap dalam konteks unit Technology

  @DEL-013
  Skenario: Penutupan toast notifikasi hapus
    Ketika admin menghapus satu baris data jadwal
    Dan admin menekan tombol silang pada toast snackbar
    Maka toast langsung ditutup

  @DEL-014
  Skenario: Pencegahan double-click tombol Hapus
    Ketika admin menekan tombol Hapus pada modal konfirmasi
    Maka tombol Hapus dinonaktifkan sementara untuk mencegah request ganda

  @DEL-015
  Skenario: Pembatalan hapus berulang kali
    Ketika admin membuka modal hapus lalu membatalkannya berulang kali
    Maka seluruh data tabel tetap utuh tanpa terjadi kebocoran memori modal
