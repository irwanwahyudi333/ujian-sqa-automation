# language: id
@api @management @departemens @api-departemens
Fitur: Pengujian Backend REST API Modul Departemens
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint REST API departemens mengelola data CRUD departemen secara aman dan konsisten
  Agar operasional departemen perusahaan berjalan tanpa gangguan

  @DEPT-API-001
  Skenario: Mengambil daftar departemen dengan token valid
    Ketika client mengirim request GET ke endpoint departemens "/api/departemens/" dengan token valid
    Maka response API departemens mengembalikan status 200 OK
    Dan response API memuat daftar departemen dan total data

  @DEPT-API-002
  Skenario: Mengambil daftar departemen tanpa token otorisasi
    Ketika client mengirim request GET ke endpoint departemens "/api/departemens/" tanpa token otorisasi
    Maka response API departemens mengembalikan status 401 Unauthorized

  @DEPT-API-003
  Skenario: Menambahkan departemen baru dengan payload valid
    Ketika client mengirim request POST ke endpoint departemens "/api/departemens/" dengan nama "Quality Assurance"
    Maka response API departemens mengembalikan status 200 atau 201
    Dan response API memuat informasi departemen yang baru dibuat

  @DEPT-API-004
  Skenario: Validasi penambahan departemen dengan nama kosong
    Ketika client mengirim request POST ke endpoint departemens "/api/departemens/" dengan payload nama kosong
    Maka response API departemens mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan field nama departemen wajib diisi

  @DEPT-API-005
  Skenario: Menghapus departemen dengan ID valid
    Ketika client mengirim request DELETE ke endpoint departemens "/api/departemens/{id}" dengan ID valid
    Maka response API departemens mengembalikan status 200 OK

