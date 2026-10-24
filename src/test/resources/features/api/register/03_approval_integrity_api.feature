# language: id
@api @module-approval-integrity @data-integrity
Fitur: Pengujian Integritas Endpoint REST API Approval dan Pembaharuan Pengguna
  Sebagai sistem backend HADIR
  Saya ingin memastikan endpoint update dan approval pengguna menegakkan keunikan NIK dan Email
  Serta memvalidasi URL aset foto selfie pendaftar dapat diakses

  @API-BUG-001 @api @image-endpoint
  Skenario: Verifikasi aksesibilitas berkas foto selfie pada endpoint static uploads
    Ketika client memeriksa aksesibilitas endpoint static foto selfie dengan ID terunggah
    Maka endpoint upload mengembalikan status HTTP 200 OK dengan format gambar valid

  @API-BUG-002 @api @negative @nik-duplicate
  Skenario: Backend wajib menolak update atau approval pengguna dengan NIK yang sudah terdaftar
    Ketika client mengirim request update user dengan NIK duplikat yang sudah terdaftar "3201234567890002"
    Maka response API mengembalikan status penolakan kesalahan duplikasi 400 atau 409

  @API-BUG-003 @api @negative @email-duplicate
  Skenario: Backend wajib menolak update atau approval pengguna dengan Email yang sudah terdaftar
    Ketika client mengirim request update user dengan Email duplikat yang sudah terdaftar "qa.approved.user@dikahadir.com"
    Maka response API mengembalikan status penolakan kesalahan duplikasi 400 atau 409
