# language: id
@api @management @absensi @lean
Fitur: Pengujian API Presensi Kehadiran & Koreksi Absen
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint presensi mandiri dan permohonan koreksi absensi
  Untuk memastikan integrasi CRUD, validasi payload, otorisasi RBAC, integritas waktu, dan deteksi manipulasi lokasi

  Skenario: Mendapatkan riwayat presensi kehadiran dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint absensi "/api/absent/history"
    Maka Response status absensi harus 200
    Dan Response body absensi harus berisikan daftar riwayat presensi

  Skenario: Pembuatan permohonan koreksi jam absensi baru via API
    Ketika Klien mengirim request "POST" ke endpoint absensi "/api/absent/correction" dengan payload:
      """
      {
        "date_in": "2026-11-04 08:30:00",
        "date_out": "2026-11-04 17:30:00",
        "reason": "Lupa absen keluar karena rapat divisi",
        "is_wfh": false
      }
      """
    Maka Response status absensi harus 201 atau 200

  Skenario: Validasi penolakan pengajuan koreksi tanggal lampau melebihi batas cut-off
    Ketika Klien mengirim request "POST" ke endpoint absensi "/api/absent/correction" dengan payload:
      """
      {
        "date_in": "2025-01-01 08:00:00",
        "date_out": "2025-01-01 17:00:00",
        "reason": "Exceeded cut-off date boundary test",
        "is_wfh": false
      }
      """
    Maka Response status absensi harus 400

  Skenario: Akses endpoint absensi tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint absensi "/api/absent/history"
    Maka Response status absensi harus 401

  @SEC-ABS-001 @security @mock-location @absensi @lean
  Skenario: Backend wajib menolak presensi masuk jika terdeteksi Fake GPS atau Mock Location provider
    Ketika pengguna mobile mengirim request presensi masuk dengan flag mock location aktif:
      """
      {
        "latitude": -6.2088,
        "longitude": 106.8456,
        "isMockLocation": true,
        "provider": "fake_gps_app"
      }
      """
    Maka sistem backend harus menolak presensi dengan status 400 Bad Request atau 403 Forbidden

