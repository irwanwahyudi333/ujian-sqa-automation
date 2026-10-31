# language: id
@ui @client @lean
Fitur: Portal Client - Unduh Rekapitulasi dan Laporan
  Sebagai perwakilan Client (Upliner V2 / Upliner V3)
  Saya ingin mengunduh berkas laporan presensi, cuti, lembur, dan shifting
  Agar data rekapitulasi dapat dianalisis secara offline

  Latar Belakang:
    Dengan pengguna masuk sebagai peran "Upliner V2"

  @tc-cl-rep-att-01
  Skenario: Generator Rekapitulasi Laporan Kehadiran Karyawan
    Dengan pengguna membuka halaman portal client "/client/download-reports/kehadiran"
    Maka formulir generator laporan terlihat pada halaman
    Ketika pengguna memilih departemen "Operasional"
    Dan pengguna menekan tombol unduh laporan
    Maka notifikasi proses unduhan berhasil ditampilkan

  @tc-cl-rep-cuti-01
  Skenario: Generator Rekapitulasi Laporan Cuti
    Dengan pengguna membuka halaman portal client "/client/download-reports/cuti"
    Maka formulir generator laporan terlihat pada halaman
    Ketika pengguna memilih departemen "Operasional"
    Dan pengguna menekan tombol unduh laporan
    Maka notifikasi proses unduhan berhasil ditampilkan

  @tc-cl-rep-sisacuti-01
  Skenario: Generator Rekapitulasi Saldo Sisa Cuti Karyawan
    Dengan pengguna membuka halaman portal client "/client/download-reports/sisa-cuti"
    Maka formulir generator laporan terlihat pada halaman
    Ketika pengguna memilih departemen "Semua Departemen"
    Dan pengguna menekan tombol unduh laporan
    Maka notifikasi proses unduhan berhasil ditampilkan

  @tc-cl-rep-ot-01
  Skenario: Generator Rekapitulasi Laporan Lembur
    Dengan pengguna membuka halaman portal client "/client/download-reports/lembur"
    Maka formulir generator laporan terlihat pada halaman
    Ketika pengguna memilih departemen "Operasional"
    Dan pengguna menekan tombol unduh laporan
    Maka notifikasi proses unduhan berhasil ditampilkan

  @tc-cl-rep-shift-01
  Skenario: Generator Rekapitulasi Roster Shifting
    Dengan pengguna membuka halaman portal client "/client/download-reports/shifting"
    Maka formulir generator laporan terlihat pada halaman
    Ketika pengguna memilih departemen "Operasional"
    Dan pengguna menekan tombol unduh laporan
    Maka notifikasi proses unduhan berhasil ditampilkan

