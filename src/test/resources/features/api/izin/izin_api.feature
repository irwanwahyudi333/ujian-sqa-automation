# language: id
@api @management @izin @lean
Fitur: Pengujian API Rekapitulasi & Permohonan Izin
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint permohonan izin karyawan dan kelengkapan lampiran
  Untuk memastikan integrasi CRUD, validasi tanggal, otorisasi RBAC, dan proteksi IDOR berkas

  Skenario: Mendapatkan daftar permohonan izin dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint izin "/api/izin"
    Maka Response status izin harus 200
    Dan Response body izin harus berisikan daftar riwayat izin

  Skenario: Pembuatan permohonan izin baru via API
    Ketika Klien mengirim request "POST" ke endpoint izin "/api/izin" dengan payload:
      """
      {
        "type": "Izin Pribadi",
        "start_date": "2026-11-05",
        "end_date": "2026-11-06",
        "reason": "Keperluan Keluarga Mendesak",
        "attachment": "surat_izin_valid.pdf"
      }
      """
    Maka Response status izin harus 201 atau 200

  Skenario: Validasi penolakan pengajuan izin dengan tanggal terbalik
    Ketika Klien mengirim request "POST" ke endpoint izin "/api/izin" dengan payload:
      """
      {
        "type": "Izin Pribadi",
        "start_date": "2026-11-06",
        "end_date": "2026-11-02",
        "reason": "Date Inversion Test",
        "attachment": "surat_izin.pdf"
      }
      """
    Maka Response status izin harus 400

  Skenario: Akses endpoint izin tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint izin "/api/izin"
    Maka Response status izin harus 401

