# language: id
@ui @management @client-upliner @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Client Upliner (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman client upliner berjalan dengan benar
  Agar hierarki atasan upliner dan klien perusahaan dapat dikelola secara transparan

  Latar Belakang:
    Dengan admin membuka halaman client upliner

  @CUPL-UI-001
  Skenario: Membuka halaman client upliner dan verifikasi elemen antarmuka
    Maka halaman client upliner berhasil dimuat
    Dan judul halaman client upliner menampilkan "Client Upliner"
    Dan form pencarian client upliner terlihat
    Dan tombol Tambahkan upliner terlihat
    Dan tabel data client upliner menampilkan 8 kolom header:
      | NO               |
      | FOTO             |
      | NAMA KARYAWAN    |
      | NIK              |
      | TIPE             |
      | ROLE             |
      | TANGGAL BUAT     |
      | AKSI             |

  @CUPL-UI-002
  Skenario: Melakukan pencarian client upliner berdasarkan nama
    Ketika admin mencari client upliner dengan kata kunci "Manager"
    Maka tabel client upliner hanya menampilkan baris yang mengandung nama "Manager"
    Ketika admin menekan tombol reset pencarian client upliner
    Maka seluruh data client upliner ditampilkan kembali di tabel

  @CUPL-UI-003
  Skenario: Membuka modal tambah client upliner dan membatalkannya
    Ketika admin mengklik tombol Tambahkan upliner
    Maka modal tambah upliner ditampilkan dengan judul "Tambah Client Upliner"
    Ketika admin menekan tombol Batal pada modal tambah upliner
    Maka modal tambah upliner tertutup

  @CUPL-UI-004
  Skenario: Membuka modal konfirmasi hapus upliner dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama upliner
    Maka modal konfirmasi hapus upliner ditampilkan
    Dan pesan konfirmasi hapus upliner memuat teks "Apakah anda ingin menghapus"
    Ketika admin menekan tombol Tidak pada modal hapus upliner
    Maka modal konfirmasi hapus upliner tertutup tanpa menghapus data

