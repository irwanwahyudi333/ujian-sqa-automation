# language: id
@ui @register-v2 @v2-absensi-configuration
Fitur: H. Validasi Pengaturan Absensi Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin mengatur parameter absensi karyawan meliputi lokasi, shift, jadwal, kewajiban selfie, dan kuota cuti
  Agar aturan presensi diterapkan secara akurat sesuai kebijakan perusahaan

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @ABSEN-001
  Skenario: ABSEN-001 Membuka dropdown Lokasi Kerja
    Ketika pengguna memilih lokasi kerja "Kantor Pusat Jakarta Selatan"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-002
  Skenario: ABSEN-002 Memilih Lokasi Kerja kantor cabang
    Ketika pengguna memilih lokasi kerja "Kantor Cabang Bandung"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-003
  Skenario: ABSEN-003 Verifikasi default Tipe Shift adalah NON_SHIFT
    Maka nilai default tipe shift adalah "NON_SHIFT"

  @ABSEN-004
  Skenario: ABSEN-004 Mengganti Tipe Shift menjadi SHIFT
    Ketika pengguna memilih tipe shift "SHIFT"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-005
  Skenario: ABSEN-005 Mengganti kembali Tipe Shift ke NON_SHIFT
    Ketika pengguna memilih tipe shift "SHIFT"
    Dan pengguna memilih kembali opsi non shift
    Maka tipe shift kembali menjadi "NON_SHIFT"

  @ABSEN-006
  Skenario: ABSEN-006 Membuka dropdown Jadwal Kerja
    Ketika pengguna memilih jadwal kerja "Reguler Office"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-007
  Skenario: ABSEN-007 Memilih Jadwal Kerja Reguler Office
    Ketika pengguna memilih jadwal kerja "Reguler Flexi"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-008
  Skenario: ABSEN-008 Verifikasi dropdown Selfie menyediakan opsi selfie dan no selfie
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-009
  Skenario: ABSEN-009 Memilih opsi Wajib Selfie true
    Ketika pengguna memilih opsi selfie "true"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-010
  Skenario: ABSEN-010 Memilih opsi Tidak Wajib Selfie false
    Ketika pengguna memilih opsi selfie "false"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-011
  Skenario: ABSEN-011 Submit tanpa memilih opsi Selfie ditolak atau diproses
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-012
  Skenario: ABSEN-012 Verifikasi nilai default Jumlah Cuti adalah angka 0
    Maka nilai default jumlah cuti adalah "0"

  @ABSEN-013
  Skenario: ABSEN-013 Masukkan Jumlah Cuti angka wajar 12 hari
    Ketika pengguna mengisi jumlah cuti 12
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-014
  Skenario: ABSEN-014 Masukkan Jumlah Cuti nilai minimum 0 hari
    Ketika pengguna mengisi jumlah cuti 0
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-015
  Skenario: ABSEN-015 Masukkan Jumlah Cuti nilai maksimum 2 digit 99 hari
    Ketika pengguna mengisi jumlah cuti 99
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-016
  Skenario: ABSEN-016 Masukkan Jumlah Cuti angka negatif ditolak
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.cuti@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna mengisi jumlah cuti -5
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Jumlah Cuti tidak boleh negatif"

  @ABSEN-017
  Skenario: ABSEN-017 Masukkan Jumlah Cuti lebih dari 2 digit 100 hari ditolak
    Ketika pengguna mengunggah foto karyawan "src/test/resources/testdata/images/register/selfie-valid.jpg"
    Dan pengguna mengisi NIK v2 "D6240005"
    Dan pengguna mengisi nama karyawan v2 "Pascal SQA"
    Dan pengguna mengisi email v2 "pascal.cuti2@dikahadir.com"
    Dan pengguna mengisi password v2 "P@ssw0rdPascal2026!"
    Dan pengguna mengisi jumlah cuti 100
    Dan pengguna menekan tombol submit registrasi v2
    Maka muncul notifikasi snackbar "Jumlah Cuti maksimal 99 hari"

  @ABSEN-018
  Skenario: ABSEN-018 Masukkan karakter non numerik pada field cuti
    Ketika pengguna mengisi jumlah cuti 0
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-019
  Skenario: ABSEN-019 Masukkan angka desimal pada field cuti
    Ketika pengguna mengisi jumlah cuti 1
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-020
  Skenario: ABSEN-020 Mengosongkan field Jumlah Cuti mengonversi ke default 0
    Ketika pengguna mengosongkan input cuti
    Maka nilai default jumlah cuti adalah "0"

  @ABSEN-021
  Skenario: ABSEN-021 Kombinasi Tipe Shift SHIFT dengan Jadwal Shift
    Ketika pengguna memilih tipe shift "SHIFT"
    Dan pengguna memilih jadwal kerja "Shift Pagi"
    Maka nilai pada field form tetap utuh tidak terhapus

  @ABSEN-022
  Skenario: ABSEN-022 Kombinasi Tipe Shift NON_SHIFT dengan Jadwal Reguler
    Ketika pengguna memilih tipe shift "NON_SHIFT"
    Dan pengguna memilih jadwal kerja "Reguler Office"
    Maka nilai pada field form tetap utuh tidak terhapus
