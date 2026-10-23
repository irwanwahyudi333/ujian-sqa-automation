# language: id
@ui @module-login @login-email-validation
Fitur: Validasi Field Email pada Halaman Login
  Sebagai sistem autentikasi
  Saya ingin memastikan format dan masukan email divalidasi dengan ketat
  Agar integritas akun pengguna tetap terjaga dari input tidak valid

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @positive @email-log-001 @lean
  Skenario: Input email format valid dan terdaftar
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem berhasil memproses verifikasi email akun

  @negative @email-log-002 @lean
  Skenario: Submit saat field Email kosong
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Email wajib diisi"

  @negative @email-log-003
  Skenario: Submit field Email hanya berisi spasi
    Ketika pengguna mengisi email login "   "
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Email wajib diisi"

  @positive @email-log-008
  Skenario: Email diawali dan diakhiri spasi dilakukan auto-trimming
    Ketika pengguna mengisi email login "  qa.approved.user@dikahadir.com  "
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem berhasil login dan mengalihkan pengguna ke dashboard

  @positive @email-log-009
  Skenario: Email menggunakan huruf kapital penuh bersifat case-insensitive
    Ketika pengguna mengisi email login "QA.APPROVED.USER@DIKAHADIR.COM"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem berhasil login dan mengalihkan pengguna ke dashboard

  @negative @email-matrix
  Skenario konsep: Validasi variasi format email pada form login (<test_id> - <keterangan>)
    Ketika pengguna mengisi email login "<email_input>"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menahan login atau menampilkan pesan kesalahan "<pesan_diharapkan>"

    Contoh:
      | test_id        | email_input                   | keterangan                          | pesan_diharapkan             |
      | EMAIL-LOG-004  | qa.user.dikahadir.com         | Email tanpa karakter @              | Format email tidak valid     |
      | EMAIL-LOG-005  | qa.user@                      | Email tanpa bagian domain           | Format email tidak valid     |
      | EMAIL-LOG-006  | @dikahadir.com                | Email tanpa username local-part     | Format email tidak valid     |
      | EMAIL-LOG-007  | qa user@dikahadir.com         | Email dengan spasi di tengah        | Format email tidak valid     |
      | EMAIL-LOG-010  | qa.user@corp.dikahadir.com    | Email dengan subdomain sah          | Email atau kata sandi        |
      | EMAIL-LOG-011  | qa.user+magang@dikahadir.com  | Email dengan plus-addressing        | Email atau kata sandi        |
      | EMAIL-LOG-012  | user_sangat_panjang_sekali_melebihi_batas_normal_input_formulir_autentikasi_sistem_hadir@dikahadir.com | Email panjang ekstrem 100+ karakter | Format email tidak valid     |
      | EMAIL-LOG-013  | qa<user>!#$%@dikahadir.com    | Email mengandung simbol ilegal      | Format email tidak valid     |
      | EMAIL-LOG-014  | qa.úsèr@dikahadir.com         | Email mengandung karakter non-ASCII | Format email tidak valid     |
      | EMAIL-LOG-015  | qa😀@dikahadir.com            | Email mengandung karakter emoji     | Format email tidak valid     |
