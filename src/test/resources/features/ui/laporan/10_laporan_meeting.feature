# language: id
@ui @laporan @meeting @lean
Fitur: Pengujian Fitur Laporan Meeting — HADIR
  Sebagai Administrator atau Manajemen Hadir
  Saya ingin memantau agenda rapat kerja, daftar kehadiran peserta rapat, dan ruangan/tautan meeting
  Agar koordinasi pertemuan bisnis dan operasional terdokumentasi dengan baik

  Latar Belakang:
    Dengan admin membuka halaman laporan meeting

  @UI-MTG-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Meeting
    Maka tabel data laporan meeting terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter meeting terlihat

  @DTR-MTG-001
  Skenario: Penyaringan rekaman meeting berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai meeting "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada meeting
    Maka tabel meeting menampilkan rekaman topik "Sprint Review"
    Dan tabel meeting menampilkan 3 baris data

  @DTR-MTG-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada meeting
    Ketika admin memasukkan tanggal mulai meeting "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada meeting
    Maka sistem menampilkan notifikasi snackbar error meeting "Start date tidak boleh melebihi end date"

  @SRC-MTG-001
  Skenario: Pencarian rekaman meeting berdasarkan topik atau host
    Ketika admin memasukkan kata kunci pencarian meeting "Irwan"
    Dan admin menekan tombol Search pada meeting
    Maka tabel meeting menampilkan rekaman topik "Sprint Review"

  @SRC-MTG-002
  Skenario: Pembersihan kriteria filter dan pencarian meeting melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian meeting "Irwan"
    Dan admin menekan tombol Search pada meeting
    Maka tabel meeting menampilkan rekaman topik "Sprint Review"
    Ketika admin menekan tombol Reset pada meeting
    Maka seluruh data meeting kembali ditampilkan pada tabel

  @FLT-MTG-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen
    Ketika admin membuka modal filter meeting
    Maka modal dialog filter meeting terbuka pada layar
    Ketika admin memilih filter departemen meeting "Quality Assurance"
    Dan admin menekan tombol Terapkan pada modal filter meeting
    Maka tabel meeting menampilkan 3 baris data

  @DTL-MTG-001
  Skenario: Peninjauan modal dialog detail meeting
    Ketika admin mengklik tombol detail meeting pada baris ke-1
    Maka modal dialog detail meeting terbuka pada layar
    Dan modal menampilkan judul detail meeting untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail meeting
    Maka tabel data laporan meeting terlihat pada halaman

  @PAG-MTG-001
  Skenario: Kontrol jumlah baris per halaman pada tabel meeting
    Ketika admin mengatur jumlah baris meeting menjadi "5" per halaman
    Maka tabel meeting menampilkan 5 baris data

  @EXP-MTG-001
  Skenario: Ekspor berkas laporan meeting ke format Excel
    Ketika admin menekan tombol Export meeting
    Maka sistem menampilkan notifikasi snackbar sukses meeting "Berhasil mengekspor Laporan Meeting ke Excel (.xlsx)"

