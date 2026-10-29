# language: id
@ui @laporan @izin @lean
Fitur: Pengujian Fitur Laporan Izin (Perizinan Karyawan) — HADIR
  Sebagai Administrator atau Tim HR
  Saya ingin memantau dan memvalidasi rekaman permohonan izin, tipe izin, lampiran surat izin, dan status persetujuan
  Agar pencatatan ketidakhadiran karyawan tertib, sah, dan terverifikasi

  Latar Belakang:
    Dengan admin membuka halaman laporan izin

  @UI-IZN-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Izin
    Maka tabel data laporan izin terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter izin terlihat

  @DTR-IZN-001
  Skenario: Penyaringan rekaman perizinan berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai izin "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada izin
    Maka tabel izin menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin menampilkan 3 baris data

  @DTR-IZN-003 @negative
  Skenario: Penolakan input logika tanggal terbalik (Start Date lebih besar dari End Date)
    Ketika admin memasukkan tanggal mulai izin "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada izin
    Maka sistem menampilkan notifikasi snackbar error izin "Start date tidak boleh melebihi end date"

  @SRC-IZN-001
  Skenario: Pencarian rekaman izin berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian izin "Irwan"
    Dan admin menekan tombol Search pada izin
    Maka tabel izin menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin menampilkan 1 baris data

  @SRC-IZN-004
  Skenario: Pembersihan kriteria filter dan pencarian melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian izin "Ahmad"
    Dan admin menekan tombol Search pada izin
    Maka tabel izin menampilkan rekaman untuk karyawan "Ahmad Subagyo"
    Ketika admin menekan tombol Reset pada izin
    Maka seluruh data izin kembali ditampilkan pada tabel

  @FLT-IZN-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan tipe izin
    Ketika admin membuka modal filter izin
    Maka modal dialog filter izin terbuka pada layar
    Ketika admin memilih filter departemen izin "Quality Assurance"
    Dan admin memilih filter tipe izin "Izin Pribadi"
    Dan admin menekan tombol Terapkan pada modal filter izin
    Maka tabel izin menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin menampilkan 2 baris data

  @DTL-IZN-001
  Skenario: Peninjauan modal dialog detail permohonan izin karyawan
    Ketika admin mengklik tombol detail izin pada baris ke-1
    Maka modal dialog detail izin terbuka pada layar
    Dan modal menampilkan judul detail izin untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail izin
    Maka tabel data laporan izin terlihat pada halaman

  @EXP-IZN-001
  Skenario: Pengunduhan berkas rekapitulasi laporan izin
    Ketika admin menekan tombol Export izin
    Maka sistem menampilkan notifikasi snackbar sukses izin "Berhasil mengekspor Laporan Izin ke Excel (.xlsx)"

  @PAG-IZN-001
  Skenario: Pengaturan jumlah baris per halaman dan navigasi paginasi
    Ketika admin mengubah jumlah baris per halaman izin menjadi "5"
    Dan admin menekan tombol halaman berikutnya pada izin
    Maka informasi paginasi izin menampilkan "Menampilkan 6-10 dari 15 data"

