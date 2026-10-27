# language: id
@ui @register-v2 @v2-work-information
Fitur: G. Validasi Informasi Pekerjaan (Work Information) Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memilih struktur divisi, unit, posisi, jabatan, hierarki atasan, dan tipe kontrak
  Agar penempatan organisasi karyawan terpetakan secara akurat dan konsisten

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @WORK-001
  Skenario: WORK-001 Membuka dropdown pilihan Divisi
    Ketika pengguna memilih divisi "IT & Digital Solution"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-002
  Skenario: WORK-002 Memfilter dan memilih Divisi spesifik
    Ketika pengguna memilih divisi "Human Capital Management"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-003
  Skenario: WORK-003 Membuka dropdown pilihan Unit kerja
    Ketika pengguna memilih unit "Quality Assurance (QA) Automation"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-004
  Skenario: WORK-004 Memilih Unit kerja spesifik
    Ketika pengguna memilih unit "Software Engineering - Frontend"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-005
  Skenario: WORK-005 Membuka dropdown pilihan Posisi Kerja
    Ketika pengguna memilih posisi kerja "SQA Automation Engineer"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-006
  Skenario: WORK-006 Memilih Posisi Kerja spesifik
    Ketika pengguna memilih posisi kerja "Frontend Engineer (React/Next.js)"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-007
  Skenario: WORK-007 Membuka dropdown Jabatan
    Ketika pengguna memilih jabatan "Junior Specialist"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-008
  Skenario: WORK-008 Memilih Jabatan level staff
    Ketika pengguna memilih jabatan "Junior Specialist"
    Maka field atasan menjadi aktif

  @WORK-009
  Skenario: WORK-009 Verifikasi field Atasan berstatus terkunci sebelum Jabatan dipilih
    Maka field atasan berstatus terkunci dengan placeholder "Pilih Jabatan Dulu"

  @WORK-010
  Skenario: WORK-010 Mencoba mengklik field Atasan saat masih terkunci
    Maka field atasan berstatus terkunci dengan placeholder "Pilih Jabatan Dulu"

  @WORK-011
  Skenario: WORK-011 Verifikasi field Atasan otomatis aktif setelah Jabatan dipilih
    Ketika pengguna memilih jabatan "Junior Specialist"
    Maka field atasan menjadi aktif

  @WORK-012
  Skenario: WORK-012 Filter atasan hierarkis sesuai level jabatan terpilih
    Ketika pengguna memilih jabatan "Junior Specialist"
    Dan pengguna memilih atasan "Irwan Wahyudi"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-013
  Skenario: WORK-013 Format label pilihan atasan memuat NIK dan Nama
    Ketika pengguna memilih jabatan "Junior Specialist"
    Dan pengguna memilih atasan "Irwan Wahyudi"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-014
  Skenario: WORK-014 Memilih Atasan langsung dari daftar
    Ketika pengguna memilih jabatan "Junior Specialist"
    Dan pengguna memilih atasan "Irwan Wahyudi"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-015
  Skenario: WORK-015 Membuka dropdown Atasan Client V2
    Ketika pengguna memilih atasan client v2 "Dika Pratama"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-016
  Skenario: WORK-016 Format label opsi Atasan Client V2 memuat Nama dan Departemen
    Ketika pengguna memilih atasan client v2 "Dika Pratama"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-017
  Skenario: WORK-017 Memilih Atasan Client V2 opsional
    Ketika pengguna memilih atasan client v2 "Dika Pratama"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-018
  Skenario: WORK-018 Mengosongkan Atasan Client V2 karena opsional
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-019
  Skenario: WORK-019 Membuka dropdown Atasan Client V3
    Ketika pengguna memilih atasan client v3 "Siti Rahmawati"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-020
  Skenario: WORK-020 Format label opsi Atasan Client V3 memuat Nama dan Departemen
    Ketika pengguna memilih atasan client v3 "Siti Rahmawati"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-021
  Skenario: WORK-021 Memilih Atasan Client V3 opsional
    Ketika pengguna memilih atasan client v3 "Siti Rahmawati"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-022
  Skenario: WORK-022 Mengosongkan Atasan Client V3 karena opsional
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-023
  Skenario: WORK-023 Membuka dropdown Tipe Kontrak
    Ketika pengguna memilih tipe kontrak "PKWT (Kontrak)"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-024
  Skenario: WORK-024 Memilih Tipe Kontrak spesifik
    Ketika pengguna memilih tipe kontrak "PKWTT (Karyawan Tetap)"
    Maka nilai pada field form tetap utuh tidak terhapus

  @WORK-025
  Skenario: WORK-025 Mereset Jabatan setelah Atasan dipilih mengunci kembali Atasan
    Ketika pengguna memilih jabatan "Junior Specialist"
    Dan pengguna memilih atasan "Irwan Wahyudi"
    Dan pengguna mengosongkan kembali pilihan jabatan
    Maka nilai atasan tereset otomatis
