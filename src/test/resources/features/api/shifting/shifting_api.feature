# language: id
@api @management @shifting
Fitur: Pengujian API Master Shifting
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint master shifting
  Untuk memastikan integrasi CRUD, batasan jam kerja, otorisasi RBAC, dan skema JSON berjalan sesuai spesifikasi

  Skenario: Mendapatkan daftar shifting dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint shifting "/api/shiftings"
    Maka Response status shifting harus 200
    Dan Response body shifting harus berisikan daftar shifting

  Skenario: Pembuatan data shifting baru via API dengan parameter lengkap
    Ketika Klien mengirim request "POST" ke endpoint shifting "/api/shiftings" dengan payload:
      """
      {
        "code": "SH-API-01",
        "name": "Shift Otomasi Malam",
        "unit": "Technology",
        "time_in": "20:00",
        "time_out": "05:00",
        "break_start": "00:00",
        "break_end": "01:00"
      }
      """
    Maka Response status shifting harus 201 atau 200

  Skenario: Validasi pembuatan shifting dengan kode yang melebihi batas karakter
    Ketika Klien mengirim request "POST" ke endpoint shifting "/api/shiftings" dengan payload:
      """
      {
        "code": "KODE_SHIFTING_TERLALU_PANJANG_LEBIH_DARI_15_KARAKTER",
        "name": "Shift Error Boundary",
        "unit": "Technology",
        "time_in": "08:00",
        "time_out": "17:00"
      }
      """
    Maka Response status shifting harus 400

  Skenario: Akses endpoint shifting tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint shifting "/api/shiftings"
    Maka Response status shifting harus 401

