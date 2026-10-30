# language: id
@api @management @job-level @api-job-level
Fitur: Pengujian Backend REST API Modul Job Level
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint REST API job level memvalidasi data jenjang level dan jam istirahat
  Agar parameter jam kerja dan tracking karyawan akurat

  @JLVL-API-001
  Skenario: Mengambil daftar job level dengan token valid
    Ketika client mengirim request GET ke endpoint "/api/job-levels" dengan token valid
    Maka response API job level mengembalikan status 200 OK
    Dan response API memuat daftar data job level

  @JLVL-API-002
  Skenario: Menambahkan job level baru dengan parameter valid
    Ketika client mengirim request POST ke endpoint "/api/job-levels" dengan level "Senior Lead", break start "12:00", break end "13:00"
    Maka response API job level mengembalikan status 200 atau 201
    Dan response API memuat job level yang baru dibuat

  @JLVL-API-003
  Skenario: Validasi penambahan job level dengan jam istirahat tidak logis
    Ketika client mengirim request POST ke endpoint "/api/job-levels" dengan break start "14:00" lebih besar dari break end "12:00"
    Maka response API job level mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan rentang jam istirahat tidak valid

  @JLVL-API-004
  Skenario: Menghapus job level dengan ID valid
    Ketika client mengirim request DELETE ke endpoint "/api/job-levels/{id}" dengan ID valid
    Maka response API job level mengembalikan status 200 OK

