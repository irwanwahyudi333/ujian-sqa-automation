# language: id
@ui @client @lean
Fitur: Portal Client - Laporan Presensi, Koreksi, dan Sakit
  Sebagai perwakilan Client (Upliner V2 / Upliner V3)
  Saya ingin memantau dan memvalidasi presensi staf binaan
  Agar data kehadiran staf tercatat akurat dan transparan

  Latar Belakang:
    Dengan pengguna masuk sebagai peran "Upliner V2"

  @tc-cl-all-01 @smoke
  Skenario: Verifikasi visual dan pencarian pada halaman Laporan Semua Client
    Dengan pengguna membuka halaman portal client "/client/all"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Budi" pada portal client
    Maka tabel menampilkan staf dengan nama atau NIK "Budi"
    Ketika pengguna menekan tombol reset pada portal client
    Maka jumlah baris data tabel lebih besar dari nol

  @tc-cl-kh-01
  Skenario: Verifikasi halaman Laporan Kehadiran Karyawan Binaan
    Dengan pengguna membuka halaman portal client "/client/kehadiran"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Siti" pada portal client
    Maka tabel menampilkan staf dengan nama atau NIK "Siti"

  @tc-cl-kr-01
  Skenario: Verifikasi halaman Laporan Koreksi Absensi
    Dengan pengguna membuka halaman portal client "/client/koreksi"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Ahmad" pada portal client
    Maka tabel menampilkan staf dengan nama atau NIK "Ahmad"

  @tc-cl-sk-01
  Skenario: Verifikasi halaman Laporan Sakit Karyawan Binaan
    Dengan pengguna membuka halaman portal client "/client/sick"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Clarissa" pada portal client
    Maka tabel menampilkan staf dengan nama atau NIK "Clarissa"

