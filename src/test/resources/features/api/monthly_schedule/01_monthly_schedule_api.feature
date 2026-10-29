# language: id
@api @monthly-schedule @api-monthly-schedule
Fitur: Pengujian Backend REST API Modul Monthly Schedule
  Sebagai sistem integrasi dan aplikasi HADIR
  Saya ingin memastikan endpoint REST API Monthly Schedule beroperasi dengan andal, aman, dan memvalidasi payload
  Agar operasi CRUD data jadwal bulanan berjalan konsisten dan terlindungi dari anomali data

  @API-001
  Skenario: Fetch daftar jadwal bulanan dengan token valid
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" dengan token valid
    Maka response API schedule mengembalikan status 200 OK
    Dan response API memuat array daftar monthly schedule dan total data

  @API-002
  Skenario: Fetch jadwal bulanan tanpa header token otorisasi
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" tanpa token otorisasi
    Maka response API schedule mengembalikan status 401 Unauthorized

  @API-003
  Skenario: Fetch jadwal bulanan dengan parameter query paginasi
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" dengan query "page=1&limit=5"
    Maka response API schedule mengembalikan status 200 OK
    Dan jumlah item jadwal dalam response tepat berjumlah 5 data

  @API-004
  Skenario: Fetch jadwal bulanan dengan filter query nama karyawan
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" dengan filter nama "Ahmad"
    Maka response API schedule mengembalikan status 200 OK
    Dan seluruh data jadwal dalam response memuat nama "Ahmad"

  @API-005
  Skenario: Fetch jadwal bulanan dengan filter query unit kerja
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" dengan filter unit "1"
    Maka response API schedule mengembalikan status 200 OK
    Dan seluruh data jadwal dalam response memiliki job_departement_id "1"

  @API-006
  Skenario: Upload file Excel jadwal bulanan via endpoint
    Ketika client mengirim request multipart POST ke "/upload-monthly-schedule" dengan file valid
    Maka response API mengembalikan status 201 Created atau 200 OK
    Dan response API memuat pesan konfirmasi sukses import

  @API-007
  Skenario: Upload file jadwal tanpa melampirkan file pada body
    Ketika client mengirim request multipart POST ke "/upload-monthly-schedule" tanpa berkas
    Maka response API schedule mengembalikan status 400 Bad Request

  @API-008
  Skenario: Update shift dan tanggal jadwal terdaftar
    Ketika client mengirim request PATCH ke endpoint "/api/monthly-schedule/{id}" dengan shift_id "2" dan tanggal "2026-11-20"
    Maka response API schedule mengembalikan status 200 OK
    Dan data jadwal yang terupdate memiliki shift_id "2" dan tanggal "2026-11-20"

  @API-009
  Skenario: Update jadwal dengan ID non-eksisten
    Ketika client mengirim request PATCH ke endpoint "/api/monthly-schedule/999999" dengan payload valid
    Maka response API schedule mengembalikan status 404 Not Found

  @API-010
  Skenario: Update jadwal dengan payload shifting_id kosong
    Ketika client mengirim request PATCH ke endpoint "/api/monthly-schedule/{id}" tanpa shifting_id
    Maka response API schedule mengembalikan status 400 Bad Request

  @API-011
  Skenario: Update jadwal dengan payload format tanggal salah
    Ketika client mengirim request PATCH ke endpoint "/api/monthly-schedule/{id}" dengan tanggal "20-11-2026"
    Maka response API schedule mengembalikan status 400 Bad Request

  @API-012
  Skenario: Hapus penugasan jadwal dengan ID valid
    Ketika client mengirim request DELETE ke endpoint "/api/monthly-schedule/{id}" dengan ID valid
    Maka response API schedule mengembalikan status 200 OK
    Dan data penugasan jadwal tersebut tidak lagi ditemukan

  @API-013
  Skenario: Hapus kembali penugasan jadwal yang sudah dihapus
    Ketika client mengirim request DELETE ke endpoint "/api/monthly-schedule/{id}" untuk kedua kalinya
    Maka response API schedule mengembalikan status 404 Not Found

  @API-014
  Skenario: Fetch daftar master shift kerja
    Ketika client mengirim request GET jadwal ke endpoint "/api/shifting" dengan token valid
    Maka response API schedule mengembalikan status 200 OK
    Dan response memuat daftar master shifting kerja aktif

  @API-015
  Skenario: Validasi kepatuhan JSON Schema entitas Monthly Schedule
    Ketika client mengirim request GET jadwal ke endpoint "/api/monthly-schedule" dengan token valid
    Maka response JSON mematuhi schema kontrak model Monthly Schedule
