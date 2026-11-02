# language: id
@ui @monitoring @lean
Fitur: Modul Monitoring - Pengawasan Cuti dan Lembur Karyawan
  Sebagai Auditor Monitoring
  Saya ingin meninjau pengajuan cuti dan kompensasi lembur karyawan
  Agar pemanfaatan hak cuti dan jam lembur sesuai dengan kepatuhan kebijakan perusahaan

  @tc-mon-cuti-01
  Skenario: Verifikasi audit riwayat pengajuan cuti karyawan
    Dengan auditor membuka halaman monitoring "/monitoring/cuti"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Cuti Tahunan" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Cuti Tahunan"
    Ketika auditor menekan tombol reset pada monitoring
    Maka jumlah baris data tabel monitoring lebih besar dari nol

  @tc-mon-ot-01 @monitoring-lembur @lembur
  Skenario: Verifikasi audit rekapitulasi jam kerja lembur karyawan
    Dengan auditor membuka halaman monitoring "/monitoring/lembur"
    Maka tabel data pemantauan terlihat pada halaman
    Dan kontrol pencarian dan filter tanggal monitoring terlihat
    Dan halaman tidak menampilkan tombol aksi manipulasi atau approval data
    Ketika auditor mencari data dengan kata kunci "Ahmad" pada monitoring
    Maka tabel monitoring menampilkan baris dengan teks "Ahmad"

