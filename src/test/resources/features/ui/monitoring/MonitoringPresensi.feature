# language: id
@ui @monitoring @lean
Fitur: Modul Monitoring - Presensi, Kehadiran, Koreksi, dan Sakit
  Sebagai Auditor Monitoring
  Saya ingin memantau seluruh catatan presensi, koreksi, dan izin sakit karyawan
  Agar kepatuhan kehadiran staf terpantau secara transparan tanpa manipulasi data

  @tc-mon-all-01 @smoke
  Skenario: Verifikasi dashboard monitoring semua presensi dan pencarian
    Dengan auditor membuka halaman monitoring "/monitoring/semua"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan kartu statistik metrik kehadiran terlihat pada dashboard monitoring
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Budi" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Budi"
    Ketika auditor menekan tombol reset pada monitoring
    Maka jumlah baris data tabel monitoring lebih besar dari nol

  @tc-mon-kh-01 @absensi @lean
  Skenario: Verifikasi pemantauan log presensi harian karyawan
    Dengan auditor membuka halaman monitoring "/monitoring/kehadiran"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Siti" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Siti"

  @tc-mon-kr-01 @absensi @lean
  Skenario: Verifikasi audit riwayat pengajuan koreksi jam kerja
    Dengan auditor membuka halaman monitoring "/monitoring/koreksi"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Ahmad" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Ahmad"

  @tc-mon-sk-01 @sakit
  Skenario: Verifikasi audit rekapitulasi izin sakit karyawan
    Dengan auditor membuka halaman monitoring "/monitoring/sakit"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Clarissa" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Clarissa"

