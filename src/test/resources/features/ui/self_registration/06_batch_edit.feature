# language: id
@ui @self-registration @module-batch-edit
Fitur: Pengujian Modal Ubah Data User Massal Batch Edit
  Sebagai administrator manajemen
  Saya ingin memperbarui informasi penugasan kerja dan absensi secara massal
  Agar proses on-boarding pengguna mobile berjalan serentak dan konsisten

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @BATCH-001
  Skenario: Buka modal Ubah Data User dichecklist
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Maka modal ubah data user dichecklist terbuka

  @BATCH-002
  Skenario: Periksa bagian Informasi Kerja
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Maka field informasi kerja dan pengaturan absensi terlihat pada modal

  @BATCH-003
  Skenario: Periksa bagian Pengaturan Absensi
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Maka field informasi kerja dan pengaturan absensi terlihat pada modal

  @BATCH-004
  Skenario: Isi seluruh field Informasi Kerja dengan data valid
    Ketika admin mencentang baris user pertama
    Dan admin mengisi formulir batch edit divisi "Technology" posisi "QA Lead" jabatan "Lead" shift "NON_SHIFT" selfie "1" cuti "12"
    Maka sistem menampilkan notifikasi "Berhasil edit user"

  @BATCH-005
  Skenario: Pilih Tipe Shift: Shift
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin memilih tipe shift "SHIFT"
    Maka opsi tipe shift "SHIFT" terpilih

  @BATCH-006
  Skenario: Pilih Tipe Shift: Non Shift
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin memilih tipe shift "NON_SHIFT"
    Maka opsi tipe shift "NON_SHIFT" terpilih

  @BATCH-007
  Skenario: Pilih Kewajiban Selfie: Selfie
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin memilih kewajiban selfie "1"
    Maka opsi kewajiban selfie "1" terpilih

  @BATCH-008
  Skenario: Pilih Kewajiban Selfie: No Selfie
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin memilih kewajiban selfie "0"
    Maka opsi kewajiban selfie "0" terpilih

  @BATCH-009
  Skenario: Masukkan Jumlah Cuti nilai numerik valid
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin mengisi jumlah cuti "12"
    Maka input jumlah cuti terisi "12"

  @BATCH-010
  Skenario: Masukkan Jumlah Cuti angka negatif
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin mengisi jumlah cuti negatif "-5"
    Maka sistem menolak atau memvalidasi batas nilai cuti

  @BATCH-011
  Skenario: Klik tombol Submit pada modal Batch Edit
    Ketika admin mencentang baris user pertama
    Dan admin mengisi formulir batch edit divisi "Technology" posisi "QA Lead" jabatan "Lead" shift "NON_SHIFT" selfie "1" cuti "12"
    Maka sistem menampilkan notifikasi "Berhasil edit user"

  @BATCH-012
  Skenario: Klik tombol Batal pada modal Batch Edit
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin menekan tombol Batal pada modal batch edit
    Maka modal batch edit tertutup

  @BATCH-013
  Skenario: Klik tombol close silang di pojok kanan modal
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin menekan tombol close pada modal batch edit
    Maka modal batch edit tertutup

  @BATCH-014
  Skenario: Submit form saat network terputus
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Dan admin mengirim batch edit saat jaringan terputus
    Maka sistem menampilkan notifikasi "Berhasil edit user"
