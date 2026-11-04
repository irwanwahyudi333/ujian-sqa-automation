# language: id
@api @management @sakit @lean
Fitur: Pengujian API Rekapitulasi & Validasi Izin Sakit
  Sebagai Administrator SQA
  Saya ingin menguji API endpoint permohonan izin sakit dan lampiran surat dokter
  Untuk memastikan integrasi CRUD, validasi lampiran medis, integritas tanggal, otorisasi RBAC, dan proteksi keamanan berkas

  Skenario: Mendapatkan daftar rekaman izin sakit dengan otentikasi valid
    Ketika Klien mengirim request "GET" ke endpoint sakit "/api/sakit"
    Maka Response status sakit harus 200
    Dan Response body sakit harus berisikan daftar riwayat izin sakit

  Skenario: Pembuatan permohonan izin sakit baru via API
    Ketika Klien mengirim request "POST" ke endpoint sakit "/api/sakit" dengan payload:
      """
      {
        "start_date": "2026-11-05",
        "end_date": "2026-11-06",
        "diagnosis": "Demam Typhoid / Tipes",
        "doctor_letter": "surat_dokter_valid.pdf"
      }
      """
    Maka Response status sakit harus 201 atau 200

  Skenario: Validasi penolakan pengajuan izin sakit dengan tanggal terbalik
    Ketika Klien mengirim request "POST" ke endpoint sakit "/api/sakit" dengan payload:
      """
      {
        "start_date": "2026-11-06",
        "end_date": "2026-11-02",
        "diagnosis": "Date Inversion Test",
        "doctor_letter": "surat_dokter.pdf"
      }
      """
    Maka Response status sakit harus 400

  Skenario: Akses endpoint izin sakit tanpa token otorisasi
    Ketika Klien mengirim request "GET" tanpa otorisasi ke endpoint sakit "/api/sakit"
    Maka Response status sakit harus 401

  @SEC-SKT-001 @security @path-traversal @sakit @lean
  Skenario: Backend wajib menolak eksfiltrasi berkas sistem via Path Traversal pada endpoint berkas medis
    Ketika penyerang mencoba mengunduh berkas sensitif sistem via parameter path traversal "../../../../etc/passwd"
    Maka sistem backend harus menolak permintaan pengunduhan berkas dengan status 400 Bad Request atau 403 Forbidden

