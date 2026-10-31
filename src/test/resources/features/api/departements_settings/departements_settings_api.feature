# language: id
@api @management @departements-settings @api-departements-settings
Fitur: Pengujian Backend REST API Modul Departements Settings
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint konfigurasi status departemen memvalidasi token dan perubahan status
  Agar integritas status aktif departemen konsisten di database

  @DEPTS-API-001
  Skenario: Mengambil daftar konfigurasi departemen dengan token valid
    Ketika client mengirim request GET ke endpoint departements settings "/api/departements-settings" dengan token valid
    Maka response API departemens settings mengembalikan status 200 OK
    Dan response API memuat daftar konfigurasi departemen

  @DEPTS-API-002
  Skenario: Menambahkan konfigurasi departemen baru dengan payload valid
    Ketika client mengirim request POST ke endpoint "/api/departements-settings" dengan nama "IT Operational Setting"
    Maka response API departemens settings mengembalikan status 200 atau 201
    Dan response API memuat setting departemen yang baru dibuat

  @DEPTS-API-003
  Skenario: Memperbarui status keaktifan setting departemen
    Ketika client mengirim request PATCH ke endpoint "/api/departements-settings/{id}" dengan status "false"
    Maka response API departemens settings mengembalikan status 200 OK

  @DEPTS-API-004
  Skenario: Menghapus konfigurasi departemen dengan ID valid
    Ketika client mengirim request DELETE ke endpoint departements settings "/api/departements-settings/{id}" dengan ID valid
    Maka response API departemens settings mengembalikan status 200 OK

