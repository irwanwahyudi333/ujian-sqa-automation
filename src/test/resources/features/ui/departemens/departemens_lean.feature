# language: id
@ui @management @departemens @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Departemens (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman departemen beroperasi dengan benar
  Agar struktur unit kerja departemen dapat dikelola secara andal

  Latar Belakang:
    Dengan admin membuka halaman departemens

  @DEPT-UI-001
  Skenario: Membuka halaman departemens dan verifikasi elemen antarmuka
    Maka halaman departemens berhasil dimuat
    Dan judul halaman departemens menampilkan "Departemens"
    Dan form pencarian departemens terlihat
    Dan tombol Tambahkan departemen terlihat
    Dan tabel data departemens menampilkan 6 kolom header:
      | NO               |
      | NAMA DEPARTEMEN  |
      | TANGGAL BUAT     |
      | TANGGAL UPDATE   |
      | ATUR VIRTUAL ID  |
      | AKSI             |

  @DEPT-UI-002
  Skenario: Melakukan pencarian departemen berdasarkan nama
    Ketika admin mencari departemen dengan kata kunci "Technology"
    Maka tabel departemens hanya menampilkan baris yang mengandung nama "Technology"
    Ketika admin menekan tombol reset pencarian departemen
    Maka seluruh data departemen ditampilkan kembali di tabel

  @DEPT-UI-003
  Skenario: Membuka modal tambah departemen dan membatalkannya
    Ketika admin mengklik tombol Tambahkan departemen
    Maka modal tambah departemen ditampilkan dengan judul "Tambah Departemens"
    Ketika admin menekan tombol Batal pada modal tambah departemen
    Maka modal tambah departemen tertutup

  @DEPT-UI-004
  Skenario: Membuka modal atur Virtual ID dan membatalkannya
    Ketika admin mengklik tombol Atur Virtual ID pada baris pertama departemen
    Maka modal dialog Atur Virtual ID ditampilkan
    Ketika admin menutup modal Atur Virtual ID
    Maka modal dialog Atur Virtual ID tertutup

  @DEPT-UI-005
  Skenario: Membuka modal konfirmasi hapus departemen dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama departemen
    Maka modal konfirmasi hapus departemen ditampilkan
    Dan pesan konfirmasi hapus departemen memuat teks "Apakah anda ingin menghapus departemen"
    Ketika admin menekan tombol Tidak pada modal hapus departemen
    Maka modal konfirmasi hapus departemen tertutup tanpa menghapus data

