# language: id
@ui @management-user @lean
Fitur: Pengujian Fitur Management User — HADIR
  Sebagai Administrator
  Saya ingin mengelola data direktori pengguna, status akun, dan sinkronisasi approval
  Agar operasional kepegawaian dapat dipantau dan dikelola dengan akurat

  Latar Belakang:
    Dengan admin membuka halaman management user

  @UI-USR-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Management User
    Maka tabel data karyawan terlihat pada halaman
    Dan elemen bar pencarian dan tombol filter user terlihat

  @SRC-USR-001
  Skenario: Pencarian karyawan berdasarkan Nama Lengkap
    Ketika admin memilih tipe pencarian user "name"
    Dan admin memasukkan kata kunci pencarian user "Irwan"
    Dan admin menekan tombol Search pada form user
    Maka tabel menampilkan karyawan bernama "Irwan Wahyudi"
    Dan tabel hanya menampilkan 1 baris data karyawan

  @SRC-USR-005
  Skenario: Pencarian karyawan berdasarkan NIK dan reset pencarian
    Ketika admin memilih tipe pencarian user "nik"
    Dan admin memasukkan kata kunci pencarian user "D6240001"
    Dan admin menekan tombol Search pada form user
    Maka tabel menampilkan karyawan bernama "Ahmad Subagyo"
    Ketika admin menekan tombol Reset pencarian user
    Maka seluruh data karyawan kembali ditampilkan pada tabel

  @FLT-USR-001
  Skenario: Penyaringan karyawan berdasarkan status aktif via modal filter
    Ketika admin membuka modal filter user
    Maka modal dialog filter user terbuka pada layar
    Ketika admin memilih filter status user "0"
    Dan admin menekan tombol Terapkan pada modal filter user
    Maka tabel menampilkan karyawan bernama "Dewi Sartika"
    Dan tabel hanya menampilkan 1 baris data karyawan

  @SWT-ACT-001
  Skenario: Pengubahan status keaktifan akun karyawan via switch Active
    Ketika admin mengubah status switch Active pada user dengan id "4"
    Maka sistem menampilkan notifikasi snackbar "User dinonaktifkan"
    Ketika admin mengubah status switch Active pada user dengan id "4"
    Maka sistem menampilkan notifikasi snackbar "User diaktifkan"

  @SWT-PRJ-001
  Skenario: Pengubahan status keterlibatan proyek via switch User Project
    Ketika admin mengubah status switch User Project pada user dengan id "1"
    Maka sistem menampilkan notifikasi snackbar "Project diaktifkan"

  @SYN-APP-001
  Skenario: Sinkronisasi alur persetujuan karyawan
    Ketika admin menekan tombol Sync Approval pada user dengan id "4"
    Maka sistem menampilkan notifikasi snackbar "User Sync Approval Berhasil"

  @LEV-USR-001
  Skenario: Peninjauan rincian sisa cuti karyawan
    Ketika admin menekan tombol Lihat Sisa Cuti pada user dengan id "4"
    Maka modal dialog sisa cuti terbuka menampilkan judul "Lihat Sisa Cuti Irwan Wahyudi"
    Dan admin menutup dialog sisa cuti

  @DEL-USR-001
  Skenario: Alur pembatalan dan konfirmasi penghapusan karyawan
    Ketika admin menekan tombol Hapus pada user dengan id "3"
    Maka modal konfirmasi hapus terbuka bertuliskan "Apakah anda ingin hapus user Dewi Sartika?"
    Ketika admin membatalkan penghapusan user
    Dan admin menekan tombol Hapus pada user dengan id "3"
    Dan admin mengonfirmasi penghapusan user
    Maka sistem menampilkan notifikasi snackbar "Berhasil Delete User"
    Dan user dengan id "3" tidak lagi berada pada tabel

  @EXP-USR-001
  Skenario: Ekspor data karyawan ke format berkas Excel
    Ketika admin menekan tombol Export data user
    Maka sistem menampilkan notifikasi snackbar "Mengunduh export-users.xlsx..."
