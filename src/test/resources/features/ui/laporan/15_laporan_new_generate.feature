# language: id
@ui @laporan @new-generate @lean
Fitur: Pengujian Fitur Laporan New Generate — HADIR
  Sebagai Administrator atau Tim Analis Data Hadir
  Saya ingin membuat konfigurasi template laporan dinamis dan memantau riwayat berkas template yang telah dibuat
  Agar penarikan data atribut kustom dapat dilakukan secara fleksibel dan terdokumentasi

  Latar Belakang:
    Dengan admin membuka halaman new generate

  @UI-NGR-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman New Generate
    Maka tabel data riwayat template terlihat pada halaman
    Dan formulir dynamic builder template dan kontrol filter terlihat

  @DTR-NGR-001
  Skenario: Penyaringan rekaman riwayat template berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai riwayat template "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada riwayat template
    Maka tabel riwayat template menampilkan data dengan kode "TMPL-201"
    Dan tabel riwayat template menampilkan 3 baris data

  @DTR-NGR-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada riwayat template
    Ketika admin memasukkan tanggal mulai riwayat template "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada riwayat template
    Maka sistem menampilkan notifikasi snackbar error riwayat template "Start date tidak boleh melebihi end date"

  @SRC-NGR-001
  Skenario: Pencarian riwayat template berdasarkan ID Template
    Ketika admin memasukkan kata kunci pencarian riwayat template "TMPL-201"
    Dan admin menekan tombol Search pada riwayat template
    Maka tabel riwayat template menampilkan data dengan kode "TMPL-201"
    Dan tabel riwayat template menampilkan 1 baris data

  @SRC-NGR-002
  Skenario: Pembersihan kriteria filter dan pencarian riwayat template melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian riwayat template "TMPL-201"
    Dan admin menekan tombol Search pada riwayat template
    Maka tabel riwayat template menampilkan data dengan kode "TMPL-201"
    Ketika admin menekan tombol Reset pada riwayat template
    Maka seluruh data riwayat template kembali ditampilkan pada tabel

  @FLT-NGR-001
  Skenario: Penyaringan riwayat template via modal filter departemen dan format
    Ketika admin membuka modal filter riwayat template
    Maka modal dialog filter riwayat template terbuka pada layar
    Ketika admin memilih filter departemen riwayat template "Quality Assurance"
    Dan admin memilih filter format riwayat template "XLSX"
    Dan admin menekan tombol Terapkan pada modal filter riwayat template
    Maka tabel riwayat template menampilkan 3 baris data

  @GEN-NGR-001
  Skenario: Pembuatan template laporan kustom baru melalui formulir builder
    Ketika admin memasukkan nama template laporan kustom "Template QA Eksklusif"
    Dan admin menekan tombol Simpan & Generate Template
    Maka sistem menampilkan notifikasi snackbar sukses template "Template laporan kustom berhasil dibuat dan digenerate"

  @DWN-NGR-001
  Skenario: Pengunduhan berkas template pada baris pertama tabel riwayat
    Ketika admin menekan tombol Download template pada baris pertama
    Maka sistem menampilkan notifikasi snackbar info template "Mengunduh template TMPL-201..."

  @PAG-NGR-001
  Skenario: Kontrol jumlah baris per halaman pada tabel riwayat template
    Ketika admin mengatur jumlah baris riwayat template menjadi "5" per halaman
    Maka tabel riwayat template menampilkan 5 baris data

