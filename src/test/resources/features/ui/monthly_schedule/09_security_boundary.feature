# language: id
@ui @monthly-schedule @security-boundary
Fitur: Pengujian Keamanan Input, Ketahanan Sanitasi, dan Batas Nilai (Boundary)
  Sebagai tim keamanan aplikasi HADIR
  Saya ingin memastikan seluruh input dan parameter jadwal terlindungi dari serangan injeksi dan anomali nilai
  Agar sistem terhindar dari celah XSS, SQLi, dan manipulasi data yang merusak

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @SEC-001
  Skenario: Sanitasi script XSS pada input pencarian
    Ketika admin memasukkan payload XSS "<script>alert('xss')</script>" pada pencarian
    Dan admin menekan tombol Cari jadwal
    Maka sistem merender input sebagai teks aman dan tidak mengeksekusi dialog alert

  @SEC-002
  Skenario: Ketahanan terhadap injeksi SQL pada pencarian
    Ketika admin memasukkan payload SQL Injection "' OR '1'='1" pada pencarian
    Dan admin menekan tombol Cari jadwal
    Maka sistem memperlakukan input sebagai teks pencarian biasa tanpa galat SQL

  @SEC-003
  Skenario: Ketahanan terhadap karakter tanda kutip tunggal/ganda
    Ketika admin memasukkan tanda kutip ganda dan kutip tunggal "'\"" pada pencarian
    Dan admin menekan tombol Cari jadwal
    Maka sistem tidak mengalami crash atau unhandled exception

  @SEC-004
  Skenario: Upload file Excel dengan payload nama XSS
    Ketika admin memilih file import "src/test/resources/testdata/schedule/xss_payload_name.xlsx"
    Dan admin menekan tombol Import
    Maka nama berkas disanitasi dengan benar di antarmuka web

  @SEC-005
  Skenario: Upload file executable disamarkan (.xlsx.exe)
    Ketika admin memilih file import "src/test/resources/testdata/schedule/malware.xlsx.exe"
    Dan admin menekan tombol Import
    Maka sistem segera menolak berkas dengan pesan validasi keamanan

  @SEC-006
  Skenario: Pencarian dengan karakter string sangat panjang (255+ char)
    Ketika admin memasukkan string acak sepanjang 300 karakter pada input pencarian
    Dan admin menekan tombol Cari jadwal
    Maka sistem memotong atau memproses string secara stabil tanpa buffer overflow

  @SEC-007
  Skenario: Pencarian dengan simbol unik dan karakter unicode/emoji
    Ketika admin memasukkan karakter emoji dan simbol unik "📅⚡🔥#*&" pada pencarian
    Dan admin menekan tombol Cari jadwal
    Maka halaman merespons dengan normal tanpa error encoding

  @SEC-008
  Skenario: Manipulasi ID jadwal non-eksisten pada operasi edit
    Ketika admin mencoba mengirim update jadwal dengan ID jadwal yang tidak ada
    Maka sistem menampilkan notifikasi error yang informatif

  @SEC-009
  Skenario: Manipulasi ID jadwal dengan format string acak tidak valid
    Ketika admin mencoba mengirim operasi jadwal dengan format ID rusak
    Maka request ditolak oleh sistem dengan validasi aman

  @SEC-010
  Skenario: Manipulasi ID jadwal non-eksisten pada operasi delete
    Ketika admin mencoba menghapus jadwal dengan ID non-eksisten
    Maka sistem menolak operasi dan menampilkan pesan kesalahan

  @SEC-011
  Skenario: Akses langsung ke halaman tanpa sesi login aktif
    Ketika pengguna tanpa sesi login mencoba mengakses "/management/monthly-schedule"
    Maka pengguna otomatis dialihkan ke halaman login "/authentication/login"

  @SEC-012
  Skenario: Akses API manajemen jadwal dengan token kedaluwarsa
    Ketika request jadwal dikirim dengan token otentikasi yang sudah expired
    Maka response API mengembalikan kode status 401 Unauthorized
