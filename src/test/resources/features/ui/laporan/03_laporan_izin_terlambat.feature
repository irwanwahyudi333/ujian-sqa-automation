# language: id
@ui @laporan @izin-terlambat @lean
Fitur: Pengujian Fitur Laporan Izin Terlambat (Keterlambatan Absensi) — HADIR
  Sebagai Administrator atau Tim HR/Supervisor
  Saya ingin memantau dan memvalidasi rekaman absensi terlambat, selisih waktu, alasan keterlambatan, dan status approval
  Agar kedisiplinan jam kerja shift karyawan terpantau dan terkelola secara adil

  Latar Belakang:
    Dengan admin membuka halaman laporan izin terlambat

  @UI-LT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Izin Terlambat
    Maka tabel data laporan izin terlambat terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter izin terlambat terlihat

  @DTR-LT-001
  Skenario: Penyaringan rekaman keterlambatan berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai izin terlambat "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada izin terlambat
    Maka tabel izin terlambat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin terlambat menampilkan 3 baris data

  @DTR-LT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik (Start Date lebih besar dari End Date)
    Ketika admin memasukkan tanggal mulai izin terlambat "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada izin terlambat
    Maka sistem menampilkan notifikasi snackbar error izin terlambat "Start date tidak boleh melebihi end date"

  @SRC-LT-001
  Skenario: Pencarian rekaman keterlambatan berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian izin terlambat "Irwan"
    Dan admin menekan tombol Search pada izin terlambat
    Maka tabel izin terlambat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin terlambat menampilkan 1 baris data

  @SRC-LT-002
  Skenario: Pembersihan kriteria filter dan pencarian melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian izin terlambat "Ahmad"
    Dan admin menekan tombol Search pada izin terlambat
    Maka tabel izin terlambat menampilkan rekaman untuk karyawan "Ahmad Subagyo"
    Ketika admin menekan tombol Reset pada izin terlambat
    Maka seluruh data izin terlambat kembali ditampilkan pada tabel

  @FLT-LT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status approval
    Ketika admin membuka modal filter izin terlambat
    Maka modal dialog filter izin terlambat terbuka pada layar
    Ketika admin memilih filter departemen izin terlambat "Quality Assurance"
    Dan admin memilih filter status izin terlambat "Approved"
    Dan admin menekan tombol Terapkan pada modal filter izin terlambat
    Maka tabel izin terlambat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin terlambat menampilkan 3 baris data

  @DTL-LT-001
  Skenario: Peninjauan modal dialog detail keterlambatan karyawan
    Ketika admin mengklik tombol detail izin terlambat pada baris ke-1
    Maka modal dialog detail izin terlambat terbuka pada layar
    Dan modal menampilkan judul detail keterlambatan untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail izin terlambat
    Maka tabel data laporan izin terlambat terlihat pada halaman

  @EXP-LT-001
  Skenario: Pengunduhan berkas rekapitulasi laporan izin terlambat
    Ketika admin menekan tombol Export izin terlambat
    Maka sistem menampilkan notifikasi snackbar sukses izin terlambat "Berhasil mengekspor Laporan Izin Terlambat ke Excel (.xlsx)"

  @PAG-LT-001
  Skenario: Pengaturan jumlah baris per halaman dan navigasi paginasi
    Ketika admin mengubah jumlah baris per halaman izin terlambat menjadi "5"
    Dan admin menekan tombol halaman berikutnya pada izin terlambat
    Maka informasi paginasi izin terlambat menampilkan "Menampilkan 6-10 dari 15 data"

