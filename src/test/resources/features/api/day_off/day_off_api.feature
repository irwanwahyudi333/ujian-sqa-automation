# language: id
@api @management @day-off
Fitur: Pengujian API Master Day Off
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master hari libur (day off)
  Untuk memastikan integrasi CRUD, validasi format tanggal, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar hari libur dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint day off "/api/day-off"
    Maka Response status day off harus 200
    Dan Response body day off harus berisikan daftar hari libur

  Skenario: Pembuatan hari libur baru via API
    Ketika Klien mengirim request "POST" ke endpoint day off "/api/day-off" dengan payload:
      """
      {
        "date": "2026-08-17",
        "description": "Hari Kemerdekaan RI API",
        "category": "Liburan Nasional"
      }
      """
    Maka Response status day off harus 201 atau 200

  Skenario: Validasi pembuatan hari libur dengan tanggal tidak valid
    Ketika Klien mengirim request "POST" ke endpoint day off "/api/day-off" dengan payload:
      """
      {
        "date": "invalid-date-format",
        "description": "Error Date Test",
        "category": "Liburan Nasional"
      }
      """
    Maka Response status day off harus 400

  Skenario: Akses endpoint hari libur tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint day off "/api/day-off"
    Maka Response status day off harus 401

