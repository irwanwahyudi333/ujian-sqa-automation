# language: id
@api @user-monitoring @api-user-monitoring
Fitur: Pengujian Backend REST API Modul User Monitoring
  Sebagai sistem integrasi dan aplikasi HADIR
  Saya ingin memastikan endpoint REST API User Monitoring beroperasi dengan andal, aman, dan memvalidasi payload
  Agar operasi CRUD data pemantauan karyawan berjalan konsisten dan terlindungi dari anomali data

  @API-001
  Skenario: Fetch daftar user monitoring dengan token valid
    Ketika client mengirim request GET user monitoring ke endpoint "/api/v2/users" dengan token valid
    Maka response API user monitoring mengembalikan status 200 OK
    Dan response API user monitoring memuat array data users dan total data

  @API-002
  Skenario: Fetch user monitoring tanpa header token otorisasi
    Ketika client mengirim request GET user monitoring ke endpoint "/api/v2/users" tanpa token otorisasi
    Maka response API user monitoring mengembalikan status 401 Unauthorized

  @API-003
  Skenario: Fetch user monitoring dengan query paginasi
    Ketika client mengirim request GET user monitoring ke endpoint "/api/v2/users" dengan query "page=1&limit=5"
    Maka response API user monitoring mengembalikan status 200 OK
    Dan jumlah item user dalam response tepat berjumlah 5 data

  @API-004
  Skenario: Fetch user monitoring dengan filter query nama
    Ketika client mengirim request GET user monitoring ke endpoint "/api/v2/users" dengan filter nama "Ahmad"
    Maka response API user monitoring mengembalikan status 200 OK
    Dan seluruh data user dalam response memuat nama "Ahmad"

  @API-005
  Skenario: Fetch user monitoring dengan filter query departemen
    Ketika client mengirim request GET user monitoring ke endpoint "/api/v2/users" dengan filter unit "1"
    Maka response API user monitoring mengembalikan status 200 OK

  @API-006
  Skenario: Tambah user monitoring dengan payload lengkap valid
    Ketika client mengirim request POST user monitoring ke "/api/v2/users" dengan payload valid
    Maka response API user monitoring mengembalikan status 201 Created atau 200 OK
    Dan response API user monitoring memuat ID user yang baru dibuat

  @API-007
  Skenario: Tambah user monitoring tanpa field wajib fullname
    Ketika client mengirim request POST user monitoring ke "/api/v2/users" tanpa field fullname
    Maka response API user monitoring mengembalikan status 400 Bad Request
