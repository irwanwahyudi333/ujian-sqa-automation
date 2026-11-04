# language: id
@ui @apps-absent @absent-sick @sakit @lean
Fitur: Pengujian UI Lean Pengajuan Izin Sakit Client Mobile
  Sebagai Karyawan atau Administrator
  Saya ingin mengakses modul izin sakit (/apps/absent/sick)
  Untuk memeriksa riwayat sakit dan membuka formulir modal pengajuan izin sakit disertai unggahan berkas

  Latar Belakang:
    Dengan Pengguna membuka halaman "/apps/absent/sick"

  @smoke
  Skenario: Verifikasi render halaman izin sakit dan daftar riwayat
    Maka Halaman absent sick harus tampil dengan benar
    Dan Tombol ajukan sakit harus ditampilkan
    Dan Thumbnail bukti surat sakit harus ditampilkan

  Skenario: Verifikasi pembukaan modal formulir pengajuan izin sakit
    Ketika Pengguna menekan tombol ajukan sakit
    Maka Modal drawer form sakit harus ditampilkan
    Dan Seluruh field formulir sakit dan upload berkas harus tersedia

  @BUG-SCK-001 @DEFECT-SCK-001 @data-binding @live
  Skenario: Verifikasi integritas data tanggal sakit pada kartu riwayat request sakit
    Maka Kartu riwayat sakit harus menampilkan tanggal sakit yang dipilih bukan tanggal dan jam pengajuan saat ini

  @BUG-SCK-002 @DEFECT-SCK-002 @negative @live
  Skenario: Validasi kewajiban lampiran surat keterangan dokter saat pengajuan izin sakit
    Ketika Pengguna membuka modal form sakit dan menekan tombol ajukan tanpa mengunggah berkas surat dokter
    Maka Sistem harus menolak pengajuan dengan validasi kewajiban lampiran surat dokter

  @BUG-SCK-003 @DEFECT-SCK-003 @boundary @policy @live
  Skenario: Validasi konfirmasi peringatan kebijakan pengajuan tanggal lampau backdate
    Ketika Pengguna mengajukan izin sakit dengan tanggal lampau atau backdate
    Maka Sistem harus menampilkan konfirmasi peringatan backdate sebelum permohonan disetujui untuk disimpan


