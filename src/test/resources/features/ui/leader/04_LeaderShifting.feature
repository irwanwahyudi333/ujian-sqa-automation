# language: id
@ui @leader @lean
Fitur: Portal Leader - Pengelolaan Jadwal Kerja dan Pertukaran Shift Tim
  Sebagai Team Leader (isLeader: true)
  Saya ingin meninjau rotasi shift dan menyetujui pertukaran pola shift kerja
  Agar kelancaran operasional kerja shift terjaga

  @tc-ldr-shf-01
  Skenario: Verifikasi roster jadwal rotasi shift kerja tim
    Dengan leader membuka halaman portal leader "/leader/shifting/semua"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Budi" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Budi"

  @tc-ldr-shf-02
  Skenario: Verifikasi antrean permohonan pertukaran shift kerja anggota tim
    Dengan leader membuka halaman portal leader "/leader/shifting/pending"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Shift" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Shift"

  @tc-ldr-shf-03
  Skenario: Leader menyetujui pertukaran jadwal shift kerja tim
    Dengan leader membuka halaman portal leader "/leader/shifting/pending"
    Ketika leader membuka modal persetujuan pada baris pertama dengan tindakan "approve"
    Maka modal persetujuan leader ditampilkan
    Ketika leader mengonfirmasi tindakan persetujuan modal
    Maka tabel data pada halaman portal leader terlihat

