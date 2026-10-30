# language: id
@ui @management @position @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Position (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman posisi/jabatan berjalan dengan benar
  Agar jabatan posisi karyawan dapat ditetapkan dan dikelola secara terstruktur

  Latar Belakang:
    Dengan admin membuka halaman position

  @POS-UI-001
  Skenario: Membuka halaman position dan verifikasi elemen antarmuka
    Maka halaman position berhasil dimuat
    Dan judul halaman position menampilkan "Position"
    Dan form pencarian position terlihat
    Dan tombol Tambahkan position terlihat
    Dan tabel data position menampilkan 6 kolom header:
      | NO               |
      | NAMA POSITION    |
      | NAMA DEPARTEMENT |
      | TANGGAL BUAT     |
      | TANGGAL UPDATE   |
      | AKSI             |

  @POS-UI-002
  Skenario: Melakukan pencarian posisi kerja berdasarkan nama
    Ketika admin mencari posisi dengan kata kunci "Engineer"
    Maka tabel position hanya menampilkan baris yang mengandung nama "Engineer"
    Ketika admin menekan tombol reset pencarian position
    Maka seluruh data position ditampilkan kembali di tabel

  @POS-UI-003
  Skenario: Membuka modal tambah position dan membatalkannya
    Ketika admin mengklik tombol Tambahkan position
    Maka modal tambah position ditampilkan dengan judul "Tambah Position"
    Dan dropdown departemen pada modal tambah position terlihat
    Ketika admin menekan tombol Batal pada modal tambah position
    Maka modal tambah position tertutup

  @POS-UI-004
  Skenario: Membuka modal konfirmasi hapus position dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama position
    Maka modal konfirmasi hapus position ditampilkan
    Dan pesan konfirmasi hapus position memuat teks "Apakah anda ingin menghapus Position"
    Ketika admin menekan tombol Tidak pada modal hapus position
    Maka modal konfirmasi hapus position tertutup tanpa menghapus data

