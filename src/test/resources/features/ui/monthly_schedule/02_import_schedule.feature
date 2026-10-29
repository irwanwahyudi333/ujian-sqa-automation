# language: id
@ui @monthly-schedule @import-schedule
Fitur: Import Jadwal Bulanan Karyawan Melalui File Excel
  Sebagai admin manajemen HADIR
  Saya ingin dapat mengimpor berkas Excel jadwal kerja bulanan
  Agar jadwal kerja seluruh karyawan dapat diperbarui secara massal dan akurat

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @IMP-001
  Skenario: Upload file Excel valid (.xlsx)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Maka notifikasi toast muncul dengan pesan "Berhasil mengimpor data jadwal"
    Dan data jadwal baru tampil pada tabel

  @IMP-002
  Skenario: Upload file Excel format lama (.xls)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/schedule_november_2026.xls"
    Dan admin menekan tombol Import
    Maka notifikasi toast muncul dengan pesan "Berhasil mengimpor data jadwal"

  @IMP-003
  Skenario: Submit form import tanpa memilih file
    Ketika admin menekan tombol Import tanpa memilih file
    Maka sistem menampilkan pesan validasi "Silakan pilih file Excel terlebih dahulu"

  @IMP-004
  Skenario: Upload file format PDF (.pdf)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/document.pdf"
    Dan admin menekan tombol Import
    Maka sistem menolak berkas dengan pesan "Format file tidak didukung"

  @IMP-005
  Skenario: Upload file gambar (.png / .jpg)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/photo.png"
    Dan admin menekan tombol Import
    Maka sistem menolak berkas dengan pesan "Format file tidak didukung"

  @IMP-006
  Skenario: Upload file teks (.txt)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/notes.txt"
    Dan admin menekan tombol Import
    Maka sistem menolak berkas dengan pesan "Format file tidak didukung"

  @IMP-007
  Skenario: Upload file dokumen (.docx)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/document.docx"
    Dan admin menekan tombol Import
    Maka sistem menolak berkas dengan pesan "Format file tidak didukung"

  @IMP-008
  Skenario: Klik tombol Download Template
    Ketika admin menekan tombol Download Template
    Maka browser mengunduh berkas template jadwal "template_jadwal_bulanan.xlsx"

  @IMP-009
  Skenario: Reset input file setelah import sukses
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Maka field input file di-reset menjadi kosong

  @IMP-010
  Skenario: Pembaruan data tabel setelah import
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Maka tabel jadwal otomatis merefresh dan memuat baris jadwal terbaru

  @IMP-011
  Skenario: Upload file Excel berukuran besar
    Ketika admin memilih file import "src/test/resources/testdata/schedule/large_schedule_10mb.xlsx"
    Dan admin menekan tombol Import
    Maka sistem memproses atau memvalidasi batas kapasitas berkas import

  @IMP-012
  Skenario: Upload file berekstensi ganda (schedule.xlsx.exe)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/schedule.xlsx.exe"
    Dan admin menekan tombol Import
    Maka sistem menolak berkas dengan pesan "Format file tidak didukung"

  @IMP-013
  Skenario: Penutupan toast notifikasi import
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Dan admin menekan tombol tutup pada snackbar notifikasi
    Maka toast notifikasi segera menghilang dari layar

  @IMP-014
  Skenario: Durasi tayang toast import
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Maka notifikasi toast tampil di layar dan berdurasi hingga 15 detik

  @IMP-015
  Skenario: State tombol Import saat proses berjalan
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Import
    Maka teks tombol Import berubah menjadi "Mengimpor..." atau disabled sementara
