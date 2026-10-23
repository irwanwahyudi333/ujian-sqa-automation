# language: id
@ui @module-login @login-business-flow
Fitur: Pengujian Alur Bisnis dan Fungsionalitas Login HADIR
  Sebagai pengguna terdaftar atau peserta magang
  Saya ingin menjalankan proses login dengan berbagai skenario alur kerja
  Agar sistem dapat mengautentikasi dan mengarahkan hak akses dengan tepat

  Latar Belakang:
    Dengan pengguna membuka halaman login

  @positive @happy-path @log-flow-001 @lean
  Skenario: Login berhasil dengan akun aktif yang telah disetujui (Happy Path)
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem berhasil login dan mengalihkan pengguna ke dashboard

  @negative @log-flow-002 @lean
  Skenario: Submit saat seluruh field formulir kosong
    Ketika pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Email dan password wajib diisi"

  @negative @log-flow-003
  Skenario: Submit hanya mengisi Email valid
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Password wajib diisi"

  @negative @log-flow-004
  Skenario: Submit hanya mengisi Password valid
    Ketika pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan peringatan login "Email wajib diisi"

  @negative @pending-approval @log-flow-005 @lean
  Skenario: Login dengan akun dalam status Pending Approval Admin
    Ketika pengguna mengisi email login "raxaf58877@backyr.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan notifikasi status "Akun Anda sedang menunggu persetujuan admin"

  @negative @log-flow-006
  Skenario: Login dengan akun yang berstatus Rejected atau Ditolak
    Ketika pengguna mengisi email login "rejected.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @negative @log-flow-007
  Skenario: Login dengan akun yang berstatus Suspended atau Dibekukan
    Ketika pengguna mengisi email login "suspended.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @negative @log-flow-008
  Skenario: Login dengan email yang belum terdaftar di sistem
    Ketika pengguna mengisi email login "unregistered.user999@example.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @negative @log-flow-009 @lean
  Skenario: Login dengan email terdaftar tetapi password salah
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongPassword123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @negative @log-flow-010
  Skenario: Login dengan kombinasi email dan password keduanya salah
    Ketika pengguna mengisi email login "random.user@example.com"
    Dan pengguna mengisi password login "RandomPassword123"
    Dan pengguna menekan tombol Masuk
    Maka sistem menampilkan pesan kesalahan login "Email atau kata sandi tidak cocok"

  @functional @log-flow-011
  Skenario: Submit login menggunakan tombol keyboard Enter pada field password
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Enter pada field password login
    Maka sistem berhasil login dan mengalihkan pengguna ke dashboard

  @functional @log-flow-012
  Skenario: Klik ganda cepat pada tombol Masuk tidak memicu pengiriman ganda
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk dua kali secara cepat
    Maka sistem hanya memproses satu kali permintaan login

  @functional @log-flow-013
  Skenario: Indikator proses loading saat request login dikirimkan
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka tombol Masuk menampilkan status proses atau disabled sesaat

  @functional @log-flow-014
  Skenario: Login berhasil lalu akses halaman dashboard
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka sistem mengalihkan pengguna ke URL dashboard aplikasi

  @security @log-flow-015
  Skenario: Akses langsung rute terproteksi dashboard tanpa login
    Ketika pengguna mengakses langsung rute dashboard tanpa sesi login
    Maka sistem secara otomatis mengalihkan pengguna kembali ke halaman Login

  @functional @log-flow-016
  Skenario: Proses Logout dari aplikasi menghapus sesi aktif
    Ketika pengguna berada di dashboard dengan sesi login aktif
    Dan pengguna melakukan proses logout
    Maka sesi login berakhir dan pengguna dialihkan kembali ke formulir login

  @security @log-flow-017
  Skenario: Navigasi Back browser setelah Logout tidak membuka sesi kembali
    Ketika pengguna telah berhasil logout dari aplikasi
    Dan pengguna menekan tombol navigasi Back pada browser
    Maka sistem tetap menampilkan halaman login dan menolak akses dashboard

  @resilience @log-flow-018
  Skenario: Refresh halaman saat proses autentikasi berlangsung
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna me-refresh halaman login
    Maka formulir login kembali bersih dan siap digunakan

  @functional @log-flow-019
  Skenario: Buka halaman login di multi-tab browser secara paralel
    Ketika pengguna membuka form login di tab browser baru
    Maka halaman login di tab baru terbuka sempurna dan independen

  @functional @log-flow-020
  Skenario: Retensi input email setelah autentikasi gagal
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongPassword123"
    Dan pengguna menekan tombol Masuk
    Maka nilai teks pada field email tetap dipertahankan

  @security @log-flow-021
  Skenario: Pembersihan field password setelah autentikasi gagal
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongPassword123"
    Dan pengguna menekan tombol Masuk
    Maka field password otomatis dikosongkan kembali demi keamanan

  @functional @log-flow-022
  Skenario: Pesan notifikasi error banner dapat dilihat dengan jelas
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "WrongPassword123"
    Dan pengguna menekan tombol Masuk
    Maka notifikasi alert error login tampil dengan jelas di antarmuka

  @security @log-flow-023
  Skenario: Session persistence token tersimpan di penyimpanan browser
    Ketika pengguna mengisi email login "qa.approved.user@dikahadir.com"
    Dan pengguna mengisi password login "P@ssw0rdMagang123"
    Dan pengguna menekan tombol Masuk
    Maka token autentikasi tersimpan aman di storage browser

  @security @log-flow-024
  Skenario: Penanganan session timeout pada sesi pengguna
    Ketika masa berlaku sesi pengguna telah berakhir
    Maka permintaan akses ke data terproteksi ditolak dengan status unauthorized

  @security @log-flow-025
  Skenario: Uji konsistensi alur login pada sesi browser terisolasi
    Ketika pengguna melakukan login pada jendela browser terpisah
    Maka setiap sesi login berjalan terisolasi tanpa saling merusak
