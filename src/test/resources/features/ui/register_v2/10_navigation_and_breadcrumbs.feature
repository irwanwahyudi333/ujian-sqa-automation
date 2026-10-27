# language: id
@ui @register-v2 @v2-navigation
Fitur: J. Navigasi dan Breadcrumb Halaman Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin menavigasi antar halaman melalui tombol kembali, breadcrumb, dan kontrol browser
  Agar pengalaman berpindah halaman berlangsung mulus dan konsisten

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @NAV-001
  Skenario: NAV-001 Menekan tombol kembali mengarahkan ke halaman daftar user
    Ketika pengguna menekan tombol kembali ke daftar user
    Maka pengguna dialihkan ke halaman daftar user

  @NAV-002
  Skenario: NAV-002 Mengklik breadcrumb Management
    Ketika pengguna mengklik breadcrumb "Management"
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAV-003
  Skenario: NAV-003 Mengklik breadcrumb User
    Ketika pengguna mengklik breadcrumb "User"
    Maka nilai pada field form tetap utuh tidak terhapus

  @NAV-004
  Skenario: NAV-004 Navigasi kembali melalui kontrol tombol browser
    Ketika pengguna menekan tombol kembali ke daftar user
    Maka pengguna dialihkan ke halaman daftar user

  @NAV-005
  Skenario: NAV-005 Akses URL registrasi user v2 dengan proteksi sesi
    Maka seluruh elemen utama input registrasi user v2 terlihat

  @NAV-006
  Skenario: NAV-006 Akses URL registrasi user v2 dengan sesi admin aktif
    Maka seluruh elemen utama input registrasi user v2 terlihat
