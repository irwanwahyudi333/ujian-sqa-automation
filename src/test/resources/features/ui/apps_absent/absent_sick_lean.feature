# language: id
@ui @apps-absent @absent-sick @lean
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

