# language: id
@ui @management @job-level @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Job Level (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman job level beroperasi dengan andal
  Agar penjenjangan karir dan aturan waktu istirahat karyawan dapat dikelola dengan tepat

  Latar Belakang:
    Dengan admin membuka halaman job level

  @JLVL-UI-001
  Skenario: Membuka halaman job level dan verifikasi elemen antarmuka
    Maka halaman job level berhasil dimuat
    Dan judul halaman job level menampilkan "Job Level"
    Dan form pencarian job level terlihat
    Dan tombol Tambahkan level terlihat
    Dan tabel data job level menampilkan 8 kolom header:
      | NO               |
      | LEVEL / JABATAN  |
      | DESCRIPTION      |
      | BREAK START      |
      | BREAK END        |
      | AKTIF TRAKING    |
      | STATUS           |
      | AKSI             |

  @JLVL-UI-002
  Skenario: Melakukan pencarian level jabatan berdasarkan nama
    Ketika admin mencari job level dengan kata kunci "Staff"
    Maka tabel job level hanya menampilkan baris yang mengandung nama "Staff"
    Ketika admin menekan tombol reset pencarian job level
    Maka seluruh data job level ditampilkan kembali di tabel

  @JLVL-UI-003
  Skenario: Membuka modal tambah job level dan membatalkannya
    Ketika admin mengklik tombol Tambahkan level
    Maka modal tambah level ditampilkan dengan judul "Tambah Job Level"
    Dan field input waktu istirahat break start dan break end terlihat pada modal tambah
    Ketika admin menekan tombol Batal pada modal tambah level
    Maka modal tambah level tertutup

  @JLVL-UI-004
  Skenario: Membuka modal konfirmasi hapus job level dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama job level
    Maka modal konfirmasi hapus level ditampilkan
    Dan pesan konfirmasi hapus level memuat teks "Apakah anda ingin menghapus Job Level"
    Ketika admin menekan tombol Tidak pada modal hapus level
    Maka modal konfirmasi hapus level tertutup tanpa menghapus data

