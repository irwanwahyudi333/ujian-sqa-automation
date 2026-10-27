# language: id
@ui @self-registration @module-security
Fitur: Pengujian Keamanan Autentikasi dan Batasan Input
  Sebagai tim Quality Assurance dan Keamanan Sistem
  Saya ingin memastikan proteksi otentikasi peran dan sanitasi input berjalan ketat
  Agar data pendaftaran mobile terlindungi dari ancaman celah keamanan

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @SEC-001
  Skenario: Buka URL self-registration tanpa sesi login aktif
    Ketika pengguna tanpa sesi login membuka URL self-registration
    Maka sistem mengalihkan pengguna ke halaman login

  @SEC-002
  Skenario: Buka URL dengan sesi role non-administrator
    Ketika pengguna non-administrator membuka URL self-registration
    Maka sistem menolak akses atau membatasi menu

  @SEC-003
  Skenario: Injeksi SQL Injection pada parameter pencarian fullname
    Ketika admin menginjeksi SQL Injection pada pencarian
    Maka sistem memproses input secara aman tanpa error database

  @SEC-004
  Skenario: Injeksi payload XSS pada input filter modal
    Ketika admin menginjeksi payload XSS pada filter
    Maka payload XSS tidak dieksekusi di browser

  @SEC-005
  Skenario: Hapus data user menggunakan ID tidak valid via API
    Ketika request DELETE user dikirim dengan ID tidak valid
    Maka response API mengembalikan status penolakan 400 atau 404

  @SEC-006
  Skenario: Request DELETE data tanpa menyertakan auth token
    Ketika request DELETE user dikirim tanpa auth token
    Maka response API mengembalikan status penolakan 401 Unauthorized
