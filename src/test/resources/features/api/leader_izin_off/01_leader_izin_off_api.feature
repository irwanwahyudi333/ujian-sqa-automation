# language: id
@api @leader-izin-off @izin-off @api-leader-izin-off @izin @lean
Fitur: Pengujian Kontrak API Backend Leader Izin Off
  Sebagai Sistem atau Pengembang
  Saya ingin memverifikasi respons endpoint API /api/off-permit/
  Agar integrasi data izin off berjalan sesuai kontrak spesifikasi

  @API-001
  Skenario: Mengambil daftar permohonan izin off tim via GET endpoint
    Ketika client mengirim request GET leader izin off ke endpoint "/api/off-permit/"
    Maka response API leader izin off mengembalikan status 200 OK
    Dan response API leader izin off memuat daftar data permohonan

  @API-002
  Skenario: Mengambil data izin off dengan filter status PENDING
    Ketika client mengirim request GET leader izin off ke endpoint "/api/off-permit/?status=PENDING"
    Maka response API leader izin off mengembalikan status 200 OK

  @API-003
  Skenario: Menyetujui permohonan izin off via PATCH endpoint
    Ketika client mengirim request PATCH leader izin off ke endpoint "/api/off-permit/101" dengan body approval
    Maka response API leader izin off mengembalikan status code 200 atau 204

  @API-004
  Skenario: Menolak permohonan izin off via PATCH endpoint
    Ketika client mengirim request PATCH leader izin off ke endpoint "/api/off-permit/102" dengan body penolakan
    Maka response API leader izin off mengembalikan status code 200 atau 204

  @API-005
  Skenario: Menambahkan pengajuan izin off baru via POST endpoint
    Ketika client mengirim request POST leader izin off ke endpoint "/api/off-permit/" dengan data permohonan valid
    Maka response API leader izin off mengembalikan status code 200 atau 201

  @API-006
  Skenario: Menghapus atau membatalkan permohonan izin off via DELETE endpoint
    Ketika client mengirim request DELETE leader izin off ke endpoint "/api/off-permit/101"
    Maka response API leader izin off mengembalikan status code 200 atau 204

  @API-007
  Skenario: Mengambil permohonan dengan parameter pagination
    Ketika client mengirim request GET leader izin off ke endpoint "/api/off-permit/?page=1&limit=5"
    Maka response API leader izin off mengembalikan status 200 OK

  @SEC-IZN-001 @security @rbac-bypass @izin @lean
  Skenario: Backend wajib menolak eskalasi approval izin off oleh staf atau client non-otoritas
    Ketika client non-otoritas mencoba melakukan mutasi persetujuan izin off ke status "APPROVED"
    Maka sistem backend harus menolak perubahan status izin off dengan status 403 Forbidden
