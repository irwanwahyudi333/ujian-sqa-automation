# language: id
@ui @user-monitoring @security-boundary
Fitur: Pengujian Keamanan, Batasan Input, dan Ketahanan Sistem User Monitoring
  Sebagai tim QA dan analis keamanan sistem HADIR
  Saya ingin menguji ketahanan form user monitoring terhadap serangan XSS, SQL Injection, dan batas karakter
  Agar data pengguna terlindungi dari eksploitasi dan celah keamanan aplikasi

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @SEC-001
  Skenario: Uji sanitasi script XSS pada field pencarian
    Ketika admin memasukkan kata kunci pencarian user monitoring "<script>alert('xss')</script>"
    Dan admin menekan tombol Search user monitoring
    Maka sistem memproses pencarian dengan aman tanpa memicu script popup
    Dan tabel user monitoring menampilkan pesan "Tidak Ada Data"

  @SEC-002
  Skenario: Uji ketahanan terhadap injeksi SQL pada field pencarian
    Ketika admin memasukkan kata kunci pencarian user monitoring "' OR '1'='1"
    Dan admin menekan tombol Search user monitoring
    Maka sistem memproses query sebagai teks pencarian biasa tanpa kegagalan sistem

  @SEC-003
  Skenario: Uji ketahanan terhadap karakter kutip tunggal dan ganda pada pencarian
    Ketika admin memasukkan karakter tanda kutip tunggal dan ganda pada pencarian user monitoring
    Dan admin menekan tombol Search user monitoring
    Maka halaman user monitoring tidak melempar error dan merespons normal

  @SEC-004
  Skenario: Uji sanitasi script XSS pada Nama User di modal Tambah User
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "<b onmouseover=alert(1)>XSS User</b>"
    Dan admin mengisi email user baru "xss.user@hadir.com"
    Dan admin mengisi password user baru "Password123!"
    Dan admin memilih unit user baru "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka tidak ada popup dialog script terpicu di peramban web
    Dan user bernama "<b onmouseover=alert(1)>XSS User</b>" tersimpan sebagai teks literal aman

  @SEC-005
  Skenario: Uji ketahanan terhadap injeksi SQL pada input Email saat tambah
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi form tambah user dengan nama "SQL Inject", email "admin'--@hadir.com", password "Pass123!", unit "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah menolak submit karena format email tidak valid

  @SEC-006
  Skenario: Uji input nama dengan string panjang batas 255 karakter
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user dengan teks panjang 255 karakter
    Dan admin mengisi email user baru "boundary255@hadir.com"
    Dan admin mengisi password user baru "Password123!"
    Dan admin memilih unit user baru "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka modal dialog Tambah User Monitoring tertutup dan data tersimpan tanpa buffer overflow

  @SEC-007
  Skenario: Pencarian dengan karakter emoji dan simbol unik
    Ketika admin memasukkan kata kunci pencarian user monitoring "🔥✨🔒"
    Dan admin menekan tombol Search user monitoring
    Maka sistem memproses pencarian simbol tanpa kegagalan encoding

  @SEC-008
  Skenario: Uji sanitasi script XSS pada form Edit User
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin mengubah nama user menjadi "<img src=x onerror=alert(1)>"
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka modal dialog Edit User Monitoring tertutup
    Dan tidak ada popup dialog script terpicu di peramban web

  @SEC-009
  Skenario: Uji manipulasi password dengan payload injeksi
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin memasukkan password baru "' OR '1'='1" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka sistem memproses pembaruan password secara aman tanpa error database

  @SEC-010
  Skenario: Uji batas string panjang 300 karakter pada nama saat edit
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin mengubah nama user dengan teks panjang 300 karakter
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka sistem memproses pembaruan nama dengan aman tanpa error antarmuka
