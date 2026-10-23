# language: id
@api @module-login-api @login-api
Fitur: Pengujian Endpoint REST API Autentikasi Pengguna (Login)
  Sebagai penyedia antarmuka backend
  Saya ingin memastikan endpoint login aman, tangguh, dan mematuhi kontrak API
  Agar proses autentikasi backend bekerja akurat dan terlindungi dari eksploitasi

  Latar Belakang:
    Dengan client menyiapkan request specification untuk endpoint login

  @positive @api-log-001 @lean
  Skenario: Request POST login dengan kredensial akun aktif yang sah
    Ketika client mengirim request login dengan email "qa.approved.user@dikahadir.com" dan password "P@ssw0rdMagang123"
    Maka status code response API login adalah 200
    Dan response API memuat token autentikasi yang sah

  @negative @api-log-002 @lean
  Skenario: Request POST login dengan password salah
    Ketika client mengirim request login dengan email "qa.approved.user@dikahadir.com" dan password "WrongPassword123"
    Maka status code response API login adalah 401 atau 400
    Dan response API memuat pesan kesalahan kredensial tidak cocok

  @negative @api-log-003
  Skenario: Request POST login dengan email belum terdaftar
    Ketika client mengirim request login dengan email "unregistered.user@dikahadir.com" dan password "Password123"
    Maka status code response API login adalah 401 atau 400
    Dan response API memuat pesan kesalahan kredensial tidak cocok

  @negative @api-log-004 @lean
  Skenario: Request POST login dengan akun berstatus Pending Approval Admin
    Ketika client mengirim request login dengan email "raxaf58877@backyr.com" dan password "P@ssw0rdMagang123"
    Maka status code response API login adalah 403 atau 400
    Dan response API memuat informasi akun sedang menunggu persetujuan admin

  @negative @api-log-005
  Skenario: Request POST login dengan akun berstatus Suspended atau Nonaktif
    Ketika client mengirim request login dengan email "suspended.user@dikahadir.com" dan password "P@ssw0rdMagang123"
    Maka status code response API login adalah 403 atau 400
    Dan response API memuat pesan penolakan akun tidak aktif

  @negative @api-log-011
  Skenario: Request login menggunakan metode HTTP GET ditolak
    Ketika client mengirim request login menggunakan metode HTTP GET
    Maka status code response API login adalah 405

  @negative @api-log-012
  Skenario: Request login menggunakan metode HTTP PUT ditolak
    Ketika client mengirim request login menggunakan metode HTTP PUT
    Maka status code response API login adalah 405

  @negative @api-log-013
  Skenario: Request login dengan header Content-Type text plain ditolak
    Ketika client mengirim request login dengan header Content-Type "text/plain"
    Maka status code response API login adalah 415

  @negative @api-log-014
  Skenario: Request login tanpa header Content-Type ditolak
    Ketika client mengirim request login tanpa header Content-Type
    Maka status code response API login adalah 415 atau 400

  @contract @api-log-015
  Skenario: Verifikasi struktur data respon sukses login sesuai kontrak API
    Ketika client mengirim request login dengan email "qa.approved.user@dikahadir.com" dan password "P@ssw0rdMagang123"
    Maka struktur skema JSON respon sukses login memuat seluruh field wajib

  @contract @api-log-016
  Skenario: Verifikasi struktur data respon error login sesuai standar
    Ketika client mengirim request login dengan email "qa.approved.user@dikahadir.com" dan password "WrongPassword123"
    Maka struktur skema JSON respon error login memuat pesan kesalahan yang terstandar

  @security @api-log-017
  Skenario: Respons error API login tidak membocorkan stack trace atau informasi internal
    Ketika client mengirim request login dengan format JSON tidak lengkap
    Maka respons error API tidak membocorkan informasi query, stack trace, atau path server

  @security @api-log-018
  Skenario: Pengiriman payload login raksasa tidak menyebabkan server crash
    Ketika client mengirimkan payload request login berukuran sangat besar
    Maka status code response API login adalah 413 atau 400

  @performance @api-log-019
  Skenario: Pengiriman sepuluh request login simultan ditangani secara konsisten
    Ketika client mengirimkan sepuluh request login secara beruntun
    Maka seluruh request login diselesaikan dengan respon yang konsisten

  @performance @api-log-020
  Skenario: Response time endpoint login memenuhi standar SLA
    Ketika client mengukur waktu respon request login
    Maka durasi respon server berada dalam batas ambang kewajaran kurang dari 2000 milidetik

  @negative @api-payload-matrix
  Skenario konsep: Validasi mutasi payload login tidak valid melalui API (<test_id> - <keterangan>)
    Ketika client mengirimkan request login dengan payload "<payload_type>" bernilai "<payload_value>"
    Maka status code response API login adalah 400

    Contoh:
      | test_id      | payload_type       | payload_value | keterangan                 |
      | API-LOG-006  | empty_body         | none          | Payload body kosong murni  |
      | API-LOG-007  | missing_email      | none          | Field email tidak dikirim  |
      | API-LOG-008  | missing_password   | none          | Field password tidak dikirim |
      | API-LOG-009  | invalid_email      | bad-email     | Format regex email salah   |
      | API-LOG-010  | invalid_data_type  | 12345         | Tipe data field bukan teks |
