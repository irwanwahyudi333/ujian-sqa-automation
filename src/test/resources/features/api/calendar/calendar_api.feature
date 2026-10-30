# language: id
@api @management @calendar
Fitur: Pengujian API Master Calendar
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master calendar
  Untuk memastikan integrasi CRUD, variasi data kalender unit, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar kalender unit dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint calendar "/api/calendar"
    Maka Response status calendar harus 200
    Dan Response body calendar harus berisikan daftar kalender

  Skenario: Pembuatan kalender unit baru via API
    Ketika Klien mengirim request "POST" ke endpoint calendar "/api/calendar" dengan payload:
      """
      {
        "name": "Kalender Operasional 2026 API",
        "departemen": "IT Development"
      }
      """
    Maka Response status calendar harus 201 atau 200

  Skenario: Validasi pembuatan kalender dengan nama kosong
    Ketika Klien mengirim request "POST" ke endpoint calendar "/api/calendar" dengan payload:
      """
      {
        "name": "",
        "departemen": "Operations"
      }
      """
    Maka Response status calendar harus 400

  Skenario: Akses endpoint calendar tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint calendar "/api/calendar"
    Maka Response status calendar harus 401

