# language: id
@ui @leader @leader-lembur @lembur @lean
Fitur: Portal Leader - Pengelolaan dan Persetujuan Lembur Tim
  Sebagai Team Leader (isLeader: true)
  Saya ingin meninjau dan menyetujui klaim lembur staf regu
  Agar jam kerja lembur dan kompensasi tervalidasi

  @tc-ldr-lmb-01
  Skenario: Verifikasi rekapitulasi riwayat lembur anggota regu
    Dengan leader membuka halaman portal leader "/leader/lembur/semua"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Budi" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Budi"

  @tc-ldr-lmb-02
  Skenario: Verifikasi antrean klaim lembur pending anggota regu
    Dengan leader membuka halaman portal leader "/leader/lembur/pending"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Ahmad" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Ahmad"

  @tc-ldr-lmb-03
  Skenario: Leader menolak pengajuan lembur anggota regu dengan alasan penolakan
    Dengan leader membuka halaman portal leader "/leader/lembur/pending"
    Ketika leader membuka modal persetujuan pada baris pertama dengan tindakan "reject"
    Maka modal persetujuan leader ditampilkan
    Dan leader mengisi alasan penolakan pada portal leader "Lembur tidak sesuai dengan instruksi kerja"
    Ketika leader mengonfirmasi tindakan persetujuan modal
    Maka tabel data pada halaman portal leader terlihat
