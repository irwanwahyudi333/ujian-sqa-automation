# language: id
@ui @laporan @generate-report-type @lean
Fitur: Pengujian Fitur Generate Report Type — HADIR
  Sebagai Administrator atau Manajemen Eksekutif Hadir
  Saya ingin membuat laporan terstandardisasi dan memantau riwayat generate laporan berkala
  Agar arsip rekapitulasi operasional dan eksekutif siap diunduh secara berkala

  Latar Belakang:
    Dengan admin membuka halaman generate report type

  @UI-GRT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Generate Report Type
    Maka tabel data riwayat generate report terlihat pada halaman
    Dan formulir generate laporan eksekutif dan kontrol filter terlihat

  @DTR-GRT-001
  Skenario: Penyaringan rekaman riwayat laporan berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai riwayat report "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada riwayat report
    Maka tabel riwayat report menampilkan data dengan kode "RPT-2026-1001"
    Dan tabel riwayat report menampilkan 3 baris data

  @DTR-GRT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada riwayat report
    Ketika admin memasukkan tanggal mulai riwayat report "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada riwayat report
    Maka sistem menampilkan notifikasi snackbar error riwayat report "Start date tidak boleh melebihi end date"

  @SRC-GRT-001
  Skenario: Pencarian riwayat laporan berdasarkan ID Report
    Ketika admin memasukkan kata kunci pencarian riwayat report "RPT-2026-1001"
    Dan admin menekan tombol Search pada riwayat report
    Maka tabel riwayat report menampilkan data dengan kode "RPT-2026-1001"
    Dan tabel riwayat report menampilkan 1 baris data

  @SRC-GRT-002
  Skenario: Pembersihan kriteria filter dan pencarian riwayat laporan melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian riwayat report "RPT-2026-1001"
    Dan admin menekan tombol Search pada riwayat report
    Maka tabel riwayat report menampilkan data dengan kode "RPT-2026-1001"
    Ketika admin menekan tombol Reset pada riwayat report
    Maka seluruh data riwayat report kembali ditampilkan pada tabel

  @FLT-GRT-001
  Skenario: Penyaringan riwayat laporan via modal filter departemen dan tipe laporan
    Ketika admin membuka modal filter riwayat report
    Maka modal dialog filter riwayat report terbuka pada layar
    Ketika admin memilih filter departemen riwayat report "Quality Assurance"
    Dan admin memilih filter tipe laporan riwayat report "Kehadiran"
    Dan admin menekan tombol Terapkan pada modal filter riwayat report
    Maka tabel riwayat report menampilkan 2 baris data

  @GEN-GRT-001
  Skenario: Eksekusi generate laporan eksekutif baru melalui formulir
    Ketika admin memilih tipe laporan generate "rekap-lembur"
    Dan admin memilih format berkas generate "xlsx"
    Dan admin menekan tombol Generate Sekarang
    Maka sistem menampilkan notifikasi snackbar sukses generate "Laporan berhasil digenerate dan siap diunduh"

  @DWN-GRT-001
  Skenario: Pengunduhan berkas laporan pada baris pertama tabel riwayat
    Ketika admin menekan tombol Download berkas pada baris pertama
    Maka sistem menampilkan notifikasi snackbar sukses generate "Mengunduh RPT-2026-1001.xlsx..."

  @PAG-GRT-001
  Skenario: Kontrol jumlah baris per halaman pada tabel riwayat report
    Ketika admin mengatur jumlah baris riwayat report menjadi "5" per halaman
    Maka tabel riwayat report menampilkan 5 baris data

