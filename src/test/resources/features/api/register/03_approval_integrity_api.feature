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

  @API-SEC-001 @api @security @mass-assignment
  Skenario: Backend wajib menolak atau mengabaikan atribut isVerified saat registrasi akun baru
    Ketika client mengirim request registrasi dengan menyisipkan atribut "isVerified" bernilai true
    Maka response API menolak pendaftaran atau tidak mengaktifkan akun secara otomatis tanpa approval

  @API-SEC-002 @api @security @bola-idor
  Skenario: Backend wajib melarang akses modifikasi akun lain oleh pengguna dengan hak akses biasa
    Ketika client melakukan pengujian otorisasi modifikasi akun lain dengan token pengguna biasa
    Maka response API mengembalikan status penolakan hak akses 403 atau 401

  @API-SEC-003 @api @security @jwt-session
  Skenario: Verifikasi masa kedaluwarsa token JWT autentikasi tidak melebihi batas aman kebijakan sesi
    Ketika client menganalisis durasi masa berlaku token JWT dari endpoint autentikasi
    Maka durasi kedaluwarsa token JWT tidak boleh melebihi batas maksimal 86400 detik

  @API-BUG-004 @api @negative @upload-contract
  Skenario: Backend wajib mengembalikan HTTP 400 atau 415 alih-alih 500 saat mengunggah format berkas tidak diizinkan
    Ketika client mengunggah berkas tidak diizinkan "test.html" ke endpoint upload
    Maka response API mengembalikan status kontrak kesalahan klien 400 atau 415 dan bukan 500

