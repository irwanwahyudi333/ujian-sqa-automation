# language: id
@ui @leader @lean
Fitur: Portal Leader - Presensi, Log Kehadiran, dan Koreksi Jam Regu
  Sebagai Team Leader (isLeader: true)
  Saya ingin memantau dan menyetujui kehadiran staf regu
  Agar pencatatan absensi tim akurat dan terverifikasi

  Latar Belakang:
    Dengan leader membuka halaman portal leader "/leader/all"

  @tc-ldr-all-01 @smoke
  Skenario: Verifikasi dashboard rekapitulasi kehadiran tim regu
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Dan kartu statistik presensi regu terlihat pada dashboard leader

  @tc-ldr-all-02
  Skenario: Filter pencarian staf pada dashboard leader berdasarkan nama
    Ketika leader mencari data dengan kata kunci "Budi" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Budi"
    Ketika leader menekan tombol reset pada portal leader
    Maka jumlah baris data tabel leader lebih besar dari nol

  @tc-ldr-kh-01
  Skenario: Verifikasi log harian presensi dan swafoto anggota tim
    Dengan leader membuka halaman portal leader "/leader/kehadiran"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Budi" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Budi"

  @tc-ldr-kr-01
  Skenario: Verifikasi antrean pengajuan koreksi jam absensi tim
    Dengan leader membuka halaman portal leader "/leader/koreksi"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader menyaring data berdasarkan status "PENDING" pada portal leader
    Maka tabel leader menampilkan baris dengan data "PENDING"

  @tc-ldr-kr-02
  Skenario: Leader menyetujui permohonan koreksi jam absensi staf
    Dengan leader membuka halaman portal leader "/leader/koreksi"
    Ketika leader membuka modal persetujuan pada baris pertama dengan tindakan "approve"
    Maka modal persetujuan leader ditampilkan
    Ketika leader mengonfirmasi tindakan persetujuan modal
    Maka tabel data pada halaman portal leader terlihat

  @tc-ldr-kr-03
  Skenario: Leader menolak permohonan koreksi jam absensi staf dengan alasan
    Dengan leader membuka halaman portal leader "/leader/koreksi"
    Ketika leader membuka modal persetujuan pada baris pertama dengan tindakan "reject"
    Maka modal persetujuan leader ditampilkan
    Dan leader mengisi alasan penolakan pada portal leader "Bukti jam masuk tidak valid"
    Ketika leader mengonfirmasi tindakan persetujuan modal
    Maka tabel data pada halaman portal leader terlihat
