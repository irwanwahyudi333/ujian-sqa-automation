# language: id
@api @management @schedule
Fitur: Pengujian API Master Schedule
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master schedule
  Untuk memastikan integrasi CRUD, variasi data jadwal, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar jadwal kerja dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint schedule "/api/schedule"
    Maka Response status schedule harus 200
    Dan Response body schedule harus berisikan daftar jadwal

  Skenario: Pembuatan jadwal kerja baru via API
    Ketika Klien mengirim request "POST" ke endpoint schedule "/api/schedule" dengan payload:
      """
      {
        "name": "Jadwal WFA API",
        "type": "Jadwal kerja flexible",
        "work_days": "Senin - Jumat"
      }
      """
    Maka Response status schedule harus 201 atau 200

  Skenario: Validasi pembuatan jadwal tanpa hari kerja
    Ketika Klien mengirim request "POST" ke endpoint schedule "/api/schedule" dengan payload:
      """
      {
        "name": "Jadwal Incomplete",
        "type": "Jadwal kerja tetap",
        "work_days": ""
      }
      """
    Maka Response status schedule harus 400

  Skenario: Akses endpoint schedule tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint schedule "/api/schedule"
    Maka Response status schedule harus 401

