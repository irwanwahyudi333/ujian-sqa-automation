# language: id
@ui @leader @lean
Fitur: Portal Leader - Generator dan Unduh Laporan Rekapitulasi Tim
  Sebagai Team Leader (isLeader: true)
  Saya ingin men-generate rekapitulasi data kehadiran dan izin regu
  Agar dapat dianalisis dan diekspor dalam format dokumen berkala

  @tc-ldr-gen-01
  Skenario: Verifikasi formulir generator dan download report leader
    Dengan leader membuka halaman portal leader "/leader/generate"
    Maka formulir generator laporan leader terlihat pada halaman

  @tc-ldr-gen-02
  Skenario: Leader men-generate laporan rekapitulasi tim dalam format Excel
    Dengan leader membuka halaman portal leader "/leader/generate"
    Ketika leader menekan tombol generate dan download laporan
    Maka notifikasi sukses pembuatan laporan leader ditampilkan

