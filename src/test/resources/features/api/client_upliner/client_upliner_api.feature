# language: id
@api @management @client-upliner @api-client-upliner
Fitur: Pengujian Backend REST API Modul Client Upliner
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint REST API client upliner memvalidasi otorisasi admin dan penugasan relasi
  Agar hak akses persetujuan atasan tidak disalahgunakan

  @CUPL-API-001
  Skenario: Mengambil daftar client upliner dengan token valid
    Ketika client mengirim request GET ke endpoint "/api/users/" dengan parameter tipe upliner
    Maka response API client upliner mengembalikan status 200 OK
    Dan response API memuat daftar akun client dan upliner

  @CUPL-API-002
  Skenario: Mengambil daftar client upliner tanpa token otorisasi
    Ketika client mengirim request GET ke endpoint client upliner "/api/users/" tanpa token otorisasi
    Maka response API client upliner mengembalikan status 401 Unauthorized

  @CUPL-API-003
  Skenario: Menghapus relasi akun upliner dengan ID valid
    Ketika client mengirim request DELETE ke endpoint "/api/users/{id}" untuk upliner dengan ID valid
    Maka response API client upliner mengembalikan status 200 OK

