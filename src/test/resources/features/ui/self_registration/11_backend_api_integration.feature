# language: id
@ui @self-registration @module-api-integration
Fitur: Pengujian Backend REST API dan Integrasi Sistem
  Sebagai tim Quality Assurance dan Pengembang Backend
  Saya ingin memvalidasi endpoint REST API manajemen registrasi mandiri
  Agar kontrak API, integritas data, dan performa backend terjamin

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @API-001
  Skenario: Panggil endpoint GET users dengan parameter is_mobile true
    Ketika client memanggil API GET users dengan parameter valid
    Maka API mengembalikan status 200 OK dan daftar user valid

  @API-002
  Skenario: Panggil endpoint GET users dengan parameter filter nama
    Ketika client memanggil API GET users dengan filter nama "Irwan"
    Maka API mengembalikan data user yang sesuai dengan filter nama

  @API-003
  Skenario: Panggil endpoint GET users dengan parameter filter NIK
    Ketika client memanggil API GET users dengan filter NIK "3201"
    Maka API mengembalikan data user yang sesuai dengan filter NIK

  @API-004
  Skenario: Panggil endpoint GET users dengan parameter paginasi per_page 5
    Ketika client memanggil API GET users dengan batas paginasi 5
    Maka API mengembalikan data dengan batas yang sesuai

  @API-005
  Skenario: Panggil endpoint DELETE user dengan ID valid
    Ketika client memanggil API DELETE user dengan ID valid
    Maka API mengembalikan status sukses 200 OK

  @API-006
  Skenario: Panggil endpoint DELETE user dengan ID yang sudah terhapus
    Ketika client memanggil API DELETE user dengan ID yang sudah terhapus
    Maka API mengembalikan status 404 Not Found

  @API-007
  Skenario: Panggil endpoint PUT users batch dengan payload valid
    Ketika client memanggil API batch update dengan payload valid
    Maka API mengembalikan status 200 OK dan seluruh user terupdate

  @API-008
  Skenario: Panggil endpoint PUT users batch dengan array ID kosong
    Ketika client memanggil API batch update dengan ID kosong
    Maka API mengembalikan status validasi 400 Bad Request

  @API-009
  Skenario: Panggil endpoint GET dropdown departemen
    Ketika client memanggil API dropdown departemen
    Maka API mengembalikan opsi departemen valid

  @API-010
  Skenario: Panggil endpoint GET dropdown level jabatan
    Ketika client memanggil API dropdown job level
    Maka API mengembalikan opsi level jabatan valid

  @API-011
  Skenario: Panggil endpoint GET dropdown lokasi kerja
    Ketika client memanggil API dropdown lokasi kerja
    Maka API mengembalikan opsi titik lokasi valid

  @API-012
  Skenario: Pengecekan response time endpoint GET users
    Ketika client memeriksa waktu respon endpoint GET users
    Maka waktu respon berada di bawah ambang batas 2000 ms
