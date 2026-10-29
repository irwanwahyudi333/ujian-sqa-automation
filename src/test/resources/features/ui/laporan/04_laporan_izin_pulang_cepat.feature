# language: id
@ui @laporan @izin-pulang-cepat @lean
Fitur: Pengujian Fitur Laporan Izin Pulang Cepat — HADIR
  Sebagai Administrator atau Tim HR Hadir
  Saya ingin mengelola dan memantau rekaman karyawan yang pulang sebelum akhir jam kerja
  Agar presensi dan kedisiplinan kerja tetap akuntabel dan transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan izin pulang cepat

  @UI-PC-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Izin Pulang Cepat
    Maka tabel data laporan izin pulang cepat terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter izin pulang cepat terlihat

  @DTR-PC-001
  Skenario: Penyaringan rekaman pulang cepat berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai izin pulang cepat "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada izin pulang cepat
    Maka tabel izin pulang cepat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin pulang cepat menampilkan 3 baris data

  @DTR-PC-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada izin pulang cepat
    Ketika admin memasukkan tanggal mulai izin pulang cepat "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada izin pulang cepat
    Maka sistem menampilkan notifikasi snackbar error izin pulang cepat "Start date tidak boleh melebihi end date"

  @SRC-PC-001
  Skenario: Pencarian rekaman pulang cepat berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian izin pulang cepat "Irwan"
    Dan admin menekan tombol Search pada izin pulang cepat
    Maka tabel izin pulang cepat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin pulang cepat menampilkan 1 baris data

  @SRC-PC-002
  Skenario: Pembersihan kriteria filter dan pencarian pulang cepat melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian izin pulang cepat "Irwan"
    Dan admin menekan tombol Search pada izin pulang cepat
    Maka tabel izin pulang cepat menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada izin pulang cepat
    Maka seluruh data izin pulang cepat kembali ditampilkan pada tabel

  @FLT-PC-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status approval
    Ketika admin membuka modal filter izin pulang cepat
    Maka modal dialog filter izin pulang cepat terbuka pada layar
    Ketika admin memilih filter departemen izin pulang cepat "Quality Assurance"
    Dan admin memilih filter status izin pulang cepat "Approved"
    Dan admin menekan tombol Terapkan pada modal filter izin pulang cepat
    Maka tabel izin pulang cepat menampilkan 2 baris data

  @DTL-PC-001
  Skenario: Peninjauan modal dialog detail pulang cepat karyawan
    Ketika admin mengklik tombol detail izin pulang cepat pada baris ke-1
    Maka modal dialog detail izin pulang cepat terbuka pada layar
    Dan modal menampilkan judul detail pulang cepat untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail izin pulang cepat
    Maka tabel data laporan izin pulang cepat terlihat pada halaman

  @PAG-PC-001
  Skenario: Kontrol jumlah baris per halaman pada tabel izin pulang cepat
    Ketika admin mengatur jumlah baris izin pulang cepat menjadi "5" per halaman
    Maka tabel izin pulang cepat menampilkan 5 baris data

  @EXP-PC-001
  Skenario: Ekspor berkas laporan izin pulang cepat ke format Excel
    Ketika admin menekan tombol Export izin pulang cepat
    Maka sistem menampilkan notifikasi snackbar sukses izin pulang cepat "Berhasil mengekspor Laporan Izin Pulang Cepat ke Excel (.xlsx)"
