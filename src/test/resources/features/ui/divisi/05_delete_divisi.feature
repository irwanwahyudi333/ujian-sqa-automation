# language: id
@ui @divisi @delete-divisi
Fitur: Penghapusan Data Divisi (Delete Divisi)
  Sebagai admin manajemen HADIR
  Saya ingin menghapus divisi yang sudah tidak digunakan lagi
  Agar daftar divisi tetap terstruktur dan teratur

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @DEL-001
  Skenario: Membuka modal konfirmasi Hapus Divisi via tombol aksi langsung
    Ketika admin menekan tombol hapus langsung pada baris divisi "Operations"
    Maka modal dialog konfirmasi Hapus Divisi terbuka
    Dan judul modal hapus menampilkan "Hapus Divisi"

  @DEL-002
  Skenario: Memeriksa judul pada modal dialog konfirmasi hapus
    Ketika admin menekan tombol hapus langsung pada baris divisi "Operations"
    Maka judul modal hapus menampilkan "Hapus Divisi"

  @DEL-003
  Skenario: Memeriksa pesan konfirmasi memuat nama divisi yang akan dihapus
    Ketika admin menekan tombol hapus langsung pada baris divisi "Operations"
    Maka pesan konfirmasi hapus menampilkan "Apakah anda ingin menghapus Divisi Operations?"

  @DEL-004
  Skenario: Membatalkan penghapusan divisi dengan tombol Batal
    Ketika admin menekan tombol hapus langsung pada baris divisi "Technology"
    Dan admin menekan tombol Batal pada modal hapus
    Maka modal dialog konfirmasi Hapus Divisi tertutup
    Dan divisi bernama "Technology" tetap ada pada tabel divisi

  @DEL-005
  Skenario: Menutup modal hapus divisi dengan tombol silang (X)
    Ketika admin menekan tombol hapus langsung pada baris divisi "Technology"
    Dan admin menekan tombol silang tutup pada modal hapus
    Maka modal dialog konfirmasi Hapus Divisi tertutup tanpa menghapus data

  @DEL-006
  Skenario: Menutup modal hapus divisi dengan tombol Escape pada keyboard
    Ketika admin menekan tombol hapus langsung pada baris divisi "Technology"
    Dan admin menekan tombol Escape pada keyboard
    Maka modal dialog konfirmasi Hapus Divisi tertutup seketika

  @DEL-007
  Skenario: Mengonfirmasi penghapusan divisi dengan tombol Hapus
    Ketika admin menekan tombol hapus langsung pada baris divisi "Customer Support"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Maka modal dialog konfirmasi Hapus Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Delete Divisi"
    Dan baris divisi "Customer Support" tidak lagi muncul di tabel

  @DEL-008
  Skenario: Membuka modal hapus divisi melalui menu dropdown 3-titik
    Ketika admin menekan tombol menu 3-titik pada baris divisi "Product Management"
    Dan admin memilih opsi "Delete" pada dropdown aksi
    Maka modal dialog konfirmasi Hapus Divisi terbuka memuat nama "Product Management"

  @DEL-009
  Skenario: Menghapus divisi saat dalam mode pencarian aktif
    Ketika admin memasukkan kata kunci pencarian divisi "Quality"
    Dan admin menekan tombol Cari pada pencarian divisi
    Dan admin menekan tombol hapus langsung pada baris divisi "Quality Assurance"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Maka notifikasi toast menampilkan pesan "Berhasil Delete Divisi"
    Dan tabel hasil pencarian otomatis memperbarui baris data

  @DEL-010
  Skenario: Menghapus data divisi terakhir pada halaman paginasi kedua
    Ketika admin memilih baris per halaman "5"
    Dan admin menekan tombol halaman selanjutnya pada paginasi divisi
    Dan admin menekan tombol hapus langsung pada baris divisi yang ada di halaman kedua
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Maka baris divisi terhapus dan sistem memperbarui status halaman paginasi secara wajar

  @DEL-011
  Skenario: Memeriksa pengurangan jumlah total data pada bar paginasi setelah hapus
    Ketika admin menekan tombol hapus langsung pada baris divisi "Finance & Accounting"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Maka total data pada teks informasi paginasi berkurang sebanyak 1 data

  @DEL-012
  Skenario: Memeriksa tampilan tabel saat seluruh baris divisi dihapus
    Ketika seluruh data divisi pada tabel dihapus
    Maka tabel divisi menampilkan pesan "Tidak ada data divisi ditemukan"

  @DEL-013
  Skenario: Memeriksa styling tombol konfirmasi Hapus pada modal dialog
    Ketika admin menekan tombol hapus langsung pada baris divisi "Technology"
    Maka tombol konfirmasi Hapus berlatar belakang merah untuk aksi destruktif

  @DEL-014
  Skenario: Memeriksa durasi penutupan otomatis notifikasi toast sukses hapus
    Ketika admin menekan tombol hapus langsung pada baris divisi "Marketing & Communication"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Maka notifikasi toast menampilkan "Berhasil Delete Divisi" dan otomatis tertutup setelah beberapa detik

  @DEL-015
  Skenario: Menutup notifikasi toast hapus secara manual dengan tombol silang
    Ketika admin menekan tombol hapus langsung pada baris divisi "Human Resources"
    Dan admin menekan tombol konfirmasi Hapus pada modal hapus
    Dan admin menekan tombol tutup pada notifikasi toast
    Maka notifikasi toast langsung tertutup seketika
