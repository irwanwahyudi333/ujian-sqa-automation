# language: id
@api @divisi @api-divisi
Fitur: Pengujian Backend REST API Modul Divisi (Management Division)
  Sebagai sistem integrasi dan aplikasi HADIR
  Saya ingin memastikan endpoint REST API divisi beroperasi dengan andal, aman, dan memvalidasi payload
  Agar operasi CRUD data divisi berjalan konsisten dan terlindungi dari anomali data

  @API-001
  Skenario: Mendapatkan daftar seluruh divisi dengan token otorisasi valid
    Ketika client mengirim request GET ke endpoint divisi "/api/division" dengan token valid
    Maka response API mengembalikan status 200 OK
    Dan response API memuat array daftar divisi dan informasi total data

  @API-002
  Skenario: Request daftar divisi tanpa header Authorization
    Ketika client mengirim request GET ke endpoint divisi "/api/division" tanpa token otorisasi
    Maka response API mengembalikan status 401 Unauthorized

  @API-003
  Skenario: Menyaring daftar divisi berdasarkan parameter pencarian nama
    Ketika client mengirim request GET ke endpoint "/api/division" dengan query nama "Technology"
    Maka response API mengembalikan status 200 OK
    Dan seluruh data divisi pada response memuat kata "Technology"

  @API-004
  Skenario: Mengambil data divisi dengan parameter paginasi page dan limit
    Ketika client mengirim request GET ke endpoint "/api/division" dengan query "page=1&limit=5"
    Maka response API mengembalikan status 200 OK
    Dan jumlah item divisi dalam response tepat berjumlah 5 data

  @API-005
  Skenario: Membuat divisi baru dengan payload JSON yang valid
    Ketika client mengirim request POST ke endpoint divisi "/api/division" dengan nama "Quality Assurance"
    Maka response API divisi mengembalikan status sukses 200 atau 201
    Dan response API memuat data divisi yang baru dibuat

  @API-006
  Skenario: Validasi pembuatan divisi tanpa field nama
    Ketika client mengirim request POST ke endpoint "/api/division" dengan payload kosong "{}"
    Maka response API mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan field nama wajib diisi

  @API-007
  Skenario: Validasi pembuatan divisi dengan field nama bernilai null
    Ketika client mengirim request POST ke endpoint "/api/division" dengan field nama bernilai null
    Maka response API mengembalikan status 400 Bad Request

  @API-008
  Skenario: Validasi pembuatan divisi dengan field nama bernilai string kosong
    Ketika client mengirim request POST ke endpoint "/api/division" dengan field nama kosong ""
    Maka response API mengembalikan status 400 Bad Request

  @API-009
  Skenario: Penolakan pembuatan divisi dengan nama yang sudah terdaftar
    Ketika client mengirim request POST ke endpoint "/api/division" dengan nama divisi yang sudah ada
    Maka response API mengembalikan status penolakan 400 Bad Request atau 409 Conflict

  @API-010
  Skenario: Memperbarui nama divisi terdaftar menggunakan metode PATCH
    Ketika client mengirim request PATCH ke endpoint "/api/division/{id}" dengan nama baru valid
    Maka response API mengembalikan status 200 OK
    Dan response memuat nama divisi yang telah diperbarui

  @API-011
  Skenario: Pembaruan divisi dengan ID non-eksisten
    Ketika client mengirim request PATCH ke endpoint "/api/division/999999" dengan nama valid
    Maka response API mengembalikan status 404 Not Found

  @API-012
  Skenario: Pembaruan divisi dengan payload nama bernilai string kosong
    Ketika client mengirim request PATCH ke endpoint "/api/division/{id}" dengan nama kosong ""
    Maka response API mengembalikan status 400 Bad Request

  @API-013
  Skenario: Menghapus data divisi berdasarkan ID yang valid
    Ketika client mengirim request DELETE ke endpoint "/api/division/{id}" dengan ID yang sah
    Maka response API mengembalikan status 200 OK

  @API-014
  Skenario: Menghapus kembali ID divisi yang sudah terhapus
    Ketika client mengirim request DELETE ke endpoint "/api/division/{id}" yang sudah terhapus
    Maka response API mengembalikan status 404 Not Found

  @API-015
  Skenario: Validasi kepatuhan format schema JSON pada response daftar divisi
    Ketika client mengirim request GET ke endpoint "/api/division" dengan token valid
    Maka struktur JSON response mematuhi schema resmi entitas divisi
