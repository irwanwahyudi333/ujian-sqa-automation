# language: id
@api @management @unit-leave @cuti @lean
Fitur: Pengujian API Master Unit Leave & Otorisasi Cuti
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master aturan cuti (unit leave) dan otorisasi persetujuan cuti
  Untuk memastikan integrasi CRUD, batasan kuota cuti, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar aturan cuti dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint unit leave "/api/unit-leave"
    Maka Response status unit leave harus 200
    Dan Response body unit leave harus berisikan daftar aturan cuti

  Skenario: Pembuatan aturan cuti baru via API
    Ketika Klien mengirim request "POST" ke endpoint unit leave "/api/unit-leave" dengan payload:
      """
      {
        "name": "Cuti Khusus Training API",
        "eligible_month": 3,
        "max_remaining": 4,
        "carry_forward_month": "Maret",
        "total_leave": 10
      }
      """
    Maka Response status unit leave harus 201 atau 200

  Skenario: Validasi pembuatan aturan cuti dengan total cuti bernilai negatif
    Ketika Klien mengirim request "POST" ke endpoint unit leave "/api/unit-leave" dengan payload:
      """
      {
        "name": "Cuti Error Boundary",
        "eligible_month": 3,
        "max_remaining": 4,
        "carry_forward_month": "Maret",
        "total_leave": -5
      }
      """
    Maka Response status unit leave harus 400

  Skenario: Akses endpoint aturan cuti tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint unit leave "/api/unit-leave"
    Maka Response status unit leave harus 401

  @SEC-CUTI-001 @security @rbac @cuti @lean
  Skenario: Backend wajib menolak mutasi persetujuan cuti jika ditembak oleh staf non-leader tanpa hak otorisasi
    Ketika staf non-leader mencoba menyetujui klaim cuti via API "/api/leader/leave/1/approval" tanpa hak otorisasi
    Maka sistem backend harus menolak permohonan persetujuan dengan status 403 Forbidden
