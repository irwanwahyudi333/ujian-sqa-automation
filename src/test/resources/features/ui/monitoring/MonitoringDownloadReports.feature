# language: id
@ui @monitoring @lean
Fitur: Modul Monitoring - Unduh Rekapitulasi Laporan Audit
  Sebagai Auditor Monitoring
  Saya ingin mengekstrak berkas laporan kehadiran, cuti, lembur, dan shifting
  Agar rekapitulasi data dapat dianalisis untuk kebutuhan audit independen

  @tc-mon-rep-att-01
  Skenario: Generator laporan audit rekapitulasi kehadiran
    Dengan auditor membuka halaman monitoring "/monitoring/download-reports/kehadiran"
    Maka formulir generator laporan monitoring terlihat pada halaman
    Ketika auditor memilih departemen monitoring "Teknologi Informasi (IT)"
    Dan auditor menekan tombol unduh laporan monitoring
    Maka notifikasi proses unduhan laporan audit berhasil ditampilkan

  @tc-mon-rep-cuti-01
  Skenario: Generator laporan audit rekapitulasi cuti
    Dengan auditor membuka halaman monitoring "/monitoring/download-reports/cuti"
    Maka formulir generator laporan monitoring terlihat pada halaman
    Ketika auditor memilih departemen monitoring "Semua Departemen"
    Dan auditor menekan tombol unduh laporan monitoring
    Maka notifikasi proses unduhan laporan audit berhasil ditampilkan

  @tc-mon-rep-ot-01
  Skenario: Generator laporan audit rekapitulasi lembur
    Dengan auditor membuka halaman monitoring "/monitoring/download-reports/lembur"
    Maka formulir generator laporan monitoring terlihat pada halaman
    Ketika auditor memilih departemen monitoring "Operasional & Lapangan"
    Dan auditor menekan tombol unduh laporan monitoring
    Maka notifikasi proses unduhan laporan audit berhasil ditampilkan

  @tc-mon-rep-shift-01
  Skenario: Generator laporan audit roster shifting
    Dengan auditor membuka halaman monitoring "/monitoring/download-reports/shifting"
    Maka formulir generator laporan monitoring terlihat pada halaman
    Ketika auditor memilih departemen monitoring "Semua Departemen"
    Dan auditor menekan tombol unduh laporan monitoring
    Maka notifikasi proses unduhan laporan audit berhasil ditampilkan

