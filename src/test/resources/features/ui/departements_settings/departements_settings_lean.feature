# language: id
@ui @management @departements-settings @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Departements Settings (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan konfigurasi pengaturan departemen dan toggle switch status berfungsi dengan baik
  Agar status operasional departemen dapat dikendalikan secara tepat

  Latar Belakang:
    Dengan admin membuka halaman departements settings

  @DEPTS-UI-001
  Skenario: Membuka halaman departements settings dan verifikasi elemen antarmuka
    Maka halaman departements settings berhasil dimuat
    Dan judul halaman departements settings menampilkan "Departements Settings"
    Dan form pencarian departements settings terlihat
    Dan tombol Tambah Departemens Setting terlihat
    Dan tabel data departements settings menampilkan 6 kolom header:
      | NO               |
      | NAMA DEPARTEMENS |
      | TANGGAL BUAT     |
      | TANGGAL UPDATE   |
      | STATUS           |
      | AKSI             |

  @DEPTS-UI-002
  Skenario: Mengubah status aktif departemens setting via toggle switch
    Ketika admin mengklik toggle switch status pada baris pertama departemens setting
    Maka status switch berubah dan notifikasi feedback departemens setting ditampilkan

  @DEPTS-UI-003
  Skenario: Melakukan pencarian konfigurasi departemen berdasarkan nama
    Ketika admin mencari setting departemen dengan kata kunci "IT Development"
    Maka tabel departemens settings hanya menampilkan baris yang mengandung nama "IT Development"
    Ketika admin menekan tombol reset pencarian departemens settings
    Maka seluruh data departemens settings ditampilkan kembali di tabel

  @DEPTS-UI-004
  Skenario: Membuka modal tambah departemens setting dan membatalkannya
    Ketika admin mengklik tombol Tambah Departemens Setting
    Maka modal tambah setting ditampilkan dengan judul "Tambah Departements Settings"
    Ketika admin menekan tombol Batal pada modal tambah setting
    Maka modal tambah setting tertutup

  @DEPTS-UI-005
  Skenario: Membuka modal konfirmasi hapus setting dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama departemens setting
    Maka modal konfirmasi hapus setting ditampilkan
    Dan pesan konfirmasi hapus setting memuat teks "Apakah anda ingin menghapus departements"
    Ketika admin menekan tombol Tidak pada modal hapus setting
    Maka modal konfirmasi hapus setting tertutup tanpa menghapus data

