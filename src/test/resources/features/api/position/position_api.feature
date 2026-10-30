# language: id
@api @management @position @api-position
Fitur: Pengujian Backend REST API Modul Position
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint REST API position memvalidasi relasi posisi dan departemen
  Agar integritas referensial data jabatan terjaga

  @POS-API-001
  Skenario: Mengambil daftar posisi jabatan dengan token valid
    Ketika client mengirim request GET ke endpoint position "/api/position" dengan token valid
    Maka response API position mengembalikan status 200 OK
    Dan response API memuat daftar posisi jabatan

  @POS-API-002
  Skenario: Menambahkan posisi baru dengan payload nama dan departemen valid
    Ketika client mengirim request POST ke endpoint "/api/position" dengan nama "Lead QA Engineer" dan departemen "Technology"
    Maka response API position mengembalikan status 200 atau 201
    Dan response API memuat posisi jabatan yang baru dibuat

  @POS-API-003
  Skenario: Validasi penambahan posisi tanpa menyertakan departemen
    Ketika client mengirim request POST ke endpoint "/api/position" dengan payload tanpa departemen
    Maka response API position mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan field departemen wajib diisi

  @POS-API-004
  Skenario: Menghapus posisi jabatan dengan ID valid
    Ketika client mengirim request DELETE ke endpoint position "/api/position/{id}" dengan ID valid
    Maka response API position mengembalikan status 200 OK

