# language: id
@ui @laporan @lean
Fitur: Pengujian Fitur Laporan Semua (Kehadiran Karyawan) — HADIR
  Sebagai Administrator atau Tim HR
  Saya ingin memantau dan memvalidasi rekaman kehadiran, foto selfie, koordinat GPS, serta mengekspor data absensi
  Agar pencatatan kehadiran karyawan akurat dan bebas dari manipulasi

  Latar Belakang:
    Dengan admin membuka halaman laporan semua

  @UI-LAP-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Semua
    Maka tabel data laporan kehadiran terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter laporan terlihat

  @DTR-LAP-001
  Skenario: Penyaringan rekaman absensi berdasarkan rentang tanggal
    Ketika admin memasukkan tanggal mulai laporan "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada laporan
    Maka tabel menampilkan aktivitas kehadiran untuk karyawan "Irwan Wahyudi"
    Dan tabel laporan menampilkan 4 baris data

  @DTR-LAP-004 @negative
  Skenario: Penolakan input logika tanggal terbalik (Start Date lebih besar dari End Date)
    Ketika admin memasukkan tanggal mulai laporan "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada laporan
    Maka sistem menampilkan notifikasi snackbar error laporan "Start date tidak boleh melebihi end date"

  @SRC-LAP-001
  Skenario: Pencarian rekaman absensi berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian laporan "Irwan"
    Dan admin menekan tombol Search pada laporan
    Maka tabel menampilkan aktivitas kehadiran untuk karyawan "Irwan Wahyudi"
    Dan tabel laporan menampilkan 1 baris data

  @SRC-LAP-010
  Skenario: Pembersihan kriteria filter dan pencarian melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian laporan "Ahmad"
    Dan admin menekan tombol Search pada laporan
    Maka tabel menampilkan aktivitas kehadiran untuk karyawan "Ahmad Subagyo"
    Ketika admin menekan tombol Reset pada laporan
    Maka seluruh data aktivitas kehadiran kembali ditampilkan pada tabel

  @FLT-LAP-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status
    Ketika admin membuka modal filter laporan
    Maka modal dialog filter laporan terbuka pada layar
    Ketika admin memilih filter departemen laporan "Quality Assurance"
    Dan admin memilih filter status laporan "Masuk"
    Dan admin menekan tombol Terapkan pada modal filter laporan
    Maka tabel menampilkan aktivitas kehadiran untuk karyawan "Irwan Wahyudi"
    Dan tabel laporan menampilkan 2 baris data

  @TBL-LAP-001
  Skenario: Peninjauan preview foto selfie absensi karyawan
    Ketika admin mengklik thumbnail foto selfie pada baris ke-1
    Maka modal dialog preview foto selfie terbuka
    Ketika admin menutup modal preview foto selfie
    Maka tabel data laporan kehadiran terlihat pada halaman

  @TBL-LAP-004
  Skenario: Peninjauan modal titik koordinat lokasi presisi
    Ketika admin mengklik tautan lokasi masuk pada baris ke-1
    Maka modal dialog peta koordinat presisi terbuka
    Ketika admin menutup modal peta koordinat
    Maka tabel data laporan kehadiran terlihat pada halaman

  @EXP-LAP-001
  Skenario: Pengunduhan berkas rekapitulasi laporan kehadiran
    Ketika admin menekan tombol Export laporan
    Maka sistem menampilkan notifikasi snackbar sukses laporan "Laporan kehadiran berhasil diunduh"

  @PAG-LAP-001
  Skenario: Pengaturan jumlah baris per halaman dan navigasi paginasi
    Ketika admin mengubah jumlah baris per halaman laporan menjadi "5"
    Dan admin menekan tombol halaman berikutnya pada laporan
    Maka informasi paginasi laporan menampilkan "Menampilkan 6-10 dari"
