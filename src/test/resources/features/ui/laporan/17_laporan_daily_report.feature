# language: id
@ui @laporan @daily-report @lean
Fitur: Pengujian Fitur Laporan Daily Report — HADIR
  Sebagai Administrator atau Manajer Operasional Hadir
  Saya ingin meninjau ringkasan konsolidasi presensi harian karyawan dan status kehadirannya
  Agar audit absensi harian dapat dimonitor secara real-time dan akuntabel

  Latar Belakang:
    Dengan admin membuka halaman laporan daily report

  @UI-DLR-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Daily Report
    Maka tabel data laporan daily report terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter daily report terlihat

  @DTR-DLR-001
  Skenario: Penyaringan rekaman presensi harian berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai daily report "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada daily report
    Maka tabel daily report menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel daily report menampilkan 10 baris data

  @DTR-DLR-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada daily report
    Ketika admin memasukkan tanggal mulai daily report "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada daily report
    Maka sistem menampilkan notifikasi snackbar error daily report "Start date tidak boleh melebihi end date"

  @SRC-DLR-001
  Skenario: Pencarian rekaman presensi harian berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian daily report "Irwan"
    Dan admin menekan tombol Search pada daily report
    Maka tabel daily report menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel daily report menampilkan 1 baris data

  @SRC-DLR-002
  Skenario: Pembersihan kriteria filter dan pencarian daily report melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian daily report "Irwan"
    Dan admin menekan tombol Search pada daily report
    Maka tabel daily report menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada daily report
    Maka seluruh data daily report kembali ditampilkan pada tabel

  @FLT-DLR-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status kehadiran
    Ketika admin membuka modal filter daily report
    Maka modal dialog filter daily report terbuka pada layar
    Ketika admin memilih filter departemen daily report "Quality Assurance"
    Dan admin memilih filter status kehadiran daily report "Hadir"
    Dan admin menekan tombol Terapkan pada modal filter daily report
    Maka tabel daily report menampilkan 2 baris data

  @DTL-DLR-001
  Skenario: Peninjauan modal dialog detail absensi harian karyawan
    Ketika admin mengklik tombol detail daily report pada baris ke-1
    Maka modal dialog detail daily report terbuka pada layar
    Dan modal menampilkan judul detail daily report untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail daily report
    Maka tabel data laporan daily report terlihat pada halaman

  @PAG-DLR-001
  Skenario: Kontrol jumlah baris per halaman pada tabel daily report
    Ketika admin mengatur jumlah baris daily report menjadi "5" per halaman
    Maka tabel daily report menampilkan 5 baris data

  @EXP-DLR-001
  Skenario: Ekspor berkas laporan daily report ke format Excel
    Ketika admin menekan tombol Export daily report
    Maka sistem menampilkan notifikasi snackbar sukses daily report "Berhasil mengekspor Laporan Daily Report ke Excel (.xlsx)"

