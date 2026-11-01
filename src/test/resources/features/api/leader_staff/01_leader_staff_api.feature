# language: id
@api @leader-staff @staff @api-leader-staff
Fitur: Pengujian Kontrak API Backend Leader Staff Lists
  Sebagai Sistem atau Pengembang
  Saya ingin memverifikasi respons endpoint API /api/v2/leader/staff
  Agar integrasi data anggota tim staf berjalan sesuai kontrak spesifikasi

  @API-001
  Skenario: Mengambil daftar staf tim leader via GET endpoint
    Ketika client mengirim request GET leader staff ke endpoint "/api/v2/leader/staff"
    Maka response API leader staff mengembalikan status 200 OK
    Dan response API leader staff memuat daftar data staf

  @API-002
  Skenario: Mengambil data staf dengan parameter paginasi limit 5
    Ketika client mengirim request GET leader staff ke endpoint "/api/v2/leader/staff?page=1&limit=5"
    Maka response API leader staff mengembalikan status 200 OK

  @API-003
  Skenario: Mengambil data staf dengan query pencarian nama
    Ketika client mengirim request GET leader staff ke endpoint "/api/v2/leader/staff?search=Ahmad"
    Maka response API leader staff mengembalikan status 200 OK

  @API-004
  Skenario: Menambahkan staf baru ke dalam tim via POST endpoint
    Ketika client mengirim request POST leader staff ke endpoint "/api/v2/leader/staff" dengan data staf valid
    Maka response API leader staff mengembalikan status code 200 atau 201

  @API-005
  Skenario: Memperbarui data profil staf via PATCH endpoint
    Ketika client mengirim request PATCH leader staff ke endpoint "/api/v2/leader/staff/1" dengan body update staf
    Maka response API leader staff mengembalikan status code 200 atau 204

  @API-006
  Skenario: Menghapus atau melepaskan staf dari tim leader via DELETE endpoint
    Ketika client mengirim request DELETE leader staff ke endpoint "/api/v2/leader/staff/1"
    Maka response API leader staff mengembalikan status code 200 atau 204

  @API-007
  Skenario: Mengambil data staf dengan filter status Aktif
    Ketika client mengirim request GET leader staff ke endpoint "/api/v2/leader/staff?status=Aktif"
    Maka response API leader staff mengembalikan status 200 OK
