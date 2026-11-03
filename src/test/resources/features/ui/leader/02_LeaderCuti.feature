# language: id
@ui @leader @leader-cuti @cuti @lean
Fitur: Portal Leader - Pengelolaan dan Persetujuan Cuti Tim
  Sebagai Team Leader (isLeader: true)
  Saya ingin meninjau dan menyetujui pengajuan cuti anggota regu
  Agar pengaturan hak libur staf terkelola dengan baik

  @tc-ldr-cut-01
  Skenario: Verifikasi riwayat seluruh cuti anggota tim
    Dengan leader membuka halaman portal leader "/leader/cuti/semua"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Budi" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Budi"

  @tc-ldr-cut-02
  Skenario: Verifikasi antrean pengajuan cuti staf regu yang berstatus pending
    Dengan leader membuka halaman portal leader "/leader/cuti/pending"
    Maka tabel data pada halaman portal leader terlihat
    Dan kontrol pencarian dan filter tanggal leader terlihat
    Ketika leader mencari data dengan kata kunci "Siti" pada portal leader
    Maka tabel leader menampilkan baris dengan data "Siti"

  @tc-ldr-cut-03
  Skenario: Leader menyetujui pengajuan cuti anggota tim dari modal approval
    Dengan leader membuka halaman portal leader "/leader/cuti/pending"
    Ketika leader membuka modal persetujuan pada baris pertama dengan tindakan "approve"
    Maka modal persetujuan leader ditampilkan
    Ketika leader mengonfirmasi tindakan persetujuan modal
    Maka tabel data pada halaman portal leader terlihat

