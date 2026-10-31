# language: id
@ui @client @lean
Fitur: Portal Client - Pengelolaan Jadwal Kerja Shifting
  Sebagai perwakilan Client (Upliner V2 / Upliner V3)
  Saya ingin meninjau jadwal roster shifting dan permohonan tukar shift staf binaan
  Agar kesinambungan operasional kerja tetap terjaga

  Latar Belakang:
    Dengan pengguna masuk sebagai peran "Upliner V2"

  @tc-cl-sh-all-01
  Skenario: Verifikasi halaman Roster Semua Jadwal Shifting
    Dengan pengguna membuka halaman portal client "/client/shifting/semua"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Shift" pada portal client
    Maka jumlah baris data tabel lebih besar dari nol

  @tc-cl-sh-pending-01
  Skenario: Verifikasi halaman Permohonan Penyesuaian Shifting Pending
    Dengan pengguna membuka halaman portal client "/client/shifting/pending"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat

