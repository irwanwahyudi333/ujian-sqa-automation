# language: id
@api @management @user-monitoring @api-monitoring
Fitur: Pengujian Backend REST API Modul User Monitoring
  Sebagai sistem HADIR dan administrator
  Saya ingin memastikan seluruh endpoint REST API user monitoring memvalidasi token, format payload, dan query parameter
  Agar integritas data pemantauan karyawan di lapangan terjamin dan aman dari anomali

  @UMON-API-001
  Skenario: Mengambil daftar monitoring pengguna dengan token otorisasi valid
    Ketika client mengirim request GET ke endpoint monitoring "/api/user-monitoring" dengan token valid
    Maka response API monitoring mengembalikan status 200 OK
    Dan response API memuat daftar data monitoring pengguna

  @UMON-API-002
  Skenario: Mengambil daftar monitoring pengguna tanpa menyertakan token otorisasi
    Ketika client mengirim request GET ke endpoint monitoring "/api/user-monitoring" tanpa token otorisasi
    Maka response API monitoring mengembalikan status 401 Unauthorized

  @UMON-API-003
  Skenario: Menambahkan monitoring pengguna baru dengan payload yang valid
    Ketika client mengirim request POST ke endpoint "/api/user-monitoring" dengan nama "Irwan Wahyudi" NIK "D6240005" lokasi "Head Office Kuningan"
    Maka response API monitoring mengembalikan status 200 atau 201
    Dan response API memuat informasi user monitoring yang baru didaftarkan

  @UMON-API-004
  Skenario: Validasi pendaftaran monitoring pengguna tanpa menyertakan NIK
    Ketika client mengirim request POST ke endpoint "/api/user-monitoring" dengan payload tanpa NIK
    Maka response API monitoring mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan field NIK wajib diisi

  @UMON-API-005
  Skenario: Menghapus data monitoring pengguna dengan ID yang valid
    Ketika client mengirim request DELETE ke endpoint monitoring "/api/user-monitoring/{id}" dengan ID valid
    Maka response API monitoring mengembalikan status 200 OK

