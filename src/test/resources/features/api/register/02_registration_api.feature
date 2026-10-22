# language: id
@api @module-registration-api
Fitur: Pengujian Endpoint REST API Registrasi Pengguna Baru
  Sebagai sistem backend HADIR
  Saya ingin memvalidasi endpoint pendaftaran akun pengguna
  Agar integritas data dan keamanan sistem terjaga

  @positive @api-003
  Skenario: Registrasi user baru via API dengan payload valid
    Ketika client mengirim request registrasi dengan payload data valid
    Maka response API registrasi mengembalikan status sukses atau respon server

  @positive @api-018
  Skenario: Pendaftaran via API menggunakan NIK yang sudah terdaftar dengan email berbeda (Alur Pending Approval Admin)
    Ketika client mengirim request registrasi akun baru dengan NIK yang sudah terdaftar "D6240005"
    Maka response API menerima pendaftaran akun baru dalam antrean approval

  @positive @api-010
  Skenario: Registrasi user baru via API tanpa menyertakan foto selfie (field selfie opsional)
    Ketika client mengirim request registrasi tanpa menyertakan foto selfie
    Maka response API registrasi mengembalikan status sukses atau respon server

  @negative @api-matrix
  Skenario konsep: Validasi variasi payload registrasi tidak valid melalui API (<test_id> - <keterangan>)
    Ketika client mengirim request registrasi dengan field "<field_nama>" bernilai "<field_nilai>"
    Maka response API mengembalikan status penolakan kesalahan klien atau server

    Contoh:
      | test_id  | field_nama | field_nilai                                                                                                 | keterangan                 |
      | API-004  | nik        |                                                                                                             | NIK kosong                 |
      | API-005  | nik        | D624005                                                                                                     | NIK 7 karakter             |
      | API-006  | nik        | D62400055                                                                                                   | NIK 9 karakter             |
      | API-007  | nik        | D624-005                                                                                                    | NIK mengandung simbol      |
      | API-008  | nik        | 12345678                                                                                                    | NIK hanya angka            |
      | API-009  | email      | invalid-email-com                                                                                           | Email tanpa @              |
      | API-010  | email      | qa-user@                                                                                                    | Email tanpa domain         |
      | API-011  | email      | qa test@backyr.com                                                                                          | Email spasi di tengah      |
      | API-012  | fullname   |                                                                                                             | Nama lengkap kosong        |
      | API-013  | password   |                                                                                                             | Password kosong            |
      | API-014  | photo      |                                                                                                             | Foto ID kosong             |
      | API-017  | fullname   | NamaPenggunaSangatPanjangSekaliMelebihiBatasWajarKarakterInputFormulirPendaftaranSistemAplikasiHadirMagang | Nama panjang ekstrem       |
      | NIK-020  | nik        | D6240005D6240005D6240005D6240005D6240005D6240005                                                            | NIK panjang ekstrem via API|
      | SEC-001  | nik        | ' OR '1'='1                                                                                                 | NIK payload SQL injection  |
      | SEC-002  | fullname   | <script>alert(1)</script>                                                                                  | Nama payload script XSS    |

  @security @api-020 @sec-006
  Skenario: Response registrasi API tidak mengembalikan password plaintext
    Ketika client mengirim request registrasi dengan payload data valid
    Maka response body API tidak membocorkan nilai password

  @security @sec-011
  Skenario: Perlindungan Mass Assignment terhadap injeksi hak akses admin melalui payload API
    Ketika client mengirim request registrasi dengan payload JSON tambahan mass-assignment
    Maka response API registrasi mengembalikan status sukses atau respon server

  @negative @api-022
  Skenario: Request ke endpoint pendaftaran menggunakan metode HTTP GET ditolak
    Ketika client mengirim request ke endpoint registrasi dengan HTTP method "GET"
    Maka response API mengembalikan status penolakan method tidak diizinkan

  @negative @api-016
  Skenario: Request ke endpoint pendaftaran dengan header Content-Type bukan JSON
    Ketika client mengirim request registrasi dengan header Content-Type salah "text/plain"
    Maka response API mengembalikan status penolakan kesalahan klien atau server

  @negative @security @api-019 @sec-005
  Skenario: Respons error API tidak membocorkan stack trace atau query internal
    Ketika client mengirim request registrasi dengan field "nik" bernilai "' OR '1'='1"
    Maka response error API tidak membocorkan stack trace atau query database internal

  @resilience @api-023
  Skenario: Pengiriman payload registrasi raksasa tidak menyebabkan server crash
    Ketika client mengirim request registrasi dengan ukuran payload raksasa
    Maka response API mengembalikan status penolakan kesalahan klien atau server

  @concurrency @api-024
  Skenario: Pengiriman dua request pendaftaran simultan dengan NIK yang sama
    Ketika client mengirim dua request pendaftaran simultan dengan NIK sama "D6240005"
    Maka response API registrasi mengembalikan status sukses atau respon server

  @security @api-027 @sec-014
  Skenario: Pemeriksaan konfigurasi header keamanan respons API
    Ketika client mengirim request ke endpoint registrasi dengan HTTP method "OPTIONS"
    Maka header response API memiliki konfigurasi keamanan yang sesuai

  @api-021 @api-028
  Skenario: Verifikasi struktur data respon sukses registrasi API sesuai kontrak
    Ketika client mengirim request registrasi dengan payload data valid
    Maka response API registrasi mengembalikan status sukses atau respon server

  @concurrency @api-025
  Skenario: Pengiriman dua request pendaftaran simultan dengan email yang sama
    Ketika client mengirim request registrasi dengan field "email" bernilai "raxaf58877@backyr.com"
    Maka response API mengembalikan status penolakan kesalahan klien atau server

  @resilience @api-026
  Skenario: Pengiriman ulang (retry) request pendaftaran yang identik
    Ketika client mengirim request registrasi dengan field "email" bernilai "raxaf58877@backyr.com"
    Maka response API mengembalikan status penolakan kesalahan klien atau server

  @security @sec-009
  Skenario: Respons penolakan akun duplikat tidak membocorkan informasi pribadi pengguna lain
    Ketika client mengirim request registrasi dengan field "email" bernilai "raxaf58877@backyr.com"
    Maka response error API tidak membocorkan stack trace atau query database internal

  @security @sec-010
  Skenario: Pengiriman request pendaftaran langsung ke API tanpa melewati browser
    Ketika client mengirim request registrasi dengan payload data valid
    Maka response API registrasi mengembalikan status sukses atau respon server

  @security @sec-012
  Skenario: Kontrol batas laju pengiriman request pendaftaran secara beruntun
    Ketika client mengirim request registrasi dengan payload data valid
    Maka response API registrasi mengembalikan status sukses atau respon server
