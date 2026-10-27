# language: id
@ui @register-v2 @v2-ui-layout
Fitur: A. Tampilan Awal dan Elemen UI Formulir Registrasi User v2
  Sebagai Administrator / HR di portal HADIR Management
  Saya ingin memastikan seluruh elemen formulir, tata letak, dan interaksi kontrol berfungsi dengan baik
  Agar pengalaman input data registrasi karyawan berlangsung intuitif dan andal

  Latar Belakang:
    Dengan pengguna membuka halaman registrasi user v2

  @UI-001
  Skenario: UI-001 Membuka URL registrasi user v2 dan memastikan formulir termuat
    Maka seluruh elemen utama input registrasi user v2 terlihat

  @UI-002
  Skenario: UI-002 Verifikasi keberadaan dan teks judul halaman
    Maka judul halaman registrasi v2 memuat "Registrasi User"

  @UI-003
  Skenario: UI-003 Verifikasi tombol tautan kembali ke daftar user
    Maka tombol kembali terlihat dan memuat label "Kembali ke Daftar User"

  @UI-004
  Skenario: UI-004 Verifikasi keterlihatan seksi 1 Account Information
    Maka seluruh field pada Seksi 1 Account Information terlihat

  @UI-005
  Skenario: UI-005 Verifikasi keterlihatan seksi 2 Work Information
    Maka seluruh field pada Seksi 2 Work Information terlihat

  @UI-006
  Skenario: UI-006 Verifikasi keterlihatan seksi 3 Pengaturan Absensi
    Maka seluruh field pada Seksi 3 Pengaturan Absensi terlihat

  @UI-007
  Skenario: UI-007 Verifikasi label seluruh field form terlihat
    Maka seluruh elemen utama input registrasi user v2 terlihat

  @UI-008
  Skenario: UI-008 Verifikasi teks placeholder seluruh input informatif
    Maka seluruh kontrol input memiliki placeholder yang informatif

  @UI-009
  Skenario: UI-009 Verifikasi tombol submit terlihat penuh dan berstatus aktif
    Maka tombol submit terlihat dan berstatus aktif

  @UI-010
  Skenario: UI-010 Verifikasi initial state input password adalah tersembunyi
    Maka karakter password v2 dalam keadaan tersembunyi

  @UI-011
  Skenario: UI-011 Verifikasi initial state field Atasan terkunci sebelum Jabatan dipilih
    Maka field atasan berstatus terkunci dengan placeholder "Pilih Jabatan Dulu"

  @UI-012
  Skenario: UI-012 Verifikasi initial value tipe shift bernilai default NON_SHIFT
    Maka nilai default tipe shift adalah "NON_SHIFT"

  @UI-013
  Skenario: UI-013 Verifikasi initial value jumlah cuti bernilai default 0
    Maka nilai default jumlah cuti adalah "0"

  @UI-014
  Skenario: UI-014 Verifikasi navigasi menggunakan tombol Tab keyboard
    Ketika pengguna menekan tombol Tab keyboard
    Maka fokus kursor berpindah secara berurutan

  @UI-015
  Skenario: UI-015 Verifikasi muat ulang halaman me-reset state formulir
    Ketika pengguna mengisi NIK v2 "D6240005"
    Dan pengguna memuat ulang halaman
    Maka state formulir tereset ke kondisi awal

  @UI-016
  Skenario: UI-016 Verifikasi tampilan formulir pada resolusi tablet
    Ketika pengguna mengatur resolusi layar ke mode tablet
    Maka tampilan formulir menyesuaikan mode tablet

  @UI-017
  Skenario: UI-017 Verifikasi tampilan formulir pada resolusi mobile
    Ketika pengguna mengatur resolusi layar ke mode mobile
    Maka tampilan formulir menyesuaikan mode mobile

  @UI-018
  Skenario: UI-018 Periksa console log browser saat halaman registrasi dibuka
    Maka console log browser bersih dari error fatal
