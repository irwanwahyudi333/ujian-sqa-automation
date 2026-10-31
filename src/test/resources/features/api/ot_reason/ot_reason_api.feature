# language: id
@api @management @ot-reason
Fitur: Pengujian API Master Overtime Reason
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master overtime reason
  Untuk memastikan integrasi CRUD, variasi data alasan lembur, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar alasan lembur dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint ot reason "/api/ot-reason"
    Maka Response status ot reason harus 200
    Dan Response body ot reason harus berisikan daftar alasan lembur

  Skenario: Pembuatan alasan lembur baru via API
    Ketika Klien mengirim request "POST" ke endpoint ot reason "/api/ot-reason" dengan payload:
      """
      {
        "name": "Audit Kepatuhan ISO API"
      }
      """
    Maka Response status ot reason harus 201 atau 200

  Skenario: Validasi pembuatan alasan lembur dengan nama kosong
    Ketika Klien mengirim request "POST" ke endpoint ot reason "/api/ot-reason" dengan payload:
      """
      {
        "name": ""
      }
      """
    Maka Response status ot reason harus 400

  Skenario: Akses endpoint alasan lembur tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint ot reason "/api/ot-reason"
    Maka Response status ot reason harus 401

